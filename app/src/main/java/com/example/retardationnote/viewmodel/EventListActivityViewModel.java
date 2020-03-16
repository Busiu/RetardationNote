package com.example.retardationnote.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.retardationnote.model.entities.Event;
import com.example.retardationnote.model.entities.Person;
import com.example.retardationnote.model.entities.PersonWithEvents;
import com.example.retardationnote.model.repositories.EventRepository;
import com.example.retardationnote.model.repositories.PersonRepository;

import java.util.List;

public class EventListActivityViewModel extends AndroidViewModel {

    private EventRepository eventRepository;
    private PersonRepository personRepository;

    private LiveData<PersonWithEvents> personWithEvents;

    private Person chosenPerson;
    private MutableLiveData<List<Event>> events;

    public EventListActivityViewModel(@NonNull Application application) {
        super(application);
        eventRepository = new EventRepository(application);
        personRepository = new PersonRepository(application);
    }

    public void insert(Event event) {
        eventRepository.insert(event);
    }

    public void update(Event event) {
        eventRepository.update(event);
    }

    public void update(Person person) {
        personRepository.update(person);
    }

    public void delete(Event event) {
        eventRepository.delete(event);
    }

    public Person getChosenPerson() {
        if (chosenPerson == null) {
            chosenPerson = personWithEvents.getValue().getOwner();
        }
        return chosenPerson;
    }

    public LiveData<List<Event>> getAllEvents() {
        if (events == null) {
            events = new MutableLiveData<>();
            events.setValue(personWithEvents.getValue().getEvents());
        }
        return events;
    }

    public LiveData<PersonWithEvents> getPersonWithEvents(String nickname) {
        if (personWithEvents == null) {
            personWithEvents = personRepository.getPersonWithEvents(nickname);
        }
        return personWithEvents;
    }
}
