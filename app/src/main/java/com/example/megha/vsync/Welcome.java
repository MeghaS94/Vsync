package com.example.megha.vsync;



import com.parse.ParseUser;
import android.app.Activity;
import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.view.View.OnClickListener;
        import android.widget.Button;
        import android.widget.TextView;
        import android.widget.Toast;

public class Welcome extends Activity {

    // Declare Variable
    Button logout;
    Button seeContacts;
    Button syncRequests;
    Button ViewTimeTable;
    Button new_button;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from singleitemview.xml
        setContentView(R.layout.welcome);

        // Retrieve current user from Parse.com
        ParseUser currentUser = ParseUser.getCurrentUser();

        // Convert currentUser into String
        String struser = currentUser.getUsername().toString();

        // Locate TextView in welcome.xml
        TextView txtuser = (TextView) findViewById(R.id.txtuser);

        // Set the currentUser String into TextView
        txtuser.setText("You are logged in as " + struser);



        seeContacts = (Button) findViewById(R.id.Contacts);
        seeContacts.setOnClickListener(new OnClickListener() {

            public void onClick(View arg0) {
                Intent intent2 = new Intent(Welcome.this,ViewContacts.class);
                startActivity(intent2);

            }
        });

        syncRequests = (Button) findViewById(R.id.SyncRequests);
        syncRequests.setOnClickListener(new OnClickListener() {

            public void onClick(View arg0) {
                Intent intent = new Intent(Welcome.this,ViewRequests.class);
                startActivity(intent);
            }
        });

        ViewTimeTable = (Button) findViewById(R.id.ViewTT);
        ViewTimeTable.setOnClickListener(new OnClickListener() {

            public void onClick(View arg0) {
                Intent intent = new Intent(Welcome.this,LIST.class);
                startActivity(intent);
            }
        });


        new_button = (Button) findViewById(R.id.new_button);
        new_button.setOnClickListener(new OnClickListener() {

            public void onClick(View arg0) {
                Intent intent = new Intent(Welcome.this,Notifications.class);
                startActivity(intent);
            }
        });

        // Locate Button in welcome.xml
        logout = (Button) findViewById(R.id.logout);

        // Logout Button Click Listener
        logout.setOnClickListener(new OnClickListener() {

            public void onClick(View arg0) {
                // Logout current user
                Intent intent = new Intent(Welcome.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}