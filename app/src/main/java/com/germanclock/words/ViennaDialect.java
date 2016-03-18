package com.germanclock.words;

import android.util.Log;

import com.germanclock.time.Pieces;
import com.germanclock.time.Settings;
import com.germanclock.time.ViennaSettings;

public class ViennaDialect implements LocalDialect {
	public ViennaDialect(){;}
	
	public String getVerbalTime(Pieces p, Settings s) {
		StringBuilder ret = new StringBuilder();

        if(getBegin(p, (ViennaSettings) s) != null) {
            ret.append(getBegin(p, (ViennaSettings) s));
            ret.append(" ");
        }
		if(getMinute(p, (ViennaSettings)s)[0] != null){
			ret.append(getMinute(p, (ViennaSettings) s)[0]);
			ret.append(" ");
		}

		if(getMinute(p, (ViennaSettings)s)[1] != null) {
			ret.append(getMinute(p, (ViennaSettings)s)[1]);
			ret.append(" ");
		}

		ret.append(getHour(p, (ViennaSettings)s));
		ret.append(" (plus remainder minutes: ");
		ret.append(p.getRemainderMinutes());
		ret.append(")");
		return ret.toString();
	}
	
	public String getBegin(Pieces p, ViennaSettings s) {
		if(s.isEsist())
			return "es ist ";
		else
			return null;
	}
	
	public String[] getMinute(Pieces p, ViennaSettings s) {
		String word[] = new String[2];
		switch (p.getFiveMinBucket()) {
			 case 0:
				 if(s.isKurznach()) {
					 word[0] = "kurz nach";
					 break;
				 }
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
		if(s.isUmgangssprachlich())
			number = p.getHr();
		else
			number = p.getHr24();

		//code to read the next hour for expressions like dreiviertel neun (8:45)
		StringBuilder minString = new StringBuilder();
		String[] st = getMinute(p, (ViennaSettings)s);
		if(st != null && st.length > 0) {
			minString.append(st[0]);
			minString.append(" ");
		}

		if(st != null && st.length > 1 ) {
			minString.append(st[1]);
		}

		String minStringTest = minString.toString();

		if( (minStringTest != null && minStringTest.contains("vor"))
				|| (minStringTest != null && minStringTest.contains("halb"))
				|| (minStringTest != null && minStringTest.contains("viertel") && !minStringTest.contains("nach"))
				|| (minStringTest != null && minStringTest.contains("dreiviertel"))
				)
			number++;

		if(number.intValue() == 24)
			number = 0;

		switch (number) {
			 case 0: {
				 if(s.isUmgangssprachlich())
					 word = "zwölf";
				 else
				 	 word = "null";
				 break;
			 }
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
	         case 21: word = "einundzwanzig";
             	break;
	         case 22: word = "zweiundzwanzig";
             	break;
	         case 23: word = "dreiundzwanzig";
             	break;             
	         default: word = "Invalid hour";
	                  break;
		}
		if(s.isUhr())
			return word += " Uhr";
		else
			return word;

	}

	


	

}
