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


    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);

        //set clock
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View germanClock = inflater.inflate(R.layout.german_clock, null);
        TextView tv = (TextView)germanClock.findViewById(R.id.textView);

        //set AlarmManager for next clock update
        AlarmManager am=(AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, ClockWakeup.class);
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent, 0);
        am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (1000 * 60), 60000, pi);
        //After after 3 seconds
        /*Calendar date = new GregorianCalendar();
        date.setTime(new Date());
        //long now = date.getTimeInMillis();
        date.add(Calendar.MINUTE, 1);
        date.set(Calendar.SECOND, 0);
        date.set(Calendar.MILLISECOND, 0);
        long next_minute = date.getTimeInMillis();
        am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (1000 * 60), 60000, pi);
        //am.setRepeating(AlarmManager.RTC_WAKEUP, next_minute, 60000, pi);
*/
    }

    public static String getVerbalTime(Context c) {

        Settings s = new Settings();
        TimeInWords tiw = new TimeInWords(c);

        // Basic umgangsprashlich test
        s.setUmgangssprachlich(Boolean.TRUE);
        s.setMinuteHybrid(Boolean.TRUE);
        s.setUmgangminute(Settings.Umgangminute.minuteword);

        s.setEsist(Boolean.TRUE);
        s.setUhr(Boolean.TRUE);
        s.setMinute(Boolean.TRUE);

        s.setMitternacht(Boolean.TRUE);
        s.setKurzvor(Boolean.TRUE);
        s.setKurznach(Boolean.TRUE);

        s.setMorgens(Boolean.TRUE);
        s.setVormittags(Boolean.TRUE);
        s.setNachmittags(Boolean.TRUE);
        s.setAbends(Boolean.TRUE);
        s.setIndernacht(Boolean.TRUE);

        s.setViertel(Settings.Viertel.viertelacht);
        s.setFuenfvorviertelacht(Boolean.TRUE);
        s.setFuenfnachviertelacht(Boolean.TRUE);

        s.setHalb(Boolean.TRUE);
        s.setFuenfvorhalb(Boolean.TRUE);
        s.setFuenfnachhalb(Boolean.TRUE);
        s.setKurzvorhalb(Boolean.TRUE);
        s.setKurznachhalb(Boolean.TRUE);

        s.setDreiviertel(Settings.Dreiviertel.dreiviertelacht);
        s.setFuenfvordreiviertelacht(Boolean.TRUE);
        s.setFuenfnachdreiviertelacht(Boolean.TRUE);


        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("H:mm");
        Pieces p = new Pieces(sdf.format(d));

        return tiw.getTimeAsSentance(p, s);
    }

    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        ComponentName thisWidget = new ComponentName(context, GermanClock.class);

        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.german_clock);
        Intent configIntent = new Intent(context, com.wordzoo.uhr.Intro.class);

        PendingIntent configPendingIntent = PendingIntent.getActivity(context, 0, configIntent, 0);

        remoteViews.setOnClickPendingIntent(R.id.textView, configPendingIntent);

        remoteViews.setTextViewText(R.id.textView, getVerbalTime(context));

        for (int widgetId : appWidgetManager.getAppWidgetIds(thisWidget))
            appWidgetManager.updateAppWidget(widgetId, remoteViews);


    }

}


