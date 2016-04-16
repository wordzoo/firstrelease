package com.wordzoo.uhr;

//import android.util.Log;

import android.app.AlarmManager;
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
import android.os.Parcel;
import android.os.Parcelable;
import android.os.PowerManager;
import android.os.SystemClock;
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


    private static Settings settings = null;


    public Settings getSettings() {
        return settings;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
        Toast.makeText(context, "onDeleted(): TimeWidgetRemoved id(s):" + appWidgetIds, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);
        Toast.makeText(context, "onDisabled():last widget instance removed", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(context, ClockWakeup.class);
        PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(sender);

    }


    public void onRestored(Context context, int[] oldWidgetIds, int[] newWidgetIds) {
        super.onRestored(context, oldWidgetIds, newWidgetIds);
        setTime(context);
        Toast.makeText(context, "onRestored()", Toast.LENGTH_SHORT).show();

    }

 @Override
 public void onUpdate(Context context, AppWidgetManager awm, int[] ids) {
    super.onUpdate(context, awm, ids);
     Toast.makeText(context, "onUpdate(): update, call setTime() ", Toast.LENGTH_SHORT).show();
     setTime(context);
     setClock(context);


 }
    @Override
    public void onEnabled(final Context context) {

        super.onEnabled(context);
        ComponentName thisWidget = new ComponentName(context.getPackageName(), GermanClock.class.getName());
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.german_clock);

        Intent configIntent = new Intent(context, ActivitySettings.class);
        PendingIntent configPendingIntent = PendingIntent.getActivity(context, 0, configIntent, 0);
        remoteViews.setOnClickPendingIntent(R.id.textView, configPendingIntent);


        setClock(context);


        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        for (int widgetId : appWidgetManager.getAppWidgetIds(thisWidget))
            appWidgetManager.updateAppWidget(widgetId, remoteViews);

        setTime(context);

    }

    public void setClock (Context context) {
        //set AlarmManager for next clock update
        AlarmManager am=(AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, ClockWakeup.class);
        intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent, 0);

        Calendar c = new GregorianCalendar();
        Date date = new Date(SystemClock.elapsedRealtime());
        c.setTime(date);
        c.add(Calendar.MINUTE, 1);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        long next_minute = c.getTimeInMillis();
        long offset = 30000;
        Toast.makeText(context, "onEnabled(): next minute " + next_minute, Toast.LENGTH_SHORT).show();

        am.setExact(AlarmManager.ELAPSED_REALTIME_WAKEUP, next_minute, pi);

    }



    public static String getVerbalTime(Context c) {
        if(settings == null) {
            //official default style
            settings = new Settings();
            settings.setEsist(Boolean.TRUE);
            settings.setUhr(Boolean.TRUE);
            settings.setMinute(Boolean.TRUE);
        }

        TimeInWords tiw = new TimeInWords(c);

        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("H:mm");
        Pieces p = new Pieces(sdf.format(d));

        return tiw.getTimeAsSentance(p, settings);
    }



    public void setTime(Context context) {
        String time = getVerbalTime(context);
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.german_clock);
        remoteViews.setTextViewText(R.id.textView, time);
        Toast.makeText(context, "setTime() setting time to: " +time, Toast.LENGTH_SHORT).show();
        AppWidgetManager appManager = AppWidgetManager.getInstance(context);
        ComponentName thisWidget = new ComponentName(context.getPackageName(), GermanClock.class.getName());
        appManager.updateAppWidget(thisWidget, remoteViews);
    }
}



