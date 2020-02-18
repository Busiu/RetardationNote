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
import com.example.retardationnote.model.Event;

import java.util.ArrayList;

public class EventAdapter extends ArrayAdapter<Event> {

    private ArrayList<Event> events;

    public EventAdapter(Context context, int layoutResourceId, ArrayList<Event> events) {
        super(context, layoutResourceId, events);
        this.events = events;
    }

    private class ViewHolder {
        private Button buttonRemove;
        private TextView textViewRank;
        private TextView textViewDescribtion;
        private TextView textViewDate;
        private TextView textViewRetardation;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();

        if(convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            convertView = layoutInflater.inflate(R.layout.list_view_events, parent, false);

            viewHolder.buttonRemove = convertView.findViewById(R.id.button_remove);
            viewHolder.textViewRank = convertView.findViewById(R.id.text_view_rank);
            viewHolder.textViewDescribtion = convertView.findViewById(R.id.text_view_description);
            viewHolder.textViewDate = convertView.findViewById(R.id.text_view_date);
            viewHolder.textViewRetardation = convertView.findViewById(R.id.text_view_retardation);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Event event = events.get(position);
        viewHolder.textViewRank.setText(event.getRankToString());
        viewHolder.textViewDescribtion.setText(event.getDescribtion());
        viewHolder.textViewDate.setText(event.getPlannedDateToString());
        viewHolder.textViewRetardation.setText(event.getRetardationToString());

        return convertView;
    }

    public void addEvent(Event event) {
        events.add(event);
        notifyDataSetChanged();
    }
}
