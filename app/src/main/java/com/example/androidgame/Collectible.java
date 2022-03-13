package com.example.androidgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

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


    private double imageWidth;
    private double imageHeight;
    private Drawable image;


    public Collectible (Context context, int width, int height, double radius) {
        this.radius = 60;
        radius = 60;
        leftRightPadding = 100 + radius;
        topPadding = 100 + radius;
        bottomPadding = 100 + 90 + radius;
        this.positionX = 0;
        this.positionY = 0;
        this.width = width;
        this.height = height;

        this.radius = radius;

        image = context.getResources().getDrawable(R.drawable.coin120x120);
        imageWidth = 120 * .75;
        imageHeight = 120 * .75;

        randomLocation();


        paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.score);
        paint.setColor(color);
    }

    public void randomLocation() {
        this.positionX = Math.random() * (width - (leftRightPadding * 2)) + leftRightPadding;
        this.positionY = Math.random() * (height /2) + topPadding;
        Rect imageBounds = new Rect();
        imageBounds.left = (int) (positionX - (imageWidth / 2));
        imageBounds.top = (int) (positionY - (imageHeight / 2));
        imageBounds.right = (int) (positionX + (imageWidth / 2));
        imageBounds.bottom = (int) (positionY + (imageHeight / 2));
        image.setBounds(imageBounds);
    }

    public void draw(Canvas canvas) {
//        canvas.drawCircle((float) positionX, (float) positionY, (float) radius, paint);
        image.draw(canvas);
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
