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

    }

    public void onRestored(Context context, int[] oldWidgetIds, int[] newWidgetIds) {
        setTime(context);
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
        theFilter.addAction(Intent.ACTION_SCREEN_ON); //DREAM STOP, SLEEP STop
        theFilter.addAction(Intent.ACTION_TIME_TICK);
        theFilter.addAction(Intent.ACTION_DREAMING_STOPPED);

        context.getApplicationContext().registerReceiver(this, theFilter);

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        for (int widgetId : appWidgetManager.getAppWidgetIds(thisWidget))
            appWidgetManager.updateAppWidget(widgetId, remoteViews);

        setTime(context);


    }

    @Override
    public void onReceive(Context context, Intent intent)
    {
        String action = intent.getAction();
        if (action.equals(Intent.ACTION_SCREEN_ON)
                || action.equals(Intent.ACTION_TIME_TICK)
                || action.equals(Intent.ACTION_DREAMING_STOPPED))
        {
            setTime(context);
            //startClock(context);
        }
        else
        {
            super.onReceive(context, intent);
        }
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
        super.onUpdate(context, appWidgetManager, appWidgetIds);
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



