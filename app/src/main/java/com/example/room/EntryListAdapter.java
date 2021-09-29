package com.example.room;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class EntryListAdapter extends RecyclerView.Adapter<EntryListAdapter.EntryViewHolder> {

    private final LayoutInflater layoutInflater;
    private Context context;
    private List<Entry> entries;

    public EntryListAdapter(Context context) {
        layoutInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public EntryListAdapter.EntryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.list_item, parent, false);
        EntryViewHolder viewHolder = new EntryViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull EntryListAdapter.EntryViewHolder holder, int position) {
        if (this.entries != null)
        {
            Entry entry = this.entries.get(position);
            holder.setContent(entry.getDestination(), entry.getCountry(), position);
        }
        else
        {
            holder.destination.setText(R.string.no_entries);
            holder.country.setText(R.string.no_entries);
        }
    }

    @Override
    public int getItemCount() {
        if (this.entries != null)
            return this.entries.size();
        else return 0;
    }

    public void setEntries(List<Entry> entries) {
        this.entries = entries;
        notifyDataSetChanged();
    }

    public class EntryViewHolder extends RecyclerView.ViewHolder {

        private TextView destination;
        private TextView country;
        private int position;

        public EntryViewHolder(@NonNull View itemView) {
            super(itemView);

            destination = itemView.findViewById(R.id.tvDestination);
            country = itemView.findViewById(R.id.tvCountry);
        }

        public void setContent(String destination, String country, int position) {
            this.destination.setText(destination);
            this.country.setText(country);
            this.position = position;
        }
    }
}
