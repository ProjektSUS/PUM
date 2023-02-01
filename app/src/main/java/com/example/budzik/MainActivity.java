package com.example.budzik;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.*;
import android.widget.AbsListView;
import android.widget.Adapter;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity{

    RecyclerView recyclerView;
    float x1, y1, x2, y2;
    List<Item> arrayList = new ArrayList<>();
    Button delete_button;
    Uri mCurrentReminderUri;

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


        //W toku - może będzie działać -> 2 klasy z nazwą RecyclerView pomijamy w aktualnym momencie działania aplikacji
//        //Lista, która wyświetla aktualne budziki
//        loadData();
//        recyclerView = findViewById(R.id.recyclerView);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(new RecyclerView_Adapter(getApplicationContext(),arrayList));
//
//        RecyclerView.ItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
//        recyclerView.addItemDecoration(divider);
//
//        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
//            @Override
//            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
//                return false;
//            }
//
//            @Override
//            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
//                int position = viewHolder.getAdapterPosition();
//                arrayList.remove(position);
//
//
//            }
//        });
//        helper.attachToRecyclerView(recyclerView);
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


    // Jak nie ma RecyclerView to nam tego na razie nie potrzeba
//    public void loadData(){
//        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("AlarmTime_preferences", Context.MODE_PRIVATE);
//        Gson gson = new Gson();
//        String json = sharedPreferences.getString("alarm_data", null);
//        Type type = new TypeToken<ArrayList<Item>>(){}.getType();
//
//        arrayList = gson.fromJson(json, type);
//
//        if(arrayList == null){
//            arrayList = new ArrayList<>();
//        }
//
//    }
}