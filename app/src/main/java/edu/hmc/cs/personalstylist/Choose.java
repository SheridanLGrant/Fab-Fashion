package edu.hmc.cs.personalstylist;


import android.util.Log;
import android.widget.TextView;

import java.util.Iterator;
import java.util.ArrayList;
import edu.hmc.cs.personalstylist.Wardrobe;
import java.util.Random;
import edu.hmc.cs.personalstylist.Clothing;

/**
 * Created by davidconnor on 10/10/14.
 *  This class is intended to take user's preferences to return viable clothing options and
 *  a #1 choice outfit.
 */
public class Choose {


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




    public ArrayList<Clothing> wardrobe = new ArrayList<Clothing>();

    public Choose(ArrayList<Clothing> listOfClothes) {
        this.wardrobe = listOfClothes;
    }


   /**
    * Eliminates non-viable clothing from list of bottoms, returns the remaining list
    *
    * @param formalityPref, weatherPref
    * @return ArrayList of viableBottoms
    */
    public ArrayList<Clothing> viableClothing(String formalityPref, String temperaturePref) {

        ArrayList<Clothing> viableClothing = new ArrayList<Clothing>();

        // Only temperature preference, clothing need only match temperature
        if ("".equals(formalityPref)) {
            for (int i = 0; i < wardrobe.size(); i++) {
                Clothing clothingArticle = wardrobe.get(i);
                String t = clothingArticle.getTemperature();
                if (temperaturePref.equals(t)) {
                    viableClothing.add(clothingArticle);
                }
            }
        }
        // Only formality preference, clothing need only match formality
        else if ("".equals(temperaturePref)) {
            for (int i = 0; i < wardrobe.size(); i++) {
                Clothing clothingArticle = wardrobe.get(i);
                String f = clothingArticle.getFormality();
                if (formalityPref.equals(f)) {
                    viableClothing.add(clothingArticle);
                }
            }
        }
        // Both temperature and formality preference, clothing must match both
        else {
            for (int i = 0; i < wardrobe.size(); i++) {
                Clothing clothingArticle = wardrobe.get(i);
                String f = clothingArticle.getFormality();
                String t = clothingArticle.getTemperature();
                if (formalityPref.equals(f) &&
                        temperaturePref.equals(t)) {
                    viableClothing.add(clothingArticle);
                }
            }
        }

        return viableClothing;

    }

    // Returns the dictionary of bad color combinations
    public ArrayList<String> badColors() {
        ArrayList<String> badColors = new ArrayList<String>();

        badColors.add(RED + RED);
        badColors.add(YELLOW + YELLOW);
        badColors.add(GREEN + GREEN);
        badColors.add(PURPLE + PURPLE);
        badColors.add(ORANGE + ORANGE);
        badColors.add(WHITE + WHITE);
        badColors.add(PINK + PINK);
        badColors.add(BROWN + BROWN);

        badColors.add(RED + ORANGE);
        badColors.add(RED + YELLOW);
        badColors.add(RED + GREEN);
        badColors.add(RED + PINK);
        badColors.add(RED + BROWN);
        badColors.add(ORANGE + RED);
        badColors.add(YELLOW + RED);
        badColors.add(GREEN + RED);
        badColors.add(PINK + RED);
        badColors.add(BROWN + RED);

        badColors.add(YELLOW + ORANGE);
        badColors.add(YELLOW + WHITE);
        badColors.add(YELLOW + BROWN);
        badColors.add(ORANGE + YELLOW);
        badColors.add(WHITE + YELLOW);
        badColors.add(BROWN + YELLOW);

        badColors.add(GREEN + ORANGE);
        badColors.add(ORANGE + GREEN);

        badColors.add(PURPLE + ORANGE);
        badColors.add(PURPLE + PINK);
        badColors.add(ORANGE + PURPLE);
        badColors.add(PINK + PURPLE);

        badColors.add(ORANGE + PINK);
        badColors.add(PINK + ORANGE);

        badColors.add(BLACK + BROWN);
        badColors.add(BROWN + BLACK);

        badColors.add(PINK + BROWN);
        badColors.add(BROWN + PINK);

        return badColors;
    }

    // TODO: public and private methods
    // Populates the arrays of parameter types
    public void parameterArrays() {
        // Build paramter arrays
        TOPS.add(LONG_SLEEVE_SHIRT);
        TOPS.add(SHORT_SLEEVE_SHIRT);
        TOPS.add(SLEEVELESS_SHIRT);
        BOTTOMS.add(PANTS);
        BOTTOMS.add(SHORTS);
        BOTTOMS.add(SKIRT);
        SHOES.add(DRESS_SHOES);
        SHOES.add(TENNIS_SHOES);
        SHOES.add(SANDALS);

        TYPES.add(LONG_SLEEVE_SHIRT);
        TYPES.add(SHORT_SLEEVE_SHIRT);
        TYPES.add(SLEEVELESS_SHIRT);
        TYPES.add(PANTS);
        TYPES.add(SHORTS);
        TYPES.add(SKIRT);
        TYPES.add(DRESS_SHOES);
        TYPES.add(TENNIS_SHOES);
        TYPES.add(SANDALS);

        COLORS.add(RED);
        COLORS.add(YELLOW);
        COLORS.add(BLUE);
        COLORS.add(GREEN);
        COLORS.add(ORANGE);
        COLORS.add(PURPLE);
        COLORS.add(BLACK);
        COLORS.add(WHITE);
        COLORS.add(PINK);
        COLORS.add(BROWN);

        FORMALITIES.add(CASUAL);
        FORMALITIES.add(FORMAL);
        FORMALITIES.add(RECREATIONAL);

        TEMPERATURES.add(HOT);
        TEMPERATURES.add(COLD);
        TEMPERATURES.add(MILD);
    }


    // Suggests a good-looking outfit from the available clothes
    public  ArrayList<Clothing> pickOutfit(ArrayList<Clothing> suggestedTops,
                                           ArrayList<Clothing> suggestedBottoms,
                                           ArrayList<Clothing> suggestedShoes) {

        ArrayList<Clothing> outfit = new ArrayList<Clothing>();

        // Initialize clothes as unknown.
        Clothing shoe = new Clothing(UNKNOWN_SHOE);
        Clothing bottom = new Clothing(UNKNOWN_BOTTOM);
        Clothing top = new Clothing(UNKNOWN_TOP);

        // Pick a random shoe, matching shoes is too complicated and could lead to worse outfits...
        if (suggestedShoes.size() > 0) {
            Random random = new Random();
            int randomNumber = random.nextInt(suggestedShoes.size());
            shoe = suggestedShoes.get(randomNumber);
        }


        // Ensure the top and bottom aren't a bad color combination
        for (int i = 0; i < suggestedTops.size(); i++) {
            for (int j =0; j < suggestedBottoms.size(); j++) {
                String colorCombo = suggestedTops.get(i).getColor() +
                        suggestedBottoms.get(j).getColor();

                boolean goodFlag = true;
                ArrayList<String> badColors = badColors();
                for (int k = 0; k < badColors.size(); k++) {
                    if (badColors.get(k).equals(colorCombo)) {
                        goodFlag = false;
                        break;
                    }
                }

                if (goodFlag) {
                    top = suggestedTops.get(i);
                    bottom = suggestedBottoms.get(j);

                    outfit.add(top);
                    outfit.add(bottom);
                    outfit.add(shoe);

                    return outfit;
                }

            }
        }
        outfit.add(top);
        outfit.add(bottom);
        outfit.add(shoe);
        return outfit;
    }


    // Finds a good outfit based on temperature and formality preference, then based on clothing
    // type. Passes to pickOutfit to check for color-coordination and final return.
    public ArrayList<Clothing> RecommendedOutfits(ArrayList<Clothing> newWardrobe, String formPref, String tempPref) {

        // Build parameter arrays
        parameterArrays();

        ArrayList<Clothing> outfit = new ArrayList<Clothing>();

        ArrayList<Clothing> topsPossible = new ArrayList<Clothing>();
        ArrayList<Clothing> bottomsPossible = new ArrayList<Clothing>();
        ArrayList<Clothing> shoesPossible = new ArrayList<Clothing>();

        // divide clothes into tops, bottoms, shoes
        for (int i = 0; i < newWardrobe.size(); i++) {
            if (TOPS.contains(newWardrobe.get(i).getType())) {
                topsPossible.add(newWardrobe.get(i));
            } else if (BOTTOMS.contains(newWardrobe.get(i).getType())) {
                bottomsPossible.add(newWardrobe.get(i));
            } else if (SHOES.contains(newWardrobe.get(i).getType())) {
                shoesPossible.add(newWardrobe.get(i));
            }
        }

        ArrayList<Clothing> suggestedTops = new ArrayList<Clothing>();
        ArrayList<Clothing> suggestedBottoms = new ArrayList<Clothing>();
        ArrayList<Clothing> suggestedShoes = new ArrayList<Clothing>();


        // Based on user preferences, remove inappropriate clothing types
        if (RECREATIONAL.equals(formPref) && HOT.equals(tempPref)) {
            for (int i = 0; i < topsPossible.size(); i++) {
                if (SLEEVELESS_SHIRT.equals(topsPossible.get(i).getType()) ||
                        SHORT_SLEEVE_SHIRT.equals(topsPossible.get(i).getType())) {
                    suggestedTops.add(topsPossible.get(i));
                }
            }

            for (int i = 0; i < bottomsPossible.size(); i++) {
                if (SHORTS.equals(bottomsPossible.get(i).getType()) ||
                        SKIRT.equals(bottomsPossible.get(i).getType())) {
                    suggestedBottoms.add(bottomsPossible.get(i));
                }
            }

            for (int i = 0; i < shoesPossible.size(); i++) {
                if (SANDALS.equals(shoesPossible.get(i).getType()) ||
                        TENNIS_SHOES.equals(shoesPossible.get(i).getType())) {
                    suggestedShoes.add(shoesPossible.get(i));
                }
            }

            outfit = pickOutfit(suggestedTops, suggestedBottoms, suggestedShoes);

        } else if (RECREATIONAL.equals(formPref) && MILD.equals(tempPref)) {

            for (int i = 0; i < topsPossible.size(); i++) {
                if (SHORT_SLEEVE_SHIRT.equals(topsPossible.get(i).getType())) {
                    suggestedTops.add(topsPossible.get(i));
                }
            }

            for (int i = 0; i < bottomsPossible.size(); i++) {
                if (SHORTS.equals(bottomsPossible.get(i).getType())||
                        SKIRT.equals(bottomsPossible.get(i).getType())) {
                    suggestedBottoms.add(bottomsPossible.get(i));
                }
            }

            for (int i = 0; i < shoesPossible.size(); i++) {
                if (TENNIS_SHOES.equals(shoesPossible.get(i).getType())) {
                    suggestedShoes.add(shoesPossible.get(i));
                }
            }

            outfit = pickOutfit(suggestedTops, suggestedBottoms, suggestedShoes);

        } else if (RECREATIONAL.equals(formPref) && COLD.equals(tempPref)) {

            for (int i = 0; i < topsPossible.size(); i++) {
                if (LONG_SLEEVE_SHIRT.equals(topsPossible.get(i).getType())) {
                    suggestedTops.add(topsPossible.get(i));
                }
            }

            for (int i = 0; i < bottomsPossible.size(); i++) {
                if (PANTS.equals(bottomsPossible.get(i).getType())) {
                    suggestedBottoms.add(bottomsPossible.get(i));
                }
            }

            for (int i = 0; i < shoesPossible.size(); i++) {
                if (TENNIS_SHOES.equals(shoesPossible.get(i).getType())) {
                    suggestedShoes.add(shoesPossible.get(i));
                }
            }

            outfit = pickOutfit(suggestedTops, suggestedBottoms, suggestedShoes);

        } else if (CASUAL.equals(formPref) && HOT.equals(tempPref)) {

            for (int i = 0; i < topsPossible.size(); i++) {
                if (SHORT_SLEEVE_SHIRT.equals(topsPossible.get(i).getType())) {
                    suggestedTops.add(topsPossible.get(i));
                }
            }

            for (int i = 0; i < bottomsPossible.size(); i++) {
                if (SHORTS.equals(bottomsPossible.get(i).getType())) {
                    suggestedBottoms.add(bottomsPossible.get(i));
                }
            }

            for (int i = 0; i < shoesPossible.size(); i++) {
                if (TENNIS_SHOES.equals(shoesPossible.get(i).getType())) {
                    suggestedShoes.add(shoesPossible.get(i));
                }
            }

            outfit = pickOutfit(suggestedTops, suggestedBottoms, suggestedShoes);

        } else if (CASUAL.equals(formPref) && MILD.equals(tempPref)) {

            for (int i = 0; i < topsPossible.size(); i++) {
                if (SHORT_SLEEVE_SHIRT.equals(topsPossible.get(i).getType()) ||
                        LONG_SLEEVE_SHIRT.equals(topsPossible.get(i).getType())) {
                    suggestedTops.add(topsPossible.get(i));
                }
            }

            for (int i = 0; i < bottomsPossible.size(); i++) {
                if (SHORTS.equals(bottomsPossible.get(i).getType()) ||
                        PANTS.equals(bottomsPossible.get(i).getType())) {
                    suggestedBottoms.add(bottomsPossible.get(i));
                }
            }

            for (int i = 0; i < shoesPossible.size(); i++) {
                if (TENNIS_SHOES.equals(shoesPossible.get(i).getType())) {
                    suggestedShoes.add(shoesPossible.get(i));
                }
            }

            outfit = pickOutfit(suggestedTops, suggestedBottoms, suggestedShoes);

        } else if (CASUAL.equals(formPref) && COLD.equals(tempPref)) {

            for (int i = 0; i < topsPossible.size(); i++) {
                if (LONG_SLEEVE_SHIRT.equals(topsPossible.get(i).getType())) {
                    suggestedTops.add(topsPossible.get(i));
                }
            }

            for (int i = 0; i < bottomsPossible.size(); i++) {
                if (PANTS.equals(bottomsPossible.get(i).getType())) {
                    suggestedBottoms.add(bottomsPossible.get(i));
                }
            }

            for (int i = 0; i < shoesPossible.size(); i++) {
                if (TENNIS_SHOES.equals(shoesPossible.get(i).getType()) ||
                        DRESS_SHOES.equals(shoesPossible.get(i).getType())) {
                    suggestedShoes.add(shoesPossible.get(i));
                }
            }

            outfit = pickOutfit(suggestedTops, suggestedBottoms, suggestedShoes);

        } else if (FORMAL.equals(formPref)) {

            for (int i = 0; i < topsPossible.size(); i++) {
                if (LONG_SLEEVE_SHIRT.equals(topsPossible.get(i).getType())) {
                    suggestedTops.add(topsPossible.get(i));
                }
            }

            for (int i = 0; i < bottomsPossible.size(); i++) {
                if (PANTS.equals(bottomsPossible.get(i).getType()) ||
                        SKIRT.equals(bottomsPossible.get(i).getType())) {
                    suggestedBottoms.add(bottomsPossible.get(i));
                }
            }

            for (int i = 0; i < shoesPossible.size(); i++) {
                if (DRESS_SHOES.equals(shoesPossible.get(i).getType())) {
                    suggestedShoes.add(shoesPossible.get(i));
                }
            }

            outfit = pickOutfit(suggestedTops, suggestedBottoms, suggestedShoes);

        } else if (RECREATIONAL.equals(formPref)) {

            for (int i = 0; i < topsPossible.size(); i++) {
                suggestedTops.add(topsPossible.get(i));
            }

            for (int i = 0; i < bottomsPossible.size(); i++) {
                suggestedBottoms.add(bottomsPossible.get(i));
            }

            for (int i = 0; i < shoesPossible.size(); i++) {
                if (TENNIS_SHOES.equals(shoesPossible.get(i).getType())) {
                    suggestedShoes.add(shoesPossible.get(i));
                }
            }

            outfit = pickOutfit(suggestedTops, suggestedBottoms, suggestedShoes);

        } else if (CASUAL.equals(formPref)) {

            for (int i = 0; i < topsPossible.size(); i++) {
                if (LONG_SLEEVE_SHIRT.equals(topsPossible.get(i).getType()) ||
                        SHORT_SLEEVE_SHIRT.equals(topsPossible.get(i).getType())) {
                    suggestedTops.add(topsPossible.get(i));
                }
            }

            for (int i = 0; i < bottomsPossible.size(); i++) {
                if (PANTS.equals(bottomsPossible.get(i).getType()) ||
                        SHORTS.equals(bottomsPossible.get(i).getType())) {
                    suggestedBottoms.add(bottomsPossible.get(i));
                }
            }

            for (int i = 0; i < shoesPossible.size(); i++) {
                if (DRESS_SHOES.equals(shoesPossible.get(i).getType()) ||
                        TENNIS_SHOES.equals(shoesPossible.get(i).getType())) {
                    suggestedShoes.add(shoesPossible.get(i));
                }
            }

            outfit = pickOutfit(suggestedTops, suggestedBottoms, suggestedShoes);

        } else if (HOT.equals(tempPref)) {

            for (int i = 0; i < topsPossible.size(); i++) {
                if ((SLEEVELESS_SHIRT.equals(topsPossible.get(i).getType()) ||
                        SHORT_SLEEVE_SHIRT.equals(topsPossible.get(i).getType())) &&
                        (RECREATIONAL.equals(topsPossible.get(i).getFormality()) ||
                                CASUAL.equals(topsPossible.get(i).getFormality()))) {
                    suggestedTops.add(topsPossible.get(i));
                }
            }

            for (int i = 0; i < bottomsPossible.size(); i++) {
                if (RECREATIONAL.equals(bottomsPossible.get(i).getFormality()) ||
                                CASUAL.equals(bottomsPossible.get(i).getFormality())) {
                    suggestedBottoms.add(bottomsPossible.get(i));
                }
            }

            for (int i = 0; i < shoesPossible.size(); i++) {
                if ((SANDALS.equals(shoesPossible.get(i).getType()) ||
                        TENNIS_SHOES.equals(shoesPossible.get(i).getType())) &&
                        (RECREATIONAL.equals(topsPossible.get(i).getFormality()) ||
                                CASUAL.equals(topsPossible.get(i).getFormality()))) {
                    suggestedShoes.add(shoesPossible.get(i));
                }
            }

            outfit = pickOutfit(suggestedTops, suggestedBottoms, suggestedShoes);

        } else if (MILD.equals(tempPref)) {

            for (int i = 0; i < topsPossible.size(); i++) {
                if ((LONG_SLEEVE_SHIRT.equals(topsPossible.get(i).getType()) ||
                        SHORT_SLEEVE_SHIRT.equals(topsPossible.get(i).getType())) &&
                        (RECREATIONAL.equals(topsPossible.get(i).getFormality()) ||
                                CASUAL.equals(topsPossible.get(i).getFormality()))) {
                    suggestedTops.add(topsPossible.get(i));
                }
            }

            for (int i = 0; i < bottomsPossible.size(); i++) {
                if (RECREATIONAL.equals(bottomsPossible.get(i).getFormality()) ||
                        CASUAL.equals(bottomsPossible.get(i).getFormality())) {
                    suggestedBottoms.add(bottomsPossible.get(i));
                }
            }

            for (int i = 0; i < shoesPossible.size(); i++) {
                if (TENNIS_SHOES.equals(shoesPossible.get(i).getType()) &&
                        (RECREATIONAL.equals(topsPossible.get(i).getFormality()) ||
                                CASUAL.equals(topsPossible.get(i).getFormality()))) {
                    suggestedShoes.add(shoesPossible.get(i));
                }
            }

            outfit = pickOutfit(suggestedTops, suggestedBottoms, suggestedShoes);

        } else if (COLD.equals(tempPref)) {

            for (int i = 0; i < topsPossible.size(); i++) {
                if (LONG_SLEEVE_SHIRT.equals(topsPossible.get(i).getType()) &&
                        (RECREATIONAL.equals(topsPossible.get(i).getFormality()) ||
                                CASUAL.equals(topsPossible.get(i).getFormality()))) {
                    suggestedTops.add(topsPossible.get(i));
                }
            }

            for (int i = 0; i < bottomsPossible.size(); i++) {
                if (PANTS.equals(bottomsPossible.get(i).getType()) &&
                        (RECREATIONAL.equals(bottomsPossible.get(i).getFormality()) ||
                        CASUAL.equals(bottomsPossible.get(i).getFormality()))) {
                    suggestedBottoms.add(bottomsPossible.get(i));
                }
            }

            for (int i = 0; i < shoesPossible.size(); i++) {
                if (TENNIS_SHOES.equals(shoesPossible.get(i).getType()) &&
                        (RECREATIONAL.equals(shoesPossible.get(i).getFormality()) ||
                                CASUAL.equals(shoesPossible.get(i).getFormality()))) {
                    suggestedShoes.add(shoesPossible.get(i));
                }
            }

            outfit = pickOutfit(suggestedTops, suggestedBottoms, suggestedShoes);

        }

        return outfit;
    }



    // Called by judge in viewOutfit, checks the current center outfit for color and type
    // coordination.
    public boolean judgeOutfit(Clothing top, Clothing bottom, Clothing shoe) {

        String topType = top.getType();
        String bottomType = bottom.getType();
        String shoeType = shoe.getType();

        String topColor = top.getColor();
        String bottomColor = bottom.getColor();
        String shoeColor = shoe.getColor();

        // Checks for bad type combinations
        if ((shoeType.equals(SANDALS) && bottomType.equals(PANTS)) ||
                (topType.equals(SLEEVELESS_SHIRT) && bottomType.equals(PANTS)) ||
                (topType.equals(LONG_SLEEVE_SHIRT) && shoeType.equals(SANDALS)) ||
                (shoeType.equals(DRESS_SHOES) && bottomType.equals(SHORTS)) ||
                (shoeType.equals(TENNIS_SHOES) && bottomType.equals(SKIRT)) ||
                (shoeType.equals(DRESS_SHOES) && topType.equals(SLEEVELESS_SHIRT)) ||
                (shoeType.equals(DRESS_SHOES) && topType.equals(SHORT_SLEEVE_SHIRT)) ||
                (bottomType.equals(PANTS) && topType.equals(SLEEVELESS_SHIRT))) {

            return false;
        }

        String colorCombo = topColor + bottomColor;
        ArrayList<String> badColors = badColors();

        // Checks for bad color combinations
        for (int i = 0; i < badColors.size(); i++) {
            if (badColors.get(i).equals(colorCombo)) {
                return false;
            }
        }

        return true;
    }












        /**
         * Returns ArrayList of Items in Top Choice Outfit (1 top, 1 bottom)
         *
         * @param viableClothes
         * @return ArrayList outfit
         */

    public ArrayList<Clothing> topOutfit(ArrayList<Clothing> viableClothes) {
        ArrayList<Clothing> outfit = new ArrayList<Clothing>();
//        Random generator = new Random();
//
//        int i = generator.nextInt(viableTops.size());
//        Wardrobe.Clothing topTop;
//        topTop = (Wardrobe.Clothing) viableTops.get(i);
//        outfit.add(topTop);
//
//        int j = generator.nextInt(viableBottoms.size());
//        Wardrobe.Clothing topBottom;
//        topBottom = (Wardrobe.Clothing) viableBottoms.get(i);
//        outfit.add(topBottom);
//
//        return outfit;


        boolean topFlag = true;
        boolean bottomFlag = true;
        boolean shoesFlag = true;

        for (int i = 0; i < viableClothes.size(); i++) {
            if (viableClothes.get(i).getType() == "shirt" && topFlag) {
                outfit.add(viableClothes.get(i));
                topFlag = false;
            }
            else if (viableClothes.get(i).getType() == "pants" && bottomFlag) {
                outfit.add(viableClothes.get(i));
                bottomFlag = false;
            }
            if (viableClothes.get(i).getType() == "shoes" && shoesFlag) {
                outfit.add(viableClothes.get(i));
                shoesFlag = false;
            }
        }
        return outfit;


    }
}