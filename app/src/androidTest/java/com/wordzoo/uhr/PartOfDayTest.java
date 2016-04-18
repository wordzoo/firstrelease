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
public class PartOfDayTest extends AndroidJUnitRunner {

    private Context context;


       protected void tearDown() throws Exception {

    }


    protected void setUp() {
        this.context =   InstrumentationRegistry.getContext();
    }

    @Test
    public void testAmBasic() {


        Settings s = new Settings();
        TimeInWords tiw = new TimeInWords(InstrumentationRegistry.getTargetContext());

        s.setUmgangssprachlich(Boolean.TRUE);
        s.setUmgangminute(Settings.Umgangminute.minuteword);
        s.setUhr(Boolean.TRUE);

        s.setAmmorgen(Boolean.TRUE);
        s.setAmvormittag(Boolean.TRUE);
        s.setAmmittag(Boolean.TRUE);
        s.setAmnachmittag(Boolean.TRUE);
        s.setAmabend(Boolean.TRUE);
        s.setIndernacht(Boolean.TRUE);

        //null Uhr fünf
        Pieces p = new Pieces("00:00");
        String out = tiw.getTimeAsSentance(p, s);
        assertEquals("null Uhr in der Nacht", out);

        p = new Pieces("00:10");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("null Uhr zehn in der Nacht", out);

        p = new Pieces("01:10");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("ein Uhr zehn in der Nacht", out);

        p = new Pieces("02:10");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("zwei Uhr zehn in der Nacht", out);

        p = new Pieces("03:10");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("drei Uhr zehn in der Nacht", out);

        p = new Pieces("04:10");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("vier Uhr zehn in der Nacht", out);

        p = new Pieces("05:10");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("fünf Uhr zehn am Morgen", out);

        p = new Pieces("06:10");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("sechs Uhr zehn am Morgen", out);

        p = new Pieces("07:10");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("sieben Uhr zehn am Morgen", out);

        p = new Pieces("08:10");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("acht Uhr zehn am Morgen", out);

        p = new Pieces("09:10");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("neun Uhr zehn am Morgen", out);

        p = new Pieces("10:10");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("zehn Uhr zehn am Vormittag", out);

        p = new Pieces("11:10");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("elf Uhr zehn am Vormittag", out);

        p = new Pieces("12:10");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("zwölf Uhr zehn am Mittag", out);

        p = new Pieces("13:10");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("ein Uhr zehn am Mittag", out);

        p = new Pieces("14:10");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("zwei Uhr zehn am Nachmittag", out);

        p = new Pieces("15:10");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("drei Uhr zehn am Nachmittag", out);

        p = new Pieces("16:10");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("vier Uhr zehn am Nachmittag", out);

        p = new Pieces("17:10");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("fünf Uhr zehn am Nachmittag", out);

        p = new Pieces("18:10");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("sechs Uhr zehn am Abend", out);

        p = new Pieces("19:10");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("sieben Uhr zehn am Abend", out);

        p = new Pieces("20:10");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("acht Uhr zehn am Abend", out);

        p = new Pieces("21:10");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("neun Uhr zehn am Abend", out);

        p = new Pieces("22:10");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("zehn Uhr zehn in der Nacht", out);

        p = new Pieces("23:10");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("elf Uhr zehn in der Nacht", out);

    }

    @Test
    public void testAmBasic() {


        Settings s = new Settings();
        TimeInWords tiw = new TimeInWords(InstrumentationRegistry.getTargetContext());

        s.setUmgangssprachlich(Boolean.TRUE);
        s.setUmgangminute(Settings.Umgangminute.minuteword);
        s.setUhr(Boolean.TRUE);

        s.setMorgens(Boolean.TRUE);
        s.setVormittags(Boolean.TRUE);
        s.setMittags(Boolean.TRUE);
        s.setNachmittags(Boolean.TRUE);
        s.setAbends(Boolean.TRUE);
        s.setNachts(Boolean.TRUE);

        //null Uhr fünf
        Pieces p = new Pieces("00:00");
        String out = tiw.getTimeAsSentance(p, s);
        assertEquals("null Uhr nachts", out);

        p = new Pieces("00:10");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("null Uhr zehn nachts", out);

        p = new Pieces("01:10");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("ein Uhr zehn nachts", out);

        p = new Pieces("02:10");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("zwei Uhr zehn nachts", out);

        p = new Pieces("03:10");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("drei Uhr zehn nachts", out);

        p = new Pieces("04:10");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("vier Uhr zehn nachts", out);

        p = new Pieces("05:10");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("fünf Uhr zehn morgens", out);

        p = new Pieces("06:10");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("sechs Uhr zehn morgens", out);

        p = new Pieces("07:10");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("sieben Uhr zehn morgens", out);

        p = new Pieces("08:10");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("acht Uhr zehn morgens", out);

        p = new Pieces("09:10");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("neun Uhr zehn morgens", out);

        p = new Pieces("10:10");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("zehn Uhr zehn morgens", out);

        p = new Pieces("11:10");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("elf Uhr zehn vormittags", out);

        p = new Pieces("12:10");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("zwölf Uhr zehn mittags", out);

        p = new Pieces("13:10");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("ein Uhr zehn mittags", out);

        p = new Pieces("14:10");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("zwei Uhr zehn nachmittags", out);

        p = new Pieces("15:10");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("drei Uhr zehn nachmittags", out);

        p = new Pieces("16:10");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("vier Uhr zehn nachmittags", out);

        p = new Pieces("17:10");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("fünf Uhr zehn nachmittags", out);

        p = new Pieces("18:10");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("sechs Uhr zehn abends", out);

        p = new Pieces("19:10");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("sieben Uhr zehn abends", out);

        p = new Pieces("20:10");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("acht Uhr zehn abends", out);

        p = new Pieces("21:10");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("neun Uhr zehn abends", out);

        p = new Pieces("22:10");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("zehn Uhr zehn in nachts", out);

        p = new Pieces("23:10");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("elf Uhr zehn nachts", out);

    }

    /*
    @Test
    public void testInDerFrueh() {
    }

    @Test
    public void testMorgenNacht() {
    }*/
}