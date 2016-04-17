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
public class HalbUnitTest extends AndroidJUnitRunner {

    private Context context;


       protected void tearDown() throws Exception {

    }


    protected void setUp() {
        this.context =   InstrumentationRegistry.getContext();
    }

   @Test
    public void halber()   {

        Settings s = new Settings();
        TimeInWords tiw = new TimeInWords(InstrumentationRegistry.getTargetContext());

        // Basic umgangsprashlich test
        s.setUmgangssprachlich(Boolean.TRUE);
        s.setUmgangminute(Settings.Umgangminute.minuteword);

        s.setHalber(Boolean.TRUE);
        s.setHalberRange(8);

        Pieces p = new Pieces("11:20");
        String out = tiw.getTimeAsSentance(p,s);
        assertEquals("zwanzig nach elf", out);

        p = new Pieces("11:24");
        out = tiw.getTimeAsSentance(p,s);
        assertEquals("sechs vor halber", out);

        p = new Pieces("11:29");
        out = tiw.getTimeAsSentance(p,s);
        assertEquals("eins vor halber", out);

        p = new Pieces("11:30");
        out = tiw.getTimeAsSentance(p,s);
        assertEquals("halber", out);

        p = new Pieces("11:34");
        out = tiw.getTimeAsSentance(p,s);
        assertEquals("vier nach halber", out);


        p = new Pieces("11:38");
        out = tiw.getTimeAsSentance(p,s);
        assertEquals("acht nach halber", out);


        p = new Pieces("11:40");
        out = tiw.getTimeAsSentance(p,s);
        assertEquals("zwanzig vor zwölf", out);

        p = new Pieces("11:41");
        out = tiw.getTimeAsSentance(p,s);
        assertEquals("neunzehn vor zwölf", out);

        p = new Pieces("11:43");
        out = tiw.getTimeAsSentance(p,s);
        assertEquals("siebzehn vor zwölf", out);

        p = new Pieces("11:48");
        out = tiw.getTimeAsSentance(p,s);
        assertEquals("zwölf vor zwölf", out);

    }

    @Test
    public void halb() {

        Settings s = new Settings();
        TimeInWords tiw = new TimeInWords(InstrumentationRegistry.getTargetContext());

        // Basic umgangsprashlich test
        s.setUmgangssprachlich(Boolean.TRUE);
        s.setUmgangminute(Settings.Umgangminute.minuteword);
        s.setHalb(Settings.Halb.halb);

        s.setFuenfnachhalb(Boolean.TRUE);
        s.setFuenfvorhalb(Boolean.TRUE);
        s.setZehnnachhalb(Boolean.TRUE);
        s.setZehnvorhalb(Boolean.TRUE);
        s.setKurzvorhalb(Boolean.TRUE);
        s.setKurznachhalb(Boolean.TRUE);


        Pieces p = new Pieces("11:20");
        String out = tiw.getTimeAsSentance(p,s);
        assertEquals("zehn vor halb zwölf", out);

        p = new Pieces("11:24");
        out = tiw.getTimeAsSentance(p,s);
        assertEquals("vierundzwanzig nach elf", out);

        p = new Pieces("11:29");
        out = tiw.getTimeAsSentance(p,s);
        assertEquals("kurz vor halb zwölf", out);

        p = new Pieces("11:30");
        out = tiw.getTimeAsSentance(p,s);
        assertEquals("halb zwölf", out);

        p = new Pieces("11:34");
        out = tiw.getTimeAsSentance(p,s);
        assertEquals("kurz nach halb zwölf", out);


        p = new Pieces("11:38");
        out = tiw.getTimeAsSentance(p,s);
        assertEquals("zweiundzwanzig vor zwölf", out);


        p = new Pieces("11:40");
        out = tiw.getTimeAsSentance(p,s);
        assertEquals("zehn nach halb zwölf", out);

    }
}