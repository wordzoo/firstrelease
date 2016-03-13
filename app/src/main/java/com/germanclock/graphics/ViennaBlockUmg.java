package com.germanclock.graphics;

import java.util.ArrayList;
import java.util.List;

import com.germanclock.time.Pieces;
import com.germanclock.time.ViennaSettings;
import com.germanclock.words.LocalDialect;
import com.germanclock.words.ViennaDialect;

public class ViennaBlockUmg extends WordBlock {
	
	
	public ViennaBlockUmg() {
		
		String[][][] clockFace = 
				{{{"E", "S"}, {"K"}, {"I", "S", "T"}, {"A"}, {"F", "Ü", "N", "F"}},
				{{"Z", "E", "H", "N"}, {"Z", "W", "A", "N", "S", "I", "G"}},
				{{"V", "O", "R"}, {"F", "U", "N", "K"}, {"N", "A", "C", "H"}},
				{{"D", "R", "E", "I","V", "I", "E", "R", "T", "E", "L"}},
				{{"V", "O", "R"}, {"F", "U", "N", "K"}, {"N", "A", "C", "H"}},
				{{"H", "A", "L", "B"}, {"E", "L", "F"}, {"F", "Ü", "N", "F"}},
				{{"E", "I", "N", "S"}, {"X"}, {"A", "M"}, {"Z", "W", "E", "I"}},
				{{"D", "R", "E", "I"}, {"P", "M"}, {"J"}, {"V", "I", "E", "R"}},
				{{"S", "E", "C", "H", "S"}, {"N", "L"}, {"A", "C", "H", "T"}},
				{{"S", "I", "E", "B", "E","N"}, {"Z", "W", "Ö", "L", "F"}},
				{{"Z", "E", "H", "N"},{"N", "E", "U", "N"}, {"U", "H", "R"}}}; 
		
		setWordBlock(clockFace);
		
	}
	
	public void showTime(String time)   {
		
		ViennaSettings s = new ViennaSettings();
		s.setUmgangssprachlich(10);
		s.setRangeForViertel(5);
		s.setRangeForHalb(5);
		s.setRangeForDreiViertel(5);
		
		Pieces p = new Pieces(time);
		
		LocalDialect v = new ViennaDialect();
		
		updateClock(v.getBegin(p,s),0,0);
		
		String minute[] = v.getMinute(p,s);
		updateClock(minute[0],0,2);
		updateClock(minute[1],3,4);
		
		updateClock(v.getHour(p, s),5,10);
		
		setRemainderMins(p.getRemainderMinutes());
		
		drawClock();
		
	}
	
	public void updateRemainder(Integer remainder) {
		
	}
}
