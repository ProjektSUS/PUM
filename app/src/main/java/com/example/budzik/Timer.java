package com.example.budzik;

import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class Timer extends AppCompatActivity {

    private long START_TIME_IN_MILIS;
    private TextView TextViewCountDown;
    private Button buttonStartPause;
    private Button buttonReset;
    private Button buttonSelect;
    private CountDownTimer CountDownTimer;
    private boolean TimerRunning;
    private long TimeLeft;
    float x1, y1, x2, y2;
    int minute, second;
    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        //Minutnik
        TextViewCountDown = findViewById(R.id.mTextViewCotunDown);
        buttonStartPause = findViewById(R.id.button_start);
        buttonReset = findViewById(R.id.button_reset);
        buttonSelect = findViewById(R.id.button_selectTime);

        buttonStartPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(TimerRunning){
                    pauseTimer();
                } else {
                    startTimer();
                }
            }
        });

        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetTimer();
            }
        });

        updateCountdowntext();
    }

    @SuppressLint("SetTextI18n")
    private void startTimer(){
        if(counter == 0){
            START_TIME_IN_MILIS = minute * 60000l + second * 600l;
            TimeLeft = START_TIME_IN_MILIS;
            counter = counter + 1;
        }
        buttonSelect.setVisibility(View.INVISIBLE);
        CountDownTimer = new CountDownTimer(TimeLeft, 1000) {
            @Override
            public void onTick(long leftTime) {
                TimeLeft = leftTime;
                updateCountdowntext();
            }

            @Override
            public void onFinish() {
                TimerRunning = false;
                buttonStartPause.setText("Start");
                buttonReset.setVisibility(View.VISIBLE);

                startActivity(new Intent(Timer.this, MathGame.class));
            }
        }.start();
        TimerRunning = true;
        buttonStartPause.setText("PAUSE");
        buttonReset.setVisibility(View.INVISIBLE);
    }

    @SuppressLint("SetTextI18n")
    private void pauseTimer(){
        CountDownTimer.cancel();
        TimerRunning = false;

        updateCountdowntext();

        buttonStartPause.setText("Start");
        buttonReset.setVisibility(View.VISIBLE);

    }

    private void resetTimer(){
        TimeLeft = START_TIME_IN_MILIS;
        updateCountdowntext();
        buttonSelect.setVisibility(View.VISIBLE);
        buttonReset.setVisibility(View.VISIBLE);
        counter = 0;
    }

    private void updateCountdowntext(){
        int minutes = (int) TimeLeft / 1000 / 60;
        int seconds = (int) TimeLeft / 1000 % 60;

        String timeLeftText = String.format(Locale.getDefault(),"%02d:%02d", minutes, seconds);
        TextViewCountDown.setText(timeLeftText);
        buttonSelect.setText(timeLeftText);
    }

    public void popTimePicker(View view){
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                minute = i;
                second = i1;
                buttonSelect.setText(String.format(Locale.getDefault(), "%02d:%02d", minute, second));
            }
        };

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, onTimeSetListener, minute, second, true);
        timePickerDialog.setTitle("05:00");
        timePickerDialog.show();
    }

    public boolean onTouchEvent(MotionEvent touchEvent){
        switch(touchEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                x1 = touchEvent.getX();
                y1 = touchEvent.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2 = touchEvent.getX();
                y2 = touchEvent.getY();
                if(x1<x2){
                    Intent intent_swipe = new Intent(Timer.this, MainActivity.class);
                    startActivity(intent_swipe);
                } else{
                    Intent intent_swipe = new Intent(Timer.this, Stoper.class);
                    startActivity(intent_swipe);
                }

                break;


        }
        return false;
    }

}
