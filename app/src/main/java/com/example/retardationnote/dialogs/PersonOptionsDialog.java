package com.example.retardationnote.dialogs;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.retardationnote.R;
import com.example.retardationnote.activities.PersonActivity;
import com.example.retardationnote.model.entities.Person;
import com.example.retardationnote.utils.ChosenObjects;

public class PersonOptionsDialog extends AppCompatDialogFragment implements
        AdvancedDeleteDialog.AdvancedDeleteDialogListener {

    private Button buttonChangeNickname;
    private Button buttonDelete;
    private Button buttonShowEvents;

    private PersonOptionsDialogListener listener;

    private AdvancedDeleteDialog advancedDeleteDialog;

    private Person chosenPerson;

    public PersonOptionsDialog(PersonOptionsDialogListener listener) {
        this.listener = listener;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_person_options, null);
        chosenPerson = ChosenObjects.currentlyChosenPerson;

        buttonChangeNickname = view.findViewById(R.id.button_change_nickname);
        buttonChangeNickname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
            }
        });

        buttonDelete = view.findViewById(R.id.button_delete);
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAdvancedDeleteDialog();
            }
        });

        buttonShowEvents = view.findViewById(R.id.button_show_events);
        buttonShowEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPersonActivity();
            }
        });

        builder.setView(view)
                .setTitle("Person Options");

        return builder.create();
    }

    private void openAdvancedDeleteDialog() {
        advancedDeleteDialog = new AdvancedDeleteDialog(this, chosenPerson, chosenPerson.getNickname());
        advancedDeleteDialog.show(getChildFragmentManager(), "Deleting person");
    }

    private void openPersonActivity() {
        Intent intent = new Intent(getActivity(), PersonActivity.class);
        startActivity(intent);
        dismiss();
    }

    @Override
    public void advancedDeleteFailure() {
        listener.deleteCurrentPersonFailure();
    }

    @Override
    public void advancedDeleteSuccess(Object object) {
        listener.deleteCurrentPersonSuccess((Person) object);
    }

    public interface PersonOptionsDialogListener {
        void deleteCurrentPersonFailure();
        void deleteCurrentPersonSuccess(Person person);
    }
}
