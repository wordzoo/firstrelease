package com.germanclock.words;

import android.content.Context;
import android.content.res.Resources;

import com.germanclock.time.Pieces;
import com.germanclock.time.Settings;
import com.wordzoo.uhr.R;


public class TimeInWords {

    private Context context;

    public Context getContext() {
        return this.context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public TimeInWords(){;}
    public TimeInWords(Context context){this.context = context;}
	
	public String getTimeAsSentance(Pieces p, Settings s) {
		StringBuilder ret = new StringBuilder();
        TimeInWordsDto tiw = new TimeInWordsDto(p, s);

        //by ref populate the beginning
        getBegin(tiw);

        if (!tiw.getSettings().getUmgangssprachlich())
            getMinuteOfficial(tiw);

        if (tiw.getSettings().getUmgangssprachlich()) {
            if( (tiw.getPieces().getRemainderMinutes() > 0)
                    && tiw.getSettings().getMinuteHybrid()
                    && tiw.getSettings().getUmgangminute().equals(Settings.Umgangminute.minuteword))
                getMinuteOfficial(tiw);
            else
               getUmgangMinutes(tiw);
        }

        //common to all expressions
        if(isNotEmpty(tiw.getBegin())) {
            ret.append(tiw.getBegin());
            ret.append(" ");
        }

        //Official or Hybrid Construction
        if(!tiw.getSettings().getUmgangssprachlich()) {
            ret.append(tiw.getHour());
            ret.append(" ");
            ret.append(tiw.getMinute1());
            ret.append(" ");
            ret.append(tiw.getMinute());
        }
        //special case when the minutes are nto a multiple of 5, and user has selected in this case to use official construction,
        //but because tiw.getSettings().getUmganssprachlich() is true, we will still use the 12 hours system...

        if( (tiw.getPieces().getRemainderMinutes() > 0)
                && (tiw.getSettings().getMinuteHybrid()
                && tiw.getSettings().getUmgangminute().equals(Settings.Umgangminute.minuteword))) {
            ret.append(tiw.getHour());
            ret.append(" ");
            ret.append(tiw.getMinute1());
            ret.append(" ");
            ret.append(tiw.getMinute());
        }
        else {


            if (isNotEmpty(tiw.getMinute1())) {
                ret.append(tiw.getMinute1());
                ret.append(" ");
            }

            //word "Minute" or "Minuten"
            if (isNotEmpty(tiw.getMinute())) {
                ret.append(tiw.getMinute());
                ret.append(" ");
            }

            if (isNotEmpty(tiw.getVornach())) {
                ret.append(tiw.getVornach());
                ret.append(" ");
            }

            if (isNotEmpty(tiw.getMinute2())) {
                ret.append(tiw.getMinute2());
                ret.append(" ");
            }

            if (isNotEmpty(tiw.getHour())) {
                ret.append(tiw.getHour());
                ret.append(" ");
            }

            if (isNotEmpty(tiw.getUhr())) {
                ret.append(tiw.getUhr());
                ret.append(" ");
            }

            if (!(tiw.getSettings().getMitternacht()
                    && (tiw.getPieces().getHr24() == 0))
                    )
                if (isNotEmpty(tiw.getSectionOfDay())) {
                    ret.append(tiw.getSectionOfDay());
                    ret.append(" ");
                }
        }
        return ret.toString().trim();
	}






	public void getBegin(TimeInWordsDto tiw) {
		if(tiw.getSettings().getEsist())
			tiw.setBegin("Es ist");

	}


    public void getMinuteOfficial(TimeInWordsDto tiw){
        Integer number = new Integer(tiw.getPieces().getMinutes());

        Resources res = getContext().getResources();
        String[] german_number = res.getStringArray(R.array.german_number);
        if(tiw.getSettings().getMinute() && tiw.getPieces().getMinutes() == 1)
            tiw.setMinute1(german_number[0]);
        else
            tiw.setMinute1(german_number[tiw.getPieces().getMinutes()]);

        tiw.setPlusHour(Boolean.FALSE);

    }


    public void getUmgangMinutes(TimeInWordsDto tiw) {

        Integer testMinute;


        //this block here is for the case where s.MinuteHybrid() is false
        //here we still use vor and nach but with the full minutes in words
        if(tiw.getSettings().getUmgangminute() == Settings.Umgangminute.minuteword) {
            testMinute = tiw.getPieces().getMinutes();
            //set up defaults
            Integer umgangMinutes = tiw.getPieces().getMinutes();
            if(umgangMinutes > 30)
                umgangMinutes = 60 - umgangMinutes;
            Resources res = getContext().getResources();
            String[] german_number = res.getStringArray(R.array.german_number);

            if(tiw.getSettings().getMinute() && tiw.getPieces().getMinutes() == 1)
                tiw.setMinute1(german_number[0]);
            else
                tiw.setMinute1(german_number[tiw.getPieces().getMinutes()]);


            tiw.setVornach((tiw.getPieces().getMinutes() <= 30)?"nach":"vor");
            if(tiw.getPieces().getMinutes() > 30)
                tiw.setPlusHour(Boolean.TRUE);
        }
        else
            testMinute = tiw.getPieces().getFiveMinBucket();

		switch (testMinute) {
			 case 0:
				 if(tiw.getSettings().getKurznach() && tiw.getPieces().getMinutes() > 0) {
                     tiw.setMinute1("kurz");
                     tiw.setVornach("nach");
                 }
                 break;
			 case 5:
                     tiw.setMinute1("fünf");
                     tiw.setVornach("nach");
                 break;
			 case 10:
                 if( (tiw.getSettings().getViertel() == Settings.Viertel.viertelacht)
                         && tiw.getSettings().getFuenfvorviertelacht()) {
                     tiw.setMinute1("fünf");
                     tiw.setVornach("vor");
                     tiw.setMinute2("viertel");
                     tiw.setPlusHour(Boolean.TRUE);
                 }
                 else {
                     tiw.setMinute1("zehn");
                     tiw.setVornach("nach");
                 }
                 break;
			 case 15:  
			 	if(tiw.getSettings().getViertel() == Settings.Viertel.viertelacht) {
                    tiw.setMinute1("viertel");
                    tiw.setPlusHour(Boolean.TRUE);
                }
			 	else if(tiw.getSettings().getViertel() == Settings.Viertel.viertelnach) {
                    tiw.setMinute1("viertel");
                    tiw.setVornach("nach");
                }
                else if(tiw.getSettings().getViertel() == Settings.Viertel.viertelueber) {
                    tiw.setMinute1("viertel");
                    tiw.setVornach("über");
                }
			 	break;
			 case 20:
                 if(tiw.getSettings().getZehnvorhalb()){
                     tiw.setMinute1("zehn");
                     tiw.setVornach("vor");
                     tiw.setMinute2("halb");
                     tiw.setPlusHour(Boolean.TRUE);
                 }
                 else if(tiw.getSettings().getZwanzignach()) {
                     tiw.setMinute1("zwanzig");
                     tiw.setVornach("nach");
                 }

				break;
			 case 25:
                 if(tiw.getSettings().getHalb()
                         && tiw.getSettings().getFuenfvorhalb()) {
                     tiw.setMinute1("fünf");
                     tiw.setVornach("vor");
                     tiw.setMinute2("halb");
                     tiw.setPlusHour(Boolean.TRUE);
                 }
				else {
                     tiw.setMinute1("fünfundzwanzig");
                     tiw.setVornach("nach");
                 }
			 	break;
			 case 30:  
				if(tiw.getSettings().getHalb()) {
                    tiw.setMinute1("halb");
                    tiw.setPlusHour(Boolean.TRUE);
                }
                else if (tiw.getSettings().getDreissignach()) {
                    tiw.setMinute1("dreißig");
                    tiw.setVornach("nach");
                }
                break;
			 case 35: 
				if(tiw.getSettings().getHalb()
                 && tiw.getSettings().getFuenfnachhalb()) {
                    tiw.setMinute1("fünf");
                    tiw.setVornach("nach");
                    tiw.setMinute2("halb");
                    tiw.setPlusHour(Boolean.TRUE);
                }
			 	else {
			 		tiw.setMinute1("fünfundzwanzig");
                    tiw.setVornach("vor");
                    tiw.setPlusHour(Boolean.TRUE);
			 	}
			 case 40:  
				if(tiw.getSettings().getDreiviertel() == Settings.Dreiviertel.dreiviertelacht) {
                    tiw.setMinute1("fünf");
                    tiw.setVornach("vor");
                    tiw.setMinute2("drei viertel");
                    tiw.setPlusHour(Boolean.TRUE);
                }
			 	else {
                    tiw.setMinute1("zwanzig");
                    tiw.setVornach("vor");
			 	}
			 	break;
			 case 45:
                 if(tiw.getSettings().getDreiviertel() == Settings.Dreiviertel.dreiviertelacht) {
                     tiw.setMinute2("drei viertel");
                     tiw.setPlusHour(Boolean.TRUE);
                 }
                 else if(tiw.getSettings().getDreiviertel() == Settings.Dreiviertel.viertelvor){
                     tiw.setMinute2("viertel");
                     tiw.setVornach("vor");
                 }
                 else if(tiw.getSettings().getDreiviertel() == Settings.Dreiviertel.fuenfzehn) {
                     tiw.setMinute1("fünfzehn");
                     tiw.setVornach("vor");
                 }

			 	break;
			 case 50:
                    tiw.setMinute1("zehn");
                    tiw.setVornach("vor");
			 	break;
			 case 55:
                 if(tiw.getSettings().getKurzvor() && tiw.getPieces().getMinutes() > 55) {
                     tiw.setMinute1("kurz");
                     tiw.setVornach("vor");
                 }
			 	break;             
	         //default: getMinuteOfficial(p);
	           // break;
		}
	}


	

	
	public String getHour(TimeInWordsDto tiw) {
        String word;
        Integer number;
        if (tiw.getSettings().getUmgangssprachlich())
            number = tiw.getPieces().getHr();
        else
            number = tiw.getPieces().getHr24();


        //code to read the next hour for expressions like dreiviertel neun (8:45)
        if (tiw.getPlusHour())
            number++;

        if (number.intValue() == 24)
            number = 0;

        Resources res = getContext().getResources();
        String[] german_number = res.getStringArray(R.array.german_number);


        if(number == 0) {
            if (tiw.getSettings().getMitternacht())
                word = "Mitternacht ";
            else
                word = "Null ";
        }
        else {
            if( tiw.getSettings().getUhr() && number == 1 && tiw.getPlusHour().equals(Boolean.FALSE))
                word = german_number[0] + " ";
            else
                word = german_number[number] + " ";
        }


        if (tiw.getSettings().getUhr()
                && !(tiw.getSettings().getMitternacht() && (tiw.getPieces().getHr24() == 0))
                && !tiw.equals("eins"))
            word += "Uhr ";
        return word;
    }


    public void getSectionOfDay(TimeInWordsDto tiw) {

//three night variatne, nach, früh and morgen, mutually exclusive
		if (tiw.getPieces().getHr24() >= 0
				&& tiw.getPieces().getHr24() < 5
                && tiw.getSettings().getNachts())
            tiw.setSectionOfDay("nachts ");
		else if (tiw.getPieces().getHr24() >= 0
				&& tiw.getPieces().getHr24() < 5
				&& tiw.getSettings().getIndernacht())
            tiw.setSectionOfDay("in der Nacht ");
		else if (tiw.getPieces().getHr24() >= 0
				&& tiw.getPieces().getHr24() < 5
				&& tiw.getSettings().getInderfrueh())
            tiw.setSectionOfDay("in der Früh ");
		else if (tiw.getPieces().getHr24() >= 0
				&& tiw.getPieces().getHr24() < 5
                && tiw.getSettings().getMorgennacht())
            tiw.setSectionOfDay("moregens ");
		else if (tiw.getPieces().getHr24() >= 0
				&& tiw.getPieces().getHr24() < 5
				&& tiw.getSettings().getAmmorgennacht())
            tiw.setSectionOfDay("am Morgen ");

		if (tiw.getPieces().getHr24() >= 5
				&& tiw.getPieces().getHr24() < 10
				&& tiw.getSettings().getMorgens())
            tiw.setSectionOfDay("morgens ");
		if (tiw.getPieces().getHr24() >= 5
				&& tiw.getPieces().getHr24() < 10
				&& tiw.getSettings().getAmmorgen())
            tiw.setSectionOfDay("am Morgen ");


		if (tiw.getPieces().getHr24() >= 10
				&& tiw.getPieces().getHr24() < 12
				&& tiw.getSettings().getVormittags())
            tiw.setSectionOfDay("vormittags ");
		if (tiw.getPieces().getHr24() >= 10
				&& tiw.getPieces().getHr24() < 12
				&& tiw.getSettings().getAmmorgen())
            tiw.setSectionOfDay("am Vormittag ");


		if (tiw.getPieces().getHr24() >= 12
				&& tiw.getPieces().getHr24() < 18
				&& tiw.getSettings().getNachmittags())
            tiw.setSectionOfDay("nachmittags ");
		if (tiw.getPieces().getHr24() >= 12
				&& tiw.getPieces().getHr24() < 18
                && tiw.getSettings().getAmnachmittag())
            tiw.setSectionOfDay("am Nachmittag ");


		if (tiw.getPieces().getHr24() >= 18
				&& tiw.getPieces().getHr24() <= 23
				&& tiw.getSettings().getAbends())
            tiw.setSectionOfDay("abends ");
		if (tiw.getPieces().getHr24() >= 18
				&& tiw.getPieces().getHr24() <= 23
				&& tiw.getSettings().getAmabend())
            tiw.setSectionOfDay("am Abend ");

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
