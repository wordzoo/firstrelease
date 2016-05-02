package com.wordzoo.uhr;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
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
import com.wordzoo.uhr.utils.StoreRetrieveGerman;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class ActivitySettings extends Activity implements OnClickListener {



    private RadioGroup  config; //group of configurations
    private RadioButton selectedConfigButton; //selected configuration
    private Button done;
    private Button delete;
    private Button newConfig;
    private Button edit;


    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        //get all configs
        SharedPreferences sharedPreferences = getSharedPreferences(Constants.SETTING,0);
        Set set = sharedPreferences.getStringSet(Constants.selectedClock + "~" + Constants.CONFIG, new HashSet());
        String chosenConfig = sharedPreferences.getString(Constants.selectedClock + "~" + Constants.selectedConfig, Constants.OFFICIAL_TIME);
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
            if(configName.equals(chosenConfig))
                config.check(rdbtn.getId());

        }

        addButtonListeners();




    }

    public void addButtonListeners() {

        config = (RadioGroup) findViewById(R.id.config);
        done = (Button) findViewById(R.id.done);
        newConfig = (Button) findViewById(R.id.newClock);
        delete = (Button) findViewById(R.id.delete);

        delete.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                int selectedId = config.getCheckedRadioButtonId();
                String configToDelete = ((RadioButton) findViewById(selectedId)).getText()+"";
                SharedPreferences sp = getSharedPreferences(Constants.SETTING, 0);
                SharedPreferences.Editor editor = sp.edit();

                //if this is currently active config, delete that key
                String chosenConfig = sp.getString(Constants.selectedClock + "~" + Constants.selectedConfig, null);
                if(chosenConfig != null)
                    editor.remove(Constants.selectedClock + "~" + Constants.selectedConfig);

                //remove from config key from set of config options
                String key_for_configs = Constants.selectedClock + "~" + Constants.CONFIG;
                Set configs = sp.getStringSet(key_for_configs, new HashSet());
                configs.remove(configToDelete);
                editor.putStringSet(key_for_configs, configs);
                editor.commit();

                //remove all detailed config for this key
                Map<String, ?> map = sp.getAll();
                Set<String> keys = map.keySet();
                Iterator<String> i = keys.iterator();
                while(i.hasNext()) {
                    String key = i.next();
                    if(key.startsWith(Constants.selectedClock + "~" + chosenConfig))
                        editor.remove(key);
                }
                editor.commit();
            }
        });

        done.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                // get selected radio button from radioGroup
                int selectedId = config.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                selectedConfigButton = (RadioButton) findViewById(selectedId);

                //pushes settings out to clock with result intent
                SharedPreferences sp = getSharedPreferences(Constants.SETTING, 0);
                new StoreRetrieveGerman().updateChosenConfig(sp, selectedConfigButton.getText() + "", ActivitySettings.this);



                //do a manual time update to immidate results of new clock configuration
                Intent intent = new Intent(ActivitySettings.this, GermanClock.class);
                intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
                AppWidgetManager widgetManager = AppWidgetManager.getInstance(ActivitySettings.this);
                int[] ids = widgetManager.getAppWidgetIds(new ComponentName(ActivitySettings.this, GermanClock.class));

                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
                    widgetManager.notifyAppWidgetViewDataChanged(ids, android.R.id.list);

                intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids);
                ActivitySettings.this.sendBroadcast(intent);

                finish();

            }


        });


        newConfig.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent configIntent = new Intent(ActivitySettings.this, ActivityCustomSettings.class);


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
