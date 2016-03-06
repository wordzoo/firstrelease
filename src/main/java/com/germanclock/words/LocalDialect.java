package com.germanclock.words;

import com.germanclock.time.Pieces;
import com.germanclock.time.Settings;
import com.germanclock.time.ViennaSettings;

public interface LocalDialect {

	String getVerbalTime(Pieces p, Settings s);

	String getBegin(Pieces p, ViennaSettings s);
	String getHour(Pieces p, ViennaSettings s);
	String[] getMinute(Pieces p, ViennaSettings s);

}