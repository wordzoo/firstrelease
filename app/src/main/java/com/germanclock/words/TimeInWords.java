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



        //conversational time construction
        if (s.getUmgangssprachlich())
            getUmgangConstruction(tiw);
            //offical time construction
        else
            getOfficialConstruction(tiw);

        if(  !(s.getMitternacht()
                && (p.getHr24() == 0))
                )
           tiw.setSectionOfDay(getSectionOfDay(tiw));

        if(isNotEmpty(tiw.getBegin())) {
            ret.append(tiw.getBegin());
            ret.append(" ");
        }

        if(isNotEmpty(tiw.getMinute1())) {
            ret.append(tiw.getMinute1());
            ret.append(" ");
        }

        //word "Minute" or "Minuten"
        if(isNotEmpty(tiw.getMinute())) {
            ret.append(tiw.getMinute());
            ret.append(" ");

        if(isNotEmpty(tiw.getVornach())) {
            ret.append(tiw.getVornach());
            ret.append(" ");
        }

        if(isNotEmpty(tiw.getMinute2())) {
            ret.append(tiw.getMinute2());
            ret.append(" ");
        }

        if(isNotEmpty(tiw.getHour())) {
            ret.append(tiw.getHour());
            ret.append(" ");
        }

        if(isNotEmpty(tiw.getUhr())) {
            ret.append(tiw.getUhr());
            ret.append(" ");
        }

        if(isNotEmpty(tiw.getSectionOfDay())) {
            ret.append(tiw.getSectionOfDay());
            ret.append(" ");
        }


        return ret.toString().trim();
	}

    public void getUmgangConstruction(TimeInWordsDto tiw) {
		tiw.merge(getMinute(tiw.getPieces(), tiw.getSettings()));

		//special case when the minutes are nto a multiple of 5, and user has selected in this case to use official construction,
		//but because s.getUmganssprachlich() is true, we will still use the 12 hours system...
		if( (p.getRemainderMinutes() > 0) &&
				(s.getMinuteHybrid() && s.getUmgangminute().equals(Settings.Umgangminute.minuteword))
				)
			return getOfficialConstruction(tiw);



        if (isNotEmpty(tiw2.getMinute1())) {
            tiw.setMinute1(tiw2.getMinute1() + " ");

            if(s.getMinute()) {
                if (p.getMinutes() > 1)
                    tiw.setMinute("Minuten ");
                else
                    tiw.setMinute("Minute ");
            }
        }
        if (isNotEmpty(tiw2.getVornach())) {
            tiw.setVornach(tiw2.getVornach() + " ");

        }

        if (isNotEmpty(tiw2.getMinute2())) {
            tiw.setMinute2(tiw2.getMinute2() + " ");
        }

        tiw.setHour(getHour(p, s, tiw2.getPlusHour()));

    }

    public void getOfficialConstruction(TimeInWordsDto tiw) {
        //official time
        tiw.setPlusHour(Boolean.FALSE);
        tiw.setHour(getHour(tiw));
        tiw.merge(getMinute(tiw));
    }


	public void getBegin(TimeInWordsDto tiw) {
		if(tiw.getSettings().getEsist())
			tiw.setBegin("Es ist");

	}






    public TimeInWordsDto getMinute(Pieces p, Settings s) {
        TimeInWordsDto timeInWordsDto = new TimeInWordsDto();
        if (!s.getUmgangssprachlich())
            //just official
            timeInWordsDto = getMinuteOfficial(p, s);

        if (s.getUmgangssprachlich()) {
			if( (p.getRemainderMinutes() > 0)
					&& s.getMinuteHybrid()
					&& s.getUmgangminute().equals(Settings.Umgangminute.minuteword))
				timeInWordsDto = getMinuteOfficial(p, s);
            //there is a simplified time, rounded to 5 minutes, to support Settings.layout.block
            //or there is a more complete time which can only be displayed using Settings.layout.sentance
            else
                timeInWordsDto = getUmgangMinutes(p, s);
        }

        timeInWordsDto.setPieces(p);
        timeInWordsDto.setSettings(s);

        return timeInWordsDto;
    }


    public TimeInWordsDto getMinuteOfficial(Pieces p, Settings s){
        Integer number = new Integer(p.getMinutes());
        TimeInWordsDto ret = new TimeInWordsDto();
        Resources res = getContext().getResources();
        String[] german_number = res.getStringArray(R.array.german_number);
        if(s.getMinute() && p.getMinutes() == 1)
            ret.setMinute1(german_number[0]);
        else
            ret.setMinute1(german_number[p.getMinutes()]);

        ret.setPlusHour(Boolean.FALSE);
        return ret;

    }


    public TimeInWordsDto getUmgangMinutes(Pieces p, Settings s) {
		TimeInWordsDto word = new TimeInWordsDto();





        Integer testMinute;


        //this block here is for the case where s.MinuteHybrid() is false
        //here we still use vor and nach but with the full minutes in words
        if(s.getUmgangminute() == Settings.Umgangminute.minuteword) {
            testMinute = p.getMinutes();
            //set up defaults
            Integer umgangMinutes = p.getMinutes();
            if(umgangMinutes > 30)
                umgangMinutes = 60 - umgangMinutes;
            Resources res = getContext().getResources();
            String[] german_number = res.getStringArray(R.array.german_number);

            if(s.getMinute() && p.getMinutes() == 1)
                word.setMinute1(german_number[0]);
            else
                word.setMinute1(german_number[p.getMinutes()]);


            word.setVornach((p.getMinutes() <= 30)?"nach":"vor");
            if(p.getMinutes() > 30)
                word.setPlusHour(Boolean.TRUE);
        }
        else
            testMinute = p.getFiveMinBucket();

		switch (testMinute) {
			 case 0:
				 if(s.getKurznach() && p.getMinutes() > 0) {
                     word.setMinute1("kurz");
                     word.setVornach("nach");
                 }
                 break;
			 case 5:
                     word.setMinute1("fünf");
                     word.setVornach("nach");
                 break;
			 case 10:
                 if( (s.getViertel() == Settings.Viertel.viertelacht)
                         && s.getFuenfvorviertelacht()) {
                     word.setMinute1("fünf");
                     word.setVornach("vor");
                     word.setMinute2("viertel");
                     word.setPlusHour(Boolean.TRUE);
                 }
                 else {
                     word.setMinute1("zehn");
                     word.setVornach("nach");
                 }
                 break;
			 case 15:  
			 	if(s.getViertel() == Settings.Viertel.viertelacht) {
                    word.setMinute1("viertel");
                    word.setPlusHour(Boolean.TRUE);
                }
			 	else if(s.getViertel() == Settings.Viertel.viertelnach) {
                    word.setMinute1("viertel");
                    word.setVornach("nach");
                }
                else if(s.getViertel() == Settings.Viertel.viertelueber) {
                    word.setMinute1("viertel");
                    word.setVornach("über");
                }
			 	break;
			 case 20:
                 if(s.getZehnvorhalb()){
                     word.setMinute1("zehn");
                     word.setVornach("vor");
                     word.setMinute2("halb");
                     word.setPlusHour(Boolean.TRUE);
                 }
                 else if(s.getZwanzignach()) {
                     word.setMinute1("zwanzig");
                     word.setVornach("nach");
                 }

				break;
			 case 25:
                 if(s.getHalb()
                         && s.getFuenfvorhalb()) {
                     word.setMinute1("fünf");
                     word.setVornach("vor");
                     word.setMinute2("halb");
                     word.setPlusHour(Boolean.TRUE);
                 }
				else {
                     word.setMinute1("fünfundzwanzig");
                     word.setVornach("nach");
                 }
			 	break;
			 case 30:  
				if(s.getHalb()) {
                    word.setMinute1("halb");
                    word.setPlusHour(Boolean.TRUE);
                }
                else if (s.getDreissignach()) {
                    word.setMinute1("dreißig");
                    word.setVornach("nach");
                }
                break;
			 case 35: 
				if(s.getHalb()
                 && s.getFuenfnachhalb()) {
                    word.setMinute1("fünf");
                    word.setVornach("nach");
                    word.setMinute2("halb");
                    word.setPlusHour(Boolean.TRUE);
                }
			 	else {
			 		word.setMinute1("fünfundzwanzig");
                    word.setVornach("vor");
                    word.setPlusHour(Boolean.TRUE);
			 	}
			 case 40:  
				if(s.getDreiviertel() == Settings.Dreiviertel.dreiviertelacht) {
                    word.setMinute1("fünf");
                    word.setVornach("vor");
                    word.setMinute2("drei viertel");
                    word.setPlusHour(Boolean.TRUE);
                }
			 	else {
                    word.setMinute1("zwanzig");
                    word.setVornach("vor");
			 	}
			 	break;
			 case 45:
                 if(s.getDreiviertel() == Settings.Dreiviertel.dreiviertelacht) {
                     word.setMinute2("drei viertel");
                     word.setPlusHour(Boolean.TRUE);
                 }
                 else if(s.getDreiviertel() == Settings.Dreiviertel.viertelvor){
                     word.setMinute2("viertel");
                     word.setVornach("vor");
                 }
                 else if(s.getDreiviertel() == Settings.Dreiviertel.fuenfzehn) {
                     word.setMinute1("fünfzehn");
                     word.setVornach("vor");
                 }

			 	break;
			 case 50:
                    word.setMinute1("zehn");
                    word.setVornach("vor");
			 	break;
			 case 55:
                 if(s.getKurzvor() && p.getMinutes() > 55) {
                     word.setMinute1("kurz");
                     word.setVornach("vor");
                 }
			 	break;             
	         //default: getMinuteOfficial(p);
	           // break;
		}

		return word;
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
                && !word.equals("eins"))
            word += "Uhr ";
        return word;
    }
    public StringBuilder getSectionOfDay(Settings s, Pieces p) {
        StringBuilder word = new StringBuilder();
//three night variatne, nach, früh and morgen, mutually exclusive
		if (p.getHr24() >= 0
				&& p.getHr24() < 5
                && s.getNachts())
			word.append("nachts ");
		else if (p.getHr24() >= 0
				&& p.getHr24() < 5
				&& s.getIndernacht())
			word.append("in der Nacht ");
		else if (p.getHr24() >= 0
				&& p.getHr24() < 5
				&& s.getInderfrueh())
			word.append("in der Früh ");
		else if (p.getHr24() >= 0
				&& p.getHr24() < 5
                && s.getMorgennacht())
			word.append("moregens ");
		else if (p.getHr24() >= 0
				&& p.getHr24() < 5
				&& s.getAmmorgennacht())
			word.append("am Morgen ");

		if (p.getHr24() >= 5
				&& p.getHr24() < 10
				&& s.getMorgens())
			word.append("morgens ");
		if (p.getHr24() >= 5
				&& p.getHr24() < 10
				&& s.getAmmorgen())
			word.append("am Morgen ");


		if (p.getHr24() >= 10
				&& p.getHr24() < 12
				&& s.getVormittags())
            word.append("vormittags ");
		if (p.getHr24() >= 10
				&& p.getHr24() < 12
				&& s.getAmmorgen())
			word.append("am Vormittag ");


		if (p.getHr24() >= 12
				&& p.getHr24() < 18
				&& s.getNachmittags())
			word.append("nachmittags ");
		if (p.getHr24() >= 12
				&& p.getHr24() < 18
                && s.getAmnachmittag())
			word.append("am Nachmittag ");


		if (p.getHr24() >= 18
				&& p.getHr24() <= 23
				&& s.getAbends())
			word.append("abends ");
		if (p.getHr24() >= 18
				&& p.getHr24() <= 23
				&& s.getAmabend())
			word.append("am Abend ");


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
