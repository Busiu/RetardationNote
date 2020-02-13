package com.example.retardationnote.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.retardationnote.R;
import com.example.retardationnote.model.Person;
import com.example.retardationnote.utils.NoDuplicateArrayList;

public class PeopleAdapter extends ArrayAdapter<Person> {

    private Context context;
    private int layoutResourceId;

    private NoDuplicateArrayList<Person> people;

    public PeopleAdapter(Context context, int layoutResourceId, NoDuplicateArrayList<Person> people) {
        super(context, layoutResourceId, people.getArrayList());

        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.people = people;
    }

    private class ViewHolder {
        private Button buttonDetails;
        private Button buttonRemove;
        private TextView textViewNickname;
        private TextView textViewPoints;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();

        if(convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            convertView = layoutInflater.inflate(R.layout.list_view_people, parent, false);

            viewHolder.buttonDetails = convertView.findViewById(R.id.button_details);
            viewHolder.buttonRemove = convertView.findViewById(R.id.button_remove);
            viewHolder.textViewNickname = convertView.findViewById(R.id.text_view_nickname);
            viewHolder.textViewPoints = convertView.findViewById(R.id.text_view_points);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Person person = people.get(position);
        viewHolder.textViewNickname.setText(person.getNickname());
        viewHolder.textViewPoints.setText(person.getPointsToString());

        return convertView;
    }

    public void addPerson(String nickname) {
        Person newPerson = new Person(nickname);
        boolean result = people.add(newPerson);
        if (result) {
            notifyDataSetChanged();
            Toast.makeText(context, "Person added successfully!", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Such a person already exists!", Toast.LENGTH_SHORT).show();
        }
    }
}
