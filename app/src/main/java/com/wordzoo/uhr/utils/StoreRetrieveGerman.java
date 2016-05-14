package com.wordzoo.uhr.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.widget.RadioGroup;

import com.germanclock.time.Settings;
import com.wordzoo.uhr.R;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by ich on 28.04.2016.
 */
public class StoreRetrieveGerman  {

    private Boolean myGetBoolean(Map map, String key) {
        if(map.get(key) != null)
            return (Boolean)map.get(key);
        else
            return Boolean.FALSE;
    }

    private String myGetString(Map map, String key) {
        if(map.get(key) != null)
            return (String)map.get(key);
        else
            return "";
    }

    private Integer myGetInteger(Map map, String key) {
        if(map.get(key) != null)
            return (Integer)map.get(key);
        else
            return 0;
    }

    public Settings loadSettingsFromDisk(SharedPreferences sp, String selectedClock, String configName, Context c) {
        Settings s = new Settings();

        Map<String, ?> map = sp.getAll();

        // an actual key covers, language~customname~setting
        //get settings for language and customName
        String prefix = selectedClock + "~" + configName + "~";

        s.setUmgangssprachlich(myGetBoolean(map, prefix + "umgangssprachlich")); //all variable settings are umgang/not official



        s.setEsist(myGetBoolean(map, prefix + "esist"));
        s.setUhr(myGetBoolean(map, prefix + "uhr"));
        s.setMinute(myGetBoolean(map, prefix + "minute"));

        s.setCustomName(myGetString(map, prefix + "customName"));
        s.setFlag(myGetBoolean(map, prefix + "flag"));
        if(map.get(prefix + "flagPattern") != null)
            s.setFlagPattern(Settings.FlagPattern.values()[(Integer) map.get(prefix + "flagPattern")]);
        s.setUm(myGetBoolean(map, prefix + "um"));

        if(map.get(prefix + "umgangminute") != null) {
            s.setUmgangminute(Settings.Umgangminute.values()[(Integer) map.get(prefix + "umgangminute")]);


        }

        s.setMinuteHybrid(myGetBoolean(map, prefix + "minuteHybrid"));

        if(map.get(prefix + "clockface") != null)
            s.setClockface(Settings.Clockface.values()[(Integer) map.get(prefix + "clockface")]);

        s.setMitternacht(myGetBoolean(map, prefix + "mitternacht"));
        s.setMorgens(myGetBoolean(map, prefix + "morgens"));
        s.setAmmorgen(myGetBoolean(map, prefix + "ammorgen"));
        s.setVormittags(myGetBoolean(map, prefix + "vormittags"));
        s.setAmvormittag(myGetBoolean(map, prefix + "amvormittag"));
        s.setMittags(myGetBoolean(map, prefix + "mittags"));
        s.setAmmittag(myGetBoolean(map, prefix + "ammittag"));
        s.setNachmittags(myGetBoolean(map, prefix + "nachmittags"));
        s.setAmnachmittag(myGetBoolean(map, prefix + "amnachmittag"));
        s.setAbends(myGetBoolean(map, prefix + "abends"));
        s.setAmabend(myGetBoolean(map, prefix + "amabend"));
        s.setNachts(myGetBoolean(map, prefix + "nachts"));
        s.setIndernacht(myGetBoolean(map, prefix + "indernacht"));
        s.setInderfrueh(myGetBoolean(map, prefix + "inderfrueh"));
        s.setMorgennacht(myGetBoolean(map, prefix + "morgennacht"));
        s.setAmmorgennacht(myGetBoolean(map, prefix + "ammorgennacht"));
        s.setKurznach(myGetBoolean(map, prefix + "kurznach"));

        //Integer index = (Integer) map.get(prefix + "viertel");
        //if(index != null)
        //    String val = Settings.Viertel.values()[index].name();

        //Toast.makeText(c,
          //      "viertel index: " + index
         //+ ", value: " + val, Toast.LENGTH_SHORT).show();


        if(map.get(prefix + "viertel") != null)
            s.setViertel(Settings.Viertel.values()[(Integer) map.get(prefix + "viertel")]);

        s.setFuenfvorviertelacht(myGetBoolean(map, prefix + "fuenfvorviertelacht"));
        s.setFuenfnachviertelacht(myGetBoolean(map, prefix + "fuenfnachviertelacht"));
        s.setKurznachviertelacht(myGetBoolean(map, prefix + "kurznachviertelacht"));
        s.setKurzvorviertelacht(myGetBoolean(map, prefix + "kurzvorviertelacht"));

        if(map.get(prefix + "halb") != null)
            s.setHalb(Settings.Halb.values()[(Integer) map.get(prefix + "halb")]);

        s.setFuenfvorhalb(myGetBoolean(map, prefix + "fuenfvorhalb"));
        s.setFuenfnachhalb(myGetBoolean(map, prefix + "fuenfnachhalb"));
        s.setKurzvorhalb(myGetBoolean(map, prefix + "kurzvorhalb"));
        s.setKurznachhalb(myGetBoolean(map, prefix + "kurznachhalb"));
        s.setZehnvorhalb(myGetBoolean(map, prefix + "zehnvorhalb"));
        s.setZehnnachhalb(myGetBoolean(map, prefix + "zehnnachhalb"));
        s.setZwanzignach(myGetBoolean(map, prefix + "zwanzignach"));
        s.setZwanzigvor(myGetBoolean(map, prefix + "zwanzigvor"));

        if(map.get(prefix + "dreiviertel") != null)
            s.setDreiviertel(Settings.Dreiviertel.values()[(Integer) map.get(prefix + "dreiviertel")]);

        s.setFuenfvordreiviertelacht(myGetBoolean(map, prefix + "fuenfvordreiviertelacht"));
        s.setFuenfnachdreiviertelacht(myGetBoolean(map, prefix + "fuenfnachdreiviertelacht"));
        s.setKurzvordreiviertelacht(myGetBoolean(map, prefix + "kurzvordreiviertelacht"));
        s.setKurznachdreiviertelacht(myGetBoolean(map, prefix + "kurznachdreiviertelacht"));
        s.setKurzvor(myGetBoolean(map, prefix + "kurzvor"));
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


    public void storeSettingsToDisk(SharedPreferences sp, String selectedClock, String newConfigName, Settings s, Context c) {
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

        Integer index = Settings.Viertel.valueOf(s.getViertel().name()).ordinal();
        String val = s.getViertel().name();

        //Toast.makeText(c,
          //      "viertel index: " + index
            //            + ", value: " + val, Toast.LENGTH_SHORT).show();

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

    public void updateChosenConfig(SharedPreferences sp, String chosenConfig, Context context) {

        SharedPreferences.Editor editor = sp.edit();
        //Toast.makeText(context,
        //        "before update: " + chosenConfig, Toast.LENGTH_SHORT).show();
        editor.putString(Constants.selectedClock + "~" + Constants.selectedConfig, chosenConfig);

        editor.commit();
        //Toast.makeText(context,
        //        "after update: " + sp.getString(Constants.selectedClock + "~" + Constants.selectedConfig, ""), Toast.LENGTH_SHORT).show();

    }

    public void storeDeafultSettingsToDisk(SharedPreferences sp, Context context) {


        storeNewConfigNameToDisk(sp, Constants.selectedClock, Constants.OFFICIAL_TIME);
        Settings s = new Settings();
        s.setEsist(Boolean.TRUE);
        s.setUhr(Boolean.TRUE);
        s.setMinute(Boolean.TRUE);
        s.setUmgangssprachlich(Boolean.FALSE);
        storeSettingsToDisk(sp, Constants.selectedClock, Constants.OFFICIAL_TIME, s, context);

        storeNewConfigNameToDisk(sp, Constants.selectedClock, Constants.INFORMAL_TIME);
        s = new Settings();
        s.setEsist(Boolean.TRUE);
        s.setUhr(Boolean.TRUE);
        s.setMinute(Boolean.TRUE);
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

        storeSettingsToDisk(sp, Constants.selectedClock, Constants.INFORMAL_TIME, s, context);

        //default on widget enabled to official time
        updateChosenConfig(sp, Constants.OFFICIAL_TIME, context);

    }

    public Boolean configExists(SharedPreferences sp, String configName) {
        //get all configs
        Set set = sp.getStringSet(Constants.selectedClock + "~" + Constants.CONFIG, new HashSet());
        Iterator i = set.iterator();
        while(i.hasNext()) {
            if (configName.equals((String) i.next()))
                return Boolean.TRUE;
        }
        return Boolean.FALSE;
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
