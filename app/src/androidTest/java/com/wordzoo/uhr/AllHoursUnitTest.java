package com.wordzoo.uhr;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.runner.AndroidJUnitRunner;
import android.test.suitebuilder.annotation.LargeTest;

import com.germanclock.time.Pieces;
import com.germanclock.time.Settings;
import com.germanclock.words.TimeInWords;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class AllHoursUnitTest extends AndroidJUnitRunner {

    private Context context;


       protected void tearDown() throws Exception {

    }


    protected void setUp() {
        this.context =   InstrumentationRegistry.getContext();
    }

    @Test
    public void testMinuteBarWords()   {


        Settings s = new Settings();
        TimeInWords tiw = new TimeInWords(InstrumentationRegistry.getTargetContext());

        // Basic umgangsprashlich test
        s.setUmgangssprachlich(Boolean.TRUE);
        s.setMinuteHybrid(Boolean.FALSE);
        s.setUmgangminute(Settings.Umgangminute.minutebar);

        s.setEsist(Boolean.TRUE);
        s.setUhr(Boolean.TRUE);
        s.setMinute(Boolean.TRUE);

        s.setMitternacht(Boolean.TRUE);
        s.setKurzvor(Boolean.TRUE);
        s.setKurznach(Boolean.TRUE);

        s.setMorgens(Boolean.TRUE);
        s.setVormittags(Boolean.TRUE);
        s.setNachmittags(Boolean.TRUE);
        s.setAbends(Boolean.TRUE);
        s.setIndernacht(Boolean.TRUE);

        s.setViertel(Settings.Viertel.viertelacht);
        s.setFuenfvorviertelacht(Boolean.TRUE);
        s.setFuenfnachviertelacht(Boolean.TRUE);

        s.setHalb(Boolean.TRUE);
        s.setFuenfvorhalb(Boolean.TRUE);
        s.setFuenfnachhalb(Boolean.TRUE);
        s.setKurzvorhalb(Boolean.TRUE);
        s.setKurznachhalb(Boolean.TRUE);

        s.setDreiviertel(Settings.Dreiviertel.dreiviertelacht);
        s.setFuenfvordreiviertelacht(Boolean.TRUE);
        s.setFuenfnachdreiviertelacht(Boolean.TRUE);


        Pieces p = new Pieces("00:00");
        String out = tiw.getTimeAsSentance(p,s);
        assertEquals("Es ist Mitternacht", out);

        p = new Pieces("00:05");
        out = tiw.getTimeAsSentance(p,s);
        assertEquals("Es ist fünf Minuten nach Mitternacht", out);

        p = new Pieces("00:10");
        out = tiw.getTimeAsSentance(p,s);
        assertEquals("Es ist fünf Minuten vor viertel eins in der Nacht", out);

        p = new Pieces("00:15");
        out = tiw.getTimeAsSentance(p,s);
        assertEquals("Es ist viertel eins in der Nacht", out);

        p = new Pieces("00:20");
        out = tiw.getTimeAsSentance(p,s);
        assertEquals("Es ist fünf Minuten nach viertel eins in der Nacht", out);


        p = new Pieces("00:25");
        out = tiw.getTimeAsSentance(p,s);
        assertEquals("Es ist fünf Minuten vor halb eins in der Nacht", out);


        p = new Pieces("00:30");
        out = tiw.getTimeAsSentance(p,s);
        assertEquals("Es ist halb eins in der Nacht", out);


        p = new Pieces("00:35");
        out = tiw.getTimeAsSentance(p,s);
        assertEquals("Es ist fünf Minuten nach halb eins in der Nacht", out);


        p = new Pieces("00:40");
        out = tiw.getTimeAsSentance(p,s);
        assertEquals("Es ist fünf Minuten vor dreiviertel eins in der Nacht", out);


        p = new Pieces("00:45");
        out = tiw.getTimeAsSentance(p,s);
        assertEquals("Es ist dreiviertel eins in der Nacht", out);


        p = new Pieces("00:50");
        out = tiw.getTimeAsSentance(p,s);
        assertEquals("Es ist fünf Minuten nach dreiviertel eins in der Nacht", out);


        p = new Pieces("00:55");
        out = tiw.getTimeAsSentance(p,s);
        assertEquals("Es ist fünf Minuten vor ein Uhr in der Nacht", out);

    }

    @Test
    public void testMinuteWordsHybrid() {

        Settings s = new Settings();
        TimeInWords tiw = new TimeInWords(InstrumentationRegistry.getTargetContext());

        // Basic umgangsprashlich test
        s.setUmgangssprachlich(Boolean.TRUE);
        s.setMinuteHybrid(Boolean.TRUE);
        s.setUmgangminute(Settings.Umgangminute.minuteword);

        s.setEsist(Boolean.TRUE);
        s.setUhr(Boolean.TRUE);
        s.setMinute(Boolean.FALSE);

        s.setMitternacht(Boolean.TRUE);
        s.setKurzvor(Boolean.TRUE);
        s.setKurznach(Boolean.TRUE);

        s.setMorgens(Boolean.TRUE);
        s.setVormittags(Boolean.TRUE);
        s.setNachmittags(Boolean.TRUE);
        s.setAbends(Boolean.TRUE);
        s.setIndernacht(Boolean.TRUE);

        s.setViertel(Settings.Viertel.viertelacht);
        s.setFuenfvorviertelacht(Boolean.TRUE);
        s.setFuenfnachviertelacht(Boolean.TRUE);

        s.setHalb(Boolean.TRUE);
        s.setFuenfvorhalb(Boolean.TRUE);
        s.setFuenfnachhalb(Boolean.TRUE);
        s.setKurzvorhalb(Boolean.TRUE);
        s.setKurznachhalb(Boolean.TRUE);

        s.setDreiviertel(Settings.Dreiviertel.dreiviertelacht);
        s.setFuenfvordreiviertelacht(Boolean.TRUE);
        s.setFuenfnachdreiviertelacht(Boolean.TRUE);


        Pieces p = new Pieces("13:01");
        String out = tiw.getTimeAsSentance(p, s);
        assertEquals("Es ist ein Uhr eins nachmittags", out);

        s.setKurznach(Boolean.FALSE);
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("Es ist ein Uhr eins nachmittags", out);

        p = new Pieces("13:02");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("Es ist ein Uhr zwei nachmittags", out);

        p = new Pieces("13:03");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("Es ist ein Uhr drei nachmittags", out);

        p = new Pieces("13:04");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("Es ist ein Uhr vier nachmittags", out);

        p = new Pieces("13:05");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("Es ist fünf nach ein Uhr nachmittags", out);

        p = new Pieces("13:06");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("Es ist ein Uhr sechs nachmittags", out);

        p = new Pieces("13:07");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("Es ist ein Uhr sieben nachmittags", out);

        p = new Pieces("13:08");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("Es ist ein Uhr acht nachmittags", out);

        p = new Pieces("13:09");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("Es ist ein Uhr neun nachmittags", out);

        p = new Pieces("13:10");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("Es ist fünf vor viertel zwei nachmittags", out);

    }

    @Test
    public void testMinuteWordsNoHybrid() {

        Settings s = new Settings();
        TimeInWords tiw = new TimeInWords(InstrumentationRegistry.getTargetContext());

        // Basic umgangsprashlich test
        s.setUmgangssprachlich(Boolean.TRUE);
        s.setMinuteHybrid(Boolean.FALSE);
        s.setUmgangminute(Settings.Umgangminute.minuteword);

        s.setEsist(Boolean.TRUE);
        s.setUhr(Boolean.FALSE);
        s.setMinute(Boolean.TRUE);

        s.setMitternacht(Boolean.TRUE);
        s.setKurzvor(Boolean.TRUE);
        s.setKurznach(Boolean.TRUE);

        s.setMorgens(Boolean.TRUE);
        s.setVormittags(Boolean.TRUE);
        s.setNachmittags(Boolean.TRUE);
        s.setAbends(Boolean.TRUE);
        s.setIndernacht(Boolean.TRUE);

        s.setViertel(Settings.Viertel.viertelacht);
        s.setFuenfvorviertelacht(Boolean.TRUE);
        s.setFuenfnachviertelacht(Boolean.TRUE);

        s.setHalb(Boolean.TRUE);
        s.setFuenfvorhalb(Boolean.TRUE);
        s.setFuenfnachhalb(Boolean.TRUE);
        s.setKurzvorhalb(Boolean.TRUE);
        s.setKurznachhalb(Boolean.TRUE);

        s.setDreiviertel(Settings.Dreiviertel.dreiviertelacht);
        s.setFuenfvordreiviertelacht(Boolean.TRUE);
        s.setFuenfnachdreiviertelacht(Boolean.TRUE);
        s.setKurzvordreiviertelacht(Boolean.TRUE);
        s.setKurznachdreiviertelacht(Boolean.TRUE);


        Pieces p = new Pieces("13:11");
        String out = tiw.getTimeAsSentance(p, s);
        assertEquals("Es ist elf Minuten nach eins nachmittags", out);


        p = new Pieces("13:12");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("Es ist zwölf Minuten nach eins nachmittags", out);

        p = new Pieces("13:13");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("Es ist dreizehn Minuten nach eins nachmittags", out);

        s.setMinute(Boolean.FALSE); //no minutes

        p = new Pieces("13:14");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("Es ist vierzehn nach eins nachmittags", out);

        p = new Pieces("13:15");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("Es ist viertel zwei nachmittags", out);

        p = new Pieces("13:16");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("Es ist sechzehn nach eins nachmittags", out);

        p = new Pieces("13:17");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("Es ist siebzehn nach eins nachmittags", out);

        p = new Pieces("13:18");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("Es ist achtzehn nach eins nachmittags", out);

        p = new Pieces("13:19");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("Es ist neunzehn nach eins nachmittags", out);

        p = new Pieces("13:20");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("Es ist fünf nach viertel zwei nachmittags", out);

        p = new Pieces("13:21");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("Es ist einundzwanzig nach eins nachmittags", out);

        p = new Pieces("13:22");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("Es ist zweiundzwanzig nach eins nachmittags", out);

        p = new Pieces("13:23");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("Es ist dreiundzwanzig nach eins nachmittags", out);

        p = new Pieces("13:24");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("Es ist vierundzwanzig nach eins nachmittags", out);

        p = new Pieces("13:25");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("Es ist fünf vor halb zwei nachmittags", out);

        p = new Pieces("13:26");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("Es ist kurz vor halb zwei nachmittags", out);

        p = new Pieces("13:27");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("Es ist kurz vor halb zwei nachmittags", out);

        p = new Pieces("13:28");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("Es ist kurz vor halb zwei nachmittags", out);

        p = new Pieces("13:29");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("Es ist kurz vor halb zwei nachmittags", out);

        p = new Pieces("13:30");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("Es ist halb zwei nachmittags", out);

        p = new Pieces("13:31");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("Es ist kurz nach halb zwei nachmittags", out);

        p = new Pieces("13:32");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("Es ist kurz nach halb zwei nachmittags", out);

        p = new Pieces("13:33");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("Es ist kurz nach halb zwei nachmittags", out);

        p = new Pieces("13:34");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("Es ist kurz nach halb zwei nachmittags", out);

        p = new Pieces("13:35");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("Es ist fünf nach halb zwei nachmittags", out);

        p = new Pieces("13:36");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("Es ist vierundzwanzig vor zwei nachmittags", out);

        p = new Pieces("13:37");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("Es ist dreiundzwanzig vor zwei nachmittags", out);

        p = new Pieces("13:38");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("Es ist zweiundzwanzig vor zwei nachmittags", out);

        p = new Pieces("13:39");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("Es ist einundzwanzig vor zwei nachmittags", out);

        p = new Pieces("13:40");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("Es ist fünf vor dreiviertel zwei nachmittags", out);

        p = new Pieces("13:41");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("Es ist kurz vor dreiviertel zwei nachmittags", out);

        p = new Pieces("13:42");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("Es ist kurz vor dreiviertel zwei nachmittags", out);

        p = new Pieces("13:43");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("Es ist kurz vor dreiviertel zwei nachmittags", out);

        p = new Pieces("13:44");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("Es ist kurz vor dreiviertel zwei nachmittags", out);

        p = new Pieces("13:45");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("Es ist dreiviertel zwei nachmittags", out);

        p = new Pieces("13:46");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("Es ist kurz nach dreiviertel zwei nachmittags", out);

        p = new Pieces("13:47");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("Es ist kurz nach dreiviertel zwei nachmittags", out);

        p = new Pieces("13:48");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("Es ist kurz nach dreiviertel zwei nachmittags", out);

        p = new Pieces("13:49");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("Es ist kurz nach dreiviertel zwei nachmittags", out);

        p = new Pieces("13:50");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("Es ist fünf nach dreiviertel zwei nachmittags", out);

        p = new Pieces("13:51");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("Es ist neun vor zwei nachmittags", out);

        p = new Pieces("13:52");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("Es ist acht vor zwei nachmittags", out);

        p = new Pieces("13:53");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("Es ist sieben vor zwei nachmittags", out);

        p = new Pieces("13:54");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("Es ist sechs vor zwei nachmittags", out);

        p = new Pieces("13:55");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("Es ist fünf vor zwei nachmittags", out);

        p = new Pieces("13:56");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("Es ist kurz vor zwei nachmittags", out);

        p = new Pieces("13:57");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("Es ist kurz vor zwei nachmittags", out);

        p = new Pieces("13:58");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("Es ist kurz vor zwei nachmittags", out);

        p = new Pieces("13:59");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("Es ist kurz vor zwei nachmittags", out);




        //TODO official time

        //TODO kurz vor-nach halb not working


    }

}