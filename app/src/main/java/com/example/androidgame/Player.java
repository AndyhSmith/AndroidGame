package com.example.androidgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

import androidx.core.content.ContextCompat;

public class Player {
    private double positionX;
    private double positionY;

    private double endPositionX;
    private double endPositionY;

    private double rotation;

    private double width;
    private Paint paint;
    private Paint aim;

    public Player (Context context, double positionX, double positionY, double width) {
        this.endPositionX = positionX + 1;
        this.endPositionY = positionY + 1;
        this.positionX = positionX;
        this.positionY = positionY;
        this.width = width;

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
        canvas.drawRect((float) (positionX - width) , (float) positionY, (float) (positionX + width), (float) (positionY + 20), paint);
        canvas.restore();


    }

    public void update() {
    }

    public void setStartPosition(double positionX, double positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.rotation = Math.toDegrees(Math.atan2((this.endPositionY - this.positionY), (this.endPositionX - this.positionX))) + 90;

    }

    public void setEndPosition(double positionX, double positionY) {
        this.endPositionX = positionX;
        this.endPositionY = positionY;
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
}
