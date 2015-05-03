package com.example.megha.vsync;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


import android.app.Activity;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

public class spinner_Act extends Activity implements
        OnItemSelectedListener{
    Spinner s1,s2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner_);
        s1 = (Spinner)findViewById(R.id.spinner1);
        s2 = (Spinner)findViewById(R.id.spinner2);
        s1.setOnItemSelectedListener(this);


        Button new_button = (Button) findViewById(R.id.b1);
        new_button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                Toast.makeText(getApplicationContext(),
                        "Request Sent",
                        Toast.LENGTH_LONG).show();
                Intent intent = new Intent(spinner_Act.this,welcome2.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
                               long arg3) {
        // TODO Auto-generated method stub
        String sp1= String.valueOf(s1.getSelectedItem());
        Toast.makeText(this, sp1, Toast.LENGTH_SHORT).show();
        if(sp1.contentEquals("Anusha")) {
            List<String> list = new ArrayList<String>();
            list.add("10:30 AM to 12:00 PM");
            list.add("2:00 PM  to 4:00  PM");
            list.add("5:00 PM  to 7:00  PM");
            list.add("9:30 AM to 10:30  PM");
            list.add("2:00 AM  to 4:00  AM");
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter.notifyDataSetChanged();
            s2.setAdapter(dataAdapter);
        }
        if(sp1.contentEquals("Pawan")) {
            List<String> list = new ArrayList<String>();
            list.add("7:30 AM to 1:00 PM");
            list.add("2:00 PM  to 4:00  PM");
            ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list);
            dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter2.notifyDataSetChanged();
            s2.setAdapter(dataAdapter2);
        }

    }
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }
}