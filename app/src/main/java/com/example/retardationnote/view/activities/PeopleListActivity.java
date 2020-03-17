package com.example.retardationnote.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.retardationnote.R;
import com.example.retardationnote.view.adapters.PeopleAdapter;
import com.example.retardationnote.view.dialogs.AddPersonDialog;
import com.example.retardationnote.view.dialogs.AdvancedDeleteDialog;
import com.example.retardationnote.view.dialogs.ChangePersonNicknameDialog;
import com.example.retardationnote.view.dialogs.PersonOptionsDialog;
import com.example.retardationnote.model.entities.Person;
import com.example.retardationnote.viewmodel.PeopleListActivityViewModel;

import java.util.List;

public class PeopleListActivity extends AppCompatActivity implements
        AdvancedDeleteDialog.AdvancedDeleteDialogListener {

    private PeopleListActivityViewModel viewModel;

    private Button buttonAddPerson;
    private RecyclerView recyclerViewPeople;
    private PeopleAdapter peopleAdapter;

    private AddPersonDialog addPersonDialog;
    private PersonOptionsDialog personOptionsDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people_list);

        recyclerViewPeople = findViewById(R.id.recycler_view_people);
        recyclerViewPeople.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewPeople.setHasFixedSize(true);
        peopleAdapter = new PeopleAdapter(this, getSupportFragmentManager());
        peopleAdapter.setOnItemClickListener(new PeopleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                openPersonOptionsDialog(position);
            }
        });
        recyclerViewPeople.setAdapter(peopleAdapter);

        buttonAddPerson = findViewById(R.id.button_add_person);
        buttonAddPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddPersonDialog();
            }
        });

        viewModel = new ViewModelProvider(this).get(PeopleListActivityViewModel.class);
        viewModel.getAllPeople().observe(this, new Observer<List<Person>>() {
            @Override
            public void onChanged(List<Person> people) {
                peopleAdapter.setPeople(people);
            }
        });
    }

    private void openAddPersonDialog() {
        addPersonDialog = new AddPersonDialog();
        addPersonDialog.show(getSupportFragmentManager(), "Adding Person");
    }

    private void openPersonOptionsDialog(int position) {
        viewModel.setChosenPerson(peopleAdapter.getPerson(position));
        personOptionsDialog = new PersonOptionsDialog();
        personOptionsDialog.show(getSupportFragmentManager(), "Person Options");
    }

    @Override
    public void advancedDeleteSuccess(Object object) {
        viewModel.delete((Person) object);
        Toast.makeText(this, "Person deleted successfully!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void advancedDeleteFailure() {
        Toast.makeText(this, "Failed to delete the person!", Toast.LENGTH_SHORT).show();
    }
}
