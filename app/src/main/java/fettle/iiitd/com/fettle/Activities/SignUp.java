package fettle.iiitd.com.fettle.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.parse.ParseException;
import com.parse.ParseUser;

import fettle.iiitd.com.fettle.R;

/**
 * Created by danishgoel on 24/03/16.
 */
public class SignUp extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, View.OnClickListener {

    private static final int RC_SIGN_IN = 9001;
    private static final String TAG = "SignUpActivity";

    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        if (ParseUser.getCurrentUser() != null) {
            startActivity(new Intent(this, LandingActivity.class));
            finish();
        }
        String emailId = "danishdgoel@gmail.com";
        ParseUser user;
        try {
            user = ParseUser.logIn(emailId, "password");
        } catch (ParseException e) {
            user = new ParseUser();
            user.setUsername(emailId);
            user.setPassword("password");
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

        // Configure sign-in to request the user's ID, email address, and basic
// profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleApiClient with access to the Google Sign-In API and the
// options specified by gso.
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        findViewById(R.id.sign_in_button).setOnClickListener(this);

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

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
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            String emailId = acct.getEmail();
            ParseUser user;
            try {
                user = ParseUser.logIn(emailId, "password");
            } catch (ParseException e) {
                user = new ParseUser();
                user.setUsername(emailId);
                user.setPassword("password");
                user.put("name", acct.getDisplayName());
                if (acct.getPhotoUrl() != null)
                    user.put("pic", acct.getPhotoUrl().toString());
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
        } else {
            // Signed out, show unauthenticated UI.
//            updateUI(false);
        }
    }

}
