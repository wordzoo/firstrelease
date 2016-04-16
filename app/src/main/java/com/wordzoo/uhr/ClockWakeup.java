package com.wordzoo.uhr;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.widget.RemoteViews;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by ich on 16.03.2016.
 */


public class ClockWakeup extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        String time = GermanClock.getVerbalTime(context);
        Toast.makeText(context, "onReceive(): WakeUpClass setting time to: " + time, Toast.LENGTH_SHORT).show();

        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "GERMAN CLOCK");
        //Acquire the lock
        wl.acquire();

        //You can do the processing here update the widget/remote views.
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
                R.layout.german_clock);
        remoteViews.setTextViewText(R.id.textView, time);
        ComponentName thiswidget = new ComponentName(context, GermanClock.class);
        AppWidgetManager manager = AppWidgetManager.getInstance(context);
        manager.updateAppWidget(thiswidget, remoteViews);

        //Release the lock
        wl.release();

        //set AlarmManager for next clock update
        AlarmManager am=(AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent i = new Intent(context, ClockWakeup.class);
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, i, 0);

        Calendar c = new GregorianCalendar();
        c.setTime(new Date());
        c.add(Calendar.MINUTE, 1);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        long next_minute = c.getTimeInMillis();
        long offset = 30000;
        Toast.makeText(context, "onEnabled(): next minute " + next_minute, Toast.LENGTH_SHORT).show();

        am.setExact(AlarmManager.RTC_WAKEUP, next_minute, pi);


    }

}