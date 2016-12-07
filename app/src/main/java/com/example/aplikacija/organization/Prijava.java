package com.example.aplikacija.organization;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.app.TaskStackBuilder;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

public class Prijava extends AppCompatActivity implements
        GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks,
        View.OnClickListener{

    public static final String PRIJAVA_POSITION="PRIJAVA POS";
    public static final String LOGOUT="LOGOUT";
    private static final String TAG = "SignInActivity";
    private static final int RC_SIGN_IN = 9001;

    private ApplicationMy app;
    private ProgressDialog mProgressDialog;
    private GoogleApiClient mGoogleApiClient;
    private TextView mStatusTextView;
    private boolean forceLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prijava);

        app = (ApplicationMy) getApplication();
        mStatusTextView = (TextView) findViewById(R.id.status);

        findViewById(R.id.sign_in_button).setOnClickListener(this);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail().requestProfile()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso).addConnectionCallbacks(this)
                .build();

        SignInButton signInButton = (SignInButton) findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        signInButton.setScopes(gso.getScopeArray());

        Intent a = getIntent(); //added
        forceLogout = a.getBooleanExtra(LOGOUT,false);
        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
        if (opr.isDone()) {
            // If the user's cached credentials are valid, the OptionalPendingResult will be "done"
            // and the GoogleSignInResult will be available instantly.
            Log.d(TAG, "Got cached sign-in");
            GoogleSignInResult result = opr.get();
            handleSignInResult(result);
        } else {
            // If the user has not previously signed in on this device or the sign-in has expired,
            // this asynchronous branch will attempt to sign in the user silently.  Cross-device
            // single sign-on will occur in this branch.
            //showProgressDialog();
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(GoogleSignInResult googleSignInResult) {
                    //hideProgressDialog();
                    handleSignInResult(googleSignInResult);
                }
            });
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop(): disconnecting");
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
    }


    @Override
    public void onStart() {
        super.onStart();


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
        Log.d(TAG, "handleSignInResult uspeh:" + result.isSuccess());
        if (result.isSuccess()) {
            app.setAcct(result.getSignInAccount());
            mStatusTextView.setText(getString(R.string.signed_in_fmt, app.getAcct().getDisplayName() + " " ));
            updateUI(true);
            if (!forceLogout) { //automatic open activity
                Intent st = new Intent(this, ActivityDogodkiMain.class);
                TextView a = (TextView)findViewById(R.id.status);
                String str = a.getText().toString();
                st.putExtra("Username",str);

                NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
                builder.setSmallIcon(R.drawable.ic_error_outline_black);

                //int nekaj = getIntent().getExtras().getInt("id");

                builder.setContentText("Naredi pregled in se UDELEŽI!");
                builder.setContentTitle("Na sporedu je 6 prihajajočih dogodkov!");
                builder.setLights(Color.BLUE, 500, 500);
                long[] vibrate = { 0, 100, 200, 300 };
                builder.setVibrate(vibrate);
                builder.setDefaults(Notification.DEFAULT_SOUND);
                //Uri path = Uri.parse("android.resource://com.androidbook.samplevideo/" + R.raw.notify);
                //builder.setSound(path);
                Intent intent = new Intent(this,ActivityDogodkiMain.class);
                TaskStackBuilder stack = TaskStackBuilder.create(this);
                stack.addParentStack(ActivityDogodkiMain.class);
                stack.addNextIntent(intent);
                PendingIntent pending = stack.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
                builder.setContentIntent(pending);
                NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                manager.notify(0,builder.build());

                startActivity(st);
                // }
                finish();
            }

            //if (first) {
            //Prevents back http://stackoverflow.com/questions/8631095/android-preventing-going-back-to-the-previous-activity
            //       }
        } else {
            // Signed out, show unauthenticated UI.
            Log.d(TAG, "handleSignInResult false!:" + result.toString());
            app.setAcct(null);
            updateUI(false);
        }
    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void signOut() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        // [START_EXCLUDE]
                        updateUI(false);
                        // [END_EXCLUDE]
                    }
                });
    }
    private void revokeAccess() {
        Auth.GoogleSignInApi.revokeAccess(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        // [START_EXCLUDE]
                        updateUI(false);
                        // [END_EXCLUDE]
                    }
                });
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        // An unresolvable error has occurred and Google APIs (including Sign-In) will not
        // be available.
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
    }

    private void updateUI(boolean signedIn) {
        if (signedIn) {
            findViewById(R.id.sign_in_button).setVisibility(View.GONE);
            //findViewById(R.id.sign_out_and_disconnect).setVisibility(View.VISIBLE);
        } else {
            mStatusTextView.setText("Odjavljen");

            findViewById(R.id.sign_in_button).setVisibility(View.VISIBLE);
            //findViewById(R.id.sign_out_and_disconnect).setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in_button:
                signIn();
                //notification();
                break;
            /*case R.id.sign_out_button:
                signOut();
                break;
            case R.id.disconnect_button:
                revokeAccess();
                break;*/
        }
    }

    @Override
    public void onConnected(Bundle bundle) {
        //only on creation!
        if (forceLogout) {
            forceLogout = false; //only when it is called from different activity
            signOut();
        }

    }
    @Override
    public void onConnectionSuspended(int i) {
    }

    /*public void notification(){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.drawable.ic_error_outline_black);
        builder.setContentText("Na sporedu je 6 prihajajočih dogodkov!");
        builder.setContentTitle("Naredi pregled in se UDELEŽI!");
        Intent intent = new Intent(this,ActivityDogodkiMain.class);
        TaskStackBuilder stack = TaskStackBuilder.create(this);
        stack.addParentStack(ActivityDogodkiMain.class);
        stack.addNextIntent(intent);
        PendingIntent pending = stack.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pending);
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0,builder.build());
    }*/
}
