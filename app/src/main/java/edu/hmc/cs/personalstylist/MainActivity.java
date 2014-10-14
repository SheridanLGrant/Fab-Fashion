package edu.hmc.cs.personalstylist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import edu.hmc.cs.personalstylist.Wardrobe;

public class MainActivity extends Activity {
    public final static String NAME_MESSAGE = "edu.hmc.cs.personalstylist.nameMessage";
    public final static String WARDROBE_MESSAGE = "edu.hmc.cs.personalstylist.wardrobeMessage";
    public final static String ARTICLE_MESSAGE = "edu.hmc.cs.personalstylist.articleMessage";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


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
        return super.onOptionsItemSelected(item);
    }

    public void enterName(View view) {
        Intent enterName = new Intent(this, DisplayName.class);
        EditText name = (EditText) findViewById(R.id.enter_name);
        String message = name.getText().toString();
        enterName.putExtra(NAME_MESSAGE, message);

        startActivity(enterName);
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
