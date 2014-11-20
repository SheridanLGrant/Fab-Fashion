package edu.hmc.cs.personalstylist;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
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

        TextView test = (TextView) findViewById(R.id.view_outfits_greeting);
        if (newWardrobe.size() == 5) {
            test.setText("five!");
        }


        Clothing currentArticle;
        for (int i = 0; i < newWardrobe.size(); i++) {
            currentArticle = newWardrobe.get(i);
            String type = currentArticle.getType();
            if (TOPS.contains(type)) {
                LinearLayout view = (LinearLayout) findViewById(R.id.chooseTopLayout);
                Button button = new Button(this);
                button.setText(currentArticle.getName());
                view.addView(button);
            }
            else if (BOTTOMS.contains(type)) {
                LinearLayout view = (LinearLayout) findViewById(R.id.chooseBottomLayout);
                Button button = new Button(this);
                button.setText(currentArticle.getName());
                view.addView(button);
            }
            else if (SHOES.contains(type)) {
                LinearLayout view = (LinearLayout) findViewById(R.id.chooseShoeLayout);
                Button button = new Button(this);
                button.setText(currentArticle.getName());
                view.addView(button);
            }
        }


        DisplaySuggestion(outfitChoice.RecommendedOutfits(newWardrobe, formPref, tempPref));

    }



    // TODO: change to appropriate icons
    private ImageButton createImageButton(Clothing currentArticle) {
        ImageButton button = new ImageButton(this);
        button.setImageResource(R.drawable.jersey);
        button.setLayoutParams(params);
        button.setBackgroundColor(Color.TRANSPARENT);
        button.setTag(currentArticle.getName());

        return button;
    }

    private void DisplaySuggestion(ArrayList<Clothing> suggestion) {
        ImageButton topSuggestion = (ImageButton) findViewById(R.id.topSuggestion);
        ImageButton bottomSuggestion = (ImageButton) findViewById(R.id.bottomSuggestion);
        ImageButton shoeSuggestion = (ImageButton) findViewById(R.id.shoeSuggestion);


        topSuggestion.setImageResource(R.drawable.jersey);
        topSuggestion.setOnClickListener(this);
//        topSuggestion.setLayoutParams(params);
        topSuggestion.setBackgroundColor(Color.TRANSPARENT);
        topSuggestion.setTag(suggestion.get(0).getName());

        bottomSuggestion.setImageResource(R.drawable.jersey);
        bottomSuggestion.setOnClickListener(this);
//        bottomSuggestion.setLayoutParams(params);
        bottomSuggestion.setBackgroundColor(Color.TRANSPARENT);
        bottomSuggestion.setTag(suggestion.get(1).getName());

        shoeSuggestion.setImageResource(R.drawable.jersey);
        shoeSuggestion.setOnClickListener(this);
//        shoeSuggestion.setLayoutParams(params);
        shoeSuggestion.setBackgroundColor(Color.TRANSPARENT);
        shoeSuggestion.setTag(suggestion.get(2).getName());

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
