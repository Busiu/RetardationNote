package com.example.retardationnote.dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.retardationnote.R;
import com.example.retardationnote.model.Event;
import com.example.retardationnote.utils.ChosenObjects;

public class SimpleDeleteDialog extends AppCompatDialogFragment {

    private TextView textViewInfo;
    private SimpleDeleteDialogListener listener;
    private Event chosenEvent;

    public SimpleDeleteDialog(SimpleDeleteDialogListener listener) {
        this.listener = listener;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_simple_approval, null);
        chosenEvent = ChosenObjects.currentlyChosenEvent;

        textViewInfo = view.findViewById(R.id.text_view_info);

        builder.setView(view)
                .setTitle("Delete Chosen Event")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.delete(chosenEvent);
                    }
                });

        return builder.create();
    }

    public interface SimpleDeleteDialogListener {
        void delete(Event event);
    }
}
