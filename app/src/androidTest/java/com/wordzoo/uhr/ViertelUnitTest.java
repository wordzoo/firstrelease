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

        // Basic umgangsprashlich test
        s.setUmgangssprachlich(Boolean.TRUE);
        s.setMinuteHybrid(Boolean.TRUE);
        s.setUmgangminute(Settings.Umgangminute.minuteword);

        s.setEsist(Boolean.TRUE);
        s.setUhr(Boolean.TRUE);
        s.setMinute(Boolean.FALSE);

        s.setMitternacht(Boolean.TRUE);
        s.setKurzvor(Boolean.TRUE);
        s.setKurznach(Boolean.TRUE);

        s.setAmmorgen(Boolean.TRUE);
        s.setAmvormittag(Boolean.TRUE);
        s.setAmnachmittag(Boolean.TRUE);
        s.setAmabend(Boolean.TRUE);
        s.setIndernacht(Boolean.TRUE);

        s.setViertel(Settings.Viertel.viertelacht);
        s.setFuenfvorviertelacht(Boolean.TRUE);
        s.setFuenfnachviertelacht(Boolean.TRUE);

        s.setHalb(Boolean.TRUE);
        s.setFuenfvorhalb(Boolean.TRUE);
        s.setFuenfnachhalb(Boolean.TRUE);
        s.setKurzvorhalb(Boolean.TRUE);
        s.setKurznachhalb(Boolean.TRUE);

        s.setDreiviertel(Settings.Dreiviertel.dreiviertelacht);
        s.setFuenfvordreiviertelacht(Boolean.TRUE);
        s.setFuenfnachdreiviertelacht(Boolean.TRUE);



        Pieces p = new Pieces("21:00");
        String out = tiw.getTimeAsSentance(p,s);
        assertEquals("Es ist neun Uhr am Abend", out);

        p = new Pieces("21:01");
        out = tiw.getTimeAsSentance(p,s);
        assertEquals("Es ist neun Uhr eins am Abend", out);

        p = new Pieces("21:12");
        out = tiw.getTimeAsSentance(p,s);
        assertEquals("Es ist neun Uhr zwölf am Abend", out);

        p = new Pieces("21:15");
        out = tiw.getTimeAsSentance(p,s);
        assertEquals("Es ist viertel zehn in der Nacht", out);

        p = new Pieces("21:18");
        out = tiw.getTimeAsSentance(p,s);
        assertEquals("Es ist neun Uhr achtzehn in der Nacht", out);


        p = new Pieces("21:29");
        out = tiw.getTimeAsSentance(p,s);
        assertEquals("Es ist kurz vor halb zehn in der Nacht", out);


        p = new Pieces("23:31");
        out = tiw.getTimeAsSentance(p,s);
        assertEquals("Es ist kurz nach halb zehn in der Nacht", out);


        p = new Pieces("23:37");
        out = tiw.getTimeAsSentance(p,s);
        assertEquals("Es ist dreiundzwanzig vor zehn Uhr in der Nacht", out);


        p = new Pieces("23:45");
        out = tiw.getTimeAsSentance(p,s);
        assertEquals("Es ist dreiviertel zwölf in der Nacht", out);


        p = new Pieces("23:47");
        out = tiw.getTimeAsSentance(p,s);
        assertEquals("Es ist dreiviertel eins in der Nacht", out);





    }

}