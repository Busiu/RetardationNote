package com.example.retardationnote.view.dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.retardationnote.R;
import com.example.retardationnote.model.entities.Person;
import com.example.retardationnote.viewmodel.PeopleListActivityViewModel;

public class AddPersonDialog extends AppCompatDialogFragment {

    private PeopleListActivityViewModel viewModel;
    private EditText editTextAddPerson;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_person, null);

        viewModel = new ViewModelProvider(requireActivity()).get(PeopleListActivityViewModel.class);

        editTextAddPerson = view.findViewById(R.id.edit_text_nickname);

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
                        addPerson(nickname);
                    }
                });

        return builder.create();
    }

    private void addPerson(String nickname) {
        viewModel.insert(new Person(nickname));
        Toast.makeText(getActivity(), "Person added successfully!", Toast.LENGTH_SHORT).show();
    }
}
