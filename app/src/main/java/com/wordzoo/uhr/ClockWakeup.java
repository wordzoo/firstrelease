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
        GermanClock.getInstance().setTime(context);
        GermanClock.getInstance().startClock(context);
    }

}
