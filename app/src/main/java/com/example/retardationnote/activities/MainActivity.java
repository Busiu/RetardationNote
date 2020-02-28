package com.example.retardationnote.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.retardationnote.R;
import com.example.retardationnote.adapters.PeopleAdapter;
import com.example.retardationnote.dialogs.AddPersonDialog;
import com.example.retardationnote.dialogs.PersonOptionsDialog;
import com.example.retardationnote.model.entities.Person;
import com.example.retardationnote.utils.NoDuplicateArrayList;
import com.example.retardationnote.utils.ChosenObjects;
import com.example.retardationnote.viewmodel.MainActivityViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MainActivityViewModel viewModel;

    private Button buttonAddPerson;
    private ListView listViewPeople;
    private PeopleAdapter peopleAdaper;

    private AddPersonDialog addPersonDialog;
    private PersonOptionsDialog personOptionsDialog;

    private NoDuplicateArrayList<Person> people = new NoDuplicateArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        viewModel.getAllPeople().observe(this, new Observer<List<Person>>() {
            @Override
            public void onChanged(List<Person> people) {
                Toast.makeText(MainActivity.this, "all people loaded", Toast.LENGTH_SHORT).show();
            }
        });

        /*
        listViewPeople = findViewById(R.id.list_view_people);
        peopleAdaper = new PeopleAdapter(this, R.layout.list_view_people, people);
        listViewPeople.setAdapter(peopleAdaper);
        listViewPeople.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                openPersonOptionsDialog(position);
            }
        });

        buttonAddPerson = findViewById(R.id.button_add_person);
        buttonAddPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddPersonDialog();
            }
        });
        */

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /*
    private void openPersonOptionsDialog(int position) {
        ChosenObjects.currentlyChosenPerson = people.get(position);
        personOptionsDialog = new PersonOptionsDialog(this);
        personOptionsDialog.show(getSupportFragmentManager(), "Person Options");
    }

    private void openAddPersonDialog() {
        addPersonDialog = new AddPersonDialog(this);
        addPersonDialog.show(getSupportFragmentManager(), "Adding Person");
    }

    @Override
    public void addPerson(Person person) {
        peopleAdaper.addPerson(person);
        Toast.makeText(this, "Person added successfully!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void deleteCurrentPersonFailure() {
        personOptionsDialog.dismiss();
        Toast.makeText(this, "Failed to delete the person!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void deleteCurrentPersonSuccess(Person person) {
        peopleAdaper.remove(person);
        personOptionsDialog.dismiss();
        Toast.makeText(this, "Person deleted successfully!", Toast.LENGTH_SHORT).show();
    }
     */
}
