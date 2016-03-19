package com.wordzoo.uhr;

//import android.util.Log;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.app.AlarmManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

import com.germanclock.time.Pieces;
import com.germanclock.time.Settings;
import com.germanclock.words.TimeInWords;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GermanClock extends AppWidgetProvider {

   
    RemoteViews views;

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
        //String out = getVerbalTime(p,s);
        //tv.setText(out);

        //set AlarmManager for next clock update
        AlarmManager am=(AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, ClockWakeup.class);
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent, 0);
        //After after 3 seconds
        am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (1000 * 60), 60000, pi);
    }

    public static String getVerbalTime(Context c) {
        Settings s = new Settings();
        s.setUmgangssprachlich(Boolean.TRUE);


        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("h:mm");
        Pieces p = new Pieces(sdf.format(d));

        TimeInWords v = new TimeInWords();
        return v.getVerbalTime(p, s);
    }

    public void onUpdate(Context context, AppWidgetManager appWidgetManager,int[] appWidgetIds) {

        ComponentName thisWidget = new ComponentName(context,
                GermanClock.class);

        for (int widgetId : appWidgetManager.getAppWidgetIds(thisWidget)) {

            //Get the remote views
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
                    R.layout.german_clock);

            // Set the text with the current time.
            remoteViews.setTextViewText(R.id.textView, getVerbalTime(context));
            appWidgetManager.updateAppWidget(widgetId, remoteViews);

        }
    }

}


