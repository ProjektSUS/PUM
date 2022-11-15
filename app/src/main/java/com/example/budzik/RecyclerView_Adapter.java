package com.example.budzik;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerView_Adapter extends RecyclerView.Adapter<RecyclerView_Holder> {

    Context context;
    List<Item> items;

    public RecyclerView_Adapter(Context context, List<Item> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public RecyclerView_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecyclerView_Holder(LayoutInflater.from(context).inflate(R.layout.alarm_view, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView_Holder holder, int position) {
        holder.time_alarm.setText(items.get(position).getTime());
        holder.active_days.setText(items.get(position).getActive());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
