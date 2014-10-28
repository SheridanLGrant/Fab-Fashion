package edu.hmc.cs.personalstylist;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;

import edu.hmc.cs.personalstylist.Wardrobe;

public class MainActivity extends Activity {
    public final static String NAME_MESSAGE = "edu.hmc.cs.personalstylist.nameMessage";
    public final static String WARDROBE_MESSAGE = "edu.hmc.cs.personalstylist.wardrobeMessage";
    public final static String ARTICLE_MESSAGE = "edu.hmc.cs.personalstylist.articleMessage";
    public final static String ARTICLE_NAME = "edu.hmc.cs.personalstylist.articleName";
    public final static String CLOTHING_TYPE = "edu.hmc.cs.personalstylist.clothingType";
    public final static String CLOTHING_COLOR = "edu.hmc.cs.personalstylist.clothingColor";
    public final static String CLOTHING_FORMALITY = "edu.hmc.cs.personalstylist.clothingFormality";
    public final static String CLOTHING_TEMPERATURE = "edu.hmc.cs.personalstylist.clothingTemperature";
    Wardrobe wardrobe;
//    Context context;
//    File wardrobeFile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

    // Here we want to read the wardrobe in.

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TextView topText = (TextView) findViewById(R.id.wardrobeTop);
        TextView middleText = (TextView) findViewById(R.id.wardrobeMiddle);
        TextView bottomText = (TextView) findViewById(R.id.wardrobeBottom);
        topText.setText("Google Shirt");
        middleText.setText("Sexy Overalls");
        bottomText.setText("Stilettos");


        Intent receivedIntent = getIntent();
        String receivedName = receivedIntent.getStringExtra(ARTICLE_NAME);
        String receivedType = receivedIntent.getStringExtra(CLOTHING_TYPE);
        String receivedColor = receivedIntent.getStringExtra(CLOTHING_COLOR);

        if ("shirt".equals(receivedType)) {
            topText.setText(receivedName);
        } else if ("pants".equals(receivedType)) {
            middleText.setText(receivedName);
        } else if ("shoes".equals(receivedType)) {
            bottomText.setText(receivedName);
        }
    }

//    public boolean getWardrobe() {
//
//        wardrobeFile = context.getFilesDir();
//
//        if (wardrobeFile.exists()) {
//            Log.d("Hey", "The file already exists");
//        }
//
//        return true;
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
        }
        return super.onOptionsItemSelected(item);
    }


    public void viewWardrobe(View view) {
        Intent viewWardrobe = new Intent(this, DisplayWardrobe.class);

        // Make this refer to something in strings.xml
        viewWardrobe.putExtra(WARDROBE_MESSAGE, "Your Wardrobe");

        startActivity(viewWardrobe);
    }

    public void enterArticle(View view) {
        Intent enterArticle = new Intent(this, EnterArticle.class);

        enterArticle.putExtra(ARTICLE_MESSAGE, "New Article of Clothing");

        startActivity(enterArticle);
    }

    public void chooseOutfit(View view) {
    }
}
