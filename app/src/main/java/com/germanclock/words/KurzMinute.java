package com.germanclock.words;

/**
 * Created by ich on 17.04.2016.
 */
public class KurzMinute extends Minute {
    public KurzMinute(String[] german_number) {
        super(german_number);
    }
    public Boolean getUmgangsMinute(TimeInWordsDto tiw) {

        Boolean ret = Boolean.FALSE;

        if (tiw.getSettings().getKurznach()
                && (tiw.getPieces().getMinutes() > 0)
                && (tiw.getPieces().getMinutes() < 5)) {
            tiw.setMinute1("kurz");
            tiw.setVornach("nach");
            tiw.setMinute2("");
            tiw.setPlusHour(Boolean.FALSE);
            ret = Boolean.TRUE;
        }


        if (tiw.getSettings().getKurzvor()
                && (tiw.getPieces().getMinutes() > 55)
                && (tiw.getPieces().getMinutes() <= 59)){
            tiw.setMinute1("kurz");
            tiw.setVornach("vor");
            tiw.setMinute2("");
            tiw.setPlusHour(Boolean.TRUE);
            ret = Boolean.TRUE;
        }

        if (tiw.getSettings().getKurzvorviertelacht()
                && (tiw.getPieces().getMinutes() > 10)
                && (tiw.getPieces().getMinutes() < 15)){
            tiw.setMinute1("kurz");
            tiw.setVornach("vor");
            tiw.setMinute2("viertel");
            tiw.setPlusHour(Boolean.TRUE);
            ret = Boolean.TRUE;
        }

        if (tiw.getSettings().getKurznachviertelacht()
                && (tiw.getPieces().getMinutes() > 15)
                && (tiw.getPieces().getMinutes() < 20)){
            tiw.setMinute1("kurz");
            tiw.setVornach("nach");
            tiw.setMinute2("viertel");
            tiw.setPlusHour(Boolean.TRUE);
            ret = Boolean.TRUE;
        }

        if (tiw.getSettings().getKurzvorhalb()
                && (tiw.getPieces().getMinutes() > 25)
                && (tiw.getPieces().getMinutes() < 30)){
            tiw.setMinute1("kurz");
            tiw.setVornach("vor");
            tiw.setMinute2("halb");
            tiw.setPlusHour(Boolean.TRUE);
            ret = Boolean.TRUE;
        }

        if (tiw.getSettings().getKurznachhalb()
                && (tiw.getPieces().getMinutes() > 30)
                && (tiw.getPieces().getMinutes() < 35)){
            tiw.setMinute1("kurz");
            tiw.setVornach("nach");
            tiw.setMinute2("halb");
            tiw.setPlusHour(Boolean.TRUE);
            ret = Boolean.TRUE;
        }


        if (tiw.getSettings().getKurzvordreiviertelacht()
                && (tiw.getPieces().getMinutes() > 40)
                && (tiw.getPieces().getMinutes() < 45)){
            tiw.setMinute1("kurz");
            tiw.setVornach("vor");
            tiw.setMinute2("dreiviertel");
            tiw.setPlusHour(Boolean.TRUE);
            ret = Boolean.TRUE;
        }

        if (tiw.getSettings().getKurznachdreiviertelacht()
                && (tiw.getPieces().getMinutes() > 45)
                && (tiw.getPieces().getMinutes() < 50)){
            tiw.setMinute1("kurz");
            tiw.setVornach("nach");
            tiw.setMinute2("dreiviertel");
            tiw.setPlusHour(Boolean.TRUE);
            ret = Boolean.TRUE;
        }

        return ret;
    }




}
