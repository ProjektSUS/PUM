package com.example.budzik;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.AbsListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity{

    RecyclerView recyclerView;
    float x1, y1, x2, y2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Przycisk aby przejść do ustawień
        FloatingActionButton button_settings = findViewById(R.id.button_settings);
        button_settings.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View v){
               startActivity(new Intent(MainActivity.this, Settings.class));
           }
        });

        //Przycisk aby przejść do dodawania budzików
        FloatingActionButton button_add = findViewById(R.id.button_add);
        button_add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, MenuAlarm.class));
            }
        });

        //Lista, która wyświetla aktualne budziki
        recyclerView = findViewById(R.id.recyclerView);

        List<Item> items = new ArrayList<>();
        items.add(new Item("00:00", ""));
        items.add(new Item("03:00", ""));
        items.add(new Item("04:00", ""));
        items.add(new Item("05:00", ""));
        items.add(new Item("06:00", ""));


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new RecyclerView_Adapter(getApplicationContext(),items));


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
                if(x1>x2){
                    Intent intent_swipe = new Intent(MainActivity.this, Timer.class);
                    startActivity(intent_swipe);
                }
                break;
        }

        return false;
    }

}