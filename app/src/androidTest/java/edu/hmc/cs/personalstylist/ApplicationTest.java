package edu.hmc.cs.personalstylist;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.test.InstrumentationTestCase;
import android.util.Log;

import java.io.WriteAbortedException;
import java.util.ArrayList;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends InstrumentationTestCase {

    /// We created this test to make sure that the tests were running.
    public void test() throws Exception {
        Log.d("WardrobeTests", "yo");
        final int expected = 1;
        final int reality = 1;
        assertEquals(expected, reality);
    }

    /// This test ensures that we can create an empty wardrobe, and it knows it is empty.
    public void test_emptyWardrobe() throws Exception {
        Wardrobe emptyWardrobe = new Wardrobe();
        int len = emptyWardrobe.wardrobeLength();
        assertEquals(len, 0);
    }

    /// Test that we can add an item to the wardrobe, and find it again.
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

    /// Test the remove function that takes a Clothing object. We want to ensure that it
    /// works when we try to remove something that is not there.
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

        // Try to remove testhat a second time, should do nothing and return false.
        assertEquals(wardrobe.wardrobeLength(), 1);
        assertFalse(wardrobe.removeArticle(testhat));
        assertEquals(wardrobe.wardrobeLength(), 1);

        assertTrue(wardrobe.removeArticle(wardrobe.getArticle("testshirt")));
        assertEquals(wardrobe.wardrobeLength(), 0);
    }

    /// Test the remove function that takes a string naming the clothing object. This test
    /// checks that it works when we try to remove something that isn't there.
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

        // We have already removed "testhat", so removeArticle should do nothing and return
        // false.
        assertEquals(wardrobe.wardrobeLength(), 1);
        assertFalse(wardrobe.removeArticle("testhat"));
        assertEquals(wardrobe.wardrobeLength(), 1);

        assertTrue(wardrobe.removeArticle("testshirt"));
        assertEquals(wardrobe.wardrobeLength(), 0);
    }

    /// Test to choose formal tops.
    public void test_formalTops() throws Exception {
        Wardrobe singleWardrobe = new Wardrobe();
        singleWardrobe.addArticle("tester", Wardrobe.ClothingType.SHIRT,
                Wardrobe.ClothingColor.RED, Wardrobe.ClothingFormality.FORMAL,
                Wardrobe.ClothingTemperature.COLD);

        singleWardrobe.addArticle("tester1", Wardrobe.ClothingType.SHIRT,
                Wardrobe.ClothingColor.BLUE, Wardrobe.ClothingFormality.FORMAL,
                Wardrobe.ClothingTemperature.COLD);

        singleWardrobe.addArticle("tester2", Wardrobe.ClothingType.SHIRT,
                Wardrobe.ClothingColor.RED, Wardrobe.ClothingFormality.CASUAL,
                Wardrobe.ClothingTemperature.COLD);

        singleWardrobe.addArticle("tester3", Wardrobe.ClothingType.SHOES,
                Wardrobe.ClothingColor.RED, Wardrobe.ClothingFormality.FORMAL,
                Wardrobe.ClothingTemperature.COLD);

        ArrayList viableTops;
        viableTops = Choose.removeTopsItems(singleWardrobe.articleList, Wardrobe.ClothingFormality.FORMAL, Wardrobe.ClothingTemperature.COLD);

        int len = viableTops.size();
        assertEquals(len, 2); //Removes casual shirt and formal shoe
        assertEquals(singleWardrobe.wardrobeLength(), 4);
        assertTrue(viableTops.contains(singleWardrobe.getArticle("tester")));
        assertTrue(viableTops.contains(singleWardrobe.getArticle("tester1")));

    }

    /// Test to choose formal bottoms.
    public void test_formalBottoms() throws Exception {
        Wardrobe singleWardrobe = new Wardrobe();
        singleWardrobe.addArticle("tester", Wardrobe.ClothingType.PANTS,
                Wardrobe.ClothingColor.RED, Wardrobe.ClothingFormality.FORMAL,
                Wardrobe.ClothingTemperature.COLD);

        singleWardrobe.addArticle("tester1", Wardrobe.ClothingType.PANTS,
                Wardrobe.ClothingColor.BLUE, Wardrobe.ClothingFormality.FORMAL,
                Wardrobe.ClothingTemperature.COLD);

        singleWardrobe.addArticle("tester2", Wardrobe.ClothingType.PANTS,
                Wardrobe.ClothingColor.RED, Wardrobe.ClothingFormality.CASUAL,
                Wardrobe.ClothingTemperature.COLD);

        singleWardrobe.addArticle("tester3", Wardrobe.ClothingType.SHOES,
                Wardrobe.ClothingColor.RED, Wardrobe.ClothingFormality.FORMAL,
                Wardrobe.ClothingTemperature.COLD);

        ArrayList viableBottoms;
        viableBottoms = Choose.removeBottomsItems(singleWardrobe.articleList, Wardrobe.ClothingFormality.FORMAL, Wardrobe.ClothingTemperature.COLD);

        int len = viableBottoms.size();
        assertEquals(len, 2); //Removes casual shirt and formal shoe
        assertEquals(singleWardrobe.wardrobeLength(), 4);
        assertTrue(viableBottoms.contains(singleWardrobe.getArticle("tester")));
        assertTrue(viableBottoms.contains(singleWardrobe.getArticle("tester1")));

    }
}
