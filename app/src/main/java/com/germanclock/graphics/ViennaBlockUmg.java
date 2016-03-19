package com.germanclock.graphics;

import com.germanclock.time.Pieces;
import com.germanclock.time.Settings;
import com.germanclock.words.TimeInWordsDto;
import com.germanclock.words.TimeInWords;

public class ViennaBlockUmg extends WordBlock {
	
	
	public ViennaBlockUmg() {
		
		String[][][] clockFace = 
				{{{"E", "S"}, {"K"}, {"I", "S", "T"}, {"A"}, {"F", "Ü", "N", "F"}},
				{{"Z", "E", "H", "N"}, {"Z", "W", "A", "N", "S", "I", "G"}},
						{{"V", "O", "R"}, {"Ü", "B", "E", "R"}, {"N", "A", "C", "H"}},
				{{"D", "R", "E", "I","V", "I", "E", "R", "T", "E", "L"}},
				{{"H", "A", "L", "B"}, {"E", "L", "F"}, {"F", "Ü", "N", "F"}},
				{{"E", "I", "N", "S"}, {"X"}, {"A", "M"}, {"Z", "W", "E", "I"}},
				{{"D", "R", "E", "I"}, {"P", "H"}, {"J"}, {"V", "I", "E", "R"}},
				{{"S", "E", "C", "H", "S"}, {"R", "L"}, {"A", "C", "H", "T"}},
				{{"S", "I", "E", "B", "E","N"}, {"Z", "W", "Ö", "L", "F"}},
				{{"Z", "E", "H", "N"},{"N", "E", "U", "N"}, {"U", "H", "R"}}}; 
		
		setWordBlock(clockFace);
		
	}
	
	public void showTime(String time)   {
		
		Settings s = new Settings();
		s.setUmgangssprachlich(Boolean.TRUE);

		Pieces p = new Pieces(time);
		
		TimeInWords v = new TimeInWords();
		
		updateClock(v.getBegin(p,s),0,0);
		
		TimeInWordsDto timeInWordsDto = v.getMinute(p,s);
		updateClock(timeInWordsDto.getMinute1(),0,2);
		updateClock(timeInWordsDto.getMinute2(),3,4);
		
		updateClock(v.getHour(p, s, timeInWordsDto.getPlusHour()),5,10);
		
		setRemainderMins(p.getRemainderMinutes());
		
		drawClock();
		
	}
	
	public void updateRemainder(Integer remainder) {
		
	}
}
