package com.germanclock.time;

import android.content.SharedPreferences;
import android.widget.RadioButton;

import com.wordzoo.uhr.R;
import com.wordzoo.uhr.utils.PersistantSettings;

import com.wordzoo.uhr.utils.Constants;

import java.util.Map;

public class Settings extends PersistantSettings {
    private String customName = "";

    private Boolean flag = Boolean.FALSE;

    public enum FlagPattern {
        vienna,
        dambach,
        kaernten
    }

    private FlagPattern flagPattern = FlagPattern.vienna;

    private Boolean esist = Boolean.FALSE;
    private Boolean umgangssprachlich = Boolean.FALSE;

    private Boolean um = Boolean.FALSE; //"um Zwölf"

    private Boolean halber = Boolean.FALSE; //"sechs nach halber"
    private Integer halberRange = 6; //0 to 10, as in 10 nach (oder vor) halber

    //you can only choose this of umgangssprachlich is true
    public enum Umgangminute {
        minutebar,
        minuteword
    }

    private Umgangminute umgangminute = Umgangminute.minutebar;

    //this means that when the minutes are not exactly on a five minute bucket, the construction goes to officie (hour <<Uhr>>  minute <<Minute>>)
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
    private Boolean ammittag = Boolean.FALSE;
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
        viertelacht,
        viertelfuenfzehn
    }

    private Viertel viertel = Viertel.viertelfuenfzehn;

	//if viertel == viertel.vieterlacht, two more options
    private Boolean fuenfvorviertelacht = Boolean.FALSE;
    private Boolean fuenfnachviertelacht = Boolean.FALSE;
    private Boolean kurznachviertelacht = Boolean.FALSE;
    private Boolean kurzvorviertelacht = Boolean.FALSE;


    //halb is selected be default with umgrangsprache
    private Halb halb = Settings.Halb.dreissig;;

    public enum Halb {
        halb,
        dreissig
    }


    //these are available only if you choose halb
    private Boolean fuenfvorhalb = Boolean.FALSE;
    private Boolean fuenfnachhalb = Boolean.FALSE;
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


    public enum Default {
        officiallong,
        officialshort,
        custom,
        umgangssprachlich
    }

    private Default def = Default.officiallong;

    private Dreiviertel dreiviertel = Dreiviertel.fuenfzehn;



    //if dreiviertel == Dreiviertel.dreiviertelacht then two more options
    private Boolean fuenfvordreiviertelacht = Boolean.FALSE;
    private Boolean fuenfnachdreiviertelacht = Boolean.FALSE;
    private Boolean kurzvordreiviertelacht = Boolean.FALSE;
    private Boolean kurznachdreiviertelacht = Boolean.FALSE;

	private Boolean kurzvor = Boolean.FALSE;


    public Boolean getUm() {
        return um;
    }

    public void setUm(Boolean um) {
        this.um = um;
    }

    public Boolean getHalber() {
        return halber;
    }

    public void setHalber(Boolean halber) {
        this.halber = halber;
    }

    public Integer getHalberRange() {
        return halberRange;
    }

    public void setHalberRange(Integer halberRange) {
        this.halberRange = halberRange;
    }

    public Halb getHalb() {
        return halb;
    }

    public void setHalb(Halb halb) {
        this.halb = halb;
    }

    public Boolean getMinute() {
        return minute;
    }


    public Boolean getKurznachviertelacht() {
        return kurznachviertelacht;
    }

    public void setKurznachviertelacht(Boolean kurznachviertelacht) {
        this.kurznachviertelacht = kurznachviertelacht;
    }

    public Boolean getKurzvorviertelacht() {
        return kurzvorviertelacht;
    }

    public void setKurzvorviertelacht(Boolean kurzvorviertelacht) {
        this.kurzvorviertelacht = kurzvorviertelacht;
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

    public Boolean getAmmittag() {
        return ammittag;
    }

    public void setAmmittag(Boolean ammittag) {
        this.ammittag = ammittag;
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

    public String getCustomName() {
        return customName;
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }

    public void loadSettings(Map<String, ?> map, String clock, String config) {

        // an actual key covers, language~customname~setting
        //get settings for language and customName
        String prefix = clock + "~" + config + "~";
        setPrefix(prefix);
        setMap(map);
        setEsist((Boolean) getSetting("esist"));
        setUhr((Boolean) getSetting("uhr"));
        setMinute((Boolean) getSetting("minute"));

        setCustomName((String) getSetting("customName"));
        setFlag((Boolean) getSetting("flag"));
        setFlagPattern(Settings.FlagPattern.values()[(Integer) getSetting("flagPattern")]);
        setUmgangssprachlich((Boolean) getSetting("umgangssprachlich"));
        setUm((Boolean) getSetting("um"));
        setHalber((Boolean) getSetting("halber"));
        setHalberRange((Integer) getSetting("halberRange"));
        setUmgangminute(Settings.Umgangminute.values()[(Integer) getSetting("umgangminute")]);
        setMinuteHybrid((Boolean) getSetting("minuteHybrid"));
        setClockface(Settings.Clockface.values()[(Integer) getSetting("clockface")]);

        setMitternacht((Boolean) getSetting("mitternacht"));
        setMorgens((Boolean) getSetting("morgens"));
        setAmmorgen((Boolean) getSetting("ammorgen"));
        setVormittags((Boolean) getSetting("vormittags"));
        setAmvormittag((Boolean) getSetting("amvormittag"));
        setMittags((Boolean) getSetting("mittags"));
        setAmmittag((Boolean) getSetting("ammittag"));
        setNachmittags((Boolean) getSetting("nachmittags"));
        setAmnachmittag((Boolean) getSetting("amnachmittag"));
        setAbends((Boolean) getSetting("abends"));
        setAmabend((Boolean) getSetting("amabend"));
        setNachts((Boolean) getSetting("nachts"));
        setIndernacht((Boolean) getSetting("indernacht"));
        setInderfrueh((Boolean) getSetting("inderfrueh"));
        setMorgennacht((Boolean) getSetting("morgennacht"));
        setAmmorgennacht((Boolean) getSetting("ammorgennacht"));
        setKurznach((Boolean) getSetting("kurznach"));

        setViertel(Settings.Viertel.values()[(Integer) getSetting("viertel")]);

        setFuenfvorviertelacht((Boolean) getSetting("fuenfvorviertelacht"));
        setFuenfnachviertelacht((Boolean) getSetting("fuenfnachviertelacht"));
        setKurznachviertelacht((Boolean) getSetting("kurznachviertelacht"));
        setKurzvorviertelacht((Boolean) getSetting("kurzvorviertelacht"));

        setHalb(Settings.Halb.values()[(Integer) getSetting("halb")]);
        setFuenfvorhalb((Boolean) getSetting("fuenfvorhalb"));
        setFuenfnachhalb((Boolean) getSetting("fuenfnachhalb"));
        setKurzvorhalb((Boolean) getSetting("kurzvorhalb"));
        setKurznachhalb((Boolean) getSetting("kurznachhalb"));
        setZehnvorhalb((Boolean) getSetting("zehnvorhalb"));
        setZehnnachhalb((Boolean) getSetting("zehnnachhalb"));
        setZwanzignach((Boolean) getSetting("zwanzignach"));
        setZwanzigvor((Boolean) getSetting("zwanzigvor"));


        setDreiviertel(Settings.Dreiviertel.values()[(Integer) getSetting("dreiviertel")]);

        setFuenfvordreiviertelacht((Boolean) getSetting("fuenfvordreiviertelacht"));
        setFuenfnachdreiviertelacht((Boolean) getSetting("fuenfnachdreiviertelacht"));
        setKurzvordreiviertelacht((Boolean) getSetting("kurzvordreiviertelacht"));
        setKurznachdreiviertelacht((Boolean) getSetting("kurznachdreiviertelacht"));
        setKurzvor((Boolean) getSetting("kurzvor"));

    }

    public void createUpdateSettings(SharedPreferences sp, String selectedClock, String newConfigName) {
        String prefix = selectedClock + "~" + newConfigName + "~";
        setPrefix(prefix);
        setMap(sp.getAll());
        setEditor(sp.edit());

        //save new config name
        putConfigName(Constants.selectedClock + "~" + Constants.CONFIG, newConfigName);
        //save settings for onfig
        putBoolean("esist", getEsist());
        putBoolean("uhr", getUhr());
        putBoolean("minute", getMinute());

        putString("customName", getCustomName());
        putBoolean("flag", getFlag());

        putInteger("flagPattern", FlagPattern.valueOf(getFlagPattern()+"").ordinal());
        putBoolean("umgangssprachlich", getUmgangssprachlich());
        putBoolean("um", getUm());
        putBoolean("halber", getHalber());
        putInteger("halberRange", getHalberRange());
        putInteger("umgangminute", FlagPattern.valueOf(getUmgangminute()+"").ordinal());
        putBoolean("minuteHybrid", getMinuteHybrid());
        putInteger("clockface", FlagPattern.valueOf(getClockface()+"").ordinal());

        putBoolean("mitternacht", getMitternacht());
        putBoolean("morgens", getMorgens());
        putBoolean("ammorgen", getAmmorgen());
        putBoolean("vormittags", getVormittags());
        putBoolean("amvormittag", getAmvormittag());
        putBoolean("mittags", getMittags());
        putBoolean("ammittag", getAmmittag());
        putBoolean("nachmittags", getNachmittags());
        putBoolean("amnachmittag", getAmnachmittag());
        putBoolean("abends", getAbends());
        putBoolean("amabend", getAmabend());
        putBoolean("nachts", getNachts());
        putBoolean("indernacht", getIndernacht());
        putBoolean("inderfrueh", getInderfrueh());
        putBoolean("morgennacht", getMorgennacht());
        putBoolean("ammorgennacht", getAmmorgennacht());
        putBoolean("kurznach", getKurznach());

        putInteger("viertel", FlagPattern.valueOf(getViertel()+"").ordinal());

        putBoolean("fuenfvorviertelacht", getFuenfvorviertelacht());
        putBoolean("fuenfnachviertelacht", getFuenfnachviertelacht());
        putBoolean("kurznachviertelacht", getKurznachviertelacht());
        putBoolean("kurzvorviertelacht", getKurzvorviertelacht());

        putInteger("halb", FlagPattern.valueOf(getHalb()+"").ordinal());
        putBoolean("fuenfvorhalb", getFuenfvorhalb());
        putBoolean("fuenfnachhalb", getFuenfnachhalb());
        putBoolean("kurzvorhalb", getKurzvorhalb());
        putBoolean("kurznachhalb", getKurznachhalb());
        putBoolean("zehnvorhalb", getZehnvorhalb());
        putBoolean("zehnnachhalb", getZehnnachhalb());
        putBoolean("zwanzignach", getZwanzignach());
        putBoolean("zwanzigvor", getZwanzigvor());


        putInteger("dreiviertel", FlagPattern.valueOf(getDreiviertel()+"").ordinal());

        putBoolean("fuenfvordreiviertelacht", getFuenfvordreiviertelacht());
        putBoolean("fuenfnachdreiviertelacht", getFuenfnachdreiviertelacht());
        putBoolean("kurzvordreiviertelacht", getKurzvordreiviertelacht());
        putBoolean("kurznachdreiviertelacht", getKurznachdreiviertelacht());
        putBoolean("kurzvor", getKurzvor());

        getEditor().commit();

    }
}