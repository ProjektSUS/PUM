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
        holder.alarm_timeHour.setText(items.get(position).getHour());
        holder.alarm_timeMinute.setText(items.get(position).getMinute());
        holder.alarm_timeSpace.setText(":");
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


}
