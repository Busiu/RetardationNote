package com.example.retardationnote.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.retardationnote.R;
import com.example.retardationnote.adapters.EventAdapter;
import com.example.retardationnote.dialogs.AddEventDialog;
import com.example.retardationnote.dialogs.ChangeEventDescriptionDialog;
import com.example.retardationnote.dialogs.EventOptionsDialog;
import com.example.retardationnote.model.Event;
import com.example.retardationnote.model.Person;
import com.example.retardationnote.utils.PickedObjects;

import java.util.ArrayList;

public class PersonActivity extends AppCompatActivity implements
        AddEventDialog.AddEventDialogListener,
        ChangeEventDescriptionDialog.ChangeEventDescriptionDialogListener,
        EventOptionsDialog.EventOptionsDialogListener {

    private Button buttonAddEvent;
    private ListView listViewEvents;
    private EventAdapter eventAdapter;

    private Person owner;
    private ArrayList<Event> events = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        owner = PickedObjects.currentlyPickedPerson;
        events = owner.getEvents();

        Toast.makeText(this, owner.getNickname(), Toast.LENGTH_SHORT).show();

        listViewEvents = findViewById(R.id.list_view_events);
        eventAdapter = new EventAdapter(this, R.layout.list_view_people, events);
        listViewEvents.setAdapter(eventAdapter);
        listViewEvents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                openEventOptionsDialog(position);
            }
        });

        buttonAddEvent = findViewById(R.id.button_add_event);
        buttonAddEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddEventDialog();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PickedObjects.currentlyPickedPerson = null;
    }

    private void openAddEventDialog() {
        AddEventDialog addEventDialog = new AddEventDialog();
        addEventDialog.show(getSupportFragmentManager(), "Adding Event");
    }

    private void openEventOptionsDialog(int position) {
        PickedObjects.currenlyPickedEvent = events.get(position);
        EventOptionsDialog eventOptionsDialog = new EventOptionsDialog(this);
        eventOptionsDialog.show(getSupportFragmentManager(), "Event Options");
    }

    @Override
    public void addEvent(Event event) {
        eventAdapter.addEvent(event);
        Toast.makeText(this, "Event added successfully!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void changeEventDescribtion() {
        eventAdapter.notifyDataSetChanged();
        Toast.makeText(this, "Describtion changed successfully!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setActualDate() {
        eventAdapter.notifyDataSetChanged();
        Toast.makeText(this, "Actual date set successfully!", Toast.LENGTH_SHORT).show();
    }
}
