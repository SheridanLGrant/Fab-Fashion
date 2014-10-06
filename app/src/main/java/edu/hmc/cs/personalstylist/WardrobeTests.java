package edu.hmc.cs.personalstylist;

import android.test.InstrumentationTestCase;
import android.util.Log;

/**
 * Created by Sheridan on 10/5/2014.
 */
class WardrobeTests extends InstrumentationTestCase {
    public void test() throws Exception {
        Log.d("WardrobeTests", "yo");
        final int expected = 1;
        final int reality = 5;
        assertEquals(expected, reality);
    }
}
