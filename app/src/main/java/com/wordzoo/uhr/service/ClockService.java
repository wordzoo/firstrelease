package com.wordzoo.uhr.service;

import com.germanclock.time.Settings;
import com.wordzoo.uhr.GermanClock;
import com.wordzoo.uhr.utils.Constants;

import android.app.Service;
import android.appwidget.AppWidgetProvider;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.widget.Toast;

import java.util.HashMap;
import java.util.HashSet;

public class ClockService extends Service
{
	protected AppWidgetProvider receiver;

	public ClockService()
	{
	}

	@Override
	public IBinder onBind( Intent intent )
	{
		return null;
	}

	@Override
	public void onStart( Intent intent, int startId )
	{
		super.onStart(intent, startId);
	}

	@Override
	public void onCreate()
	{

		super.onCreate();
		createReceiver();
		registerReceiver(receiver, new IntentFilter(Intent.ACTION_TIME_TICK));
	}

	protected void createReceiver()
	{
		receiver = new GermanClock();
	}



	@Override
	public void onDestroy()
	{
		unregisterReceiver();
		super.onDestroy();
	}

	protected void unregisterReceiver()
	{
		unregisterReceiver( receiver );
	}
}
