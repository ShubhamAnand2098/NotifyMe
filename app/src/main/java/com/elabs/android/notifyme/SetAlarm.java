package com.elabs.android.notifyme;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

public class SetAlarm {

    private Date mCurrebtDate;
    private Calendar cal_now, cal_alarm;
    DateTime dateTime;
    private AlarmManager mAlarmManager;
    private Context mContext;
    private PendingIntent pendingIntent;


    public SetAlarm(TextView dateView, TextView timeView, Context context, AlarmManager alarmManager)
    {
        //set current time in both calendar instances
        mContext = context;
        mCurrebtDate = new Date();
        cal_alarm = Calendar.getInstance();
        cal_now = Calendar.getInstance();
        cal_now.setTime(mCurrebtDate);
        cal_alarm.setTime(mCurrebtDate);

        dateTime = new DateTime(dateView, timeView,context);

        mAlarmManager = alarmManager;
    }



    public void setAlarmTime(Button button)
    {

        dateTime.showDate(cal_now.get(Calendar.YEAR), cal_now.get(Calendar.MONTH),
                cal_now.get(Calendar.DAY_OF_MONTH));

        dateTime.showTime(cal_now.get(Calendar.HOUR_OF_DAY), cal_now.get(Calendar.MINUTE));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cal_alarm.set(Calendar.HOUR_OF_DAY,dateTime.getmHours());
                cal_alarm.set(Calendar.MINUTE,dateTime.getmMinutes());
                cal_alarm.set(Calendar.SECOND,0);
                if(cal_alarm.before(cal_now)){
                    cal_alarm.add(Calendar.DATE,1);
                }

                Intent myIntent = new Intent(mContext, AlarmReceiver.class);

                pendingIntent = PendingIntent.getBroadcast(mContext,  0, myIntent, 0);

                mAlarmManager.set(AlarmManager.RTC_WAKEUP,cal_alarm.getTimeInMillis(), pendingIntent);
            }
        });






    }
}
