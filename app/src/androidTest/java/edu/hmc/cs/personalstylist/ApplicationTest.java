package edu.hmc.cs.personalstylist;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.test.InstrumentationTestCase;
import android.util.Log;

import java.io.WriteAbortedException;

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
        singleWardrobe.addArticle("tester", Wardrobe.ClothingType.HAT,
                Wardrobe.ClothingColor.RED, Wardrobe.ClothingFormality.FORMAL,
                Wardrobe.ClothingTemperature.COLD);
        int len = singleWardrobe.wardrobeLength();
        assertEquals(len, 1);
        Wardrobe.Clothing testcloth = singleWardrobe.getArticle("tester");
        assertTrue(testcloth != null);
    }

    public void test_removeByClothing() throws Exception {
        Wardrobe wardrobe = new Wardrobe();

        wardrobe.addArticle("testhat", Wardrobe.ClothingType.HAT,
                Wardrobe.ClothingColor.RED, Wardrobe.ClothingFormality.FORMAL,
                Wardrobe.ClothingTemperature.COLD);
        wardrobe.addArticle("testshirt", Wardrobe.ClothingType.SHIRT,
                Wardrobe.ClothingColor.BLUE, Wardrobe.ClothingFormality.CASUAL,
                Wardrobe.ClothingTemperature.HOT);

        assertEquals(wardrobe.wardrobeLength(), 2);
        Wardrobe.Clothing testhat = wardrobe.getArticle("testhat");
        assertTrue(wardrobe.removeArticle(testhat));

        assertEquals(wardrobe.wardrobeLength(), 1);
        assertFalse(wardrobe.removeArticle(testhat));
        assertEquals(wardrobe.wardrobeLength(), 1);

        assertTrue(wardrobe.removeArticle(wardrobe.getArticle("testshirt")));
        assertEquals(wardrobe.wardrobeLength(), 0);
    }

    public void test_removeByString() throws Exception {
        Wardrobe wardrobe = new Wardrobe();

        wardrobe.addArticle("testhat", Wardrobe.ClothingType.HAT,
                Wardrobe.ClothingColor.RED, Wardrobe.ClothingFormality.FORMAL,
                Wardrobe.ClothingTemperature.COLD);
        wardrobe.addArticle("testshirt", Wardrobe.ClothingType.SHIRT,
                Wardrobe.ClothingColor.BLUE, Wardrobe.ClothingFormality.CASUAL,
                Wardrobe.ClothingTemperature.HOT);

        assertEquals(wardrobe.wardrobeLength(), 2);
        assertTrue(wardrobe.removeArticle("testhat"));

        assertEquals(wardrobe.wardrobeLength(), 1);
        assertFalse(wardrobe.removeArticle("testhat"));
        assertEquals(wardrobe.wardrobeLength(), 1);

        assertTrue(wardrobe.removeArticle("testshirt"));
        assertEquals(wardrobe.wardrobeLength(), 0);
    }
}
