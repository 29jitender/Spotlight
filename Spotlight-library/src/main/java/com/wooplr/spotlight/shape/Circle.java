package com.wooplr.spotlight.shape;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

import com.wooplr.spotlight.target.Target;

/**
 * Created by jitender on 10/06/16.
 */

public class Circle {

    private Target target;
    private int radius;
    private Point circlePoint;
    private int padding = 20;


    public Circle(Target target, int padding) {
        this.target = target;
        this.padding = padding;
        circlePoint = getFocusPoint();
        calculateRadius(padding);
    }

    public void draw(Canvas canvas, Paint eraser, int padding) {
        calculateRadius(padding);
        circlePoint = getFocusPoint();
        canvas.drawCircle(circlePoint.x, circlePoint.y, radius, eraser);
    }

    private Point getFocusPoint() {

        return target.getPoint();
    }

    public void reCalculateAll() {
        calculateRadius(padding);
        circlePoint = getFocusPoint();
    }

    private void calculateRadius(int padding) {
        int side;
        int minSide = Math.min(target.getRect().width() / 2, target.getRect().height() / 2);
        int maxSide = Math.max(target.getRect().width() / 2, target.getRect().height() / 2);
        side = (minSide + maxSide) / 2;
        radius = side + padding;
    }

    public int getRadius() {
        return radius;
    }

    public Point getPoint() {
        return circlePoint;
    }

}
