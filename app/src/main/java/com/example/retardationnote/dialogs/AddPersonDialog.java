package com.example.retardationnote.dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.retardationnote.R;
import com.example.retardationnote.model.Person;

public class AddPersonDialog extends AppCompatDialogFragment {

    private EditText editTextAddPerson;
    private AddPersonDialogListener listener;

    public AddPersonDialog(AddPersonDialogListener listener) {
        this.listener = listener;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
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
                        listener.addPerson(new Person(nickname));
                    }
                });

        return builder.create();
    }

    public interface AddPersonDialogListener {
        void addPerson(Person person);
    }
}
