package com.wooplr.spotlight.shape;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;

import com.wooplr.spotlight.target.AnimPoint;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapted from github.com/dupengtao/LineAnimation
 */
public class NormalLineAnimDrawable extends Drawable implements ValueAnimator.AnimatorUpdateListener {

    private static final String FACTOR_X = "factorX";
    private static final String FACTOR_Y = "factorY";
    private final Path mPath2;
    private final Paint mPaint2;
    private float factorY, factorX;
    private AnimPoint curAnimPoint = null;
    private int moveTimes;
    private List<AnimPoint> mAnimPoints = new ArrayList<AnimPoint>();
    private ObjectAnimator mLineAnim;
    private DisplayMode curDisplayMode = DisplayMode.Appear;
    private long lineAnimDuration = 400;
    private int lineColor = Color.parseColor("#eb273f");
    private int lineStroke = 8;

    private Animator.AnimatorListener mListner;

    public NormalLineAnimDrawable() {
        this(null);
    }

    public NormalLineAnimDrawable(Paint paint) {
        mPath2 = new Path();
        mPaint2 = paint == null ? getDefaultPaint() : paint;
    }

    private Paint getDefaultPaint() {
        Paint p = new Paint();
        p.setAntiAlias(true);
        p.setDither(true);
        p.setStyle(Paint.Style.STROKE);
        p.setStrokeJoin(Paint.Join.ROUND);
        p.setStrokeCap(Paint.Cap.ROUND);
        p.setStrokeWidth(lineStroke);
        p.setColor(lineColor);
        return p;
    }

    private ObjectAnimator getLineAnim() {
        PropertyValuesHolder pvMoveY = PropertyValuesHolder.ofFloat(FACTOR_Y,
                0f, 1f);
        PropertyValuesHolder pvMoveX = PropertyValuesHolder.ofFloat(FACTOR_X,
                0f, 1f);
        ObjectAnimator lineAnim = ObjectAnimator.ofPropertyValuesHolder(
                this, pvMoveY, pvMoveX).setDuration(lineAnimDuration);
        lineAnim.setRepeatMode(ValueAnimator.RESTART);
        lineAnim.setRepeatCount(mAnimPoints.size() - 1);
        lineAnim.addUpdateListener(this);
        if (android.os.Build.VERSION.SDK_INT > 17) {
            lineAnim.setAutoCancel(true);
        }
        lineAnim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                moveTimes = 0;
                curAnimPoint = mAnimPoints.get(moveTimes);
                if (mListner != null)
                    mListner.onAnimationStart(animation);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (mListner != null)
                    mListner.onAnimationEnd(animation);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                if (mListner != null)
                    mListner.onAnimationCancel(animation);
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                moveTimes++;
                curAnimPoint = mAnimPoints.get(moveTimes);
                if (mListner != null)
                    mListner.onAnimationRepeat(animation);
            }
        });
        //lineAnim.addListener(mListner);
        return lineAnim;
    }

    @NonNull
    public void setmListner(Animator.AnimatorListener mListner) {
        this.mListner = mListner;
    }

    public void playAnim(List<AnimPoint> animPoints) {
        if (animPoints != null) {
            mAnimPoints = animPoints;
        }
        if (mLineAnim == null) {
            mLineAnim = getLineAnim();
        }
        if (mLineAnim.isRunning()) {
            mLineAnim.cancel();
        }
        mLineAnim.start();
    }

    public void playAnim() {
        playAnim(null);
    }


    public float getFactorY() {
        return factorY;
    }

    public void setFactorY(float factorY) {
        this.factorY = factorY;
    }

    public float getFactorX() {
        return factorX;
    }

    public void setFactorX(float factorX) {
        this.factorX = factorX;
    }

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        invalidateSelf();
    }

    private void drawLine(List<AnimPoint> animPoints, int num) {
        drawLine(animPoints, num, animPoints.size());
    }

    private void drawLine(List<AnimPoint> animPoints, int num, int size) {
        for (int i = num, j = size; i < j; i++) {
            AnimPoint p = animPoints.get(i);
            mPath2.moveTo(p.getCurX(), p.getCurY());
            mPath2.lineTo(p.getMoveX(), p.getMoveY());
        }
    }

    public DisplayMode getCurDisplayMode() {
        return curDisplayMode;
    }

    public void setCurDisplayMode(DisplayMode curDisplayMode) {
        this.curDisplayMode = curDisplayMode;
    }

    @Override
    public void draw(Canvas canvas) {
        if (curAnimPoint != null) {
            mPath2.rewind();
            float curX = curAnimPoint.getCurX();
            float curY = curAnimPoint.getCurY();
            float moveX = curAnimPoint.getMoveX();
            float moveY = curAnimPoint.getMoveY();
            if (curDisplayMode == DisplayMode.Disappear) {
                mPath2.moveTo(curX == moveX ? moveX : curX + ((moveX - curX) * factorX), curY == moveY ? moveY : curY + ((moveY - curY) * factorY));
                mPath2.lineTo(moveX, moveY);
                drawLine(mAnimPoints, moveTimes + 1);
            } else if (curDisplayMode == DisplayMode.Appear) {
                drawLine(mAnimPoints, 0, moveTimes);
                mPath2.moveTo(curX, curY);
                mPath2.lineTo(curX == moveX ? moveX : curX + ((moveX - curX) * factorX), curY == moveY ? moveY : curY + ((moveY - curY) * factorY));
            }
            canvas.drawPath(mPath2, mPaint2);
        } else {
            canvas.drawPath(mPath2, mPaint2);
        }
    }

    @Override
    public void setAlpha(int alpha) {

    }

    @Override
    public void setColorFilter(ColorFilter cf) {

    }

    @Override
    public int getOpacity() {
        return 0;
    }

    public List<AnimPoint> getPoints() {
        return mAnimPoints;
    }

    public void setPoints(List<AnimPoint> animPoints) {
        mAnimPoints = animPoints;
    }

    public void setLineAnimDuration(long lineAnimDuration) {
        this.lineAnimDuration = lineAnimDuration;
    }

    public void setLineColor(int lineColor) {
        this.lineColor = lineColor;
    }

    public void setLineStroke(int lineStroke) {
        this.lineStroke = lineStroke;
    }

    /**
     * How to display the LineAnim
     */
    public enum DisplayMode {

        Disappear,

        Appear,
    }

}
