package fettle.iiitd.com.fettle.Activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.fitness.Fitness;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import fettle.iiitd.com.fettle.Classes.Utils;
import fettle.iiitd.com.fettle.R;

public class ProfileInput extends AppCompatActivity {
//    public static GoogleApiClient mClient = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_input);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        mClient=buildFitnessClient();

        ParseUser user = ParseUser.getCurrentUser();
        if (user != null) {
            try {
                ((EditText) findViewById(R.id.input_name)).setText(user.getString("name"));
                ((EditText) findViewById(R.id.input_age)).setText(user.getInt("age") + "");
                ((EditText) findViewById(R.id.input_height)).setText(user.getInt("height") + "");
                ((EditText) findViewById(R.id.input_weight)).setText(user.getInt("weight") + "");
                ((EditText) findViewById(R.id.input_weight_target)).setText(user.getInt("weight_target") + "");
                ((CheckBox) findViewById(R.id.checkboxMale)).setChecked(user.getBoolean("male"));
                ((CheckBox) findViewById(R.id.checkboxFemale)).setChecked(!user.getBoolean("male"));
                List<String> array = Arrays.asList(getResources().getStringArray(R.array.itemsActive));
                ((Spinner) findViewById(R.id.spinnerActive)).setSelection(array.indexOf(user.getString("active")));
                array = Arrays.asList(getResources().getStringArray(R.array.itemsExercise));
                ((Spinner) findViewById(R.id.spinnerExercise1)).setSelection(array.indexOf(user.getString("exercise1")));
                ((Spinner) findViewById(R.id.spinnerExercise2)).setSelection(array.indexOf(user.getString("exercise2")));
            } catch (Exception e) {
            }
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void onUpdate(View view) {
        String name = ((EditText) findViewById(R.id.input_name)).getText().toString();
        int age = Integer.parseInt(((EditText) findViewById(R.id.input_age)).getText().toString());
        int height = Integer.parseInt(((EditText) findViewById(R.id.input_height)).getText().toString());
        int weight = Integer.parseInt(((EditText) findViewById(R.id.input_weight)).getText().toString());
        int weight_target = Integer.parseInt(((EditText) findViewById(R.id.input_weight_target)).getText().toString());
        boolean isMale = ((CheckBox) findViewById(R.id.checkboxMale)).isChecked();
        String active = (String) ((Spinner) findViewById(R.id.spinnerActive)).getSelectedItem();
        String exercise1 = (String) ((Spinner) findViewById(R.id.spinnerExercise1)).getSelectedItem();
        String exercise2 = (String) ((Spinner) findViewById(R.id.spinnerExercise2)).getSelectedItem();

        ParseUser user = ParseUser.getCurrentUser();

        if (user == null) {
            Log.d("abc", "user null");
        }
        if (user.getInt("height") != height || user.getInt("weight") != weight) {
            ParseObject parseObject = new ParseObject("Track");
            parseObject.put("user", user);
            parseObject.put("height", height);
            parseObject.put("weight", weight);
            parseObject.put("CreatedAt", Calendar.getInstance().getTime());
            parseObject.saveEventually();
        }

        user.put("name", name);
        user.put("age", age);
        user.put("height", height);
        user.put("weight", weight);
        user.put("weight_target", weight_target);
        user.put("male", isMale);
        user.put("active", active);
        user.put("exercise1", exercise1);
        user.put("exercise2", exercise2);
        user.saveEventually();

        new GetTargetWeightTask(this).execute();

    }

    private GoogleApiClient buildFitnessClient() {
        final String TAG = "BasicHistoryApi";
        GoogleApiClient mClient = null;
        // Create the Google API Client1
        mClient = new GoogleApiClient.Builder(this)
                .addApi(Fitness.HISTORY_API)
                .addScope(new Scope(Scopes.FITNESS_ACTIVITY_READ_WRITE))
                .addConnectionCallbacks(
                        new GoogleApiClient.ConnectionCallbacks() {
                            @Override
                            public void onConnected(Bundle bundle) {
                                Log.d(TAG, "Connected!!!");

                            }

                            @Override
                            public void onConnectionSuspended(int i) {
                                // If your connection to the sensor gets lost at some point,
                                // you'll be able to determine the reason and react to it here.
                                if (i == GoogleApiClient.ConnectionCallbacks.CAUSE_NETWORK_LOST) {
                                    Log.d(TAG, "Connection lost.  Cause: Network Lost.");
                                } else if (i == GoogleApiClient.ConnectionCallbacks.CAUSE_SERVICE_DISCONNECTED) {
                                    Log.d(TAG, "Connection lost.  Reason: Service Disconnected");
                                }
                            }
                        }
                )
                .enableAutoManage(this, 0, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(ConnectionResult result) {
                        Log.i(TAG, "Google Play services connection failed. Cause: " +
                                result.toString());
                    }
                })
                .build();
        Log.d("ac", "dd");
        return mClient;
    }

    public class GetTargetWeightTask extends AsyncTask<Void, Void, Void> {

        public Activity activity;
        ProgressDialog pd;
        Map<String, String> mapCalAct;
        Map<String, String> mapCalTarget;

        public GetTargetWeightTask(Activity activity) {
            this.activity = activity;
        }

        @Override
        protected void onPreExecute() {
            pd = new ProgressDialog(activity);
            pd.setIndeterminate(true);
            pd.setTitle("wait");
            pd.setMessage("wait for data to process");
            pd.show();
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            ParseUser user = ParseUser.getCurrentUser();
            mapCalAct = Utils.CaloriesToActivity(user.getInt("weight"), 10);
            mapCalTarget = Utils.CalTarget(user.getInt("age"), user.getInt("weight"), user.getInt("height"), user.getBoolean("male") ? "m" : "f");
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            pd.dismiss();
            ParseUser user = ParseUser.getCurrentUser();

            if (user.getInt("weight") > user.getInt("weight_target")) {
                try {
                    Utils.storePref(activity, Utils.DAILY_CALORIE_KEY, Integer.parseInt(mapCalTarget.get("lose_0.5kg").replace(",", "")));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (user.getInt("weight") < user.getInt("weight_target")) {
                try {
                    Utils.storePref(activity, Utils.DAILY_CALORIE_KEY, Integer.parseInt(mapCalTarget.get("gain_0.5kg").replace(",", "")));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    Utils.storePref(activity, Utils.DAILY_CALORIE_KEY, Integer.parseInt(mapCalTarget.get("maintain_weight").replace(",", "")));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            try {
                String walk10 = mapCalAct.get("Walk");
                String[] str = walk10.split(":");
                int m = Integer.parseInt(str[0]);
                int s = Integer.parseInt(str[1]);

                Utils.storePref(activity, Utils.WALK_10_CALORIES_KEY, m * 60 + s);

                String run10 = mapCalAct.get("Run");
                str = run10.split(":");
                m = Integer.parseInt(str[0]);
                s = Integer.parseInt(str[1]);

                Utils.storePref(activity, Utils.RUN_10_CALORIES_KEY, m * 60 + s);

                String stairs10 = mapCalAct.get("Stairs");
                str = stairs10.split(":");
                m = Integer.parseInt(str[0]);
                s = Integer.parseInt(str[1]);

                Utils.storePref(activity, Utils.STAIRS_10_CALORIES_KEY, m * 60 + s);

                String bicycle10 = mapCalAct.get("Bicycle");
                str = bicycle10.split(":");
                m = Integer.parseInt(str[0]);
                s = Integer.parseInt(str[1]);

                Utils.storePref(activity, Utils.BICYCLE_10_CALORIES_KEY, m * 60 + s);
            } catch (Exception e) {
                e.printStackTrace();
            }

            super.onPostExecute(aVoid);

            if (getIntent().getBooleanExtra("landing", false)) {
                startActivity(new Intent(activity, LandingActivity.class));
                finish();
            } else
                onBackPressed();
        }
    }

}