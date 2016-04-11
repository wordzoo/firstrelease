package com.wordzoo.uhr;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RemoteViews;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Settings extends Activity implements OnClickListener {


    //user prefs
    private SharedPreferences clockPrefs;

    private RadioGroup def;
    private RadioButton defButton;
    private Button done;

    Context context;

    public void onCreate(Bundle savedInstanceState) {
        /*
        Boolean default = this.getResources().getBoolean(R.integer.officallong);
        if(officiallong)
            view.setChecked(default);

*/

        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        addDoneButtonListener();

        //user prefs
        clockPrefs = getSharedPreferences("CustomClockPrefs", 0);

        this.context = this;
    }


    public void addDoneButtonListener() {

        def = (RadioGroup) findViewById(R.id.def);
        done = (Button) findViewById(R.id.done);

        done.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                // get selected radio button from radioGroup
                int selectedId = def.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                defButton = (RadioButton) findViewById(selectedId);

                Toast.makeText(Settings.this,
                        defButton.getText(), Toast.LENGTH_SHORT).show();

                com.germanclock.time.Settings settings = new com.germanclock.time.Settings();
                settings.parse(defButton);
                updateSettings(settings);



                AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
                RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.german_clock);
                ComponentName thisWidget = new ComponentName(context, GermanClock.class);
                appWidgetManager.updateAppWidget(thisWidget, remoteViews);

                //GermanClock.getInstance().startClock(GermanClock.getInstance().context);
                finish();

            }
            


        });



    }

    public void updateSettings(com.germanclock.time.Settings settings) {
        com.germanclock.time.Settings s = new com.germanclock.time.Settings();

        // Is the button now checked?
        if(settings.getDef().equals(com.germanclock.time.Settings.Default.officiallong)) {

            s.setEsist(Boolean.TRUE);
            s.setUhr(Boolean.TRUE);
            s.setMinute(Boolean.TRUE);

        }
        else if(settings.getDef().equals(com.germanclock.time.Settings.Default.officialshort)) {

            s.setEsist(Boolean.TRUE);
            s.setUhr(Boolean.TRUE);
            s.setMinute(Boolean.TRUE);

        }
        else if(settings.getDef().equals(com.germanclock.time.Settings.Default.umgangssprachlich)) {

            s.setUmgangssprachlich(Boolean.TRUE);
            s.setUmgangminute(com.germanclock.time.Settings.Umgangminute.minuteword);
            s.setKurznach(Boolean.TRUE);
            s.setViertel(com.germanclock.time.Settings.Viertel.viertelacht);
            s.setZehnvorhalb(Boolean.TRUE);
            s.setFuenfvorhalb(Boolean.TRUE);
            s.setKurzvorhalb(Boolean.TRUE);
            s.setHalb(com.germanclock.time.Settings.Halb.halb);
            s.setKurznachhalb(Boolean.TRUE);
            s.setFuenfnachhalb(Boolean.TRUE);
            s.setZehnnachhalb(Boolean.TRUE);
            s.setFuenfvordreiviertelacht(Boolean.TRUE);
            s.setKurzvordreiviertelacht(Boolean.TRUE);
            s.setDreiviertel(com.germanclock.time.Settings.Dreiviertel.dreiviertelacht);
            s.setKurznachdreiviertelacht(Boolean.TRUE);
            s.setFuenfnachdreiviertelacht(Boolean.TRUE);
            s.setKurzvor(Boolean.TRUE);
            s.setAmabend(Boolean.TRUE);
            s.setAmmorgen(Boolean.TRUE);
            s.setInderfrueh(Boolean.TRUE);
            s.setAmnachmittag(Boolean.TRUE);
            s.setAmvormittag(Boolean.TRUE);

            s.setEsist(Boolean.TRUE);

        }
        else if(settings.getDef().equals(com.germanclock.time.Settings.Default.custom)) {
            //comming soon.....
        }
        GermanClock.getInstance().setSettings(s);
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