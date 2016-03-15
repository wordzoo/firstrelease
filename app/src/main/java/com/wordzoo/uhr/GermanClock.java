package com.wordzoo.uhr;

//import android.util.Log;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.germanclock.graphics.ViennaBlockUmg;
import com.germanclock.time.Pieces;
import com.germanclock.time.ViennaSettings;
import com.germanclock.words.LocalDialect;
import com.germanclock.words.ViennaDialect;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GermanClock extends AppWidgetProvider {

    RemoteViews views;

    public void onUpdate(Context context, AppWidgetManager appWidgetManager,int[] appWidgetIds) {

        ViennaSettings s = new ViennaSettings();
        s.setUmgangssprachlich(10);
        s.setRangeForViertel(5);
        s.setRangeForHalb(5);
        s.setRangeForDreiViertel(5);

        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("h:mm");
        Pieces p = new Pieces(sdf.format(d));

        LocalDialect v = new ViennaDialect();
        String out = v.getVerbalTime(p,s);


        for(int i=0; i<appWidgetIds.length; i++){
            int currentWidgetId = appWidgetIds[i];

            //TODO need to init the event handler for opening Settings activity on click
            Intent intent = new Intent(Intent.ACTION_PICK_ACTIVITY);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //intent.setClassName("com.wordzoo.uhr", Settings.class);
            PendingIntent pending = PendingIntent.getActivity(context, 0,intent, 0);

            RemoteViews views = new RemoteViews(context.getPackageName(),R.layout.german_clock);
            views.setString(R.id.textView, "text", out);
            views.setOnClickPendingIntent(R.id.textView, pending);
            appWidgetManager.updateAppWidget(currentWidgetId,views);
            Toast.makeText(context, "widget added", Toast.LENGTH_SHORT).show();
        }
    }
    public void onReceive(Context context, Intent intent) {
        //find out the action
        String action = intent.getAction();
        //is it time to update
        if (AppWidgetManager.ACTION_APPWIDGET_UPDATE.equals(action))
        {
            views = new RemoteViews(context.getPackageName(),
                    R.layout.german_clock);

            AppWidgetManager.getInstance(context).updateAppWidget
                    (intent.getIntArrayExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS), views);

            Intent choiceIntent = new Intent(context, GermanClock.class);

            PendingIntent clickPendIntent = PendingIntent.getActivity
                    (context, 0, choiceIntent, PendingIntent.FLAG_UPDATE_CURRENT);

            views.setOnClickPendingIntent(R.id.german_clock, clickPendIntent);

        }
    }
}


