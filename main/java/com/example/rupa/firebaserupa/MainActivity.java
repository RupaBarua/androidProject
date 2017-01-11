package com.example.rupa.firebaserupa;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class MainActivity extends ActionBarActivity implements View.OnClickListener{

    Button btn;
    String val;
    ListView mainListView;
    ArrayList<String>  titles;
    Firebase ref;
    SessionManager sm;

//listview with storylist, and logout button
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);
        sm=new SessionManager(getApplicationContext());
        ref = new Firebase("https://blinding-inferno-6012.firebaseio.com/Stories");
        btn= (Button)findViewById(R.id.button4);
        mainListView= (ListView) findViewById(R.id.listView);
        titles= new ArrayList<String>();


        val=sm.getName();
    //  System.out.println(val);
        ref.addValueEventListener(new ValueEventListener() {
            public void onDataChange(DataSnapshot snapshot) {
             System.out.println("There are " + snapshot.getChildrenCount() + " blog posts");
              
                for (DataSnapshot p: snapshot.getChildren()) {
                  Stories post= p.getValue(Stories.class);
                    titles.add(post.getTitle());
                }
                doThis();
                settingOnClickListener();
                titles= new ArrayList<String>();
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });



    }


    @Override
    public void onBackPressed() {
        //your code when back button pressed
        //nothing will happen
    }



    public void doThis(){
    ArrayAdapter<String> itemAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,titles);
    mainListView.setAdapter(itemAdapter);
}
    public void settingOnClickListener(){
        mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selval = ((TextView) view).getText().toString();
                System.out.println(selval);
                Intent i = new Intent(MainActivity.this, ViewStory.class);
                i.putExtra("author", val);
                i.putExtra("selval", selval);
                startActivity(i);
            }
        });
    }
    @Override
    public void onClick(View v) {
        Intent i = new Intent(this,EditStory.class);
        i.putExtra("name",val);
        startActivity(i);

    }

    public void logOut(View view){
        sm.logoutUser();
    }
}


