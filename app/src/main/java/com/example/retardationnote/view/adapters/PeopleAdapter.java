package com.example.retardationnote.view.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retardationnote.R;
import com.example.retardationnote.model.entities.Person;
import com.example.retardationnote.view.dialogs.AdvancedDeleteDialog;
import com.example.retardationnote.view.dialogs.AdvancedDeleteDialog.*;

import java.util.ArrayList;
import java.util.List;

public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.PersonHolder> {

    private List<Person> people;
    private AdvancedDeleteDialogListener deleteListener;
    private FragmentManager fragmentManager;
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    class PersonHolder extends RecyclerView.ViewHolder {
        private Button buttonDelete;
        private TextView textViewNickname;
        private TextView textViewPoints;

        public PersonHolder(View itemView) {
            super(itemView);
            buttonDelete = itemView.findViewById(R.id.button_delete);
            textViewNickname = itemView.findViewById(R.id.text_view_nickname);
            textViewPoints = itemView.findViewById(R.id.text_view_points);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            onItemClickListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    public PeopleAdapter(AdvancedDeleteDialogListener deleteListener, FragmentManager fragmentManager) {
        this.people = new ArrayList<>();
        this.deleteListener = deleteListener;
        this.fragmentManager = fragmentManager;
    }

    @NonNull
    @Override
    public PersonHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_person, parent, false);
        return new PersonHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonHolder holder, int position) {
        final Person currentPerson = people.get(position);
        holder.buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDeleteDialog(currentPerson);
            }
        });
        holder.textViewNickname.setText(currentPerson.getNickname());
        holder.textViewPoints.setText(Integer.toString(currentPerson.getPoints()));
    }

    @Override
    public int getItemCount() {
        return people.size();
    }

    public void setPeople(List<Person> people) {
        this.people = people;
        notifyDataSetChanged();
    }

    public Person getPerson(int position) {
        return people.get(position);
    }

    private void openDeleteDialog(Person person) {
        AdvancedDeleteDialog dialog = new AdvancedDeleteDialog(deleteListener, person, person.getNickname());
        dialog.show(fragmentManager, "Deleting Person Dialog");
    }
}
