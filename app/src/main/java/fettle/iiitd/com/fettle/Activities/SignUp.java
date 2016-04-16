package fettle.iiitd.com.fettle.Activities;

import android.accounts.AccountManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.common.AccountPicker;
import com.parse.ParseException;
import com.parse.ParseUser;

import fettle.iiitd.com.fettle.R;

/**
 * Created by danishgoel on 24/03/16.
 */
public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private static final int RC_SIGN_IN = 9001;
    private static final int ACCOUNT_PICKER_REQUEST_CODE = 100;
    private static final String TAG = "SignUpActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (ParseUser.getCurrentUser() != null) {
            startActivity(new Intent(this, LandingActivity.class));
            finish();
            return;
        }
//        String emailId = "danishdgoel@gmail.com";
//        //TODO Danish/Manan remove this hardcoding before distributing
//        String emailId = "manang168@gmail.com";
//        ParseUser user;
//        try {
//            user = ParseUser.logIn(emailId, "password");
//            startActivity(new Intent(this, LandingActivity.class));
//            finish();
//        } catch (ParseException e) {
//            user = new ParseUser();
//            user.setUsername(emailId);
//            user.setPassword("password");
//            try {
//                user.signUp();
//            } catch (ParseException e1) {
//                e1.printStackTrace();
//            }
//        }
//        if (user != null) {
//            startActivity(new Intent(this, ProfileInput.class).putExtra("landing", true));
//            finish();
//        }

        // Configure sign-in to request the user's ID, email address, and basic
// profile. ID and basic profile are included in DEFAULT_SIGN_IN.
//        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestEmail()
//                .build();

        // Build a GoogleApiClient with access to the Google Sign-In API and the
// options specified by gso.


        findViewById(R.id.sign_in_button).setOnClickListener(this);

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


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in_button:
                signIn();
                break;
        }
    }

    private void signIn() {
        Intent intent = AccountPicker.newChooseAccountIntent(null, null,
                new String[]{"com.google"}, true, null, null, null,
                null);
        startActivityForResult(intent, ACCOUNT_PICKER_REQUEST_CODE);
//        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
//        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ACCOUNT_PICKER_REQUEST_CODE) {
            try {
                String mEmail = data.getStringExtra(AccountManager.KEY_ACCOUNT_NAME);
                handleSignInResult(mEmail);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
//        if (requestCode == RC_SIGN_IN) {
//            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
//            handleSignInResult(result);
//        }
    }


    private void handleSignInResult(String email) {


        String emailId = email;
        ParseUser user;
        try {
            user = ParseUser.logIn(emailId, "password");
        } catch (ParseException e) {
            user = new ParseUser();
            user.setUsername(emailId);
            user.setPassword("password");
            user.put("name", "");
//                if (acct.getPhotoUrl() != null)
//                    user.put("pic", acct.getPhotoUrl().toString());
            try {
                user.signUp();
            } catch (ParseException e1) {
                e1.printStackTrace();
            }
        }
        if (user != null) {
            startActivity(new Intent(this, ProfileInput.class).putExtra("landing", true));
            finish();
        }
//            mStatusTextView.setText(getString(R.string.signed_in_fmt, acct.getDisplayName()));
//            updateUI(true);

    }

}
