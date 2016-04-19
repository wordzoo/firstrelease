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

    private HalberMinute hm = null;
    private KurzMinute km = null;
    private ViertelMinute vm = null;
    private HalbMinute hb = null;
    private DreiviertelMinute dm = null;
    private UmgangsMinute um = null;

    public TimeInWords() {


    }

    public TimeInWords(Context context) {
        this.context = context;
        Resources res = getContext().getResources();
        this.german_number = res.getStringArray(R.array.german_number);
        this.hm = new HalberMinute(german_number);
        this.km = new KurzMinute(german_number);
        this.vm = new ViertelMinute(german_number);
        this.hb = new HalbMinute(german_number);
        this.dm = new DreiviertelMinute(german_number);
        this.um = new UmgangsMinute(german_number);
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
        if(!adjustAgainstEinsMinute(tiw))
            tiw.setMinute1(german_number[tiw.getPieces().getMinutes()]);


    }



    public void getUmgangMinutes(TimeInWordsDto tiw) {

        Integer testMinute = tiw.getPieces().getMinutes();

        //these are the basic umgang minutes....
        if (testMinute > 30)
            testMinute = 60 - testMinute;

        tiw.setMinute1(german_number[testMinute]);



        if(tiw.getPieces().getRemainderMinutes() > 0 && tiw.getSettings().getMinuteHybrid() == Boolean.TRUE) {
            tiw.setVornach("");
            tiw.setPlusHour(Boolean.FALSE); //go to official format
        } else {
            if (tiw.getPieces().getMinutes() == 0) {
                tiw.setVornach("");
                tiw.setMinute1("");
            }
            else if (tiw.getPieces().getMinutes() <= 30) {
                tiw.setVornach("nach");
                tiw.setPlusHour(Boolean.FALSE);
            } else {
                tiw.setVornach("vor");
                tiw.setPlusHour(Boolean.TRUE);
            }
        }


       // here we have the priority of treatment of minutes
        //this priority also applies to the setting dialog
        // e.g. if you choose a halber setting, it will
        //likely deactivate kurz settings related to halb (e.g. kurz for halb)

        //these "get" fuctions will set the minutes if BOTH the settings and time apply
        //and in that case return true....

        //HALBER
        if(hm.getUmgangsMinute(tiw))
            return;

        //KURZ VOR NACH
        if( km.getUmgangsMinute(tiw))
            return;

        //VIERTEL
        if( vm.getUmgangsMinute(tiw))
            return;

        //HALB
        if( hb.getUmgangsMinute(tiw))
            return;

        //DREIVIERTEL
        if( dm.getUmgangsMinute(tiw))
            return;

        //HYBRID -- means, if non of the rules above applied,
        // and minutes are not a multiple of five,
        // instead of "Einundzwanzig Uhr siebzehn Minuten"
        // you can switch to official short "siebzehn nach elf"
        if(hasHybrid(tiw)) {
            getMinuteDetail(tiw);
            return;
        }

        //UMGANGSPRACHLICH
        if(um.getUmgangsMinute(tiw))
            return;

        //only called if we fell through to getUmgangsMinute() and returned false
       adjustAgainstEinsMinute(tiw);

    }

    public Boolean adjustAgainstEinsMinute(TimeInWordsDto tiw) {
        Boolean ret = Boolean.FALSE;

        if (tiw.getPieces().getMinutes() == 0) {
            tiw.setMinute1("");
            ret = Boolean.TRUE;
        }
        else if (tiw.getSettings().getMinute() && tiw.getPieces().getMinutes() == 1) {
            tiw.setMinute1(german_number[0]);
            ret = Boolean.TRUE;
        }

        return ret;
    }


    public Boolean hasHybrid(TimeInWordsDto tiw) {
        if(tiw.getSettings().getMinuteHybrid()
               && tiw.getPieces().getRemainderMinutes() > 0)
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


        if (
                (tiw.getPieces().getHr24() == 0 && tiw.getPlusHour() == Boolean.FALSE)
                || (tiw.getPieces().getHr24() == 23 && tiw.getPlusHour() == Boolean.TRUE) ) {
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
        if ( (tiw.getPieces().getHr24() >= 0
                && tiw.getPieces().getHr24() < 5)
                || tiw.getPieces().getHr24() >= 22) {
            if (tiw.getSettings().getNachts())
                tiw.setSectionOfDay("nachts");
            if (tiw.getSettings().getIndernacht())
                tiw.setSectionOfDay("in der Nacht");
        }

        if (tiw.getPieces().getHr24() >= 0
                && tiw.getPieces().getHr24() < 5) {
            if (tiw.getSettings().getInderfrueh())
                tiw.setSectionOfDay("in der Früh");
            if (tiw.getSettings().getMorgennacht())
                tiw.setSectionOfDay("morgens");
            if (tiw.getSettings().getAmmorgennacht())
                tiw.setSectionOfDay("am Morgen");
        }

        if (tiw.getPieces().getHr24() >= 5
                && tiw.getPieces().getHr24() < 10) {
            if (tiw.getSettings().getMorgens())
                tiw.setSectionOfDay("morgens");
            if (tiw.getSettings().getAmmorgen())
                tiw.setSectionOfDay("am Morgen");
        }

        if (tiw.getPieces().getHr24() >= 10
                && tiw.getPieces().getHr24() < 12) {

            if (tiw.getSettings().getVormittags())
                tiw.setSectionOfDay("vormittags");
            if (tiw.getSettings().getAmvormittag())
                tiw.setSectionOfDay("am Vormittag");
        }

        if (tiw.getPieces().getHr24() >= 12
                && tiw.getPieces().getHr24() < 14) {
            if (tiw.getSettings().getMittags())
                tiw.setSectionOfDay("mittags");

            if (tiw.getSettings().getAmmittag())
                tiw.setSectionOfDay("am Mittag");
        }

        if (tiw.getPieces().getHr24() >= 14
                && tiw.getPieces().getHr24() < 18) {
            if (tiw.getSettings().getNachmittags())
                tiw.setSectionOfDay("nachmittags");

            if (tiw.getSettings().getAmnachmittag())
                tiw.setSectionOfDay("am Nachmittag");
        }

        if (tiw.getPieces().getHr24() >= 18
                && tiw.getPieces().getHr24() < 22) {
            if (tiw.getSettings().getAbends())
                tiw.setSectionOfDay("abends");
            if (tiw.getSettings().getAmabend())
                tiw.setSectionOfDay("am Abend");
        }

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
