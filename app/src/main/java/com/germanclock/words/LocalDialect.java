package com.germanclock.words;

import android.content.Context;

import com.germanclock.time.Pieces;
import com.germanclock.time.Settings;
import com.germanclock.time.ViennaSettings;

public interface LocalDialect {

	String getVerbalTime(Pieces p, Settings s, Context context);

	String getBegin(Pieces p, ViennaSettings s);
	String getHour(Pieces p, ViennaSettings s, Boolean plusHour);
	String[] getMinute(Pieces p, ViennaSettings s, Context context);

}