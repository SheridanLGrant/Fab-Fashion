package edu.hmc.cs.personalstylist;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.LinearLayout;


// TODO: Max, comment this class por favorrrr.
/**
 * Created by Max on 11/17/2014.
 *
 * From http://stackoverflow.com/questions/8181828/android-detect-when-scrollview-stops-scrolling
 */
public class MyScrollView extends HorizontalScrollView {
    private Runnable scrollerTask;
    private int initialPosition;

    private int newCheck = 100;
    private static final String TAG = "MyScrollView";

    public interface OnScrollStoppedListener{
        void onScrollStopped();
    }

    private OnScrollStoppedListener onScrollStoppedListener;

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

    /// Helper function for Sheridan.
    /// Will center the layout if necessary. Returns null if there are no clothes in the layout.
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
