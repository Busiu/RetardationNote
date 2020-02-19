package com.example.retardationnote.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.retardationnote.R;
import com.example.retardationnote.model.Event;
import com.example.retardationnote.utils.ChosenObjects;

public class ChangeEventDescriptionDialog extends AppCompatDialogFragment {

    private EditText editTextChangeDescribtion;
    private ChangeEventDescriptionDialogListener listener;
    private Event chosenEvent;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_change_event_description, null);
        chosenEvent = ChosenObjects.currentlyChosenEvent;

        editTextChangeDescribtion = view.findViewById(R.id.edit_text_change_describtion);
        editTextChangeDescribtion.setText(chosenEvent.getDescribtion());

        builder.setView(view)
                .setTitle("Change Describtion")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("change", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String describtion = editTextChangeDescribtion.getText().toString();
                        chosenEvent.setDescribtion(describtion);
                        listener.changeEventDescribtion();
                    }
                });

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (ChangeEventDescriptionDialogListener) context;
        }
        catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement " + ChangeEventDescriptionDialogListener.class.getName());
        }
    }

    public interface ChangeEventDescriptionDialogListener {
        void changeEventDescribtion();
    }
}
