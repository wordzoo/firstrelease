package com.wordzoo.uhr;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import android.widget.ImageButton;
import android.widget.RadioButton;
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
        Boolean default = this.getResources().getBoolean(R.integer.officallong);
        if(officiallong)
            view.setChecked(default);

*/

        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        //user prefs
        clockPrefs = getSharedPreferences("CustomClockPrefs", 0);

    }

    public void onDefaultClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.officiallong:
                if (checked)
                    // Pirates are the best
                    break;
            case R.id.officialshort:
                if (checked)
                    // Ninjas rule
                    break;
            case R.id.umgangsprachlich:
                if (checked)
                    // Ninjas rule
                    break;
            case R.id.custom:
                if (checked)
                    // Ninjas rule
                    break;
        }
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