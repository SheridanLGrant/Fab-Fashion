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
    public final static String CLOTHING_FORMALITY = "edu.hmc.cs.personalstylist.clothingFormality";
    public final static String CLOTHING_TEMPERATURE = "edu.hmc.cs.personalstylist.clothingTemperature";

    public final static String CASUAL = "Casual";
    public final static String BUSINESS = "Business";
    public final static String FORMAL = "Formal";
    public final static String BEACH = "Beach";
    public final static ArrayList<String> FORMALITIES = new ArrayList<String>();

    public final static String HOT = "Hot";
    public final static String COLD = "Cold";
    public final static String MILD = "Mild";
    public final static ArrayList<String> TEMPERATURES = new ArrayList<String>();

    String clothingFormality;
    String clothingTemperature;

    Context context;
    String file;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // Populate ArrayLists
        FORMALITIES.add(CASUAL);
        FORMALITIES.add(FORMAL);
        FORMALITIES.add(BUSINESS);
        FORMALITIES.add(BEACH);

        TEMPERATURES.add(HOT);
        TEMPERATURES.add(COLD);
        TEMPERATURES.add(MILD);

        setContentView(R.layout.activity_choose_outfit);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
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


    public void moveToOutfitView(View v){
        Intent outfitChoose = new Intent(this, viewOutfit.class);
        TextView tempText = (TextView) findViewById(R.id.choose_selected_temperature);
        String tempPref = (String) tempText.getText();
        TextView formText = (TextView) findViewById(R.id.choose_selected_formality);
        String formPref = (String) formText.getText();

        if ("".equals(formPref) && "".equals(tempPref)) {
            noInput();
            return;
        }

        outfitChoose.putExtra(CLOTHING_FORMALITY, formPref);
        outfitChoose.putExtra(CLOTHING_TEMPERATURE, tempPref);
        startActivity(outfitChoose);
        this.finish();
    }

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
