package edu.hmc.cs.personalstylist;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import edu.hmc.cs.personalstylist.Clothing;

public class MainActivity extends Activity implements OnClickListener, PopupMenu.OnMenuItemClickListener {
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
    String wardrobeString;
    Context context;
    String file = "wardrobeData";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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
            wardrobeString = temp;
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
            wardrobeString = gson.toJson(wardrobe, clothingList);
            try {
                FileOutputStream fOut = openFileOutput(file, context.MODE_PRIVATE);
                fOut.write(wardrobeString.getBytes());
                fOut.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            wardrobe = gson.fromJson(wardrobeString, clothingList);
            Clothing first = wardrobe.get(wardrobe.size()-1);

        }

        Clothing currentArticle;
        for (int i = 0; i < wardrobe.size(); i++) {
            currentArticle = wardrobe.get(i);
            String name = currentArticle.getName();
            String type = currentArticle.getType();
            String color = currentArticle.getColor();
            String formality = currentArticle.getFormality();
            String temperature = currentArticle.getTemperature();

            if ("shirt".equals(type)) {
                LinearLayout view = (LinearLayout) findViewById(R.id.topLayout);
                Button button = new Button(this);
                button.setText(currentArticle.getName());
                button.setOnClickListener(this);
                view.addView(button);
            }
            else if ("pants".equals(type)) {
                LinearLayout view = (LinearLayout) findViewById(R.id.bottomLayout);
                Button button = new Button(this);
                button.setText(currentArticle.getName());
                button.setOnClickListener(this);
                view.addView(button);
            }
            else if ("shoes".equals(type)) {
                LinearLayout view = (LinearLayout) findViewById(R.id.shoeLayout);
                Button button = new Button(this);
                button.setText(currentArticle.getName());
                button.setOnClickListener(this);
                view.addView(button);
            }
        }






    }


//    @Override
//    public boolean onMenuItemClick(MenuItem menuItem) {
//
//        String clicked = (String) menuItem.getTitle();
//        if (clicked.equals("shirt") || clicked.equals("pants") || clicked.equals("shoes")) {
//            TextView view = (TextView) findViewById(R.id.selected_type);
//            view.setText(clicked);
//        } else if (clicked.equals("red") || clicked.equals("white") || clicked.equals("blue")) {
//            TextView view = (TextView) findViewById(R.id.selected_color);
//            view.setText(clicked);
//        } else if (clicked.equals("wedding") || clicked.equals("day at the office") || clicked.equals("eating ice cream alone")) {
//            TextView view = (TextView) findViewById(R.id.selected_formality);
//            view.setText(clicked);
//        } else {
//            TextView view = (TextView) findViewById(R.id.selected_temperature);
//            view.setText(clicked);
//        }
//
//
//        return true;
//
//    }


//    public void showPopUp(View v) {
//        PopupMenu popup = new PopupMenu(this, v);
//        popup.setOnMenuItemClickListener(this);
//        MenuInflater inflater = popup.getMenuInflater();
//        inflater.inflate(R.menu.formality, popup.getMenu());
//        popup.show();
//    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        else if (id == R.id.action_enter_article) {
            Intent enterArticle = new Intent(this, EnterArticle.class);
            startActivity(enterArticle);
        } else if (id == R.id.action_choose_outfit) {
            Intent chooseOutfit = new Intent(this, ChooseOutfit.class);
            startActivity(chooseOutfit);
        }
        return super.onOptionsItemSelected(item);
    }

    public void chooseOutfit(View view) {
    }


    // popup.getMenu().add(groupId, itemId, order, title);
    @Override
    public void onClick(View v) {
        Button b = (Button) v;
        PopupMenu popup = new PopupMenu(this, b);
        popup.getMenu().add("Name: " + b.getText());

        // get data
        CharSequence type = "Type: ";
        View parent = (View) b.getParent();
        if (parent == findViewById(R.id.topLayout)) {
            popup.getMenu().add(type + "top");
        } else if (parent == findViewById(R.id.bottomLayout)) {
            popup.getMenu().add(type + "bottom");
        } else if (parent == findViewById(R.id.shoeLayout)) {
            popup.getMenu().add(type + "shoes");
        }

        popup.getMenu().add("Delete: " + b.getText());



        popup.setOnMenuItemClickListener(this);
        MenuInflater inflater = popup.getMenuInflater();
//        View parent = (View) v.getParent();
//        int parentID = parent.getId();
        inflater.inflate(R.menu.article_options, popup.getMenu());
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

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        String clicked = (String) menuItem.getTitle();
        boolean removeFlag = false;
        int deletion = 0;

        if (!clicked.startsWith("Del")) {
            return true;
        } else {
            for (int i = 0; i < wardrobe.size(); i++) {
                if (clicked.equals(("Delete: " + wardrobe.get(i).getName()))) {
                    deletion = i;
                    removeFlag = true;
                }
            }
            if (removeFlag) {
                wardrobe.remove(deletion);
                Gson gson = new Gson();
                wardrobeString = gson.toJson(wardrobe);
                try {
                    FileOutputStream fOut = openFileOutput(file, context.MODE_PRIVATE);
                    fOut.write(wardrobeString.getBytes());
                    fOut.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }


        }


        return true;
    }
}
