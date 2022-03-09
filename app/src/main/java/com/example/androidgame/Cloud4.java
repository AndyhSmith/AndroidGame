package com.example.androidgame;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

public class Cloud4 extends Cloud{
    private Drawable cloud;
    private int imageWidth;
    private int imageHeight;

    public Cloud4(Context context, int width, int height) {
        super(context, width, height);
        cloud = context.getResources().getDrawable(R.drawable.cloud4s240x40);
        this.imageWidth = 240;
        this.imageHeight = 40;
        this.setImageCloud(cloud);
        this.setImageHeight(this.imageHeight);
        this.setImageWidth(this.imageWidth);
    }


}
