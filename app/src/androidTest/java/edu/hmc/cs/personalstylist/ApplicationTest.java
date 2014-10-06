package edu.hmc.cs.personalstylist;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.test.InstrumentationTestCase;
import android.util.Log;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends InstrumentationTestCase {
    public void test() throws Exception {
        Log.d("WardrobeTests", "yo");
        final int expected = 1;
        final int reality = 1;
        assertEquals(expected, reality);
    }

    public void test_emptyWardrobe() throws Exception {
        Wardrobe emptyWardrobe = new Wardrobe();
        int len = emptyWardrobe.wardrobeLength();
        assertEquals(len, 0);
    }

    public void test_singleWardrobe() throws Exception {
        Wardrobe singleWardrobe = new Wardrobe();
        singleWardrobe.addArticle("tester", Wardrobe.ClothingType.HAT, Wardrobe.ClothingColor.RED, Wardrobe.ClothingFormality.FORMAL, Wardrobe.ClothingTemperature.COLD);
        int len = singleWardrobe.wardrobeLength();
        assertEquals(len, 1);
        Wardrobe.Clothing testcloth = singleWardrobe.getArticle("tester");
        assertTrue(testcloth != null);

    }
}
