package com.example.retardationnote.dialogs;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.retardationnote.R;
import com.example.retardationnote.model.Event;

import java.util.Calendar;

public class AddEventDialog extends AppCompatDialogFragment implements
        DatePickerDialog.OnDateSetListener,
        TimePickerDialog.OnTimeSetListener {

    private Button buttonChooseDate;
    private EditText editTextAddDescribtion;
    private TextView textViewChosenDate;

    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;

    private AddEventDialogListener addEventDialogListener;

    private int currentYear, setYear;
    private int currentMonth, setMonth;
    private int currentDay, setDay;
    private int currentHour, setHour;
    private int currentMinute, setMinute;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_event, null);

        editTextAddDescribtion = view.findViewById(R.id.edit_text_describtion);

        textViewChosenDate = view.findViewById(R.id.text_view_date);
        buttonChooseDate = view.findViewById(R.id.button_choose_date);
        buttonChooseDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDatePickerDialog();
            }
        });

        builder.setView(view)
                .setTitle("Add Event")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String description = editTextAddDescribtion.getText().toString();
                        Calendar date = Calendar.getInstance();
                        date.set(setYear, setMonth, setDay, setHour, setMinute);
                        addEventDialogListener.addEvent(new Event(description, date));
                    }
                });

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            addEventDialogListener = (AddEventDialogListener) context;
        }
        catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement " + AddEventDialogListener.class.getName());
        }
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

        textViewChosenDate.setText(getSetDate());
    }

    private void openDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();

        currentYear = calendar.get(Calendar.YEAR);
        currentMonth = calendar.get(Calendar.MONTH);
        currentDay = calendar.get(Calendar.DAY_OF_MONTH);

        datePickerDialog = new DatePickerDialog(getContext(), AddEventDialog.this,
                currentYear, currentMonth, currentDay);
        datePickerDialog.show();
    }

    private void openTimePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        currentHour = calendar.get(Calendar.HOUR);
        currentMinute = calendar.get(Calendar.MINUTE);

        timePickerDialog = new TimePickerDialog(getContext(), AddEventDialog.this,
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

    private String getSetDate() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(setYear);
        stringBuilder.append('/');
        stringBuilder.append(setMonth);
        stringBuilder.append('/');
        stringBuilder.append(setDay);
        stringBuilder.append(' ');
        stringBuilder.append(setHour);
        stringBuilder.append(':');
        stringBuilder.append(setMinute);

        return stringBuilder.toString();
    }

    public interface AddEventDialogListener {
        void addEvent(Event event);
    }
}
