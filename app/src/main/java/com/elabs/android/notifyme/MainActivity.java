package com.elabs.android.notifyme;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    TextView dateView, timeView;

    Button button;
    DateTime dateTime;

    Calendar cal_alarm, cal_now;
    PendingIntent pendingIntent;

    AlarmManager alarmManager;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        dateView = (TextView)findViewById(R.id.date_view);
        timeView = (TextView)findViewById(R.id.time_view);
        button = (Button)findViewById(R.id.button);

        SetAlarm setAlarm = new SetAlarm(dateView, timeView, this, alarmManager);
        setAlarm.setAlarmTime(button);


    }







    @RequiresApi(api = Build.VERSION_CODES.N)
    void extras()
    {
        int year, month, date, hours, minutes;



        Date dat = new Date();

        cal_alarm = Calendar.getInstance();
        cal_now = Calendar.getInstance();
        cal_now.setTime(dat);
        cal_alarm.setTime(dat);

        year = cal_now.get(Calendar.YEAR);
        month = cal_now.get(Calendar.MONTH);
        date = cal_now.get(Calendar.DATE);
        hours = cal_now.get(Calendar.HOUR_OF_DAY);
        minutes = cal_now.get(Calendar.MINUTE);


        dateTime = new DateTime(dateView, timeView,this);
        dateTime.showDate(year, month, date);
        dateTime.showTime(hours, minutes);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cal_alarm.set(Calendar.HOUR_OF_DAY,dateTime.getmHours());
                cal_alarm.set(Calendar.MINUTE,dateTime.getmMinutes());
                cal_alarm.set(Calendar.SECOND,0);
                if(cal_alarm.before(cal_now)){
                    cal_alarm.add(Calendar.DATE,1);
                }

                Intent myIntent = new Intent(MainActivity.this, AlarmReceiver.class);

                pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, myIntent, 0);

                alarmManager.set(AlarmManager.RTC_WAKEUP,cal_alarm.getTimeInMillis(), pendingIntent);
            }
        });


    }

}
