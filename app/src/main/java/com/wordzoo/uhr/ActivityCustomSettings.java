package com.wordzoo.uhr;

import android.app.Activity;
import android.app.AlertDialog;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.LayoutInflater;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.germanclock.time.Pieces;
import com.germanclock.time.Settings;
import com.germanclock.words.TimeInWords;
import com.wordzoo.uhr.utils.Constants;
import com.wordzoo.uhr.utils.StoreRetrieveGerman;
import com.wordzoo.uhr.utils.TimePickerFragment;

public class ActivityCustomSettings extends FragmentActivity implements OnClickListener {


    private Button cancel;
    private Button save;
    private Button saveas;
    private Spinner spinner;

    private Settings settings;

    final String DEFAULT_TIME = "08:45";

    String promptResultConfigName = "new config";

    public void setupSpinners(int id1, int id2) {
        Spinner spinner = (Spinner) findViewById(id1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                id2, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View v, int position, long id) {
                String time = DEFAULT_TIME;
                TextView testclock = (TextView)findViewById(R.id.testclock);
                TextView preview = (TextView)findViewById(R.id.preview);

                switch(parentView.getId()) {
                    case R.id.dreiviertel:
                        time = "21:45";

                        if (((TextView)v).getText().equals("viertel vor")) {
                            getSettings().setDreiviertel(Settings.Dreiviertel.viertelvor);
                        }
                        else if(((TextView)v).getText().equals("dreiviertel")) {
                            getSettings().setDreiviertel(Settings.Dreiviertel.dreiviertelacht);
                        }
                        else if (((TextView)v).getText().equals("fünfzehn vor")) {
                            getSettings().setDreiviertel(Settings.Dreiviertel.fuenfzehn);
                        }
                        else
                            getSettings().setDreiviertel(Settings.Dreiviertel.kein);

                        break;
                    case R.id.viertel:
                        time = "21:15";
                        if(((TextView)v).getText().equals("viertel nach")) {
                            getSettings().setViertel(Settings.Viertel.viertelnach);
                        }
                        else if (((TextView)v).getText().equals("viertel über")) {
                            getSettings().setViertel(Settings.Viertel.viertelueber);
                        }
                        else if (((TextView)v).getText().equals("viertel")) {
                            getSettings().setViertel(Settings.Viertel.viertelacht);
                        }
                        else if (((TextView)v).getText().equals("fünfzehn nach")) {
                            getSettings().setViertel(Settings.Viertel.viertelfuenfzehn);
                        }
                        else
                            getSettings().setDreiviertel(Settings.Dreiviertel.kein);
                        break;
                    case R.id.morgen:
                        time = "08:45";
                        if(((TextView)v).getText().equals("morgens"))
                            getSettings().setMorgens(Boolean.TRUE);
                        else
                            getSettings().setMorgens(Boolean.FALSE);

                        if(((TextView)v).getText().equals("am Morgen"))
                            getSettings().setAmmorgen(Boolean.TRUE);
                        else
                            getSettings().setAmmorgen(Boolean.FALSE);
                        break;
                    case R.id.vormittag:
                        time = "11:45";
                        if(((TextView)v).getText().equals("vormittags"))
                            getSettings().setVormittags(Boolean.TRUE);
                        else
                            getSettings().setVormittags(Boolean.FALSE);

                        if(((TextView)v).getText().equals("am Vormittag"))
                            getSettings().setAmvormittag(Boolean.TRUE);
                        else
                            getSettings().setAmvormittag(Boolean.FALSE);
                        break;
                    case R.id.mittag:
                        time = "12:45";
                        if(((TextView)v).getText().equals("mittags"))
                            getSettings().setMittags(Boolean.TRUE);
                        else
                            getSettings().setMittags(Boolean.FALSE);

                        if(((TextView)v).getText().equals("am Mittag"))
                            getSettings().setAmmittag(Boolean.TRUE);
                        else
                            getSettings().setAmmittag(Boolean.FALSE);
                        break;
                    case R.id.nachmittag:
                        time = "14:45";
                        if(((TextView)v).getText().equals("nachmittags"))
                            getSettings().setNachmittags(Boolean.TRUE);
                        else
                            getSettings().setNachmittags(Boolean.FALSE);

                        if(((TextView)v).getText().equals("am Nachmittag"))
                            getSettings().setAmnachmittag(Boolean.TRUE);
                        else
                            getSettings().setAmnachmittag(Boolean.FALSE);
                        break;
                    case R.id.abend:
                        time = "18:45";
                        if(((TextView)v).getText().equals("abends"))
                            getSettings().setAbends(Boolean.TRUE);
                        else
                            getSettings().setAbends(Boolean.FALSE);

                        if(((TextView)v).getText().equals("am Abend"))
                            getSettings().setAmabend(Boolean.TRUE);
                        else
                            getSettings().setAmabend(Boolean.FALSE);
                        break;
                    case R.id.nacht:
                        time = "22:45";
                        if(((TextView)v).getText().equals("nachts"))
                            getSettings().setNachts(Boolean.TRUE);
                        else
                            getSettings().setNachts(Boolean.FALSE);

                        if(((TextView)v).getText().equals("in der Nacht"))
                            getSettings().setIndernacht(Boolean.TRUE);
                        else
                            getSettings().setIndernacht(Boolean.FALSE);
                        break;

                    case R.id.frueh:
                        time = "03:45";
                        if(((TextView)v).getText().equals("morgens"))
                            getSettings().setMorgennacht(Boolean.TRUE);
                        else
                            getSettings().setMorgennacht(Boolean.FALSE);

                        if(((TextView)v).getText().equals("am Morgen"))
                            getSettings().setAmmorgennacht(Boolean.TRUE);
                        else
                            getSettings().setAmmorgennacht(Boolean.FALSE);
                        if(((TextView)v).getText().equals("in der Früh"))
                            getSettings().setInderfrueh(Boolean.TRUE);
                        else
                            getSettings().setInderfrueh(Boolean.FALSE);
                        break;

                }

                //updateAvailable();

                drawTime(time);

            }

            @Override
            public void onNothingSelected(AdapterView<?> v) {
                Toast.makeText(ActivityCustomSettings.this, "onNothingSelected()", Toast.LENGTH_SHORT).show();

            }

        });

    }


    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.customsettings);

        //assume its a new setting
        Intent intent = getIntent();
        String CONFIG_MODE = intent.getStringExtra(Constants.CONFIG_MODE);
        String selectedConfig = intent.getStringExtra(Constants.selectedConfig);


        if(CONFIG_MODE.equals(Constants.CONFIG_MODE_EDIT)) {
            setSettings(new StoreRetrieveGerman().loadSettingsFromDisk(
                    getSharedPreferences(Constants.SETTING, 0),
                    Constants.selectedClock,
                    selectedConfig,
                    ActivityCustomSettings.this));

        }
        else {
            setSettings(new Settings());
            getSettings().setUmgangssprachlich(Boolean.TRUE); //this page is only use to configure umgang variations
        }
        addButtonListeners(CONFIG_MODE, selectedConfig);
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
        if(CONFIG_MODE.equals(Constants.CONFIG_MODE_EDIT)) {
            loadUI();
            //updateAvailable();
        }


    }

    public Settings getSettings() {
        return settings;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }


    public void addButtonListeners(String CONFIG_MODE, final String selectedConfig) {
        cancel = (Button) findViewById(R.id.cancel);
        save = (Button) findViewById(R.id.save);
        saveas = (Button) findViewById(R.id.saveas);
        if(CONFIG_MODE.equals(Constants.CONFIG_MODE_EDIT)) {
            save.setVisibility(View.VISIBLE);
            save.setText("update " + selectedConfig);
            saveas.setVisibility(View.GONE);
        }
        else {
            save.setVisibility(View.GONE);
            saveas.setVisibility(View.VISIBLE);
        }

        saveas.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                promptForName();

            }


        });

        save.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                SharedPreferences sp = getSharedPreferences(Constants.SETTING, 0);
                new StoreRetrieveGerman().storeSettingsToDisk(sp,
                        Constants.selectedClock,
                        selectedConfig,
                        getSettings(),
                        ActivityCustomSettings.this);
                new StoreRetrieveGerman().updateChosenConfig(sp,selectedConfig, ActivityCustomSettings.this);
                setResult(RESULT_OK, null);
                finish();

            }


        });

        cancel.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                finish();

            }


        });



    }


    public void promptForName(){

        // get prompts.xml view
        LayoutInflater li = LayoutInflater.from(ActivityCustomSettings.this);
        View promptsView = li.inflate(R.layout.prompt, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                ActivityCustomSettings.this);

        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);

        final EditText userInput = (EditText) promptsView
                .findViewById(R.id.editTextDialogUserInput);

        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                promptResultConfigName = userInput.getText().toString();
                                SharedPreferences sp = getSharedPreferences(Constants.SETTING, 0);
                                new StoreRetrieveGerman().storeNewConfigNameToDisk(sp, Constants.selectedClock, promptResultConfigName);
                                new StoreRetrieveGerman().storeSettingsToDisk(sp, Constants.selectedClock,
                                        promptResultConfigName,
                                        getSettings(),
                                        ActivityCustomSettings.this);
                                new StoreRetrieveGerman().updateChosenConfig(sp, promptResultConfigName, ActivityCustomSettings.this);
                                setResult(RESULT_OK, null);
                                finish();
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();

    }

    public void onClick(View v) {

        ;
    }


    public void timeOnClick(View view) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }


    public void onHalberRangeClick(View view) {
        if(((EditText) view).getText() == null
                || ((EditText) view).getText().equals(""))
            return;
        Integer range = new Integer(((EditText) view).getText()+"");
        getSettings().setHalberRange(range);

        String time = "09:22";;
        drawTime(time);

    }

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        String time = DEFAULT_TIME;

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
            case R.id.mult_of_five:
                time = "8:48";
                if (checked) {
                    getSettings().setUmgangminute(Settings.Umgangminute.minutebar);
                }
                else {
                    getSettings().setUmgangminute(Settings.Umgangminute.minuteword);
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
                if (checked) {
                    getSettings().setHalb(Settings.Halb.halb);
                }
                else {
                    getSettings().setHalb(Settings.Halb.dreissig);
                }
                break;
            case R.id.halber:
                time = "09:22";
                if (checked) {
                    getSettings().setHalber(Boolean.TRUE);
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
                }
                else {
                    getSettings().setZehnnachhalb(Boolean.FALSE);
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
                }
                else {
                    getSettings().setFuenfvordreiviertelacht(Boolean.FALSE);
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
                    getSettings().setFuenfnachviertelacht(Boolean.TRUE);
                }
                else {
                    getSettings().setFuenfnachviertelacht(Boolean.FALSE);
                }
                break;
            case R.id.hybrid:
                time = "09:49";
                if (checked) {
                    getSettings().setMinuteHybrid(Boolean.TRUE);
                }
                else {
                    getSettings().setMinuteHybrid(Boolean.FALSE);
                }
                break;

        }
        updateAvailable();

        drawTime(time);
    }


    public void toggleAllKurz(Boolean t){
        CheckBox def = (CheckBox) findViewById(R.id.kurz_nach);
        updateCheckBox(def, t);
        getSettings().setKurznach(t);
        def = (CheckBox) findViewById(R.id.kurz_vor);
        updateCheckBox(def, t);
        getSettings().setKurzvor(t);
        def = (CheckBox) findViewById(R.id.kurz_nach_viertel);
        updateCheckBox(def, t);
        getSettings().setKurznachviertelacht(t);
        def = (CheckBox) findViewById(R.id.kurz_vor_viertel);
        updateCheckBox(def, t);
        getSettings().setKurzvorviertelacht(t);
        def = (CheckBox) findViewById(R.id.kurz_nach_halb);
        updateCheckBox(def, t);
        getSettings().setKurznachhalb(t);
        def = (CheckBox) findViewById(R.id.kurz_vor_halb);
        updateCheckBox(def, t);
        getSettings().setKurzvorhalb(t);
        def = (CheckBox) findViewById(R.id.kurz_nach_dreiviertel);
        updateCheckBox(def, t);
        getSettings().setKurznachdreiviertelacht(t);
        def = (CheckBox) findViewById(R.id.kurz_vor_dreiviertel);
        updateCheckBox(def, t);
        getSettings().setKurzvordreiviertelacht(t);

    }

    public void toggleHalbAndAdjustments(Boolean t) {
        CheckBox def = (CheckBox) findViewById(R.id.halb);
        updateCheckBox(def, t);
        def = (CheckBox) findViewById(R.id.kurz_nach_halb);
        updateCheckBox(def, t);
        getSettings().setKurznachhalb(t);
        def = (CheckBox) findViewById(R.id.kurz_vor_halb);
        updateCheckBox(def, t);
        getSettings().setKurzvorhalb(t);
        def = (CheckBox) findViewById(R.id.fuenf_nach_halb);
        updateCheckBox(def, t);
        getSettings().setFuenfnachhalb(t);
        def = (CheckBox) findViewById(R.id.fuenf_vor_halb);
        updateCheckBox(def, t);
        getSettings().setFuenfvorhalb(t);
        def = (CheckBox) findViewById(R.id.zehn_nach_halb);
        updateCheckBox(def, t);
        getSettings().setZehnnachhalb(t);
        def = (CheckBox) findViewById(R.id.zehn_vor_halb);
        updateCheckBox(def, t);
        getSettings().setZehnvorhalb(t);
    }

    public void toggleViertelAdjustments(Boolean t){
        CheckBox def = (CheckBox) findViewById(R.id.kurz_nach_viertel);
        updateCheckBox(def, t);
        getSettings().setKurznachviertelacht(t);
        def = (CheckBox) findViewById(R.id.kurz_vor_viertel);
        updateCheckBox(def, t);
        getSettings().setKurzvorviertelacht(t);
        def = (CheckBox) findViewById(R.id.fuenf_vor_viertel);
        updateCheckBox(def, t);
        getSettings().setFuenfvorviertelacht(t);
        def = (CheckBox) findViewById(R.id.fuenf_nach_viertel);
        updateCheckBox(def, t);
        getSettings().setFuenfnachviertelacht(t);

    }

    public void toggleDreiviertelAdjustments(Boolean t){
        CheckBox def = (CheckBox) findViewById(R.id.kurz_nach_dreiviertel);
        updateCheckBox(def, t);
        getSettings().setKurznachdreiviertelacht(t);
        def = (CheckBox) findViewById(R.id.kurz_vor_dreiviertel);
        updateCheckBox(def, t);
        getSettings().setKurzvordreiviertelacht(t);
        def = (CheckBox) findViewById(R.id.fuenf_vor_dreiviertel);
        updateCheckBox(def, t);
        getSettings().setFuenfvordreiviertelacht(t);
        def = (CheckBox) findViewById(R.id.fuenf_nach_dreiviertel);
        updateCheckBox(def, t);
        getSettings().setFuenfnachdreiviertelacht(t);

    }

    private void toggleHalber(Boolean t) {
        CheckBox def = (CheckBox) findViewById(R.id.halber);
        updateCheckBox(def, t);
        getSettings().setHalber(t);
        EditText inp = (EditText) findViewById(R.id.halber_range);
        updateCheckBox(def, t);
    }

    private void toggleZehnNachHalb(Boolean t) {
        CheckBox def = (CheckBox) findViewById(R.id.zehn_nach_halb);
        updateCheckBox(def, t);
        getSettings().setZehnnachhalb(t);
    }
    private void toggleFuenfVorDreiviertel(Boolean t) {
        CheckBox def = (CheckBox) findViewById(R.id.fuenf_vor_dreiviertel);
        updateCheckBox(def, t);
        getSettings().setFuenfvordreiviertelacht(t);
    }
    private void toggleZehnVorHalb(Boolean t) {
        CheckBox def = (CheckBox) findViewById(R.id.zehn_vor_halb);
        updateCheckBox(def, t);
        getSettings().setZehnvorhalb(t);

    }

    private void updateCheckBox(CheckBox def, Boolean t) {
        if(t)
            def.setEnabled(t);
        else {
            def.setChecked(t);
            def.setEnabled(t);
        }
    }
    public void loadUI() {
        CheckBox def = (CheckBox) findViewById(R.id.esist);
        def.setChecked(getSettings().getEsist());
        def = (CheckBox) findViewById(R.id.minute);
        def.setChecked(getSettings().getMinute());
        def = (CheckBox) findViewById(R.id.uhr);
        def.setChecked(getSettings().getUhr());
        def = (CheckBox) findViewById(R.id.mult_of_five);
        def.setChecked(getSettings().getUmgangminute().equals(Settings.Umgangminute.minutebar));
        def = (CheckBox) findViewById(R.id.kurz_nach);
        def.setChecked(getSettings().getKurznach());
        def = (CheckBox) findViewById(R.id.kurz_vor);
        def.setChecked(getSettings().getKurzvor());
        //halb
        def = (CheckBox) findViewById(R.id.halber);
        def.setChecked(getSettings().getHalber());
        EditText tv = (EditText) findViewById(R.id.halber_range);
        tv.setText(getSettings().getHalberRange()+"");

        def = (CheckBox) findViewById(R.id.halb);
        def.setChecked(getSettings().getHalb().equals(Settings.Halb.halb));

        def = (CheckBox) findViewById(R.id.fuenf_vor_halb);
        def.setChecked(getSettings().getFuenfvorhalb());
        def = (CheckBox) findViewById(R.id.fuenf_nach_halb);
        def.setChecked(getSettings().getFuenfnachhalb());
        def = (CheckBox) findViewById(R.id.zehn_vor_halb);
        def.setChecked(getSettings().getZehnvorhalb());
        def = (CheckBox) findViewById(R.id.zehn_nach_halb);
        def.setChecked(getSettings().getZehnnachhalb());
        def = (CheckBox) findViewById(R.id.kurz_vor_halb);
        def.setChecked(getSettings().getKurzvorhalb());
        def = (CheckBox) findViewById(R.id.kurz_nach_halb);
        def.setChecked(getSettings().getKurznachhalb());



        //dreiviertel
        Spinner sp = (Spinner) findViewById(R.id.dreiviertel);
        sp.setSelection(Settings.Dreiviertel.valueOf(getSettings().getDreiviertel().name()).ordinal(), Boolean.FALSE);
        def = (CheckBox) findViewById(R.id.kurz_vor_dreiviertel);
        def.setChecked(getSettings().getKurzvordreiviertelacht());
        def = (CheckBox) findViewById(R.id.kurz_nach_dreiviertel);
        def.setChecked(getSettings().getKurznachdreiviertelacht());
        def = (CheckBox) findViewById(R.id.fuenf_vor_dreiviertel);
        def.setChecked(getSettings().getFuenfvordreiviertelacht());
        def = (CheckBox) findViewById(R.id.fuenf_nach_dreiviertel);
        def.setChecked(getSettings().getFuenfnachdreiviertelacht());

        //viertel
        sp = (Spinner) findViewById(R.id.viertel);
        sp.setSelection(Settings.Viertel.valueOf(getSettings().getViertel().name()).ordinal(), Boolean.FALSE); //ordinal is 0  based
        def = (CheckBox) findViewById(R.id.kurz_vor_viertel);
        def.setChecked(getSettings().getKurzvorviertelacht());
        def = (CheckBox) findViewById(R.id.kurz_nach_viertel);
        def.setChecked(getSettings().getKurznachviertelacht());
        def = (CheckBox) findViewById(R.id.fuenf_vor_viertel);
        def.setChecked(getSettings().getFuenfvorviertelacht());
        def = (CheckBox) findViewById(R.id.fuenf_nach_viertel);
        def.setChecked(getSettings().getFuenfnachviertelacht());

        //TOD
        sp = (Spinner) findViewById(R.id.morgen);
        if(getSettings().getMorgens())
            sp.setSelection(1, Boolean.FALSE);
        else if(getSettings().getAmmorgen())
            sp.setSelection(2, Boolean.FALSE);
        else
            sp.setSelection(0, Boolean.FALSE);


        sp = (Spinner) findViewById(R.id.vormittag);
        if(getSettings().getVormittags())
            sp.setSelection(1, Boolean.FALSE);
        else if(getSettings().getAmvormittag())
            sp.setSelection(2, Boolean.FALSE);
        else
            sp.setSelection(0, Boolean.FALSE);

        sp = (Spinner) findViewById(R.id.mittag);
        if(getSettings().getMittags())
            sp.setSelection(1, Boolean.FALSE);
        else if(getSettings().getAmmittag())
            sp.setSelection(2, Boolean.FALSE);
        else
            sp.setSelection(0, Boolean.FALSE);


        sp = (Spinner) findViewById(R.id.nachmittag);
        if(getSettings().getNachmittags())
            sp.setSelection(1, Boolean.FALSE);
        else if(getSettings().getAmnachmittag())
            sp.setSelection(2, Boolean.FALSE);
        else
            sp.setSelection(0, Boolean.FALSE);


        sp = (Spinner) findViewById(R.id.abend);
        if(getSettings().getAbends())
            sp.setSelection(1, Boolean.FALSE);
        else if(getSettings().getAmabend())
            sp.setSelection(2, Boolean.FALSE);
        else
            sp.setSelection(0, Boolean.FALSE);


        sp = (Spinner) findViewById(R.id.nacht);
        if(getSettings().getNachts())
            sp.setSelection(1, Boolean.FALSE);
        else if(getSettings().getIndernacht())
            sp.setSelection(2, Boolean.FALSE);
        else
            sp.setSelection(0, Boolean.FALSE);

        sp = (Spinner) findViewById(R.id.frueh);
        if(getSettings().getMorgennacht())
            sp.setSelection(1, Boolean.FALSE);
        else if(getSettings().getAmmorgennacht())
            sp.setSelection(2, Boolean.FALSE);
        else if(getSettings().getInderfrueh())
            sp.setSelection(3, Boolean.FALSE);
        else
            sp.setSelection(0, Boolean.FALSE);

        //hybrid
        def = (CheckBox) findViewById(R.id.hybrid);
        def.setChecked(getSettings().getMinuteHybrid());
    }

    public void updateAvailable() {
       /* if (getSettings().getUmgangminute().equals(Settings.Umgangminute.minutebar)){
            toggleHalber(Boolean.FALSE);
        }
        else if (getSettings().getUmgangminute().equals(Settings.Umgangminute.minuteword)) {
            toggleHalber(Boolean.TRUE);
        }
*/
        if (getSettings().getHalber()) {
            toggleHalbAndAdjustments(Boolean.FALSE);
        }
        else {
            toggleHalbAndAdjustments(Boolean.TRUE);
        }
/*
        if (getSettings().getZehnnachhalb()) {
            toggleFuenfVorDreiviertel(Boolean.FALSE);
        }
        else {
            toggleFuenfVorDreiviertel(Boolean.TRUE);
        }

        if (getSettings().getFuenfvordreiviertelacht()) {
            toggleZehnNachHalb(Boolean.FALSE);
        }
        else {
            toggleZehnNachHalb(Boolean.TRUE);
        }

        if (getSettings().getFuenfnachviertelacht()) {
            toggleZehnVorHalb(Boolean.FALSE);
        }
        else {
            toggleZehnVorHalb(Boolean.TRUE);
        }
        if (getSettings().getMinuteHybrid()) {
            toggleAllKurz(Boolean.FALSE);
        }
        else {
            toggleAllKurz(Boolean.TRUE);
        }


        if(getSettings().getDreiviertel().equals(Settings.Dreiviertel.dreiviertelacht)) {

            toggleDreiviertelAdjustments(Boolean.TRUE);
        }
        else if(getSettings().getDreiviertel().equals(Settings.Dreiviertel.viertelvor)) {
            toggleDreiviertelAdjustments(Boolean.FALSE);
        }
        else { //Settings.Dreiviertel.fuenfzehn);
            toggleDreiviertelAdjustments(Boolean.FALSE);
        }

        if(getSettings().getViertel().equals(Settings.Viertel.viertelnach)) {
            toggleViertelAdjustments(Boolean.FALSE);
        }
        else if(getSettings().getViertel().equals(Settings.Viertel.viertelueber)) {
            toggleViertelAdjustments(Boolean.FALSE);
        }
        else if(getSettings().getViertel().equals(Settings.Viertel.viertelacht)) {

            toggleViertelAdjustments(Boolean.TRUE);
        }
        else { //Settings.Viertel.viertelfuenfzehn);
            toggleViertelAdjustments(Boolean.FALSE);
        }

*/
    }

    public void drawTime(String time) {

        TextView testclock = (TextView)findViewById(R.id.testclock);
        TextView preview = (TextView)findViewById(R.id.preview);


        Pieces p = new Pieces(time);
        preview.setText(time);
        testclock.setText(new TimeInWords(ActivityCustomSettings.this).getTimeAsSentance(p, getSettings()));
        int drawableid = 0;
        if(getSettings().getUmgangminute().equals(Settings.Umgangminute.minutebar)
                && getSettings().getUmgangssprachlich()) {
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
