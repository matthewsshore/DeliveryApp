package com.example.skantoro.deliveryapp_testing;

import android.graphics.Color;
import android.os.Bundle;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;
import com.example.skantoro.myapplication.backend.myApi.model.User;
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
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


/**
 * Created by skantoro on 10/5/15.
 */
public class MyOrderPage extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_orders);

        Intent intent = getIntent();
        String email = intent.getStringExtra("email");
        String firstName = intent.getStringExtra("firstName");
        String lastName = intent.getStringExtra("lastName");
        String role = intent.getStringExtra("role");
        String mobileNumber = intent.getStringExtra("mobileNumber");
        Integer userID = 2; //intent.getIntExtra("userID", 1);

        Log.w("UserID", "UserID: " + userID);

        /*TextView text = (TextView) findViewById(R.id.info_text);
        text.setTextColor(Color.parseColor("#FFFFFF"));
        text.setText("Hello my name is " + firstName);*/

        new GetAllOrders().execute(userID);

    }


    private class GetAllOrders
            extends AsyncTask<Integer, Void, OrderCollection> {


        private MyApi myApiService = null;
        private Context context;


        protected void onPostExecute(final OrderCollection result) {
            RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.rvOrders);
            mRecyclerView.setHasFixedSize(true);

            StaggeredGridLayoutManager gridLayoutManager =
                    new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
            mRecyclerView.setLayoutManager(gridLayoutManager);

            OrderAdapter mAdapter = new OrderAdapter(result);
            mRecyclerView.setAdapter(mAdapter);
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


}