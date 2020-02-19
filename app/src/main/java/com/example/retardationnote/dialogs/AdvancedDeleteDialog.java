package com.example.retardationnote.dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.retardationnote.R;

public class AdvancedDeleteDialog extends AppCompatDialogFragment {

    private EditText editTextKey;
    private TextView textViewInfo;

    private AdvancedDeleteDialogListener listener;

    private Object chosenObject;

    private String key;

    public AdvancedDeleteDialog(AdvancedDeleteDialogListener listener, Object chosenObject, String key) {
        this.listener = listener;
        this.chosenObject = chosenObject;
        this.key = key;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_advanced_approval, null);

        editTextKey = view.findViewById(R.id.edit_text_key);

        textViewInfo = view.findViewById(R.id.text_view_info);
        textViewInfo.setText(getInfo());

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
                        String enteredKey = editTextKey.getText().toString();
                        if (enteredKey.equals(key)) {
                            listener.advancedDeleteSuccess(chosenObject);
                        }
                        else{
                            listener.advancedDeleteFailure();
                        }
                    }
                });

        return builder.create();
    }

    private String getInfo() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Are you sure? To proceed, please enter ");
        stringBuilder.append('"');
        stringBuilder.append(key);
        stringBuilder.append('"');
        stringBuilder.append(" and click DELETE");

        return stringBuilder.toString();
    }

    public interface AdvancedDeleteDialogListener {
        void advancedDeleteSuccess(Object object);
        void advancedDeleteFailure();
    }
}