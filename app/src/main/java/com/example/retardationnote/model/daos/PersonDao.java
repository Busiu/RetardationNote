package com.example.retardationnote.model.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.retardationnote.model.entities.Person;

import java.util.List;

@Dao
public interface PersonDao {

    @Insert
    void insert(Person person);

    @Delete
    void delete(Person person);

    @Update
    void update(Person person);

    @Query("SELECT * FROM person_table ORDER BY nickname ASC")
    LiveData<List<Person>> getAllPeople();
}
