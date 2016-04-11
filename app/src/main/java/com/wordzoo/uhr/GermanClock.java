package com.wordzoo.uhr;

//import android.util.Log;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.germanclock.time.Pieces;
import com.germanclock.time.Settings;
import com.germanclock.words.TimeInWords;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class GermanClock extends AppWidgetProvider {

    private Settings settings = null;

    public Settings getSettings() {
        return settings;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }

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

        Intent configIntent = new Intent(context, ActivitySettings.class);
        PendingIntent configPendingIntent = PendingIntent.getActivity(context, 0, configIntent, 0);
        remoteViews.setOnClickPendingIntent(R.id.textView, configPendingIntent);

        final IntentFilter theFilter = new IntentFilter();
        theFilter.addAction(Intent.ACTION_SCREEN_ON);

        ClockWakeup mPowerKeyReceiver = new ClockWakeup();
        context.getApplicationContext().registerReceiver(mPowerKeyReceiver, theFilter);

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        for (int widgetId : appWidgetManager.getAppWidgetIds(thisWidget))
            appWidgetManager.updateAppWidget(widgetId, remoteViews);

        startClock(context);

    }


    public String getVerbalTime(Context c) {
        if(getSettings() == null) {
            //official default style
            settings = new Settings();
            settings.setEsist(Boolean.TRUE);
            settings.setUhr(Boolean.TRUE);
            settings.setMinute(Boolean.TRUE);
            setSettings(settings);
        }

        TimeInWords tiw = new TimeInWords(c);

        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("H:mm");
        Pieces p = new Pieces(sdf.format(d));

        return tiw.getTimeAsSentance(p, getSettings());
    }

    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        //startClock(context);

    }


    final Handler handler = new Handler(Looper.getMainLooper());

    public synchronized void startClock(final Context context) {
        Calendar c = new GregorianCalendar();
        c.setTime(new Date());
        c.add(Calendar.MINUTE, 1);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        long next_minute = c.getTimeInMillis();
        final long first_interval = Math.abs(next_minute - System.currentTimeMillis());

        Runnable outer = new Object() {
            Runnable task = new Runnable() {
                public void run() {
                    setTime(context);
                    Calendar c = new GregorianCalendar();
                    c.setTime(new Date());
                    c.add(Calendar.MINUTE, 1);
                    c.set(Calendar.SECOND, 0);
                    c.set(Calendar.MILLISECOND, 0);
                    long next_minute = c.getTimeInMillis();
                    long subsequent_interval = Math.abs(next_minute - System.currentTimeMillis());
                    handler.removeCallbacks(task);
                    handler.postDelayed(task, subsequent_interval);
                }
            };
        }.task;
        handler.removeCallbacks(outer);
        handler.postDelayed(outer, first_interval);
        setTime(context);
    }
    public void setTime(Context context) {
        String time = getVerbalTime(context);
        Toast.makeText(context, time, Toast.LENGTH_SHORT).show();

        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.german_clock);
        remoteViews.setTextViewText(R.id.textView, time);
        AppWidgetManager appManager = AppWidgetManager.getInstance(context);
        ComponentName thisWidget = new ComponentName(context.getPackageName(), GermanClock.class.getName());
        appManager.updateAppWidget(thisWidget, remoteViews);
    }
}



