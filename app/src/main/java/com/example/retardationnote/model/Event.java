package com.example.retardationnote.model;

import java.io.Serializable;
import java.util.Calendar;

public class Event implements Serializable {

    private Calendar actualDate;
    private Calendar plannedDate;
    private RetardationRank rank;
    private String describtion;

    public Event(String describtion, Calendar plannedDate) {
        this.describtion = describtion;
        this.plannedDate = plannedDate;

        this.rank = null;
        this.actualDate = null;
    }

    public int getPoints() {
        return rank.getPoints();
    }

    public String getPlannedDateToString() {
        return plannedDate.getTime().toString();
    }

    public String getRankToString() {
        if (rank == null) {
            return "event not ended";
        }
        return rank.toString();
    }

    public String getDescribtion() {
        return describtion;
    }

    public String getRetardationToString() {
        if (actualDate == null) {
            return "event not ended";
        }

        long a = actualDate.getTimeInMillis() / (1000 * 60);
        long b = plannedDate.getTimeInMillis() / (1000 * 60);
        return Long.toString(a - b);
    }

    public void setDescribtion(String describtion) {
        this.describtion = describtion;
    }
}
