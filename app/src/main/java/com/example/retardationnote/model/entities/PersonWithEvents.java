package com.example.retardationnote.model.entities;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class PersonWithEvents {

    @Embedded
    private Person owner;

    @Relation(
            parentColumn = "nickname",
            entityColumn = "owner"
    )
    private List<Event> events;

    public Person getOwner() {
        return owner;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}
