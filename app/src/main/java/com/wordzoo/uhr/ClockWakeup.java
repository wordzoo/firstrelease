package com.wordzoo.uhr;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.widget.RemoteViews;

/**
 * Created by ich on 16.03.2016.
 */


public class ClockWakeup extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.german_clock);
        GermanClock gc = new GermanClock();

        remoteViews.setTextViewText(R.id.textView, gc.getVerbalTime(context));
        ComponentName thisWidget = new ComponentName(context, GermanClock.class);
        appWidgetManager.updateAppWidget(thisWidget, remoteViews);

        
    }

}
