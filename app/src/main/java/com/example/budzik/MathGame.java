package com.example.budzik;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MathGame extends AppCompatActivity {

    private int number_one, number_two, answer, result;
    EditText answer_in;
    TextView textViewOperation;
    Button button_submit, button_drzemka;
    SharedPreferences sharedPreferences, sharedPreferences_puzzle;
    int VibrationTime;
    int VibrationTime_Bad;
    Vibrator vibrator;
    Ringtone ringtone;
    Uri notification_Uri;
    int notificationMaxVol;
    String chosen_puzzle;

    //Zmienne do generowania randomowego liczb
    private int min = -10;
    private int max = 50;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mathgame);

        Toast.makeText(MathGame.this, "Alarm został uruchomiony", Toast.LENGTH_SHORT).show();

        AudioManager audio_up = (AudioManager) getApplicationContext().getSystemService(AUDIO_SERVICE);
        notificationMaxVol = audio_up.getStreamMaxVolume(AudioManager.STREAM_NOTIFICATION);

        sharedPreferences = getSharedPreferences("Vibration_preferences", Context.MODE_PRIVATE);
        VibrationTime = sharedPreferences.getInt("VibrationTime", 1);
        VibrationTime_Bad = sharedPreferences.getInt("VibrationTime_Bad", 2);

        vibrator = (Vibrator) getApplicationContext().getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(VibrationTime);

        notification_Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
        ringtone = RingtoneManager.getRingtone(getApplicationContext(), notification_Uri);
        ringtone.play();

        sharedPreferences_puzzle = getSharedPreferences("PuzzlePreference", Context.MODE_PRIVATE);
        chosen_puzzle = sharedPreferences_puzzle.getString("chosen_puzzle", "Dodawanie");

        if(chosen_puzzle.equals("Odejmowanie")) {
            getAnswer_minus();
        }else if(chosen_puzzle.equals("Dodawanie")){
            getAnswer_add();
        }else if(chosen_puzzle.equals("Mnożenie")){
            getAnswer_multiple();
        }

        //Drzemka pułapka
        button_drzemka = findViewById(R.id.button_drzemka);
        button_drzemka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ringtone.stop();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        vibrator.vibrate(VibrationTime * 2);
                        audio_up.setStreamVolume(AudioManager.STREAM_NOTIFICATION, notificationMaxVol, 0);
                        ringtone.play();
                    }
                }, 10000);

            }
        });
    }

    //Tworzenie losowych liczb
    public void generateNumbers(){
        number_one = (int) Math.floor(Math.random()*(max-min+1));
        number_two = (int) Math.floor(Math.random()*(max-min+1));
    }

    //Sprawdzanie odpowiedzi i wyłączanie alarmu w odpowiednim momencie
    @SuppressLint("SetTextI18n")
    public void getAnswer_add(){
        textViewOperation = findViewById(R.id.textViewOperation);
        answer_in = findViewById(R.id.editTextNumber_Answer);

        generateNumbers();
        textViewOperation.setText(number_one + " + " + number_two + " =");

        //Sprawdzanie odpowiedzi
        button_submit = findViewById(R.id.button_submit);
        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try{
                    answer = Integer.parseInt(answer_in.getText().toString());
                    result = number_one + number_two;
                } catch(NumberFormatException ex){
                    answer = -7898;
                }

                if(result != answer){
                    generateNumbers();
                    textViewOperation.setText(number_one + " + " + number_two + " =");
                    Toast.makeText(MathGame.this, "Błędna odpowiedź", Toast.LENGTH_SHORT).show();
                    vibrator.vibrate(VibrationTime_Bad);
                }else{
                    startActivity(new Intent(MathGame.this, MainActivity.class));
                    Toast.makeText(MathGame.this, "Poprawna odpowiedź", Toast.LENGTH_SHORT).show();
                    ringtone.stop();
                }

            }
        });

    }

    @SuppressLint("SetTextI18n")
    public void getAnswer_minus(){
        textViewOperation = findViewById(R.id.textViewOperation);
        answer_in = findViewById(R.id.editTextNumber_Answer);

        generateNumbers();

        if(number_one >= number_two){
            textViewOperation.setText(number_one + " - " + number_two + " =");
            result = number_one - number_two;
        }
        else{
            textViewOperation.setText(number_two + " - " + number_one + " =");
            result = number_two - number_one;
        }

        //Sprawdzanie odpowiedzi
        button_submit = findViewById(R.id.button_submit);
        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(number_one >= number_two){
                    result = number_one - number_two;
                }
                else{
                    result = number_two - number_one;
                }

                try{
                    answer = Integer.parseInt(answer_in.getText().toString());

                } catch(NumberFormatException ex){
                    answer = 7898;
                }

                if(result != answer){
                    generateNumbers();
                    if(number_one >= number_two){
                        textViewOperation.setText(number_one + " - " + number_two + " =");
                    }
                    else{
                        textViewOperation.setText(number_two + " - " + number_one + " =");
                    }
                    Toast.makeText(MathGame.this, "Błędna odpowiedź", Toast.LENGTH_SHORT).show();
                    vibrator.vibrate(VibrationTime_Bad);
                }else{
                    startActivity(new Intent(MathGame.this, MainActivity.class));
                    Toast.makeText(MathGame.this, "Poprawna odpowiedź", Toast.LENGTH_SHORT).show();
                    ringtone.stop();
                }

            }
        });
    }

    @SuppressLint("SetTextI18n")
    public void getAnswer_multiple(){
        textViewOperation = findViewById(R.id.textViewOperation);
        answer_in = findViewById(R.id.editTextNumber_Answer);

        generateNumbers();
        textViewOperation.setText(number_one + " * " + number_two + " =");

        //Sprawdzanie odpowiedzi
        button_submit = findViewById(R.id.button_submit);
        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try{
                    answer = Integer.parseInt(answer_in.getText().toString());
                    result = number_one * number_two;
                } catch(NumberFormatException ex){
                    answer = -7898;
                }

                if(result != answer){
                    generateNumbers();
                    textViewOperation.setText(number_one + " * " + number_two + " =");
                    Toast.makeText(MathGame.this, "Błędna odpowiedź", Toast.LENGTH_SHORT).show();
                    vibrator.vibrate(VibrationTime_Bad);
                }else{
                    startActivity(new Intent(MathGame.this, MainActivity.class));
                    Toast.makeText(MathGame.this, "Poprawna odpowiedź", Toast.LENGTH_SHORT).show();
                    ringtone.stop();
                }

            }
        });
    }

}
