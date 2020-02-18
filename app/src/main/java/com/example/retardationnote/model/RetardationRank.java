package com.example.retardationnote.model;

public enum RetardationRank {

    DIDDY("DIDDY"),
    ANY("ANY"),
    VERY_SMALL("VERY SMALL"),
    SMALL("SMALL"),
    NORMAL("NORMAL"),
    SERIOUS("SERIOUS"),
    VERY_SERIOUS("VERY SERIOUS"),
    EXTREME("EXTREME"),
    XD("XD");

    private String name;

    RetardationRank(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
