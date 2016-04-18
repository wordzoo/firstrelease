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

        //fünf Minuten nach Mitternacht
        s.setUmgangssprachlich(Boolean.TRUE);
        s.setMinuteHybrid(Boolean.TRUE);
        s.setUmgangminute(Settings.Umgangminute.minuteword);

        s.setEsist(Boolean.TRUE);
        s.setUhr(Boolean.TRUE);
        s.setMinute(Boolean.TRUE);

        s.setMitternacht(Boolean.TRUE);
        s.setKurzvor(Boolean.FALSE);
        s.setKurznach(Boolean.FALSE);

        s.setMorgens(Boolean.TRUE);
        s.setVormittags(Boolean.TRUE);
        s.setNachmittags(Boolean.TRUE);
        s.setAbends(Boolean.TRUE);
        s.setIndernacht(Boolean.TRUE);


        Pieces p = new Pieces("23:00");
        String out = tiw.getTimeAsSentance(p,s);
        assertEquals("Es ist Mitternacht", out);

        p = new Pieces("23:01");
        out = tiw.getTimeAsSentance(p,s);
        assertEquals("Es ist fünf Minuten nach Mitternacht", out);

        p = new Pieces("23:12");
        out = tiw.getTimeAsSentance(p,s);
        assertEquals("Es ist fünf Minuten vor viertel eins in der Nacht", out);

        p = new Pieces("23:15");
        out = tiw.getTimeAsSentance(p,s);
        assertEquals("Es ist viertel eins in der Nacht", out);

        p = new Pieces("23:18");
        out = tiw.getTimeAsSentance(p,s);
        assertEquals("Es ist fünf Minuten nach viertel eins in der Nacht", out);


        p = new Pieces("23:29");
        out = tiw.getTimeAsSentance(p,s);
        assertEquals("Es ist fünf Minuten vor halb eins in der Nacht", out);


        p = new Pieces("23:31");
        out = tiw.getTimeAsSentance(p,s);
        assertEquals("Es ist halb eins in der Nacht", out);


        p = new Pieces("23:37");
        out = tiw.getTimeAsSentance(p,s);
        assertEquals("Es ist fünf Minuten nach halb eins in der Nacht", out);


        p = new Pieces("23:45");
        out = tiw.getTimeAsSentance(p,s);
        assertEquals("Es ist fünf Minuten vor dreiviertel eins in der Nacht", out);


        p = new Pieces("23:47");
        out = tiw.getTimeAsSentance(p,s);
        assertEquals("Es ist dreiviertel eins in der Nacht", out);





    }

}