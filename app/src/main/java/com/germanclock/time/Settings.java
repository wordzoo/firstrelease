package com.germanclock.time;

public class Settings {
    private Boolean flag = Boolean.FALSE;

    public enum FlagPattern {
        vienna,
        dambach,
        kaernten
    }

    private FlagPattern flagPattern = FlagPattern.vienna;

    private Boolean esist = Boolean.FALSE;;
    private Boolean umgangssprachlich = Boolean.FALSE;;


    //you can only choose this of umgangssprachlich is true
    public enum Umgangminute {
        minutebar,
        minuteword
    }

    private Umgangminute umgangminute = Umgangminute.minutebar;

    private Boolean minuteHybrid = Boolean.FALSE;

    public Boolean getMinuteHybrid() {
        return minuteHybrid;
    }

    public void setMinuteHybrid(Boolean minuteHybrid) {
        this.minuteHybrid = minuteHybrid;
    }

    //note if umgangssprachlich get false, nothing below here applies
    //note for block layout, you can only have Umgangminute.minutebar
    public enum Clockface {
        block,
        sentance
    }

    private Clockface clockface = Clockface.sentance;

   private Boolean mitternacht = Boolean.FALSE;
	private Boolean morgens = Boolean.FALSE;
	private Boolean ammorgen = Boolean.FALSE;
	private Boolean vormittags = Boolean.FALSE;
	private Boolean amvormittag = Boolean.FALSE;
	private Boolean mittags = Boolean.FALSE;
	private Boolean nachmittags = Boolean.FALSE;
	private Boolean amnachmittag = Boolean.FALSE;
	private Boolean abends = Boolean.FALSE;
	private Boolean amabend = Boolean.FALSE;
	private Boolean nachts = Boolean.FALSE;
	private Boolean indernacht = Boolean.FALSE;
    private Boolean inderfrueh = Boolean.FALSE;
    private Boolean morgennacht = Boolean.FALSE;
    private Boolean ammorgennacht = Boolean.FALSE;

	private Boolean uhr = Boolean.FALSE;
    private Boolean minute = Boolean.FALSE;

	private Boolean kurznach = Boolean.FALSE;


    public enum Viertel {
        viertelueber,
        viertelnach,
        viertelacht
    }

    private Viertel viertel = Viertel.viertelnach;

	//if viertel == viertel.vieterlacht, two more options
    private Boolean fuenfvorviertelacht = Boolean.FALSE;;
    private Boolean fuenfnachviertelacht = Boolean.FALSE;;



    //halb is selected be default with umgrangsprache
    private Boolean halb = Boolean.FALSE;;

    //these are available only if you choose halb
    private Boolean fuenfvorhalb = Boolean.FALSE;;
    private Boolean fuenfnachhalb = Boolean.FALSE;;
    private Boolean kurzvorhalb = Boolean.FALSE;;
    private Boolean kurznachhalb = Boolean.FALSE;;


    private Boolean zehnvorhalb = Boolean.FALSE;;
    private Boolean zehnnachhalb = Boolean.FALSE;;
    //or
    private Boolean zwanzignach = Boolean.FALSE;;
    private Boolean zwanzigvor = Boolean.FALSE;;







    public enum Dreiviertel {
        viertelvor,
        dreiviertelacht,
        fuenfzehn
    }


    private Dreiviertel dreiviertel = Dreiviertel.viertelvor;

    //if dreiviertel == Dreiviertel.dreiviertelacht then two more options
    private Boolean fuenfvordreiviertelacht = Boolean.FALSE;
    private Boolean fuenfnachdreiviertelacht = Boolean.FALSE;
    private Boolean kurzvordreiviertelacht = Boolean.FALSE;
    private Boolean kurznachdreiviertelacht = Boolean.FALSE;

	private Boolean kurzvor = Boolean.FALSE;

    //this also does not work on block layout
    private Boolean dreissignach = Boolean.FALSE;

    public Boolean getMinute() {
        return minute;
    }

    public Boolean getKurzvordreiviertelacht() {
        return kurzvordreiviertelacht;
    }

    public void setKurzvordreiviertelacht(Boolean kurzvordreiviertelacht) {
        this.kurzvordreiviertelacht = kurzvordreiviertelacht;
    }

    public Boolean getKurznachdreiviertelacht() {
        return kurznachdreiviertelacht;
    }

    public void setKurznachdreiviertelacht(Boolean kurznachdreiviertelacht) {
        this.kurznachdreiviertelacht = kurznachdreiviertelacht;
    }

    public void setMinute(Boolean minute) {
        this.minute = minute;
    }

    public Boolean getInderfrueh() {
        return inderfrueh;
    }

    public void setInderfrueh(Boolean inderfrueh) {
        this.inderfrueh = inderfrueh;
    }

    public Boolean getMorgennacht() {
        return morgennacht;
    }

    public void setMorgennacht(Boolean morgennacht) {
        this.morgennacht = morgennacht;
    }

    public Boolean getAmmorgennacht() {
        return ammorgennacht;
    }

    public void setAmmorgennacht(Boolean ammorgennacht) {
        this.ammorgennacht = ammorgennacht;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public FlagPattern getFlagPattern() {
        return flagPattern;
    }

    public void setFlagPattern(FlagPattern flagPattern) {
        this.flagPattern = flagPattern;
    }

    public Boolean getEsist() {
        return esist;
    }

    public void setEsist(Boolean esist) {
        this.esist = esist;
    }

    public Boolean getUmgangssprachlich() {
        return umgangssprachlich;
    }

    public void setUmgangssprachlich(Boolean umgangssprachlich) {
        this.umgangssprachlich = umgangssprachlich;
    }

    public Umgangminute getUmgangminute() {
        return umgangminute;
    }

    public void setUmgangminute(Umgangminute umgangminute) {
        this.umgangminute = umgangminute;
    }

    public Clockface getClockface() {
        return clockface;
    }

    public void setClockface(Clockface clockface) {
        this.clockface = clockface;
    }

    public Boolean getMitternacht() {
        return mitternacht;
    }

    public void setMitternacht(Boolean mitternacht) {
        this.mitternacht = mitternacht;
    }

    public Boolean getMorgens() {
        return morgens;
    }

    public void setMorgens(Boolean morgens) {
        this.morgens = morgens;
    }

    public Boolean getAmmorgen() {
        return ammorgen;
    }

    public void setAmmorgen(Boolean ammorgen) {
        this.ammorgen = ammorgen;
    }

    public Boolean getVormittags() {
        return vormittags;
    }

    public void setVormittags(Boolean vormittags) {
        this.vormittags = vormittags;
    }

    public Boolean getAmvormittag() {
        return amvormittag;
    }

    public void setAmvormittag(Boolean amvormittag) {
        this.amvormittag = amvormittag;
    }

    public Boolean getMittags() {
        return mittags;
    }

    public void setMittags(Boolean mittags) {
        this.mittags = mittags;
    }

    public Boolean getNachmittags() {
        return nachmittags;
    }

    public void setNachmittags(Boolean nachmittags) {
        this.nachmittags = nachmittags;
    }

    public Boolean getAmnachmittag() {
        return amnachmittag;
    }

    public void setAmnachmittag(Boolean amnachmittag) {
        this.amnachmittag = amnachmittag;
    }

    public Boolean getAbends() {
        return abends;
    }

    public void setAbends(Boolean abends) {
        this.abends = abends;
    }

    public Boolean getAmabend() {
        return amabend;
    }

    public void setAmabend(Boolean amabend) {
        this.amabend = amabend;
    }

    public Boolean getNachts() {
        return nachts;
    }

    public void setNachts(Boolean nachts) {
        this.nachts = nachts;
    }

    public Boolean getIndernacht() {
        return indernacht;
    }

    public void setIndernacht(Boolean indernacht) {
        this.indernacht = indernacht;
    }

    public Boolean getUhr() {
        return uhr;
    }

    public void setUhr(Boolean uhr) {
        this.uhr = uhr;
    }

    public Boolean getKurznach() {
        return kurznach;
    }

    public void setKurznach(Boolean kurznach) {
        this.kurznach = kurznach;
    }

    public Viertel getViertel() {
        return viertel;
    }

    public void setViertel(Viertel viertel) {
        this.viertel = viertel;
    }

    public Boolean getFuenfvorviertelacht() {
        return fuenfvorviertelacht;
    }

    public void setFuenfvorviertelacht(Boolean fuenfvorviertelacht) {
        this.fuenfvorviertelacht = fuenfvorviertelacht;
    }

    public Boolean getFuenfnachviertelacht() {
        return fuenfnachviertelacht;
    }

    public void setFuenfnachviertelacht(Boolean fuenfnachviertelacht) {
        this.fuenfnachviertelacht = fuenfnachviertelacht;
    }

    public Boolean getHalb() {
        return halb;
    }

    public void setHalb(Boolean halb) {
        this.halb = halb;
    }

    public Boolean getFuenfvorhalb() {
        return fuenfvorhalb;
    }

    public void setFuenfvorhalb(Boolean fuenfvorhalb) {
        this.fuenfvorhalb = fuenfvorhalb;
    }

    public Boolean getFuenfnachhalb() {
        return fuenfnachhalb;
    }

    public void setFuenfnachhalb(Boolean fuenfnachhalb) {
        this.fuenfnachhalb = fuenfnachhalb;
    }

    public Boolean getKurzvorhalb() {
        return kurzvorhalb;
    }

    public void setKurzvorhalb(Boolean kurzvorhalb) {
        this.kurzvorhalb = kurzvorhalb;
    }

    public Boolean getKurznachhalb() {
        return kurznachhalb;
    }

    public void setKurznachhalb(Boolean kurznachhalb) {
        this.kurznachhalb = kurznachhalb;
    }

    public Boolean getZehnvorhalb() {
        return zehnvorhalb;
    }

    public void setZehnvorhalb(Boolean zehnvorhalb) {
        this.zehnvorhalb = zehnvorhalb;
    }

    public Boolean getZehnnachhalb() {
        return zehnnachhalb;
    }

    public void setZehnnachhalb(Boolean zehnnachhalb) {
        this.zehnnachhalb = zehnnachhalb;
    }

    public Boolean getZwanzignach() {
        return zwanzignach;
    }

    public void setZwanzignach(Boolean zwanzignach) {
        this.zwanzignach = zwanzignach;
    }

    public Boolean getZwanzigvor() {
        return zwanzigvor;
    }

    public void setZwanzigvor(Boolean zwanzigvor) {
        this.zwanzigvor = zwanzigvor;
    }

    public Dreiviertel getDreiviertel() {
        return dreiviertel;
    }

    public void setDreiviertel(Dreiviertel dreiviertel) {
        this.dreiviertel = dreiviertel;
    }

    public Boolean getFuenfvordreiviertelacht() {
        return fuenfvordreiviertelacht;
    }

    public void setFuenfvordreiviertelacht(Boolean fuenfvordreiviertelacht) {
        this.fuenfvordreiviertelacht = fuenfvordreiviertelacht;
    }

    public Boolean getFuenfnachdreiviertelacht() {
        return fuenfnachdreiviertelacht;
    }

    public void setFuenfnachdreiviertelacht(Boolean fuenfnachdreiviertelacht) {
        this.fuenfnachdreiviertelacht = fuenfnachdreiviertelacht;
    }

    public Boolean getKurzvor() {
        return kurzvor;
    }

    public void setKurzvor(Boolean kurzvor) {
        this.kurzvor = kurzvor;
    }

    public Boolean getDreissignach() {
        return dreissignach;
    }

    public void setDreissignach(Boolean dreissignach) {
        this.dreissignach = dreissignach;
    }
}