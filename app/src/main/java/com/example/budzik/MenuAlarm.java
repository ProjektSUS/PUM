package com.example.budzik;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;

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

        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

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
//                CheckBox checkBox_pn = findViewById(R.id.checkBox_pn);
//                CheckBox checkBox_wt = findViewById(R.id.checkBox_wt);
//                CheckBox checkBox_sr = findViewById(R.id.checkBox_sr);
//                CheckBox checkBox_czw = findViewById(R.id.checkBox_czw);
//                CheckBox checkBox_pt = findViewById(R.id.checkBox_pt);
//                CheckBox checkBox_sb = findViewById(R.id.checkBox_sb);
//                CheckBox checkBox_nd = findViewById(R.id.checkBox_nd);

                System.out.println("A");
                System.out.println(hour);
                System.out.println(minute);

//                setTimer(v);
//                addToList(v);

//                if(!vibrator.hasVibrator()){
//                    return;
//                }
//
//                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.S){
//                    vibrator.vibrate(VibrationEffect.createOneShot(1000, VibrationEffect.DEFAULT_AMPLITUDE));
//                } else{
//                    vibrator.vibrate(4000);
//                }

            }
        });

    }

    //Ustawienie alarmu
    public void setTimer(View v){

//        Vibrator vibrator = (Vibrator) getApplicationContext().getSystemService(VIBRATOR_SERVICE);
//        vibrator.vibrate(2000);
//
//        Uri notification_Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
//        Ringtone ringtone = RingtoneManager.getRingtone(getApplicationContext(), notification_Uri);
//        ringtone.play();
    }

    public void addToList(View v) {

    }

}
