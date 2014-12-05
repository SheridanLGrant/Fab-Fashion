package edu.hmc.cs.personalstylist;

import android.test.InstrumentationTestCase;
import java.util.ArrayList;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends InstrumentationTestCase {



    public final static String UNKNOWN_TOP = "Unknown top";
    public final static String UNKNOWN_BOTTOM = "Unknown bottom";
    public final static String UNKNOWN_SHOE = "Unknown shoe";
    public final static String LONG_SLEEVE_SHIRT = "Long-sleeve shirt";
    public final static String SHORT_SLEEVE_SHIRT = "Short-sleeve shirt";
    public final static String SLEEVELESS_SHIRT = "Sleeveless shirt";
    public final static String PANTS = "Pants";
    public final static String SHORTS = "Shorts";
    public final static String SKIRT = "Skirt";
    public final static String DRESS_SHOES = "Dress shoes";
    public final static String TENNIS_SHOES = "Tennis shoes";
    public final static String SANDALS = "Sandals";
    public final static ArrayList<String> TYPES = new ArrayList<String>();
    public final static ArrayList<String> TOPS = new ArrayList<String>();
    public final static ArrayList<String> BOTTOMS = new ArrayList<String>();
    public final static ArrayList<String> SHOES = new ArrayList<String>();

    public final static String RED = "Red";
    public final static String BLUE = "Blue";
    public final static String YELLOW = "Yellow";
    public final static String GREEN = "Green";
    public final static String PURPLE = "Purple";
    public final static String ORANGE = "Orange";
    public final static String BLACK = "Black";
    public final static String WHITE = "White";
    public final static String PINK = "Pink";
    public final static String BROWN = "Brown";
    public final static ArrayList<String> COLORS = new ArrayList<String>();

    public final static String CASUAL = "Casual";
    public final static String RECREATIONAL = "Recreational";
    public final static String FORMAL = "Formal";
    public final static ArrayList<String> FORMALITIES = new ArrayList<String>();

    public final static String HOT = "Hot";
    public final static String COLD = "Cold";
    public final static String MILD = "Mild";
    public final static ArrayList<String> TEMPERATURES = new ArrayList<String>();

    ArrayList<Clothing> testWardrobe = new ArrayList<Clothing>();

    // CLOTHING CLASS TESTS

    // Set up the test wardrobe
    public void populateWardrobe() {
        Clothing teeBlueRecHot = new Clothing("teeBlueRecHot", SHORT_SLEEVE_SHIRT,
                BLUE, RECREATIONAL, HOT);
        Clothing longRedCasualCold = new Clothing("longRedCasualCold", LONG_SLEEVE_SHIRT, RED,
                CASUAL, COLD);
        Clothing pantsBlackFormalMild = new Clothing("pantsBlackFormalMild", PANTS, BLACK, FORMAL,
                MILD);
        Clothing skirtGreenCasualHot = new Clothing("skirtGreenCasualHot", SKIRT, GREEN, CASUAL,
                HOT);
        Clothing tennisOrangeRecHot = new Clothing("tennisOrangeRecHot", TENNIS_SHOES, ORANGE,
                RECREATIONAL, HOT);
        Clothing tennisBlackCasualMild = new Clothing("tennisBlackCasualMild", TENNIS_SHOES, BLACK,
                CASUAL, MILD);

        testWardrobe.add(teeBlueRecHot);
        testWardrobe.add(longRedCasualCold);
        testWardrobe.add(pantsBlackFormalMild);
        testWardrobe.add(skirtGreenCasualHot);
        testWardrobe.add(tennisOrangeRecHot);
        testWardrobe.add(tennisBlackCasualMild);
    }

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

    // Tests the viableClothing function
    public void test_viableClothing() throws Exception {

        populateWardrobe();
        Choose choose = new Choose(testWardrobe);
        ArrayList<Clothing> returnedHot = choose.viableClothing("", HOT);
        assertEquals(returnedHot.size(), 3);
        assertEquals(returnedHot.get(2).getName(), "tennisOrangeRecHot");


        ArrayList<Clothing> returnedRec = choose.viableClothing(RECREATIONAL, "");
        assertEquals(returnedRec.size(), 2);
        assertEquals(returnedRec.get(0).getName(), "teeBlueRecHot");

        ArrayList<Clothing> returnedCasualMild = choose.viableClothing(CASUAL, MILD);
        assertEquals(returnedCasualMild.size(), 1);
        assertEquals(returnedCasualMild.get(0).getName(), "tennisBlackCasualMild");


    }

    // Tests pickOutfit
    public void test_pickOutfit() throws Exception {

        populateWardrobe();
        Choose choose = new Choose(testWardrobe);

        ArrayList<Clothing> tops = new ArrayList<Clothing>();
        ArrayList<Clothing> bottoms = new ArrayList<Clothing>();
        ArrayList<Clothing> shoes = new ArrayList<Clothing>();

        tops.add(testWardrobe.get(0));
        bottoms.add(testWardrobe.get(4));
        shoes.add(testWardrobe.get(5));

        ArrayList<Clothing> recommendations = choose.pickOutfit(tops, bottoms, shoes);
        assertEquals(recommendations.get(0), tops.get(0));
        assertEquals(recommendations.get(1), bottoms.get(0));
        assertEquals(recommendations.get(2), shoes.get(0));

        tops.remove(0);
        tops.add(testWardrobe.get(1));

        // Should fail to return a top/bottom combo as red/green is a bad color combo
        ArrayList<Clothing> recommendations2 = choose.pickOutfit(tops, bottoms, shoes);
        assertEquals(recommendations2.get(2), shoes.get(0));
        assertEquals(recommendations2.get(1).getName(), "No recommended clothing");
        assertEquals(recommendations2.get(0).getName(), "No recommended clothing");

    }

    // Tests RecommendedOutfits. DOES NOT COVER EVER SINGLE CONDITIONAL IN THE FUNCTION. To do so
    // would require probably 1000 lines of code; we chose to finish off the design and interface
    // of the app nicely rather than spending all night doing this completely. Again, these
    // functions were all tested via the actual application...
    public void test_RecommendedOutfits() throws Exception {

        populateWardrobe();
        Choose choose = new Choose(testWardrobe);

        // These parameters should return the blue tee/green skirt combo as it's an okay color
        // combination, and a randomly selected tennis shoe.
        ArrayList<Clothing> recommended = choose.RecommendedOutfits(testWardrobe, RECREATIONAL,
                HOT);
        assertEquals(recommended.get(0).getName(), "teeBlueRecHot");
        assertEquals(recommended.get(1).getName(), "skirtGreenCasualHot");
        assertEquals(recommended.get(2).getType(), TENNIS_SHOES);

        ArrayList<Clothing> recommended2 = choose.RecommendedOutfits(testWardrobe, CASUAL,
                COLD);
        assertEquals(recommended2.get(0).getName(), "longRedCasualCold");
        assertEquals(recommended2.get(1).getName(), "pantsBlackFormalMild");
        assertEquals(recommended2.get(2).getType(), TENNIS_SHOES);

        // Remove the pants. Now there are no appropriate pants, so the algorithm only
        // recommends shoes as there is no shirt/pants combo.
        testWardrobe.remove(2);
        assertEquals(testWardrobe.size(), 5);
        ArrayList<Clothing> recommended3 = choose.RecommendedOutfits(testWardrobe, CASUAL,
                COLD);
        assertEquals(recommended3.get(0).getName(), "No recommended clothing");
        assertEquals(recommended3.get(1).getName(), "No recommended clothing");
        assertEquals(recommended3.get(2).getType(), TENNIS_SHOES);
    }

    // Tests judgeOutfit. Again, we can't cover every case, but if you look at the function
    // you can see fairly easily that all the cases are of the exact same general format.
    public void test_judgeOutfit() throws Exception {

        populateWardrobe();
        Choose choose = new Choose(testWardrobe);

        // No offensive combinations of type or color here
        assertTrue(choose.judgeOutfit(testWardrobe.get(0), testWardrobe.get(3),
                testWardrobe.get(4)));

        // Red and green--not a good idea!
        assertFalse(choose.judgeOutfit(testWardrobe.get(1), testWardrobe.get(3),
                testWardrobe.get(4)));

        // tee shirt and dress shoes?!?! No thanks.
        Clothing dressy = new Clothing("dressy", DRESS_SHOES, BLACK, FORMAL, MILD);
        assertFalse(choose.judgeOutfit(testWardrobe.get(1), testWardrobe.get(3),
                dressy));
    }




}
