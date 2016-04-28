package com.wordzoo.uhr.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.germanclock.time.Settings;

import java.util.Map;

/**
 * Created by ich on 28.04.2016.
 */
public class StoreRetrieveGerman extends PersistantSettings {

    public Settings loadSettings(Map<String, ?> map, String clock, String config) {

        // an actual key covers, language~customname~setting
        //get settings for language and customName
        String prefix = clock + "~" + config + "~";
        Settings s = new Settings();
        s.setPrefix(prefix);
        s.setMap(map);
        s.setEsist((Boolean) getBooleanSetting("esist"));
        s.setUhr((Boolean) getBooleanSetting("uhr"));
        s.setMinute((Boolean) getBooleanSetting("minute"));

        s.setCustomName((String) getStringSetting("customName"));
        s.setFlag((Boolean) getBooleanSetting("flag"));
        if(getIntegerSetting("flagPattern") != null)
            s.setFlagPattern(Settings.FlagPattern.values()[(Integer) getIntegerSetting("flagPattern")]);
        s.setUmgangssprachlich((Boolean) getBooleanSetting("umgangssprachlich"));
        s.setUm((Boolean) getBooleanSetting("um"));
        s.setHalber((Boolean) getBooleanSetting("halber"));
        s.setHalberRange((Integer) getIntegerSetting("halberRange"));
        if(getIntegerSetting("umgangminute") != null)
            s.setUmgangminute(Settings.Umgangminute.values()[(Integer) getIntegerSetting("umgangminute")]);
        s.setMinuteHybrid((Boolean) getBooleanSetting("minuteHybrid"));

        if(getIntegerSetting("clockface") != null)
            s.setClockface(Settings.Clockface.values()[(Integer) getIntegerSetting("clockface")]);

        s.setMitternacht((Boolean) getBooleanSetting("mitternacht"));
        s.setMorgens((Boolean) getBooleanSetting("morgens"));
        s.setAmmorgen((Boolean) getBooleanSetting("ammorgen"));
        s.setVormittags((Boolean) getBooleanSetting("vormittags"));
        s.setAmvormittag((Boolean) getBooleanSetting("amvormittag"));
        s.setMittags((Boolean) getBooleanSetting("mittags"));
        s.setAmmittag((Boolean) getBooleanSetting("ammittag"));
        s.setNachmittags((Boolean) getBooleanSetting("nachmittags"));
        s.setAmnachmittag((Boolean) getBooleanSetting("amnachmittag"));
        s.setAbends((Boolean) getBooleanSetting("abends"));
        s.setAmabend((Boolean) getBooleanSetting("amabend"));
        s.setNachts((Boolean) getBooleanSetting("nachts"));
        s.setIndernacht((Boolean) getBooleanSetting("indernacht"));
        s.setInderfrueh((Boolean) getBooleanSetting("inderfrueh"));
        s.setMorgennacht((Boolean) getBooleanSetting("morgennacht"));
        s.setAmmorgennacht((Boolean) getBooleanSetting("ammorgennacht"));
        s.setKurznach((Boolean) getBooleanSetting("kurznach"));

        if(getIntegerSetting("viertel") != null)
            s.setViertel(Settings.Viertel.values()[(Integer) getIntegerSetting("viertel")]);

        s.setFuenfvorviertelacht((Boolean) getBooleanSetting("fuenfvorviertelacht"));
        s.setFuenfnachviertelacht((Boolean) getBooleanSetting("fuenfnachviertelacht"));
        s.setKurznachviertelacht((Boolean) getBooleanSetting("kurznachviertelacht"));
        s.setKurzvorviertelacht((Boolean) getBooleanSetting("kurzvorviertelacht"));

        if(getIntegerSetting("halb") != null)
            s.setHalb(Settings.Halb.values()[(Integer) getIntegerSetting("halb")]);

        s.setFuenfvorhalb((Boolean) getBooleanSetting("fuenfvorhalb"));
        s.setFuenfnachhalb((Boolean) getBooleanSetting("fuenfnachhalb"));
        s.setKurzvorhalb((Boolean) getBooleanSetting("kurzvorhalb"));
        s.setKurznachhalb((Boolean) getBooleanSetting("kurznachhalb"));
        s.setZehnvorhalb((Boolean) getBooleanSetting("zehnvorhalb"));
        s.setZehnnachhalb((Boolean) getBooleanSetting("zehnnachhalb"));
        s.setZwanzignach((Boolean) getBooleanSetting("zwanzignach"));
        s.setZwanzigvor((Boolean) getBooleanSetting("zwanzigvor"));

        if(getIntegerSetting("dreiviertel") != null)
            s.setDreiviertel(Settings.Dreiviertel.values()[(Integer) getIntegerSetting("dreiviertel")]);

        s.setFuenfvordreiviertelacht((Boolean) getBooleanSetting("fuenfvordreiviertelacht"));
        s.setFuenfnachdreiviertelacht((Boolean) getBooleanSetting("fuenfnachdreiviertelacht"));
        s.setKurzvordreiviertelacht((Boolean) getBooleanSetting("kurzvordreiviertelacht"));
        s.setKurznachdreiviertelacht((Boolean) getBooleanSetting("kurznachdreiviertelacht"));
        s.setKurzvor((Boolean) getBooleanSetting("kurzvor"));
        return s;
    }

    public void storeSettingsToDisk(SharedPreferences sp, String selectedClock, String newConfigName, Settings s) {
        String prefix = selectedClock + "~" + newConfigName + "~";
        setPrefix(prefix);
        setMap(sp.getAll());
        setEditor(sp.edit());

        //save new config name
        putConfigName(sp, Constants.selectedClock + "~" + Constants.CONFIG, newConfigName);

        //save settings for onfig
        putBoolean("esist", s.getEsist());
        putBoolean("uhr", s.getUhr());
        putBoolean("minute", s.getMinute());

        putString("customName", s.getCustomName());
        putBoolean("flag", s.getFlag());

        putInteger("flagPattern", Settings.FlagPattern.valueOf(s.getFlagPattern().name()).ordinal());
        putBoolean("umgangssprachlich", s.getUmgangssprachlich());
        putBoolean("um", s.getUm());
        putBoolean("halber", s.getHalber());
        putInteger("halberRange", s.getHalberRange());
        putInteger("umgangminute", Settings.Umgangminute.valueOf(s.getUmgangminute().name()).ordinal());
        putBoolean("minuteHybrid", s.getMinuteHybrid());
        putInteger("clockface", Settings.Clockface.valueOf(s.getClockface().name()).ordinal());

        putBoolean("mitternacht", s.getMitternacht());
        putBoolean("morgens", s.getMorgens());
        putBoolean("ammorgen", s.getAmmorgen());
        putBoolean("vormittags", s.getVormittags());
        putBoolean("amvormittag", s.getAmvormittag());
        putBoolean("mittags", s.getMittags());
        putBoolean("ammittag", s.getAmmittag());
        putBoolean("nachmittags", s.getNachmittags());
        putBoolean("amnachmittag", s.getAmnachmittag());
        putBoolean("abends", s.getAbends());
        putBoolean("amabend", s.getAmabend());
        putBoolean("nachts", s.getNachts());
        putBoolean("indernacht", s.getIndernacht());
        putBoolean("inderfrueh", s.getInderfrueh());
        putBoolean("morgennacht", s.getMorgennacht());
        putBoolean("ammorgennacht", s.getAmmorgennacht());
        putBoolean("kurznach", s.getKurznach());

        putInteger("viertel", Settings.Viertel.valueOf(s.getViertel().name()).ordinal());

        putBoolean("fuenfvorviertelacht", s.getFuenfvorviertelacht());
        putBoolean("fuenfnachviertelacht", s.getFuenfnachviertelacht());
        putBoolean("kurznachviertelacht", s.getKurznachviertelacht());
        putBoolean("kurzvorviertelacht", s.getKurzvorviertelacht());

        putInteger("halb", Settings.Halb.valueOf(s.getHalb().name()).ordinal());
        putBoolean("fuenfvorhalb", s.getFuenfvorhalb());
        putBoolean("fuenfnachhalb", s.getFuenfnachhalb());
        putBoolean("kurzvorhalb", s.getKurzvorhalb());
        putBoolean("kurznachhalb", s.getKurznachhalb());
        putBoolean("zehnvorhalb", s.getZehnvorhalb());
        putBoolean("zehnnachhalb", s.getZehnnachhalb());
        putBoolean("zwanzignach", s.getZwanzignach());
        putBoolean("zwanzigvor", s.getZwanzigvor());


        putInteger("dreiviertel", Settings.Dreiviertel.valueOf(s.getDreiviertel().name()).ordinal());

        putBoolean("fuenfvordreiviertelacht", s.getFuenfvordreiviertelacht());
        putBoolean("fuenfnachdreiviertelacht", s.getFuenfnachdreiviertelacht());
        putBoolean("kurzvordreiviertelacht", s.getKurzvordreiviertelacht());
        putBoolean("kurznachdreiviertelacht", s.getKurznachdreiviertelacht());
        putBoolean("kurzvor", s.getKurzvor());

        getEditor().commit();

    }

    public Settings loadSettingsFromDisk(Context c, String configKey) {
        Settings s = new Settings();

        SharedPreferences preferences = c.getSharedPreferences(Constants.SETTING, 0);
        //(crated when you call SharedPreferences.edit()) and then commit changes (Editor.commit()).
        s = loadSettings(preferences.getAll(), Constants.selectedClock, configKey);
        return s;

    }

    public void storeDeafultSettingsToDisk(SharedPreferences sp) {

        Settings s = new Settings();
        s.setEsist(Boolean.TRUE);
        s.setUhr(Boolean.TRUE);
        s.setMinute(Boolean.TRUE);
        storeSettingsToDisk(sp, Constants.selectedClock, Constants.OFFICIAL_TIME, s);

        s = new Settings();
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
        storeSettingsToDisk(sp, Constants.selectedClock, Constants.INFORMAL, s);

    }

}
