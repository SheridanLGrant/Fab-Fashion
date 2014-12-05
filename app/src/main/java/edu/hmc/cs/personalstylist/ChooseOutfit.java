package edu.hmc.cs.personalstylist;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Sheridan on 11/5/2014.
 */
public class ChooseOutfit extends Activity implements PopupMenu.OnMenuItemClickListener {

    // Variables to pass to the next activity
    private final static String CLOTHING_FORMALITY = "edu.hmc.cs.personalstylist.clothingFormality";
    private final static String CLOTHING_TEMPERATURE = "edu.hmc.cs.personalstylist.clothingTemperature";

    // Clothing Formalities
    private final static String CASUAL = "Casual";
    private final static String FORMAL = "Formal";
    private final static String RECREATIONAL = "Recreational";
    private final static ArrayList<String> FORMALITIES = new ArrayList<String>();

    // Temperature Formalities
    private final static String HOT = "Hot";
    private final static String COLD = "Cold";
    private final static String MILD = "Mild";
    private final static ArrayList<String> TEMPERATURES = new ArrayList<String>();

    // On Activity start, do this
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // Populate ArrayLists
        FORMALITIES.add(CASUAL);
        FORMALITIES.add(FORMAL);
        FORMALITIES.add(RECREATIONAL);

        TEMPERATURES.add(HOT);
        TEMPERATURES.add(COLD);
        TEMPERATURES.add(MILD);

        setContentView(R.layout.activity_choose_outfit);

    }

    // No activity bar options, does nothing, needed as class extends activity
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    // Creates Formality popup menu
    public void showPopUpFormality(View v){
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(this);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.formality, popup.getMenu());
        popup.show();
    }

    // Creates Temperature popup menu
    public void showPopUpTemperature(View v){
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(this);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.temperature, popup.getMenu());
        popup.show();
    }


    // Called if the user does not enter a valid name or clothing type
    private void noInput() {
        Context context = getApplicationContext();
        CharSequence message = "No preferences entered";
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, message, duration);
        toast.show();

        Intent retry = new Intent(this, ChooseOutfit.class);
        startActivity(retry);
        this.finish();
    }


    // When the user presses the enter preferences button, send the next activity their preferences
    // and ensure they have selected something
    public void moveToOutfitView(View v){
        Intent outfitChoose = new Intent(this, ViewOutfit.class);
        TextView tempText = (TextView) findViewById(R.id.choose_selected_temperature);
        String tempPref = (String) tempText.getText();
        TextView formText = (TextView) findViewById(R.id.choose_selected_formality);
        String formPref = (String) formText.getText();

        // Ensure the user has selected something
        if ("".equals(formPref) && "".equals(tempPref)) {
            noInput();
            return;
        }

        outfitChoose.putExtra(CLOTHING_FORMALITY, formPref);
        outfitChoose.putExtra(CLOTHING_TEMPERATURE, tempPref);
        startActivity(outfitChoose);
        this.finish();
    }

    // Handles drop-down menu clicks straightforwardly
    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {

        String clicked = (String) menuItem.getTitle();

        if (FORMALITIES.contains(clicked)) {
            TextView view = (TextView) findViewById(R.id.choose_selected_formality);
            view.setText(clicked);
        } else {
            TextView view = (TextView) findViewById(R.id.choose_selected_temperature);
            view.setText(clicked);
        }

        return true;
    }
}
