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

        Pieces p = new Pieces("00:00");
        String out = tiw.getTimeAsSentance(p, s);
        assertEquals("Mitternacht", out);

        p = new Pieces("00:10");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("zehn nach Mitternacht", out);

        p = new Pieces("01:10");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("zehn nach ein Uhr in der Nacht", out);

        p = new Pieces("02:10");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("zehn nach zwei Uhr in der Nacht", out);

        p = new Pieces("03:10");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("zehn nach drei Uhr in der Nacht", out);

        p = new Pieces("04:10");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("zehn nach vier Uhr in der Nacht", out);

        p = new Pieces("05:10");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("zehn nach fünf Uhr am Morgen", out);

        p = new Pieces("06:10");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("zehn nach sechs Uhr am Morgen", out);

        p = new Pieces("07:10");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("zehn nach sieben Uhr am Morgen", out);

        p = new Pieces("08:10");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("zehn nach acht Uhr am Morgen", out);

        p = new Pieces("09:10");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("zehn nach neun Uhr am Morgen", out);

        p = new Pieces("10:10");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("zehn nach zehn Uhr am Vormittag", out);

        p = new Pieces("11:10");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("zehn nach elf Uhr am Vormittag", out);

        p = new Pieces("12:10");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("zehn nach zwölf Uhr am Mittag", out);

        p = new Pieces("13:10");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("zehn nach ein Uhr am Mittag", out);

        p = new Pieces("14:10");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("zehn nach zwei Uhr am Nachmittag", out);

        p = new Pieces("15:10");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("zehn nach drei Uhr am Nachmittag", out);

        p = new Pieces("16:10");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("zehn nach vier Uhr am Nachmittag", out);

        p = new Pieces("17:10");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("zehn nach fünf Uhr am Nachmittag", out);

        p = new Pieces("18:10");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("zehn nach sechs Uhr am Abend", out);

        p = new Pieces("19:10");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("zehn nach sieben Uhr am Abend", out);

        p = new Pieces("20:10");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("zehn nach acht Uhr am Abend", out);

        p = new Pieces("21:10");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("zehn nach neun Uhr am Abend", out);

        p = new Pieces("22:10");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("zehn nach zehn Uhr in der Nacht", out);

        p = new Pieces("23:10");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("zehn nach elf Uhr in der Nacht", out);

    }

    @Test
    public void testMorgens() {


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
        assertEquals("Mitternacht", out);

        p = new Pieces("00:10");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("zehn nach Mitternacht", out);

        p = new Pieces("01:10");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("zehn nach ein Uhr nachts", out);

        p = new Pieces("02:10");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("zehn nach zwei Uhr nachts", out);

        p = new Pieces("03:10");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("zehn nach drei Uhr nachts", out);

        p = new Pieces("04:10");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("zehn nach vier Uhr nachts", out);

        p = new Pieces("05:10");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("zehn nach fünf Uhr morgens", out);

        p = new Pieces("06:10");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("zehn nach sechs Uhr morgens", out);

        p = new Pieces("07:10");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("zehn nach sieben Uhr morgens", out);

        p = new Pieces("08:10");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("zehn nach acht Uhr morgens", out);

        p = new Pieces("09:10");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("zehn nach neun Uhr morgens", out);

        p = new Pieces("10:10");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("zehn nach zehn Uhr vormittags", out);

        p = new Pieces("11:10");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("zehn nach elf Uhr vormittags", out);

        p = new Pieces("12:10");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("zehn nach zwölf Uhr mittags", out);

        p = new Pieces("13:10");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("zehn nach ein Uhr mittags", out);

        p = new Pieces("14:10");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("zehn nach zwei Uhr nachmittags", out);

        p = new Pieces("15:10");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("zehn nach drei Uhr nachmittags", out);

        p = new Pieces("16:10");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("zehn nach vier Uhr nachmittags", out);

        p = new Pieces("17:10");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("zehn nach fünf Uhr nachmittags", out);

        p = new Pieces("18:10");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("zehn nach sechs Uhr abends", out);

        p = new Pieces("19:10");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("zehn nach sieben Uhr abends", out);

        p = new Pieces("20:10");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("zehn nach acht Uhr abends", out);

        p = new Pieces("21:10");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("zehn nach neun Uhr abends", out);

        p = new Pieces("22:10");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("zehn nach zehn Uhr nachts", out);

        p = new Pieces("23:10");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("zehn nach elf Uhr nachts", out);

    }


    @Test
    public void testInDerFrueh() {

        Settings s = new Settings();
        TimeInWords tiw = new TimeInWords(InstrumentationRegistry.getTargetContext());

        s.setUmgangssprachlich(Boolean.TRUE);
        s.setUmgangminute(Settings.Umgangminute.minuteword);
        s.setUhr(Boolean.TRUE);

        s.setInderfrueh(Boolean.TRUE);

        Pieces p = new Pieces("00:00");
        String out = tiw.getTimeAsSentance(p, s);
        assertEquals("Mitternacht", out);

        p = new Pieces("04:10");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("zehn nach vier Uhr in der Früh", out);

    }

    @Test
    public void testMorgenNacht() {

        Settings s = new Settings();
        TimeInWords tiw = new TimeInWords(InstrumentationRegistry.getTargetContext());

        s.setUmgangssprachlich(Boolean.TRUE);
        s.setUmgangminute(Settings.Umgangminute.minuteword);
        s.setUhr(Boolean.TRUE);

        s.setMorgennacht(Boolean.TRUE);
        s.setAmmorgen(Boolean.TRUE);

        //null Uhr fünf
        Pieces p = new Pieces("02:00");
        String out = tiw.getTimeAsSentance(p, s);
        assertEquals("zwei Uhr morgens", out);

        p = new Pieces("06:00");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("sechs Uhr am Morgen", out);

        s.setMorgennacht(Boolean.FALSE);
        s.setAmmorgennacht(Boolean.TRUE);
        s.setMorgens(Boolean.TRUE);
        s.setAmmorgen(Boolean.FALSE);


        p = new Pieces("04:00");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("vier Uhr am Morgen", out);

        p = new Pieces("08:00");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("acht Uhr morgens", out);


    }
}