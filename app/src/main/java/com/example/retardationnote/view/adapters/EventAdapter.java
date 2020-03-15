package com.example.retardationnote.view.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retardationnote.R;
import com.example.retardationnote.model.entities.Event;

import java.util.ArrayList;
import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventHolder> {

    private List<Event> events;
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    class EventHolder extends RecyclerView.ViewHolder{
        private Button buttonDelete;
        private TextView textViewRank;
        private TextView textViewDescribtion;
        private TextView textViewDate;
        private TextView textViewRetardation;

        public EventHolder(View itemView) {
            super(itemView);
            buttonDelete = itemView.findViewById(R.id.button_delete);
            textViewRank = itemView.findViewById(R.id.text_view_rank);
            textViewDescribtion = itemView.findViewById(R.id.text_view_description);
            textViewDate = itemView.findViewById(R.id.text_view_date);
            textViewRetardation = itemView.findViewById(R.id.text_view_retardation);

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

    public EventAdapter() {
        this.events = new ArrayList<>();
    }

    @NonNull
    @Override
    public EventHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_event, parent, false);
        return new EventHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull EventHolder holder, int position) {
        Event currentEvent = events.get(position);
        holder.buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
            }
        });
        holder.textViewRank.setText(currentEvent.getRank().toString());
        holder.textViewDescribtion.setText(currentEvent.getDescribtion());
        holder.textViewDate.setText(currentEvent.getPlannedDate().toString());
        holder.textViewRetardation.setText(currentEvent.getRetardationToString());
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public void setEvents(List<Event> events) {
        this.events = events;
        notifyDataSetChanged();
    }
}
