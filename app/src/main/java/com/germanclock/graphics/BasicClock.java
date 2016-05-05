package com.germanclock.graphics;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.RemoteViews;
import android.widget.TextView;

import com.germanclock.time.Pieces;
import com.germanclock.time.Settings;
import com.germanclock.words.TimeInWords;
import com.wordzoo.uhr.R;
import com.wordzoo.uhr.utils.Constants;
import com.wordzoo.uhr.utils.StoreRetrieveGerman;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ich on 05.05.2016.
 */
public class BasicClock {
    public void drawTime(Boolean widgetUpdate, Settings settings,
                         String time, int clockViewId, Context context,
                         TextView testclock) {

        String chosenConfig = "";

        if(settings == null) {
            SharedPreferences sp = context.getSharedPreferences(Constants.SETTING, 0 | Context.MODE_MULTI_PROCESS);
            chosenConfig = sp.getString(Constants.selectedClock + "~" + Constants.selectedConfig, null);
            if(chosenConfig == null)
                chosenConfig = Constants.OFFICIAL_TIME; //default

            settings =  new StoreRetrieveGerman().loadSettingsFromDisk(sp, Constants.selectedClock, chosenConfig, context);
        }

//Toast.makeText(context,
//				"chosen: " + chosenConfig, Toast.LENGTH_SHORT).show();



        TimeInWords tiw = new TimeInWords(context);
        Pieces p =  null;

        if(time == null) {
            Date d = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("H:mm");
            p = new Pieces(sdf.format(d));
        }
        else
            p = new Pieces(time);

        String timeWords = tiw.getTimeAsSentance(p, settings);


        int drawableid = 0;

        if(settings.getUmgangssprachlich()
                && settings.getUmgangminute().equals(Settings.Umgangminute.minutebar)
                ) {
            switch (p.getRemainderMinutes()){
                case 0:
                    drawableid = R.drawable.lederhosen;
                    break;
                case 1:
                    drawableid = R.drawable.lederhosen1;
                    break;
                case 2:
                    drawableid = R.drawable.lederhosen2;
                    break;
                case 3:
                    drawableid = R.drawable.lederhosen3;
                    break;
                case 4:
                    drawableid = R.drawable.lederhosen4;
                    break;
            }
        }

        //to update the widget
        if(widgetUpdate) {
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.german_clock);
            remoteViews.setTextViewText(R.id.textView, timeWords);
            if(drawableid > 0)
                remoteViews.setTextViewCompoundDrawables(R.id.textView, 0, 0, drawableid, 0);
            else
                remoteViews.setTextViewCompoundDrawables(R.id.textView, 0, 0, 0, 0);

        } else {
            testclock.setText(timeWords);
        }

    }
}
