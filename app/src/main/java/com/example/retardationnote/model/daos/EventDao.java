package com.example.retardationnote.model.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.retardationnote.model.entities.Event;

import java.util.List;

@Dao
public interface EventDao {

    @Insert
    void insert(Event event);

    @Delete
    void delete(Event event);

    @Update
    void update(Event event);

    @Query("SELECT * FROM event_table WHERE owner LIKE :nickname ORDER BY planned_date DESC")
    LiveData<List<Event>> getAllChosenPersonEvents(String nickname);

    @Query("SELECT * FROM event_table ORDER BY planned_date DESC")
    LiveData<List<Event>> getAllEvents();
}
