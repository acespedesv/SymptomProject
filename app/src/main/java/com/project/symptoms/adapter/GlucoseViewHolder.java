package com.project.symptoms.adapter;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GlucoseViewHolder extends RecyclerView.ViewHolder {
    public TextView textView;
    long modelId;

    public GlucoseViewHolder(@NonNull TextView textView) {
        super(textView);
        this.textView = textView;
    }
}
