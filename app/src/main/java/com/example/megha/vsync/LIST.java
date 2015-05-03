package com.example.megha.vsync;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
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

import java.util.ArrayList;
import java.util.List;

//view time table!
public class LIST extends ListActivity {
    String[] resultAsString = {""};

    ArrayAdapter<String> adapter;
    final Context context = this;
    private Button button;
    private EditText result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Parse.initialize(this, "RpQHrxwX9YiA2nw0yZf1RKnJvZnLgvGfDs7Os2sN", "RTDRn8qvHKxRElMXsJEv9fQ79Z74sIIlJ5wQugzP");
        ParseObject.registerSubclass(activities.class);
        final ArrayList<String> list = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);


        System.out.println("wind");
        ParseQuery<activities> query = ParseQuery.getQuery("activities");
        //get user to sync with
        // Retrieve current user from Parse.com
        ParseUser currentUser = ParseUser.getCurrentUser();

        // Convert currentUser into String
        String struser = currentUser.getUsername().toString();
        query.whereEqualTo("myname", struser);
        query.findInBackground(new FindCallback<activities>() {
            @Override
            public void done(List<activities> resultsList, com.parse.ParseException e) {
                if (e == null) {
                            list.add("7:00  AM  :   "+resultsList.get(0).gett7());
                            list.add("7:30  AM  :   "+resultsList.get(0).gett8());
                            list.add("8:00  AM  :   "+resultsList.get(0).gett9());
                            list.add("8:30  AM  :   "+resultsList.get(0).gett10());
                            list.add("9:00  AM  :   "+resultsList.get(0).gett11());
                            list.add("9:30  AM  :   "+resultsList.get(0).gett12());
                            list.add("10:00 AM  :   "+resultsList.get(0).gett14());
                            list.add("10:30 AM  :   "+resultsList.get(0).gett15());
                            list.add("11:00 AM  :   "+resultsList.get(0).gett16());
                            list.add("1 :00 PM  :   "+resultsList.get(0).gett17());


                    final ListView listView = (ListView) findViewById(android.R.id.list);

                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        public void onItemClick(AdapterView<?> parent, View view,
                                                int position, long id) {
                            final String oldtask = ((TextView) view).getText().toString();
                            /*Intent intent2 = new Intent(LIST.this,Editlist.class);
                            String s = ((TextView) view).getText().toString();
                            intent2.putExtra("ActivityName", s);
                            startActivity(intent2);*/
                            // get prompts.xml view
                            LayoutInflater li = LayoutInflater.from(context);
                            View promptsView = li.inflate(R.layout.prompts, null);

                            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                                    context);

                            // set prompts.xml to alertdialog builder
                            alertDialogBuilder.setView(promptsView);

                            final EditText userInput = (EditText) promptsView
                                    .findViewById(R.id.editTextDialogUserInput);

                            // set dialog message
                            alertDialogBuilder
                                    .setCancelable(false)
                                    .setPositiveButton("OK",
                                            new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog,int id) {
                                                    // get user input and set it to result
                                                    // edit text
                                                    //result.setText(userInput.getText());
                                                    Toast.makeText(getApplicationContext(),
                                                            userInput.getText() , Toast.LENGTH_LONG)
                                                            .show();
                                                    Editable temp = userInput.getText();
                                                    EditText(temp, oldtask);
                                                }
                                            })
                                    .setNegativeButton("Cancel",
                                            new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog,int id) {
                                                    dialog.cancel();
                                                }
                                            });

                            // create alert dialog
                            AlertDialog alertDialog = alertDialogBuilder.create();

                            // show it
                            alertDialog.show();

                        }
                    });


                    System.out.println("baadal");
                    adapter.addAll(list);
                    setListAdapter(adapter);
                    System.out.println("After looop");
                }
            else {
                    Log.d("Parse Query", e.getMessage());
                }
            }

            //System.out.println("wind");

        });


    }

    public void EditText(Editable text, String oldtask) {
        // old task not used.. since functionality implemented for only hour7
        final String text2 = text.toString();
        ParseUser currentUser = ParseUser.getCurrentUser();
        final String struser = currentUser.getUsername().toString(); //user
        ParseQuery<activities> query2 = ParseQuery.getQuery("activities");
        query2.whereEqualTo("myname", struser);
        query2.findInBackground(new FindCallback<activities>() {
            public void done(List<activities> objects, com.parse.ParseException e) {
                if (e == null) {
                    String ObjectId = objects.get(0).getObjectId();
                    ParseQuery<activities> query3 = ParseQuery.getQuery("activities");
                    query3.getInBackground(ObjectId, new GetCallback<activities>() {
                        public void done(activities object, com.parse.ParseException e) {
                            if (e == null) {
                                object.sett7(text2);
                                object.saveInBackground();
                            } else {
                            }
                        }
                    });


                    Intent i = new Intent(LIST.this, LIST.class);
                    startActivity(i);
                } else {

                }
            }
        });






    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list, menu);
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
