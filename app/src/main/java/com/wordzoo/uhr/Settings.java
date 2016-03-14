package com.wordzoo.uhr;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;

import java.util.ArrayList;

public class Settings extends Activity {

    private static final String PREFS_NAME
            = "io.appium.android.apis.appwidget.ExampleAppWidgetProvider";

    private static final String PREF_PREFIX_KEY = "prefix_";

    EditText mEditText;
    int mAppWidgetId;

    public Settings() {
        super();
        mAppWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;
    }

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.settings);
        mAppWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;

        // Set the widget id from the intent.  (This never happens.)
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            mAppWidgetId = extras.getInt(
                    AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
            // If they gave us an intent without the widget id, just bail.
            if (mAppWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
                // Uncomment this once the widget ID is being set.
//                finish();
            }
        }
        mEditText = (EditText) findViewById(R.id.app_widget_prefix_text);
    }

    // When the Activity starts, initialize the text.
    @Override
    public void onResume() {
        super.onResume();
        String oldPrefValue = loadTitlePref(this, mAppWidgetId);
        mEditText.setText(oldPrefValue);
    }

    // When the Activity ends, save the text.
    @Override
    public void onPause() {
        super.onPause();
        String newPrefValue = mEditText.getText().toString();
        saveTitlePref(newPrefValue);
    }

    @Override
    public void onDestroy() {
        // Push widget update to surface with newly set prefix
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        String newPrefValue = mEditText.getText().toString();
        ExampleAppWidgetProvider.updateAppWidget(
                this, appWidgetManager, mAppWidgetId, newPrefValue);

        // Make sure we pass back the original appWidgetId
        Intent resultValue = new Intent();
        resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId);
        setResult(RESULT_OK, resultValue);
        super.onDestroy();
    }

    // Write the prefix to the SharedPreferences object for this widget
    void saveTitlePref(String text) {
        SharedPreferences.Editor prefs = getSharedPreferences(PREFS_NAME, 0).edit();
        prefs.putString(PREF_PREFIX_KEY + mAppWidgetId, text);
        prefs.apply();
    }

    // Read the prefix from the SharedPreferences object for this widget.
    // If there is no preference saved, get the default from a resource
    static String loadTitlePref(Context context, int widgetId) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        String prefix = prefs.getString(PREF_PREFIX_KEY + widgetId, null);
        if (prefix != null) {
            return prefix;
        }
        return context.getString(R.string.appwidget_prefix_default);
    }

    static void deleteTitlePref(Context context, int appWidgetId) {
    }

    static void loadAllTitlePrefs(
            Context context, ArrayList<Integer> appWidgetIds, ArrayList<String> texts) {
    }
}