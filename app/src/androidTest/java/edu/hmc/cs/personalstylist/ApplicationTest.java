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

    // CLOTHING CLASS TESTS

    // Tests construction of an Unknown clothing object of type shoe
    public void test_unknownClothing() throws Exception {
        Clothing unknown = new Clothing("shoe");
        String message = "No recommended clothing";
        assertEquals(message, unknown.getName());
    }

    // Tests construction of a clothing object with all five inputs. Here, the strings are
    // arbitrary, but they cannot be so in the application.
    public void test_standardClothing() throws Exception {
        Clothing article = new Clothing("a", "b", "c", "d", "e");
        assertEquals("a", article.getName());
        assertEquals("b", article.getType());
        assertEquals("c", article.getColor());
        assertEquals("d", article.getFormality());
        assertEquals("e", article.getTemperature());
    }

    // Tests addition of a clothing article into an empty ArrayList of Clothing
    // and its subsequent removal
    public void test_emptyAddition() throws Exception {
        ArrayList<Clothing> wardrobe = new ArrayList<Clothing>();
        Clothing article = new Clothing("a", "b", "c", "d", "e");
        wardrobe.add(article);
        assertEquals(article, wardrobe.remove(0));
        ArrayList<Clothing> empty = new ArrayList<Clothing>();
        assertEquals(wardrobe, empty);
    }

    // Tests addition of a clothing article into a nonempty ArrayList of Clothing
    // and its subsequent removal
    public void test_nonemptyAdditionRemoval() throws Exception {
        ArrayList<Clothing> wardrobe = new ArrayList<Clothing>();
        Clothing article = new Clothing("a", "b", "c", "d", "e");
        Clothing article2 = new Clothing("z", "y", "x", "w", "v");
        wardrobe.add(article);
        wardrobe.add(article2);
        assertEquals(article, wardrobe.remove(0));
        assertEquals(article2,wardrobe.remove(0));
        ArrayList<Clothing> empty = new ArrayList<Clothing>();
        assertEquals(wardrobe, empty);
    }


    // CHOOSE CLASS TESTS
    // Much of the testing for this class was done with the actual application (i.e. by running
    // code and checking results visually). Were this a larger-scale project, we would have
    // developed mock-up Android tests to handle more testing.







}
