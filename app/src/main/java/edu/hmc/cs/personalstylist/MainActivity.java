package edu.hmc.cs.personalstylist;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
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

    public final static String LONG_SLEEVE_SHIRT = "Long-sleeve shirt";
    public final static String SHORT_SLEEVE_SHIRT = "Short-sleeve shirt";
    public final static String SLEEVELESS_SHIRT = "Sleeveless shirt";
    public final static String PANTS = "Pants";
    public final static String SHORTS = "Shorts";
    public final static String SKIRT = "Skirt";
    public final static String DRESS_SHOES = "Dress shoes";
    public final static String TENNIS_SHOES = "Tennis shoes";
    public final static String SANDALS = "Sandals";

    ArrayList<Clothing> wardrobe = new ArrayList<Clothing>();
    String wardrobeString;
    Context context;
    String file = "wardrobeData";

    // For image buttons
    RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // This block sets the padding, and should be moved later
        // Based on: http://stackoverflow.com/questions/20438705/xml-get-width-and-set-to-androidheight
        // - Max, desperately trying to get everything to work
        Display mDisplay = getWindowManager().getDefaultDisplay();
        int width = mDisplay.getWidth();
        LinearLayout shoeLayout = (LinearLayout) findViewById(R.id.shoeLayout);
        shoeLayout.setPadding(width, 0, width, 0);


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
        }
        else {
            wardrobe = gson.fromJson(wardrobeString, clothingList);
        }

        displayStoredWardrobe();

        initializeScrollViews();

    }



    private void displayStoredWardrobe() {
        Clothing currentArticle;
        for (int i = 0; i < wardrobe.size(); i++) {
            currentArticle = wardrobe.get(i);
            String name = currentArticle.getName();
            String type = currentArticle.getType();
            String color = currentArticle.getColor();
            String formality = currentArticle.getFormality();
            String temperature = currentArticle.getTemperature();

            ImageButton button = createImageButton(currentArticle);

            if (LONG_SLEEVE_SHIRT.equals(type) || SHORT_SLEEVE_SHIRT.equals(type) || SLEEVELESS_SHIRT.equals(type)) {
                LinearLayout view = (LinearLayout) findViewById(R.id.topLayout);
                view.addView(button);
            }
            else if (PANTS.equals(type) || SHORTS.equals(type) || SKIRT.equals(type)) {
                LinearLayout view = (LinearLayout) findViewById(R.id.bottomLayout);
                view.addView(button);
            }
            else if (DRESS_SHOES.equals(type) || TENNIS_SHOES.equals(type) || SANDALS.equals(type)) {
                LinearLayout view = (LinearLayout) findViewById(R.id.shoeLayout);
                view.addView(button);
            }
        }
    }



    // TODO: change to appropriate icons
    private ImageButton createImageButton(Clothing currentArticle) {
        ImageButton button = new ImageButton(this);
        button.setImageResource(R.drawable.jersey);
        button.setLayoutParams(params);
        button.setOnClickListener(this);
        button.setBackgroundColor(Color.TRANSPARENT);
        button.setTag(currentArticle.getName());

        return button;
    }


    private void initializeScrollViews() {
        final MyScrollView shoeScroller = (MyScrollView) findViewById(R.id.shoeScroller);
        MyScrollView topScroller = (MyScrollView) findViewById(R.id.topScroller);
        MyScrollView bottomScroller = (MyScrollView) findViewById(R.id.bottomScroller);

        shoeScroller.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    shoeScroller.startScrollerTask();
                }
                return false;
            }
        });
        shoeScroller.setOnScrollStoppedListener(new MyScrollView.OnScrollStoppedListener() {
            @Override
            public void onScrollStopped() {
                // From http://stackoverflow.com/questions/12424373/how-to-scroll-to-center-of-child-of-horizontalscrollview
                //get the center
                int center = shoeScroller.getScrollX() + shoeScroller.getWidth() / 2;
                LinearLayout linearLayout = ((LinearLayout)shoeScroller.findViewById(R.id.shoeLayout));
                int numChildren = linearLayout.getChildCount();
                for (int i = 0; i < numChildren; i++) {
                    View v = linearLayout.getChildAt(i);
                    int viewLeft = v.getLeft();
                    int viewWidth = v.getWidth();
                    if (center >= viewLeft && center <= viewLeft + viewWidth) {
                        Log.d("initializeScrollViews()", "CENTER THIS : " + ((viewLeft + viewWidth / 2) - center));
                        shoeScroller.scrollBy((viewLeft + viewWidth / 2) - center, 0);
                        break;
                    }
                }
            }
        });
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

        else if (id == R.id.action_enter_article) {
            Intent enterArticle = new Intent(this, EnterArticle.class);
            startActivity(enterArticle);
        } else if (id == R.id.action_choose_outfit) {
            Intent chooseOutfit = new Intent(this, ChooseOutfit.class);
            startActivity(chooseOutfit);
        }
        return super.onOptionsItemSelected(item);
    }



    // TODO: need to get article info in this function to display info properly
    @Override
    public void onClick(View v) {
        ImageButton b = (ImageButton) v;
        PopupMenu popup = new PopupMenu(this, b);
        popup.getMenu().add("Name: " + b.getTag());

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

        popup.getMenu().add("Delete: " + b.getTag());


        popup.setOnMenuItemClickListener(this);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.article_options, popup.getMenu());
        popup.show();
    }



    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        String clicked = (String) menuItem.getTitle();
        boolean removeFlag = false;
        int deletion = 0;

        if (!clicked.startsWith("Del")) {
            return true;
        }
        else {
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
