package com.example.megha.vsync;

/**
 * Created by Sharathyk on 4/27/2015.
 */
import java.util.Date;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("Connections")
public class Connection extends ParseObject{
    public Connection(){

    }



    public String getPerson1Name(){
        return getString("Person1_Name");
    }

    public void setPerson1Name(String name){
        put("Person1_Name", name);
    }
    //----------
    public String getPerson2Name(){
        return getString("Person2_Name");
    }

    public void setPerson2Name(String name){
        put("Person2_Name", name);
    }
}
