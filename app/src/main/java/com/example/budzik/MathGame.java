package com.example.budzik;

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
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MathGame extends AppCompatActivity {

    private int number_one, number_two, answer, result;
    EditText answer_in;
    TextView textViewOperation;
    Button button_submit, button_drzemka;
    SharedPreferences sharedPreferences;

    //Zmienne do generowania randomowego liczb
    private int min = -10;
    private int max = 50;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mathgame);
        getAnswer();

        Toast.makeText(MathGame.this, "Alarm został uruchomiony", Toast.LENGTH_SHORT).show();
    }

    //Tworzenie losowych liczb
    public void generateNumbers(){
        number_one = (int) Math.floor(Math.random()*(max-min+1));
        number_two = (int) Math.floor(Math.random()*(max-min+1));
        result = number_one + number_two;
    }

    //Sprawdzanie odpowiedzi i wyłączanie alarmu w odpowiednim momencie
    public void getAnswer(){
        textViewOperation = findViewById(R.id.textViewOperation);
        answer_in = findViewById(R.id.editTextNumber_Answer);
        AudioManager audio_up = (AudioManager) getApplicationContext().getSystemService(AUDIO_SERVICE);
        int notificationMaxVol = audio_up.getStreamMaxVolume(AudioManager.STREAM_NOTIFICATION);

        generateNumbers();
        textViewOperation.setText(number_one + " + " + number_two + " =");

        sharedPreferences = getSharedPreferences("Vibration_preferences", Context.MODE_PRIVATE);
        int VibrationTime = sharedPreferences.getInt("VibrationTime", 0);
        int VibrationTime_Bad = sharedPreferences.getInt("VibrationTime_Bad", 0);

        Vibrator vibrator = (Vibrator) getApplicationContext().getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(VibrationTime);

        Uri notification_Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
        Ringtone ringtone = RingtoneManager.getRingtone(getApplicationContext(), notification_Uri);
        ringtone.play();

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
                        ringtone.play();
                        audio_up.setStreamVolume(AudioManager.STREAM_NOTIFICATION, notificationMaxVol, 0);
                    }
                }, 10000);

            }
        });

        //Sprawdzanie odpowiedzi
        button_submit = findViewById(R.id.button_submit);
        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try{
                    answer = Integer.parseInt(answer_in.getText().toString());
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
}
