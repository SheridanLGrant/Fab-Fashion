package edu.hmc.cs.personalstylist;

/**
 * Created by Sheridan on 11/3/2014.
 */

/**
 * The clothing class is the essential element of the Wardrobe that represents each article of
 * clothing in the Wardrobe. This is a private that behaves like a C++ struct, without
 * any additional functionality. For this reason, we access its data members directly rather
 * than working through getters and setters, a practice recommended by the Sun Java Coding
 * Guidelines.
 */
public class Clothing {
    // Set static variables to refer to the different types of clothing conveniently
//    public enum ClothingType {
//        HAT, DRESS, SHIRT, PANTS, SHOES, LAYER, ACCESSORY, UNKNOWN_TYPE
//    }
//
//    // Set static variables to refer to different colors of clothing
//    public enum ClothingColor {
//        RED, YELLOW, GREEN, BLUE, PURPLE, GRAY, BLACK, PINK, ORANGE, WHITE, BROWN, UNKNOWN_COLOR
//    }
//
//    // Set static variables to refer to the formality of the clothing
//    public enum ClothingFormality {
//        CASUAL, BUSINESS, FORMAL, BEACH, UNKNOWN_FORMALITY
//    }
//
//    // Set static variables to refer to the ideal temperature for wearing the clothing
//    public enum ClothingTemperature {
//        HOT, COLD, MILD, SUNNY, RAINY, UNKNOWN_TEMPERATURE
//    }

    public String name = "";
    public String type = "";
    public String color = "";
    public String formality = "";
    public String temperature = "";

    public Clothing(String givenName, String givenType, String givenColor, String givenFormality, String givenTemperature) {
         this.name = givenName;
         this.type = givenType;
         this.color = givenColor;
         this.formality = givenFormality;
         this.temperature = givenTemperature;
    }

    public String getType() {
        return this.type;
    }

    public String getName() {
        return this.name;
    }

    public String getColor() {
        return this.color;
    }

    public String getFormality() {
        return this.formality;
    }

    public String getTemperature() {
        return this.temperature;
    }

    /**
     * Returns a clothing article's name
     *
     * @return
     */
    public String toString() {
        return name;
    }
}
