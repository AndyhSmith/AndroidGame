package com.example.androidgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.Log;

import androidx.core.content.ContextCompat;

public class Item {
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

    private boolean moving;
    private int counter;
    private int visableTime;



    public Item (Context context, int width, int height, double radius) {
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

        image = context.getResources().getDrawable(R.drawable.itemup120x120);
        imageWidth = 120 * .75;
        imageHeight = 120 * .75;

        randomLocation(false);

        this.moving = false;
        this.counter = 0;
        this.visableTime = 0;


        paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.score);
        paint.setColor(color);
    }

    public void randomLocation(boolean onScreen) {
        if (onScreen) {
            this.positionY = (Math.random() * (height /3)) + (height / 3);
        } else {
            this.positionY = height + 100;
        }
        this.positionX = Math.random() * (width - (leftRightPadding * 2)) + leftRightPadding;

        Rect imageBounds = new Rect();
        imageBounds.left = (int) (positionX - (imageWidth / 2));
        imageBounds.top = (int) (positionY - (imageHeight / 2));
        imageBounds.right = (int) (positionX + (imageWidth / 2));
        imageBounds.bottom = (int) (positionY + (imageHeight / 2));
        image.setBounds(imageBounds);
    }

    public void draw(Canvas canvas) {
//        canvas.drawCircle((float) positionX, (float) positionY, (float) radius, paint);
        if (!moving) {
            Rect imageBounds = new Rect();
            imageBounds.left = (int) (positionX - (imageWidth / 2));
            imageBounds.top = (int) (positionY - (imageHeight / 2));
            imageBounds.right = (int) (positionX + (imageWidth / 2));
            imageBounds.bottom = (int) (positionY + (imageHeight / 2));
            image.setBounds(imageBounds);
            image.draw(canvas);
        }

        else if (moving && (counter / 40) % 2 == 0) {
            Rect imageBounds = new Rect();
            imageBounds.left = (int) (positionX - (imageWidth / 2));
            imageBounds.top = (int) (positionY - (imageHeight / 2));
            imageBounds.right = (int) (positionX + (imageWidth / 2));
            imageBounds.bottom = (int) (positionY + (imageHeight / 2));
            image.setBounds(imageBounds);
            image.draw(canvas);
        }
    }

    public void update() {
        visableTime += 1;
        if (moving && visableTime > 10 * 60) {
            counter += 1;
        }
        if (Math.random() <= .001) {
            moving = true;
        }
        if (counter > 5 * 60) {
            counter = 0;
            visableTime = 0;
            if (Math.random() > .5) {
                randomLocation(true);
            }
            else {
                randomLocation(false);
            }

        }
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

    public void reset() {
        counter = 0;
        visableTime = 0;
        randomLocation(false);
    }
}
