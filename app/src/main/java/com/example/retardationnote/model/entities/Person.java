package com.example.retardationnote.model.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity(tableName = "person_table")
public class Person {

    @NonNull
    @PrimaryKey
    private String nickname;

    @Ignore
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
