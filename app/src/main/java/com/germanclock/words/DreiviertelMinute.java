package com.germanclock.words;

import com.germanclock.time.Settings;

/**
 * Created by ich on 17.04.2016.
 */
public class DreiviertelMinute  extends Minute {

    public DreiviertelMinute(String[] german_number) {
        super(german_number);
    }

    public Boolean getUmgangsMinute(TimeInWordsDto tiw) {
        Boolean ret = Boolean.FALSE;

        switch (tiw.getPieces().getMinutes()) {

            case 40:
                if (tiw.getSettings().getDreiviertel() == Settings.Dreiviertel.dreiviertelacht
                        && tiw.getSettings().getFuenfvordreiviertelacht()) {
                    tiw.setMinute1("fünf");
                    tiw.setVornach("vor");
                    tiw.setMinute2("dreiviertel");
                    tiw.setPlusHour(Boolean.TRUE);
                    ret = Boolean.TRUE;
                }
                break;
            case 45:
                if (tiw.getSettings().getDreiviertel() == Settings.Dreiviertel.dreiviertelacht) {
                    tiw.setMinute1("dreiviertel");
                    tiw.setVornach("");
                    tiw.setMinute2("");
                    tiw.setPlusHour(Boolean.TRUE);
                    ret = Boolean.TRUE;
                } else if (tiw.getSettings().getDreiviertel() == Settings.Dreiviertel.viertelvor) {
                    tiw.setMinute1("viertel");
                    tiw.setVornach("vor");
                    tiw.setPlusHour(Boolean.TRUE);
                    ret = Boolean.TRUE;
                }
                break;
            case 50:
                if (tiw.getSettings().getFuenfnachdreiviertelacht()) {
                    tiw.setMinute1("fünf");
                    tiw.setVornach("nach");
                    tiw.setMinute2("dreiviertel");
                    tiw.setPlusHour(Boolean.TRUE);
                    ret = Boolean.TRUE;
                }
                break;
        }
        return ret;
    }
}
