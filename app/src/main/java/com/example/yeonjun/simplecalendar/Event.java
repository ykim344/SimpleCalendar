package com.example.yeonjun.simplecalendar;

/**
 * Created by Yeonjun on 2016-03-08.
 */
public class Event {

    String title;
    String description;
    int timeStartHr;
    int timeStartMin;
    int timeEndHr;
    int timeEndMin;

    public Event(String title, String description, int timeStartHr, int timeStartMin, int timeEndHr,int timeEndMin){
        this.title = title;
        this.description=description;
        this.timeStartHr = timeStartHr;
        this.timeStartMin = timeStartMin;
        this.timeEndHr = timeEndHr;
        this.timeEndMin = timeEndMin;
    }


}
