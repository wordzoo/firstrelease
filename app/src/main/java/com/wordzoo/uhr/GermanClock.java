package com.wordzoo.uhr;

//import android.util.Log;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.app.AlarmManager;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

import com.germanclock.time.Pieces;
import com.germanclock.time.Settings;
import com.germanclock.words.TimeInWords;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;

public class GermanClock extends AppWidgetProvider {



    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        Toast.makeText(context, "onDeleted(): TimeWidgetRemoved id(s):"+appWidgetIds, Toast.LENGTH_SHORT).show();
        super.onDeleted(context, appWidgetIds);
    }

    @Override
    public void onDisabled(Context context) {
        Toast.makeText(context, "onDisabled():last widget instance removed", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(context, ClockWakeup.class);
        PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(sender);
        super.onDisabled(context);
    }



    private static Runnable task;

    @Override
    public void onEnabled(final Context context) {
        super.onEnabled(context);
        //start clock
        Calendar c = new GregorianCalendar();
        c.setTime(new Date());
        c.add(Calendar.MINUTE, 1);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        long next_minute = c.getTimeInMillis();
        final long first_interval = Math.abs(next_minute - System.currentTimeMillis());
        final Handler handler = new Handler(Looper.getMainLooper());
        task = new Runnable() {
            public void run() {
                ComponentName thisWidget = new ComponentName(context.getPackageName(), GermanClock.class.getName());
                String time = getVerbalTime(context);
                Toast.makeText(context, time, Toast.LENGTH_SHORT).show();

                RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.german_clock);
                remoteViews.setTextViewText(R.id.textView, time);
                AppWidgetManager appManager = AppWidgetManager.getInstance(context);
                appManager.updateAppWidget(thisWidget, remoteViews);

                Calendar c = new GregorianCalendar();
                c.setTime(new Date());
                c.add(Calendar.MINUTE, 1);
                c.set(Calendar.SECOND, 0);
                c.set(Calendar.MILLISECOND, 0);
                long next_minute = c.getTimeInMillis();
                long subsequent_interval = Math.abs(next_minute - System.currentTimeMillis());
                handler.postDelayed(task, subsequent_interval);
            }
        };
        handler.postDelayed(task, first_interval);
    }

    public static String getVerbalTime(Context c) {

        Settings s = new Settings();
        TimeInWords tiw = new TimeInWords(c);


        s.setUmgangssprachlich(Boolean.TRUE);
        s.setUmgangminute(Settings.Umgangminute.minutebar);

        s.setEsist(Boolean.TRUE);
        s.setUhr(Boolean.TRUE);
        s.setMinute(Boolean.TRUE);


        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("H:mm");
        Pieces p = new Pieces(sdf.format(d));

        return tiw.getTimeAsSentance(p, s);
    }

    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        ComponentName thisWidget = new ComponentName(context.getPackageName(), GermanClock.class.getName());

        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.german_clock);
        Intent configIntent = new Intent(context, com.wordzoo.uhr.Intro.class);

        PendingIntent configPendingIntent = PendingIntent.getActivity(context, 0, configIntent, 0);

        remoteViews.setOnClickPendingIntent(R.id.textView, configPendingIntent);

        remoteViews.setTextViewText(R.id.textView, getVerbalTime(context));


        for (int widgetId : appWidgetManager.getAppWidgetIds(thisWidget))
            appWidgetManager.updateAppWidget(widgetId, remoteViews);


    }

}



