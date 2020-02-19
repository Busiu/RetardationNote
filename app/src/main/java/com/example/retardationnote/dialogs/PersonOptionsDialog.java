package com.example.retardationnote.dialogs;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.retardationnote.R;

public class PersonOptionsDialog extends AppCompatDialogFragment {

    public PersonOptionsDialogListener listener;

    public PersonOptionsDialog(PersonOptionsDialogListener listener) {
        this.listener = listener;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_person_options, null);

        builder.setView(view)
                .setTitle("Person Options");

        return builder.create();
    }

    public interface PersonOptionsDialogListener {

    }
}
