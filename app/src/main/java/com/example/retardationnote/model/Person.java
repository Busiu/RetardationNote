package com.example.retardationnote.model;

import java.util.ArrayList;

public class Person {

    private String nickname;
    private ArrayList<Event> events;

    public Person(String nickname) {
        this.nickname = nickname;
        this.events = new ArrayList<>();
    }

    public void addEvent(String name, int score) {
        events.add(new Event(name, score));
    }

    public String getNickname() {
        return nickname;
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
