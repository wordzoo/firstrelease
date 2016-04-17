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

        Integer testMinute = tiw.getPieces().getMinutes();

        //these are the basic umgang minutes....
        if (testMinute > 30)
            testMinute = 60 - testMinute;

        tiw.setMinute1(testMinute+"");
        if (tiw.getPieces().getMinutes() <= 30) {
            tiw.setVornach("nach");
            tiw.setPlusHour(Boolean.FALSE);
        }
        else {
            tiw.setVornach("vor");
            tiw.setPlusHour(Boolean.TRUE);
        }


        //if we want to display images for the remainder minutes,
        //verbalize the rounded (down) multiple of five for minutes
        if (tiw.getSettings().getUmgangminute() == Settings.Umgangminute.minutebar) {


            //...but for "minute bar" we only
            //verbalize minutes rounded down to a multiple of 5

            if(tiw.getPieces().getFiveMinBucket() == 0) {

                tiw.setMinute1("");
                tiw.setVornach("");
                tiw.setMinute2("");
            }
            else {
                tiw.setMinute1(german_number[tiw.getPieces().getFiveMinBucket()]);
                //vor vs nach and plus hours we get from default umgang minutes (above)
            }
        }
        return Boolean.TRUE;
    }
}
