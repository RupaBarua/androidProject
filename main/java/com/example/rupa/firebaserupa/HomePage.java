package com.example.rupa.firebaserupa;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Rupa Barua on 3/26/2016.
 */
public class HomePage extends ActionBarActivity{
    EditText name;
    EditText password;
    Button signup;
    Button login;
    Firebase ref ;
    boolean logint=false;
    SessionManager sm ;


//login and signup activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);
        Firebase.setAndroidContext(this);
       sm= new SessionManager(getApplicationContext());
        if(sm.getName().equals("name")){

        }
        else{
            Intent ip= new Intent(this,MainActivity.class);
            startActivity(ip);
        }

        name = (EditText) findViewById(R.id.editText);
        password = (EditText) findViewById(R.id.editText2);
        signup = (Button) findViewById(R.id.button);
        login = (Button) findViewById(R.id.button2);

        ref = new Firebase("https://blinding-inferno-6012.firebaseio.com/users");
        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                login();
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });
    }


     public void  signup(){
         name = (EditText) findViewById(R.id.editText);

         password = (EditText) findViewById(R.id.editText2);
         ref = new Firebase("https://blinding-inferno-6012.firebaseio.com/users");
         ref.addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(DataSnapshot snapshot) {
                 //if the username already exists
                 for (DataSnapshot p: snapshot.getChildren()) {
                     BlogPost post = p.getValue(BlogPost.class);
                     if(post.getName().equals(name.getText().toString()) && post.getPassword().equals(password.getText().toString())){
                         sm.createLoginSession(name.getText().toString());
                         Toast toast = Toast.makeText(getApplicationContext(), "You are already a member!!", Toast.LENGTH_SHORT);
                         toast.show();
                         log();
                         goTo();
                         ref.removeEventListener(this);
                         break;
                     }

                 }
                 if(!logint){
                     //if not
                     Map<String, String> post1 = new HashMap<String, String>();
                     post1.put("name", name.getText().toString());
                     post1.put("password", password.getText().toString());
                     ref.push().setValue(post1);
                     Toast toast = Toast.makeText(getApplicationContext(), "Success Signing in", Toast.LENGTH_SHORT);
                     toast.show();
                     sm.createLoginSession(name.getText().toString());
                     ref.removeEventListener(this);
                     goTo();
                 }

             }
             @Override
             public void onCancelled(FirebaseError firebaseError) {
                 System.out.println("The read failed: " + firebaseError.getMessage());
             }
         });


     }
        public void login(){

            name = (EditText) findViewById(R.id.editText);

            password = (EditText) findViewById(R.id.editText2);

            sm.createLoginSession(name.getText().toString());
            //check name and password
            ref = new Firebase("https://blinding-inferno-6012.firebaseio.com/users");
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {


                    for (DataSnapshot p: snapshot.getChildren()) {
                        BlogPost post = p.getValue(BlogPost.class);
                        if(post.getName().equals(name.getText().toString()) && post.getPassword().equals(password.getText().toString())){
                            Toast toast = Toast.makeText(getApplicationContext(), "Success Logging in", Toast.LENGTH_SHORT);
                            log();
                            toast.show();
                            goTo();
                            break;
                        }

                    }
                    if(!logint){

                        Toast toast = Toast.makeText(getApplicationContext(), "Log in failed", Toast.LENGTH_SHORT);
                        toast.show();

                    }

                }
                @Override
                public void onCancelled(FirebaseError firebaseError) {
                    System.out.println("The read failed: " + firebaseError.getMessage());
                }
            });




            }
    public boolean log(){
        logint=true;
        return logint ;
    }


    public void goTo()
    {
        Intent i=new Intent(this,MainActivity.class);
        i.putExtra("name",name.getText().toString());
        startActivity(i);
    }
    @Override
    public void onBackPressed(){

    }



        }







