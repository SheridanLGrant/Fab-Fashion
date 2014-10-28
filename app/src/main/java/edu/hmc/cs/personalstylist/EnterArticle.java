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

/**
 * Created by Sheridan on 10/12/2014.
 */
public class EnterArticle extends Activity implements PopupMenu.OnMenuItemClickListener {
    public final static String ARTICLE_NAME = "edu.hmc.cs.personalstylist.articleName";
    public final static String CLOTHING_TYPE = "edu.hmc.cs.personalstylist.clothingType";
    String articleName;
    String clothingType;
    Wardrobe wardrobe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
//        String message = intent.getStringExtra(MainActivity.WARDROBE_MESSAGE);

        setContentView(R.layout.activity_enter_article);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void showPopUpType(View v){
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(this);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.type, popup.getMenu());
        popup.show();
    }

    public void enterItem(View view) throws IOException {
//        Context context = getApplicationContext();
//        String filename = "filename";
//        FileOutputStream outputStream = openFileOutput(filename, context.MODE_PRIVATE);
        EditText clothingName = (EditText) findViewById(R.id.clothingName);
        articleName = clothingName.getText().toString();

        // Obviously needs to be more sophisticated
//        outputStream.write(name.getBytes());
//        outputStream.close();


//        FileInputStream fin = openFileInput(filename);
//        int c = 0;
//        String temp = "";
//        while( (c = fin.read()) != -1){
//            temp = temp + Character.toString((char)c);
//        }
////string temp contains all the data of the file.
//        fin.close();

        TextView type = (TextView) findViewById(R.id.selected_type);
        clothingType = (String) type.getText();

        TextView test = (TextView) findViewById(R.id.testingFileWriteRead);
        test.setText(clothingType);

        Intent returnToMain = new Intent(this, MainActivity.class);
        returnToMain.putExtra(ARTICLE_NAME, articleName);
        returnToMain.putExtra(CLOTHING_TYPE, clothingType);

        startActivity(returnToMain);




    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {

        CharSequence type = menuItem.getTitle();
        TextView view = (TextView) findViewById(R.id.selected_type);
        view.setText(type);


        return true;
    }
}
