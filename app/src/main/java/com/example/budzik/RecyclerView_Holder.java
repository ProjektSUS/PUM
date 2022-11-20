package com.example.budzik;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerView_Holder extends RecyclerView.ViewHolder {
    TextView alarm_timeHour, alarm_timeMinute, alarm_timeSpace;
    Button delete_button;
    Uri mCurrentReminderUri;

    public RecyclerView_Holder(@NonNull View itemView){
        super(itemView);
        alarm_timeHour = itemView.findViewById(R.id.alarm_timeHour);
        alarm_timeMinute = itemView.findViewById(R.id.alarm_timeMinute);
        alarm_timeSpace = itemView.findViewById(R.id.alarm_timeSpace);
        //active_days = itemView.findViewById(R.id.active_days);
    }

    public void onBindViewHolder(RecyclerView_Holder holder) {
        delete_button = itemView.findViewById(R.id.button_delete);

        holder.delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

}

