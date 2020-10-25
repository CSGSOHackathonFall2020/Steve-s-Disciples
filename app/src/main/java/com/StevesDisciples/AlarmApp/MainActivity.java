package com.StevesDisciples.AlarmApp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.StevesDisciples.AlarmApp.spotify.util.SpotifyUtil;
import com.StevesDisciples.SpotifyApi.remote.SpotifyRemote;
import com.spotify.sdk.android.authentication.AuthenticationClient;
import com.spotify.sdk.android.authentication.AuthenticationRequest;
import com.spotify.sdk.android.authentication.AuthenticationResponse;

import java.util.Date;
import java.util.Random;

public class MainActivity extends AppCompatActivity
{
    private static final String CLIENT_ID = "506d2499036447adbf170c0fb14e552f";
    private static final int REQUEST_CODE = 1337;
    private static final String REDIRECT_URI = "https://google.com";

    Date d = new Date();
    Button confirmAlarm;
    TimePicker tp;
    Button setDate;
    EditText title;
    Button maybeButton;
    MediaPlayer mediaPlayer;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm_create);

        confirmAlarm = findViewById(R.id.confirmAlarm);
        tp = findViewById(R.id.alarmTimePicker);
        setDate = findViewById(R.id.setDate);
        title = findViewById(R.id.alarmTitle);
        maybeButton = findViewById(R.id.maybeButton);

        scheduleAlarm();

        maybeButton.setOnClickListener(new View.OnClickListener()
        {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view)
            {
                SpotifyRemote remote = new SpotifyRemote();
                remote.connectAppRemote(MainActivity.this);
                Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
//                mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw);
//                mediaPlayer.start();

            }
        });

        setDate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent myIntent = new Intent(MainActivity.this, DatePicker.class);
                startActivity(myIntent);
            }
        });



    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void scheduleAlarm()
    {
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
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public static void authenticate(Activity activity)
    {
        AuthenticationRequest.Builder builder =
                new AuthenticationRequest.Builder(CLIENT_ID, AuthenticationResponse.Type.TOKEN, REDIRECT_URI);

        builder.setScopes(new String[]{"streaming"});
        AuthenticationRequest request = builder.build();

        AuthenticationClient.openLoginActivity(activity, REQUEST_CODE, request);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent)
    {
        super.onActivityResult(requestCode, resultCode, intent);

        // Check if result comes from the correct activity
        if (requestCode == REQUEST_CODE)
        {
            AuthenticationResponse response = AuthenticationClient.getResponse(resultCode, intent);

            switch (response.getType())
            {
                // Response was successful and contains auth token
                case TOKEN:
                    Log.d("MainActivity", "A token was received");
                    SpotifyUtil spotifyUtil = new SpotifyUtil(response.getAccessToken());
                    break;

                // Auth flow returned an error
                case ERROR:
                    Log.e("MainActivity", "An error occured");
                    break;

                // Most likely auth flow was cancelled
                default:
                    // Handle other cases
                    Log.i("MainActivity", "Other case");
            }
        }
    }
}