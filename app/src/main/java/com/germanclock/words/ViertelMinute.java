package com.germanclock.words;

import com.germanclock.time.Settings;

/**
 * Created by ich on 17.04.2016.
 */
public class ViertelMinute {
    public void getUmgangsMinute(TimeInWordsDto tiw) {
        return;
    }

    public Boolean hasViertel(TimeInWordsDto tiw) {
        if( (tiw.getSettings().getViertel() == Settings.Viertel.viertelacht)
                || (tiw.getSettings().getViertel() == Settings.Viertel.viertelnach
                || (tiw.getSettings().getViertel() == Settings.Viertel.viertelueber)
        ) )
            return Boolean.TRUE;
        else
            return Boolean.FALSE;
    }

    public Boolean hasDreiviertel(TimeInWordsDto tiw) {
        if( (tiw.getSettings().getDreiviertel() == Settings.Dreiviertel.dreiviertelacht)
                || (tiw.getSettings().getDreiviertel() == Settings.Dreiviertel.viertelvor)
                )
            return Boolean.TRUE;
        else
            return Boolean.FALSE;

    }
}
