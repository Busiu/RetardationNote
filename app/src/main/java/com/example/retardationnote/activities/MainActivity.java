package com.example.retardationnote.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.retardationnote.R;
import com.example.retardationnote.dialogs.AddPersonDialog;
import com.example.retardationnote.utils.Person;

import java.util.HashSet;

public class MainActivity extends AppCompatActivity {

    private Button buttonAddPerson;

    private HashSet<Person> people = new HashSet<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonAddPerson = findViewById(R.id.button_add_person);

        buttonAddPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialogAddPerson();
            }
        });

        /*
        addPersonButton = findViewById(R.id.addPersonButton);
        addPersonEditText = findViewById(R.id.addPersonEditText);

        addPersonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nickname = addPersonEditText.getText().toString();
                System.out.println("lol");
                if(!nickname.equals("")) {
                    people.add(new Person(nickname));
                    Toast.makeText(getApplicationContext(), Integer.toString(people.size()), Toast.LENGTH_SHORT).show();
                }
            }
        });
        */

        //TODO: Sprobowac z GSON
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void openDialogAddPerson() {
        AddPersonDialog addPersonDialog = AddPersonDialog.newInstance(people);
        addPersonDialog.show(getSupportFragmentManager(), "Adding Person");
    }
}
