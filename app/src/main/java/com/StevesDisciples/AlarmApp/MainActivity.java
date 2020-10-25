package com.StevesDisciples.AlarmApp;

import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Date;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Date d = new Date();
    Button confirmAlarm ;
    TimePicker tp ;
    Button setDate ;
    EditText title ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm_create);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        confirmAlarm = findViewById(R.id.confirmAlarm);
        tp = findViewById(R.id.alarmTimePicker);
        setDate = findViewById(R.id.setDate);
        title = findViewById(R.id.alarmTitle);

//        setDate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//            }
//        });


        confirmAlarm.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {

                scheduleAlarm();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void scheduleAlarm() {
        int alarmId = new Random().nextInt(Integer.MAX_VALUE);

        Alarm alarm = new Alarm(
                alarmId,
                TimePickerUtil.getTimePickerHour(tp),
                TimePickerUtil.getTimePickerMinute(tp),
                title.getText().toString(),
                true
        );

        alarm.schedule(getApplicationContext());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}