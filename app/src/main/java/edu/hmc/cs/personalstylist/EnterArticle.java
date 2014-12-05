package edu.hmc.cs.personalstylist;
// Used code from http://www.androidhive.info/2013/07/android-expandable-list-view-tutorial/

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import android.view.View;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * This class handles entering a new item of clothing.
 *
 * Displays the clothing entry screen and saves the user's clothing parameters.
 *
 * Created by Sheridan on 10/12/2014.
 */
public class EnterArticle extends Activity implements PopupMenu.OnMenuItemClickListener {

    // Clothing Types
    private final static String LONG_SLEEVE_SHIRT = "Long-sleeve shirt";
    private final static String SHORT_SLEEVE_SHIRT = "Short-sleeve shirt";
    private final static String SLEEVELESS_SHIRT = "Sleeveless shirt";
    private final static String PANTS = "Pants";
    private final static String SHORTS = "Shorts";
    private final static String SKIRT = "Skirt";
    private final static String DRESS_SHOES = "Dress shoes";
    private final static String TENNIS_SHOES = "Tennis shoes";
    private final static String SANDALS = "Sandals";
    private final static ArrayList<String> TYPES = new ArrayList<String>();

    // Clothing Colors
    private final static String RED = "Red";
    private final static String BLUE = "Blue";
    private final static String YELLOW = "Yellow";
    private final static String GREEN = "Green";
    private final static String PURPLE = "Purple";
    private final static String ORANGE = "Orange";
    private final static String BLACK = "Black";
    private final static String WHITE = "White";
    private final static String PINK = "Pink";
    private final static String BROWN = "Brown";
    private final static ArrayList<String> COLORS = new ArrayList<String>();

    // Clothing Formalities
    private final static String CASUAL = "Casual";
    private final static String FORMAL = "Formal";
    private final static String RECREATIONAL = "Recreational";
    private final static ArrayList<String> FORMALITIES = new ArrayList<String>();

    // Clothing Temperatures
    private final static String HOT = "Hot";
    private final static String COLD = "Cold";
    private final static String MILD = "Mild";
    private final static ArrayList<String> TEMPERATURES = new ArrayList<String>();

    // Clothing data members
    String articleName;
    String clothingType;
    String clothingColor;
    String clothingFormality;
    String clothingTemperature;
    ArrayList<Clothing> wardrobe = new ArrayList<Clothing>();
    Context context;
    String file;

    Type clothingList = new TypeToken<ArrayList<Clothing>>() {}.getType();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_enter_article);

        // Populate the arrays of parameters
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

    // Necessary for an Activity object
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    /**
     * Inflate the Type drop-down menu
     *
     * @param v View where the menu should appear.
     */
    public void showPopUpType(View v){
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(this);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.type, popup.getMenu());
        popup.show();
    }

    /**
     * Inflate the Color drop-down menu
     *
     * @param v View where the menu should appear.
     */
    public void showPopUpColor(View v){
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(this);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.color, popup.getMenu());
        popup.show();
    }

    /**
     * Inflate the Formality drop-down menu
     *
     * @param v View where the menu should appear.
     */
    public void showPopUpFormality(View v){
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(this);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.formality, popup.getMenu());
        popup.show();
    }

    /**
     * Inflate the Temperature drop-down menu
     *
     * @param v View where the menu should appear.
     */
    public void showPopUpTemperature(View v){
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(this);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.temperature, popup.getMenu());
        popup.show();
    }

    /**
     * Saves the new article of clothing to the wardrobe.
     *
     * This method saves the new article of clothing and the information entered by the user. It
     * then stores the information in memory so MainActivity may access it.
     *
     * Ends the current activity and any other background activities, and returns to MainActivity.
     *
     * @param view
     * @throws IOException
     */
    public void enterItem(View view) throws IOException {

        EditText clothingName = (EditText) findViewById(R.id.clothingName);
        articleName = clothingName.getText().toString();


        TextView type = (TextView) findViewById(R.id.selected_type);
        clothingType = (String) type.getText();

        TextView color = (TextView) findViewById(R.id.selected_color);
        clothingColor = (String) color.getText();

        TextView formality = (TextView) findViewById(R.id.selected_formality);
        clothingFormality = (String) formality.getText();

        TextView temperature = (TextView) findViewById(R.id.selected_temperature);
        clothingTemperature = (String) temperature.getText();

        // Check that the user entered valid data
        if ("".equals(articleName) || "".equals(clothingTemperature)) {
            quitWithoutSaving();
            return;
        }

        // Return to MainActivity and end other activities
        Intent returnToMain = new Intent(this, MainActivity.class);
        returnToMain.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Read wardrobe data
        Gson gson = new Gson();
        readWardrobeData(gson);

        Clothing article = new Clothing(articleName, clothingType, clothingColor, clothingFormality, clothingTemperature);

        // Ensure that the clothing name hasn't already been used
        for (int i = 0; i < wardrobe.size(); i++) {
            if (wardrobe.get(i).getName().equals(articleName)) {
                sameName();
                return;
            }
        }

        wardrobe.add(article);
        String wardrobeName = gson.toJson(wardrobe, clothingList);

//      ONLY STORE DATA ONPAUSE(). STORE PATHS TO IMAGES ON DEVICE.
        storeWardrobeData(wardrobeName);

        startActivity(returnToMain);
        this.finish();
    }

    /**
     * Restarts the activity without saving the new clothing item.
     *
     * Called if the user does not enter correct parameters. Returns to the article entry screen so
     * the user may take another attempt to enter the new article.
     */
    private void quitWithoutSaving() {
        Context context = getApplicationContext();
        CharSequence message = "Article not saved: please enter a valid name and clothing type";
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, message, duration);
        toast.show();

        Intent retryEntry = new Intent(this, EnterArticle.class);
        startActivity(retryEntry);
        this.finish();
    }


    /**
     * Restarts the activity without saving the new clothing item.
     *
     * Called if the user enters an article with a repeated name. Returns to the article entry
     * screen so the user may take another attempt to enter the new article, and warns the user.
     */
    private void sameName() {
        Context context = getApplicationContext();
        CharSequence message = "Article name already in wardrobe";
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, message, duration);
        toast.show();

        Intent retryEntry = new Intent(this, EnterArticle.class);
        startActivity(retryEntry);
        this.finish();
    }


    /**
     * Reads the wardrobe data
     *
     * @param gson The GSon that stores the wardrobe data.
     */
    private void readWardrobeData(Gson gson) {
        file = "wardrobeData";
        String temp="";

        try {
            FileInputStream fIn = openFileInput(file);
            int c;
            while( (c = fIn.read()) != -1) {
                temp = temp + Character.toString((char)c);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if ("".equals(temp)) {
            String wardrobeName = gson.toJson(wardrobe, clothingList);
            try {
                FileOutputStream fOut = openFileOutput(file, context.MODE_PRIVATE);
                fOut.write(wardrobeName.getBytes());
                fOut.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            wardrobe = gson.fromJson(temp, clothingList);
        }
    }


    /**
     * Stores the wardrobe in memory.
     *
     * @param wardrobeName The wardrobe to be saved.
     */
    private void storeWardrobeData(String wardrobeName) {
        file = "wardrobeData";
        try {
            FileOutputStream fOut = openFileOutput(file, context.MODE_PRIVATE);
            fOut.write(wardrobeName.getBytes());
            fOut.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // Shows selected parameter next to buttons
    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {

        String clicked = (String) menuItem.getTitle();
        if (TYPES.contains(clicked)) {
            TextView view = (TextView) findViewById(R.id.selected_type);
            view.setText(clicked);
        } else if (COLORS.contains(clicked)) {
            TextView view = (TextView) findViewById(R.id.selected_color);
            view.setText(clicked);
        } else if (FORMALITIES.contains(clicked)) {
            TextView view = (TextView) findViewById(R.id.selected_formality);
            view.setText(clicked);
        } else {
            TextView view = (TextView) findViewById(R.id.selected_temperature);
            view.setText(clicked);
        }

        return true;
    }
}
