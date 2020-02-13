package com.example.retardationnote.dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.retardationnote.R;
import com.example.retardationnote.utils.Person;

import java.util.HashSet;

public class AddPersonDialog extends AppCompatDialogFragment {

    private EditText editTextAddPerson;

    private HashSet<Person> people = new HashSet<>();
    private static final String peopleKey = "people";

    public static AddPersonDialog newInstance(HashSet people) {
        AddPersonDialog addPersonDialog = new AddPersonDialog();

        Bundle args = new Bundle();
        args.putSerializable(peopleKey, people);
        addPersonDialog.setArguments(args);

        return addPersonDialog;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        getData();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_person, null);

        editTextAddPerson = view.findViewById(R.id.edit_text_add_person);

        builder.setView(view)
                .setTitle("Add Person")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String nickname = editTextAddPerson.getText().toString();
                        boolean result = people.add(new Person(nickname));
                        if (result) {
                            Toast.makeText(getContext(), "Person added successfully!", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(getContext(), "Such a person already exists!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        return builder.create();
    }

    @SuppressWarnings("unchecked")
    private void getData() {
        this.people = (HashSet<Person>) getArguments().getSerializable(peopleKey);
    }
}
