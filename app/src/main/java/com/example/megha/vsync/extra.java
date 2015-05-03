package com.example.megha.vsync;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseUser;


public class extra extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extra);

        Parse.initialize(this, "RpQHrxwX9YiA2nw0yZf1RKnJvZnLgvGfDs7Os2sN", "RTDRn8qvHKxRElMXsJEv9fQ79Z74sIIlJ5wQugzP");

        ParseObject.registerSubclass(RequestSync.class);


        final Button AddtaskBtn = (Button) findViewById(R.id.Contst);
        AddtaskBtn.setOnClickListener(new View.OnClickListener() {
                                          public void onClick(View v){
                                              Toast.makeText(getApplicationContext(),
                                                      "Rt Sent Successfully", Toast.LENGTH_LONG)
                                                      .show();
                                             //EditText inputdate = (EditText) findViewById(R.id.ddate);
                                              //String SlotDate = inputdate.getText().toString();
                                              EditText inputstart = (EditText) findViewById(R.id.start_slot);
                                              String StartSlot = inputstart.getText().toString();
                                              EditText inputend = (EditText) findViewById(R.id.end_slot);
                                              String EndSlot = inputend.getText().toString();

                                              ParseUser currentUser = ParseUser.getCurrentUser();
                                              String struser = currentUser.getUsername().toString();
                                              String ContactName = getIntent().getStringExtra("ContactName");


                                              RequestSync ri = new RequestSync();
                                              ri.setSender(struser);
                                              ri.setReceiver(ContactName);
                                              ri.setTimeSlot( StartSlot + EndSlot);
                                              ri.setAcceptStatus(0);
                                              ri.setType("sync");
                                              ri.saveInBackground();
                                              Toast.makeText(getApplicationContext(),
                                                      "Request Sent Successfully", Toast.LENGTH_LONG)
                                                      .show();


                                          }
                                      }
        );

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_extra, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
