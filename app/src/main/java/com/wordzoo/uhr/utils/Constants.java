package com.wordzoo.uhr.utils;

/**
 * Created by ich on 27.04.2016.
 */
public class Constants {
    //Settings
    public final static String CLOCK = "CLOCK"; //e.g. germanclock, englishclock, russianclock
    public final static String selectedClock = "germanclock"; //for now there is only one clock

    public final static String CONFIG = "CONFIG"; //note key includes clock, e.g. germanclock~myconfig
    public final static String SETTING = "SETTING"; //note key includes clock~config~setting e.g. germanclock~myconfig~kurznach

}
