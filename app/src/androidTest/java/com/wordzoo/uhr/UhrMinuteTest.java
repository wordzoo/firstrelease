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
public class UhrMinuteTest extends AndroidJUnitRunner {

    private Context context;


       protected void tearDown() throws Exception {

    }


    protected void setUp() {
        this.context =   InstrumentationRegistry.getContext();
    }

    @Test
    public void testMinuteWords() {


        Settings s = new Settings();
        TimeInWords tiw = new TimeInWords(InstrumentationRegistry.getTargetContext());

        s.setUmgangssprachlich(Boolean.TRUE);
        s.setUmgangminute(Settings.Umgangminute.minuteword);
        s.setUhr(Boolean.TRUE);



        Pieces p = new Pieces("01:01");
        String out = tiw.getTimeAsSentance(p, s);
        assertEquals("eins nach ein Uhr", out);

        s.setMinute(Boolean.TRUE);

        p = new Pieces("01:01");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("ein Minute nach ein Uhr", out);

        p = new Pieces("01:02");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("zwei Minuten nach ein Uhr", out);

        s.setUhr(Boolean.FALSE);

        p = new Pieces("01:02");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("zwei Minuten nach eins", out);

        s.setMinute(Boolean.FALSE);

        p = new Pieces("01:01");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("eins nach eins", out);

        p = new Pieces("01:15");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("fünfzehn nach eins", out);

        p = new Pieces("01:50");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("zehn vor zwei", out);


    }

    @Test
    public void testTODMinuteUhr() {


        Settings s = new Settings();
        TimeInWords tiw = new TimeInWords(InstrumentationRegistry.getTargetContext());
        s.setUmgangssprachlich(Boolean.TRUE);
        s.setUmgangminute(Settings.Umgangminute.minutebar);
        s.setUhr(Boolean.TRUE);
        s.setMinute(Boolean.TRUE);
        s.setEsist(Boolean.TRUE);
        s.setAmvormittag(Boolean.TRUE);


        Pieces p = new Pieces("11:37");
        String out = tiw.getTimeAsSentance(p, s);
        assertEquals("Es ist fünfundzwanzig Minuten vor zwölf Uhr am Vormittag", out);


    }

    @Test
    public void testOfficialMinuteUhr() {


        Settings s = new Settings();
        TimeInWords tiw = new TimeInWords(InstrumentationRegistry.getTargetContext());
        s.setUmgangssprachlich(Boolean.FALSE);
        s.setUhr(Boolean.TRUE);
        s.setMinute(Boolean.TRUE);
        s.setEsist(Boolean.TRUE);


        Pieces p = new Pieces("11:27");
        String out = tiw.getTimeAsSentance(p, s);
        assertEquals("Es ist elf Uhr siebenundzwanzig Minuten", out);


    }
}