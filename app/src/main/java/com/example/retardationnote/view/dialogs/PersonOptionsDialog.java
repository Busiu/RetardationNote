package com.example.retardationnote.view.dialogs;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.retardationnote.R;
import com.example.retardationnote.view.activities.EventListActivity;

public class PersonOptionsDialog extends AppCompatDialogFragment {

    private Button buttonChangeNickname;
    private Button buttonShowEvents;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_person_options, null);

        buttonChangeNickname = view.findViewById(R.id.button_change_nickname);
        buttonChangeNickname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
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

    private void openPersonActivity() {
        Intent intent = new Intent(getActivity(), EventListActivity.class);
        startActivity(intent);
        dismiss();
    }
}
