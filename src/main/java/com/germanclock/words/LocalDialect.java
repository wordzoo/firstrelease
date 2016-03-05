package com.germanclock.words;

import com.germanclock.time.Pieces;
import com.germanclock.time.Settings;

public interface LocalDialect {

	String getVerbalTime(Pieces p, Settings s);

}