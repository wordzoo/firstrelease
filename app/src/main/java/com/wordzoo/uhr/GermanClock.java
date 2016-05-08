package com.wordzoo.uhr;

import com.germanclock.time.Pieces;
import com.germanclock.time.Settings;
import com.germanclock.words.TimeInWords;
import com.wordzoo.uhr.service.ClockService;
import com.wordzoo.uhr.utils.StoreRetrieveGerman;
import com.wordzoo.uhr.utils.StringUtils;
import com.wordzoo.uhr.utils.Constants;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

public class GermanClock extends AppWidgetProvider implements Serializable
{

//Toast.makeText(context,
	//		"settime: Umgangminute: " + settings.getUmgangminute(), Toast.LENGTH_SHORT).show();



	@Override
	public void onDeleted( Context context, int[] appWidgetIds )
	{

		super.onDeleted(context, appWidgetIds);
	}

	@Override
	public void onDisabled( Context context )
	{

		stopClockService(context);
		super.onDisabled(context);
	}

	protected void startClockService( Context context )
	{
        Intent serviceIntent = new Intent(context, ClockService.class);
        context.startService(serviceIntent);
	}

	protected void stopClockService(Context context) {
        Intent serviceIntent = new Intent(context, ClockService.class);
        context.stopService(serviceIntent);
	}



	@Override
	public void onEnabled( Context context )
	{
        super.onEnabled(context);

		String chosenConfig = "";
		SharedPreferences sp = context.getSharedPreferences(Constants.SETTING, 0 | Context.MODE_MULTI_PROCESS);
		chosenConfig = sp.getString(Constants.selectedClock + "~" + Constants.selectedConfig, null);
		if(chosenConfig.equals(""))
			new StoreRetrieveGerman().storeDeafultSettingsToDisk(context.getSharedPreferences(Constants.SETTING, 0), context);

		startClockService(context);

        ComponentName thisWidget = new ComponentName(context.getPackageName(), GermanClock.class.getName());
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.german_clock);

        final Intent configIntent = new Intent(context, ActivitySettings.class);
        PendingIntent configPendingIntent = PendingIntent.getActivity(context, 0, configIntent, 0);
        remoteViews.setOnClickPendingIntent(R.id.textView, configPendingIntent);

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        for (int widgetId : appWidgetManager.getAppWidgetIds(thisWidget))
            appWidgetManager.updateAppWidget(widgetId, remoteViews);

	}


    @Override
    public void onReceive( Context context, Intent intent) {

        super.onReceive(context, intent);

        handleIntent(context, intent);
    }

    protected void handleIntent( Context context, Intent intent) {


		if( intent.getAction().equals( Intent.ACTION_TIME_TICK ) )
        {
			setTime(context);
            AppWidgetManager manager = AppWidgetManager.getInstance( context );
            int appWidgetIds[] = manager.getAppWidgetIds( new ComponentName( context, GermanClock.class.getName() ) );
            onUpdate(context, manager, appWidgetIds);

		}
    }
	@Override
	public void onUpdate( Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds )
	{
		super.onUpdate(context, appWidgetManager, appWidgetIds);
		setTime(context);

		//need to reregister this because we can loose it on restart of phone
		RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.german_clock);
		final Intent configIntent = new Intent(context, ActivitySettings.class);
		PendingIntent configPendingIntent = PendingIntent.getActivity(context, 0, configIntent, 0);
		remoteViews.setOnClickPendingIntent(R.id.textView, configPendingIntent);
		ComponentName thisWidget = new ComponentName(context.getPackageName(), GermanClock.class.getName());

		for (int widgetId : appWidgetManager.getAppWidgetIds(thisWidget))
			appWidgetManager.updateAppWidget(widgetId, remoteViews);

	}



	/**
	 * @return
	 */
	protected static ComponentName getComponentName()
	{
		ComponentName widgetName = new ComponentName( "com.wordzoo.uhr", "com.wordzoo.uhr.GermanClock" );
		return widgetName;

	}





	public void setTime(Context context) {
		//PreferenceManager.getDefaultSharedPreferences();
		SharedPreferences sp = context.getSharedPreferences(Constants.SETTING, 0 | Context.MODE_MULTI_PROCESS);
		String chosenConfig = sp.getString(Constants.selectedClock + "~" + Constants.selectedConfig, null);


//Toast.makeText(context,
//				"chosen: " + chosenConfig, Toast.LENGTH_SHORT).show();


		if(chosenConfig == null)
			chosenConfig = Constants.OFFICIAL_TIME; //default

		Settings settings =  new StoreRetrieveGerman().loadSettingsFromDisk(sp, Constants.selectedClock, chosenConfig, context);

		TimeInWords tiw = new TimeInWords(context);

		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("H:mm");
		Pieces p = new Pieces(sdf.format(d));


		String time = tiw.getTimeAsSentance(p, settings);

		RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.german_clock);
		remoteViews.setTextViewText(R.id.textView, time);

		int drawableid = 0;

		if(settings.getUmgangssprachlich()
		&& settings.getUmgangminute().equals(Settings.Umgangminute.minutebar)
				) {
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
			remoteViews.setTextViewCompoundDrawables(R.id.textView, 0, 0, drawableid, 0);
		else
			remoteViews.setTextViewCompoundDrawables(R.id.textView, 0, 0, 0, 0);


		AppWidgetManager appManager = AppWidgetManager.getInstance(context);
		ComponentName thisWidget = new ComponentName(context.getPackageName(), GermanClock.class.getName());
		appManager.updateAppWidget(thisWidget, remoteViews);
	}



}
