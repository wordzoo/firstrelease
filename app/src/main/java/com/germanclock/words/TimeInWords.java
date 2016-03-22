package com.germanclock.words;

import android.content.Context;
import android.content.res.Resources;

import com.germanclock.time.Pieces;
import com.germanclock.time.Settings;
import com.wordzoo.uhr.R;


public class TimeInWords {

    private Context context;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public TimeInWords(){;}
    public TimeInWords(Context c){this.context = c;}
	
	public String getTimeAsSentance(Pieces p, Settings s) {
		StringBuilder ret = new StringBuilder();

        if(isNotEmpty(getBegin(p, s))) {
            ret.append(getBegin(p, s));
            ret.append(" ");
        }


        //conversational time construction
        if (s.getUmgangssprachlich())
            getUmgangConstruction(ret, s, p);

        //offical time construction
        else
            getOfficialConstruction(ret, s, p);

		return ret.toString().trim();
	}

    public StringBuilder getUmgangConstruction(StringBuilder ret, Settings s, Pieces p) {
        TimeInWordsDto timeInWordsDto = getMinute(p, s);

        if (isNotEmpty(timeInWordsDto.getMinute1())) {
            ret.append(timeInWordsDto.getMinute1());
            ret.append(" ");
        }

        if (isNotEmpty(timeInWordsDto.getMinute2())) {
            ret.append(timeInWordsDto.getMinute2());
            ret.append(" ");
        }

        ret.append(getHour(p, s, timeInWordsDto.getPlusHour()));

        return ret;
    }

    public StringBuilder getOfficialConstruction(StringBuilder ret, Settings s, Pieces p) {
        //official time
        ret.append(getHour(p, s, Boolean.FALSE));

        TimeInWordsDto timeInWordsDto = getMinute(p, s);
        if (isNotEmpty(timeInWordsDto.getMinute1())) {
            ret.append(timeInWordsDto.getMinute1());
            ret.append(" ");
        }
        return ret;
    }


	public String getBegin(Pieces p, Settings s) {
		if(s.getEsist())
			return "Es ist";
		else
			return null;
	}






    public TimeInWordsDto getMinute(Pieces p, Settings s) {

        if (!s.getUmgangssprachlich())
            //just official
            return getMinuteOfficial(p);

        if (s.getUmgangssprachlich()) {
            //there is a simplified time, rounded to 5 minutes, to support Settings.layout.block
            //or there is a more complete time which can only be displayed using Settings.layout.sentance
            return getUmgangMinutes(p, s);
        }
        return null;
    }


    public TimeInWordsDto getMinuteOfficial(Pieces p){
        Integer number = new Integer(p.getMinutes());
        TimeInWordsDto ret = new TimeInWordsDto();
        Resources res = getContext().getResources();
        String[] german_number = res.getStringArray(R.array.german_number);
        ret.setMinute1(german_number[p.getMinutes()-1]);
        ret.setUmgangssprachlich(Boolean.FALSE);
        ret.setPlusHour(Boolean.FALSE);
        return ret;

    }


    public TimeInWordsDto getUmgangMinutes(Pieces p, Settings s) {
		TimeInWordsDto word = new TimeInWordsDto();



        //the point is that for Settings.Umgangminute.minutebar, rouding to the nearest 5 is what we want
        // but for Settings.Umgangminute.minuteword, only when p.getMinutes() == p.getFiveMinuteBucket
        //do we want to drop into a case....

        Integer testMinute;
        if(s.getUmgangminute() == Settings.Umgangminute.minuteword) {
            testMinute = p.getMinutes();
            //set up defaults
            Integer umgangMinutes = p.getMinutes();
            if(umgangMinutes > 30)
                umgangMinutes = 60 - umgangMinutes;
            int id = getContext().getResources().getIdentifier("germannumber"+umgangMinutes, "string",
                    getContext().getPackageName());
            String stringUmgangMinutes = getContext().getString(id);
            word.setMinute1(stringUmgangMinutes + " " + ((p.getMinutes() <= 30)?"nach":"vor"));
            if(p.getMinutes() > 30)
                word.setPlusHour(Boolean.TRUE);
        }
        else
            testMinute = p.getFiveMinBucket();

		switch (testMinute) {
			 case 0:
				 if(s.getKurznach() && p.getMinutes() > 0)
					 word.setMinute1("kurz nach");
                 break;
			 case 5:
                    word.setMinute1("fünf nach");

                 break;
			 case 10:
                 if( (s.getViertel() == Settings.Viertel.viertelacht)
                         && s.getFuenfvorviertelacht()) {
                     word.setMinute1("fünf vor");
                     word.setMinute2("viertel");
                     word.setPlusHour(Boolean.TRUE);
                 }
                 else
                     word.setMinute1("zehn nach");
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
			 	break;
			 case 20:
                 if(s.getZehnvorhalb()){
                     word.setMinute1("zehn vor");
                     word.setMinute2("halb");
                     word.setPlusHour(Boolean.TRUE);
                 }
                 else if(s.getZwanzignach())
			 		word.setMinute1("zwanzig nach");

				break;
			 case 25:
                 if(s.getHalb()
                         && s.getFuenfvorhalb()) {
                     word.setMinute1("fünf vor");
                     word.setMinute2("halb");
                     word.setPlusHour(Boolean.TRUE);
                 }
				else
			 		word.setMinute1("fünfundzwanzig nach");
			 	break;
			 case 30:  
				if(s.getHalb()) {
                    word.setMinute1("halb");
                    word.setPlusHour(Boolean.TRUE);
                }
                else if (s.getDreissignach())
                    word.setMinute1("dreißig nach");
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
                    word.setMinute1("zehn vor");
			 	break;
			 case 55:
                 if(s.getKurzvor() && p.getMinutes() > 55)
                     word.setMinute1("kurz vor");
			 	break;             
	         //default: getMinuteOfficial(p);
	           // break;
		}
		if(s.getUhr())
			word.setUhr("Uhr");
		
		return word;
	}


	

	
	public String getHour(Pieces p, Settings s, Boolean plusHour) {
		String word;
		Integer number;
		if (s.getUmgangssprachlich())
			number = p.getHr();
		else
			number = p.getHr24();


		//code to read the next hour for expressions like dreiviertel neun (8:45)
		if (plusHour)
			number++;

		if (number.intValue() == 24)
			number = 0;

		switch (number) {
			case 0: {
                if (s.getMitternachts())
					word = "Mitternacht ";
				else
					word = "Null ";
				break;
			}
			case 1:
				word = "ein ";
				break;
			case 2:
				word = "zwei ";
				break;
			case 3:
				word = "drei ";
				break;
			case 4:
				word = "vier ";
				break;
			case 5:
				word = "fünf ";
				break;
			case 6:
				word = "sechs ";
				break;
			case 7:
				word = "sieben ";
				break;
			case 8:
				word = "acht ";
				break;
			case 9:
				word = "neun ";
				break;
			case 10:
				word = "zehn ";
				break;
			case 11:
				word = "elf ";
				break;
			case 12:
				word = "zwölf ";
				break;
			case 13:
				word = "dreizehn ";
				break;
			case 14:
				word = "vierzehn ";
				break;
			case 15:
				word = "fünfzehn ";
				break;
			case 16:
				word = "sechszehn ";
				break;
			case 17:
				word = "sebzehn ";
				break;
			case 18:
				word = "achtzehn ";
				break;
			case 19:
				word = "neunzehn ";
				break;
			case 20:
				word = "zwanzig ";
				break;
			case 21:
				word = "einundzwanzig ";
				break;
			case 22:
				word = "zweiundzwanzig ";
				break;
			case 23:
				word = "dreiundzwanzig ";
				break;
			default:
				word = "Invalid hour ";
				break;
		}
		if (s.getUhr())
			word += "Uhr ";

//three night variatne, nach, früh and morgen, mutually exclusive
		if (p.getHr24() > 0
				&& p.getHr24() < 5
				&& s.getNachts())
			word += "nachts ";
		else if (p.getHr24() > 0
				&& p.getHr24() < 5
				&& s.getIndernacht())
			word += "in der Nacht ";
		else if (p.getHr24() > 0
				&& p.getHr24() < 5
				&& s.getInderfrueh())
			word += "in der Früh ";
		else if (p.getHr24() > 0
				&& p.getHr24() < 5
				&& s.getMorgennacht())
			word += "moregens ";
		else if (p.getHr24() > 0
				&& p.getHr24() < 5
				&& s.getAmmorgennacht())
			word += "am Morgen ";

		if (p.getHr24() >= 5
				&& p.getHr24() < 10
				&& s.getMorgens())
			word += "morgens ";
		if (p.getHr24() >= 5
				&& p.getHr24() < 10
				&& s.getAmmorgen())
			word += "am Morgen ";


		if (p.getHr24() >= 10
				&& p.getHr24() < 12
				&& s.getVormittags())
			word += "vormittags ";
		if (p.getHr24() >= 10
				&& p.getHr24() < 12
				&& s.getAmmorgen())
			word += "am Vormittag ";


		if (p.getHr24() >= 12
				&& p.getHr24() < 18
				&& s.getNachmittags())
			word += "nachmittags ";
		if (p.getHr24() >= 12
				&& p.getHr24() < 18
                && s.getAmnachmittag())
			word += "am Nachmittag ";


		if (p.getHr24() >= 18
				&& p.getHr24() <= 23
				&& s.getAbends())
			word += "abends ";
		if (p.getHr24() >= 18
				&& p.getHr24() <= 23
				&& s.getAmmorgen())
			word += "am Abend ";


		return word;

	}

	public Boolean isNotEmpty(String in){
		if(in != null && in != "")
			return Boolean.TRUE;
		else
			return Boolean.FALSE;
	}


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

    /* karntnerisch
		1 ans
		2 zwa
		3 drei
		4 vier
		5 fünf
		6 sex
		7 ziebn
		8 ocht
		9 nein
		10 zehn
		11 ülf
		12 zwülf
		13 dreizehn
		14 vierzehn
		15 fuchzehn
		16 sechszehn
		17 ziebzehn
		18 ochtzechn
		19 neinzehn
		20 zwanzig
		21 anezwanzig
		22 zwarezwanzig
		23 dreiezwanzig
		00:05 beides oder einfach nur " fünf nach"
		*/

}
