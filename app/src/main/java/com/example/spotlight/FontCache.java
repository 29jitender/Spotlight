package com.example.spotlight;

import android.content.Context;
import android.graphics.Typeface;

import java.util.HashMap;
import java.util.Map;

/**
 * Adapted from github.com/romannurik/muzei/
 * <p/>
 * Also see https://code.google.com/p/android/issues/detail?id=9904
 */
public class FontCache {
    private static final Map<String, Typeface> typefaceCache = new HashMap<>();
    
    private FontCache() {

    }

    public static Typeface get(Context context, String font) {
        synchronized (typefaceCache) {
            if (!typefaceCache.containsKey(font)) {
                Typeface tf = Typeface.createFromAsset(
                        context.getApplicationContext().getAssets(), font + ".ttf");
                typefaceCache.put(font, tf);
            }
            return typefaceCache.get(font);
        }
    }
}
