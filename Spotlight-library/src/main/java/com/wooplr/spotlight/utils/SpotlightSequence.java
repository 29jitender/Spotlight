package com.wooplr.spotlight.utils;

/**
 * Created by Carlos Reyna on 30/07/16.
 */

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.wooplr.spotlight.SpotlightConfig;
import com.wooplr.spotlight.SpotlightView;
import com.wooplr.spotlight.prefs.PreferencesManager;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Class responsible for performing a sequence of
 * spotlight animations one after the other.
 */
public class SpotlightSequence {

    private Activity activity;
    private SpotlightConfig config;
    private Queue<SpotlightView.Builder> queue;

    private static SpotlightSequence instance;
    private final String TAG = "Tour Sequence";

    /**
     * Creates an instance of SpotlightSequence
     * with an empty queue and a {@link SpotlightConfig} configuration
     * @param activity where this sequence will be executed
     * @param config {@link SpotlightConfig}
     */
    private SpotlightSequence(Activity activity, SpotlightConfig config){
        Log.d(TAG,"NEW TOUR_SEQUENCE INSTANCE");
        this.activity = activity;
        setConfig(config);
        queue = new LinkedList<>();
    }

    /**
     * Retriebes the current instance of SpotlightSequence
     * @param activity where this sequence will be executed
     * @param config {@link SpotlightConfig}
     * @return If no instance was found. {@link SpotlightSequence()} will be called.
     */
    public static SpotlightSequence getInstance(Activity activity, SpotlightConfig config){
        if(instance == null){
            instance = new SpotlightSequence(activity,config);
        }
        return instance;
    }

    /**
     * Adds a new SpotlightView.Builder object to {@link this.queue}
     * @param target View where the spotlight will focus
     * @param title Spotlight title
     * @param subtitle Spotlight subtitle
     *                 * @param usageId id used to store the SpotlightView in {@link PreferencesManager}
     * @return SpotlightSequence instance
     */
    public SpotlightSequence addSpotlight(View target, String title, String subtitle, String usageId){
        Log.d(TAG, "Adding " + usageId);
        SpotlightView.Builder builder = new SpotlightView.Builder(activity)
                .setConfiguration(config)
                .headingTvText(title)
                .usageId(usageId)
                .subHeadingTvText(subtitle)
                .target(target)
                .setListener(new SpotlightListener() {
                    @Override
                    public void onUserClicked(String s) {
                        playNext();
                    }
                })
                .enableDismissAfterShown(true);
        queue.add(builder);
        return instance;
    }

    /**
     * Adds a new SpotlightView.Builder object to {@link this.queue}
     * @param target View where the spotlight will focus
     * @param titleResId Spotlight title
     * @param subTitleResId Spotlight subtitle
     * @param usageId id used to store the SpotlightView in {@link PreferencesManager}
     * @return SpotlightSequence instance
     */
    public SpotlightSequence addSpotlight(@NonNull View target, int titleResId, int subTitleResId, String usageId){
        String title = activity.getString(titleResId);
        String subtitle = activity.getString(subTitleResId);
        SpotlightView.Builder builder = new SpotlightView.Builder(activity)
                .setConfiguration(config)
                .headingTvText(title)
                .usageId(usageId)
                .subHeadingTvText(subtitle)
                .target(target)
                .setListener(new SpotlightListener() {
                    @Override
                    public void onUserClicked(String s) {
                        playNext();
                    }
                })
                .enableDismissAfterShown(true);
        queue.add(builder);
        return instance;
    }

    /**
     * Starts the sequence.
     */
    public void startSequence(){
        if(queue.isEmpty()) {
            Log.d(TAG, "EMPTY SEQUENCE");
        }else{
            queue.poll().show();
        }
    }

    /**
     * Free variables. Executed when the tour has finished
     */
    private void resetTour() {
        instance = null;
        queue.clear();
        this.activity = null;
        config = null;
    }

    /**
     * Executes the next Spotlight animation in the queue.
     * If no more animations are found, resetTour()is called.
     */
    private void playNext(){
        SpotlightView.Builder next = queue.poll();
        if(next != null){
//            Log.d(TAG,"PLAYING NEXT SPOTLIGHT");
            next.show().setReady(true);

        }else {
            Log.d(TAG, "END OF QUEUE");
            resetTour();
        }
    }

    /**
     * Clear all Spotlights usageId from shared preferences.
     * @param context
     */
    public static void resetSpotlights(@NonNull Context context){
        new PreferencesManager(context).resetAll();
    }

    /**
     * Sets the specified {@link SpotlightConfig} configuration
     * as the configuration to use.
     * If no configuration is specified, the default configuration is used.
     * @param config {@link SpotlightConfig}
     */
    private void setConfig(@Nullable SpotlightConfig config) {
        if(config == null){
            config = new SpotlightConfig();
            config.setLineAndArcColor(Color.parseColor("#eb273f"));
            config.setDismissOnTouch(true);
            config.setMaskColor(Color.argb(240,0,0,0));
            config.setHeadingTvColor(Color.parseColor("#eb273f"));
            config.setHeadingTvSize(32);
            config.setSubHeadingTvSize(16);
            config.setSubHeadingTvColor(Color.parseColor("#ffffff"));
            config.setPerformClick(false);
            config.setRevealAnimationEnabled(true);
            config.setLineAnimationDuration(400);
        }
        this.config = config;
    }
}

