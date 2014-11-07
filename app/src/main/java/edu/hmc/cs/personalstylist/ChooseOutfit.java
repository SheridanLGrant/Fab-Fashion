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

/**
 * Created by Sheridan on 11/5/2014.
 */
public class ChooseOutfit extends Activity implements PopupMenu.OnMenuItemClickListener {
    public final static String CLOTHING_FORMALITY = "edu.hmc.cs.personalstylist.clothingFormality";
    public final static String CLOTHING_TEMPERATURE = "edu.hmc.cs.personalstylist.clothingTemperature";
    String clothingFormality;
    String clothingTemperature;

    Context context;
    String file;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
//        String message = intent.getStringExtra(MainActivity.WARDROBE_MESSAGE);

        setContentView(R.layout.activity_choose_outfit);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
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
    public void moveToOutfitView(View v){
        Intent outfitChoose = new Intent(this, viewOutfit.class);
        TextView tempText = (TextView) findViewById(R.id.choose_selected_temperature);
        String tempPref = (String) tempText.getText();
        TextView formText = (TextView) findViewById(R.id.choose_selected_formality);
        String formPref = (String) formText.getText();
        outfitChoose.putExtra(CLOTHING_FORMALITY, formPref);
        outfitChoose.putExtra(CLOTHING_TEMPERATURE, tempPref);
        startActivity(outfitChoose);

    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {

        String clicked = (String) menuItem.getTitle();

        if (clicked.equals("wedding") || clicked.equals("day at the office") || clicked.equals("eating ice cream alone")) {
            TextView view = (TextView) findViewById(R.id.choose_selected_formality);
            view.setText(clicked);
        } else {
            TextView view = (TextView) findViewById(R.id.choose_selected_temperature);
            view.setText(clicked);
        }


        return true;
    }
}
