package com.example.rupa.firebaserupa;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;

import com.firebase.client.Firebase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Rupa Barua on 4/3/2016.
 */
public class ViewStory2 extends ActionBarActivity implements View.OnClickListener {

    TextView title;
    TextView body;
    String theta, key, tt,author;
    Firebase ref;
    // after editing the existing story.. it saves the story.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewstory2);
        Firebase.setAndroidContext(this);
        Calendar c = Calendar.getInstance();

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c.getTime());
        String s;
        Bundle hello = getIntent().getExtras();
        author=hello.getString("author");
        tt = hello.getString("title");
        s="The story: " + tt + " has been updated by "+ author;
        key = hello.getString("key");
        theta = hello.getString("theta");
        System.out.println("im in view story 2");

        title = (TextView) findViewById(R.id.textView5);
        title.setText(tt);
        body = (TextView) findViewById(R.id.textView6);
        body.setText(theta);
        ref = new Firebase("https://blinding-inferno-6012.firebaseio.com/Stories");
        ref.child(key).child("editable").setValue("true");
        ref.child(key).child("body").setValue(theta);
        ref.child(key).child("le").setValue(author);
        ref.child(key).child("date").setValue(formattedDate);

        Intent rupa= new Intent("RUPA");
        rupa.putExtra("message",(CharSequence)s);
        rupa.setAction("com.example.rupa.INTENT");
        sendBroadcast(rupa);

    }



    @Override
    public void onBackPressed() {
        //your code when back button pressed
        //nothing will happen
    }



    @Override
    public void onClick(View v) {
        Intent i=new Intent(this,MainActivity.class);
        i.putExtra("name",author);
        startActivity(i);

    }
}
