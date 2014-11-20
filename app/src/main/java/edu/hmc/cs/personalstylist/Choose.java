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
    public final static String BUSINESS = "Business";
    public final static String FORMAL = "Formal";
    public final static String BEACH = "Beach";
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

        if ("".equals(formalityPref)) {
            for (int i = 0; i < wardrobe.size(); i++) {
                Clothing clothingArticle = wardrobe.get(i);
                String t = clothingArticle.getTemperature();
                if (temperaturePref.equals(t)) {
                    viableClothing.add(clothingArticle);
                }
            }
        } else if ("".equals(temperaturePref)) {
            for (int i = 0; i < wardrobe.size(); i++) {
                Clothing clothingArticle = wardrobe.get(i);
                String f = clothingArticle.getFormality();
                if (formalityPref.equals(f)) {
                    viableClothing.add(clothingArticle);
                }
            }
        } else {
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


    public ArrayList<Clothing> RecommendedOutfits(ArrayList<Clothing> newWardrobe, String formPref, String tempPref) {

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
        FORMALITIES.add(BUSINESS);
        FORMALITIES.add(BEACH);

        TEMPERATURES.add(HOT);
        TEMPERATURES.add(COLD);
        TEMPERATURES.add(MILD);

        ArrayList<Clothing> outfit = new ArrayList<Clothing>();

        ArrayList<Clothing> topsPossible = new ArrayList<Clothing>();
        ArrayList<Clothing> bottomsPossible = new ArrayList<Clothing>();
        ArrayList<Clothing> shoesPossible = new ArrayList<Clothing>();

        Clothing top = new Clothing(UNKNOWN_TOP);
        Clothing bottom = new Clothing(UNKNOWN_BOTTOM);
        Clothing shoe = new Clothing(UNKNOWN_SHOE);

        for (int i = 0; i < newWardrobe.size(); i++) {
            if (TOPS.contains(newWardrobe.get(i).getType())) {
                topsPossible.add(newWardrobe.get(i));
            } else if (BOTTOMS.contains(newWardrobe.get(i).getType())) {
                bottomsPossible.add(newWardrobe.get(i));
            } else if (SHOES.contains(newWardrobe.get(i).getType())) {
                shoesPossible.add(newWardrobe.get(i));
            }
        }


        if (CASUAL.equals(formPref) && HOT.equals(tempPref)) {

            for (int i1 = 0; i1 < topsPossible.size(); i1++) {
                if (SLEEVELESS_SHIRT.equals(topsPossible.get(i1).getType())) {
                    top = topsPossible.get(i1);
                    break;
                }
            }

            for (int i2 = 0; i2 < bottomsPossible.size(); i2++) {
                if (SHORTS.equals(bottomsPossible.get(i2).getType())) {
                    bottom = bottomsPossible.get(i2);
                    break;
                }
            }

            for (int i3 = 0; i3 < shoesPossible.size(); i3++) {
                if (SANDALS.equals(shoesPossible.get(i3).getType())) {
                    shoe = shoesPossible.get(i3);
                    break;
                }
            }
        } else if (CASUAL.equals(formPref) && MILD.equals(tempPref)) {

            for (int i = 0; i < topsPossible.size(); i++) {
                if (SHORT_SLEEVE_SHIRT.equals(topsPossible.get(i).getType())) {
                    top = topsPossible.get(i);
                    break;
                }
            }

            for (int i = 0; i < bottomsPossible.size(); i++) {
                if (SHORTS.equals(bottomsPossible.get(i).getType())) {
                    bottom = bottomsPossible.get(i);
                    break;
                }
            }

            for (int i = 0; i < shoesPossible.size(); i++) {
                if (TENNIS_SHOES.equals(shoesPossible.get(i).getType())) {
                    shoe = shoesPossible.get(i);
                    break;
                }
            }
        } else if (BUSINESS.equals(formPref) || FORMAL.equals(formPref)) {

            for (int i = 0; i < topsPossible.size(); i++) {
                if (LONG_SLEEVE_SHIRT.equals(topsPossible.get(i).getType())) {
                    top = topsPossible.get(i);
                    break;
                }
            }

            for (int i = 0; i < bottomsPossible.size(); i++) {
                if (PANTS.equals(bottomsPossible.get(i).getType())) {
                    bottom = bottomsPossible.get(i);
                    break;
                }
            }

            for (int i = 0; i < shoesPossible.size(); i++) {
                if (DRESS_SHOES.equals(shoesPossible.get(i).getType())) {
                    shoe = shoesPossible.get(i);
                    break;
                }
            }
        }

        outfit.add(top);
        outfit.add(bottom);
        outfit.add(shoe);



        return outfit;
    }













        /**
         * Eliminates non-viable clothing from list of bottoms, returns remaining list
         *
         * @param  articleList, formalityPref, weatherPref
         * @return ArrayList of viableBottoms
         */


//    public static ArrayList removeBottomsItems(ArrayList articleList, Wardrobe.ClothingFormality formalityPref, Wardrobe.ClothingTemperature weatherPref) {
//        ArrayList viableBottoms = null;
//        for (int i = 0; i < articleList.size(); ++i) {
//            Wardrobe.Clothing clothingArticle = (Wardrobe.Clothing) articleList.get(i);
//            if (clothingArticle.type == Wardrobe.ClothingType.PANTS && clothingArticle.formality == formalityPref &&
//                    clothingArticle.temperature == weatherPref) {
//                viableBottoms.add(clothingArticle);
//            }
//        }
//            return viableBottoms;
//
//    }


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