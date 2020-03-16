package com.example.retardationnote.model.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.retardationnote.model.RetardationNoteDatabase;
import com.example.retardationnote.model.daos.PersonDao;
import com.example.retardationnote.model.entities.Person;
import com.example.retardationnote.model.entities.PersonWithEvents;

import java.util.List;

public class PersonRepository {

    private PersonDao personDao;

    public PersonRepository(Application application) {
        RetardationNoteDatabase database = RetardationNoteDatabase.getInstance(application);
        personDao = database.personDao();
    }

    public void insert(Person person) {
        new InsertPersonAsyncTask(personDao).execute(person);
    }

    public void update(Person person) {
        new UpdatePersonAsyncTask(personDao).execute(person);
    }

    public void delete(Person person) {
        new DeletePersonAsyncTask(personDao).execute(person);
    }

    public LiveData<List<Person>> getAllPeople() {
        return personDao.getAllPeople();
    }

    public LiveData<PersonWithEvents> getPersonWithEvents(String nickname) {
        System.out.println("XDDDDDDDDDDDDDDDDDDDD");
        System.out.println(nickname);
        return personDao.getPersonWithEvents(nickname);
    }

    private static class InsertPersonAsyncTask extends AsyncTask<Person, Void, Void> {
        private PersonDao personDao;

        private InsertPersonAsyncTask(PersonDao personDao) {
            this.personDao = personDao;
        }

        @Override
        protected Void doInBackground(Person... people) {
            personDao.insert(people[0]);
            return null;
        }
    }

    private static class UpdatePersonAsyncTask extends AsyncTask<Person, Void, Void> {
        private PersonDao personDao;

        private UpdatePersonAsyncTask(PersonDao personDao) {
            this.personDao = personDao;
        }

        @Override
        protected Void doInBackground(Person... people) {
            personDao.update(people[0]);
            return null;
        }
    }

    private static class DeletePersonAsyncTask extends AsyncTask<Person, Void, Void> {
        private PersonDao personDao;

        private DeletePersonAsyncTask(PersonDao personDao) {
            this.personDao = personDao;
        }

        @Override
        protected Void doInBackground(Person... people) {
            personDao.delete(people[0]);
            return null;
        }
    }
}
