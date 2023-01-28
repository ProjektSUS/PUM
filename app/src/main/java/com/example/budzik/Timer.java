package com.example.budzik;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Locale;

public class Timer extends AppCompatActivity {

    private static final long START_TIME_IN_MILIS = 10000;
    private TextView TextViewCountDown;
    private Button buttonStartPause;
    private Button buttonReset;
    private CountDownTimer CountDownTimer;
    private boolean TimerRunning;
    private long TimeLeft = START_TIME_IN_MILIS;
    float x1, y1, x2, y2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        //Minutnik
        TextViewCountDown = findViewById(R.id.mTextViewCotunDown);
        buttonStartPause = findViewById(R.id.button_start);
        buttonReset = findViewById(R.id.button_reset);

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

    private void startTimer(){
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
                buttonReset.setVisibility(View.INVISIBLE);
                buttonReset.setVisibility(View.VISIBLE);

                startActivity(new Intent(Timer.this, MathGame.class));
            }
        }.start();
        TimerRunning = true;
        buttonStartPause.setText("PAUSE");
        buttonReset.setVisibility(View.INVISIBLE);
    }

    private void pauseTimer(){
        CountDownTimer.cancel();
        TimerRunning = false;
        buttonStartPause.setText("Start");
        buttonReset.setVisibility(View.VISIBLE);
    }

    private void resetTimer(){
        TimeLeft = START_TIME_IN_MILIS;
        updateCountdowntext();
        buttonReset.setVisibility(View.INVISIBLE);
        buttonReset.setVisibility(View.VISIBLE);
    }

    private void updateCountdowntext(){
        int minutes = (int) TimeLeft / 1000 / 60;
        int seconds = (int) TimeLeft / 1000 % 60;

        String timeLeftText = String.format(Locale.getDefault(),"%02d:%02d", minutes, seconds);
        TextViewCountDown.setText(timeLeftText);
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
                }
                break;
        }
        return false;
    }

}
