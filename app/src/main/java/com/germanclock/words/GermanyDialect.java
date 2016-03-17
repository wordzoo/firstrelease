package com.germanclock.words;

import com.germanclock.time.Pieces;
import com.germanclock.time.Settings;
import com.germanclock.time.ViennaSettings;

public class GermanyDialect implements LocalDialect {
	public GermanyDialect(){;}
	
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
		/*
		//Frank von Dambach Deutchland (Sud Ost) hat geschrieben:
		 * good to hear from you ;-)
Interesting task üwriting a german word clockü ;-)

From my perspective I donüt really see differences to articulate the clock time in different german areas.
and I see no differences between formal and commonly use of the time. perhaps, some minimal usage in special case like 
for example 7:35 or 7:25 - in this cases we say twenty-five minutes after seven - fünfundzwanzig minuten nach sieben- 
or five minutes to half eight - fünf minuten vor halb acht.

This brings me to a generell statement:
We bring the time never in relationship to the quarter or three quarter only to the full hour or half hour.

Your examples:
8:05 - we donüt say üzehn vor viertel nach achtü! we say only üfünf Minuten nach achtü
8:10 - we say only 10 Minuten nach acht
8:20 - we say zwanzig Minuten nach acht or zehn Minuten vor halb neun (both is possible)
8:40 - we say often zwanzig Minuten vor neun or zehn Minuten nach halb neun

To shorten this forms, we lost the words minutes. Instead of "zehn Minuten nach acht" - you can say "zehn nach acht" 

We donüt use the forms üzehn vor dreiviertel neunü (8:35)

AND we use in colloquial way very often the shortest form like üacht uhr zwanzigü for 8:20 
some examples:
8:05 - acht uhr fünf
8:17 - acht uhr siebzehn
8:35 - acht uhr fünfunddreiüig
8:45 - acht uhr fünfundvierzig
8:50 - acht uhr fünfzig

For Midnight we say Mitternacht und for 00:05 we say üNull uhr fünfü or üFünf Minuten nach Mitternachtü
		*/
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
