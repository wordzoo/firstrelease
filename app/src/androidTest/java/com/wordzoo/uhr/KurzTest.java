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
public class KurzTest extends AndroidJUnitRunner {

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

        s.setKurznach(Boolean.TRUE);
        s.setKurzvor(Boolean.TRUE);

       s.setKurznachhalb(Boolean.TRUE);
       s.setKurzvorhalb(Boolean.TRUE);

       s.setKurznachviertelacht(Boolean.TRUE);
       s.setKurzvorviertelacht(Boolean.TRUE);

       s.setKurzvordreiviertelacht(Boolean.TRUE);
       s.setKurznachdreiviertelacht(Boolean.TRUE);

        Pieces p = new Pieces("11:57");
        String out = tiw.getTimeAsSentance(p,s);
        assertEquals("kurz vor zwölf", out);

        p = new Pieces("11:01");
        out = tiw.getTimeAsSentance(p,s);
        assertEquals("kurz nach elf", out);

       p = new Pieces("11:00");
       out = tiw.getTimeAsSentance(p,s);
       assertEquals("elf", out);


       p = new Pieces("11:11");
        out = tiw.getTimeAsSentance(p,s);
        assertEquals("kurz vor viertel zwölf", out);

        p = new Pieces("11:16");
        out = tiw.getTimeAsSentance(p,s);
        assertEquals("kurz nach viertel zwölf", out);

        p = new Pieces("11:20");
        out = tiw.getTimeAsSentance(p,s);
        assertEquals("zwanzig nach elf", out);


        p = new Pieces("11:29");
        out = tiw.getTimeAsSentance(p,s);
        assertEquals("kurz vor halb zwölf", out);


        p = new Pieces("11:34");
        out = tiw.getTimeAsSentance(p,s);
        assertEquals("kurz nach halb zwölf", out);

        p = new Pieces("11:41");
        out = tiw.getTimeAsSentance(p,s);
        assertEquals("kurz vor dreiviertel zwölf", out);

        p = new Pieces("11:43");
        out = tiw.getTimeAsSentance(p,s);
        assertEquals("kurz vor dreiviertel zwölf", out);

        p = new Pieces("11:48");
        out = tiw.getTimeAsSentance(p,s);
        assertEquals("kurz nach dreiviertel zwölf", out);

       p = new Pieces("11:50");
       out = tiw.getTimeAsSentance(p,s);
       assertEquals("zehn vor zwölf", out);

   }

}