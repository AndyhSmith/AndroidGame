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
    private double xSpeed;
    private int width;
    private int height;

    public Cloud(Context context, int width, int height) {
        this.width = width + (int) (Math.random() * 500);
        this.height = height;
        cloud = context.getResources().getDrawable(R.drawable.cloud1s31x16);
        this.positionX = (int) (Math.random() * width);
        this.positionY = (int) (Math.random() * (height - 90 - imageHeight));
        this.imageWidth = 31;
        this.imageHeight = 16;
        this.xSpeed = Math.random() + .5;
    }

    public void draw(Canvas canvas) {
        if (positionX < width) {
            cloud.draw(canvas);
        }
    }

    public void update() {
        positionX -= xSpeed;
        Rect imageBounds = new Rect();
        imageBounds.left = (int) positionX;
        imageBounds.top = (int) positionY;
        imageBounds.right = (int) (positionX + imageWidth);
        imageBounds.bottom = (int) (positionY + imageHeight);
        cloud.setBounds(imageBounds);

        if (positionX + imageWidth < 0) {
            positionX = width + (int) (Math.random() * 500);
            positionY = (int) (Math.random() * (height - 90 - imageHeight));
        }
    }

    public void setImageWidth(int imageWidth) {
        this.imageWidth = imageWidth;
    }

    public void setImageHeight(int imageHeight) {
        this.imageHeight = imageHeight;
    }

    public void setImageCloud(Drawable cloud) {
        this.cloud = cloud;
    }
}
