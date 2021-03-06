package com.example.retardationnote.view.dialogs;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.retardationnote.R;
import com.example.retardationnote.model.entities.Event;

import java.util.Calendar;

public class EventOptionsDialog extends AppCompatDialogFragment implements
        ChangeEventDescriptionDialog.ChangeEventDescriptionDialogListener,
        DateTimePickerDialog.DateTimePickerDialogListener,
        SimpleDeleteDialog.SimpleDeleteDialogListener {

    private Button buttonChangeDescribtion;
    private Button buttonSetActualDate;
    private Button buttonDelete;

    private EventOptionsDialogListener listener;

    private ChangeEventDescriptionDialog changeEventDescriptionDialog;
    private DateTimePickerDialog dateTimePickerDialog;
    private SimpleDeleteDialog simpleDeleteDialog;

    private Event chosenEvent;

    public EventOptionsDialog(EventOptionsDialogListener listener) {
        this.listener = listener;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_event_options, null);

        buttonChangeDescribtion = view.findViewById(R.id.button_change_description);
        buttonChangeDescribtion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openChangeEventDescribtionDialog();
            }
        });

        buttonSetActualDate = view.findViewById(R.id.button_set_actual_date);
        buttonSetActualDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSetActualDateDialog();
            }
        });

        buttonDelete = view.findViewById(R.id.button_delete);
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDeleteEventDialog();
            }
        });

        builder.setView(view)
                .setTitle("Event Options");

        return builder.create();
    }

    private void openChangeEventDescribtionDialog() {
        changeEventDescriptionDialog = new ChangeEventDescriptionDialog(this);
        changeEventDescriptionDialog.show(getChildFragmentManager(), "Changing Event Describtion");
    }

    private void openDeleteEventDialog() {
        simpleDeleteDialog = new SimpleDeleteDialog(this, chosenEvent);
        simpleDeleteDialog.show(getChildFragmentManager(), "Deleting Event");
    }

    private void openSetActualDateDialog() {
        dateTimePickerDialog = new DateTimePickerDialog(this);
        dateTimePickerDialog.show(getChildFragmentManager(), "Setting actual date");
    }

    @Override
    public void changeEventDescribtion() {
        listener.changeEventDescribtion();
    }

    @Override
    public void simpleDelete(Object object) {
        listener.deleteCurrentEvent((Event) object);
    }

    @Override
    public void setDateTime(int year, int month, int day, int hour, int minute) {
        Calendar actualDate = Calendar.getInstance();
        actualDate.set(year, month, day, hour, minute);

        dateTimePickerDialog.dismiss();

        //chosenEvent.setActualDate(actualDate);
        listener.setActualDate();
    }

    public interface EventOptionsDialogListener {
        void changeEventDescribtion();
        void deleteCurrentEvent(Event event);
        void setActualDate();
    }
}
