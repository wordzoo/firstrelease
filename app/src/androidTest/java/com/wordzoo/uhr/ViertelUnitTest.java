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
public class ViertelUnitTest extends AndroidJUnitRunner {

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
        s.setUmgangminute(Settings.Umgangminute.minuteword);

        s.setViertel(Settings.Viertel.viertelacht);

        Pieces p = new Pieces("21:13");
        String out = tiw.getTimeAsSentance(p,s);
        assertEquals("dreizehn nach neun", out);


        p = new Pieces("21:15");
        out = tiw.getTimeAsSentance(p,s);
        assertEquals("viertel zehn", out);


        s.setViertel(Settings.Viertel.viertelnach);
        p = new Pieces("21:15");
        out = tiw.getTimeAsSentance(p,s);
        assertEquals("viertel nach neun", out);

        p = new Pieces("21:17");
        out = tiw.getTimeAsSentance(p,s);
        assertEquals("siebzehn nach neun", out);


        s.setViertel(Settings.Viertel.viertelueber);
        p = new Pieces("21:15");
        out = tiw.getTimeAsSentance(p,s);
        assertEquals("viertel über neun", out);

        p = new Pieces("21:14");
        out = tiw.getTimeAsSentance(p,s);
        assertEquals("vierzehn nach neun", out);

        s.setViertel(Settings.Viertel.viertelfuenfzehn);
        p = new Pieces("21:15");
        out = tiw.getTimeAsSentance(p,s);
        assertEquals("fünfzehn nach neun", out);



        s.setDreiviertel(Settings.Dreiviertel.dreiviertelacht);
        p = new Pieces("21:44");
        out = tiw.getTimeAsSentance(p,s);
        assertEquals("sechzehn vor zehn", out);

        p = new Pieces("21:45");
        out = tiw.getTimeAsSentance(p,s);
        assertEquals("dreiviertel zehn", out);

        s.setDreiviertel(Settings.Dreiviertel.viertelvor);
        p = new Pieces("21:46");
        out = tiw.getTimeAsSentance(p,s);
        assertEquals("vierzehn vor zehn", out);

        p = new Pieces("21:45");
        out = tiw.getTimeAsSentance(p,s);
        assertEquals("viertel vor zehn", out);


        s.setDreiviertel(Settings.Dreiviertel.fuenfzehn);
        p = new Pieces("21:45");
        out = tiw.getTimeAsSentance(p,s);
        assertEquals("fünfzehn vor zehn", out);






    }

}