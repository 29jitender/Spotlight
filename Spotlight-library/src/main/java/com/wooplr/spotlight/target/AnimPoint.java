package com.wooplr.spotlight.target;

/**
 * Adapted from github.com/dupengtao/LineAnimation
 */
public class AnimPoint {
    private float curX;
    private float curY;
    private float moveX;
    private float moveY;

    public AnimPoint(float curX, float curY, float moveX, float moveY) {
        this.curX = curX;
        this.curY = curY;
        this.moveX = moveX;
        this.moveY = moveY;
    }

    public AnimPoint() {
    }

    public float getCurX() {
        return curX;
    }

    public void setCurX(float curX) {
        this.curX = curX;
    }

    public float getCurY() {
        return curY;
    }

    public void setCurY(float curY) {
        this.curY = curY;
    }

    public float getMoveX() {
        return moveX;
    }

    public void setMoveX(float moveX) {
        this.moveX = moveX;
    }

    public float getMoveY() {
        return moveY;
    }

    public void setMoveY(float moveY) {
        this.moveY = moveY;
    }
}
