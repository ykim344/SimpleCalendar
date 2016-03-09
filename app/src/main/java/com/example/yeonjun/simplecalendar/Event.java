package com.example.yeonjun.simplecalendar;

/**
 * Created by Yeonjun on 2016-03-08.
 */
public class Event {

    String title;
    String description;
    String timeStart;
    String timeEnd;

    public Event(String title, String description, String timeStart, String timeEnd){
        this.title = title;
        this.description=description;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
    }


}
