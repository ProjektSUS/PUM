package com.example.budzik;

import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MathGame extends AppCompatActivity {

    private int number_one, number_two, answer, result;
    EditText answer_in;
    TextView textViewOperation;
    Button button_submit;

    //Zmienne do generowania randomowego liczb
    private int min = -10;
    private int max = 50;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mathgame);
        getAnswer();

    }

    public void generateNumbers(){
        number_one = (int) Math.floor(Math.random()*(max-min+1));
        number_two = (int) Math.floor(Math.random()*(max-min+1));
        result = number_one + number_two;
    }

    public void getAnswer(){
        textViewOperation = findViewById(R.id.textViewOperation);
        answer_in = findViewById(R.id.editTextNumber_Answer);

        generateNumbers();
        textViewOperation.setText(number_one + " + " + number_two + " =");

        Vibrator vibrator = (Vibrator) getApplicationContext().getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(3000);

        Uri notification_Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
        Ringtone ringtone = RingtoneManager.getRingtone(getApplicationContext(), notification_Uri);
        ringtone.play();

        button_submit = findViewById(R.id.button_submit);
        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answer = Integer.valueOf(answer_in.getText().toString());

                if(result != answer){
                    generateNumbers();
                    textViewOperation.setText(number_one + " + " + number_two + " =");
                    answer = Integer.valueOf(answer_in.getText().toString());
                    System.out.println("Result" + result);
                    System.out.println("Answer" + answer);
                    vibrator.vibrate(1000);
                }else{
                    startActivity(new Intent(MathGame.this, Timer.class));
                    System.out.println("Result" + result);
                    System.out.println("Answer" + answer);
                    ringtone.stop();
                }

            }
        });

    }
}
