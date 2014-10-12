package edu.hmc.cs.personalstylist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Sheridan on 10/12/2014.
 */
public class DisplayName extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // In the future, we're going to want the following line,
        // as we'll move to a new activity and not just a TextView
//        setContentView(R.layout.activity_display_name);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.NAME_MESSAGE);

        TextView nameView = new TextView(this);
        nameView.setTextSize(40);
        nameView.setText(message);

        setContentView(nameView);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
