package com.elabs.android.notifyme;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SearchRecentSuggestionsProvider;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

public class DateTime {

    private TextView dateView, timeView;
    private Context context;

    private int mYear, mMonth, mDate;
    private int mHours, mMinutes;

    String time, fullDate;

    public DateTime(TextView dateView, TextView timeView, Context context)
    {
        this.dateView = dateView;
        this.timeView = timeView;
        this.context = context;

    }

    public void showDate(int year, int month, int date)
    {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {


                String dayString = String.valueOf(dayOfMonth);
                String monthString = String.valueOf(month);

                if(month < 10)
                    monthString = "0"+monthString;
                if(dayOfMonth<10)
                    dayString = "0"+dayString;

                mYear = year;
                mMonth = Integer.valueOf(monthString);
                mDate = Integer.valueOf(dayString);
                fullDate = dayString+"/"+monthString+"/"+year;
                dateView.setText(dayString+"/"+monthString+"/"+year);

            }
        };

        final DatePickerDialog datePickerDialog = new DatePickerDialog(context, dateSetListener, year, month, date);

        dateView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });



    }

    public void showTime(int hours, int minutes)
    {

        TimePickerDialog.OnTimeSetListener timeChangedListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                String hourString = String.valueOf(hourOfDay);
                String minuteString = String.valueOf(minute);
                if (hourOfDay<10)
                    hourString = "0"+hourString;
                if (minute < 10)
                    minuteString = "0"+minuteString;

                time = hourString+":"+minuteString;
                mHours = Integer.valueOf(hourString);
                mMinutes = Integer.valueOf(minuteString);

                timeView.setText(hourString+":"+minuteString);

            }
        };
        final TimePickerDialog timePickerDialog = new TimePickerDialog(context, timeChangedListener,hours, minutes, false );
        timeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialog.show();
            }
        });



    }

    public int getmDate() {
        return mDate;
    }

    public int getmYear() {
        return mYear;
    }

    public int getmMonth() {
        return mMonth;
    }

    public int getmHours() {
        return mHours;
    }

    public int getmMinutes() {
        return mMinutes;
    }

}

