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


            if(tiw.getPieces().getFiveMinBucket() == 0) {

                tiw.setMinute1("");
                tiw.setVornach("");
                tiw.setMinute2("");
            }
            else {
                tiw.setMinute1(german_number[tiw.getPieces().getFiveMinBucket()]);
                //vor vs nach and plus hours we get from default umgang minutes (above)
            }
            ret = Boolean.TRUE;
        }
        return ret;
    }
}
