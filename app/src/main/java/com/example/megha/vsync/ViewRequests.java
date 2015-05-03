package com.example.megha.vsync;


import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;


public class ViewRequests extends ListActivity {
    String[] resultAsString = {""};
    String[] resultTimeString = {""};
    String[] resultTypeString = {""};
    ArrayAdapter<String> adapter;
    final Context context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_requests);

        Parse.initialize(this, "RpQHrxwX9YiA2nw0yZf1RKnJvZnLgvGfDs7Os2sN", "RTDRn8qvHKxRElMXsJEv9fQ79Z74sIIlJ5wQugzP");
        ParseObject.registerSubclass(RequestSync.class);

        final ArrayList<String> list = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, list);

        ParseUser currentUser = ParseUser.getCurrentUser();
        final String struser = currentUser.getUsername().toString();

        ParseQuery<RequestSync> query = ParseQuery.getQuery("RequestSync");
        query.whereEqualTo("Receiver", struser);
        query.whereEqualTo("AcceptStatus", 0 );
        query.findInBackground(new FindCallback<RequestSync>() {
            @Override
            public void done(List<RequestSync> resultsList, com.parse.ParseException e) {
                if (e == null) {

                    resultAsString = new String[resultsList.size()];
                    resultTypeString = new String[resultsList.size()];
                    resultTimeString = new String[resultsList.size()];
                    for (int index = 0; index < resultsList.size(); index++) {

                        resultAsString[index] = resultsList.get(index).getSender();
                        resultTypeString[index] = resultsList.get(index).getType();
                        resultTimeString[index] = resultsList.get(index).getTimeSlot();// to be added to list view later.....
                    }

                    for (int i = 0; i < resultAsString.length; ++i) {
                        if (resultTypeString[i].equals("sync"))
                        {
                            list.add(resultAsString[i]+" Requested for Sync at 9:00 AM " );
                        }
                        else{
                            list.add(resultAsString[i]+" Requested Connection"  );
                        }
                    }



                    final ListView listView = (ListView) findViewById(android.R.id.list);

                    listView.setOnItemClickListener(new OnItemClickListener() {
                        public void onItemClick(AdapterView<?> parent, View view,
                                                int position, long id) {
                            //ParseUser currentUser = ParseUser.getCurrentUser();
                            //String struser = currentUser.getUsername().toString();
                            String shown = ((TextView) view).getText().toString();
                            final String[] a = shown.split(" ");



                            LayoutInflater li = LayoutInflater.from(context);
                            View promptsView = li.inflate(R.layout.promptsure, null);

                            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                                    context);

                            // set prompts.xml to alertdialog builder
                            alertDialogBuilder.setView(promptsView);


                            // set dialog message
                            alertDialogBuilder
                                    .setCancelable(false)
                                    .setPositiveButton("ACCEPT",
                                            new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog,int id) {


                                                    ParseQuery<RequestSync> query2 = ParseQuery.getQuery("RequestSync");
                                                    query2.whereEqualTo("Receiver", struser);
                                                    query2.whereEqualTo("AcceptStatus", 0 );
                                                    query2.whereEqualTo("Sender", a[0]);
                                                    query2.whereEqualTo("TimeSlot", a[a.length-1]);


                                                    query2.findInBackground(new FindCallback<RequestSync>() {
                                                        public void done(List<RequestSync> objects, com.parse.ParseException e) {
                                                            if (e == null) {
                                                                String ObjectId = objects.get(0).getObjectId();
                                                                ParseQuery<RequestSync> query3 = ParseQuery.getQuery("RequestSync");
                                                                query3.getInBackground(ObjectId, new GetCallback<RequestSync>() {
                                                                    public void done(RequestSync object, com.parse.ParseException e) {
                                                                        if (e == null) {
                                                                            object.setAcceptStatus(1);
                                                                            object.saveInBackground();
                                                                        } else {
                                                                        }
                                                                    }
                                                                });

                                                                Intent i = new Intent(ViewRequests.this, ViewRequests.class);

                                                                startActivity(i);
                                                            } else {

                                                            }
                                                        }
                                                    });

                                                    if (a[a.length-1].equals("Connection"))
                                                    {
                                                        // update connections

                                                        ParseObject testObject = new ParseObject("Connections");
                                                        testObject.put("Person1_Name", a[0]);
                                                        testObject.put("Person2_Name", struser);
                                                        testObject.saveInBackground();
                                                        ParseObject test = new ParseObject("Connections");
                                                        test.put("Person1_Name", struser);
                                                        test.put("Person2_Name", a[0]);
                                                        test.saveInBackground();
                                                        Toast.makeText(getApplicationContext(),
                                                                "Added Successfully", Toast.LENGTH_LONG)
                                                                .show();
                                                    }
                                                    else
                                                    {
                                                        //....*** Update the tasks of sender and receiver...

                                                    }

                                                }
                                            })
                                    .setNegativeButton("REJECT",
                                            new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog,int id) {

                                                    ParseQuery<RequestSync> query2 = ParseQuery.getQuery("RequestSync");
                                                    query2.whereEqualTo("Receiver", struser);
                                                    query2.whereEqualTo("AcceptStatus", 0 );
                                                    query2.whereEqualTo("Sender", a[0]);
                                                    query2.whereEqualTo("TimeSlot", a[a.length-1]);


                                                    query2.findInBackground(new FindCallback<RequestSync>() {
                                                        public void done(List<RequestSync> objects, com.parse.ParseException e) {
                                                            if (e == null) {
                                                                String ObjectId = objects.get(0).getObjectId();
                                                                ParseQuery<RequestSync> query3 = ParseQuery.getQuery("RequestSync");
                                                                query3.getInBackground(ObjectId, new GetCallback<RequestSync>() {
                                                                    public void done(RequestSync object, com.parse.ParseException e) {
                                                                        if (e == null) {
                                                                            object.setAcceptStatus(2);
                                                                            object.saveInBackground();
                                                                        } else {
                                                                        }
                                                                    }
                                                                });

                                                                Intent i = new Intent(ViewRequests.this, ViewRequests.class);

                                                                startActivity(i);
                                                            } else {

                                                            }
                                                        }
                                                    });

                                                    if (a[a.length-1].equals("Connection"))
                                                    {
                                                        // update connections

                                                        ParseObject testObject = new ParseObject("Connections");
                                                        testObject.put("Person1_Name", a[0]);
                                                        testObject.put("Person2_Name", struser);
                                                        testObject.saveInBackground();
                                                        ParseObject test = new ParseObject("Connections");
                                                        test.put("Person1_Name", struser);
                                                        test.put("Person2_Name", a[0]);
                                                        test.saveInBackground();
                                                        Toast.makeText(getApplicationContext(),
                                                                "Added Successfully", Toast.LENGTH_LONG)
                                                                .show();
                                                    }
                                                    else
                                                    {
                                                        //....*** Update the tasks of sender and receiver...

                                                    }


                                                }
                                            });

                            // create alert dialog
                            AlertDialog alertDialog = alertDialogBuilder.create();

                            // show it
                            alertDialog.show();

                        }
                    });



                    adapter.addAll(list);


                    setListAdapter(adapter);


                } else {
                    Log.d("Parse Query", e.getMessage());
                }
            }
        });
    }
   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.view_requests, menu);
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
