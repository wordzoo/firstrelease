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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RemoteViews;
import android.widget.Spinner;
import android.widget.Toast;
import com.germanclock.time.Settings;
public class ActivitySettings extends Activity implements OnClickListener, AdapterView.OnItemSelectedListener {


    //user prefs
    private SharedPreferences clockPrefs;

    private RadioGroup def;
    private RadioButton defButton;
    private Button done;

    Context context;

    public void onCreate(Bundle savedInstanceState) {
        /*
        Boolean default = this.getResources().getBoolean(R.integer.officallong);
        if(officiallong)
            view.setChecked(default);

*/

        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        addDoneButtonListener();

        //user prefs
        clockPrefs = getSharedPreferences("CustomClockPrefs", 0);

        this.context = this;

        spinner = (Spinner)findViewById(R.id.dreiviertel);
        ArrayAdapter<String>adapter = new ArrayAdapter<String>(ActivitySettings.this,
                android.R.layout.simple_spinner_item, new String[]{"none","dreiviertel (next hour)","viertel (vor)", "fünfzehn"});

        adapter.setDropDownViewResource(R.layout.settings);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

    }
    private Spinner spinner;

    public void addDoneButtonListener() {

        def = (RadioGroup) findViewById(R.id.def);
        done = (Button) findViewById(R.id.done);

        done.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                // get selected radio button from radioGroup
                int selectedId = def.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                defButton = (RadioButton) findViewById(selectedId);

                Toast.makeText(ActivitySettings.this,
                        defButton.getText(), Toast.LENGTH_SHORT).show();

                Settings settings = new Settings();
                settings.parse(defButton);
                Settings s = loadSettings(defButton.getText() + "");



                AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
                RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.german_clock);
                GermanClock gc = new GermanClock();
                gc.setSettings(s);
                remoteViews.setTextViewText(R.id.textView, gc.getVerbalTime(context));

                ComponentName thisWidget = new ComponentName(context, GermanClock.class);
                appWidgetManager.updateAppWidget(thisWidget, remoteViews);
                finish();

            }
            


        });



    }

    public Settings loadSettings(String def) {
        Settings s = new Settings();


        Toast.makeText(ActivitySettings.this,
                "lookn for..." + def + "...", Toast.LENGTH_SHORT).show();

        // Is the button now checked?
        if(def.equals("offiziell lang")) {

            s.setEsist(Boolean.TRUE);
            s.setUhr(Boolean.TRUE);
            s.setMinute(Boolean.TRUE);

        }
        else if(def.equals("offiziell kurz")) {

            //

        }
        else if(def.equals("umgangssprachlich")) {

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

        }
        else if(def.equals(Settings.Default.custom)) {
            //comming soon.....
        }

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

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.uhr:
                if (checked)
                    ;
                // Put some meat on the sandwich
                else
                    ;
                // Remove the meat
                break;
            case R.id.esist:
                if (checked)
                    ;
                // Cheese me
                else
                    ;
                // I'm lactose intolerant
                break;
            // TODO: Veggie sandwich
        }
    }
}
/*package com.wordzoo.uhr;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

public class CleanClockWidgetConfigure extends Activity
{
	protected static final String PREFS_NAME = "it.sephiroth.apps.widget.cleanclock";
	protected static final String PREF_PREFIX_KEY = "prefix42_";
	protected int mAppWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;

	protected CheckBox mUse24Checkbox;
	protected CheckBox mShadowCheckbox;

	public CleanClockWidgetConfigure()
	{
		super();
	}

	@Override
	public void onCreate( Bundle icicle )
	{
		super.onCreate( icicle );

		setResult( RESULT_CANCELED );
		setContentView( R.layout.configure );

		mUse24Checkbox = (CheckBox) findViewById( R.id.checkbox_24 );
		mShadowCheckbox = (CheckBox) findViewById( R.id.checkbox_shadow );

		findViewById( R.id.save_button ).setOnClickListener( mOnClickListener );

		Intent intent = getIntent();
		Bundle extras = intent.getExtras();

		if( extras != null )
		{
			mAppWidgetId = extras.getInt( AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID );
		}

		if( mAppWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID )
		{
			finish();
		}
	}

	protected View.OnClickListener mOnClickListener = new View.OnClickListener()
	{

		public void onClick( View v )
		{
			final Context context = getContext();
			boolean use24 = mUse24Checkbox.isChecked();
			boolean useShadow = mShadowCheckbox.isChecked();

			savePrefs( context, use24, useShadow );

			AppWidgetManager appWidgetManager = AppWidgetManager.getInstance( context );
			Constants.log( "Configure completed!" );
			updateAppWidget( context, use24, useShadow, appWidgetManager );

			Intent resultValue = new Intent();
			resultValue.putExtra( AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId );
			setResult( RESULT_OK, resultValue );
			finish();
		}
	};

	protected Context getContext() {
		Constants.log( this.getClass().getSimpleName() + "::getContext" );
		return this;
	}

	protected void savePrefs( final Context context, boolean use24, boolean useShadow )
	{
		Constants.log( getClass().getSimpleName() + "::savePrefs" );
		savePref( context, mAppWidgetId, Constants.PREF_24HOUR, use24 );
		savePref( context, mAppWidgetId, Constants.PREF_SHADOW, useShadow  );
	}

	protected void updateAppWidget( final Context context, boolean use24, boolean useShadow, AppWidgetManager appWidgetManager )
	{
		Constants.log( getClass().getSimpleName() + "::updateAppWidget" );
		ClockAppWidgetProvider.updateAppWidget( context, appWidgetManager, mAppWidgetId, use24, useShadow );
	}

	static void savePref( Context context, int appWidgetId, String name, Boolean value )
	{
		savePref( context, appWidgetId, PREF_PREFIX_KEY, name, value );
	}

	static boolean loadPref( Context context, int appWidgetId, String name )
	{
		return loadPref( context, appWidgetId, PREF_PREFIX_KEY, name );
	}

	protected static void savePref( Context context, int appWidgetId, String prefix, String name, Boolean value )
	{
		Constants.log( "savePrev. prefix=" + prefix + ", appWidgetId=" + appWidgetId + ", name=" + name );
		SharedPreferences.Editor prefs = context.getSharedPreferences( PREFS_NAME, 0 ).edit();
		prefs.putBoolean( prefix + appWidgetId + "-" + name, value );
		prefs.commit();
	}

	protected static boolean loadPref( Context context, int appWidgetId, String prefix, String name )
	{
		Constants.log( "loadPrev. prefix=" + prefix + ", appWidgetId=" + appWidgetId + ", name=" + name );
		SharedPreferences prefs = context.getSharedPreferences( PREFS_NAME, 0 );
		boolean value = prefs.getBoolean( prefix + appWidgetId + "-" + name, false );
		return value;
	}

}
*/