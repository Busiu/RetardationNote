package com.example.retardationnote.model;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.retardationnote.model.converters.DateConverter;
import com.example.retardationnote.model.converters.RetardationRankConverter;
import com.example.retardationnote.model.daos.EventDao;
import com.example.retardationnote.model.daos.PersonDao;
import com.example.retardationnote.model.entities.Event;
import com.example.retardationnote.model.entities.Person;

@Database(
        entities = {
                Event.class,
                Person.class},
        version = 1)
@TypeConverters({
        DateConverter.class,
        RetardationRankConverter.class})
public abstract class RetardationNoteDatabase extends RoomDatabase {

    private static RetardationNoteDatabase instance;

    public abstract EventDao eventDao();
    public abstract PersonDao personDao();

    public static synchronized RetardationNoteDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    RetardationNoteDatabase.class,
                    "retardation_note.db")
                    .fallbackToDestructiveMigration()
                    .addCallback(populateCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback populateCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDatabaseAsyncTask(instance).execute();
        }
    };

    private static class PopulateDatabaseAsyncTask extends AsyncTask<Void, Void, Void> {
        private PersonDao personDao;

        private PopulateDatabaseAsyncTask(RetardationNoteDatabase database) {
            personDao = database.personDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            personDao.insert(new Person("Busiu"));
            personDao.insert(new Person("Piotr"));
            personDao.insert(new Person("Bułka"));
            personDao.insert(new Person("Krzysztof"));
            return null;
        }
    }
}
