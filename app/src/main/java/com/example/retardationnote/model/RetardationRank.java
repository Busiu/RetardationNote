package com.example.retardationnote.model;

import androidx.annotation.NonNull;

public enum RetardationRank {

    DIDDY("DIDDY", 5),
    ANY("ANY", 0),
    VERY_SMALL("VERY SMALL", -1),
    SMALL("SMALL", -2),
    NORMAL("NORMAL", -3),
    SERIOUS("SERIOUS", -5),
    VERY_SERIOUS("VERY SERIOUS", -7),
    EXTREME("EXTREME", -10),
    XD("XD", -15);

    private String name;
    private int points;

    RetardationRank(String name, int points) {
        this.name = name;
        this.points = points;
    }

    @NonNull
    @Override
    public String toString() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    public String getPointsToString() {
        if (points > 0) {
            return "+" + points;
        }
        return Integer.toString(points);
    }

    public RetardationRank getRank(int minutesOfRetardation) {
        if (minutesOfRetardation <= 0) {
            return DIDDY;
        }
        if (minutesOfRetardation <= 5) {
            return ANY;
        }
        if (minutesOfRetardation <= 10) {
            return VERY_SMALL;
        }
        if (minutesOfRetardation <= 20) {
            return SMALL;
        }
        if (minutesOfRetardation <= 30) {
            return NORMAL;
        }
        if (minutesOfRetardation <= 45) {
           return SERIOUS;
        }
        if (minutesOfRetardation <= 60) {
            return VERY_SERIOUS;
        }
        if (minutesOfRetardation <= 120) {
            return EXTREME;
        }
        return XD;
    }
}
