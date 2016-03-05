package com.germanclock.test;

import com.germanclock.time.Pieces;
import com.germanclock.time.Settings;
import com.germanclock.words.Vienna;

public class Core {

	//assume a 24 hr time input string
	public static void main(String[] args) {
		//String time = args[0];
		String time="6:59";
		
		Settings s = new Settings();
		s.setUmgangssprachlich(5);
		
		Pieces p = new Pieces(time);
		
		Vienna v = new Vienna();
		
		System.out.println(v.getVerbalTime(p, s));
		
	}

	private static String getSouthernAustrianTime(Integer hr24, 
												  Integer hr, 
												  Integer fiveMinBucket,
												  Integer remainderMinutes) {
		return "Es ist halb seben";
		
	}

	private static Integer get12Hour(String hour) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
