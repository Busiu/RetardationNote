package com.example.retardationnote.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Retardation {

    //private final static String separator = ";";

    private Date date;
    private int grade;
    private String name;

    public Retardation(String name, int grade) {
        this.date = Calendar.getInstance().getTime();
        this.grade = grade;
        this.name = name;
    }

    public String getDate() {
        DateFormat dateFormat = DateFormat.getDateTimeInstance();
        return dateFormat.format(date);
    }

    public int getGrade() {
        return grade;
    }

    public String getName() {
        return name;
    }

    /*
    public Retardation(String data) {
        String[] tmp = data.split(separator);
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss", Locale.getDefault());

        try {
            this.date = formatter.parse(tmp[0]);
            this.name = tmp[1];
        }
        catch(ParseException e) {
            //TODO
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append(date.toString());
        stringBuffer.append(separator);
        stringBuffer.append(name);

        return stringBuffer.toString();
    }
    */
}
