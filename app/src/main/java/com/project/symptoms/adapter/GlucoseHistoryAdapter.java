package com.project.symptoms.adapter;

import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class GlucoseHistoryAdapter extends RecyclerView.Adapter<GlucoseViewHolder> {
    private ArrayList<String> dataSet = new ArrayList<String>(){{
        for (int i = 0; i < 100; i++) {
            add("item "+i);
        }
    }};

    public GlucoseHistoryAdapter() {
        super();
    }

    @NonNull
    @Override
    public GlucoseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TextView text = new TextView(parent.getContext());
        text.setTextSize(20);
        return new GlucoseViewHolder(text);
    }

    @Override
    public void onBindViewHolder(@NonNull GlucoseViewHolder holder, int position) {
        holder.textView.setText(dataSet.get(position));
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
