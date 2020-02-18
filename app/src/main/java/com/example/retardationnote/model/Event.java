package com.example.retardationnote.model;

import java.util.Calendar;

public class Event {

    private Calendar actualDate;
    private Calendar plannedDate;
    private int points;
    private RetardationRank rank;
    private String describtion;

    public Event(String describtion, Calendar plannedDate) {
        this.describtion = describtion;
        this.plannedDate = plannedDate;

        this.rank = null;
        this.points = 0;
        this.actualDate = null;
    }
}
