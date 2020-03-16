package com.example.retardationnote.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.retardationnote.model.entities.Person;
import com.example.retardationnote.model.repositories.PersonRepository;

import java.util.List;

public class PeopleListActivityViewModel extends AndroidViewModel {

    private PersonRepository personRepository;
    private LiveData<List<Person>> allPeople;
    private Person chosenPerson;

    public PeopleListActivityViewModel(@NonNull Application application) {
        super(application);
        personRepository = new PersonRepository(application);
        allPeople = personRepository.getAllPeople();
    }

    public void insert(Person person) {
        personRepository.insert(person);
    }

    public void update(Person person) {
        personRepository.update(person);
    }

    public void delete(Person person) {
        personRepository.delete(person);
    }

    public LiveData<List<Person>> getAllPeople() {
        return allPeople;
    }

    public Person getChosenPerson() {
        return chosenPerson;
    }

    public void setChosenPerson(Person chosenPerson) {
        this.chosenPerson = chosenPerson;
    }
}
