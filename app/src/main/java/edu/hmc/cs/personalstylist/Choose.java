package edu.hmc.cs.personalstylist;


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

    public ArrayList<Clothing> wardrobe;

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
        for (int i = 0; i < wardrobe.size(); ++i) {
            Clothing clothingArticle = wardrobe.get(i);
            if (clothingArticle.getFormality() == formalityPref &&
                    clothingArticle.getTemperature() == temperaturePref) {
                viableClothing.add(clothingArticle);
            }
        }
            return viableClothing;

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