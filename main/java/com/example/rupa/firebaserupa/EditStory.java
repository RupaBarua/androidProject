package com.example.rupa.firebaserupa;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Rupa Barua on 3/28/2016.
 */
public class EditStory extends ActionBarActivity implements View.OnClickListener {

        EditText Title;
        EditText Story;
        Button  post;
        String val;
        //write new story
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.editstory);
            Firebase.setAndroidContext(this);
            Title= (EditText) findViewById(R.id.editText3);
            Story= (EditText) findViewById(R.id.editText4);
            post= (Button) findViewById(R.id.button3);
        }


    @Override
    public void onClick(View v) {
        //putting new story into the database
       Bundle hello = getIntent().getExtras();
        val= hello.getString("name");
        Firebase postref = new Firebase("https://blinding-inferno-6012.firebaseio.com/Stories");
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c.getTime());
        Map<String, String> post1 = new HashMap<String, String>();
        post1.put("title", Title.getText().toString());
        post1.put("author",val);
        post1.put("body", Story.getText().toString());
        post1.put("editable","true");
        post1.put("le",val);
        post1.put("date", formattedDate);
        postref.push().setValue(post1);

        Toast toast = Toast.makeText(getApplicationContext(), "your story has been saved!", Toast.LENGTH_SHORT);
        toast.show();
        Intent i = new Intent(this,MainActivity.class);
        i.putExtra("name",val);
        startActivity(i);


    }
}
