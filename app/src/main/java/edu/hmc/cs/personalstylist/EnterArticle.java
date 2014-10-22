package edu.hmc.cs.personalstylist;
// Used code from http://www.androidhive.info/2013/07/android-expandable-list-view-tutorial/

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.Toast;

/**
 * Created by Sheridan on 10/12/2014.
 */
public class EnterArticle extends Activity {

    ClothingNameListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
//        String message = intent.getStringExtra(MainActivity.WARDROBE_MESSAGE);

        setContentView(R.layout.activity_enter_article);

        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.clothingType);

        // preparing list data
        prepareListData();

        listAdapter = new ClothingNameListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /*
    * Preparing the list data
    */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Types");
        listDataHeader.add("Colors");
        listDataHeader.add("Formalities");
        listDataHeader.add("Temperatures");

        // Adding child data
        // HAT, DRESS, SHIRT, PANTS, SHOES, LAYER, ACCESSORY, UNKNOWN_TYPE
        ArrayList<String> types = new ArrayList<String>();
        types.add("Hat");
        types.add("Dress");
        types.add("Shirt");
        types.add("Pants");
        types.add("Shoes");
        types.add("Layer");
        types.add("Accessory");
        types.add("Other");

        // RED, YELLOW, GREEN, BLUE, PURPLE, GRAY, BLACK, PINK, ORANGE, WHITE, BROWN, UNKNOWN_COLOR
        ArrayList<String> colors = new ArrayList<String>();
        types.add("Red");
        types.add("Yellow");
        types.add("Green");
        types.add("Blue");
        types.add("Purple");
        types.add("Gray");
        types.add("Black");
        types.add("Pink");
        types.add("Orange");
        types.add("White");
        types.add("Brown");
        types.add("Other");

        // CASUAL, BUSINESS, FORMAL, BEACH, UNKNOWN_FORMALITY
        ArrayList<String> formalities = new ArrayList<String>();
        types.add("Casual");
        types.add("Business");
        types.add("Formal");
        types.add("Beach");
        types.add("Other");

        // HOT, COLD, MILD, SUNNY, RAINY, UNKNOWN_TEMPERATURE
        ArrayList<String> temperatures = new ArrayList<String>();
        types.add("Hot");
        types.add("Cold");
        types.add("Mild");
        types.add("Sunny");
        types.add("Rainy");
        types.add("Other");

        listDataChild.put(listDataHeader.get(0), types); // Header, Child data
        listDataChild.put(listDataHeader.get(1), colors);
        listDataChild.put(listDataHeader.get(2), formalities);
        listDataChild.put(listDataHeader.get(3), temperatures);
    }

    public void enterItem(View view) {
    }
}
