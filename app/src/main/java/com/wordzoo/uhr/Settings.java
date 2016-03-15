package com.wordzoo.uhr;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;



public class Settings extends Activity implements View.OnClickListener {

    private SharedPreferences clockPrefs;

    public void onClick(View v) {
        //boilerplate
        //remoteViews.setViewVisibility(pickedOption, View.VISIBLE);
        SharedPreferences.Editor custClockEdit = clockPrefs.edit();
        custClockEdit.putInt("coolOption", 1);
        custClockEdit.commit();
        finish();
    }
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        clockPrefs = getSharedPreferences("GermanClockPrefs", 0);
    }
}