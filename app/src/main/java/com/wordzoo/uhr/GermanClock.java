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
import android.view.View;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

public class GermanClock extends AppWidgetProvider implements Serializable
{


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

		new StoreRetrieveGerman().storeDeafultSettingsToDisk(context.getSharedPreferences(Constants.SETTING, 0));
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
            AppWidgetManager manager = AppWidgetManager.getInstance( context );
            int appWidgetIds[] = manager.getAppWidgetIds( new ComponentName( context, GermanClock.class.getName() ) );
            onUpdate( context, manager, appWidgetIds );
        }
    }
	@Override
	public void onUpdate( Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds )
	{

		final int N = appWidgetIds.length;
		for( int i = 0; i < N; i++ )
		{
			int appWidgetId = appWidgetIds[i];


			setTime(context);
		}
	}



	/**
	 * @return
	 */
	protected static ComponentName getComponentName()
	{
		ComponentName alarmName = new ComponentName( "com.wordzoo.uhr", "com.wordzoo.uhr.GermanClock" );
		return alarmName;

	}





	public void setTime(Context context) {
		SharedPreferences sp = context.getSharedPreferences(Constants.SETTING, 0);
		String chosenConfig = sp.getString(Constants.selectedClock + "~" + Constants.selectedConfig, null);

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

		if(settings.getUmgangminute().equals(Settings.Umgangminute.minutebar)
				&& p.getRemainderMinutes() > 0) {
			switch (p.getRemainderMinutes()){
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
