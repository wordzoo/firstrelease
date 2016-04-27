package com.wordzoo.uhr.utils;

import android.widget.RadioButton;

import com.germanclock.time.Settings;
import com.wordzoo.uhr.R;

import java.util.HashMap;
import java.util.Map;

/**
 * customName is the name that people save their settings under
 */
public class PersistantSettings {
    String prefix = "";
    Map map = new HashMap();

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public Object getSetting(String key) {
        return getMap().get(getPrefix() + key);
    }

    public Settings loadSettings(Map <String, ?> map, String clock, String config) {

        // an actual key covers, language~customname~setting
        Settings s = new Settings();

        //get settings for language and customName
        String prefix = clock + "~" + config + "~";
        setPrefix(prefix);
        setMap(map);
        s.setEsist((Boolean) getSetting("esist"));
        s.setUhr((Boolean) getSetting("uhr"));
        s.setMinute((Boolean) getSetting("minute"));

        s.setCustomName((String) getSetting("customName"));
        s.setFlag((Boolean) getSetting("flag"));
        s.setFlagPattern(Settings.FlagPattern.values()[(Integer) getSetting("flagPattern")]);
        s.setUmgangssprachlich((Boolean) getSetting("umgangssprachlich"));
        s.setUm((Boolean) getSetting("um"));
        s.setHalber((Boolean) getSetting("halber"));
        s.setHalberRange((Integer) getSetting("halberRange"));
        s.setUmgangminute(Settings.Umgangminute.values()[(Integer) getSetting("umgangminute")]);
        s.setMinuteHybrid((Boolean) getSetting("minuteHybrid"));
        s.setClockface(Settings.Clockface.values()[(Integer) getSetting("clockface")]);

        s.setMitternacht((Boolean) getSetting("mitternacht"));
        s.setMorgens((Boolean) getSetting("morgens"));
        s.setAmmorgen((Boolean) getSetting("ammorgen"));
        s.setVormittags((Boolean) getSetting("vormittags"));
        s.setAmvormittag((Boolean) getSetting("amvormittag"));
        s.setMittags((Boolean) getSetting("mittags"));
        s.setAmmittag((Boolean) getSetting("ammittag"));
        s.setNachmittags((Boolean) getSetting("nachmittags"));
        s.setAmnachmittag((Boolean) getSetting("amnachmittag"));
        s.setAbends((Boolean) getSetting("abends"));
        s.setAmabend((Boolean) getSetting("amabend"));
        s.setNachts((Boolean) getSetting("nachts"));
        s.setIndernacht((Boolean) getSetting("indernacht"));
        s.setInderfrueh((Boolean) getSetting("inderfrueh"));
        s.setMorgennacht((Boolean) getSetting("morgennacht"));
        s.setAmmorgennacht((Boolean) getSetting("ammorgennacht"));
        s.setKurznach((Boolean) getSetting("kurznach"));

        s.setViertel(Settings.Viertel.values()[(Integer) getSetting("viertel")]);

        s.setFuenfvorviertelacht((Boolean) getSetting("fuenfvorviertelacht"));
        s.setFuenfnachviertelacht((Boolean) getSetting("fuenfnachviertelacht"));
        s.setKurznachviertelacht((Boolean) getSetting("kurznachviertelacht"));
        s.setKurzvorviertelacht((Boolean) getSetting("kurzvorviertelacht"));

        s.setHalb(Settings.Halb.values()[(Integer) getSetting("halb")]);
        s.setFuenfvorhalb((Boolean) getSetting("fuenfvorhalb"));
        s.setFuenfnachhalb((Boolean) getSetting("fuenfnachhalb"));
        s.setKurzvorhalb((Boolean) getSetting("kurzvorhalb"));
        s.setKurznachhalb((Boolean) getSetting("kurznachhalb"));
        s.setZehnvorhalb((Boolean) getSetting("zehnvorhalb"));
        s.setZehnnachhalb((Boolean) getSetting("zehnnachhalb"));
        s.setZwanzignach((Boolean) getSetting("zwanzignach"));
        s.setZwanzigvor((Boolean) getSetting("zwanzigvor"));


        s.setDreiviertel(Settings.Dreiviertel.values()[(Integer) getSetting("dreiviertel")]);

        s.setFuenfvordreiviertelacht((Boolean) getSetting("fuenfvordreiviertelacht"));
        s.setFuenfnachdreiviertelacht((Boolean) getSetting("fuenfnachdreiviertelacht"));
        s.setKurzvordreiviertelacht((Boolean) getSetting("kurzvordreiviertelacht"));
        s.setKurznachdreiviertelacht((Boolean) getSetting("kurznachdreiviertelacht"));
        s.setKurzvor((Boolean) getSetting("kurzvor"));
        return s;
    }

    public void createUpdateSettings(String selectedClock, String newConfigName, this) {

    }
}
