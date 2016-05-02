package com.wordzoo.uhr.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.widget.Toast;

import com.germanclock.time.Settings;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by ich on 28.04.2016.
 */
public class StoreRetrieveGerman  {


    public Settings loadSettingsFromDisk(SharedPreferences sp, String selectedClock, String configName, Context c) {
        Settings s = new Settings();

        Map<String, ?> map = sp.getAll();

        // an actual key covers, language~customname~setting
        //get settings for language and customName
        String prefix = selectedClock + "~" + configName + "~";

        s.setUmgangssprachlich((Boolean) map.get(prefix + "umgangssprachlich")); //all variable settings are umgang/not official



        s.setEsist((Boolean) map.get(prefix + "esist"));
        s.setUhr((Boolean) map.get(prefix + "uhr"));
        s.setMinute((Boolean) map.get(prefix + "minute"));

        s.setCustomName((String) map.get(prefix + "customName"));
        s.setFlag((Boolean) map.get(prefix + "flag"));
        if(map.get(prefix + "flagPattern") != null)
            s.setFlagPattern(Settings.FlagPattern.values()[(Integer) map.get(prefix + "flagPattern")]);
        s.setUm((Boolean) map.get(prefix + "um"));
        s.setHalber((Boolean) map.get(prefix + "halber"));
        s.setHalberRange((Integer) map.get(prefix + "halberRange"));
        if(map.get(prefix + "umgangminute") != null) {
            s.setUmgangminute(Settings.Umgangminute.values()[(Integer) map.get(prefix + "umgangminute")]);


        }

        s.setMinuteHybrid((Boolean) map.get(prefix + "minuteHybrid"));

        if(map.get(prefix + "clockface") != null)
            s.setClockface(Settings.Clockface.values()[(Integer) map.get(prefix + "clockface")]);

        s.setMitternacht((Boolean) map.get(prefix + "mitternacht"));
        s.setMorgens((Boolean) map.get(prefix + "morgens"));
        s.setAmmorgen((Boolean) map.get(prefix + "ammorgen"));
        s.setVormittags((Boolean) map.get(prefix + "vormittags"));
        s.setAmvormittag((Boolean) map.get(prefix + "amvormittag"));
        s.setMittags((Boolean) map.get(prefix + "mittags"));
        s.setAmmittag((Boolean) map.get(prefix + "ammittag"));
        s.setNachmittags((Boolean) map.get(prefix + "nachmittags"));
        s.setAmnachmittag((Boolean) map.get(prefix + "amnachmittag"));
        s.setAbends((Boolean) map.get(prefix + "abends"));
        s.setAmabend((Boolean) map.get(prefix + "amabend"));
        s.setNachts((Boolean) map.get(prefix + "nachts"));
        s.setIndernacht((Boolean) map.get(prefix + "indernacht"));
        s.setInderfrueh((Boolean) map.get(prefix + "inderfrueh"));
        s.setMorgennacht((Boolean) map.get(prefix + "morgennacht"));
        s.setAmmorgennacht((Boolean) map.get(prefix + "ammorgennacht"));
        s.setKurznach((Boolean) map.get(prefix + "kurznach"));

        if(map.get(prefix + "viertel") != null)
            s.setViertel(Settings.Viertel.values()[(Integer) map.get(prefix + "viertel")]);

        s.setFuenfvorviertelacht((Boolean) map.get(prefix + "fuenfvorviertelacht"));
        s.setFuenfnachviertelacht((Boolean) map.get(prefix + "fuenfnachviertelacht"));
        s.setKurznachviertelacht((Boolean) map.get(prefix + "kurznachviertelacht"));
        s.setKurzvorviertelacht((Boolean) map.get(prefix + "kurzvorviertelacht"));

        if(map.get(prefix + "halb") != null)
            s.setHalb(Settings.Halb.values()[(Integer) map.get(prefix + "halb")]);

        s.setFuenfvorhalb((Boolean) map.get(prefix + "fuenfvorhalb"));
        s.setFuenfnachhalb((Boolean) map.get(prefix + "fuenfnachhalb"));
        s.setKurzvorhalb((Boolean) map.get(prefix + "kurzvorhalb"));
        s.setKurznachhalb((Boolean) map.get(prefix + "kurznachhalb"));
        s.setZehnvorhalb((Boolean) map.get(prefix + "zehnvorhalb"));
        s.setZehnnachhalb((Boolean) map.get(prefix + "zehnnachhalb"));
        s.setZwanzignach((Boolean) map.get(prefix + "zwanzignach"));
        s.setZwanzigvor((Boolean) map.get(prefix + "zwanzigvor"));

        if(map.get(prefix + "dreiviertel") != null)
            s.setDreiviertel(Settings.Dreiviertel.values()[(Integer) map.get(prefix + "dreiviertel")]);

        s.setFuenfvordreiviertelacht((Boolean) map.get(prefix + "fuenfvordreiviertelacht"));
        s.setFuenfnachdreiviertelacht((Boolean) map.get(prefix + "fuenfnachdreiviertelacht"));
        s.setKurzvordreiviertelacht((Boolean) map.get(prefix + "kurzvordreiviertelacht"));
        s.setKurznachdreiviertelacht((Boolean) map.get(prefix + "kurznachdreiviertelacht"));
        s.setKurzvor((Boolean) map.get(prefix + "kurzvor"));
        return s;
    }

    public void storeNewConfigNameToDisk(SharedPreferences sp, String selectedClock, String newConfigName) {
        SharedPreferences.Editor editor = sp.edit();

        //save new config name
        String key_for_configs = selectedClock + "~" + Constants.CONFIG;
        Set configs = sp.getStringSet(key_for_configs, new HashSet());
        configs.add(newConfigName);
        editor.putStringSet(key_for_configs, configs);
        editor.commit();
    }


    public void storeSettingsToDisk(SharedPreferences sp, String selectedClock, String newConfigName, Settings s) {
        String prefix = selectedClock + "~" + newConfigName + "~";
        SharedPreferences.Editor editor = sp.edit();


        //save settings for config
        editor.putBoolean(prefix + "umgangssprachlich", s.getUmgangssprachlich());

        editor.putBoolean(prefix + "esist", s.getEsist());
        editor.putBoolean(prefix + "uhr", s.getUhr());
        editor.putBoolean(prefix + "minute", s.getMinute());

        editor.putString(prefix + "customName", s.getCustomName());
        editor.putBoolean(prefix + "flag", s.getFlag());

        editor.putInt(prefix + "flagPattern", Settings.FlagPattern.valueOf(s.getFlagPattern().name()).ordinal());
        editor.putBoolean(prefix + "um", s.getUm());
        editor.putBoolean(prefix + "halber", s.getHalber());
        editor.putInt(prefix + "halberRange", s.getHalberRange());
        editor.putInt(prefix + "umgangminute", Settings.Umgangminute.valueOf(s.getUmgangminute().name()).ordinal());
        editor.putBoolean(prefix + "minuteHybrid", s.getMinuteHybrid());
        editor.putInt(prefix + "clockface", Settings.Clockface.valueOf(s.getClockface().name()).ordinal());

        editor.putBoolean(prefix + "mitternacht", s.getMitternacht());
        editor.putBoolean(prefix + "morgens", s.getMorgens());
        editor.putBoolean(prefix + "ammorgen", s.getAmmorgen());
        editor.putBoolean(prefix + "vormittags", s.getVormittags());
        editor.putBoolean(prefix + "amvormittag", s.getAmvormittag());
        editor.putBoolean(prefix + "mittags", s.getMittags());
        editor.putBoolean(prefix + "ammittag", s.getAmmittag());
        editor.putBoolean(prefix + "nachmittags", s.getNachmittags());
        editor.putBoolean(prefix + "amnachmittag", s.getAmnachmittag());
        editor.putBoolean(prefix + "abends", s.getAbends());
        editor.putBoolean(prefix + "amabend", s.getAmabend());
        editor.putBoolean(prefix + "nachts", s.getNachts());
        editor.putBoolean(prefix + "indernacht", s.getIndernacht());
        editor.putBoolean(prefix + "inderfrueh", s.getInderfrueh());
        editor.putBoolean(prefix + "morgennacht", s.getMorgennacht());
        editor.putBoolean(prefix + "ammorgennacht", s.getAmmorgennacht());
        editor.putBoolean(prefix + "kurznach", s.getKurznach());

        editor.putInt(prefix + "viertel", Settings.Viertel.valueOf(s.getViertel().name()).ordinal());

        editor.putBoolean(prefix + "fuenfvorviertelacht", s.getFuenfvorviertelacht());
        editor.putBoolean(prefix + "fuenfnachviertelacht", s.getFuenfnachviertelacht());
        editor.putBoolean(prefix + "kurznachviertelacht", s.getKurznachviertelacht());
        editor.putBoolean(prefix + "kurzvorviertelacht", s.getKurzvorviertelacht());

        editor.putInt(prefix + "halb", Settings.Halb.valueOf(s.getHalb().name()).ordinal());
        editor.putBoolean(prefix + "fuenfvorhalb", s.getFuenfvorhalb());
        editor.putBoolean(prefix + "fuenfnachhalb", s.getFuenfnachhalb());
        editor.putBoolean(prefix + "kurzvorhalb", s.getKurzvorhalb());
        editor.putBoolean(prefix + "kurznachhalb", s.getKurznachhalb());
        editor.putBoolean(prefix + "zehnvorhalb", s.getZehnvorhalb());
        editor.putBoolean(prefix + "zehnnachhalb", s.getZehnnachhalb());
        editor.putBoolean(prefix + "zwanzignach", s.getZwanzignach());
        editor.putBoolean(prefix + "zwanzigvor", s.getZwanzigvor());


        editor.putInt(prefix + "dreiviertel", Settings.Dreiviertel.valueOf(s.getDreiviertel().name()).ordinal());

        editor.putBoolean(prefix + "fuenfvordreiviertelacht", s.getFuenfvordreiviertelacht());
        editor.putBoolean(prefix + "fuenfnachdreiviertelacht", s.getFuenfnachdreiviertelacht());
        editor.putBoolean(prefix + "kurzvordreiviertelacht", s.getKurzvordreiviertelacht());
        editor.putBoolean(prefix + "kurznachdreiviertelacht", s.getKurznachdreiviertelacht());
        editor.putBoolean(prefix + "kurzvor", s.getKurzvor());

        editor.commit();

    }

    public void updateChosenConfig(SharedPreferences sp, String chosenConfig) {

        SharedPreferences.Editor editor = sp.edit();
        editor.putString(Constants.selectedClock + "~" + Constants.selectedConfig, chosenConfig);
        editor.commit();

    }

    public void storeDeafultSettingsToDisk(SharedPreferences sp) {


        storeNewConfigNameToDisk(sp, Constants.selectedClock, Constants.OFFICIAL_TIME);
        Settings s = new Settings();
        s.setEsist(Boolean.TRUE);
        s.setUhr(Boolean.TRUE);
        s.setMinute(Boolean.TRUE);
        s.setUmgangssprachlich(Boolean.FALSE);
        storeSettingsToDisk(sp, Constants.selectedClock, Constants.OFFICIAL_TIME, s);

        storeNewConfigNameToDisk(sp, Constants.selectedClock, Constants.INFORMAL);
        s = new Settings();
        s.setEsist(Boolean.TRUE);
        s.setUhr(Boolean.TRUE);
        s.setMinute(Boolean.TRUE);
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

        storeSettingsToDisk(sp, Constants.selectedClock, Constants.INFORMAL, s);

        //default on widget enabled to official time
        updateChosenConfig(sp, Constants.OFFICIAL_TIME);

    }

    public void debugthis(SharedPreferences prefs) {

        // BEGIN EXAMPLE
        File myPath = new File(Environment.getDownloadCacheDirectory().toString());
        File myFile = new File(myPath, "MySharedPreferences");

        try {
            FileWriter fw = new FileWriter(myFile);
            PrintWriter pw = new PrintWriter(fw);

            Map<String, ?> prefsMap = prefs.getAll();

            for (Map.Entry<String, ?> entry : prefsMap.entrySet()) {
                pw.println(entry.getKey() + ": " + entry.getValue().toString());
            }

            pw.close();
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
