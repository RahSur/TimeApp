package com.shwethavasudevan.timeapp;

import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Button btn1, btn2, btndiff, alertBtn;
    TextView time1, time2, timediff;

    // Objects for calendar and date format


    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

    int t1hours, t1mins, t1day, t1secs, t2hours, t2mins, t2day, t2secs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Time 1
        time1 = findViewById(R.id.time1txt);
        btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar start = Calendar.getInstance();
                time1.setText( start.getDisplayName(Calendar.DAY_OF_WEEK,Calendar.SHORT, Locale.getDefault())+ ", " + sdf.format(start.getTime()));
                t1hours  = start.get(Calendar.HOUR_OF_DAY);
                t1mins = start.get(Calendar.MINUTE);
                t1day = start.get(Calendar.DAY_OF_WEEK);
                t1secs = start.get(Calendar.SECOND);
            }
        });

        // Time 2
        time2 = findViewById(R.id.time2txt);
        btn2 = findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar stop = Calendar.getInstance();
                time2.setText( stop.getDisplayName(Calendar.DAY_OF_WEEK,Calendar.SHORT, Locale.getDefault()) + ", " + sdf.format(stop.getTime()));
                t2hours = stop.get(Calendar.HOUR_OF_DAY);
                t2mins = stop.get(Calendar.MINUTE);
                t2day = stop.get(Calendar.DAY_OF_WEEK);
                t2secs = stop.get(Calendar.SECOND);
            }
        });

        // Time difference
        timediff = findViewById(R.id.timedifftxt);
        btndiff = findViewById(R.id.btndiff);
        btndiff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int hours, minutes, seconds;

                // same day
                if(t2day-t1day==0) {
                    hours = t2hours - t1hours;
                    minutes = t2mins - t1mins;
                    seconds = t2secs - t1secs;
                    if (minutes < 0) {
                        minutes += 60;
                        hours -= 1;

                    }
                    if (seconds<0) {
                        seconds += 60;
                        minutes -= 1;
                    }
                    timediff.setText("Duration: " + hours + " hrs " + minutes + " mins " + seconds + " sec" );
                }
                    // different days
                    else {
                      // t2day-t1day not equals 0
                    int n = ((t2day-t1day) + 7)%7;
                    hours = ((t2hours - t1hours) + 24)%24;
                    minutes = t2mins - t1mins;
                    seconds = t2secs - t1secs;
                    if(minutes<0) {
                        minutes += 60;
                        hours -=1;
                    }
                    if(seconds<0) {
                        seconds += 60;
                        minutes -= 1;
                    }
                    hours += (n-1)*24;
                    timediff.setText("Duration: " + hours + " hrs " + minutes + " mins " + seconds + " sec" );
                    }
                }

        });

        alertBtn = findViewById(R.id.albtn);

        alertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder ab = new AlertDialog.Builder(view.getContext());
                ab.setMessage("Alert closes in... \n00:20");

                final AlertDialog alert = ab.create();
                alert.show();
                new CountDownTimer(20000,1000)
                {
                    @Override
                    public void onTick(long l)
                    {
                        if (l < 10)
                            alert.setMessage("Alert closes in... \n" + "00:"+l/10000);
                        alert.setMessage("Alert closes in... \n" + "00:"+l/1000);

                    }

                    @Override
                    public void onFinish() {
                        alert.dismiss();
                    }
                }.start();

            }
        });

    }
}
