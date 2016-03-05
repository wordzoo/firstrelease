package com.germanclock.words;

import com.germanclock.time.Pieces;
import com.germanclock.time.Settings;

public class Vienna {
	public Vienna(){;}
	public String getVerbalTime(Pieces p, Settings s) {
		StringBuffer ret = new StringBuffer();
		ret.append(getBegin(p,s));
		ret.append(getMinutes(p,s));
		ret.append(getHour(p, s));
		return ret.toString();
	}
	
	private String getBegin(Pieces p, Settings s) {
		if(s.isFormal())
			return "es ist ";
		else
			return "";
	}
	
	private String getMinutes(Pieces p, Settings s) {
		String word;
		switch (p.getFiveMinBucket()) {
			 case 0:  word = "kurz nach";
			 	break;
			 case 5:  word = "fünf nach";
			 	break;
			 case 10:  word = "zehn nach";
			 	break;
			 case 15:  
			 	if(s.isFormal())
			 		word = "fünfzehn nach";
			 	else
			 		word = "viertel nach";
			 	break;
			 case 20: 
				 if(s.isFormal())
			 		word = "zwanzig nach";
			 	else
			 		word = "zehn vor halb";
				 break;
			 case 25:  word = "sechs";
			 	break;
			 case 30:  word = "seben";
			 	break;
			 case 35:  word = "acht";
			 	break;
			 case 40:  word = "neun";
			 	break;
			 case 45: word = "zehn";
			 	break;
			 case 50: word = "elf";
			 	break;
			 case 55: word = "zwölf";
			 	break;             
	         default: word = "Invalid hour";
	            break;
		}
		if(s.isFormal())
			return word += " Uhr";
		else
			return word;
	}


	

	
	private String getHour(Pieces p, Settings s) {
		String word;
		Integer number;
		if(s.isFormal())
			number = p.getHr24();
		else
			number = p.getHr();
		
		switch (number) {
			 case 1:  
				 if(s.isFormal())
					 word = "ein";
				 else if(p.getFiveMinBucket() > 15 && p.getFiveMinBucket() < 45)
					 word = "zwölf";
			 	break;
			 case 2:  word = "zwei";
			 	break;
			 case 3:  word = "drei";
			 	break;
			 case 4:  word = "vier";
			 	break;
			 case 5:  word = "fünf";
			 	break;
			 case 6:  word = "sechs";
			 	break;
			 case 7:  word = "seben";
			 	break;
			 case 8:  word = "acht";
			 	break;
			 case 9:  word = "neun";
			 	break;
			 case 10: word = "zehn";
			 	break;
			 case 11: word = "elf";
			 	break;
			 case 12: word = "zwölf";
			 	break;
			 case 13: word = "dreizehn";
			 	break;
	         case 14: word = "vierzehn";
             	break;
	         case 15: word = "fünfzehn";
             	break;
	         case 16: word = "sechszehn";
             	break;
	         case 17: word = "sebzehn";
             	break;
	         case 18: word = "achtzehn";
             	break;
	         case 19: word = "neunzehn";
             	break;
	         case 20: word = "zwanzig";
             	break;
	         case 21: word = "ein und zwanzig";
             	break;
	         case 22: word = "zwei und zwanzig";
             	break;
	         case 23: word = "drei und zwanzig";
             	break;             
	         default: word = "Invalid hour";
	                  break;
		}
		if(s.isFormal())
			return word += " Uhr";
		else
			return word;
	}
	

}
