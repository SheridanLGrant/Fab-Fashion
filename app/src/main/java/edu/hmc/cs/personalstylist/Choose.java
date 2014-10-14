package edu.hmc.cs.personalstylist;


import java.util.Iterator;
import java.util.ArrayList;
import edu.hmc.cs.personalstylist.Wardrobe;
import java.util.Random;

/**
 * Created by davidconnor on 10/10/14.
 *  This class is intended to take user's preferences to return viable clothing options and
 *  a #1 choice outfit.
 */
public class Choose {


        /**
         * Eliminates non-viable clothing from list of bottoms, returns the remaining list
         *
         * @param articleList, formalityPref, weatherPref
         * @return ArrayList of viableBottoms
         */
    public ArrayList removeTopsItems(ArrayList articleList, Wardrobe.ClothingFormality formalityPref, Wardrobe.ClothingTemperature weatherPref) {
        ArrayList viableTops = null;
        for (int i = 0; i < articleList.size(); ++i) {
            Wardrobe.Clothing clothingArticle = (Wardrobe.Clothing) articleList.get(i);
            if (clothingArticle.formality == formalityPref &&
                    clothingArticle.temperature == weatherPref) {
                viableTops.add(clothingArticle);
            }
        }
            return viableTops;

    }

        /**
         * Eliminates non-viable clothing from list of bottoms, returns remaining list
         *
         * @param  articleList, formalityPref, weatherPref
         * @return ArrayList of viableBottoms
         */


    public ArrayList removeBottomsItems(ArrayList articleList, Wardrobe.ClothingFormality formalityPref, Wardrobe.ClothingTemperature weatherPref) {
        ArrayList viableBottoms = null;
        for (int i = 0; i < articleList.size(); ++i) {
            Wardrobe.Clothing clothingArticle = (Wardrobe.Clothing) articleList.get(i);
            if (clothingArticle.formality == formalityPref &&
                    clothingArticle.temperature == weatherPref) {
                viableBottoms.add(clothingArticle);
            }
        }
            return viableBottoms;

    }


        /**
         * Returns ArrayList of Items in Top Choice Outfit (1 top, 1 bottom)
         *
         * @param viableTops, viableBottoms
         * @return ArrayList outfit
         */

    public ArrayList topOutfit(ArrayList viableTops, ArrayList viableBottoms) {
        ArrayList outfit = null;
        Random generator = new Random();

        int i = generator.nextInt(viableTops.size());
        Wardrobe.Clothing topTop;
        topTop = (Wardrobe.Clothing) viableTops.get(i);
        outfit.add(topTop);

        int j = generator.nextInt(viableBottoms.size());
        Wardrobe.Clothing topBottom;
        topBottom = (Wardrobe.Clothing) viableBottoms.get(i);
        outfit.add(topBottom);

        return outfit;

    }
}