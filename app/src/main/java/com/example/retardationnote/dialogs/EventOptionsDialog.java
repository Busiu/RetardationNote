package com.example.retardationnote.dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.retardationnote.R;
import com.example.retardationnote.utils.PickedObjects;

public class EventOptionsDialog extends AppCompatDialogFragment {

    private Button buttonChangeDescribtion;
    private Button buttonSetActualDate;
    private Button buttonDelete;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_event_options, null);

        buttonChangeDescribtion = view.findViewById(R.id.button_change_description);
        buttonChangeDescribtion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangeEventDescriptionDialog dialog = new ChangeEventDescriptionDialog();
                dialog.show(getFragmentManager(), "Changing Event Describtion");
            }
        });

        buttonSetActualDate = view.findViewById(R.id.button_set_actual_date);
        buttonSetActualDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
            }
        });

        buttonDelete = view.findViewById(R.id.button_delete);
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
            }
        });

        builder.setView(view)
                .setTitle("Event Options")
                .setNegativeButton("exit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        return builder.create();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        PickedObjects.currenlyPickedEvent = null;
    }
}
