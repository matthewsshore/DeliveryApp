package com.example.skantoro.deliveryapp_testing;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.skantoro.myapplication.backend.myApi.MyApi;
import com.example.skantoro.myapplication.backend.myApi.model.AddressCollection;
import com.example.skantoro.myapplication.backend.myApi.model.Bid;
import com.example.skantoro.myapplication.backend.myApi.model.ItemCollection;
import com.example.skantoro.myapplication.backend.myApi.model.MyBean;
import com.example.skantoro.myapplication.backend.myApi.model.MyBeanCollection;
import com.example.skantoro.myapplication.backend.myApi.model.Order;
import com.example.skantoro.myapplication.backend.myApi.model.OrderCollection;
import com.example.skantoro.myapplication.backend.myApi.model.UserCollection;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        View.OnClickListener {


    private static final String TAG = "MainActivity";

    /* RequestCode for resolutions involving sign-in */
    private static final int RC_SIGN_IN = 9001;

    /* Keys for persisting instance variables in savedInstanceState */
    private static final String KEY_IS_RESOLVING = "is_resolving";
    private static final String KEY_SHOULD_RESOLVE = "should_resolve";

    /* Client for accessing Google APIs */
    private GoogleApiClient mGoogleApiClient;

    /* View to display current status (signed-in, signed-out, disconnected, etc) */
    private TextView mStatus;

    // [START resolution_variables]
    /* Is there a ConnectionResult resolution in progress? */
    private boolean mIsResolving = false;

    /* Should we automatically resolve ConnectionResults when possible? */
    private boolean mShouldResolve = false;
    // [END resolution_variables]

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            mIsResolving = savedInstanceState.getBoolean(KEY_IS_RESOLVING);
            mShouldResolve = savedInstanceState.getBoolean(KEY_SHOULD_RESOLVE);
        }

        findViewById(R.id.sign_in_button).setOnClickListener(this);
        findViewById(R.id.sign_out_button).setOnClickListener(this);
        findViewById(R.id.disconnect_button).setOnClickListener(this);

        // Large sign-in
        ((SignInButton) findViewById(R.id.sign_in_button)).setSize(SignInButton.SIZE_WIDE);

        // Start with sign-in button disabled until sign-in either succeeds or fails
        findViewById(R.id.sign_in_button).setEnabled(false);

        // Set up view instances
        mStatus = (TextView) findViewById(R.id.status);

        // Build GoogleApiClient with access to basic profile
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Plus.API)
                .addScope(new Scope(Scopes.PROFILE))
                .build();




        Integer userID = 2;
        Integer orderID = 2;
        Double bid = 300.55;
        Double estPrice = 300.00;
        Integer addressID = 2;
        Integer status = 1;
        String orderName = "3rd Order";


       // new GetUser().execute();
       // new GetAllOrders().execute(userID);
       // new GetItemsOfOrder().execute(orderID);
       // new SetNewBid(orderID, bid).execute();
       // new GetAddressesByUserID().execute(userID);
       // new CreateNewOrder(addressID, orderName, status, estPrice, bid, userID).execute();
                // new AddItemsToOrder().execute(orderID);
                // new UpdateOrder().execute(orderID);
                // new NewAddressByUserID().execute(userID);
                // new UpdateUserProfile().execute(userID);
                // new UpdateOrderStatus().execute(orderID);
                // new DeleteOrder().execute(orderID);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in_button:
                onSignInClicked();
                Log.w("Click", "Sign in clicked");
                break;
            case R.id.sign_out_button:
                onSignOutClicked();
                break;
            case R.id.disconnect_button:
                onDisconnectClicked();
                break;
        }
    }

    private void onDisconnectClicked() {
        mShouldResolve = true;
        mGoogleApiClient.connect();

        // Show a message to the user that we are signing in.
        mStatus.setText(R.string.signing_in);
    }

    private void onSignOutClicked() {
        if (mGoogleApiClient.isConnected()) {
            Plus.AccountApi.clearDefaultAccount(mGoogleApiClient);
            mGoogleApiClient.disconnect();
        }

        showSignedOutUI();
    }

    private void onSignInClicked() {
        mShouldResolve = true;
        mGoogleApiClient.connect();

        // Show a message to the user that we are signing in.
        mStatus.setText(R.string.signing_in);

    }

    @Override
    public void onConnected(Bundle bundle) {
        Log.d(TAG, "onConnected:" + bundle);
        mShouldResolve = false;

        // Show the signed-in UI
        showSignedInUI();

    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.w(TAG, "onConnectionSuspended:" + i);
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        // Could not connect to Google Play Services.  The user needs to select an account,
        // grant permissions or resolve an error in order to sign in. Refer to the javadoc for
        // ConnectionResult to see possible error codes.
        Log.d(TAG, "onConnectionFailed:" + connectionResult);

        if (!mIsResolving && mShouldResolve) {
            if (connectionResult.hasResolution()) {
                try {
                    connectionResult.startResolutionForResult(this, RC_SIGN_IN);
                    mIsResolving = true;
                } catch (IntentSender.SendIntentException e) {
                    Log.e(TAG, "Could not resolve ConnectionResult.", e);
                    mIsResolving = false;
                    mGoogleApiClient.connect();
                }
            } else {
                // Could not resolve the connection result, show the user an
                // error dialog.
                showErrorDialog(connectionResult);
            }
        } else {
            // Show the signed-out UI
            showSignedOutUI();
        }
    }

    private void showErrorDialog(ConnectionResult connectionResult) {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(this);

        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                apiAvailability.getErrorDialog(this, resultCode, RC_SIGN_IN,
                        new DialogInterface.OnCancelListener() {
                            @Override
                            public void onCancel(DialogInterface dialog) {
                                mShouldResolve = false;
                                showSignedOutUI();
                            }
                        }).show();
            } else {
                Log.w(TAG, "Google Play Services Error:" + connectionResult);
                String errorString = apiAvailability.getErrorString(resultCode);
                Toast.makeText(this, errorString, Toast.LENGTH_SHORT).show();

                mShouldResolve = false;
                showSignedOutUI();
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mGoogleApiClient.disconnect();
    }

    private void updateUI(boolean isSignedIn) {
        if (isSignedIn) {
            Log.w("mGoogle", "HERE: " + mGoogleApiClient);
            // Show signed-in user's name
            Person currentPerson = Plus.PeopleApi.getCurrentPerson(mGoogleApiClient);
            if (currentPerson != null) {
                String name = currentPerson.getDisplayName();
                mStatus.setText(getString(R.string.signed_in_fmt, name));
            } else {
                Log.w(TAG, getString(R.string.error_null_person));
                mStatus.setText(getString(R.string.signed_in_err));
            }

            // Set button visibility
            findViewById(R.id.sign_in_button).setVisibility(View.GONE);
            findViewById(R.id.sign_out_and_disconnect).setVisibility(View.VISIBLE);
        } else {
            // Show signed-out message
            mStatus.setText(R.string.signed_out);

            // Set button visibility
            findViewById(R.id.sign_in_button).setEnabled(true);
            findViewById(R.id.sign_in_button).setVisibility(View.VISIBLE);
            findViewById(R.id.sign_out_and_disconnect).setVisibility(View.GONE);
        }
    }

    private void showSignedInUI() {
        updateUI(true);
    }

    private void showSignedOutUI() {
        updateUI(false);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(KEY_IS_RESOLVING, mIsResolving);
        outState.putBoolean(KEY_SHOULD_RESOLVE, mShouldResolve);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult:" + requestCode + ":" + resultCode + ":" + data);

        if (requestCode == RC_SIGN_IN) {
            // If the error resolution was not successful we should not resolve further.
            if (resultCode != RESULT_OK) {
                mShouldResolve = false;
            }

            mIsResolving = false;
            mGoogleApiClient.connect();
        }
    }

    private class GetUser
            extends AsyncTask<Void, Void, UserCollection> {


        private MyApi myApiService = null;
        private Context context;


        protected void onPostExecute(final UserCollection result) {
            Log.w("Results", "The Results are: " + result);
        }


        @Override
        protected UserCollection doInBackground(Void... params) {
            if (myApiService == null) {  // Only do this once
                MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                        .setRootUrl("https://deliveryapp-testing.appspot.com/_ah/api/");
                // end options for devappserver

                myApiService = builder.build();
            }

            Log.w("API", "API: " + myApiService);

            try {

                return myApiService.getUser().execute();
            } catch (IOException e) {
                Log.w("EXCEPTION", "exception");
                String message = e.getMessage();
                if (message == null) {
                    message = e.toString();
                }
                return null;
            }
        }
    }

    private class GetAllOrders
            extends AsyncTask<Integer, Void, OrderCollection> {


        private MyApi myApiService = null;
        private Context context;


        protected void onPostExecute(final OrderCollection result) {
            Log.w("Results", "The Results are: " + result);
        }


        @Override
        protected OrderCollection doInBackground(final Integer... params) {
            Integer user = params[0];

            if (myApiService == null) {  // Only do this once
                MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                        .setRootUrl("https://deliveryapp-testing.appspot.com/_ah/api/");
                // end options for devappserver

                myApiService = builder.build();
            }

            Log.w("API", "API: " + myApiService);

            try {

                return myApiService.getAllOrdersByUser(user).execute();
            } catch (IOException e) {
                Log.w("EXCEPTION", "exception");
                String message = e.getMessage();
                if (message == null) {
                    message = e.toString();
                }
                return null;
            }
        }
    }


    private class GetItemsOfOrder
            extends AsyncTask<Integer, Void, ItemCollection> {


        private MyApi myApiService = null;
        private Context context;


        protected void onPostExecute(final ItemCollection result) {
            Log.w("Results", "The Results are: " + result);
        }


        @Override
        protected ItemCollection doInBackground(final Integer... params) {
            Integer orderID = params[0];

            if (myApiService == null) {  // Only do this once
                MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                        .setRootUrl("https://deliveryapp-testing.appspot.com/_ah/api/");
                // end options for devappserver

                myApiService = builder.build();
            }

            Log.w("API", "API: " + myApiService);

            try {

                return myApiService.getAllItemsByOrder(orderID).execute();
            } catch (IOException e) {
                Log.w("EXCEPTION", "exception");
                String message = e.getMessage();
                if (message == null) {
                    message = e.toString();
                }
                return null;
            }
        }
    }

    private class SetNewBid
            extends AsyncTask<Void, Void, Bid> {


        private MyApi myApiService = null;
        private Context context;
        Integer orderID;
        Double bid;

        public SetNewBid(Integer orderID, Double bid){
            this.orderID = orderID;
            this.bid = bid;
        }

        protected void onPostExecute(final Bid result) {
            Log.w("Results", "The bid has been updated to " + bid);
        }


        @Override
        protected Bid doInBackground(Void... params) {
            //Integer orderID = params[0];

            if (myApiService == null) {  // Only do this once
                MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                        .setRootUrl("https://deliveryapp-testing.appspot.com/_ah/api/");
                // end options for devappserver

                myApiService = builder.build();
            }

            Log.w("API", "API: " + myApiService);

            try {

                return myApiService.setNewBidByOrderID(orderID, bid).execute();
            } catch (IOException e) {
                Log.w("EXCEPTION", "exception");
                String message = e.getMessage();
                if (message == null) {
                    message = e.toString();
                }
                return null;
            }
        }
    }

    private class GetAddressesByUserID
            extends AsyncTask<Integer, Void, AddressCollection> {


        private MyApi myApiService = null;
        private Context context;


        protected void onPostExecute(final AddressCollection result) {
            Log.w("Results", "The Results are: " + result.getItems().get(1));

        }


        @Override
        protected AddressCollection doInBackground(final Integer... params) {
            Integer user = params[0];

            if (myApiService == null) {  // Only do this once
                MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                        .setRootUrl("https://deliveryapp-testing.appspot.com/_ah/api/");
                // end options for devappserver

                myApiService = builder.build();
            }

            Log.w("API", "API: " + myApiService);

            try {

                return myApiService.getAddressesByUser(user).execute();
            } catch (IOException e) {
                Log.w("EXCEPTION", "exception");
                String message = e.getMessage();
                if (message == null) {
                    message = e.toString();
                }
                return null;
            }
        }
    }

    private class CreateNewOrder
            extends AsyncTask<Void, Void, OrderCollection> {


        private MyApi myApiService = null;
        private Context context;
        Integer addressID;
        String orderName;
        Integer status;
        Double estPrice;
        Double bid;
        Integer userID;


        public CreateNewOrder(Integer addressID, String orderName, Integer status, Double estPrice, Double bid, Integer userID){
            this.addressID = addressID;
            this.orderName = orderName;
            this.status = status;
            this.estPrice = estPrice;
            this.bid = bid;
            this.userID = userID;
        }

        protected void onPostExecute(final OrderCollection result) {
            Log.w("Results", "The Results are: " + result);
        }


        @Override
        protected OrderCollection doInBackground(Void... params) {

            if (myApiService == null) {  // Only do this once
                MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                        .setRootUrl("https://deliveryapp-testing.appspot.com/_ah/api/");
                // end options for devappserver

                myApiService = builder.build();
            }

            Log.w("API", "API: " + myApiService);

            try {

                return myApiService.createNewOrder(addressID, orderName, status, estPrice, bid, userID).execute();
            } catch (IOException e) {
                Log.w("EXCEPTION", "exception");
                String message = e.getMessage();
                if (message == null) {
                    message = e.toString();
                }
                return null;
            }
        }
    }

}



