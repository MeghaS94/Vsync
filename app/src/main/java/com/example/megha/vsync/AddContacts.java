package com.example.megha.vsync;



        import java.util.ArrayList;
        import java.util.List;

        import android.support.v7.app.ActionBarActivity;
        import java.util.ArrayList;
        import java.util.List;

        import android.app.ListActivity;
        import android.content.Intent;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.AdapterView.OnItemClickListener;
        import android.widget.ArrayAdapter;
        import android.widget.ListView;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.parse.FindCallback;
        import com.parse.GetCallback;
        import com.parse.Parse;
        import com.parse.ParseObject;
        import com.parse.ParseQuery;
        import com.parse.ParseUser;

public class AddContacts extends ListActivity {

    String[] resultNameString = {""};
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contacts);
        final ArrayList<String> Contacts = getIntent().getStringArrayListExtra("Contacts");

        Parse.initialize(this, "RpQHrxwX9YiA2nw0yZf1RKnJvZnLgvGfDs7Os2sN", "RTDRn8qvHKxRElMXsJEv9fQ79Z74sIIlJ5wQugzP");


        final ArrayList<String> list = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, list);
        ParseQuery<ParseObject> query = ParseQuery.getQuery("_User");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> objects, com.parse.ParseException e) {
                if (e == null) {
                    ParseUser currentUser = ParseUser.getCurrentUser();
                    String struser = currentUser.getUsername().toString();
                    resultNameString = new String[objects.size()];
                    for (int index = 0; index < objects.size(); index++) {
                        resultNameString[index] = objects.get(index).getString("username");
                    }
                    int cnt = resultNameString.length;
                    for (int i = 0; i < resultNameString.length; i++) {
                        if (struser.equals(objects.get(i).getString("username")))
                        {}
                        else{
                            for (int j = 0; j < (Contacts.size()/2); j++) {
                                if(resultNameString[i].equals(Contacts.get(j)))
                                {}
                                else{
                                    list.add(resultNameString[i]);
                                }
                            }
                        }

                    }

                    Toast.makeText(getApplicationContext(),
                            "Number"+cnt, Toast.LENGTH_LONG)
                            .show();
                    final ListView listView = (ListView) findViewById(android.R.id.list);

                    listView.setOnItemClickListener(new OnItemClickListener() {
                        public void onItemClick(AdapterView<?> parent, View view,
                                                int position, long id) {
                            ParseUser currentUser = ParseUser.getCurrentUser();
                            String struser = currentUser.getUsername().toString();
                            String ReceiverName = ((TextView) view).getText().toString();

                            ParseObject testObject = new ParseObject("RequestSync");
                            testObject.put("Sender", struser);
                            testObject.put("Receiver", ReceiverName);
                            testObject.put("AcceptStatus", 0);
                            testObject.put("TimeSlot", "Connection");
                            testObject.put("type", "contact");
                            testObject.saveInBackground();

                            Toast.makeText(getApplicationContext(),
                                    "Add Request Sent Successfully", Toast.LENGTH_LONG)
                                    .show();

                        }
                    });

                    adapter.addAll(list);
                    setListAdapter(adapter);
                } else {

                }
            }
        });



    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.add_contacts, menu);
        return true;
    }
*/
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
