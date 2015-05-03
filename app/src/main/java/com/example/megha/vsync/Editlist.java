package com.example.megha.vsync;


import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class Editlist extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editlist);
        TextView contactName = (TextView)findViewById(R.id.activityname);


        Parse.initialize(this, "RpQHrxwX9YiA2nw0yZf1RKnJvZnLgvGfDs7Os2sN", "RTDRn8qvHKxRElMXsJEv9fQ79Z74sIIlJ5wQugzP");
        ParseObject.registerSubclass(activities.class);

        final Button AddTaskBtn = (Button) findViewById(R.id.edit_activity);
        AddTaskBtn.setOnClickListener(new View.OnClickListener() {
                                          public void onClick(View v){
                                              EditText edited_activity = (EditText) findViewById(R.id.ddate);
                                              final String Editedstr = edited_activity.getText().toString(); //activity
                                              ParseUser currentUser = ParseUser.getCurrentUser();
                                              final String struser = currentUser.getUsername().toString(); //user
                                              ParseQuery<activities> query2 = ParseQuery.getQuery("activities");
                                              query2.whereEqualTo("myname", struser);
                                              String s = getIntent().getStringExtra("ActivityName");
                                              query2.findInBackground(new FindCallback<activities>() {
                                                  public void done(List<activities> objects, com.parse.ParseException e) {
                                                      if (e == null) {
                                                          String ObjectId = objects.get(0).getObjectId();
                                                          ParseQuery<activities> query3 = ParseQuery.getQuery("activities");
                                                          query3.getInBackground(ObjectId, new GetCallback<activities>() {
                                                              public void done(activities object, com.parse.ParseException e) {
                                                                  if (e == null) {
                                                                      object.sett7(Editedstr);
                                                                      object.saveInBackground();
                                                                  } else {
                                                                  }
                                                              }
                                                          });

                                                          Toast.makeText(getApplicationContext(),
                                                                  "Activity edited successfully", Toast.LENGTH_LONG)
                                                                  .show();
                                                          Intent i = new Intent(Editlist.this, LIST.class);
                                                          startActivity(i);
                                                      } else {

                                                      }
                                                  }
                                              });



                                          }
                                      }
        );

    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.sync, menu);
        return true;
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
