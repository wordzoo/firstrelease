package com.wordzoo.uhr;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.view.View.OnClickListener;

import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RemoteViews;
import android.widget.Toast;
import com.germanclock.time.Settings;
import com.wordzoo.uhr.utils.Constants;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class ActivitySettings extends Activity implements OnClickListener {



    private RadioGroup  config; //group of configurations
    private RadioButton selectedConfigButton; //selected configuration
    private Button done;
    private Button newConfig;
    private Button editClock;

    private GermanClock germanClock;

    Context context;

    public GermanClock getGermanClock() {
        return germanClock;
    }

    public void setGermanClock(GermanClock germanClock) {
        this.germanClock = germanClock;
    }

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        //Settings settings = (Settings)getIntent().getExtras().getSerializable(Constants.SETTING);

        //Store reference to our clock settings to update later
        //settings.setUmgangssprachlich(Boolean.TRUE);
        //settings.setEsist(Boolean.FALSE);



        //get all configs
        SharedPreferences sharedPreferences = getSharedPreferences(Constants.SETTING,0);
        Set set = sharedPreferences.getStringSet(Constants.selectedClock + "~" + Constants.CONFIG, new HashSet());
        Iterator i = set.iterator();
        String configName = "";
        int id = 0;
        config = (RadioGroup) findViewById(R.id.config);
        while(i.hasNext()) {
            configName = (String) i.next();
            RadioButton rdbtn = new RadioButton(this);
            rdbtn.setId(id); id++;
            rdbtn.setText(configName);
            config.addView(rdbtn);
        }

        addButtonListeners();



        this.context = this;


    }

    public void addButtonListeners() {

        config = (RadioGroup) findViewById(R.id.config);
        done = (Button) findViewById(R.id.done);
        newConfig = (Button) findViewById(R.id.newClock);

        done.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                // get selected radio button from radioGroup
                int selectedId = config.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                selectedConfigButton = (RadioButton) findViewById(selectedId);



                //pushes settings out to clock with result intent
                SharedPreferences sp = getSharedPreferences(Constants.SETTING, 0);
                SharedPreferences.Editor editor = sp.edit();
                editor.remove(Constants.selectedClock + "~" + Constants.selectedConfig);
                editor.putString(Constants.selectedClock + "~" + Constants.selectedConfig, selectedConfigButton.getText()+"");
                editor.commit();

                //do a manual time update to immidate results of new clock configuration
                AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
                RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.german_clock);
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

                startActivityForResult(configIntent, 0);


            }


        });




    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            Intent refresh = new Intent(this, ActivitySettings.class);
            startActivity(refresh);
            this.finish();
        }
    }



    public void onClick(View v) {


        finish();
    }


}
