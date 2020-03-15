package com.example.retardationnote.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.retardationnote.model.entities.Event;
import com.example.retardationnote.model.entities.Person;
import com.example.retardationnote.model.repositories.EventRepository;
import com.example.retardationnote.utils.ChosenObjects;

import java.util.List;

public class EventListActivityViewModel extends AndroidViewModel {

    private EventRepository eventRepository;

    private final Person chosenPerson;
    private LiveData<List<Event>> allChosenPersonEvents;
    private LiveData<List<Event>> allEvents;

    public EventListActivityViewModel(@NonNull Application application) {
        super(application);
        chosenPerson = ChosenObjects.currentlyChosenPerson;
        eventRepository = new EventRepository(application);
        allChosenPersonEvents = eventRepository.getAllChosenPersonEvents(chosenPerson);
        allEvents = eventRepository.getAllEvents();
    }

    public void insert(Event event) {
        eventRepository.insert(event);
    }

    public void update(Event event) {
        eventRepository.update(event);
    }

    public void delete(Event event) {
        eventRepository.delete(event);
    }

    public LiveData<List<Event>> getAllChosenPersonEvents() {
        return allChosenPersonEvents;
    }

    public LiveData<List<Event>> getAllEvents() {
        return allEvents;
    }
}
