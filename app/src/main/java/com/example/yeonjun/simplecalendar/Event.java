package com.example.yeonjun.simplecalendar;

/**
 * Created by Yeonjun on 2016-03-08.
 */
public class Event {

    int year;
    int month;
    int day;
    String title;
    int timeStart;
    int timeEnd;

    public Event(int year, int month, int day, String title, int timeStart, int timeEnd){
        this.year = year;
        this.month = month;
        this.day = day;
        this.title = title;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
    }


}
