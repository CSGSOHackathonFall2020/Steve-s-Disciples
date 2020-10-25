package com.StevesDisciples.AlarmApp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class DatePicker extends AppCompatActivity
{

    Button butt;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.date_picker);

        butt = findViewById(R.id.confirmDate);

        butt.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent myIntent = new Intent(DatePicker.this, MainActivity.class);
                startActivity(myIntent);
            }
        });
    }
}