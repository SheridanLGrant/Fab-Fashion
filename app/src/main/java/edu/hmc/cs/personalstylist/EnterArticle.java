package edu.hmc.cs.personalstylist;
// Used code from http://www.androidhive.info/2013/07/android-expandable-list-view-tutorial/

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.BaseExpandableListAdapter;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Created by Sheridan on 10/12/2014.
 */
public class EnterArticle extends Activity implements PopupMenu.OnMenuItemClickListener {
    public final static String ARTICLE_NAME = "edu.hmc.cs.personalstylist.articleName";
    public final static String CLOTHING_TYPE = "edu.hmc.cs.personalstylist.clothingType";
    public final static String CLOTHING_COLOR = "edu.hmc.cs.personalstylist.clothingColor";
    public final static String CLOTHING_FORMALITY = "edu.hmc.cs.personalstylist.clothingFormality";
    public final static String CLOTHING_TEMPERATURE = "edu.hmc.cs.personalstylist.clothingTemperature";

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
        FORMALITIES.add(BUSINESS);
        FORMALITIES.add(BEACH);

        TEMPERATURES.add(HOT);
        TEMPERATURES.add(COLD);
        TEMPERATURES.add(MILD);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    public void showPopUpType(View v){
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(this);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.type, popup.getMenu());
        popup.show();
    }

    public void showPopUpColor(View v){
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(this);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.color, popup.getMenu());
        popup.show();
    }

    public void showPopUpFormality(View v){
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(this);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.formality, popup.getMenu());
        popup.show();
    }

    public void showPopUpTemperature(View v){
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(this);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.temperature, popup.getMenu());
        popup.show();
    }

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
        }

        Intent returnToMain = new Intent(this, MainActivity.class);
        returnToMain.putExtra(ARTICLE_NAME, articleName);
        returnToMain.putExtra(CLOTHING_TYPE, clothingType);
        returnToMain.putExtra(CLOTHING_COLOR, clothingColor);


        // Read wardrobe data
        Gson gson = new Gson();
        readWardrobeData(gson);

        Clothing article = new Clothing(articleName, clothingType, clothingColor, clothingFormality, clothingTemperature);

//        for (int i = 0; i < wardrobe.size(); i++) {
//            if (wardrobe.get(i).getName().equals(articleName)) {
//                sameName();
//            }
//        }

        wardrobe.add(article);
        String wardrobeName = gson.toJson(wardrobe, clothingList);

//      ONLY STORE DATA ONPAUSE(). STORE PATHS TO IMAGES ON DEVICE.
        storeWardrobeData(wardrobeName);

        startActivity(returnToMain);
        this.finish();
    }

    // Called if the user does not enter a valid name or clothing type
    private void quitWithoutSaving() {
        Context context = getApplicationContext();
        CharSequence message = "Article not saved: please enter a valid name and clothing type";
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, message, duration);
        toast.show();

        Intent retryEntry = new Intent(this, EnterArticle.class);
        startActivity(retryEntry);
    }


    // Called if the user enters an article name that is already present
    private void sameName() {
        Context context = getApplicationContext();
        CharSequence message = "Article name already in wardrobe";
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, message, duration);
        toast.show();

        Intent retryEntry = new Intent(this, EnterArticle.class);
        startActivity(retryEntry);
    }


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
