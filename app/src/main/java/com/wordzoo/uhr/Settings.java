package com.wordzoo.uhr;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import android.widget.ImageButton;
import android.widget.RemoteViews;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Settings extends Activity implements OnClickListener {


    //user prefs
    private SharedPreferences clockPrefs;


    public void onCreate(Bundle savedInstanceState) {
        /*
        example retrieve resource values, numDesigns = this.getResources().getInteger(R.integer.num_clocks);
        e.g. in a numbers.xml file
        <resources>
    <integer name="num_clocks">3</integer>
</resources>
*/

        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        //user prefs
        clockPrefs = getSharedPreferences("CustomClockPrefs", 0);

    }

    private void updateClock(com.germanclock.time.Settings settings) {


        RemoteViews remoteViews = new RemoteViews
                (this.getApplicationContext().getPackageName(),
                        R.layout.german_clock);


        //get component name for widget class
        ComponentName comp = new ComponentName(this, GermanClock.class);

        //germanClock.setSettings(settings);
        //germanClock.startClock(settings);

        //get AppWidgetManager
        AppWidgetManager appWidgetManager =
                AppWidgetManager.getInstance(this.getApplicationContext());

        //update
        appWidgetManager.updateAppWidget(comp, remoteViews);

    }
    public void onClick(View v) {

        //save prefs
        SharedPreferences.Editor custClockEdit = clockPrefs.edit();
        custClockEdit.commit();

        finish();
    }
}