package com.example.rupa.firebaserupa;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ViewStoryInEditMode extends ActionBarActivity {
    //TextView title;
    TextView body;
    EditText ed;
    String shauvik,theta,author;
    Firebase ref;
    String key="";
   //allow the users to edit the story
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_story_in_edit_mode);
        Firebase.setAndroidContext(this);

        body= (TextView)findViewById(R.id.textView4);
        ed=(EditText)findViewById(R.id.editText6);
        Bundle hello=getIntent().getExtras();

        shauvik= hello.getString("selval") ;
        author=hello.getString("author");
        // displaying the existing part of the story
        ref = new Firebase("https://blinding-inferno-6012.firebaseio.com/Stories");

        ref.addValueEventListener(new ValueEventListener() {
            public void onDataChange(DataSnapshot snapshot) {
                System.out.println("There are " + snapshot.getChildrenCount() + " blog posts");

                for (DataSnapshot p : snapshot.getChildren()) {
                    Stories post = p.getValue(Stories.class);
                    if (post.getTitle().equals(shauvik)) {
                        body.setText(post.getBody());
                        break;
                    }


                }

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });

    }

    public void putNew(View view){
        body= (TextView)findViewById(R.id.textView4);
        ed=(EditText)findViewById(R.id.editText6);
        String alpha=body.getText().toString();
        String beta=ed.getText().toString();
        theta=alpha+beta;
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c.getTime());
         // adding the new part to the existing story
        ref.addValueEventListener(new ValueEventListener() {
            public void onDataChange(DataSnapshot snapshot) {
                System.out.println("There are " + snapshot.getChildrenCount() + " blog posts");

                for (DataSnapshot p : snapshot.getChildren()) {
                    Stories post = p.getValue(Stories.class);

                    if (post.getTitle().equals(shauvik)) {
                        key=p.getKey().toString();
                        body.setText(post.getBody());
                        ref.removeEventListener(this);
                        break;
                    }
                }
                System.out.println("im in view edit");
                Intent i= new Intent(ViewStoryInEditMode.this,ViewStory2.class);
                i.putExtra("author",author);
                i.putExtra("title",shauvik);
                i.putExtra("key",key);
                i.putExtra("theta",theta);
                startActivity(i);




            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });
    }


}
