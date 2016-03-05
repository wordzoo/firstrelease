package com.germanclock.test;

import com.germanclock.time.Pieces;
import com.germanclock.time.Settings;
import com.germanclock.time.ViennaSettings;
import com.germanclock.words.LocalDialect;
import com.germanclock.words.ViennaDialect;

public class Test {

	//assume a 24 hr time input string
	public static void main(String[] args) {
		//String time = args[0];
		String time="7:20";
		
		ViennaSettings s = new ViennaSettings();
		s.setUmgangssprachlich(10);
		s.setRangeForViertel(10);
		s.setRangeForHalb(10);
		s.setRangeForDreiViertel(5);
		
		Pieces p = new Pieces(time);
		
		LocalDialect v = new ViennaDialect();
		
		System.out.println(v.getVerbalTime(p, s));
		
	}
	

}
