package com.example.androidgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

public class Cloud {

    private Drawable cloud;
    private double positionX;
    private double positionY;
    private int imageWidth;
    private int imageHeight;
    private int xSpeed;
    private int width;
    private int height;

    public Cloud(Context context, int width, int height) {
        this.width = width;
        this.height = height;
        cloud = context.getResources().getDrawable(R.drawable.cloud1s148x64);
        this.positionX = (int) (Math.random() * width);
        this.positionY = (int) (Math.random() * height);
        this.imageWidth = 148;
        this.imageHeight = 64;

    }

    public void draw(Canvas canvas) {
        cloud.draw(canvas);
    }

    public void update() {
        positionX -= 1;
        Rect imageBounds = new Rect();
        imageBounds.left = (int) positionX;
        imageBounds.top = (int) positionY;
        imageBounds.right = (int) (positionX + imageWidth);
        imageBounds.bottom = (int) (positionY + imageHeight);
        cloud.setBounds(imageBounds);

        if (positionX + imageWidth < 0) {
            positionX = width + (int) (Math.random() * 200);
            positionY = (int) (Math.random() * height);
        }
    }
}
