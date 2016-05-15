package com.wordzoo.uhr.utils;

/**
 * Created by ich on 27.04.2016.
 */
public class Constants {
    //Settings
    public final static String CLOCK = "CLOCK"; //e.g. germanclock, englishclock, russianclock
    public final static String selectedClock = "germanclock"; //for now there is only one clock
    public final static String selectedConfig = "selectedConfig"; //current config for clock

    public final static String CONFIG = "CONFIG"; //note key includes clock, e.g. germanclock~myconfig
    public final static String SETTING = "SETTING"; //note key includes clock~config~setting e.g. germanclock~myconfig~kurznach
    public final static String OFFICIAL_TIME = "Offizielle Zeit";
    public final static String INFORMAL_TIME = "Umgangssprachlich";
    public final static String MIXED_TIME = "Hybride";
    public final static String STAR_TIME = "Sterne";

    public final static String CONFIG_MODE_EDIT = "CONFIG_MODE_EDIT";
    public final static String CONFIG_MODE_NEW = "CONFIG_MODE_NEW";
    public final static String CONFIG_MODE = "CONFIG_MODE";
}
