package com.example.budzik;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Settings extends AppCompatActivity {

    private int VibrationTimeTimer;
    private int VibrationTimeTimer_bad;
    private String selected_puzzle;
    private EditText number_VibrationTimeTimer;
    private EditText number_VibrationTimeTimer_bad;
    SharedPreferences sharedPreferences, sharedPreferences_puzzle;
    String[] puzzle = {"Dodawanie", "Odejmowanie", "Mnożenie"};
    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapterItems;

    @SuppressLint("SetTextI18n")
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

        //Odczytywanie potrzebnych danych z shared preferences dla typu zagadki
        sharedPreferences_puzzle = getSharedPreferences("PuzzlePreference", Context.MODE_PRIVATE);
        selected_puzzle = sharedPreferences_puzzle.getString("chosen_puzzle", "");

        //Wybór zagadki z listy rozwijanej:
        autoCompleteTextView = findViewById(R.id.auto_complete_txt);
        adapterItems = new ArrayAdapter<String>(this, R.layout.list_item, puzzle);

        autoCompleteTextView.setText(selected_puzzle);
        autoCompleteTextView.setAdapter(adapterItems);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                selected_puzzle = adapterView.getItemAtPosition(position).toString();
            }
        });

        //Ustawienia długości wibracji
        number_VibrationTimeTimer = findViewById(R.id.editTextNumber_vibration);
        number_VibrationTimeTimer_bad = findViewById(R.id.editTextNumber_vibrationBad);

        //Filter do wpisywania długości wibracji -> aktualnie wartości między 0, a 5
        number_VibrationTimeTimer.setFilters(new InputFilterNumber[]{ new InputFilterNumber("0", "5")});
        number_VibrationTimeTimer_bad.setFilters(new InputFilterNumber[]{ new InputFilterNumber("0", "5")});

        //Odczytywanie potrzebnych danych z shared preferences dla wibracji
        sharedPreferences = getSharedPreferences("Vibration_preferences", Context.MODE_PRIVATE);
        number_VibrationTimeTimer.setText(Integer.toString((sharedPreferences.getInt("VibrationTime", 0))/1000));
        number_VibrationTimeTimer_bad.setText(Integer.toString((sharedPreferences.getInt("VibrationTime_Bad", 0))/1000));;

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

                    Toast.makeText(Settings.this, "Puste pola czasu wibracji, przypisano ustawienia podstawowe", Toast.LENGTH_SHORT).show();
                }

                if(selected_puzzle.isEmpty()){
                    selected_puzzle = "Dodawanie";
                    Toast.makeText(Settings.this, "Puste pole wyboru zagadki, wybrano 'Dodawanie'", Toast.LENGTH_SHORT).show();
                }

                VibrationTimeTimer = VibrationTimeTimer * 1000;
                VibrationTimeTimer_bad = VibrationTimeTimer_bad * 1000;

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("VibrationTime", VibrationTimeTimer);
                editor.putInt("VibrationTime_Bad", VibrationTimeTimer_bad);
                editor.apply();

                SharedPreferences.Editor editor2 = sharedPreferences_puzzle.edit();
                editor2.putString("chosen_puzzle", selected_puzzle);
                editor2.apply();

                Toast.makeText(Settings.this, "Zapisano zmiany", Toast.LENGTH_SHORT).show();
                System.out.println(selected_puzzle);
                startActivity(new Intent(Settings.this, MainActivity.class));
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
