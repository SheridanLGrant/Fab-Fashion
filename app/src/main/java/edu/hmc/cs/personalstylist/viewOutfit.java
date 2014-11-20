package edu.hmc.cs.personalstylist;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import edu.hmc.cs.personalstylist.Choose;

/**
 * Created by davidconnor on 11/6/14.
 */
public class viewOutfit extends Activity implements View.OnClickListener {
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

    ArrayList<Clothing> wardrobe = new ArrayList<Clothing>();
    Context context;
    String file = "wardrobeData";

    // For image buttons
    RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Populate ArrayLists
        TOPS.add(LONG_SLEEVE_SHIRT);
        TOPS.add(SHORT_SLEEVE_SHIRT);
        TOPS.add(SLEEVELESS_SHIRT);
        BOTTOMS.add(PANTS);
        BOTTOMS.add(SHORTS);
        BOTTOMS.add(SKIRT);
        SHOES.add(DRESS_SHOES);
        SHOES.add(TENNIS_SHOES);
        SHOES.add(SANDALS);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_outfits);

        Intent outfitChoose = getIntent();




        // Read wardrobe data
        Gson gson = new Gson();
        String temp="";
        Type clothingList = new TypeToken<ArrayList<Clothing>>() {}.getType();

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


        if ("".equals(temp) || "[]".equals(temp)) {
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


        String formPref = outfitChoose.getStringExtra(CLOTHING_FORMALITY);
        String tempPref = outfitChoose.getStringExtra(CLOTHING_TEMPERATURE);

        Choose outfitChoice = new Choose(wardrobe);
        ArrayList<Clothing> newWardrobe = outfitChoice.viableClothing(formPref, tempPref);



        Clothing currentArticle;
        for (int i = 0; i < newWardrobe.size(); i++) {
            currentArticle = newWardrobe.get(i);
            String type = currentArticle.getType();

            ImageButton button = createImageButton(currentArticle);

            if (TOPS.contains(type)) {
                LinearLayout view = (LinearLayout) findViewById(R.id.chooseTopLayout);
                view.addView(button);
            }
            else if (BOTTOMS.contains(type)) {
                LinearLayout view = (LinearLayout) findViewById(R.id.chooseBottomLayout);
                view.addView(button);
            }
            else if (SHOES.contains(type)) {
                LinearLayout view = (LinearLayout) findViewById(R.id.chooseShoeLayout);
                view.addView(button);
            }
        }


        DisplaySuggestion(outfitChoice.RecommendedOutfits(newWardrobe, formPref, tempPref));

        initializeScrollViews();

    }

    private void initializeScrollViews() {
        MyScrollView topScroller = (MyScrollView) findViewById(R.id.topScrollerChoose);
        MyScrollView bottomScroller = (MyScrollView) findViewById(R.id.bottomScrollerChoose);
        final MyScrollView shoeScroller = (MyScrollView) findViewById(R.id.shoeScrollerChoose);

        LinearLayout topLayout = (LinearLayout) findViewById(R.id.chooseTopLayout);
        LinearLayout bottomLayout = (LinearLayout) findViewById(R.id.chooseBottomLayout);
        LinearLayout shoeLayout = (LinearLayout) findViewById(R.id.chooseShoeLayout);

        initializeOneScrollView(topScroller, topLayout);
        initializeOneScrollView(bottomScroller, bottomLayout);
        initializeOneScrollView(shoeScroller, shoeLayout);

        Display mDisplay = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        mDisplay.getSize(size);
        int width = size.x;

        topLayout.setPadding(width, 0, width, 0);
        bottomLayout.setPadding(width, 0, width, 0);
        shoeLayout.setPadding(width, 0, width, 0);
    }


    public void initializeOneScrollView(final MyScrollView myView, final LinearLayout myLayout) {
        myView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    myView.startScrollerTask();
                }
                return false;
            }
        });
        myView.setOnScrollStoppedListener(new MyScrollView.OnScrollStoppedListener() {
            @Override
            public void onScrollStopped() {
                myView.center(myLayout);
            }
        });
    }



    // Behold the mass of conditional code required to sort clothing items into the current 99
    // possible combinations of type and color
    private ImageButton createImageButton(Clothing currentArticle) {
        ImageButton button = new ImageButton(this);
        String type = currentArticle.getType();
        String color = currentArticle.getColor();

        if (LONG_SLEEVE_SHIRT.equals(type)) {
            if (RED.equals(color)) {
                button.setImageResource(R.drawable.longsleeveshirt_red);
            }
            else if (YELLOW.equals(color)) {
                button.setImageResource(R.drawable.longsleeveshirt_yellow);
            }
            else if (BLUE.equals(color)) {
                button.setImageResource(R.drawable.longsleeveshirt_blue);
            }
            else if (GREEN.equals(color)) {
                button.setImageResource(R.drawable.longsleeveshirt_green);
            }
            else if (ORANGE.equals(color)) {
                button.setImageResource(R.drawable.longsleeveshirt_orange);
            }
            else if (PURPLE.equals(color)) {
                button.setImageResource(R.drawable.longsleeveshirt_purple);
            }
            else if (BLACK.equals(color)) {
                button.setImageResource(R.drawable.longsleeveshirt_black);
            }
            else if (WHITE.equals(color)) {
                button.setImageResource(R.drawable.longsleeveshirt_white);
            }
            else if (PINK.equals(color)) {
                button.setImageResource(R.drawable.longsleeveshirt_pink);
            }
            else { // BROWN
                button.setImageResource(R.drawable.longsleeveshirt_brown);
            }
        }
        else if (SHORT_SLEEVE_SHIRT.equals(type)) {
            if (RED.equals(color)) {
                button.setImageResource(R.drawable.shortsleeveshirt_red);
            }
            else if (YELLOW.equals(color)) {
                button.setImageResource(R.drawable.shortsleeveshirt_yellow);
            }
            else if (BLUE.equals(color)) {
                button.setImageResource(R.drawable.shortsleeveshirt_blue);
            }
            else if (GREEN.equals(color)) {
                button.setImageResource(R.drawable.shortsleeveshirt_green);
            }
            else if (ORANGE.equals(color)) {
                button.setImageResource(R.drawable.shortsleeveshirt_orange);
            }
            else if (PURPLE.equals(color)) {
                button.setImageResource(R.drawable.shortsleeveshirt_purple);
            }
            else if (BLACK.equals(color)) {
                button.setImageResource(R.drawable.shortsleeveshirt_black);
            }
            else if (WHITE.equals(color)) {
                button.setImageResource(R.drawable.shortsleeveshirt_white);
            }
            else if (PINK.equals(color)) {
                button.setImageResource(R.drawable.shortsleeveshirt_pink);
            }
            else { // BROWN
                button.setImageResource(R.drawable.shortsleeveshirt_brown);
            }
        }
        else if (SLEEVELESS_SHIRT.equals(type)) {
            if (RED.equals(color)) {
                button.setImageResource(R.drawable.sleevelessshirt_red);
            }
            else if (YELLOW.equals(color)) {
                button.setImageResource(R.drawable.sleevelessshirt_yellow);
            }
            else if (BLUE.equals(color)) {
                button.setImageResource(R.drawable.sleevelessshirt_blue);
            }
            else if (GREEN.equals(color)) {
                button.setImageResource(R.drawable.sleevelessshirt_green);
            }
            else if (ORANGE.equals(color)) {
                button.setImageResource(R.drawable.sleevelessshirt_orange);
            }
            else if (PURPLE.equals(color)) {
                button.setImageResource(R.drawable.sleevelessshirt_purple);
            }
            else if (BLACK.equals(color)) {
                button.setImageResource(R.drawable.sleevelessshirt_black);
            }
            else if (WHITE.equals(color)) {
                button.setImageResource(R.drawable.sleevelessshirt_white);
            }
            else if (PINK.equals(color)) {
                button.setImageResource(R.drawable.sleevelessshirt_pink);
            }
            else { // BROWN
                button.setImageResource(R.drawable.sleevelessshirt_brown);
            }
        }
        else if (PANTS.equals(type)) {
            if (RED.equals(color)) {
                button.setImageResource(R.drawable.longpants_red);
            }
            else if (YELLOW.equals(color)) {
                button.setImageResource(R.drawable.longpants_yellow);
            }
            else if (BLUE.equals(color)) {
                button.setImageResource(R.drawable.longpants_blue);
            }
            else if (GREEN.equals(color)) {
                button.setImageResource(R.drawable.longpants_green);
            }
            else if (ORANGE.equals(color)) {
                button.setImageResource(R.drawable.longpants_orange);
            }
            else if (PURPLE.equals(color)) {
                button.setImageResource(R.drawable.longpants_purple);
            }
            else if (BLACK.equals(color)) {
                button.setImageResource(R.drawable.longpants_black);
            }
            else if (WHITE.equals(color)) {
                button.setImageResource(R.drawable.longpants_white);
            }
            else if (PINK.equals(color)) {
                button.setImageResource(R.drawable.longpants_pink);
            }
            else { // BROWN
                button.setImageResource(R.drawable.longpants_brown);
            }
        }
        else if (SHORTS.equals(type)) {
            if (RED.equals(color)) {
                button.setImageResource(R.drawable.shortpants_red);
            }
            else if (YELLOW.equals(color)) {
                button.setImageResource(R.drawable.shortpants_yellow);
            }
            else if (BLUE.equals(color)) {
                button.setImageResource(R.drawable.shortpants_blue);
            }
            else if (GREEN.equals(color)) {
                button.setImageResource(R.drawable.shortpants_green);
            }
            else if (ORANGE.equals(color)) {
                button.setImageResource(R.drawable.shortpants_orange);
            }
            else if (PURPLE.equals(color)) {
                button.setImageResource(R.drawable.shortpants_purple);
            }
            else if (BLACK.equals(color)) {
                button.setImageResource(R.drawable.shortpants_black);
            }
            else if (WHITE.equals(color)) {
                button.setImageResource(R.drawable.shortpants_white);
            }
            else if (PINK.equals(color)) {
                button.setImageResource(R.drawable.shortpants_pink);
            }
            else { // BROWN
                button.setImageResource(R.drawable.shortpants_brown);
            }
        }
        else if (SKIRT.equals(type)) {
            if (RED.equals(color)) {
                button.setImageResource(R.drawable.skirtpants_red);
            }
            else if (YELLOW.equals(color)) {
                button.setImageResource(R.drawable.skirtpants_yellow);
            }
            else if (BLUE.equals(color)) {
                button.setImageResource(R.drawable.skirtpants_blue);
            }
            else if (GREEN.equals(color)) {
                button.setImageResource(R.drawable.skirtpants_green);
            }
            else if (ORANGE.equals(color)) {
                button.setImageResource(R.drawable.skirtpants_orange);
            }
            else if (PURPLE.equals(color)) {
                button.setImageResource(R.drawable.skirtpants_purple);
            }
            else if (BLACK.equals(color)) {
                button.setImageResource(R.drawable.skirtpants_black);
            }
            else if (WHITE.equals(color)) {
                button.setImageResource(R.drawable.skirtpants_white);
            }
            else if (PINK.equals(color)) {
                button.setImageResource(R.drawable.skirtpants_pink);
            }
            else { // BROWN
                button.setImageResource(R.drawable.skirtpants_brown);
            }
        }
        else if (DRESS_SHOES.equals(type)) {
            if (RED.equals(color)) {
                button.setImageResource(R.drawable.dressshoes_red);
            }
            else if (YELLOW.equals(color)) {
                button.setImageResource(R.drawable.dressshoes_yellow);
            }
            else if (BLUE.equals(color)) {
                button.setImageResource(R.drawable.dressshoes_blue);
            }
            else if (GREEN.equals(color)) {
                button.setImageResource(R.drawable.dressshoes_green);
            }
            else if (ORANGE.equals(color)) {
                button.setImageResource(R.drawable.dressshoes_orange);
            }
            else if (PURPLE.equals(color)) {
                button.setImageResource(R.drawable.dressshoes_purple);
            }
            else if (BLACK.equals(color)) {
                button.setImageResource(R.drawable.dressshoes_black);
            }
            else if (WHITE.equals(color)) {
                button.setImageResource(R.drawable.dressshoes_white);
            }
            else if (PINK.equals(color)) {
                button.setImageResource(R.drawable.dressshoes_pink);
            }
            else { // BROWN
                button.setImageResource(R.drawable.dressshoes_brown);
            }
        }
        else if (TENNIS_SHOES.equals(type)) {
            if (RED.equals(color)) {
                button.setImageResource(R.drawable.tennisshoes_red);
            }
            else if (YELLOW.equals(color)) {
                button.setImageResource(R.drawable.tennisshoes_yellow);
            }
            else if (BLUE.equals(color)) {
                button.setImageResource(R.drawable.tennisshoes_blue);
            }
            else if (GREEN.equals(color)) {
                button.setImageResource(R.drawable.tennisshoes_green);
            }
            else if (ORANGE.equals(color)) {
                button.setImageResource(R.drawable.tennisshoes_orange);
            }
            else if (PURPLE.equals(color)) {
                button.setImageResource(R.drawable.tennisshoes_purple);
            }
            else if (BLACK.equals(color)) {
                button.setImageResource(R.drawable.tennisshoes_black);
            }
            else if (WHITE.equals(color)) {
                button.setImageResource(R.drawable.tennisshoes_white);
            }
            else if (PINK.equals(color)) {
                button.setImageResource(R.drawable.tennisshoes_pink);
            }
            else { // BROWN
                button.setImageResource(R.drawable.tennisshoes_brown);
            }
        }
        else { // SANDALS
            if (RED.equals(color)) {
                button.setImageResource(R.drawable.sandalshoes_red);
            }
            else if (YELLOW.equals(color)) {
                button.setImageResource(R.drawable.sandalshoes_yellow);
            }
            else if (BLUE.equals(color)) {
                button.setImageResource(R.drawable.sandalshoes_blue);
            }
            else if (GREEN.equals(color)) {
                button.setImageResource(R.drawable.sandalshoes_green);
            }
            else if (ORANGE.equals(color)) {
                button.setImageResource(R.drawable.sandalshoes_orange);
            }
            else if (PURPLE.equals(color)) {
                button.setImageResource(R.drawable.sandalshoes_purple);
            }
            else if (BLACK.equals(color)) {
                button.setImageResource(R.drawable.sandalshoes_black);
            }
            else if (WHITE.equals(color)) {
                button.setImageResource(R.drawable.sandalshoes_white);
            }
            else if (PINK.equals(color)) {
                button.setImageResource(R.drawable.sandalshoes_pink);
            }
            else { // BROWN
                button.setImageResource(R.drawable.sandalshoes_brown);
            }
        }

        button.setLayoutParams(params);
        button.setOnClickListener(this);
        button.setBackgroundColor(Color.TRANSPARENT);
        button.setTag(currentArticle.getName());

        return button;
    }

    private void DisplaySuggestion(ArrayList<Clothing> suggestion) {

        ImageButton topButton = createImageButton(suggestion.get(0));
        ImageButton bottomButton = createImageButton(suggestion.get(1));
        ImageButton shoeButton = createImageButton(suggestion.get(2));

        LinearLayout view = (LinearLayout) findViewById(R.id.suggestions);
        view.addView(topButton);
        view.addView(bottomButton);
        view.addView(shoeButton);
    }

    @Override
    public void onClick(View v) {
        ImageButton b = (ImageButton) v;
        PopupMenu popup = new PopupMenu(this, b);
        popup.getMenu().add("Name: " + b.getTag());

        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.article_options, popup.getMenu());
        popup.show();
    }
}
