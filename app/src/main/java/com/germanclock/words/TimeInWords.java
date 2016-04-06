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

    public TimeInWords() {
        ;
    }

    public TimeInWords(Context context) {
        this.context = context;
    }

    public String getTimeAsSentance(Pieces p, Settings s) {

        TimeInWordsDto tiw = new TimeInWordsDto(p, s);
        developTimeWords(tiw);
        return assembleTextTime(tiw);
    }

    public void developTimeWords(TimeInWordsDto tiw) {
        //by ref populate the beginning
        getBegin(tiw);

        //by ref populate minutes
        if (!tiw.getSettings().getUmgangssprachlich())
            getMinuteDetail(tiw, Boolean.TRUE);
        else
            getUmgangMinutes(tiw);

        //pupulate word "minute" or "Minutes"
        if (tiw.getSettings().getMinute()) {
            if (tiw.getPieces().getMinutes() == 1)
                tiw.setMinute("Minute");
            else if (tiw.getPieces().getMinutes() > 1)
                tiw.setMinute("Minuten");
        }

        //by ref popualte hour
        getHour(tiw);

        //populate wod "Uhr"
        if (tiw.getSettings().getUhr()
                && !tiw.getHour().equals("Mitternacht")
                && !tiw.getHour().equals("eins")
                && !tiw.getMinute1().equals("viertel")
                && !tiw.getMinute1().equals("halb")
                && !tiw.getMinute1().equals("dreiviertel")
                && !tiw.getMinute2().equals("viertel")
                && !tiw.getMinute2().equals("halb")
                && !tiw.getMinute2().equals("dreiviertel")
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
                && tiw.getSettings().getUmgangminute().equals(Settings.Umgangminute.minuteword)))) {

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

            if (!tiw.getHour().contains("Mitternacht"))
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


    public void getMinuteDetail(TimeInWordsDto tiw, Boolean official) {
        Integer number = new Integer(tiw.getPieces().getMinutes());

        if (!official) {
            if (number > 30)
                number = 60 - number;

            if (tiw.getPieces().getMinutes() <= 30)
                tiw.setVornach("nach");
            else {
                tiw.setVornach("vor");
                tiw.setPlusHour(Boolean.TRUE);
            }
        } else {
            tiw.setPlusHour(Boolean.FALSE);
        }
        Resources res = getContext().getResources();
        String[] german_number = res.getStringArray(R.array.german_number);
        if (tiw.getPieces().getMinutes() == 0)
            tiw.setMinute1("");
        else if (tiw.getSettings().getMinute() && tiw.getPieces().getMinutes() == 1)
            tiw.setMinute1(german_number[0]);
        else
            tiw.setMinute1(german_number[number]);


    }


    public Boolean hasKurz(TimeInWordsDto tiw) {


        if (tiw.getSettings().getKurznach()
                && (tiw.getPieces().getMinutes() > 0)
                && (tiw.getPieces().getMinutes() < 5))
            return Boolean.TRUE;


        if (tiw.getSettings().getKurzvor()
                && (tiw.getPieces().getMinutes() > 55)
                && (tiw.getPieces().getMinutes() <= 59))
            return Boolean.TRUE;

        if (tiw.getSettings().getKurzvorviertelacht()
                && (tiw.getPieces().getMinutes() > 10)
                && (tiw.getPieces().getMinutes() < 15))
            return Boolean.TRUE;
        if (tiw.getSettings().getKurznachviertelacht()
                && (tiw.getPieces().getMinutes() > 15)
                && (tiw.getPieces().getMinutes() < 20))
            return Boolean.TRUE;

        if (tiw.getSettings().getKurzvorhalb()
                && (tiw.getPieces().getMinutes() > 25)
                && (tiw.getPieces().getMinutes() < 30))
            return Boolean.TRUE;

        if (tiw.getSettings().getKurznachhalb()
                && (tiw.getPieces().getMinutes() > 30)
                && (tiw.getPieces().getMinutes() < 35))
            return Boolean.TRUE;


        if (tiw.getSettings().getKurzvordreiviertelacht()
                && (tiw.getPieces().getMinutes() > 40)
                && (tiw.getPieces().getMinutes() < 45))
            return Boolean.TRUE;

        if (tiw.getSettings().getKurznachdreiviertelacht()
                && (tiw.getPieces().getMinutes() > 45)
                && (tiw.getPieces().getMinutes() < 50))
            return Boolean.TRUE;

        return Boolean.FALSE;
    }

    public void getUmgangMinutes(TimeInWordsDto tiw) {

        Integer testMinute;


        //set up language for detailed minutes
        if (tiw.getSettings().getUmgangminute() == Settings.Umgangminute.minuteword) {
            //serves as default values if the special cases below to not apply
            getMinuteDetail(tiw, Boolean.FALSE);

            //this is for cases when we do not want to use the five minute buckets
            if (tiw.getSettings().getMinuteHybrid()
                    && tiw.getPieces().getRemainderMinutes() > 0)
                return;
            else if ((tiw.getPieces().getRemainderMinutes() > 0)
                    && tiw.getSettings().getUmgangminute().equals(Settings.Umgangminute.minuteword)
                    && !hasKurz(tiw))
                return;
        }

        //5 minute bucket is nearest multiple of five below current minute
        switch (tiw.getPieces().getFiveMinBucket()) {
            case 0:
                if (tiw.getSettings().getKurznach() && tiw.getPieces().getMinutes() > 0) {
                    tiw.setMinute1("kurz");
                    tiw.setVornach("nach");
                }
                break;
            case 5:
                tiw.setMinute1("fünf");
                tiw.setVornach("nach");
                break;
            case 10:
                if ((tiw.getSettings().getViertel() == Settings.Viertel.viertelacht)
                        && tiw.getSettings().getFuenfvorviertelacht()) {
                    tiw.setMinute1("fünf");
                    tiw.setVornach("vor");
                    tiw.setMinute2("viertel");
                    tiw.setPlusHour(Boolean.TRUE);
                } else {
                    tiw.setMinute1("zehn");
                    tiw.setVornach("nach");
                }
                break;
            case 15:
                if (tiw.getSettings().getViertel() == Settings.Viertel.viertelacht) {
                    tiw.setMinute1("viertel");
                    tiw.setVornach("");
                    tiw.setPlusHour(Boolean.TRUE);
                } else if (tiw.getSettings().getViertel() == Settings.Viertel.viertelnach) {
                    tiw.setMinute1("viertel");
                    tiw.setVornach("nach");
                } else if (tiw.getSettings().getViertel() == Settings.Viertel.viertelueber) {
                    tiw.setMinute1("viertel");
                    tiw.setVornach("über");
                } else if (tiw.getSettings().getViertel() == Settings.Viertel.viertelfuenfzehn) {
                    tiw.setMinute1("fünfzehn");
                    tiw.setVornach("nach");
                }
                break;
            case 20:
                if (tiw.getSettings().getZehnvorhalb()) {
                    tiw.setMinute1("zehn");
                    tiw.setVornach("vor");
                    tiw.setMinute2("halb");
                    tiw.setPlusHour(Boolean.TRUE);
                } else if (tiw.getSettings().getViertel() == Settings.Viertel.viertelacht
                        && tiw.getSettings().getFuenfnachviertelacht()) {
                    tiw.setMinute1("fünf");
                    tiw.setVornach("nach");
                    tiw.setMinute2("viertel");
                    tiw.setPlusHour(Boolean.TRUE);
                } else { // default is tiw.getSettings().getZwanzignach()
                    tiw.setMinute1("zwanzig");
                    tiw.setVornach("nach");
                }

                break;
            case 25:
                if (tiw.getSettings().getKurzvorhalb() && tiw.getPieces().getMinutes() > 25) {
                    tiw.setMinute1("kurz");
                    tiw.setVornach("vor");
                    tiw.setMinute2("halb");
                    tiw.setPlusHour(Boolean.TRUE);
                } else if (tiw.getSettings().getFuenfvorhalb()) {
                    tiw.setMinute1("fünf");
                    tiw.setVornach("vor");
                    tiw.setMinute2("halb");
                    tiw.setPlusHour(Boolean.TRUE);
                } else {
                    tiw.setMinute1("fünfundzwanzig");
                    tiw.setVornach("nach");
                }
                break;
            case 30:
                if (tiw.getSettings().getKurznachhalb()
                        && tiw.getPieces().getMinutes() > 30) {
                    tiw.setMinute1("kurz");
                    tiw.setVornach("nach");
                    tiw.setMinute2("halb");
                    tiw.setPlusHour(Boolean.TRUE);
                } else if (tiw.getSettings().getHalb() == Settings.Halb.halb) {
                    tiw.setMinute1("halb");
                    tiw.setVornach("");
                    tiw.setPlusHour(Boolean.TRUE);
                } else if (tiw.getSettings().getHalb() == Settings.Halb.dreissig) {
                    tiw.setMinute1("dreißig");
                    tiw.setVornach("nach");
                }
                break;
            case 35:
                if (tiw.getSettings().getKurznachhalb() && tiw.getPieces().getMinutes() < 35) {
                    tiw.setMinute1("kurz");
                    tiw.setVornach("nach");
                    tiw.setMinute2("halb");
                    tiw.setPlusHour(Boolean.TRUE);
                } else if (tiw.getSettings().getFuenfnachhalb()) {
                    tiw.setMinute1("fünf");
                    tiw.setVornach("nach");
                    tiw.setMinute2("halb");
                    tiw.setPlusHour(Boolean.TRUE);
                } else {
                    tiw.setMinute1("fünfundzwanzig");
                    tiw.setVornach("vor");
                    tiw.setPlusHour(Boolean.TRUE);
                }
                break;
            case 40:
                if (tiw.getSettings().getKurzvordreiviertelacht()
                        && tiw.getPieces().getMinutes() > 40) {
                    tiw.setMinute1("kurz");
                    tiw.setVornach("vor");
                    tiw.setMinute2("dreiviertel");
                    tiw.setPlusHour(Boolean.TRUE);
                } else if (tiw.getSettings().getDreiviertel() == Settings.Dreiviertel.dreiviertelacht) {
                    tiw.setMinute1("fünf");
                    tiw.setVornach("vor");
                    tiw.setMinute2("dreiviertel");
                    tiw.setPlusHour(Boolean.TRUE);
                } else {
                    tiw.setMinute1("zwanzig");
                    tiw.setVornach("vor");
                    tiw.setPlusHour(Boolean.TRUE);
                }
                break;
            case 45:
                if (tiw.getSettings().getKurznachdreiviertelacht()
                        && tiw.getPieces().getMinutes() > 45) {
                    tiw.setMinute1("kurz");
                    tiw.setVornach("nach");
                    tiw.setMinute2("dreiviertel");
                    tiw.setPlusHour(Boolean.TRUE);
                } else if (tiw.getSettings().getDreiviertel() == Settings.Dreiviertel.dreiviertelacht) {
                    tiw.setMinute1("dreiviertel");
                    tiw.setVornach("");
                    tiw.setMinute2("");
                    tiw.setPlusHour(Boolean.TRUE);
                } else if (tiw.getSettings().getDreiviertel() == Settings.Dreiviertel.viertelvor) {
                    tiw.setMinute1("viertel");
                    tiw.setVornach("vor");
                    tiw.setPlusHour(Boolean.TRUE);
                } else if (tiw.getSettings().getDreiviertel() == Settings.Dreiviertel.fuenfzehn) {
                    tiw.setMinute1("fünfzehn");
                    tiw.setVornach("vor");
                    tiw.setPlusHour(Boolean.TRUE);
                }

                break;
            case 50:
                if (tiw.getSettings().getFuenfnachdreiviertelacht()) {
                    tiw.setMinute1("fünf");
                    tiw.setVornach("nach");
                    tiw.setMinute2("dreiviertel");
                    tiw.setPlusHour(Boolean.TRUE);
                } else {
                    tiw.setMinute1("zehn");
                    tiw.setVornach("vor");
                    tiw.setPlusHour(Boolean.TRUE);
                }
                break;
            case 55:
                if (tiw.getSettings().getKurzvor() && tiw.getPieces().getMinutes() > 55) {
                    tiw.setMinute1("kurz");
                    tiw.setVornach("vor");
                    tiw.setPlusHour(Boolean.TRUE);
                } else {
                    tiw.setMinute1("fünf");
                    tiw.setVornach("vor");
                    tiw.setPlusHour(Boolean.TRUE);
                }
                break;

        }
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

        Resources res = getContext().getResources();
        String[] german_number = res.getStringArray(R.array.german_number);


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


        tiw.setHour(word);
    }


    public void getSectionOfDay(TimeInWordsDto tiw) {

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
