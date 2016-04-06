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
public class OfficalUnitTest extends AndroidJUnitRunner {

    private Context context;


       protected void tearDown() throws Exception {

    }


    protected void setUp() {
        this.context =   InstrumentationRegistry.getContext();
    }

    @Test
    public void testOfficialLong() {


        Settings s = new Settings();
        TimeInWords tiw = new TimeInWords(InstrumentationRegistry.getTargetContext());


        // official full time

        s.setEsist(Boolean.TRUE);
        s.setUhr(Boolean.TRUE);
        s.setMinute(Boolean.TRUE);

        Pieces p = new Pieces("00:00");
        String out = tiw.getTimeAsSentance(p, s);
        assertEquals("Es ist null Uhr", out);

        p = new Pieces("01:01");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("Es ist ein Uhr ein Minute", out);

        p = new Pieces("02:02");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("Es ist zwei Uhr zwei Minuten", out);

        p = new Pieces("03:03");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("Es ist drei Uhr drei Minuten", out);

        p = new Pieces("04:04");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("Es ist vier Uhr vier Minuten", out);

        p = new Pieces("05:05");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("Es ist fünf Uhr fünf Minuten", out);

        p = new Pieces("06:06");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("Es ist sechs Uhr sechs Minuten", out);

        p = new Pieces("07:07");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("Es ist sieben Uhr sieben Minuten", out);

        p = new Pieces("08:08");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("Es ist acht Uhr acht Minuten", out);

        p = new Pieces("09:09");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("Es ist neun Uhr neun Minuten", out);

        p = new Pieces("10:10");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("Es ist zehn Uhr zehn Minuten", out);

        p = new Pieces("11:11");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("Es ist elf Uhr elf Minuten", out);


        p = new Pieces("12:12");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("Es ist zwölf Uhr zwölf Minuten", out);

        p = new Pieces("13:13");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("Es ist dreizehn Uhr dreizehn Minuten", out);


        p = new Pieces("14:14");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("Es ist vierzehn Uhr vierzehn Minuten", out);

        p = new Pieces("15:15");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("Es ist fünfzehn Uhr fünfzehn Minuten", out);

        p = new Pieces("16:16");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("Es ist sechzehn Uhr sechzehn Minuten", out);

        p = new Pieces("17:17");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("Es ist siebzehn Uhr siebzehn Minuten", out);

        p = new Pieces("18:18");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("Es ist achtzehn Uhr achtzehn Minuten", out);

        p = new Pieces("19:19");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("Es ist neunzehn Uhr neunzehn Minuten", out);

        p = new Pieces("20:20");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("Es ist zwanzig Uhr zwanzig Minuten", out);

        p = new Pieces("21:21");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("Es ist einundzwanzig Uhr einundzwanzig Minuten", out);

        p = new Pieces("22:22");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("Es ist zweiundzwanzig Uhr zweiundzwanzig Minuten", out);

        p = new Pieces("23:23");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("Es ist dreiundzwanzig Uhr dreiundzwanzig Minuten", out);

        p = new Pieces("00:24");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("Es ist null Uhr vierundzwanzig Minuten", out);

        p = new Pieces("13:25");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("Es ist dreizehn Uhr fünfundzwanzig Minuten", out);
    }

    @Test
    public void testOfficialShort() {


        Settings s = new Settings();
        TimeInWords tiw = new TimeInWords(InstrumentationRegistry.getTargetContext());


        //start official short
        s.setUhr(Boolean.TRUE);



        Pieces p = new Pieces("01:01");
        String out = tiw.getTimeAsSentance(p, s);
        assertEquals("ein Uhr eins", out);

        p = new Pieces("13:26");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("dreizehn Uhr sechsundzwanzig", out);

        p = new Pieces("13:27");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("dreizehn Uhr siebenundzwanzig", out);

        p = new Pieces("13:28");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("dreizehn Uhr achtundzwanzig", out);

        p = new Pieces("13:29");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("dreizehn Uhr neunundzwanzig", out);

        p = new Pieces("13:30");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("dreizehn Uhr dreißig", out);

        p = new Pieces("13:31");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("dreizehn Uhr einunddreißig", out);

        p = new Pieces("13:32");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("dreizehn Uhr zweiunddreißig", out);

        p = new Pieces("13:33");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("dreizehn Uhr dreiunddreißig", out);

        p = new Pieces("13:34");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("dreizehn Uhr vierunddreißig", out);

        p = new Pieces("13:35");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("dreizehn Uhr fünfunddreißig", out);

        p = new Pieces("13:36");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("dreizehn Uhr sechsunddreißig", out);

        p = new Pieces("13:37");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("dreizehn Uhr siebenunddreißig", out);

        p = new Pieces("13:38");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("dreizehn Uhr achtunddreißig", out);

        p = new Pieces("13:39");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("dreizehn Uhr neununddreißig", out);

        p = new Pieces("13:40");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("dreizehn Uhr vierzig", out);

        p = new Pieces("13:41");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("dreizehn Uhr einundvierzig", out);

        p = new Pieces("13:42");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("dreizehn Uhr zweiundvierzig", out);

        p = new Pieces("13:43");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("dreizehn Uhr dreiundvierzig", out);

        p = new Pieces("13:44");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("dreizehn Uhr vierundvierzig", out);

        p = new Pieces("13:45");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("dreizehn Uhr fünfundvierzig", out);

        p = new Pieces("13:46");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("dreizehn Uhr sechsundvierzig", out);

        p = new Pieces("13:47");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("dreizehn Uhr siebenundvierzig", out);

        p = new Pieces("13:48");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("dreizehn Uhr achtundvierzig", out);

        p = new Pieces("13:49");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("dreizehn Uhr neunundvierzig", out);

        p = new Pieces("13:50");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("dreizehn Uhr fünfzig", out);

        p = new Pieces("13:51");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("dreizehn Uhr einundfünfzig", out);

        p = new Pieces("13:52");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("dreizehn Uhr zweiundfünfzig", out);

        p = new Pieces("13:53");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("dreizehn Uhr dreiundfünfzig", out);

        p = new Pieces("13:54");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("dreizehn Uhr vierundfünfzig", out);

        p = new Pieces("13:55");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("dreizehn Uhr fünfundfünfzig", out);

        p = new Pieces("13:56");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("dreizehn Uhr sechsundfünfzig", out);

        p = new Pieces("13:57");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("dreizehn Uhr siebenundfünfzig", out);

        p = new Pieces("13:58");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("dreizehn Uhr achtundfünfzig", out);

        p = new Pieces("13:59");
        out = tiw.getTimeAsSentance(p, s);
        assertEquals("dreizehn Uhr neunundfünfzig", out);






    }

}