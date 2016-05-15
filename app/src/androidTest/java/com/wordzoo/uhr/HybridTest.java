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

    @Test
    public void hybridUmgang() {

        Settings s = new Settings();
        TimeInWords tiw = new TimeInWords(InstrumentationRegistry.getTargetContext());

        s.setUmgangssprachlich(Boolean.TRUE);
        s.setUmgangminute(Settings.Umgangminute.minuteword);
        s.setMinuteHybrid(Boolean.TRUE);

        s.setEsist(Boolean.TRUE);
        s.setUhr(Boolean.TRUE);
        s.setMinute(Boolean.TRUE);
        s.setUmgangssprachlich(Boolean.TRUE);
        s.setUmgangminute(Settings.Umgangminute.minuteword);
        s.setKurznach(Boolean.TRUE);
        s.setViertel(Settings.Viertel.viertelacht);
        s.setZehnvorhalb(Boolean.TRUE);
        s.setFuenfvorhalb(Boolean.TRUE);
        s.setKurzvorhalb(Boolean.TRUE);
        s.setHalb(Settings.Halb.halb);
        s.setKurznachhalb(Boolean.TRUE);
        s.setFuenfnachhalb(Boolean.TRUE);
        s.setZehnnachhalb(Boolean.TRUE);
        s.setFuenfvordreiviertelacht(Boolean.TRUE);
        s.setKurzvordreiviertelacht(Boolean.TRUE);
        s.setDreiviertel(Settings.Dreiviertel.dreiviertelacht);
        s.setKurznachdreiviertelacht(Boolean.TRUE);
        s.setFuenfnachdreiviertelacht(Boolean.TRUE);
        s.setKurzvor(Boolean.TRUE);
        s.setAmabend(Boolean.TRUE);
        s.setIndernacht(Boolean.TRUE);
        s.setAmmorgen(Boolean.TRUE);
        s.setInderfrueh(Boolean.TRUE);
        s.setAmnachmittag(Boolean.TRUE);
        s.setAmvormittag(Boolean.TRUE);

        Pieces p = new Pieces("11:00");
        String out = tiw.getTimeAsSentance(p, s);
        assertEquals("Es ist elf Uhr am Vormittag", out);

        p = new Pieces("11:02");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("Es ist elf Uhr zwei Minuten", out);

        p = new Pieces("11:06");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("Es ist elf Uhr sechs Minuten", out);

        p = new Pieces("11:56");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("Es ist elf Uhr sechsundfünfzig Minuten", out);

        p = new Pieces("11:50");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("Es ist fünf Minuten nach dreiviertel zwölf am Vormittag", out);

        p = new Pieces("11:20");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("Es ist zehn Minuten vor halb zwölf am Vormittag", out);

        p = new Pieces("22:20");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("Es ist zehn Minuten vor halb elf in der Nacht", out);

        p = new Pieces("22:22");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("Es ist zweiundzwanzig Uhr zweiundzwanzig Minuten", out);
    }

    }