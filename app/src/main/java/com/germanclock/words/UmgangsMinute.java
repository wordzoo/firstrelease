package com.germanclock.words;

import com.germanclock.time.Settings;

/**
 * Created by ich on 17.04.2016.
 */
public class UmgangsMinute  extends Minute {

    public UmgangsMinute(String[] german_number) {
        super(german_number);
    }

    public  Boolean getUmgangsMinute(TimeInWordsDto tiw) {

        Boolean ret = Boolean.FALSE;



        //if we want to display images for the remainder minutes,
        // we verbalize the rounded (down) multiple of five for minutes
        if (tiw.getSettings().getUmgangminute() == Settings.Umgangminute.minutebar) {

            if(tiw.getPieces().getMinutes() == 1)
                ret = Boolean.FALSE;
            else if(tiw.getPieces().getMinutes() == 0) {

                tiw.setMinute1("");
                tiw.setVornach("");
                tiw.setMinute2("");
                ret = Boolean.TRUE;
            }
            else {
                int umMinute = 0;
                if(tiw.getPieces().getFiveMinBucket() > 30)
                    umMinute = 60 - tiw.getPieces().getFiveMinBucket();
                else
                    umMinute = tiw.getPieces().getFiveMinBucket();

                tiw.setMinute1(german_number[umMinute]);
                //vor vs nach and plus hours we get from default umgang defaults (in calling class)
            }
            ret = Boolean.TRUE;
        }
        return ret;
    }
}
