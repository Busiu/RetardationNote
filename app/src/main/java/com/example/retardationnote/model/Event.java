package com.example.retardationnote.model;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class Event {

    //private final static String separator = ";";

    private Date date;
    private int points;
    private String name;

    public Event(String name, int points) {
        this.date = Calendar.getInstance().getTime();
        this.points = points;
        this.name = name;
    }

    public String getDate() {
        DateFormat dateFormat = DateFormat.getDateTimeInstance();
        return dateFormat.format(date);
    }

    public int getPoints() {
        return points;
    }

    public String getName() {
        return name;
    }
}
