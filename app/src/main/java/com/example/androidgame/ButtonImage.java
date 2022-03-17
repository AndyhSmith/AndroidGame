package com.example.androidgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.Log;

public class ButtonImage extends Button {

    private Drawable image;
    private double width;
    private double height;
    private double positionX;
    private double positionY;

    public ButtonImage(Context context, double height, double width, double positionX, double positionY, Drawable image) {
        super(context, height, width, positionX, positionY, "", 0);
        this.image = image;
        this.width = width;
        this.height = height;
        this.positionX = positionX;
        this.positionY = positionY;
        Rect imageBounds = new Rect();
        Log.d("width", Double.toString(width));
        imageBounds.left = (int) (positionX - width);
        imageBounds.top = (int) (positionY - height);
        imageBounds.right = (int) (positionX + width);
        imageBounds.bottom = (int) (positionY + height);
        image.setBounds(imageBounds);
    }

    @Override
    public void draw(Canvas canvas) {
        image.draw(canvas);
    }
    public boolean checkPress(float x, float y) {
        if (x < positionX + (width / 2) && x > positionX - (width / 2) && y > positionY - (height / 2) && y < positionY + (height / 2)) {
            return true;
        }
        return false;
    }
}
