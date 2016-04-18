package com.germanclock.words;

import com.germanclock.time.Settings;

/**
 * Created by ich on 17.04.2016.
 */
public class ViertelMinute  extends Minute {

    public ViertelMinute(String[] german_number) {
        super(german_number);
    }

    public Boolean getUmgangsMinute(TimeInWordsDto tiw) {
        Boolean ret = Boolean.FALSE;

        switch (tiw.getPieces().getMinutes()) {

            case 15:
                if (tiw.getSettings().getViertel() == Settings.Viertel.viertelacht) {
                    tiw.setMinute1("viertel");
                    tiw.setVornach("");
                    tiw.setPlusHour(Boolean.TRUE);
                    ret = Boolean.TRUE;
                } else if (tiw.getSettings().getViertel() == Settings.Viertel.viertelnach) {
                    tiw.setMinute1("viertel");
                    tiw.setVornach("nach");
                    tiw.setPlusHour(Boolean.FALSE);
                    ret = Boolean.TRUE;
                } else if (tiw.getSettings().getViertel() == Settings.Viertel.viertelueber) {
                    tiw.setMinute1("viertel");
                    tiw.setVornach("über");
                    tiw.setPlusHour(Boolean.FALSE);
                    ret = Boolean.TRUE;
                }
                break;


        }
        return ret;
    }

}
