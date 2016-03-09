package com.example.yeonjun.simplecalendar;

import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Yeonjun on 2016-03-08.
 */
public class Date {

    int year;
    int month;
    int day;
    ArrayList<Event> eventList;

    public Date(int year, int month, int day){
        this.year = year;
        this.month = month;
        this.day = day;

        eventList = new ArrayList<Event>();

    }

    public ArrayList<Event> getEventList(){
        return eventList;
    }

    public void addEvent(Event e){
        eventList.add(e);
    }
}
