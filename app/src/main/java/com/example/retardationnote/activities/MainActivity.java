package com.example.retardationnote.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.retardationnote.R;
import com.example.retardationnote.adapters.PeopleAdapter;
import com.example.retardationnote.dialogs.AddPersonDialog;
import com.example.retardationnote.model.Person;
import com.example.retardationnote.utils.NoDuplicateArrayList;
import com.example.retardationnote.utils.PickedObjects;

public class MainActivity extends AppCompatActivity implements
        AddPersonDialog.AddPersonDialogListener {

    private Button buttonAddPerson;
    private ListView listViewPeople;
    private PeopleAdapter peopleAdaper;

    private NoDuplicateArrayList<Person> people = new NoDuplicateArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        people.add(new Person("Piotr"));
        people.add(new Person("Busiu"));

        listViewPeople = findViewById(R.id.list_view_people);
        peopleAdaper = new PeopleAdapter(this, R.layout.list_view_people, people);
        listViewPeople.setAdapter(peopleAdaper);
        listViewPeople.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                openPersonActivity(position);
            }
        });

        buttonAddPerson = findViewById(R.id.button_add_person);
        buttonAddPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddPersonDialog();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void openPersonActivity(int position) {
        Intent intent = new Intent(MainActivity.this, PersonActivity.class);
        PickedObjects.currentlyPickedPerson = people.get(position);
        startActivity(intent);
    }

    private void openAddPersonDialog() {
        AddPersonDialog addPersonDialog = new AddPersonDialog();
        addPersonDialog.show(getSupportFragmentManager(), "Adding Person");
    }

    @Override
    public void addPerson(Person person) {
        peopleAdaper.addPerson(person);
    }
}
