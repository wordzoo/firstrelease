package com.wordzoo.uhr;

import com.germanclock.time.Pieces;
import com.germanclock.time.Settings;
import com.germanclock.words.TimeInWords;
import com.wordzoo.uhr.service.ClockService;
import com.wordzoo.uhr.utils.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.Toast;

public class GermanClock extends AppWidgetProvider
{

	private Settings settings = null;


	public Settings getSettings() {
		return settings;
	}

	public void setSettings(Settings settings) {
		this.settings = settings;
	}


	@Override
	public void onDeleted( Context context, int[] appWidgetIds )
	{
		Constants.log("GermanClock::onDeleted");
		super.onDeleted(context, appWidgetIds);
	}

	@Override
	public void onDisabled( Context context )
	{
		Constants.log( "GermanClock::onDisabled" );
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
		Constants.log("GermanClock::onEnabled");
		startClockService(context);

        ComponentName thisWidget = new ComponentName(context.getPackageName(), GermanClock.class.getName());
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.german_clock);

        Intent configIntent = new Intent(context, ActivitySettings.class);
        PendingIntent configPendingIntent = PendingIntent.getActivity(context, 0, configIntent, 0);
        remoteViews.setOnClickPendingIntent(R.id.textView, configPendingIntent);

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        for (int widgetId : appWidgetManager.getAppWidgetIds(thisWidget))
            appWidgetManager.updateAppWidget(widgetId, remoteViews);

	}


    @Override
    public void onReceive( Context context, Intent intent) {
        Constants.log(getClass().getSimpleName() + "::onReceive: " + intent.getAction() );
        super.onReceive(context, intent);
        handleIntent( context, intent );
    }

    protected void handleIntent( Context context, Intent intent) {
        Constants.log( getClass().getSimpleName() + "::handleIntent" );

        if( intent.getAction().equals( Intent.ACTION_TIME_TICK ) )
        {
            AppWidgetManager manager = AppWidgetManager.getInstance( context );
            int appWidgetIds[] = manager.getAppWidgetIds( new ComponentName( context, GermanClock.class.getName() ) );
            onUpdate( context, manager, appWidgetIds );
        }
    }
	@Override
	public void onUpdate( Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds )
	{
		Constants.log( getClass().getSimpleName() + "::onUpdate. " + appWidgetIds.length );

		final int N = appWidgetIds.length;
		for( int i = 0; i < N; i++ )
		{
			int appWidgetId = appWidgetIds[i];
			Constants.log( "onUpdate() appWidgetId: " + appWidgetId );

			setTime(context);
		}
	}



	/**
	 * @return
	 */
	protected static ComponentName getComponentName()
	{
		ComponentName alarmName = new ComponentName( "com.android.deskclock", "com.android.deskclock.DeskClock" );

		if( android.os.Build.PRODUCT.equals( "GT-I9000" ) )
			alarmName = new ComponentName( "com.sec.android.app.clockpackage", "com.sec.android.app.clockpackage.ClockPackage" );
		else if( android.os.Build.PRODUCT.equals( "htc_bravo" ) )
			alarmName = new ComponentName( "com.htc.android.worldclock", "com.htc.android.worldclock.WorldClockTabControl" );
		return alarmName;
	}

	public String getVerbalTime(Context c) {

		if(getSettings() == null) {
			//official default style
			settings = new Settings();
			settings.setEsist(Boolean.TRUE);
			settings.setUhr(Boolean.TRUE);
			settings.setMinute(Boolean.TRUE);

			setSettings(settings);
		}

		TimeInWords tiw = new TimeInWords(c);

		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("H:mm");
		Pieces p = new Pieces(sdf.format(d));

		return tiw.getTimeAsSentance(p, getSettings());
	}



	public void setTime(Context context) {
		String time = getVerbalTime(context);
		RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.german_clock);
		remoteViews.setTextViewText(R.id.textView, time);
		Toast.makeText(context, "setTime() setting time to: " +time, Toast.LENGTH_SHORT).show();
		AppWidgetManager appManager = AppWidgetManager.getInstance(context);
		ComponentName thisWidget = new ComponentName(context.getPackageName(), GermanClock.class.getName());
		appManager.updateAppWidget(thisWidget, remoteViews);
	}
}
