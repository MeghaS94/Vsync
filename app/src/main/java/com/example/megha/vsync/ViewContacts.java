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


public class ViewContacts extends ListActivity {
    String[] resultAsString = {""};

    ArrayAdapter<String> adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.view_contacts);
        Parse.initialize(this, "RpQHrxwX9YiA2nw0yZf1RKnJvZnLgvGfDs7Os2sN", "RTDRn8qvHKxRElMXsJEv9fQ79Z74sIIlJ5wQugzP");

        ParseObject.registerSubclass(Connection.class);
        final ArrayList<String> list = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, list);

        ParseUser currentUser = ParseUser.getCurrentUser();
        String struser = currentUser.getUsername().toString();

        ParseQuery<Connection> query = ParseQuery.getQuery("Connections");
        query.whereEqualTo("Person1_Name", struser);
        query.findInBackground(new FindCallback<Connection>() {
            @Override
            public void done(List<Connection> resultsList, com.parse.ParseException e) {
                if (e == null) {
                    resultAsString = new String[resultsList.size()];
                    for (int index = 0; index < resultsList.size(); index++) {

                        resultAsString[index] = resultsList.get(index).getPerson2Name();
                    }

                    for (int i = 0; i < resultAsString.length; ++i) {
                        list.add(resultAsString[i]);
                    }

                    System.out.println(list);

                    final ListView listView = (ListView) findViewById(android.R.id.list);
                    listView.setOnItemClickListener(new OnItemClickListener() {
                        public void onItemClick(AdapterView<?> parent, View view,
                                                int position, long id) {
                            String ContactName = ((TextView) view).getText().toString();
                            Intent i = new Intent(ViewContacts.this, SyncActivity.class);
                            // sending data to new activity
                            i.putExtra("ContactName", ContactName);
                            startActivity(i);

                        }
                    });

                    adapter.addAll(list);


                    setListAdapter(adapter);
                    System.out.println("in if");

                } else {
                    Log.d("Parse Query", e.getMessage());
                }
            }
        });

        final Button AddTaskBtn = (Button) findViewById(R.id.AddContact);
        AddTaskBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Intent intent2 = new Intent(ViewContacts.this,AddContacts.class);
                intent2.putStringArrayListExtra("Contacts", list);
                startActivity(intent2);

            }
        });




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
