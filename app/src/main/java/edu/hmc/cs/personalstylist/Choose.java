package edu.hmc.cs.personalstylist;


import java.util.Iterator;
import java.util.ArrayList;
import Wardrobe;

/**
 * Created by davidconnor on 10/10/14.
 */
public class Choose {
}


    // The ArrayList that contains the articles of clothing
    ArrayList<ViableClothes> clothingList = new ArrayList<ViableClothes>();



    /**
     * Eliminates non-viable clothing from list of viable tops
     *
     * @param name The name of the article of clothing to remove
     * @return
     */
    public Clothing removeItems(list articleList, string formalityPref, string weatherPref)  {
        for (int i = 0; i < articleList.size(); ++i) {
            if (articleList.get(i).formality != formalityPref) {
                articleList.remove(i);
                --i;
            else if (articleList.get(i).temperature != weatherPref) {
                articleList.remove(i);
                --i;
            }
        return null;

 }
        /**
         * Eliminates non-viable clothing from a  list of viable bottoms
         *
         * @param name The name of the article of clothing to remove
         * @return
         */