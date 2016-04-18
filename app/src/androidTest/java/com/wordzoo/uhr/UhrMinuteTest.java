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
    public void testMinuteBarWords() {


        Settings s = new Settings();
        TimeInWords tiw = new TimeInWords(InstrumentationRegistry.getTargetContext());

        s.setUmgangssprachlich(Boolean.TRUE);
        s.setUmgangminute(Settings.Umgangminute.minuteword);
        s.setUhr(Boolean.TRUE);



        Pieces p = new Pieces("01:01");
        String out = tiw.getTimeAsSentance(p, s);
        assertEquals("ein Uhr eins in der Nacht", out);

        s.setMinute(Boolean.TRUE);

        p = new Pieces("01:01");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("ein Uhr ein Minute", out);

        p = new Pieces("01:02");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("ein Uhr zwei Minuten", out);

        s.setUhr(Boolean.FALSE);

        p = new Pieces("01:02");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("eins zwei Minuten", out);

        s.setMinute(Boolean.FALSE);

        p = new Pieces("01:01");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("eins eins", out);

    }
}