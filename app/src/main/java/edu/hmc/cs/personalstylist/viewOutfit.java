package edu.hmc.cs.personalstylist;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
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
public class viewOutfit extends Activity {
    public final static String NAME_MESSAGE = "edu.hmc.cs.personalstylist.nameMessage";
    public final static String WARDROBE_MESSAGE = "edu.hmc.cs.personalstylist.wardrobeMessage";
    public final static String ARTICLE_MESSAGE = "edu.hmc.cs.personalstylist.articleMessage";
    public final static String ARTICLE_NAME = "edu.hmc.cs.personalstylist.articleName";
    public final static String CLOTHING_TYPE = "edu.hmc.cs.personalstylist.clothingType";
    public final static String CLOTHING_COLOR = "edu.hmc.cs.personalstylist.clothingColor";
    public final static String CLOTHING_FORMALITY = "edu.hmc.cs.personalstylist.clothingFormality";
    public final static String CLOTHING_TEMPERATURE = "edu.hmc.cs.personalstylist.clothingTemperature";
    //    Wardrobe wardrobe;
    ArrayList<Clothing> wardrobe = new ArrayList<Clothing>();
    Context context;
    String file = "wardrobeData";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_outfits);

        Intent outfitChoose = getIntent();




        // Read wardrobe data
        Gson gson = new Gson();
        String temp="";
        TextView testRead = (TextView) findViewById(R.id.testViewOutfit);
        Type clothingList = new TypeToken<ArrayList<Clothing>>() {}.getType();

        try {
            FileInputStream fIn = openFileInput(file);
            int c;
            while( (c = fIn.read()) != -1) {
                temp = temp + Character.toString((char)c);
            }
//            wardrobe = gson.fromJson(temp, Wardrobe.class);
//            TextView testRead = (TextView) findViewById(R.id.testRead);
//            if (wardrobe.wardrobeLength() < 1) {
//                testRead.setText("worked");
//            } else {
//                testRead.setText("did not work");
//            }
//            Toast.makeText(getBaseContext(), "wardrobe found", Toast.LENGTH_LONG).show();
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
        testRead.setText(wardrobe.get(0).getTemperature());
//        if (newWardrobe.size() == 0) {
//
//        }


        Clothing currentArticle;
        for (int i = 0; i < newWardrobe.size(); i++) {
            currentArticle = newWardrobe.get(i);
            String type = currentArticle.getType();
            if ("shirt".equals(type)) {
                LinearLayout view = (LinearLayout) findViewById(R.id.chooseTopLayout);
                Button button = new Button(this);
                button.setText(currentArticle.getName());
//                button.setOnClickListener(new View.OnClickListener() {
//                    public void onClick(View v) {
//                        PopupMenu popup = new PopupMenu(this, v);
//                        popup.setOnMenuItemClickListener(this);
//                        MenuInflater inflater = popup.getMenuInflater();
//                        inflater.inflate(R.menu.formality, popup.getMenu());
//                        popup.show();
//                    }
//                });
                view.addView(button);
            }
            else if ("pants".equals(type)) {
                LinearLayout view = (LinearLayout) findViewById(R.id.chooseBottomLayout);
                Button button = new Button(this);
                button.setText(currentArticle.getName());
                view.addView(button);
            }
            else if ("shoes".equals(type)) {
                LinearLayout view = (LinearLayout) findViewById(R.id.chooseShoeLayout);
                Button button = new Button(this);
                button.setText(currentArticle.getName());
                view.addView(button);
            }
        }

    }

}
