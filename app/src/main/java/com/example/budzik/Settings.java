package com.example.budzik;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Settings extends AppCompatActivity {

    private int VibrationTimeTimer;
    private int VibrationTimeTimer_bad;
    private EditText number_VibrationTimeTimer;
    private EditText number_VibrationTimeTimer_bad;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //Przycisk powrotu do menu
        FloatingActionButton button_back_set = findViewById(R.id.button_back_set);
        button_back_set.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(Settings.this, MainActivity.class));
            }
        });

        //Tryb nocny i dzienny <- kiedyś zrobimy xD


        //Ustawienia długości wibracji
        number_VibrationTimeTimer = findViewById(R.id.editTextNumber_vibration);
        number_VibrationTimeTimer_bad = findViewById(R.id.editTextNumber_vibrationBad);

        number_VibrationTimeTimer.setFilters(new InputFilterNumber[]{ new InputFilterNumber("0", "5")});
        number_VibrationTimeTimer_bad.setFilters(new InputFilterNumber[]{ new InputFilterNumber("0", "5")});

        Button button_settingsSave = findViewById(R.id.button_settings_save);
        button_settingsSave.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                try{
                    VibrationTimeTimer = Integer.parseInt(number_VibrationTimeTimer.getText().toString());
                    VibrationTimeTimer_bad = Integer.parseInt(number_VibrationTimeTimer_bad.getText().toString());


                } catch(NumberFormatException ex){
                    VibrationTimeTimer = 1;
                    VibrationTimeTimer_bad = 2;

                    Toast.makeText(Settings.this, "Puste pola, przypisano ustawienia podstawowe", Toast.LENGTH_SHORT).show();
                }

                VibrationTimeTimer = VibrationTimeTimer * 1000;
                VibrationTimeTimer_bad = VibrationTimeTimer_bad * 1000;

                sharedPreferences = getSharedPreferences("Vibration_preferences", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putInt("VibrationTime", VibrationTimeTimer);
                editor.putInt("VibrationTime_Bad", VibrationTimeTimer_bad);
                editor.commit();

                Toast.makeText(Settings.this, "Zapisano zmiany", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public int getVibrationTimeTimer(){
        return VibrationTimeTimer;
    }

    public int getVibrationTimeTimer_bad(){
        return VibrationTimeTimer_bad;
    }
}
