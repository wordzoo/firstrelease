package com.germanclock.words;

import com.germanclock.time.Pieces;
import com.germanclock.time.Settings;

/**
 * Created by ich on 19.03.2016.
 */
public class TimeInWordsDto {
    private String begin = "";
    private String minute1 = "";
    private String minute2 = "";
    private String minute = "";
    private String hour = "";
    private String uhr = "";
    private String vornach = "";
    private String sectionOfDay = "";
    private Settings settings = new Settings();
    private Pieces pieces = new Pieces("00:00");

    private Boolean plusHour = Boolean.FALSE;
    private Boolean umgangssprachlich = Boolean.FALSE;


    public String getSectionOfDay() {
        return sectionOfDay;
    }

    public void setSectionOfDay(String sectionOfDay) {
        this.sectionOfDay = sectionOfDay;
    }

    public TimeInWordsDto(Pieces pieces, Settings settings) {
        this.pieces = pieces;
        this.settings = settings;
    }


    public Settings getSettings() {
        return settings;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }

    public Pieces getPieces() {
        return pieces;
    }

    public void setPieces(Pieces pieces) {
        this.pieces = pieces;
    }

    public String getMinute() {
        return minute;
    }

    public void setMinute(String minute) {
        this.minute = minute;
    }

    public String getVornach() {
        return vornach;
    }

    public void setVornach(String vornach) {
        this.vornach = vornach;
    }

    public Boolean getUmgangssprachlich() {
        return umgangssprachlich;
    }

    public void setUmgangssprachlich(Boolean umgangssprachlich) {
        this.umgangssprachlich = umgangssprachlich;
    }

    public Boolean getPlusHour() {
        return plusHour;
    }

    public void setPlusHour(Boolean plusHour) {
        this.plusHour = plusHour;
    }

    public String getBegin() {
        return begin;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public String getMinute1() {
        return minute1;
    }

    public void setMinute1(String minute1) {
        this.minute1 = minute1;
    }

    public String getMinute2() {
        return minute2;
    }

    public void setMinute2(String minute2) {
        this.minute2 = minute2;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getUhr() {
        return uhr;
    }

    public void setUhr(String uhr) {
        this.uhr = uhr;
    }

    public Boolean isNotEmpty(String in){
        if(in != null && in != "")
            return Boolean.TRUE;
        else
            return Boolean.FALSE;
    }

}
