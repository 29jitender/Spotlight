package com.example.spotlight;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.wooplr.spotlight.SpotlightView;
import com.wooplr.spotlight.prefs.PreferencesManager;
import com.wooplr.spotlight.utils.Utils;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity {
    private static final String INTRO_CARD = "fab_intro";
    private boolean isRevealEnabled = true;

    @BindView(R.id.switchAnimation)
    TextView switchAnimationView;

    @BindView(R.id.fab)
    FloatingActionButton fabView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                showIntro(fabView, INTRO_CARD);
            }
        }, 2400);
    }

    @OnClick({R.id.switchAnimation, R.id.reset, R.id.resetAndPlay, R.id.changePosAndPlay})
    public void onClick(View view) {
        PreferencesManager preferencesManager = new PreferencesManager(MainActivity.this);
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int screenWidth = displaymetrics.widthPixels;
        int screenHeight = displaymetrics.heightPixels;

        switch (view.getId()) {
            case R.id.switchAnimation:
                if (isRevealEnabled) {
                    switchAnimationView.setText("Switch to reveal animation");
                    isRevealEnabled = false;
                } else {
                    switchAnimationView.setText("Switch to fade animation");
                    isRevealEnabled = true;
                }
                preferencesManager.resetAll();
                break;

            case R.id.reset:
                preferencesManager.resetAll();
                break;

            case R.id.resetAndPlay:
                preferencesManager.resetAll();
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        showIntro(fabView, INTRO_CARD);
                    }
                }, 400);
                break;

            case R.id.changePosAndPlay:
                preferencesManager.resetAll();
                Random r = new Random();
                int right = r.nextInt((screenWidth - Utils.dpToPx(16)) - 16) + 16;
                int bottom = r.nextInt((screenHeight - Utils.dpToPx(16)) - 16) + 16;
                CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) fabView.getLayoutParams();
                params.setMargins(Utils.dpToPx(16), Utils.dpToPx(16), right, bottom);
                fabView.setLayoutParams(params);
                break;
        }
    }

    @OnClick(R.id.fab)
    void onFabClick() {
        Toast.makeText(MainActivity.this, "Fab Clicked!", Toast.LENGTH_SHORT).show();
    }

    private void showIntro(View targetView, String usageId) {
        new SpotlightView.Builder(this)
                .introAnimationDuration(400)
                .enableRevalAnimation(isRevealEnabled)
                .performClick(true)
                .fadeinTextDuration(400)
                //.setTypeface(FontUtil.get(this, "RemachineScript_Personal_Use"))
                .headingTvColor(ContextCompat.getColor(this, R.color.spotlight_header_text))
                .headingTvSize(32)
                .headingTvText("Love")
                .subHeadingTvColor(ContextCompat.getColor(this, R.color.spotlight_description_text))
                .subHeadingTvSize(16)
                .subHeadingTvText("Like the picture?\nLet others know.")
                .maskColor(ContextCompat.getColor(this, R.color.spotlight_mask))
                .target(targetView)
                .lineAnimDuration(400)
                .lineAndArcColor(ContextCompat.getColor(this, R.color.spotlight_line))
                .dismissOnTouch(true)
                .dismissOnBackPress(true)
                .enableDismissAfterShown(true)
                .usageId(usageId) // should be a unique ID
                .show();
    }
}

