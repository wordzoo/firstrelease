package com.wordzoo.uhr;

import android.app.Application;
import android.os.Debug;
import android.test.ApplicationTestCase;
import android.util.Log;

import com.germanclock.time.Pieces;
import com.germanclock.time.ViennaSettings;
import com.germanclock.words.LocalDialect;
import com.germanclock.words.ViennaDialect;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ViennaDialectTest extends ApplicationTestCase<Application> {
    public ViennaDialectTest() {
        super(Application.class);
    }

    public void testWord() {
        ViennaSettings s = new ViennaSettings();
        s.setUmgangssprachlich(10);
        s.setRangeForViertel(5);
        s.setRangeForHalb(5);
        s.setRangeForDreiViertel(5);

        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("h:mm");
        Pieces p = new Pieces(sdf.format(d));
        LocalDialect v = new ViennaDialect();
        String out = v.getVerbalTime(p, s);
        Log.i("testWord() ", out);


        p = new Pieces("17:15");

        out = v.getVerbalTime(p, s);

        assertEquals(out, "viertel nach fünf (plus remainder minutes: 0)");
    }
}