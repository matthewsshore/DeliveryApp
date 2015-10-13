package com.example.skantoro.deliveryapp_testing;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.content.Intent;

import com.example.skantoro.myapplication.backend.myApi.MyApi;
import com.example.skantoro.myapplication.backend.myApi.model.User;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by skantoro on 10/10/15.
 */
public class LogIn extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
    }

    public void signInUser(View view) {
        EditText email_input = (EditText) findViewById(R.id.email);
        String email = email_input.getText().toString();

        // change this back to email when ready for testing
        new GetUser().execute(email);
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
        signInComplete.putExtra("role", role);
        signInComplete.putExtra("mobileNumber", mobileNumber);
        startActivity(signInComplete);

    }

    private class GetUser
        extends AsyncTask<String, Void, User> {


    private MyApi myApiService = null;
    private Context context;


    protected void onPostExecute(final User result) {
        // Log.w("First Name", "The users first name is: " + result);
        goToOrderPage(result);


    }


    @Override
    protected User doInBackground(final String... params) {
        String email = params[0];
        Log.w("Email", "Email for SQL: " + email);
        if (myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://deliveryapp-testing.appspot.com/_ah/api/");
            // end options for devappserver

            myApiService = builder.build();
        }

        Log.w("API", "API: " + myApiService);

        try {

            return myApiService.getUser(email).execute();
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
