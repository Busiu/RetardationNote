package com.example.retardationnote.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Person implements Serializable {

    private String nickname;
    private ArrayList<Event> events;

    public Person(String nickname) {
        this.nickname = nickname;
        this.events = new ArrayList<>();
    }

    public String getNickname() {
        return nickname;
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public String getPointsToString() {
        int points = 0;
        for(Event event : events) {
            points += event.getPoints();
        }
        return Integer.toString(points);
    }

    @Override
    public boolean equals(Object o) {
        if(o == null) {
            return false;
        }
        if(!(o instanceof Person)) {
            return false;
        }
        return nickname.equals(((Person) o).nickname);
    }

    @Override
    public int hashCode() {
        return nickname.hashCode();
    }
}
