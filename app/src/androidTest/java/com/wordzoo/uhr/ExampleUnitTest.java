package com.wordzoo.uhr;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnitRunner;
import android.test.InstrumentationTestCase;

import com.germanclock.time.Pieces;
import com.germanclock.time.Settings;
import com.germanclock.words.TimeInWords;
import android.os.Parcel;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.test.suitebuilder.annotation.SmallTest;
import android.util.Pair;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.util.List;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ExampleUnitTest extends AndroidJUnitRunner {

    private Context context;


       protected void tearDown() throws Exception {

    }


    protected void setUp() {
        this.context =   InstrumentationRegistry.getContext();
                //this.getInstrumentation().getTargetContext();


    }

    @Test
    public void testWords()   {


        Settings s = new Settings();
        // Five ocklock Evening tests
        s.setUmgangssprachlich(Boolean.TRUE);
        Pieces p = new Pieces("17:00");
        TimeInWords tiw = new TimeInWords(InstrumentationRegistry.getTargetContext());
        String out = tiw.getTimeAsSentance(p,s);
        assertEquals("fünf", out);

        s.setEsist(Boolean.TRUE);
        out = tiw.getTimeAsSentance(p,s);
        assertEquals("Es ist fünf", out);

        s.setUhr(Boolean.TRUE);
        out = tiw.getTimeAsSentance(p,s);
        assertEquals("Es ist fünf Uhr", out);

        s.setNachmittags(Boolean.TRUE);
        out = tiw.getTimeAsSentance(p,s);
        assertEquals("Es ist fünf Uhr nachmittags", out);
        s.setNachmittags(Boolean.FALSE);

        s.setAmnachmittag(Boolean.TRUE);
        out = tiw.getTimeAsSentance(p,s);
        assertEquals("Es ist fünf Uhr am Nachmittag", out);
        s.setAmnachmittag(Boolean.FALSE);

        s.setAmabend(Boolean.TRUE);
        s.setUmgangminute(Settings.Umgangminute.minuteword);
        p = new Pieces("18:17");
        out = tiw.getTimeAsSentance(p,s);
        assertEquals("Es ist siebzehn nach sechs Uhr am Abend", out);
        s.setAmabend(Boolean.FALSE);

        p = new Pieces("17:05");
        out = tiw.getTimeAsSentance(p,s);
        assertEquals("Es ist fünf nach fünf Uhr", out);

        //mitternachts
    }


/*    private Boolean flag = Boolean.FALSE;

    public enum FlagPattern {
        vienna,
        dambach,
        kaernten
    }

    private FlagPattern flagPattern = FlagPattern.vienna;

    private Boolean esist = Boolean.FALSE;;
    private Boolean umgangssprachlich = Boolean.FALSE;;


    //you can only choose this of umgangssprachlich is true
    public enum Umgangminute {
        minutebar,
        minuteword
    }

    private Umgangminute umgangminute = Umgangminute.minutebar;


    //note if umgangssprachlich get false, nothing below here applies
    //note for block layout, you can only have Umgangminute.minutebar
    public enum Clockface {
        block,
        sentance
    }

    private Clockface clockface = Clockface.sentance;




	private Boolean kurznach = Boolean.FALSE;;


    public enum Viertel {
        viertelueber,
        viertelnach,
        viertelacht
    }

    private Viertel viertel = Viertel.viertelnach;

	//if viertel == viertel.vieterlacht, two more options
    private Boolean fuenfvorviertelacht = Boolean.FALSE;;
    private Boolean fuenfnachviertelacht = Boolean.FALSE;;



    //halb is selected be default with umgrangsprache
    private Boolean halb = Boolean.FALSE;;

    //these are available only if you choose halb
    private Boolean fuenfvorhalb = Boolean.FALSE;;
    private Boolean fuenfnachhalb = Boolean.FALSE;;
    private Boolean kurzvorhalb = Boolean.FALSE;;
    private Boolean kurznachhalb = Boolean.FALSE;;


    private Boolean zehnvorhalb = Boolean.FALSE;;
    private Boolean zehnnachhalb = Boolean.FALSE;;
    //or
    private Boolean zwanzignach = Boolean.FALSE;;
    private Boolean zwanzigvor = Boolean.FALSE;;


*/
}