package com.example.retardationnote.model.converters;

import androidx.room.TypeConverter;

import com.example.retardationnote.model.entities.RetardationRank;

public class RetardationRankConverter {

    @TypeConverter
    public static RetardationRank fromString(String value) {
        return RetardationRank.valueOf(value);
    }

    @TypeConverter
    public static String RetardationRankToString(RetardationRank retardationRank) {
        return retardationRank.toString();
    }
}
