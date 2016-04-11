package com.wordzoo.uhr;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
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
public class ActivitySettings extends Activity implements OnClickListener {


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

                Toast.makeText(ActivitySettings.this,
                        defButton.getText(), Toast.LENGTH_SHORT).show();

                Settings settings = new Settings();
                settings.parse(defButton);
                updateSettings(defButton.getText()+"");



                AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
                RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.german_clock);
                ComponentName thisWidget = new ComponentName(context, GermanClock.class);
                appWidgetManager.updateAppWidget(thisWidget, remoteViews);

                //GermanClock.getInstance().startClock(GermanClock.getInstance().context);
                finish();

            }
            


        });



    }

    public void updateSettings(String def) {
        Settings s = new Settings();


        Toast.makeText(ActivitySettings.this,
                "lookn for..." + def + "...", Toast.LENGTH_SHORT).show();

        // Is the button now checked?
        if(def.equals("offiziell lang")) {

            s.setEsist(Boolean.TRUE);
            s.setUhr(Boolean.TRUE);
            s.setMinute(Boolean.TRUE);

        }
        else if(def.equals("offiziell kurz")) {

            //

        }
        else if(def.equals("umgangssprachlich")) {

            s.setUmgangssprachlich(Boolean.TRUE);
            s.setUmgangminute(Settings.Umgangminute.minuteword);
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

        }
        else if(def.equals(Settings.Default.custom)) {
            //comming soon.....
        }
        GermanClock.getInstance().setSettings(s);
        //GermanClock.getInstance().setTime(context);
        GermanClock.getInstance().startClock(context);


    }


    public void onClick(View v) {

        //save prefs
        SharedPreferences.Editor custClockEdit = clockPrefs.edit();
        custClockEdit.commit();

        finish();
    }
}