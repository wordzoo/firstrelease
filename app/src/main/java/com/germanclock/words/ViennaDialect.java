package com.germanclock.words;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import com.germanclock.time.Pieces;
import com.germanclock.time.Settings;
import com.germanclock.time.ViennaSettings;
import com.wordzoo.uhr.R;

public class ViennaDialect implements LocalDialect {


    public ViennaDialect(){;}
	
	public String getVerbalTime(Pieces p, Settings s, Context c) {
		StringBuilder ret = new StringBuilder();

        if(getBegin(p, (ViennaSettings) s) != null) {
            ret.append(getBegin(p, (ViennaSettings) s));
            ret.append(" ");
        }

        TimeInWords minute = getMinute(p, (ViennaSettings) s, c);

        //no need for official time construction
        if (s.getUmgangssprachlich()
                && s.getUmgangminute() == Settings.Umgangminute.minutebar) {
            getUmgangConstruction(ret, minute, (ViennaSettings)s, p);
        }

        //just offical construction
        else if (!s.getUmgangssprachlich())
        {
            ret.append(getOfficialConstruction(ret, minute, (ViennaSettings) s, p));
        }

        //hybrid construction.  on minute multiples of 5, umgansprache. and on other minutes official format.
        else if (s.getUmgangssprachlich()
                && s.getUmgangminute() == Settings.Umgangminute.minuteword) {

            if(p.getFiveMinBucket() == p.getMinutes()) {
                //umgangsspraliche konstruktion

                //one exception is if user does not want "halb" then we need official time here
                if( (p.getMinutes() == 30)
                        && s.getHalb() == Boolean.FALSE)
                    ret.append(getOfficialConstruction(ret, minute, (ViennaSettings) s, p));
                else
                    ret.append(getUmgangConstruction(ret, minute, (ViennaSettings) s, p));
            }
            else {
                ret.append(getOfficialConstruction(ret, minute, (ViennaSettings)s, p));
            }

        }

		return ret.toString();
	}

    public StringBuilder getUmgangConstruction(StringBuilder ret, TimeInWords minute, ViennaSettings s, Pieces p) {
        if (minute[0] != null) {
            ret.append(minute[0]);
            ret.append(" ");
        }

        if (minute[1] != null) {
            ret.append(minute[1]);
            ret.append(" ");
        }

        ret.append(getHour(p, (ViennaSettings)s, (minute[2].equals(PLUS_HOUR)?Boolean.TRUE:Boolean.FALSE)));
    }

    public StringBuilder getOfficialConstruction(StringBuilder ret, TimeInWords minute, ViennaSettings s, Pieces p) {
        //official time
        ret.append(getHour(p, (ViennaSettings)s, Boolean.FALSE));

        if (minute[0] != null) {
            ret.append(minute[0]);
            ret.append(" ");
        }
    }


	public String getBegin(Pieces p, ViennaSettings s) {
		if(s.getEsist())
			return "es ist ";
		else
			return null;
	}






    public TimeInWords getMinute(Pieces p, ViennaSettings s, Context c) {
        TimeInWords firstPass = new TimeInWords();
        if (!s.getUmgangssprachlich())
            //just official
            return getMinuteOfficial(p, c);

        if (s.getUmgangssprachlich()
                && s.getUmgangminute() == Settings.Umgangminute.minutebar)
            //no need for detail minutes
            getMinuteFive(p,s, null);

        else {
            //hybrid, two passes, load official string, then override it
            firstPass = getMinuteOfficial(p, c);
            return getMinuteFive(p,s, firstPass);
        }
    }


    public TimeInWords getMinuteOfficial(Pieces p, Context c){
        Integer number = new Integer(p.getMinutes());
        TimeInWords ret = new TimeInWords();
        int id = c.getResources().getIdentifier("germannumber"+p.getMinutes(), "string",
                c.getPackageName());
        ret.setMinute1(c.getString(id));
        ret.setUmgangssprachlich(Boolean.FALSE);
        return ret;

    }

        //TO DO form our component here to so that if you need to fall through to official time, you can do that....
    public TimeInWords getMinuteFive(Pieces p, ViennaSettings s, TimeInWords def) {
		TimeInWords word = new TimeInWords();

		switch (p.getFiveMinBucket()) {
			 case 0:
				 if(s.getKurznach() && p.getMinutes() > 0)
					 word.setMinute1("kurz nach");
                 else
                    return def;
                 break;
			 case 5: 
			 	if( s.getFuenfnach())
                    word.setMinute1("fünf nach");
                else
                    return def;
                 break;
			 case 10:
                 if( (s.getViertel() == Settings.Viertel.viertelacht)
                         && s.getFuenfvorviertelacht()) {
                     word.setMinute1("fünf vor");
                     word.setMinute2("viertel");
                     word.setPlusHour(Boolean.TRUE);
                 }
                 else if( s.getZehnnach())
                     word.setMinute1("zehn nach");
                 else
                     return def;
                 break;
			 case 15:  
			 	if(s.getViertel() == Settings.Viertel.viertelacht) {
                    word.setMinute1("viertel");
                    word.setPlusHour(Boolean.TRUE);
                }
			 	else if(s.getViertel() == Settings.Viertel.viertelnach) {
                    word.setMinute1("viertel");
                    word.setMinute2("nach");
                }
                else if(s.getViertel() == Settings.Viertel.viertelueber) {
                    word.setMinute1("viertel");
                    word.setMinute2("über");
                }
                else
                    return def;
			 	break;
			 case 20:
                 if(s.getZehnvorhalb()){
                     word.setMinute1("zehn vor");
                     word.setMinute2("halb");
                     word.setPlusHour(Boolean.TRUE);
                 }
                 else if(s.getZwanzignach())
			 		word.setMinute1("zwanzig nach");
                 else
                     return def;
				break;
			 case 25:
                 if(s.getHalb()
                         && s.getFuenfvorhalb()) {
                     word.setMinute1("fünf vor");
                     word.setMinute2("halb");
                     word.setPlusHour(Boolean.TRUE);
                 }
				else if(s.getFuenfundzwanzignach())
			 		word.setMinute1("fünfundzwanzig nach");
                 else
                     return def;
			 	break;
			 case 30:  
				if(s.getHalb()) {
                    word.setMinute1("halb");
                    word.setPlusHour(Boolean.TRUE);
                }
                else if (s.getDreissignach())
                    word.setMinute1("dreißig nach");
                    return def;
			 	break;
			 case 35: 
				if(s.getHalb()
                 && s.getFuenfnachhalb()) {
                    word.setMinute1("fünf nach");
                    word.setMinute2("halb");
                    word.setPlusHour(Boolean.TRUE);
                }
			 	else {
			 		word.setMinute1("fünfundzwanzig vor");
                    word.setPlusHour(Boolean.TRUE);
			 	}
			 case 40:  
				if(s.getDreiviertel() == Settings.Dreiviertel.dreiviertelacht) {
                    word.setMinute1("fünf vor");
                    word.setMinute2("dreiviertel");
                    word.setPlusHour(Boolean.TRUE);
                }
			 	else {
                    word.setMinute1("zwanzig vor");
			 	}
			 	break;
			 case 45:
                 if(s.getDreiviertel() == Settings.Dreiviertel.dreiviertelacht) {
                     word.setMinute2("dreiviertel");
                     word.setPlusHour(Boolean.TRUE);
                 }
                 else if(s.getDreiviertel() == Settings.Dreiviertel.viertelvor){
                     word.setMinute2("viertel vor");
                 }
                 else if(s.getDreiviertel() == Settings.Dreiviertel.fuenfzehn)
                     word.setMinute1("fünfzehn vor");

			 	break;
			 case 50:
                 if(s.getZehnvor())
                    word.setMinute1("zehn vor");

			 	break;
			 case 55:
                 if(s.getKurzvor() && p.getMinutes() > 55)
                     word.setMinute1("kurz vor");
				 else if(s.getFuenfvor())
                     word.setMinute1("fünf vor");
			 	break;             
	         default: getMinuteOfficial(p,c);
	            break;
		}
		if(s.getUhr())
			word.setUhr("Uhr");
		
		return word;
	}


	

	
	public String getHour(Pieces p, ViennaSettings s, Boolean plusHour) {
		String word;
		Integer number;
		if(s.getUmgangssprachlich())
			number = p.getHr();
		else
			number = p.getHr24();


		//code to read the next hour for expressions like dreiviertel neun (8:45)
		if(plusHour)
            number++;

		if(number.intValue() == 24)
			number = 0;

		switch (number) {
			 case 0: {
				 if(s.getUmgangssprachlich())
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
		if(s.getUhr())
			return word += " Uhr";
		else
			return word;

	}

	


	

}
