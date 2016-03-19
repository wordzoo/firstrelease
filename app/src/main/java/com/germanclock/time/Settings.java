package com.germanclock.time;

public class Settings {
    private Boolean flag;

    public enum FlagPattern {
        vienna,
        dambach,
        kaernten
    }

    private FlagPattern flagPattern;

    private Boolean esist;
    private Boolean umgangssprachlich;


    //you can only choose this of umgangssprachlich is true
    public enum Umgangminute {
        minutebar,
        minuteword
    }

    private Umgangminute umgangminute;


    //note if umgangssprachlich get false, nothing below here applies
    //note for block layout, you can only have Umgangminute.minutebar
    public enum layout {
        block,
        sentance
    }

   private Boolean mitternachts;
	private Boolean morgens;
	private Boolean ammorgen;
	private Boolean vormittags;
	private Boolean amvormittag;
	private Boolean mittags;
	private Boolean nachmittags;
	private Boolean amnachmittag;
	private Boolean abdends;
	private Boolean amabend;
	private Boolean nachts;
	private Boolean indernacht;

	private Boolean uhr;

	private Boolean kurznach;
	private Boolean fuenfnach;
	private Boolean zehnnach;

    public enum Viertel {
        viertelueber,
        viertelnach,
        viertelacht
    }

    private Viertel viertel;

	//if viertel == viertel.vieterlacht, two more options
    private Boolean fuenfvorviertelacht;
    private Boolean fuenfnachviertelacht;



    //halb is selected be default with umgrangsprache
    private Boolean halb;

    //these are available only if you choose halb
    private Boolean fuenfvorhalb;
    private Boolean fuenfnachhalb;
    //or
    private Boolean fuenfundzwanzignach;
    private Boolean fuenfundzwanzigvor;


    private Boolean zehnvorhalb;
    private Boolean zehnnachhalb;
    //or
    private Boolean zwanzignach;
    private Boolean zwanzigvor;







    public enum Dreiviertel {
        viertelvor,
        dreiviertelacht,
        fuenfzehn
    }


    private Dreiviertel dreiviertel;

    //if dreiviertel == Dreiviertel.dreiviertelacht then two more options
    private Boolean fuenfvordreiviertelacht;
    private Boolean fuenfnachdreiviertelacht;

	private Boolean zehnvor;
	private Boolean fuenfvor;
	private Boolean kurzvor;

    //this also does not work on block layout
    private Boolean dreissignach;

    public Boolean getDreissignach() {
        return dreissignach;
    }

    public void setDreissignach(Boolean dreissignach) {
        this.dreissignach = dreissignach;
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

    public Boolean getMitternachts() {
        return mitternachts;
    }

    public void setMitternachts(Boolean mitternachts) {
        this.mitternachts = mitternachts;
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

    public Boolean getAbdends() {
        return abdends;
    }

    public void setAbdends(Boolean abdends) {
        this.abdends = abdends;
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

    public Boolean getFuenfnach() {
        return fuenfnach;
    }

    public void setFuenfnach(Boolean fuenfnach) {
        this.fuenfnach = fuenfnach;
    }

    public Boolean getZehnnach() {
        return zehnnach;
    }

    public void setZehnnach(Boolean zehnnach) {
        this.zehnnach = zehnnach;
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

    public Boolean getZwanzignach() {
        return zwanzignach;
    }

    public void setZwanzignach(Boolean zwanzignach) {
        this.zwanzignach = zwanzignach;
    }

    public Boolean getFuenfundzwanzignach() {
        return fuenfundzwanzignach;
    }

    public void setFuenfundzwanzignach(Boolean fuenfundzwanzignach) {
        this.fuenfundzwanzignach = fuenfundzwanzignach;
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

    public Boolean getFuenfundzwanzigvor() {
        return fuenfundzwanzigvor;
    }

    public void setFuenfundzwanzigvor(Boolean fuenfundzwanzigvor) {
        this.fuenfundzwanzigvor = fuenfundzwanzigvor;
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

    public Boolean getZehnvor() {
        return zehnvor;
    }

    public void setZehnvor(Boolean zehnvor) {
        this.zehnvor = zehnvor;
    }

    public Boolean getFuenfvor() {
        return fuenfvor;
    }

    public void setFuenfvor(Boolean fuenfvor) {
        this.fuenfvor = fuenfvor;
    }

    public Boolean getKurzvor() {
        return kurzvor;
    }

    public void setKurzvor(Boolean kurzvor) {
        this.kurzvor = kurzvor;
    }
}