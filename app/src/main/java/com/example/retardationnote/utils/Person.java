package com.example.retardationnote.utils;

import java.util.ArrayList;

public class Person {

    private ArrayList<Retardation> retardations = new ArrayList<>();
    private String nickname;

    public Person(String nickname) {
        this.nickname = nickname;
    }

    public void addRetardation(String name, int grade) {
        retardations.add(new Retardation(name, grade));
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

    /*
    public void load() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(nickname, Context.MODE_PRIVATE);

        int noArguments = sharedPreferences.getInt("noArguments", -1);
        for(int i = 0; i < noArguments; i++) {
            String argName = Integer.toString(i);
            retardations.add(new Retardation(sharedPreferences.getString(argName, null)));
        }
    }

    public void save() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(nickname, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        for(int i = 0; i < retardations.size(); i++) {
            String argName = Integer.toString(i);
            Retardation retardation = retardations.get(i);

            editor.putString(argName, retardation.toString());
            editor.apply();
        }
    }
    */
}
