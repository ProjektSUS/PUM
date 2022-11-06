package com.example.budzik;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerView_Holder extends RecyclerView.ViewHolder {
    TextView time_alarm, active_days;

    public RecyclerView_Holder(@NonNull View itemView){
        super(itemView);
        time_alarm = itemView.findViewById(R.id.alarm_time);
        active_days = itemView.findViewById(R.id.active_days);
    }
}
