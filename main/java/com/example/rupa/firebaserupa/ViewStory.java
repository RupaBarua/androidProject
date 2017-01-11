package com.example.rupa.firebaserupa;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

/**
 * Created by Rupa Barua on 4/2/2016.
 */
public class ViewStory extends ActionBarActivity {
    boolean ji=false;
    TextView title;
    TextView body;
    TextView le;
    String shauvik,key,author;
    Firebase ref;
    // after clicking the story title, this activity will display that story
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewstory);
        Firebase.setAndroidContext(this);

        title=(TextView)findViewById(R.id.textView2);
        body= (TextView)findViewById(R.id.textView3);
        le= (TextView) findViewById(R.id.textView7);
        Bundle hello=getIntent().getExtras();

        title.setText(hello.getString("selval"));
        shauvik= hello.getString("selval") ;
        author= hello.getString("author");

        ref = new Firebase("https://blinding-inferno-6012.firebaseio.com/Stories");
        ref.addValueEventListener(new ValueEventListener() {
            public void onDataChange(DataSnapshot snapshot) {
                System.out.println("There are " + snapshot.getChildrenCount() + " blog posts");

                for (DataSnapshot p: snapshot.getChildren()) {
                    Stories post= p.getValue(Stories.class);
                    if(post.getTitle().equals(shauvik)){
                       body.setText(post.getBody());
                        le.setText("last edited by: "+ post.getLE()+" at "+post.getDate());
                        key=p.getKey().toString();
                        ref.removeEventListener(this);
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

    public void yolo(View view){

        ref.addValueEventListener(new ValueEventListener() {
            public void onDataChange(DataSnapshot snapshot) {
                System.out.println("There are " + snapshot.getChildrenCount() + " blog posts");

                for (DataSnapshot p : snapshot.getChildren()) {
                    Stories post = p.getValue(Stories.class);
                    if (post.getTitle().equals(shauvik)) {
                        if(post.getEditable().equals("true")){
                            ji=true;
                            key=p.getKey();
                            ref.removeEventListener(this);
                            break;
                        }
                        else{
                            Toast toast = Toast.makeText(getApplicationContext(), "Someone else is trying to edit, try again later.", Toast.LENGTH_SHORT);
                            toast.show();
                            break;
                        }

                    }
                }
                if(ji){
                    goTo();
                }



            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });
    }






    public void goTo(){
            ji=false;
        ref.child(key).child("editable").setValue("false");
        System.out.println("im in view story");
        Intent i = new Intent(ViewStory.this, ViewStoryInEditMode.class);
        i.putExtra("author",author);
        i.putExtra("selval", shauvik);
        startActivity(i);
    }

    }

