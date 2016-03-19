package com.germanclock.words;

/**
 * Created by ich on 19.03.2016.
 */
public class TimeInWordsDto {
    private String begin;
    private String minute1;
    private String minute2;
    private String hour;
    private String uhr;
    private Boolean plusHour;
    private Boolean umgangssprachlich;

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
}
