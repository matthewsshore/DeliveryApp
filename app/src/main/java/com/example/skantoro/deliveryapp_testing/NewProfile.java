package com.example.skantoro.deliveryapp_testing;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.skantoro.myapplication.backend.myApi.MyApi;
import com.example.skantoro.myapplication.backend.myApi.model.User;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by skantoro on 10/16/15.
 */
public class NewProfile extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_creation);
    }

    public void createUserProfile (View view) {
        EditText emailInput= (EditText) findViewById(R.id.email);
        String userEmail = emailInput.getText().toString();

        EditText firstNameInput= (EditText) findViewById(R.id.firstName);
        String userFirstName = firstNameInput.getText().toString();

        EditText lastNameInput= (EditText) findViewById(R.id.lastName);
        String userLastName = lastNameInput.getText().toString();

        EditText mobileInput= (EditText) findViewById(R.id.phone);
        String userMobilePhone = mobileInput.getText().toString();


        new createUser(userFirstName, userLastName, userMobilePhone, userEmail).execute();
    }

    public void goToOrderPage(User result){
        Intent signInComplete = new Intent(this, MyOrderPage.class);
        String email = result.getEmail();
        String firstName = result.getFirstName();
        String lastName = result.getLastName();
        String role = result.getRole();
        String mobileNumber = result.getMobileNumber();
        Integer userID = result.getUserID();
        signInComplete.putExtra("userID", userID);
        signInComplete.putExtra("email", email);
        signInComplete.putExtra("firstName", firstName);
        signInComplete.putExtra("lastName", lastName);
        signInComplete.putExtra("mobileNumber", mobileNumber);
        startActivity(signInComplete);

    }

    private class createUser
            extends AsyncTask<Void, Void, User> {


        private MyApi myApiService = null;
        private Context context;
        String firstName;
        String lastName;
        String mobileNumber;
        String email;

        public createUser(String firstName, String lastName, String mobileNumber, String email){
            this.firstName = firstName;
            this.lastName = lastName;
            this.mobileNumber = mobileNumber;
            this.email = email;
        }


        protected void onPostExecute(final User result) {
            Log.w("User", "result: " + result.getFirstName());

            goToOrderPage(result);
        }


        @Override
        protected User doInBackground(Void... params) {
            if (myApiService == null) {  // Only do this once
                MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                        .setRootUrl("https://deliveryapp-testing.appspot.com/_ah/api/");
                // end options for devappserver

                myApiService = builder.build();
            }

            Log.w("API", "API: " + myApiService);

            try {

                return myApiService.createNewUser(firstName, lastName, mobileNumber, email).execute();
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
