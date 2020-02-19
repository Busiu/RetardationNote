package com.example.retardationnote.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.retardationnote.R;
import com.example.retardationnote.model.Event;

import java.util.Calendar;

public class AddEventDialog extends AppCompatDialogFragment implements
        DateTimePickerDialog.DateTimePickerDialogListener {

    private Button buttonChooseDate;
    private EditText editTextAddDescribtion;
    private TextView textViewChosenDate;

    private DateTimePickerDialog dateTimePickerDialog;

    private AddEventDialogListener addEventDialogListener;

    private int setYear;
    private int setMonth;
    private int setDay;
    private int setHour;
    private int setMinute;

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
                openDateTimePickerDialog();
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

    private void openDateTimePickerDialog() {
        dateTimePickerDialog = new DateTimePickerDialog(this);
        dateTimePickerDialog.show(getChildFragmentManager(), "Getting Date and Time");
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

    @Override
    public void setDateTime(int year, int month, int day, int hour, int minute) {
        setYear = year;
        setMonth = month;
        setDay = day;
        setHour = hour;
        setMinute = minute;
        textViewChosenDate.setText(getSetDate());
    }

    public interface AddEventDialogListener {
        void addEvent(Event event);
    }
}
