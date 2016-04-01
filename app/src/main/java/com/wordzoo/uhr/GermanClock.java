package com.wordzoo.uhr;

//import android.util.Log;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.app.AlarmManager;
import android.content.SharedPreferences;
import android.os.Bundle;
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

import static android.view.View.*;

public class GermanClock extends AppWidgetProvider {

   
    RemoteViews views;

    //preferences
    private SharedPreferences custClockPrefs;
    //number of possible designs
    private int numClocks = 3;
    //IDs of Analog Clock elements
    int[] clockDesigns;




    @Override
    public void onReceive(Context context, Intent intent) {

        clockDesigns = new int[numClocks];
        for(int d=0; d<numClocks; d++){
            clockDesigns[d]=context.getResources().getIdentifier
                    ("AnalogClock"+d, "id", context.getPackageName());
        }

        //get preferences
        custClockPrefs = context.getSharedPreferences("CustomClockPrefs", 0);
        int chosenDesign = custClockPrefs.getInt("clockdesign", -1);

        if(chosenDesign>=0){
            for(int d=0; d<numClocks; d++){
                if(d!=chosenDesign)
                    views.setViewVisibility(clockDesigns[d], INVISIBLE);
            }
            views.setViewVisibility(clockDesigns[chosenDesign], VISIBLE);
        }



    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager,int[] appWidgetIds) {

        ComponentName thisWidget = new ComponentName(context,
                GermanClock.class);

        //Get the remote views
        views = new RemoteViews(context.getPackageName(),
                R.layout.german_clock);

        // Set the text with the current time.
        views.setTextViewText(R.id.textView, getVerbalTime(context));

        for (int widgetId : appWidgetManager.getAppWidgetIds(thisWidget)) {
            appWidgetManager.updateAppWidget(widgetId, views);

        }



        Intent configIntent = new Intent(context, com.wordzoo.uhr.Settings.class);
        PendingIntent configPendingIntent = PendingIntent.getActivity(context, 0, configIntent, 0);

        views.setOnClickPendingIntent(R.id.german_clock, configPendingIntent);
        appWidgetManager.updateAppWidget(appWidgetIds, views);


    }

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


        //String out = getTimeAsSentance(p,s);
        //tv.setText(out);

        //set AlarmManager for next clock update
        AlarmManager am=(AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, ClockWakeup.class);
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent, 0);
        //After after 1 min, but start with an even minute
        //DateFormat.getDateTimeInstance().format(new Date(0))); 
        am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (1000 * 60), 60000, pi);
    }

    public static String getVerbalTime(Context c) {

        Settings s = new Settings();
        TimeInWords tiw = new TimeInWords(c);

        // Basic umgangsprashlich test
        s.setUmgangssprachlich(Boolean.TRUE);
        s.setMinuteHybrid(Boolean.TRUE);
        s.setUmgangminute(Settings.Umgangminute.minutebar);

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


}


