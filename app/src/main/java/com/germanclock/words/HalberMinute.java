package com.germanclock.words;

import com.germanclock.time.Settings;

/**
 * Created by ich on 17.04.2016.
 */
public class HalberMinute extends Minute {
    public HalberMinute(String[] german_number) {
        super(german_number);
    }


    public Boolean getUmgangsMinute(TimeInWordsDto tiw) {

        Boolean ret = Boolean.FALSE;




        //5 minute bucket is nearest multiple of five below current minute
        switch (tiw.getPieces().getFiveMinBucket()) {

            case 20:
                if(tiw.getSettings().getHalber() &&
                        tiw.getSettings().getHalberRange() >= (10 - tiw.getPieces().getRemainderMinutes())) {
                    tiw.setMinute1(german_number[(10 - tiw.getPieces().getRemainderMinutes())]);
                    tiw.setVornach("vor");
                    tiw.setMinute2("halber");
                    ret = Boolean.TRUE;
                }
                break;
            case 25:
                if(tiw.getSettings().getHalber() &&
                        tiw.getSettings().getHalberRange() >= (5 - tiw.getPieces().getRemainderMinutes())) {
                    tiw.setMinute1(german_number[(5 - tiw.getPieces().getRemainderMinutes())]);
                    tiw.setVornach("vor");
                    tiw.setMinute2("halber");
                    ret = Boolean.TRUE;
                }
                break;
            case 30:
                if(tiw.getSettings().getHalber()
                        && tiw.getSettings().getHalberRange() >= (tiw.getPieces().getRemainderMinutes())) {
                    if(tiw.getPieces().getRemainderMinutes() == 0) {
                        tiw.setMinute1("");
                        tiw.setVornach("");
                        tiw.setMinute2("halber");
                        ret = Boolean.TRUE;
                    }
                    else {
                        tiw.setMinute1(german_number[tiw.getPieces().getRemainderMinutes()]);
                        tiw.setVornach("nach");
                        tiw.setMinute2("halber");
                        ret = Boolean.TRUE;
                    }
                }

                break;
            case 35:
                if(tiw.getSettings().getHalber()
                        && tiw.getSettings().getHalberRange() >= (5 + tiw.getPieces().getRemainderMinutes())) {
                    tiw.setMinute1(german_number[tiw.getPieces().getRemainderMinutes() + 5]);
                    tiw.setVornach("nach");
                    tiw.setMinute2("halber");
                    ret = Boolean.TRUE;
                }

                break;
            case 40:
                if(tiw.getSettings().getHalber() &&
                        tiw.getSettings().getHalberRange() >= (10 - tiw.getPieces().getRemainderMinutes())) {
                    tiw.setMinute1(german_number[(10 - tiw.getPieces().getRemainderMinutes())]);
                    tiw.setVornach("nach");
                    tiw.setMinute2("halber");
                    ret = Boolean.TRUE;
                }
                break;

        }
        return ret;
    }
}
