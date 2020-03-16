package com.example.retardationnote.view.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retardationnote.R;
import com.example.retardationnote.view.adapters.EventAdapter;
import com.example.retardationnote.view.dialogs.AddEventDialog;
import com.example.retardationnote.model.entities.Event;
import com.example.retardationnote.viewmodel.EventListActivityViewModel;

import java.util.List;

public class EventListActivity extends AppCompatActivity implements
        AddEventDialog.AddEventDialogListener {

    private EventListActivityViewModel viewModel;

    private Button buttonAddEvent;
    private RecyclerView recyclerViewEvents;
    private EventAdapter eventAdapter;

    private AddEventDialog addEventDialog;
    //private EventOptionsDialog eventOptionsDialog;

    private String chosenPersonNickname;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            chosenPersonNickname = bundle.getString("chosenPersonNickname");
            throw new NullPointerException();
        }

        recyclerViewEvents = findViewById(R.id.recycler_view_events);
        recyclerViewEvents.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewEvents.setHasFixedSize(true);
        eventAdapter = new EventAdapter();
        eventAdapter.setOnItemClickListener(new EventAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                openEventOptionsDialog(position);
            }
        });
        recyclerViewEvents.setAdapter(eventAdapter);

        buttonAddEvent = findViewById(R.id.button_add_event);
        buttonAddEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddEventDialog();
            }
        });

        viewModel = new ViewModelProvider(this).get(EventListActivityViewModel.class);
        viewModel.getPersonWithEvents(chosenPersonNickname);
        viewModel.getAllEvents().observe(this, new Observer<List<Event>>() {
            @Override
            public void onChanged(List<Event> events) {
                eventAdapter.setEvents(events);
            }
        });
    }

    private void openAddEventDialog() {
        addEventDialog = new AddEventDialog(chosenPersonNickname, this);
        addEventDialog.show(getSupportFragmentManager(), "Adding Event");
    }

    private void openEventOptionsDialog(int position) {
        //TODO
        //ChosenObjects.currentlyChosenEvent = events.get(position);
        //eventOptionsDialog = new EventOptionsDialog(this);
        //eventOptionsDialog.show(getSupportFragmentManager(), "Event Options");
    }

    @Override
    public void addEvent(Event event) {
        viewModel.insert(event);
        Toast.makeText(this, "Event added successfully!", Toast.LENGTH_SHORT).show();
    }

    /*
    @Override
    public void changeEventDescribtion() {
        eventAdapter.notifyDataSetChanged();
        Toast.makeText(this, "Describtion changed successfully!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void deleteCurrentEvent(Event event) {
        eventAdapter.deleteEvent(event);
        eventOptionsDialog.dismiss();
        Toast.makeText(this, "Event deleted successfully!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setActualDate() {
        eventAdapter.notifyDataSetChanged();
        Toast.makeText(this, "Actual date set successfully!", Toast.LENGTH_SHORT).show();
    }
    */
}
