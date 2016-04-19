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
public class UmgangUnitTest extends AndroidJUnitRunner {

    private Context context;


    protected void tearDown() throws Exception {

    }


    protected void setUp() {
        this.context =   InstrumentationRegistry.getContext();
    }

    @Test
    public void testMultipleOfFive()   {


        Settings s = new Settings();
        TimeInWords tiw = new TimeInWords(InstrumentationRegistry.getTargetContext());

        // Basic umgangsprashlich test
        s.setUmgangssprachlich(Boolean.TRUE);
        s.setUmgangminute(Settings.Umgangminute.minutebar);


        Pieces p = new Pieces("00:00");
        String out = tiw.getTimeAsSentance(p,s);
        assertEquals("Mitternacht", out);

        p = new Pieces("00:05");
        out = tiw.getTimeAsSentance(p,s);
        assertEquals("fünf nach Mitternacht", out);

        p = new Pieces("00:10");
        out = tiw.getTimeAsSentance(p,s);
        assertEquals("zehn nach Mitternacht", out);

        p = new Pieces("00:15");
        out = tiw.getTimeAsSentance(p,s);
        assertEquals("fünfzehn nach Mitternacht", out);

        p = new Pieces("00:20");
        out = tiw.getTimeAsSentance(p,s);
        assertEquals("zwanzig nach Mitternacht", out);


        p = new Pieces("00:25");
        out = tiw.getTimeAsSentance(p,s);
        assertEquals("fünfundzwanzig nach Mitternacht", out);


        p = new Pieces("00:30");
        out = tiw.getTimeAsSentance(p,s);
        assertEquals("dreißig nach Mitternacht", out);


        p = new Pieces("00:35");
        out = tiw.getTimeAsSentance(p,s);
        assertEquals("fünfundzwanzig vor eins", out);


        p = new Pieces("00:40");
        out = tiw.getTimeAsSentance(p,s);
        assertEquals("zwanzig vor eins", out);


        p = new Pieces("00:45");
        out = tiw.getTimeAsSentance(p,s);
        assertEquals("fünfzehn vor eins", out);


        p = new Pieces("00:50");
        out = tiw.getTimeAsSentance(p,s);
        assertEquals("zehn vor eins", out);


        p = new Pieces("00:55");
        out = tiw.getTimeAsSentance(p,s);
        assertEquals("fünf vor eins", out);


    }

    @Test
    public void testMultipleMinutes() {


        Settings s = new Settings();
        TimeInWords tiw = new TimeInWords(InstrumentationRegistry.getTargetContext());

        // Basic umgangsprashlich test
        s.setUmgangssprachlich(Boolean.TRUE);
        s.setUmgangminute(Settings.Umgangminute.minuteword);


        Pieces p = new Pieces("12:11");
        String out = tiw.getTimeAsSentance(p, s);
        assertEquals("elf nach zwölf", out);

        p = new Pieces("13:11");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("elf nach eins", out);

        p = new Pieces("13:01");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("eins nach eins", out);
    }


}