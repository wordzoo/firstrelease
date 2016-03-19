package com.wordzoo.uhr;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.util.Log;

import com.germanclock.time.Settings;
import com.germanclock.time.Pieces;
import com.germanclock.words.TimeInWords;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class TimeInWordsTest extends ApplicationTestCase<Application> {
    public TimeInWordsTest() {
        super(Application.class);
    }

    public void testWord() {
        Settings s = new Settings();
        s.setUmgangssprachlich(Boolean.TRUE);
        s.setViertel(Settings.Viertel.viertelnach);
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("h:mm");
        Pieces p = new Pieces(sdf.format(d));
        TimeInWords v = new TimeInWords();
        String out = v.getVerbalTime(p, s);
        Log.i("testWord() ", out);


        p = new Pieces("17:15");

        out = v.getVerbalTime(p, s);

        assertEquals(out, "viertel nach fünf");


    }
}