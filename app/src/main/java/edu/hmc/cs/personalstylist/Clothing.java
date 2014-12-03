package edu.hmc.cs.personalstylist;

/**
 * Created by Sheridan on 11/3/2014.
 */

/**
 * The clothing class is the essential element of the Wardrobe that represents each article of
 * clothing in the Wardrobe.
 */
public class Clothing {
    // We do not enumerate the possible values for the data members, as the user will have no
    // choice but to select one of a few pre-defined options through the interface.

    public String name = "";
    public String type = "";
    public String color = "";
    public String formality = "";
    public String temperature = "";

    public Clothing(String givenName, String givenType, String givenColor,
                    String givenFormality, String givenTemperature) {

         this.name = givenName;
         this.type = givenType;
         this.color = givenColor;
         this.formality = givenFormality;
         this.temperature = givenTemperature;
    }

    // An "unknown" article, used when the outfit suggestion algorithm does not return an actual
    // clothing article
    public Clothing(String givenType) {
        this.type = givenType;
        this.name = "No recommended clothing";
    }

    // The obvious getter functions
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

}
