package com.wordzoo.uhr;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RemoteViews;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.germanclock.time.Pieces;
import com.germanclock.time.Settings;
import com.germanclock.words.HalberMinute;
import com.germanclock.words.TimeInWords;

import java.util.Date;

public class ActivityCustomSettings extends Activity implements OnClickListener, AdapterView.OnItemSelectedListener {


    //user prefs
    private SharedPreferences clockPrefs;

    private RadioGroup def;
    private RadioButton defButton;
    private Button done;
    private Button cancel;
    private Spinner spinner;

    private Context context;
    private Settings settings;



    public void setupSpinners(int id1, int id2) {
        Spinner spinner = (Spinner) findViewById(id1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                id2, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

    }


    public void onCreate(Bundle savedInstanceState) {
        /*
        Boolean default = this.getResources().getBoolean(R.integer.officallong);
        if(officiallong)
            view.setChecked(default);

*/

        super.onCreate(savedInstanceState);
        setContentView(R.layout.customsettings);

        this.settings = loadSettings();

        addDoneButtonListener();
        setupSpinners(R.id.dreiviertel, R.array.settings_dreiviertel);
        setupSpinners(R.id.viertel, R.array.settings_viertel);
        setupSpinners(R.id.morgen, R.array.settings_morgen);
        setupSpinners(R.id.vormittag, R.array.settings_vormittag);
        setupSpinners(R.id.mittag, R.array.settings_mittag);
        setupSpinners(R.id.nachmittag, R.array.settings_nachmittag);
        setupSpinners(R.id.abend, R.array.settings_abend);
        setupSpinners(R.id.nacht, R.array.settings_nacht);
        setupSpinners(R.id.frueh, R.array.settings_frueh);
        //user prefs

        this.context = this;

    }

    public Settings getSettings() {
        return settings;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }



    public void addDoneButtonListener() {

        def = (RadioGroup) findViewById(R.id.def);
        cancel = (Button) findViewById(R.id.cancel);

        cancel.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                finish();

            }


        });



    }

    public Settings loadSettings() {
        Settings s = new Settings();
        s.setUmgangssprachlich(Boolean.TRUE);
        s.setUmgangminute(Settings.Umgangminute.minuteword);

        Toast.makeText(ActivityCustomSettings.this,
                "lookn for..." + def + "...", Toast.LENGTH_SHORT).show();



        return s;

    }


    public void onClick(View v) {

        //save prefs
        SharedPreferences.Editor custClockEdit = clockPrefs.edit();
        custClockEdit.commit();

        finish();
    }

    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {

        switch (position) {
            case 0:
                // Whatever you want to happen when the first item gets selected
                break;
            case 1:
                // Whatever you want to happen when the second item gets selected
                break;
            case 2:
                // Whatever you want to happen when the thrid item gets selected
                break;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        String time = "08:45";
        TextView testclock = (TextView)findViewById(R.id.testclock);
        TextView preview = (TextView)findViewById(R.id.preview);


        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.uhr:
                if (checked)
                    getSettings().setUhr(Boolean.TRUE);
                else
                    getSettings().setUhr(Boolean.FALSE);
                break;
            case R.id.esist:
                if (checked)
                    getSettings().setEsist(Boolean.TRUE);
                else
                    getSettings().setEsist(Boolean.FALSE);
                break;
            case R.id.minute:
                if (checked)
                    getSettings().setMinute(Boolean.TRUE);
                else
                    getSettings().setMinute(Boolean.FALSE);
                break;
            case R.id.lederhosen_minutes:
                time = "8:48";
                if (checked) {
                    getSettings().setUmgangminute(Settings.Umgangminute.minutebar);
                    toggleAllKurz(Boolean.FALSE);
                    toggleHalber(Boolean.FALSE);
                }
                else {
                    getSettings().setUmgangminute(Settings.Umgangminute.minuteword);
                    toggleAllKurz(Boolean.TRUE);
                    toggleHalber(Boolean.TRUE);
                }
                break;
            case R.id.kurz_vor:
                time = "08:57";
                if (checked)
                    getSettings().setKurzvor(Boolean.TRUE);
                else
                    getSettings().setKurzvor(Boolean.FALSE);
                break;
            case R.id.kurz_nach:
                time = "09:02";
                if (checked)
                    getSettings().setKurznach(Boolean.TRUE);
                else
                    getSettings().setKurznach(Boolean.FALSE);
                break;
            case R.id.halb:
                time = "09:30";
                if (checked)
                    getSettings().setHalb(Settings.Halb.halb);
                else
                    getSettings().setHalb(Settings.Halb.dreissig);
                break;
            case R.id.halber:
                time = "09:29";
                if (checked) {
                    getSettings().setHalber(Boolean.TRUE);
                    getSettings().setHalberRange(5);
                }
                else {
                    getSettings().setHalber(Boolean.FALSE);
                }
                break;

            case R.id.fuenf_vor_halb:
                time = "09:25";
                if (checked)
                    getSettings().setFuenfvorhalb(Boolean.TRUE);
                else
                    getSettings().setFuenfvorhalb(Boolean.FALSE);
                break;
            case R.id.fuenf_nach_halb:
                time = "09:35";
                if (checked)
                    getSettings().setFuenfnachhalb(Boolean.TRUE);
                else
                    getSettings().setFuenfnachhalb(Boolean.FALSE);
                break;
            case R.id.zehn_vor_halb:
                time = "09:20";
                if (checked)
                    getSettings().setZehnvorhalb(Boolean.TRUE);
                else
                    getSettings().setZehnvorhalb(Boolean.FALSE);
                break;
            case R.id.zehn_nach_halb:
                time = "09:40";
                if (checked) {
                    getSettings().setZehnnachhalb(Boolean.TRUE);
                    toggleFuenfVorDreiviertel(Boolean.FALSE);
                }
                else {
                    getSettings().setZehnnachhalb(Boolean.FALSE);
                    toggleFuenfVorDreiviertel(Boolean.TRUE);
                }
                break;
            case R.id.kurz_vor_halb:
                time = "09:27";
                if (checked)
                    getSettings().setKurzvorhalb(Boolean.TRUE);
                else
                    getSettings().setKurzvorhalb(Boolean.FALSE);
                break;
            case R.id.kurz_nach_halb:
                time = "09:32";
                if (checked)
                    getSettings().setKurznachhalb(Boolean.TRUE);
                else
                    getSettings().setKurznachhalb(Boolean.FALSE);
                break;
            case R.id.kurz_vor_dreiviertel:
                time = "09:44";
                if (checked)
                    getSettings().setKurzvordreiviertelacht(Boolean.TRUE);
                else
                    getSettings().setKurzvordreiviertelacht(Boolean.FALSE);
                break;
            case R.id.kurz_nach_dreiviertel:
                time = "09:48";
                if (checked)
                    getSettings().setKurznachdreiviertelacht(Boolean.TRUE);
                else
                    getSettings().setKurznachdreiviertelacht(Boolean.FALSE);
                break;
            case R.id.fuenf_vor_dreiviertel:
                time = "09:40";
                if (checked) {
                    getSettings().setFuenfvordreiviertelacht(Boolean.TRUE);
                    toggleZehnNachHalb(Boolean.FALSE);
                }
                else {
                    getSettings().setFuenfvordreiviertelacht(Boolean.FALSE);
                    toggleZehnNachHalb(Boolean.TRUE);
                }
                break;
            case R.id.fuenf_nach_dreiviertel:
                time = "09:50";
                if (checked)
                    getSettings().setFuenfnachdreiviertelacht(Boolean.TRUE);
                else
                    getSettings().setFuenfnachdreiviertelacht(Boolean.FALSE);
                break;
            case R.id.kurz_vor_viertel:
                time = "09:13";
                if (checked)
                    getSettings().setKurzvorviertelacht(Boolean.TRUE);
                else
                    getSettings().setKurzvorviertelacht(Boolean.FALSE);
                break;
            case R.id.kurz_nach_viertel:
                time = "09:18";
                if (checked)
                    getSettings().setKurznachviertelacht(Boolean.TRUE);
                else
                    getSettings().setKurznachviertelacht(Boolean.FALSE);
                break;
            case R.id.fuenf_vor_viertel:
                time = "09:10";
                if (checked)
                    getSettings().setFuenfvorviertelacht(Boolean.TRUE);
                else
                    getSettings().setFuenfvorviertelacht(Boolean.FALSE);
                break;
            case R.id.fuenf_nach_viertel:
                time = "09:20";
                if (checked) {
                    toggleZehnVorHalb(Boolean.FALSE);
                    getSettings().setFuenfnachviertelacht(Boolean.TRUE);
                }
                else {
                    toggleZehnVorHalb(Boolean.TRUE);
                    getSettings().setFuenfnachviertelacht(Boolean.FALSE);
                }
                break;
        }

        preview.setText("Preview: " + time);
        testclock.setText(new TimeInWords(this.context).getTimeAsSentance(new Pieces(time), getSettings()));
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void toggleAllKurz(Boolean t){
        CheckBox def = (CheckBox) findViewById(R.id.kurz_nach);
        def.setEnabled(t);
        def = (CheckBox) findViewById(R.id.kurz_vor);
        def.setEnabled(t);
        def = (CheckBox) findViewById(R.id.kurz_nach_viertel);
        def.setEnabled(t);
        def = (CheckBox) findViewById(R.id.kurz_vor_viertel);
        def.setEnabled(t);
        def = (CheckBox) findViewById(R.id.kurz_nach_halb);
        def.setEnabled(t);
        def = (CheckBox) findViewById(R.id.kurz_vor_halb);
        def.setEnabled(t);
        def = (CheckBox) findViewById(R.id.kurz_nach_dreiviertel);
        def.setEnabled(t);
        def = (CheckBox) findViewById(R.id.kurz_vor_dreiviertel);
        def.setEnabled(t);

    }

    private void toggleHalber(Boolean t) {
        CheckBox def = (CheckBox) findViewById(R.id.halber);
        def.setEnabled(t);
        EditText inp = (EditText) findViewById(R.id.halber_range);
        inp.setEnabled(t);
    }

    private void toggleZehnNachHalb(Boolean t) {
        CheckBox def = (CheckBox) findViewById(R.id.zehn_nach_halb);
        def.setEnabled(t);

    }
    private void toggleFuenfVorDreiviertel(Boolean t) {
        CheckBox def = (CheckBox) findViewById(R.id.fuenf_vor_dreiviertel);
        def.setEnabled(t);

    }
    private void toggleZehnVorHalb(Boolean t) {
        CheckBox def = (CheckBox) findViewById(R.id.zehn_vor_halb);
        def.setEnabled(t);

    }
}
