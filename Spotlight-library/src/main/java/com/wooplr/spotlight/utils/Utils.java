package com.wooplr.spotlight.utils;

import android.content.res.Resources;

/**
 * Created by jitender on 10/06/16.
 */

public class Utils {

    public static int pxToDp(int px) {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }

    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }
}
