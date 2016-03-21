package com.wordzoo.uhr;

import android.test.InstrumentationTestCase;
import android.util.Log;

import com.germanclock.time.Pieces;
import com.germanclock.time.Settings;
import com.germanclock.words.TimeInWords;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

//extends InstrumentationTestCase
public class ExampleUnitTest  {



    @Test
    public void testWord()   {
        Settings s = new Settings();
        s.setUmgangssprachlich(Boolean.TRUE);
        Pieces p = new Pieces("17:00");
        TimeInWords tiw = new TimeInWords();

        String out = tiw.getTimeAsSentance(p,s);
        assertEquals("fünf", out);

        s.setEsist(Boolean.TRUE);
        out = tiw.getTimeAsSentance(p,s);
        assertEquals("Es ist fünf", out);

        s.setUhr(Boolean.TRUE);
        out = tiw.getTimeAsSentance(p,s);
        assertEquals("Es ist fünf Uhr", out);

        s.setNachmittags(Boolean.TRUE);
        out = tiw.getTimeAsSentance(p,s);
        assertEquals("Es ist fünf Uhr nachmittags", out);

        p = new Pieces("17:17");
        out = tiw.getTimeAsSentance(p,s);
        assertEquals("Es ist siebzehn nach fünf am Abend", out);

        p = new Pieces("17:05");
        out = tiw.getTimeAsSentance(p,s);
        assertEquals("Es ist fünf nach fünf Uhr", out);

        //mitternachts
    }


/*    private Boolean flag = Boolean.FALSE;

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


    //note if umgangssprachlich get false, nothing below here applies
    //note for block layout, you can only have Umgangminute.minutebar
    public enum Clockface {
        block,
        sentance
    }

    private Clockface clockface = Clockface.sentance;




	private Boolean kurznach = Boolean.FALSE;;


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


*/
}