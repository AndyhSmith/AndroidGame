package com.example.androidgame;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

public class Cloud7 extends Cloud{
    private Drawable cloud;
    private int imageWidth;
    private int imageHeight;

    public Cloud7(Context context, int width, int height) {
        super(context, width, height);
        cloud = context.getResources().getDrawable(R.drawable.cloud7s70x30);
        this.imageWidth = 70;
        this.imageHeight = 30;
        this.setImageCloud(cloud);
        this.setImageHeight(this.imageHeight);
        this.setImageWidth(this.imageWidth);
    }


}
