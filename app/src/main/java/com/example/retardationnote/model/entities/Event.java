package com.example.retardationnote.model.entities;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Date;

import static androidx.room.ForeignKey.CASCADE;

@Entity(
        tableName = "event_table",
        foreignKeys = @ForeignKey(
                entity = Person.class,
                parentColumns = "nickname",
                childColumns = "owner",
                onDelete = CASCADE
        )
)
public class Event {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(index = true)
    private String owner;

    @ColumnInfo(name = "actual_date")
    @Nullable
    private Date actualDate;

    @ColumnInfo(name = "planned_date")
    private Date plannedDate;

    @Nullable
    private RetardationRank rank;

    private String describtion;

    public Event(String describtion, Date plannedDate) {
        this.describtion = describtion;
        this.plannedDate = plannedDate;

        this.rank = null;
        this.actualDate = null;
    }

    public int getId() {
        return id;
    }

    @Nullable
    public Date getActualDate() {
        return actualDate;
    }

    public Date getPlannedDate() {
        return plannedDate;
    }

    @Nullable
    public RetardationRank getRank() {
        return rank;
    }

    public String getOwner() {
        return owner;
    }

    public int getPoints() {
        return rank.getPoints();
    }

    public String getPlannedDateToString() {
        return plannedDate.toString();
    }

    public String getRankToString() {
        if (rank == null) {
            return "event not ended";
        }
        return rank.toString();
    }

    public String getDescribtion() {
        return describtion;
    }

    public String getRetardationToString() {
        if (actualDate == null) {
            return "event not ended";
        }

        long a = actualDate.getTime() / (1000 * 60);
        long b = plannedDate.getTime() / (1000 * 60);
        return Long.toString(a - b);
    }

    public void setActualDate(Date actualDate) {
        this.actualDate = actualDate;
    }

    public void setDescribtion(String describtion) {
        this.describtion = describtion;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setPlannedDate(Date plannedDate) {
        this.plannedDate = plannedDate;
    }

    public void setRank(@Nullable RetardationRank rank) {
        this.rank = rank;
    }
}
