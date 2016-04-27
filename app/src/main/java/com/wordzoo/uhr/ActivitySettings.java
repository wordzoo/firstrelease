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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RemoteViews;
import android.widget.Toast;
import com.germanclock.time.Settings;
import com.wordzoo.uhr.utils.Constants;
import java.util.Set;

public class ActivitySettings extends Activity implements OnClickListener {



    private RadioGroup def;
    private RadioButton defButton;
    private Button done;
    private Button newConfig;
    private Button editClock;



    Context context;

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        SharedPreferences sharedPreferences = getSharedPreferences("germanClocks",0);
        addButtonListeners();



        this.context = this;


    }

    public void addButtonListeners() {

        def = (RadioGroup) findViewById(R.id.def);
        done = (Button) findViewById(R.id.done);
        newConfig = (Button) findViewById(R.id.newClock);
        //editClock = (Button) findViewById(R.id.editClock);

        done.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                // get selected radio button from radioGroup
                int selectedId = def.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                defButton = (RadioButton) findViewById(selectedId);

                Toast.makeText(ActivitySettings.this,
                        defButton.getText(), Toast.LENGTH_SHORT).show();

                Settings settings = new Settings();
                Settings s = passSettingsDownToClock(defButton.getText() + "");


                AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
                RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.german_clock);
                GermanClock gc = new GermanClock();
                gc.setSettings(s);
                remoteViews.setTextViewText(R.id.textView, gc.getVerbalTime(context));

                ComponentName thisWidget = new ComponentName(context, GermanClock.class);
                appWidgetManager.updateAppWidget(thisWidget, remoteViews);
                finish();

            }


        });




        newConfig.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent configIntent = new Intent(context, ActivityCustomSettings.class);


                configIntent.putExtra(Constants.CLOCK, Constants.selectedClock);

                startActivity(configIntent);


            }


        });



    }

    public Settings passSettingsDownToClock(String configKey) {
        Settings s = new Settings();


        Toast.makeText(ActivitySettings.this,
                "lookn for germanClockKey..." + configKey + "...", Toast.LENGTH_SHORT).show();

        // Is the button now checked?
        if (configKey.equals("offiziell zeit")) {

            s.setEsist(Boolean.TRUE);
            s.setUhr(Boolean.TRUE);
            s.setMinute(Boolean.TRUE);

        } else if (configKey.equals("umgangssprachlich")) {

            s.setUmgangssprachlich(Boolean.TRUE);
            s.setUmgangminute(Settings.Umgangminute.minuteword);
            s.setHalber(Boolean.TRUE);
            s.setHalberRange(10);
            s.setKurznach(Boolean.TRUE);
            s.setViertel(Settings.Viertel.viertelacht);
            s.setZehnvorhalb(Boolean.TRUE);
            s.setFuenfvorhalb(Boolean.TRUE);
            s.setKurzvorhalb(Boolean.TRUE);
            s.setHalb(Settings.Halb.halb);
            s.setKurznachhalb(Boolean.TRUE);
            s.setFuenfnachhalb(Boolean.TRUE);
            s.setZehnnachhalb(Boolean.TRUE);
            s.setFuenfvordreiviertelacht(Boolean.TRUE);
            s.setKurzvordreiviertelacht(Boolean.TRUE);
            s.setDreiviertel(Settings.Dreiviertel.dreiviertelacht);
            s.setKurznachdreiviertelacht(Boolean.TRUE);
            s.setFuenfnachdreiviertelacht(Boolean.TRUE);
            s.setKurzvor(Boolean.TRUE);
            s.setAmabend(Boolean.TRUE);
            s.setAmmorgen(Boolean.TRUE);
            s.setInderfrueh(Boolean.TRUE);
            s.setAmnachmittag(Boolean.TRUE);
            s.setAmvormittag(Boolean.TRUE);

            s.setEsist(Boolean.TRUE);

        } else  {
            SharedPreferences preferences = getSharedPreferences(Constants.SETTING, 0);
            //(crated when you call SharedPreferences.edit()) and then commit changes (Editor.commit()).
            s.loadSettings(preferences.getAll(), Constants.selectedClock, configKey);
        }

        return s;

    }



    public void onClick(View v) {


        finish();
    }


}
