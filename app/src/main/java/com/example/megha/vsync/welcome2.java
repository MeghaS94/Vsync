package com.example.megha.vsync;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

public class welcome2 extends Activity {
    GridView grid;
    String[] web = {
            "My Time Table",
            "View Contacts",
            "Requests",
            "Notifications",

            "Request Sync",
            "LogOut",


    } ;
    int[] imageId = {
            R.drawable.tt,
            R.drawable.contacts,
            R.drawable.requests,
            R.drawable.notification,
            R.drawable.synccc,
            R.drawable.exit


    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_2);

        CustomGrid adapter = new CustomGrid(welcome2.this, web, imageId);
        grid=(GridView)findViewById(R.id.grid);
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(welcome2.this, "You Clicked at " +web[+ position], Toast.LENGTH_SHORT).show();
                if (position == 0) {
                    Intent intent = new Intent(welcome2.this,LIST.class);
                    startActivity(intent);
                }
                else if (position == 1) {
                    Intent intent2 = new Intent(welcome2.this,ViewContacts.class);
                    startActivity(intent2);
                }
                else if (position == 2) {
                    Intent intent = new Intent(welcome2.this,ViewRequests.class);
                    startActivity(intent);
                }

                else if (position == 3) {
                    Intent intent = new Intent(welcome2.this,Notifications.class);
                    startActivity(intent);
                }
                else if (position == 4) {
                    Intent intent = new Intent(welcome2.this,spinner_Act.class);
                    //intent.putExtra("ContactName", "anusha");
                    startActivity(intent);
                }
                else {
                    Intent intent = new Intent(welcome2.this,MainActivity.class);
                    startActivity(intent);
                }

                }

            });


    }

}