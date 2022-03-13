package com.example.androidgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.Log;

import androidx.core.content.ContextCompat;

public class Player {
    private double permPositionX;
    private double permPositionY;

    private double positionX;
    private double positionY;

    private double endPositionX;
    private double endPositionY;

    private double rotation;

    private double width;
    private Paint paint;
    private Paint aim;

    private Drawable image;
    private double imageWidth;
    private double imageHeight;

    public Player (Context context, double positionX, double positionY, double width) {
        this.endPositionX = positionX + 1;
        this.endPositionY = positionY + 1;
        this.positionX = positionX;
        this.positionY = positionY;
        this.permPositionX = positionX;
        this.permPositionY = this.positionY;
        this.width = width;
        this.rotation = 0;


        image = context.getResources().getDrawable(R.drawable.player460x50);
        imageWidth = 460;
        imageHeight = 50;
        this.width = imageWidth / 2;


        paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.player);
        paint.setColor(color);

        aim = new Paint();
        int colorAim = ContextCompat.getColor(context, R.color.aim);
        aim.setColor(colorAim);
    }

    public void draw(Canvas canvas) {

        canvas.drawLine((float) positionX, (float) positionY, (float) endPositionX, (float) endPositionY, aim);

        canvas.save();
        canvas.rotate((float) this.rotation, (float) this.positionX, (float) this.positionY);
//        canvas.drawRect((float) (positionX - width) , (float) positionY, (float) (positionX + width), (float) (positionY + 20), paint);

        Rect imageBounds = new Rect();
        imageBounds.left = (int) (positionX - (imageWidth / 2));
        imageBounds.top = (int) (positionY - (imageHeight / 2));
        imageBounds.right = (int) (positionX + (imageWidth / 2));
        imageBounds.bottom = (int) (positionY + (imageHeight / 2));
        image.setBounds(imageBounds);
        image.draw(canvas);

        canvas.restore();


    }

    public void update() {
    }

    public void setStartPosition(double positionX, double positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.rotation = Math.toDegrees(Math.atan2((this.endPositionY - this.positionY), (this.endPositionX - this.positionX))) + 90;

    }

    public void setEndPosition(double x, double y) {
        this.endPositionX = x;
        this.endPositionY = y;
        this.rotation = Math.toDegrees(Math.atan2((this.endPositionY - this.positionY), (this.endPositionX - this.positionX))) + 90;
    }

    public double getPositionY() {
        return this.positionY;
    }

    public double getPositionX() {
        return this.positionX;
    }

    public double getRotation() {
        return this.rotation;
    }

    public double getAimSlope() {
        return (positionY - endPositionY) / (positionX - endPositionX);
    }

    public double getWidth() {
        return width;
    }

    public void updatePosition(double x, double y) {
        this.positionX = endPositionX;
        this.positionY = endPositionY;
        this.endPositionX = x;
        this.endPositionY = y;
        this.rotation = Math.toDegrees(Math.atan2((this.endPositionY - this.positionY), (this.endPositionX - this.positionX))) + 90;
        Log.d("settings", Double.toString(this.rotation));
    }

    public void reset() {
        this.positionX = permPositionX;
        this.positionY = permPositionY;
        this.endPositionX = this.positionX;
        this.endPositionY = this.positionY - 1;
        this.rotation = 0;
        Log.d("reset", Double.toString(this.positionX));
    }
}
