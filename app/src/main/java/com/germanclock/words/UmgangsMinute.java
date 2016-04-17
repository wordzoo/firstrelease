package com.germanclock.words;

import com.germanclock.time.Settings;

/**
 * Created by ich on 17.04.2016.
 */
public class UmgangsMinute {
    public void getUmgangsMinute(TimeInWordsDto tiw) {

        Integer testMinute = tiw.getPieces().getMinutes();

        if (testMinute > 30)
            testMinute = 60 - testMinute;

        if (tiw.getPieces().getMinutes() <= 30)
            tiw.setVornach("nach");
        else {
            tiw.setVornach("vor");
            tiw.setPlusHour(Boolean.TRUE);
        }


        //set up language for detailed minutes
        if (tiw.getSettings().getUmgangminute() == Settings.Umgangminute.minuteword) {

            //this is for cases when we do not want to use the five minute buckets
            if (tiw.getSettings().getMinuteHybrid()
                    && (tiw.getPieces().getRemainderMinutes() > 0)
                    && !hasKurz(tiw)
                    && !hasHalber(tiw))
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
                if(tiw.getSettings().getHalber() &&
                        tiw.getSettings().getHalberRange() >= (10 - tiw.getPieces().getRemainderMinutes())) {
                    tiw.setMinute1(german_number[(10 - tiw.getPieces().getRemainderMinutes())]);
                    tiw.setVornach("vor");
                    tiw.setMinute2("halber");
                }
                else if (tiw.getSettings().getZehnvorhalb()) {
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
                if(tiw.getSettings().getHalber() &&
                        tiw.getSettings().getHalberRange() >= (5 - tiw.getPieces().getRemainderMinutes())) {
                    tiw.setMinute1(german_number[(5 - tiw.getPieces().getRemainderMinutes())]);
                    tiw.setVornach("vor");
                    tiw.setMinute2("halber");
                }
                else if (tiw.getSettings().getKurzvorhalb() && tiw.getPieces().getMinutes() > 25) {
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
                if(tiw.getSettings().getHalber()
                        && tiw.getSettings().getHalberRange() >= (tiw.getPieces().getRemainderMinutes())) {
                    if(tiw.getPieces().getRemainderMinutes() == 0) {
                        tiw.setMinute1("");
                        tiw.setVornach("");
                        tiw.setMinute2("halber");
                    }
                    else {
                        tiw.setMinute1(german_number[tiw.getPieces().getRemainderMinutes()]);
                        tiw.setVornach("nach");
                        tiw.setMinute2("halber");
                    }
                }
                else if (tiw.getSettings().getKurznachhalb()
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
                if(tiw.getSettings().getHalber()
                        && tiw.getSettings().getHalberRange() >= (5 + tiw.getPieces().getRemainderMinutes())) {
                    tiw.setMinute1(german_number[tiw.getPieces().getRemainderMinutes() + 5]);
                    tiw.setVornach("nach");
                    tiw.setMinute2("halber");
                }
                else if (tiw.getSettings().getKurznachhalb() && tiw.getPieces().getMinutes() < 35) {
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
                if(tiw.getSettings().getHalber() &&
                        tiw.getSettings().getHalberRange() >= (10 - tiw.getPieces().getRemainderMinutes())) {
                    tiw.setMinute1(german_number[(10 - tiw.getPieces().getRemainderMinutes())]);
                    tiw.setVornach("nach");
                    tiw.setMinute2("halber");
                }
                else if (tiw.getSettings().getKurzvordreiviertelacht()
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
}
