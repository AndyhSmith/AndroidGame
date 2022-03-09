package com.example.androidgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

public class Collectible {
    private double positionX;
    private double positionY;
    private double radius;

    private double leftRightPadding;
    private double topPadding;
    private double bottomPadding;

    private int width;
    private int height;

    private Paint paint;

    public Collectible (Context context, int width, int height, double radius) {
        leftRightPadding = 20 + radius;
        topPadding = 50 + radius;
        bottomPadding = 100 + radius;
        this.positionX = 0;
        this.positionY = 0;
        this.width = width;
        this.height = height;
        randomLocation();
        this.radius = radius;



        paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.score);
        paint.setColor(color);
    }

    public void randomLocation() {
        this.positionX = Math.random() * (width - (leftRightPadding * 2)) + leftRightPadding;
        this.positionY = Math.random() * (height - topPadding - bottomPadding) + topPadding;
    }

    public void draw(Canvas canvas) {
        canvas.drawCircle((float) positionX, (float) positionY, (float) radius, paint);
    }

    public void update() {
    }

    public double getPositionX() {
        return this.positionX;
    }

    public double getPositionY() {
        return this.positionY;
    }

    public double getRadius() {
        return this.radius;
    }
}
