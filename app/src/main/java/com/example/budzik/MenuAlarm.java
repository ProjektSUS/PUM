package com.example.budzik;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TimePicker;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MenuAlarm extends AppCompatActivity {

    Button button_addAl;
    TimePicker timePicker;
    int hour, minute;
    ArrayList<Item> arrayList;
    Intent intent;
    Uri mCurrentReminderUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        loadData();

        //Przycisk powrotu
        FloatingActionButton button_back_al = findViewById(R.id.button_back_al);
        button_back_al.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(MenuAlarm.this, MainActivity.class));
            }
        });

        //Ustawianie wartości zegara [TimePicker]
        timePicker = findViewById(R.id.timePicker);
        timePicker.setIs24HourView(true);
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int hour, int minute) {
                MenuAlarm.this.hour = hour;
                MenuAlarm.this.minute = minute;
            }
        });

        //Dodawanie alarmu
        button_addAl = findViewById(R.id.button_addAl);
        button_addAl.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Sprawdzenie, które dni tygodnia są zaznaczone
                CheckBox checkBox_pn = findViewById(R.id.checkBox_pn);
                CheckBox checkBox_wt = findViewById(R.id.checkBox_wt);
                CheckBox checkBox_sr = findViewById(R.id.checkBox_sr);
                CheckBox checkBox_czw = findViewById(R.id.checkBox_czw);
                CheckBox checkBox_pt = findViewById(R.id.checkBox_pt);
                CheckBox checkBox_sb = findViewById(R.id.checkBox_sb);
                CheckBox checkBox_nd = findViewById(R.id.checkBox_nd);

                intent = getIntent();
                mCurrentReminderUri = intent.getData();

                setAlarm();
                saveData(hour, minute);

            }
        });
    }


    //Ustawienie alarmu
    public void setAlarm(){
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        Calendar calendar = Calendar.getInstance();
        Calendar calendar_now = Calendar.getInstance();

        Date date = new Date();

        calendar_now.setTime(date);
        calendar.setTime(date);

        calendar.set(Calendar.HOUR_OF_DAY, timePicker.getHour());
        calendar.set(Calendar.MINUTE, timePicker.getMinute());
        calendar.set(Calendar.SECOND, 0);

        if(calendar.before(calendar_now)){
            calendar.add(Calendar.DATE,1);
        }

        Intent intent = new Intent(this, MyAlarmReceiver.class);
        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        intent.addFlags(Intent.FLAG_RECEIVER_FOREGROUND);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, (int)calendar.getTimeInMillis(), intent,0);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.S){
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        } else {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        }

        Toast.makeText(this, "Alarm ustawiony", Toast.LENGTH_SHORT).show();
    }

    //Wczytywanie danych
    public void loadData(){
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("AlarmTime_preferences", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("alarm_data", null);
        Type type = new TypeToken<ArrayList<Item>>(){}.getType();

        arrayList = gson.fromJson(json, type);

        if(arrayList == null){
            arrayList = new ArrayList<>();
        }

    }

    //Zapis danych
    public void saveData(int hour, int minute){
        String hour_s, minute_s;
        hour_s = String.valueOf(hour);
        minute_s = String.valueOf(minute);
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("AlarmTime_preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Gson gson = new Gson();
        arrayList.add(new Item(hour_s, minute_s));
        String json = gson.toJson(arrayList);
        editor.putString("alarm_data", json);
        editor.apply();
    }

    public void cancelTimer(){
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent myIntent = new Intent(getApplicationContext(),
                MyAlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                getApplicationContext(), 1, myIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        alarmManager.cancel(pendingIntent);

        Toast.makeText(this, "Alarm został usunięty", Toast.LENGTH_SHORT).show();
    }

}


//                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.S){
//                    vibrator.vibrate(VibrationEffect.createOneShot(1000, VibrationEffect.DEFAULT_AMPLITUDE));
//                } else{
//                    vibrator.vibrate(4000);
//               }