package com.wordzoo.uhr;

//import android.util.Log;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.app.AlarmManager;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
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

        super.onDisabled(context);
    }



    private static Runnable task;

    public void onAppWidgetOptionsChanged(Context context, AppWidgetManager appWidgetManager, int appWidgetId, Bundle newOptions) {
        //startClock(context);
    }

    public void onRestored(Context context, int[] oldWidgetIds, int[] newWidgetIds) {
        startClock(context);
    }

    @Override
    public void onEnabled(final Context context) {
        super.onEnabled(context);
        ComponentName thisWidget = new ComponentName(context.getPackageName(), GermanClock.class.getName());

        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.german_clock);

        Intent configIntent = new Intent(context, com.wordzoo.uhr.Settings.class);
        PendingIntent configPendingIntent = PendingIntent.getActivity(context, 0, configIntent, 0);
        remoteViews.setOnClickPendingIntent(R.id.textView, configPendingIntent);

        remoteViews.setTextViewText(R.id.textView, getVerbalTime(context));

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);

        final IntentFilter theFilter = new IntentFilter();
        /** System Defined Broadcast */
        theFilter.addAction(Intent.ACTION_SCREEN_ON);
        theFilter.addAction(Intent.ACTION_TIME_TICK);

        ClockWakeup mPowerKeyReceiver = new ClockWakeup();

        context.getApplicationContext().registerReceiver(mPowerKeyReceiver, theFilter);
        //context.registerReceiver(mPowerKeyReceiver, theFilter);

        for (int widgetId : appWidgetManager.getAppWidgetIds(thisWidget))
            appWidgetManager.updateAppWidget(widgetId, remoteViews);

        startClock(context);

    }

    public static String getVerbalTime(Context c) {

        Settings s = new Settings();
        TimeInWords tiw = new TimeInWords(c);

        //official default style
        s.setEsist(Boolean.TRUE);
        s.setUhr(Boolean.TRUE);
        s.setMinute(Boolean.TRUE);

        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("H:mm");
        Pieces p = new Pieces(sdf.format(d));

        return tiw.getTimeAsSentance(p, s);
    }

    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        //startClock(context);

    }




    public static synchronized void startClock(final Context context) {
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
}



