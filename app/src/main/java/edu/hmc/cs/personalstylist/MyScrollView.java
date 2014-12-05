package edu.hmc.cs.personalstylist;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;


/**
 * A customized Horizontal Scroll view with extended functionality.
 *
 * Keeps track of when the view has been scrolled, which allows the view to respond (i.e. by
 * snapping an item to the center) when the user stops scrolling.
 *
 * Based on code from
 * http://stackoverflow.com/questions/8181828/android-detect-when-scrollview-stops-scrolling
 *
 * Created by Max on 11/17/2014.
 *
 * @see android.widget.HorizontalScrollView
 */
public class MyScrollView extends HorizontalScrollView {
    private Runnable scrollerTask;
    private int initialPosition;

    private final int newCheck = 100;

    public interface OnScrollStoppedListener{
        void onScrollStopped();
    }

    private OnScrollStoppedListener onScrollStoppedListener;

    /**
     * Constructor for a MyScrollView object.
     *
     * Extends the functionality of a HorizontalScrollView to keep track of when the view has been
     * scrolled.
     *
     * @param context
     * @param attrs
     *
     * @see android.widget.HorizontalScrollView
     */
    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);

        scrollerTask = new Runnable() {

            public void run() {

                int newPosition = getScrollX();
                if(initialPosition - newPosition == 0){//has stopped

                    if(onScrollStoppedListener!=null){

                        onScrollStoppedListener.onScrollStopped();
                    }
                }else{
                    initialPosition = getScrollX();
                    MyScrollView.this.postDelayed(scrollerTask, newCheck);
                }
            }
        };
    }

    public void setOnScrollStoppedListener(MyScrollView.OnScrollStoppedListener listener){
        onScrollStoppedListener = listener;
    }

    public void startScrollerTask(){

        initialPosition = getScrollX();
        MyScrollView.this.postDelayed(scrollerTask, newCheck);
    }

    /**
     * Centers an item in a LinearLayout in the view.
     *
     * Finds the item nearest to the center, and centers it. Does nothing if there are no items in
     * the LinearLayout.
     *
     * @param myLayout A LinearLayout contained in the HorizontalScrollView.
     */
    public void center(LinearLayout myLayout) {
        // From http://stackoverflow.com/questions/12424373/how-to-scroll-to-center-of-child-of-horizontalscrollview
        // get the center
        int numChildren = myLayout.getChildCount();
        if (numChildren == 0) {
            return;
        }

        int center = this.getScrollX() + this.getWidth() / 2;

        View firstChild = myLayout.getChildAt(0);
        int firstLeft = firstChild.getLeft();
        int firstWidth = firstChild.getWidth();

        View lastChild = myLayout.getChildAt(numChildren-1);
        int lastLeft = lastChild.getLeft();
        int lastWidth = lastChild.getWidth();

        if (center < firstLeft) {
            this.scrollBy((firstLeft + (firstWidth / 2)) - center, 0);
        }
        else if (center > lastLeft + lastWidth) {
            this.scrollBy((lastLeft + (lastWidth / 2)) - center, 0);
        }
        else {
            for (int i = 0; i < numChildren; i++) {
                View v = myLayout.getChildAt(i);
                int viewLeft = v.getLeft();
                int viewWidth = v.getWidth();

                if (center >= viewLeft && center <= viewLeft + viewWidth) {
                    this.scrollBy((viewLeft + viewWidth / 2) - center, 0);
                    break;
                }
            }
        }
    }

    /**
     * Finds the clothing item closest to the center of a LinearLayout in the view.
     *
     * The LinearLayout must contain clothing items for this function to work.
     *
     * @param myLayout A LinearLayout contained in the HorizontalScrollView.
     * @return The Clothing object nearest to the center of the view, or null if the LinearLayout
     *         is empty.
     */
    public Clothing getCenterItem(LinearLayout myLayout) {
        center(myLayout);

        int numChildren = myLayout.getChildCount();

        if (numChildren == 0) {
            return null;
        }

        int center = this.getScrollX() + this.getWidth() / 2;

        View firstChild = myLayout.getChildAt(0);
        int firstLeft = firstChild.getLeft();

        View lastChild = myLayout.getChildAt(numChildren-1);
        int lastLeft = lastChild.getLeft();
        int lastWidth = lastChild.getWidth();

        if (center < firstLeft) {
            return (Clothing) myLayout.getChildAt(0).getTag();
        }
        else if (center > lastLeft + lastWidth) {
            return (Clothing) myLayout.getChildAt(numChildren-1).getTag();
        }
        else {
            for (int i = 0; i < numChildren; i++) {
                View v = myLayout.getChildAt(i);
                int viewLeft = v.getLeft();
                int viewWidth = v.getWidth();

                if (center >= viewLeft && center <= viewLeft + viewWidth) {
                    return (Clothing) myLayout.getChildAt(i).getTag();
                }
            }
        }

        return null;
    }
}
