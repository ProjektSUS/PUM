package com.example.budzik;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MenuAlarm extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        //Przycisk powrotu
        FloatingActionButton button_back_al = findViewById(R.id.button_back_al);
        button_back_al.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(MenuAlarm.this, MainActivity.class));
            }
        });

        //Przycisk dodania alarmu
        Button button_addAl = findViewById(R.id.button_addAl);
        button_addAl.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                CheckBox checkBox_pon = findViewById(R.id.checkBox_pon);
                CheckBox checkBox_wt = findViewById(R.id.checkBox_wt);
                CheckBox checkBox_sr = findViewById(R.id.checkBox_sr);
                CheckBox checkBox_czw = findViewById(R.id.checkBox_czw);
                CheckBox checkBox_pt = findViewById(R.id.checkBox_pt);
                CheckBox checkBox_sb = findViewById(R.id.checkBox_sb);
                CheckBox checkBox_nd = findViewById(R.id.checkBox_nd);

                boolean isCheck_pn = checkBox_pon.isChecked();
                boolean isCheck_wt = checkBox_wt.isChecked();
                boolean isCheck_sr = checkBox_sr.isChecked();
                boolean isCheck_czw = checkBox_czw.isChecked();
                boolean isCheck_pt = checkBox_pt.isChecked();
                boolean isCheck_sb = checkBox_sb.isChecked();
                boolean isCheck_nd = checkBox_nd.isChecked();



            }
        });

    }
}
