package com.example.megha.vsync;

/**
 * Created by Sharathyk on 4/27/2015.
**/
import android.widget.Toast;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("RequestSync")
public class RequestSync extends ParseObject{
    public RequestSync(){

    }

    public String getSender(){
        return getString("Sender");
    }

    public void setSender(String name){
        put("Sender", name);
    }

    public String getReceiver(){
        return getString("Receiver");
    }

    public void setType(String name){
        put("type", name);
    }

    public String getType(){
        return getString("type");
    }

    public void setReceiver(String name){
        put("Receiver", name);
    }

    public String getTimeSlot(){
        return getString("TimeSlot");
    }

    public void setTimeSlot(String name){
        put("TimeSlot", name);
    }

    public int getAcceptStatus(){
        return getInt("AcceptStatus");
    }

    public void setAcceptStatus(int val){
        put("AcceptStatus", val);

    }





}
