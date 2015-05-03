package com.example.megha.vsync;
import java.util.ArrayList;

import java.util.List;



import android.app.ListActivity;

import android.content.Intent;

import android.os.Bundle;

import android.util.Log;

import android.view.MenuItem;

import android.view.View;

import android.widget.AdapterView;

import android.widget.AdapterView.OnItemClickListener;

import android.widget.ArrayAdapter;

import android.widget.Button;

import android.widget.ListView;

import android.widget.TextView;



import com.parse.FindCallback;

import com.parse.Parse;

import com.parse.ParseObject;

import com.parse.ParseQuery;

import com.parse.ParseUser;



public class Notifications extends ListActivity {

    String[] resultAsString = {""};

    String[] resultTypeString = {""};

    Integer[] resultBoolString = {};



    ArrayAdapter<String> adapter;



    @Override

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_notifications);

        Parse.initialize(this, "RpQHrxwX9YiA2nw0yZf1RKnJvZnLgvGfDs7Os2sN", "RTDRn8qvHKxRElMXsJEv9fQ79Z74sIIlJ5wQugzP");

        ParseObject.registerSubclass(RequestSync.class);

        final ArrayList<String> list = new ArrayList<String>();

        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, list);

        ParseUser currentUser = ParseUser.getCurrentUser();

        String struser = currentUser.getUsername().toString();

        ParseQuery<RequestSync> query = ParseQuery.getQuery("RequestSync");

        query.whereEqualTo("Sender", struser);

        query.findInBackground(new FindCallback<RequestSync>() {

            @Override

            public void done(List<RequestSync> resultsList, com.parse.ParseException e) {

                if (e == null) {

                    resultAsString = new String[resultsList.size()];

                    resultTypeString = new String[resultsList.size()];

                    resultBoolString = new Integer[resultsList.size()];

                    for (int index = 0; index < resultsList.size(); index++) {

                        resultAsString[index] = resultsList.get(index).getReceiver();
                                resultTypeString[index] = resultsList.get(index).getType();

                        resultBoolString[index] = resultsList.get(index).getAcceptStatus();

                    }

                    for (int i = 0; i < resultAsString.length; ++i) {

                        if (resultBoolString[i] == 1)

                        {

                            list.add(resultAsString[i] +  " has accepted your request for " + resultTypeString[i]);

                        } else if (resultBoolString[i] == 2)

                        {

                            list.add(resultAsString[i] + " has declined your request for " + resultTypeString[i]);

                        }

                    }
                    System.out.println(list);
                    adapter.addAll(list);
                    setListAdapter(adapter);

                }
                else{

                    Log.d("Parse Query", e.getMessage());

                }


            }

        });

    //});

}



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

