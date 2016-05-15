package com.wordzoo.uhr;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.germanclock.time.Pieces;
import com.germanclock.time.Settings;
import com.germanclock.words.TimeInWords;
import com.wordzoo.uhr.utils.ChromeHelpPopup;
import com.wordzoo.uhr.utils.Constants;
import com.wordzoo.uhr.utils.StoreRetrieveGerman;

import java.text.SimpleDateFormat;
import java.util.Date;
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

    private Boolean configNotDefault(String config) {
        if(!config.equals(Constants.OFFICIAL_TIME)
                && !config.equals(Constants.INFORMAL_TIME)
                && !config.equals(Constants.STAR_TIME)
                && !config.equals(Constants.MIXED_TIME))
            return Boolean.TRUE;
        else
            return Boolean.FALSE;

    }

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
            rdbtn.setId(id);
            id++;
            rdbtn.setText(configName);
            config.addView(rdbtn);
            if (configName.equals(chosenConfig))
                config.check(rdbtn.getId());


        }


        Settings s = new StoreRetrieveGerman().loadSettingsFromDisk(getSharedPreferences(Constants.SETTING, 0),
                Constants.selectedClock,
                chosenConfig,
                ActivitySettings.this);

        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("H:mm");
        drawTime(sdf.format(d), s, chosenConfig);

        updateButtons(chosenConfig);

        config.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup rg, int i) {

                String selectedConfig = ((RadioButton) findViewById(i)).getText() + "";

                Settings s = new StoreRetrieveGerman().loadSettingsFromDisk(getSharedPreferences(Constants.SETTING, 0),
                        Constants.selectedClock,
                        selectedConfig,
                        ActivitySettings.this);

                Date d = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("H:mm");
                drawTime(sdf.format(d), s, selectedConfig);


                updateButtons(selectedConfig);

            }
        });


        addButtonListeners();




    }

    public void updateButtons(String selectedConfig) {
        delete = (Button) findViewById(R.id.delete);
        edit = (Button) findViewById(R.id.edit);

        //to base configs we don't delete
        if (selectedConfig.equals(Constants.INFORMAL_TIME)
                || selectedConfig.equals(Constants.OFFICIAL_TIME)
                || selectedConfig.equals(Constants.MIXED_TIME)
                || selectedConfig.equals(Constants.STAR_TIME)) {
            delete.setBackgroundColor(Color.GRAY);
            edit.setBackgroundColor(Color.GRAY);
            delete.setEnabled(Boolean.FALSE);
            edit.setEnabled(Boolean.FALSE);
        }
        else {
            delete.setBackground(getResources().getDrawable(R.drawable.mybutton));
            edit.setBackground(getResources().getDrawable(R.drawable.mybutton));
            delete.setEnabled(Boolean.TRUE);
            edit.setEnabled(Boolean.TRUE);
        }
    }

    public void addButtonListeners() {

        config = (RadioGroup) findViewById(R.id.config);
        done = (Button) findViewById(R.id.done);
        newConfig = (Button) findViewById(R.id.newClock);
        delete = (Button) findViewById(R.id.delete);
        edit = (Button) findViewById(R.id.edit);

        delete.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                int selectedId = config.getCheckedRadioButtonId();
                String configToDelete = ((RadioButton) findViewById(selectedId)).getText()+"";

                //to base configs we don't delete
                if(configToDelete.equals(Constants.INFORMAL_TIME)
                        || configToDelete.equals(Constants.OFFICIAL_TIME)
                        || configToDelete.equals(Constants.STAR_TIME)
                        || configToDelete.equals(Constants.MIXED_TIME))
                    return;

                SharedPreferences sp = getSharedPreferences(Constants.SETTING, 0);
                SharedPreferences.Editor editor = sp.edit();

                //if this is currently active config, delete that key, and set default
                String chosenConfig = sp.getString(Constants.selectedClock + "~" + Constants.selectedConfig, null);
                if(chosenConfig.equals(configToDelete)) {
                    editor.remove(Constants.selectedClock + "~" + Constants.selectedConfig);
                    editor.putString(Constants.selectedClock + "~" + Constants.selectedConfig, Constants.OFFICIAL_TIME);
                    editor.commit();
                }

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
                    if(key.startsWith(Constants.selectedClock + "~" + configToDelete))
                        editor.remove(key);
                }
                editor.commit();
                finish();
                startActivity(getIntent());
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

                Settings s = new StoreRetrieveGerman().loadSettingsFromDisk(sp, Constants.selectedClock, selectedConfigButton.getText()+ "", ActivitySettings.this);
                //Toast.makeText(ActivitySettings.this,
                 //            "minuteword is: " + s.getUmgangminute(), Toast.LENGTH_SHORT).show();


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
                configIntent.putExtra(Constants.CONFIG_MODE, Constants.CONFIG_MODE_NEW);
                startActivityForResult(configIntent, 0);


            }


        });

        edit.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                int selectedId = config.getCheckedRadioButtonId();
                String selectedConfig = ((RadioButton) findViewById(selectedId)).getText() + "";


                //to base configs we don't delete
                if (selectedConfig.equals(Constants.INFORMAL_TIME)
                        || selectedConfig.equals(Constants.OFFICIAL_TIME))
                    return;

                Intent configIntent = new Intent(ActivitySettings.this, ActivityCustomSettings.class);


                configIntent.putExtra(Constants.CLOCK, Constants.selectedClock);
                configIntent.putExtra(Constants.CONFIG_MODE, Constants.CONFIG_MODE_EDIT);

                configIntent.putExtra(Constants.selectedConfig, selectedConfig);

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

    public String setXPreviewDescription(String chosenConfig) {
        if(chosenConfig.equals(Constants.OFFICIAL_TIME))
            return getResources().getString(R.string.xofficial_time);
        if(chosenConfig.equals(Constants.INFORMAL_TIME))
            return getResources().getString(R.string.xconv_time);
        if(chosenConfig.equals(Constants.STAR_TIME))
            return getResources().getString(R.string.xstar_time);
        if(chosenConfig.equals(Constants.MIXED_TIME))
            return getResources().getString(R.string.xmixed_time);

        return "Custom";
    }

    public void help(View view) {
        ChromeHelpPopup chromeHelpPopup = new ChromeHelpPopup(ActivitySettings.this, view.getContentDescription()+"");
        chromeHelpPopup.show(view);
    }

    public void drawTime(String time, Settings settings, String chosenConfig) {

        TextView testclock = (TextView)findViewById(R.id.example);
        TextView preview = (TextView)findViewById(R.id.preview);
        TextView previewLabel = (TextView)findViewById(R.id.preview_label);
        ImageView xpreview = (ImageView)findViewById(R.id.xpreview);


        Pieces p = new Pieces(time);
        preview.setText(time);
        previewLabel.setText(chosenConfig);
        xpreview.setContentDescription(setXPreviewDescription(chosenConfig));

        testclock.setText(new TimeInWords(ActivitySettings.this).getTimeAsSentance(p, settings));
        int drawableid = 0;
        if(settings.getUmgangminute().equals(Settings.Umgangminute.minutebar)
                && settings.getUmgangssprachlich()) {
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
        if(drawableid > 0)
            testclock.setCompoundDrawablesWithIntrinsicBounds(0, 0, drawableid, 0);
        else
            testclock.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
    }

}
