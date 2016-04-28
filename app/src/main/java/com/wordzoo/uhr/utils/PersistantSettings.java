package com.wordzoo.uhr.utils;

import android.content.SharedPreferences;
import android.widget.RadioButton;

import com.germanclock.time.Settings;
import com.wordzoo.uhr.R;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * customName is the name that people save their settings under
 */
public class PersistantSettings {
    String prefix = "";

    //map for getting settings
    Map map = new HashMap();

    //editor for writing settings
    SharedPreferences.Editor editor;

    public SharedPreferences.Editor getEditor() {
        return editor;
    }

    public void setEditor(SharedPreferences.Editor editor) {
        this.editor = editor;
    }

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

    public void putInteger(String key, Integer value) {
        getEditor().putInt(getPrefix() + key, value);
    }

    public void putBoolean(String key, Boolean value) {
        getEditor().putBoolean(getPrefix() + key, value);
    }

    public void putString(String key, String value) {
        getEditor().putString(getPrefix() + key, value);
    }

    public void putConfigName(SharedPreferences sharedPreference, String key, String value) {
        Set configs = sharedPreference.getStringSet(key, new HashSet());
        configs.add(value);
        getEditor().putStringSet(key, configs);
    }

}
