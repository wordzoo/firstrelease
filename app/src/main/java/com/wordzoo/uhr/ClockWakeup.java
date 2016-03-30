package com.wordzoo.uhr;

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
        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "GERMAN CLOCK");
        //Acquire the lock
        wl.acquire();

        //You can do the processing here update the widget/remote views.
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
                R.layout.german_clock);
        remoteViews.setTextViewText(R.id.textView, GermanClock.getVerbalTime(context));
        ComponentName thiswidget = new ComponentName(context, GermanClock.class);
        AppWidgetManager manager = AppWidgetManager.getInstance(context);
        manager.updateAppWidget(thiswidget, remoteViews);

        //Release the lock
        wl.release();
    }

}
