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

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Integer userID = 2;
        Integer orderID = 2;
        Double bid = 300.55;
        Double estPrice = 300.00;
        Integer addressID = 2;
        Integer status = 1;
        String orderName = "3rd Order";


       // new GetUser().execute();
        new GetAllOrders().execute(userID);
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



