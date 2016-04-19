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
public class HybridTest extends AndroidJUnitRunner {

    private Context context;


       protected void tearDown() throws Exception {

    }


    protected void setUp() {
        this.context =   InstrumentationRegistry.getContext();
    }

   @Test
    public void hybrid()   {

        Settings s = new Settings();
        TimeInWords tiw = new TimeInWords(InstrumentationRegistry.getTargetContext());

        // Basic umgangsprashlich test
        s.setUmgangssprachlich(Boolean.TRUE);
        s.setUmgangminute(Settings.Umgangminute.minuteword);
        s.setMinuteHybrid(Boolean.TRUE);

        Pieces p = new Pieces("11:20");
        String out = tiw.getTimeAsSentance(p,s);
        assertEquals("zwanzig nach elf", out);

        p = new Pieces("11:24");
        out = tiw.getTimeAsSentance(p,s);
        assertEquals("elf vierundzwanzig", out);

        p = new Pieces("11:29");
        out = tiw.getTimeAsSentance(p,s);
        assertEquals("elf neunundzwanzig", out);

        p = new Pieces("11:30");
        out = tiw.getTimeAsSentance(p,s);
        assertEquals("dreißig nach elf", out);

        p = new Pieces("11:34");
        out = tiw.getTimeAsSentance(p,s);
        assertEquals("elf vierunddreißig", out);


        p = new Pieces("11:38");
        out = tiw.getTimeAsSentance(p,s);
        assertEquals("elf achtunddreißig", out);


        p = new Pieces("11:40");
        out = tiw.getTimeAsSentance(p,s);
        assertEquals("zwanzig vor zwölf", out);

        p = new Pieces("11:41");
        out = tiw.getTimeAsSentance(p,s);
        assertEquals("elf einundvierzig", out);

        p = new Pieces("11:43");
        out = tiw.getTimeAsSentance(p,s);
        assertEquals("elf dreiundvierzig", out);

        p = new Pieces("11:48");
        out = tiw.getTimeAsSentance(p,s);
        assertEquals("elf achtundvierzig", out);

    }

}