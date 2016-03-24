package com.wordzoo.uhr;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnitRunner;

import com.germanclock.time.Pieces;
import com.germanclock.time.Settings;
import com.germanclock.words.TimeInWords;

import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ExampleUnitTest extends AndroidJUnitRunner {

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
        s.setMinuteHybrid(Boolean.TRUE);
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
        assertEquals("Es ist fünf Minuten vor drei viertel eins in der Nacht", out);


        p = new Pieces("00:45");
        out = tiw.getTimeAsSentance(p,s);
        assertEquals("Es ist drei viertel eins in der Nacht", out);


        p = new Pieces("00:50");
        out = tiw.getTimeAsSentance(p,s);
        assertEquals("Es ist zehn Minuten vor ein Uhr in der Nacht", out);


        p = new Pieces("00:55");
        out = tiw.getTimeAsSentance(p,s);
        assertEquals("Es ist fünf Minuten vor ein Uhr in der Nacht", out);

    }


}