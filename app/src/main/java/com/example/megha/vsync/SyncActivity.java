package com.example.megha.vsync;


        import com.parse.FindCallback;
        import com.parse.Parse;
        import com.parse.ParseException;
        import com.parse.ParseObject;
        import com.parse.ParseQuery;
        import com.parse.ParseUser;
        import com.parse.SaveCallback;

        import android.app.Activity;
        import android.app.ListActivity;
        import android.support.v7.app.ActionBarActivity;
        import android.content.Intent;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;
        import android.widget.Toast;

        import java.util.ArrayList;
        import java.util.List;

public class SyncActivity extends ListActivity {

    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sync);
        final String ContactName = getIntent().getStringExtra("ContactName");
        TextView contactName = (TextView)findViewById(R.id.Contactname);
        contactName.setText(ContactName);



        Parse.initialize(this, "RpQHrxwX9YiA2nw0yZf1RKnJvZnLgvGfDs7Os2sN", "RTDRn8qvHKxRElMXsJEv9fQ79Z74sIIlJ5wQugzP");

        ParseObject.registerSubclass(activities.class);

        final ArrayList<String> list = new ArrayList<String>();
        final ArrayList<String> list2 = new ArrayList<String>();
        final ArrayList<String> list_final = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list_final);
        System.out.println("wind");
        ParseQuery<activities> query = ParseQuery.getQuery("activities");
        query.whereEqualTo("myname", "anusha"); //parse query for her


        //list.add("not null");
        System.out.println(list.size());
        query.findInBackground(new FindCallback<activities>() {
            @Override
            public void done(List<activities> resultsList, com.parse.ParseException e) {
                if (e == null) {
                    Toast.makeText(getApplicationContext(),
                            "went into if", Toast.LENGTH_LONG)

                            .show();
                    //System.out.println(resultsList.get(0).gett7());
                    if (resultsList.size() != 0) {
                        list.add("7:" + resultsList.get(0).gett7());
                        //System.out.println(list.size());
                    list.add("8:"+resultsList.get(0).gett8());
                    list.add("9:"+resultsList.get(0).gett9());
                    list.add("10:"+resultsList.get(0).gett10());
                    list.add("11:"+resultsList.get(0).gett11());
                    list.add("12:"+resultsList.get(0).gett12());
                    list.add("13:"+resultsList.get(0).gett14());
                    list.add("14:"+resultsList.get(0).gett15());
                    list.add("15:"+resultsList.get(0).gett16());
                    list.add("16:"+resultsList.get(0).gett17());
                        //System.out.println(list);
                        //System.out.println(list2);
                        //System.out.print(list2);
                    }
                    ParseUser currentUser = ParseUser.getCurrentUser();
                    String struser = currentUser.getUsername().toString();

                    ParseQuery<activities> query1 = ParseQuery.getQuery("activities");
                    query1.whereEqualTo("myname", struser); //parse query for me
                    query1.findInBackground(new FindCallback<activities>() {
                        @Override
                        public void done(List<activities> resultsList2, com.parse.ParseException e) {
                            if (e == null) {
                                list2.add("7:" + resultsList2.get(0).gett7());
                                list2.add("8:" + resultsList2.get(0).gett8());
                                list2.add("9:" + resultsList2.get(0).gett9());
                                list2.add("10:" + resultsList2.get(0).gett10());
                                list2.add("11:" + resultsList2.get(0).gett11());
                                list2.add("12:" + resultsList2.get(0).gett12());
                                list2.add("13:" + resultsList2.get(0).gett14());
                                list2.add("14:" + resultsList2.get(0).gett15());
                                list2.add("15:" + resultsList2.get(0).gett16());
                                list2.add("16:" + resultsList2.get(0).gett17());

                                System.out.println(list2);
                                System.out.println(list);

                                for (int i = 0; i < list.size(); i++) {
                                    System.out.println("i m innnn");
                                    for (int j = 0; j < list2.size(); j++) {
                                        String[] str1 = list.get(i).split(":");
                                        String[] str2 = list2.get(j).split(":");
                                        if (str1[0].equals(str2[0]) ) {
                                            if (str1[1].equals("null") && str2[1].equals("null")) {
                                                int temp = (Integer.parseInt(str1[0]));
                                                int temp1 = temp +1;
                                                System.out.println("Available at time :" + str1[0] + " to " +  temp1);
                                                list_final.add("Available at time :" + str1[0] + " to " +  temp1);
                                                //list_final.add("Available at time : " + "10 : 00 AM " + " to " +  " 12 : 00 AM");
                                            }
                                        }
                                        else {
                                            if (str1[0].equals(str2[0])) {
                                                System.out.println(str1[1]);
                                                System.out.println(str2[1]);
                                                System.out.println(str1[0]);
                                                System.out.println(str2[0]);
                                            }
                                        }
                                        }
                                    }


                                adapter.addAll(list_final);
                                setListAdapter(adapter);
                            }
                                else {
                                }
                        }
                    });



                }
                else {
                }
                }
            }
        );

        /*ParseUser currentUser = ParseUser.getCurrentUser();
        String struser = currentUser.getUsername().toString();

        ParseQuery<activities> query1 = ParseQuery.getQuery("activities");
        query1.whereEqualTo("myname", struser); //parse query for me
        query1.findInBackground(new FindCallback<activities>() {
            @Override
            public void done(List<activities> resultsList2, com.parse.ParseException e) {
                if (e == null) {
                    list2.add("7:"+resultsList2.get(0).gett7());
                    list2.add("8:"+resultsList2.get(0).gett8());
                    list2.add("9:"+resultsList2.get(0).gett9());
                    list2.add("10:"+resultsList2.get(0).gett10());
                    list2.add("11:"+resultsList2.get(0).gett11());
                    list2.add("12:"+resultsList2.get(0).gett12());
                    list2.add("13:"+resultsList2.get(0).gett14());
                    list2.add("14:"+resultsList2.get(0).gett15());
                    list2.add("15:"+resultsList2.get(0).gett16());
                    list2.add("16:"+resultsList2.get(0).gett17());
                    System.out.println(list2);
                    //System.out.print(list2);
                }
                else {
                }
            }
        });*/
        ///System.out.println(list);
        //System.out.println(list2);

        //compare schedules

        //System.out.print(list2);


        ParseObject.registerSubclass(RequestSync.class);
        /*final Button AddTaskBtn = (Button) findViewById(R.id.Conts);
         AddTaskBtn.setOnClickListener(new View.OnClickListener() {
                                          public void onClick(View v){
                                              Intent intent = new Intent(
                                                      SyncActivity.this,
                                                      extra.class);
                                              intent.putExtra("ContactName", ContactName);
                                              startActivity(intent);
                                           }
                                      }
        );*/

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




/*ParseQuery<activities> query3 = ParseQuery.getQuery("activities");
query3.whereEqualTo("myname", struser);
        query3.findInBackground(new FindCallback<activities>() {
public void done(List<activities> objects, com.parse.ParseException e) {
        if (e == null) {
        String ObjectId = objects.get(0).getObjectId();
        ParseQuery<activities> query4 = ParseQuery.getQuery("activities");
        query4.getInBackground(ObjectId, new GetCallback<RequestSync>() {
public void done(RequestSync object, com.parse.ParseException e) {
        if (e == null) {
        object.setAcceptStatus(1);
        object.saveInBackground();
        } else {
        }
        }
        });
        }
        }
        });
*/