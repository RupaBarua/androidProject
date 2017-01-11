package com.example.rupa.firebaserupa;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Handler;
import android.sax.StartElementListener;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;


/**
 * Created by Rupa Barua on 4/5/2016.
 */
public class OpeningScreen extends ActionBarActivity{

    SessionManager session;
// opening activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.openingscreen);
        session = new SessionManager(getApplicationContext());

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 3s = 3000ms
                if(session.getName().equals("name"))
                    goToHomepage();
                else

                    goToMain();
            }
        }, 3000);



}
    public void  goToMain(){
        Intent i= new Intent(this,MainActivity.class);
        startActivity(i);

    };
    public void goToHomepage(){
      Intent i = new Intent(this,HomePage.class);
        startActivity(i);
    };
}
