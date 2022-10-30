package com.example.budzik;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Settings extends AppCompatActivity {

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

        //Tryb nocny i dzienny





    }
}
