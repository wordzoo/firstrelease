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
public class MitternachtUnitTest extends AndroidJUnitRunner {

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

        s.setUmgangssprachlich(Boolean.TRUE);
        s.setMitternacht(Boolean.TRUE); //note: currently implied by setUmgangsprachlich()
        s.setUmgangminute(Settings.Umgangminute.minuteword);
        s.setIndernacht(Boolean.TRUE);

        //fünf Minuten nach Mitternacht
        Pieces p = new Pieces("00:00");
        String out = tiw.getTimeAsSentance(p,s);
        assertEquals("Mitternacht", out);

        p = new Pieces("00:05");
        out = tiw.getTimeAsSentance(p,s);
        assertEquals("fünf nach Mitternacht", out);

        p = new Pieces("23:50");
        out = tiw.getTimeAsSentance(p,s);
        assertEquals("zehn vor Mitternacht", out);

        p = new Pieces("00:50");
        out = tiw.getTimeAsSentance(p,s);
        assertEquals("zehn vor eins in der Nacht", out);

    }

}