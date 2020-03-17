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

public class ChangePersonNicknameDialog extends AppCompatDialogFragment {

    private PeopleListActivityViewModel viewModel;
    private EditText editTextChangeNickname;
    private Person chosenPerson;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_person, null);

        viewModel = new ViewModelProvider(requireActivity()).get(PeopleListActivityViewModel.class);
        chosenPerson = viewModel.getChosenPerson();

        editTextChangeNickname = view.findViewById(R.id.edit_text_nickname);
        editTextChangeNickname.setText(chosenPerson.getNickname());

        builder.setView(view)
                .setTitle("Change Nickname")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("change", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String newNickname = editTextChangeNickname.getText().toString();
                        changePerson(newNickname);
                    }
                });

        return builder.create();
    }

    private void changePerson(String nickname) {
        chosenPerson.setNickname(nickname);
        viewModel.update(chosenPerson);
        Toast.makeText(getActivity(), "Person nickname changed successfully!", Toast.LENGTH_SHORT).show();
    }
}