package com.germanclock.words;

/**
 * Created by ich on 17.04.2016.
 */
public class KurzMinute {
    public void getUmgangsMinute(TimeInWordsDto tiw) {
        return ;
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


}
