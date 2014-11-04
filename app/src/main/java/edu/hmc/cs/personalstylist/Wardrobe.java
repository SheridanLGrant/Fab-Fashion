package edu.hmc.cs.personalstylist;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Max on 10/1/2014.
 *
 * This class is intended to hold a collection of clothing items. Allows clothing items to be
 * viewed, added, or removed.
 */
public class Wardrobe {

    // The ArrayList that actually contains the articles of clothing
    ArrayList<Clothing> articleList = new ArrayList<Clothing>();

    // Set static variables to refer to the different types of clothing conveniently
    public enum ClothingType {
        HAT, DRESS, SHIRT, PANTS, SHOES, LAYER, ACCESSORY, UNKNOWN_TYPE
    }

    // Set static variables to refer to different colors of clothing
    public enum ClothingColor {
        RED, YELLOW, GREEN, BLUE, PURPLE, GRAY, BLACK, PINK, ORANGE, WHITE, BROWN, UNKNOWN_COLOR
    }

    // Set static variables to refer to the formality of the clothing
    public enum ClothingFormality {
        CASUAL, BUSINESS, FORMAL, BEACH, UNKNOWN_FORMALITY
    }

    // Set static variables to refer to the ideal temperature for wearing the clothing
    public enum ClothingTemperature {
        HOT, COLD, MILD, SUNNY, RAINY, UNKNOWN_TEMPERATURE
    }

    /**
     * Add an article of clothing to the wardrobe. Returns true if the article was
     * successfully added.
     *
     * @param name The name of the article
     * @param type The type of article
     * @param color The color of the article
     * @param formality The level of formality of the article
     * @param temperature The appropriate temperature for the article
     * @return
     */
    public boolean addArticle(String name, ClothingType type, ClothingColor color,
                           ClothingFormality formality, ClothingTemperature temperature) {
        Clothing newArticle = new Clothing();
        newArticle.name = name;
        newArticle.type = type;
        newArticle.color = color;
        newArticle.formality = formality;
        newArticle.temperature = temperature;

        return articleList.add(newArticle);
    }

    /**
     * Remove an article of clothing. Returns true if the article was successfully removed.
     *
     * @param article The article of clothing to remove
     * @return
     */
    public boolean removeArticle(Clothing article) {
        return articleList.remove(article);
    }

    /**
     * Remove an article of clothing. Returns true if the article was successfully removed.
     *
     * @param name The name of the article of clothing to remove
     * @return
     */
    public boolean removeArticle(String name) {
        Clothing articleToRemove = getArticle(name);
        if (name != null) {
            return removeArticle(articleToRemove);
        }
        return false;
    }

    public int wardrobeLength() {
        return articleList.size();
    }

    /**
     * Returns the first article of clothing with the specified name. If no such
     * article exists, returns null.
     *
     * @param name The name of the article we want to find
     * @return
     */
    public Clothing getArticle(String name) {
        for (int i = 0; i < articleList.size(); ++i) {
            if (articleList.get(i).name == name) {
                return articleList.get(i);
            }
        }
        return null;
    }


    /**
     * The clothing class is the essential element of the Wardrobe that represents each article of
     * clothing in the Wardrobe. This is a private that behaves like a C++ struct, without
     * any additional functionality. For this reason, we access its data members directly rather
     * than working through getters and setters, a practice recommended by the Sun Java Coding
     * Guidelines.
     */
    public static class Clothing {
        public String name = "";
        public ClothingType type = ClothingType.UNKNOWN_TYPE;
        public ClothingColor color = ClothingColor.UNKNOWN_COLOR;
        public ClothingFormality formality = ClothingFormality.UNKNOWN_FORMALITY;
        public ClothingTemperature temperature = ClothingTemperature.UNKNOWN_TEMPERATURE;

        /**
         * Returns a clothing article's name
         *
         * @return
         */
        public String toString() {
            return name;
        }
    }
}
