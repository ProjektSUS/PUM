package com.example.budzik;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;
import java.util.Date;

public class MenuAlarm extends AppCompatActivity {

    Button button_addAl;
    TimePicker timePicker;
    int hour, minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        //Ustawianie wartości zegara [TimePicker]
        timePicker = findViewById(R.id.timePicker);
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int hour, int minute) {
                MenuAlarm.this.hour = hour;
                MenuAlarm.this.minute = minute;
            }
        });

        //Przycisk powrotu
        FloatingActionButton button_back_al = findViewById(R.id.button_back_al);
        button_back_al.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(MenuAlarm.this, MainActivity.class));
            }
        });

        //Dodawanie alarmu
        button_addAl = findViewById(R.id.button_addAl);
        button_addAl.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Sprawdzenie, które dni tygodnia są zaznaczone
//                CheckBox checkBox_pn = findViewById(R.id.checkBox_pn);
//                CheckBox checkBox_wt = findViewById(R.id.checkBox_wt);
//                CheckBox checkBox_sr = findViewById(R.id.checkBox_sr);
//                CheckBox checkBox_czw = findViewById(R.id.checkBox_czw);
//                CheckBox checkBox_pt = findViewById(R.id.checkBox_pt);
//                CheckBox checkBox_sb = findViewById(R.id.checkBox_sb);
//                CheckBox checkBox_nd = findViewById(R.id.checkBox_nd);
//
//                boolean isCheck_pn = checkBox_pn.isChecked();
//                boolean isCheck_wt = checkBox_wt.isChecked();
//                boolean isCheck_sr = checkBox_sr.isChecked();
//                boolean isCheck_czw = checkBox_czw.isChecked();
//                boolean isCheck_pt = checkBox_pt.isChecked();
//                boolean isCheck_sb = checkBox_sb.isChecked();
//                boolean isCheck_nd = checkBox_nd.isChecked();

                System.out.println("A");
                setTimer(v);


            }
        });

    }

    //Ustawienie alarmu
    public void setTimer(View v){
        AlarmManager alarmManager = (AlarmManager)getSystemService((Context.ALARM_SERVICE));
        Date date = new Date();
        Calendar cal_alarm = Calendar.getInstance();
        Calendar cal_now = Calendar.getInstance();
        cal_now.setTime(date);
        cal_alarm.setTime(date);

        cal_alarm.set(Calendar.HOUR_OF_DAY, hour);
        cal_alarm.set(Calendar.MINUTE, minute);
        cal_alarm.set(Calendar.SECOND,0);

        if(cal_alarm.before(cal_now)){
            cal_alarm.add(Calendar.DATE,1);
        }


        Intent intent = new Intent(MenuAlarm.this,MyBroadcastReceiver.class);
        PendingIntent pendingIntent;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.S){
            pendingIntent = PendingIntent.getActivity(MenuAlarm.this, 123, intent, PendingIntent.FLAG_IMMUTABLE);
        } else{
            pendingIntent = PendingIntent.getActivity(MenuAlarm.this, 123, intent,PendingIntent.FLAG_ONE_SHOT);
        }
        alarmManager.set(AlarmManager.RTC_WAKEUP,cal_alarm.getTimeInMillis(),pendingIntent);

    }

}

//Brak błędu przy przyciskaniu "dodaj alarm", ale nie działa xDDD
//Tryb ciemny byłby fajen
//Sprawdzić te FLAG na nowo, co to dokładnie robi i jak działa z getActivities
//Dni tygodnia, custom wiadomość i custom title dodać
//