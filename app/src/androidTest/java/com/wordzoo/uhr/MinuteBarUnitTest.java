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
public class MinuteBarUnitTest extends AndroidJUnitRunner {

    private Context context;


    protected void tearDown() throws Exception {

    }


    protected void setUp() {
        this.context =   InstrumentationRegistry.getContext();
    }

    @Test
    public void testMultipleOfFive() {


        Settings s = new Settings();
        TimeInWords tiw = new TimeInWords(InstrumentationRegistry.getTargetContext());

        // Basic umgangsprashlich test
        s.setUmgangssprachlich(Boolean.TRUE);
        s.setEsist(Boolean.TRUE);
        s.setMinute(Boolean.TRUE);
        s.setUhr(Boolean.TRUE);
        s.setUmgangminute(Settings.Umgangminute.minutebar);

        Pieces p = new Pieces("11:00");
        String out = tiw.getTimeAsSentance(p, s);
        assertEquals("Es ist elf Uhr", out);
        assertEquals(p.getRemainderMinutes(), new Integer(0));

        p = new Pieces("11:03");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("Es ist elf Uhr", out);
        assertEquals(p.getRemainderMinutes(), new Integer(3));

        s.setKurzvor(Boolean.TRUE);
        s.setKurznach(Boolean.TRUE);
        p = new Pieces("23:59");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("Es ist kurz vor Mitternacht", out);
        assertEquals(p.getRemainderMinutes(), new Integer(4));

        p = new Pieces("00:01");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("Es ist kurz nach Mitternacht", out);
        assertEquals(p.getRemainderMinutes(), new Integer(1));

        p = new Pieces("01:33");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("Es ist dreißig Minuten vor zwei Uhr", out);
        assertEquals(p.getRemainderMinutes(), new Integer(3));


    }
}