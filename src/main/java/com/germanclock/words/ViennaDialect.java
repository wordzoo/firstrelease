package com.germanclock.words;

import com.germanclock.time.Pieces;
import com.germanclock.time.Settings;
import com.germanclock.time.ViennaSettings;

public class ViennaDialect implements LocalDialect {
	public ViennaDialect(){;}
	
	public String getVerbalTime(Pieces p, Settings s) {
		StringBuffer ret = new StringBuffer();
		ret.append(getBegin(p, (ViennaSettings)s));
		ret.append(" ");
		ret.append(getMinute(p, (ViennaSettings)s));
		ret.append(" ");
		ret.append(getHour(p, (ViennaSettings)s));
		ret.append(" (plus remainder minutes: ");
		ret.append(p.getRemainderMinutes());
		ret.append(")");
		return ret.toString();
	}
	
	public String getBegin(Pieces p, ViennaSettings s) {
		if(s.isFormal())
			return "es ist ";
		else
			return "";
	}
	
	public String[] getMinute(Pieces p, ViennaSettings s) {
		String word[] = new String[2];
		switch (p.getFiveMinBucket()) {
			 case 0:  word[0] = "kurz nach";
			 	break;
			 case 5: 
			 	if( (s.getRangeForViertel() == 10)
			 		&& (!s.isFormal()) ) {
		 			word[0] = "zehn vor";
		 			word[1] = "viertel nach";
			 	}
		 		else
		 			word[0] = "fünf nach";
			 	break;
			 case 10:  
				 if(s.isFormal())
					 word[0] = "zehn nach";
				 else {
					 word[0] = "fünf vor";
				 	 word[1] = "viertel nach";
				 }
			 	break;
			 case 15:  
			 	if(s.isFormal())
			 		word[0] = "fünfzehn nach";
			 	else
			 		word[0] = "viertel nach";
			 	break;
			 case 20: 
				if(s.isFormal())
			 		word[0] = "zwanzig nach";
			 	else {
			 		word[0] = "zehn vor";
					word[1] = "halb";
			 	}
				break;
			 case 25:  
				if(s.isFormal())
			 		word[0] = "fünf und zwanzig nach";
			 	else {
			 		word[0] = "fünf vor";
					word[1] = "halb";
			 	}
			 	break;
			 case 30:  
				if(s.isFormal())
			 		word[0] = "dreizig nach";
			 	else
			 		word[0] = "halb";
			 	break;
			 case 35: 
				if(s.isFormal())
			 		word[0] = "fünf und zwanzig vor";
			 	else {
			 		word[0] = "fünf nach";
					word[1] = "halb";
			 	}
			 case 40:  
				if(s.isFormal())
			 		word[0] = "zwanzig vor";
			 	else {
			 		word[0] = "fünf vor";
			 		word[1] = "dreiviertel";
			 	}
			 	break;
			 case 45:
				 if(s.isFormal())
				 		word[0] = "fünfzehn vor";
				 	else
				 		word[1] = "dreiviertel";
			 	break;
			 case 50: 
				if(s.isFormal())
			 		word[0] = "zehn vor";
			 	else {
			 		word[0] = "fünf nach";
					word[1] = "dreiviertel";
			 	}
			 	break;
			 case 55: 
				 if(s.isFormal())
			 		word[0] = "fünf vor";
			 	else
			 		word[0] = "kurz vor";
			 
			 	break;             
	         default: word[0] = "Invalid hour";
	            break;
		}
		if(s.isFormal())
			word[0] += " Uhr";
		
		return word;
	}


	

	
	public String getHour(Pieces p, ViennaSettings s) {
		String word;
		Integer number;
		if(s.isFormal())
			number = p.getHr24();
		else
			number = p.getHr();
		 
		if(p.getFiveMinBucket() > 30)
			number++;
		else if( (!s.isFormal())
				&& (p.getFiveMinBucket() >= 20) )
			number++;
		
		//TODO: when hour is 24 or 13 (non 24) what do germans call it?
		
		switch (number) {
			 case 1:  word = "ein";
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
			 case 7:  word = "sieben";
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
