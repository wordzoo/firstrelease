package com.germanclock.words;

import com.germanclock.time.Settings;

/**
 * Created by ich on 17.04.2016.
 */
public class HalbMinute  extends Minute {

    public HalbMinute(String[] german_number) {
        super(german_number);
    }

    public Boolean getUmgangsMinute(TimeInWordsDto tiw) {
        Boolean ret = Boolean.FALSE;

        switch (tiw.getPieces().getMinutes()) {
            case 20:

                if (tiw.getSettings().getZehnvorhalb()) {
                    tiw.setMinute1("zehn");
                    tiw.setVornach("vor");
                    tiw.setMinute2("halb");
                    tiw.setPlusHour(Boolean.TRUE);
                    ret = Boolean.TRUE;
                }
                break;
            case 25:
                if (tiw.getSettings().getFuenfvorhalb()) {
                    tiw.setMinute1("fünf");
                    tiw.setVornach("vor");
                    tiw.setMinute2("halb");
                    tiw.setPlusHour(Boolean.TRUE);
                    ret = Boolean.TRUE;
                }
                break;
            case 30:

                if (tiw.getSettings().getHalb() == Settings.Halb.halb) {
                    tiw.setMinute1("halb");
                    tiw.setVornach("");
                    tiw.setPlusHour(Boolean.TRUE);
                    ret = Boolean.TRUE;
                }
                break;
            case 35:
                if (tiw.getSettings().getFuenfnachhalb()) {
                    tiw.setMinute1("fünf");
                    tiw.setVornach("nach");
                    tiw.setMinute2("halb");
                    tiw.setPlusHour(Boolean.TRUE);
                    ret = Boolean.TRUE;
                }
                break;
            case 40:
                if (tiw.getSettings().getZehnnachhalb()) {
                    tiw.setMinute1("zehn");
                    tiw.setVornach("nach");
                    tiw.setMinute2("halb");
                    tiw.setPlusHour(Boolean.TRUE);
                    ret = Boolean.TRUE;
                }
                break;

        }
        return ret;
    }
}
