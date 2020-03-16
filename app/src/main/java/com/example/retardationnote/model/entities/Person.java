package com.example.retardationnote.model.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity(
        tableName = "person_table",
        indices = {
                @Index(value = {"nickname"}, unique = true)
        }
)
public class Person {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    private String nickname;

    private int points;

    public Person(@NonNull String nickname) {
        this.nickname = nickname;
        this.points = 0;
    }

    public int getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public int getPoints() {
        return points;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNickname(@NonNull String nickname) {
        this.nickname = nickname;
    }

    public void setPoints(int points) {
        this.points = points;
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
