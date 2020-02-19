package com.example.retardationnote.dialogs;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import java.util.Calendar;

public class DateTimePickerDialog extends AppCompatDialogFragment implements
        DatePickerDialog.OnDateSetListener,
        TimePickerDialog.OnTimeSetListener {

    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;

    private DateTimePickerDialogListener listener;

    private int currentYear, setYear;
    private int currentMonth, setMonth;
    private int currentDay, setDay;
    private int currentHour, setHour;
    private int currentMinute, setMinute;

    public DateTimePickerDialog(DateTimePickerDialogListener listener) {
        this.listener = listener;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        return builder.create();
    }

    @Override
    public void onStart() {
        super.onStart();
        openDatePickerDialog();
    }

    private void openDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();

        currentYear = calendar.get(Calendar.YEAR);
        currentMonth = calendar.get(Calendar.MONTH);
        currentDay = calendar.get(Calendar.DAY_OF_MONTH);

        datePickerDialog = new DatePickerDialog(getContext(), this,
                currentYear, currentMonth, currentDay);
        datePickerDialog.show();
    }

    private void openTimePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        currentHour = calendar.get(Calendar.HOUR);
        currentMinute = calendar.get(Calendar.MINUTE);

        timePickerDialog = new TimePickerDialog(getContext(), this,
                currentHour, currentMinute, true);
        timePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "undo", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == DialogInterface.BUTTON_NEGATIVE) {
                    openDatePickerDialog();
                }
            }
        });
        timePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        setYear = year;
        setMonth = month;
        setDay = day;

        openTimePickerDialog();
    }

    @Override
    public void onTimeSet(TimePicker view, int hour, int minute) {
        setHour = hour;
        setMinute = minute;

        listener.setDateTime(setYear, setMonth, setDay, setHour, setMinute);
    }

    public interface DateTimePickerDialogListener {
        void setDateTime(int year, int month, int day, int hour, int minute);
    }
}
