package com.example.androidgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.Log;

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
    private Drawable imageCoin;
    private Drawable imageCorrupted;

    private boolean corrupted;


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

        imageCoin = context.getResources().getDrawable(R.drawable.coin120x120);
        imageCorrupted = context.getResources().getDrawable(R.drawable.corruptedcoin120x120);
        image = imageCoin;

        imageWidth = 120 * .75;
        imageHeight = 120 * .75;

        randomLocation();


        paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.score);
        paint.setColor(color);
        corrupted = false;
    }

    public void randomLocation() {
        this.positionX = Math.random() * (width - (leftRightPadding * 2)) + leftRightPadding;
        this.positionY = Math.random() * (height /2) + topPadding;

        corrupted = false;
        image = imageCoin;

        Rect imageBounds = new Rect();
        imageBounds.left = (int) (positionX - (imageWidth / 2));
        imageBounds.top = (int) (positionY - (imageHeight / 2));
        imageBounds.right = (int) (positionX + (imageWidth / 2));
        imageBounds.bottom = (int) (positionY + (imageHeight / 2));
        image.setBounds(imageBounds);

    }

    public void draw(Canvas canvas) {
//        canvas.drawCircle((float) positionX, (float) positionY, (float) radius, paint);
        Rect imageBounds = new Rect();
        imageBounds.left = (int) (positionX - (imageWidth / 2));
        imageBounds.top = (int) (positionY - (imageHeight / 2));
        imageBounds.right = (int) (positionX + (imageWidth / 2));
        imageBounds.bottom = (int) (positionY + (imageHeight / 2));
        image.setBounds(imageBounds);
        image.draw(canvas);
    }

    public void update(double xSpeed, double ySpeed, double posX, double posY, int width, int height, String balloonType) {
        if (balloonType == "radiation" || balloonType == "magnet") {

            double d = Math.pow(this.positionX - posX, 2) + Math.pow(this.positionY - posY, 2);
            if (d < 60000) {
                int speed = 0;
                if (balloonType == "radiation") {
                    speed = 5;
                } else if (balloonType == "magnet"){
                    speed = -3;
                }

                if (this.positionX > 0 + radius && this.positionX < width - radius) {
//                positionX += xSpeed / 2;
                    if (this.positionX > posX) {
                        this.positionX += speed;
                    } else if (this.positionX < posX) {
                        this.positionX -= speed;
                    }
                }
                if (this.positionY > 0 + radius && this.positionY < height - radius - 60) {
//                positionY += ySpeed / 2;
                    if (this.positionY > posY) {
                        this.positionY += speed;
                    } else if (this.positionY < posY) {
                        this.positionY -= speed;
                    }
                }
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

    public void corrupt() {
        image = imageCorrupted;
        this.corrupted = true;
    }

    public boolean getCorrupted() {
        return corrupted;
    }
}
