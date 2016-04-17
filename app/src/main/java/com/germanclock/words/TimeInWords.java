package com.germanclock.words;

import android.content.Context;
import android.content.res.Resources;

import com.germanclock.time.Pieces;
import com.germanclock.time.Settings;
import com.wordzoo.uhr.R;


public class TimeInWords {

    /*
    public static final Parcelable.Creator<germanClock> CREATOR = new Parcelable.Creator<germanClock>() {

        public GermanClock createFromParcel(Parcel in) {
            return new GermanClock(in);
        }

        public GermanClock[] newArray(int size) {
            return new GermanClock[size];
        }

    };

    @Override
    public void writeToParcel(Parcel out, int flags) {
        ;
    }

    public GermanClock(Parcel in) {
        readFromParcel(in);
    }

    @Override
    public int describeContents() {
        return 0;
    }


    public void readFromParcel(Parcel in) {

    }*/
    private Context context;

    public Context getContext() {
        return this.context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public TimeInWords() {


    }

    public TimeInWords(Context context) {
        this.context = context;
        Resources res = getContext().getResources();
        this.german_number = res.getStringArray(R.array.german_number);
    }


    private String[] german_number = new String[0];


    public String getTimeAsSentance(Pieces p, Settings s) {

        TimeInWordsDto tiw = new TimeInWordsDto(p, s);
        developTimeWords(tiw);
        return assembleTextTime(tiw);
    }

    public void developTimeWords(TimeInWordsDto tiw) {
        //by ref populate

        // the beginning
        getBegin(tiw);

        //minutes
        if (!tiw.getSettings().getUmgangssprachlich() )
            getMinuteDetail(tiw);
        else
            getUmgangMinutes(tiw);

        //pupulate word "minute" or "Minutes"
        if (tiw.getSettings().getMinute()) {
            if (tiw.getPieces().getMinutes() == 1)
                tiw.setMinute("Minute");
            else if (tiw.getPieces().getMinutes() > 1)
                tiw.setMinute("Minuten");
        }

        //hour
        getHour(tiw);

        //populate word "Uhr"
        if (tiw.getSettings().getUhr()
                && !tiw.getHour().equals("Mitternacht")
                && !tiw.getHour().equals("eins")
                && !tiw.getMinute1().equals("viertel")
                && !tiw.getMinute1().equals("halb")
                && !tiw.getMinute1().equals("dreiviertel")
                && !tiw.getMinute2().equals("viertel")
                && !tiw.getMinute2().equals("halb")
                && !tiw.getMinute2().equals("dreiviertel")
                && !tiw.getMinute2().equals("halber")
                )
            tiw.setUhr("Uhr");

        //by sectoin of day
        getSectionOfDay(tiw);
    }

    public String assembleTextTime(TimeInWordsDto tiw) {
        //build output string
        StringBuilder ret = new StringBuilder();

        //common to all registers of time expressions
        if (isNotEmpty(tiw.getBegin())) {
            ret.append(tiw.getBegin());
            ret.append(" ");
        }

        //Official Construction
        if (!tiw.getSettings().getUmgangssprachlich()
                || ((tiw.getPieces().getRemainderMinutes() > 0)
                && (tiw.getSettings().getMinuteHybrid()
                && tiw.getSettings().getUmgangminute().equals(Settings.Umgangminute.minuteword)
                && !tiw.getSettings().getHalber()))) {

            if (isNotEmpty(tiw.getHour())) {
                ret.append(tiw.getHour());
                ret.append(" ");
            }

            if (isNotEmpty(tiw.getUhr())) {
                ret.append(tiw.getUhr());
                ret.append(" ");
            }

            if (isNotEmpty(tiw.getMinute1())) {
                ret.append(tiw.getMinute1());
                ret.append(" ");
            }

            //the word "Minute" or "Minuten"
            if (isNotEmpty(tiw.getMinute())) {
                ret.append(tiw.getMinute());
                ret.append(" ");
            }

            if (tiw.getSettings().getMinuteHybrid()
                    && !tiw.getHour().contains("Mitternacht"))
                if (isNotEmpty(tiw.getSectionOfDay())) {
                    ret.append(tiw.getSectionOfDay());
                    ret.append(" ");
                }


        }

        //umgansprachlich
        else {

            if (isNotEmpty(tiw.getMinute1())) {
                ret.append(tiw.getMinute1());
                ret.append(" ");
            }

            //word "Minute" or "Minuten"
            if (isNotEmpty(tiw.getMinute())
                    && !tiw.getMinute1().equals("viertel")
                    && !tiw.getMinute1().equals("dreiviertel")
                    && !tiw.getMinute1().equals("halb")
                    && !tiw.getMinute1().equals("kurz")
                    ) {
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


            if (isNotEmpty(tiw.getSectionOfDay())) {
                ret.append(tiw.getSectionOfDay());
                ret.append(" ");
            }

            if(tiw.getSettings().getUmgangminute().equals(Settings.Umgangminute.minutebar)
                    && tiw.getPieces().getRemainderMinutes() > 0) {
                ret.append(" ");
                for(int x = 1; x <= tiw.getPieces().getRemainderMinutes();x++) {
                    ret.append(" ^ ");
                }
            }
        }
        return ret.toString().trim();
    }


    public void getBegin(TimeInWordsDto tiw) {
        if (tiw.getSettings().getEsist())
            tiw.setBegin("Es ist");

    }

    public void getMinuteDetail(TimeInWordsDto tiw) {
        Integer number = new Integer(tiw.getPieces().getMinutes());

        if (tiw.getPieces().getMinutes() == 0)
            tiw.setMinute1("");
        else if (tiw.getSettings().getMinute() && tiw.getPieces().getMinutes() == 1)
            tiw.setMinute1(german_number[0]);
        else
            tiw.setMinute1(german_number[number]);
    }

    private HalberMinute hm = new HalberMinute(german_number);
    private KurzMinute km = new KurzMinute(german_number);
    private ViertelMinute vm = new ViertelMinute(german_number);
    private UmgangsMinute um = new UmgangsMinute(german_number);

    public void getUmgangMinutes(TimeInWordsDto tiw) {

       // here we have the priority of treatment of minutes
        //this priority also applies to the setting dialog
        // e.g. if you choose a halber setting, it will
        //likely deactivate kurz settings related to halb (e.g. kurz for halb)

        //these "get" fuctions will set the minutes if BOTH the settings and time apply
        //and in that case return true....

        if(hm.getUmgangsMinute(tiw))
            return;

        if( km.getUmgangsMinute(tiw))
            return;

        if( vm.getUmgangsMinute(tiw))
            return;

        if(hasHybrid(tiw)) {
            getMinuteDetail(tiw);
            return;
        }
        um.getUmgangsMinute(tiw);
    }



    public Boolean hasHybrid(TimeInWordsDto tiw) {
        if(tiw.getPieces().getRemainderMinutes() > 0)
            return Boolean.TRUE;
        else
            return Boolean.FALSE;
    }




    public void getHour(TimeInWordsDto tiw) {
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


        if (number == 0) {
            //note: umgangsprachlich does not work with null
            if (tiw.getSettings().getUmgangssprachlich() || tiw.getSettings().getMitternacht())
                word = "Mitternacht";
            else
                word = "null";
        } else {
            if (tiw.getSettings().getUhr()
                    && number == 1
                    && !tiw.getMinute1().equals("viertel")
                    && !tiw.getMinute1().equals("halb")
                    && !tiw.getMinute1().equals("dreiviertel")
                    && !tiw.getMinute2().equals("viertel")
                    && !tiw.getMinute2().equals("halb")
                    && !tiw.getMinute2().equals("dreiviertel"))
                word = german_number[0];
            else
                word = german_number[number];
        }

        if(tiw.getMinute2().equals("halber"))
            tiw.setHour("");
        else
            tiw.setHour(word);
    }


    public void getSectionOfDay(TimeInWordsDto tiw) {

        if(tiw.getMinute2().equals("halber")
                || tiw.getHour().contains("Mitternacht")) {
            tiw.setSectionOfDay("");
            return;
        }


//three night variatne, nach, früh and morgen, mutually exclusive
        if (tiw.getPieces().getHr24() >= 0
                && tiw.getPieces().getHr24() < 5
                && tiw.getSettings().getNachts())
            tiw.setSectionOfDay("nachts");
        else if (tiw.getPieces().getHr24() >= 0
                && tiw.getPieces().getHr24() < 5
                && tiw.getSettings().getIndernacht())
            tiw.setSectionOfDay("in der Nacht");
        else if (tiw.getPieces().getHr24() >= 0
                && tiw.getPieces().getHr24() < 5
                && tiw.getSettings().getInderfrueh())
            tiw.setSectionOfDay("in der Früh");
        else if (tiw.getPieces().getHr24() >= 0
                && tiw.getPieces().getHr24() < 5
                && tiw.getSettings().getMorgennacht())
            tiw.setSectionOfDay("morgens");
        else if (tiw.getPieces().getHr24() >= 0
                && tiw.getPieces().getHr24() < 5
                && tiw.getSettings().getAmmorgennacht())
            tiw.setSectionOfDay("am Morgen");

        if (tiw.getPieces().getHr24() >= 5
                && tiw.getPieces().getHr24() < 10
                && tiw.getSettings().getMorgens())
            tiw.setSectionOfDay("morgens");
        if (tiw.getPieces().getHr24() >= 5
                && tiw.getPieces().getHr24() < 10
                && tiw.getSettings().getAmmorgen())
            tiw.setSectionOfDay("am Morgen");


        if (tiw.getPieces().getHr24() >= 10
                && tiw.getPieces().getHr24() < 12
                && tiw.getSettings().getVormittags())
            tiw.setSectionOfDay("vormittags");
        if (tiw.getPieces().getHr24() >= 10
                && tiw.getPieces().getHr24() < 12
                && tiw.getSettings().getAmmorgen())
            tiw.setSectionOfDay("am Vormittag");


        if (tiw.getPieces().getHr24() >= 12
                && tiw.getPieces().getHr24() < 18
                && tiw.getSettings().getNachmittags())
            tiw.setSectionOfDay("nachmittags");
        if (tiw.getPieces().getHr24() >= 12
                && tiw.getPieces().getHr24() < 18
                && tiw.getSettings().getAmnachmittag())
            tiw.setSectionOfDay("am Nachmittag");


        if (tiw.getPieces().getHr24() >= 18
                && tiw.getPieces().getHr24() <= 23
                && tiw.getSettings().getAbends())
            tiw.setSectionOfDay("abends");
        if (tiw.getPieces().getHr24() >= 18
                && tiw.getPieces().getHr24() <= 23
                && tiw.getSettings().getAmabend())
            tiw.setSectionOfDay("am Abend");



    }

    public Boolean isNotEmpty(String in) {
        if (in != null && in != "")
            return Boolean.TRUE;
        else
            return Boolean.FALSE;
    }


	/*
        //Frank von Dambach Deutchland (Sud Ost) hat geschrieben:
		 * good to hear from you ;-)
Interesting task üwriting a german word clockü ;-)

From my perspective I dont really see differences to articulate the clock time in different german areas.
and I see no differences between formal and commonly use of the time. perhaps, some minimal usage in special case like
for example 7:35 or 7:25 - in this cases we say twenty-five minutes after seven - fünfundzwanzig minuten nach sieben-
or five minutes to half eight - fünf minuten vor halb acht.

This brings me to a generell statement:
We bring the time never in relationship to the quarter or three quarter only to the full hour or half hour.

Your examples:
8:05 - we dont say zehn vor viertel nach acht! we say only fünf Minuten nach acht
8:10 - we say only 10 Minuten nach acht
8:20 - we say zwanzig Minuten nach acht or zehn Minuten vor halb neun (both is possible)
8:40 - we say often zwanzig Minuten vor neun or zehn Minuten nach halb neun

To shorten this forms, we lost the words minutes. Instead of "zehn Minuten nach acht" - you can say "zehn nach acht"

We dont use the forms zehn vor dreiviertel neun (8:35)

AND we use in colloquial way very often the shortest form like acht uhr zwanzig for 8:20
some examples:
8:05 - acht uhr fünf
8:17 - acht uhr siebzehn
8:35 - acht uhr fünfunddreißig
8:45 - acht uhr fünfundvierzig
8:50 - acht uhr fünfzig

For Midnight we say Mitternacht und for 00:05 we say null Uhr fünf or fünf Minuten nach Mitternacht
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
