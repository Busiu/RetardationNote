package com.example.retardationnote.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.retardationnote.R;
import com.example.retardationnote.dialogs.AddEventDialog;

public class PersonActivity extends AppCompatActivity implements
        AddEventDialog.AddEventDialogListener {

    private Button buttonAddEvent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);

        buttonAddEvent = findViewById(R.id.button_add_event);
        buttonAddEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddEventDialog();
            }
        });
    }

    private void openAddEventDialog() {
        AddEventDialog addEventDialog = new AddEventDialog();
        addEventDialog.show(getSupportFragmentManager(), "Adding Event");
    }

    @Override
    public void addDate(String date) {

    }

    @Override
    public void addDescribtion(String nickname) {

    }
}
