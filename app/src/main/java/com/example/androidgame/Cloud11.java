package com.example.androidgame;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

public class Cloud11 extends Cloud{
    private Drawable cloud;
    private int imageWidth;
    private int imageHeight;

    public Cloud11(Context context, int width, int height) {
        super(context, width, height);
        cloud = context.getResources().getDrawable(R.drawable.cloud11s40x20);
        this.imageWidth = 40;
        this.imageHeight = 20;
        this.setImageCloud(cloud);
        this.setImageHeight(this.imageHeight);
        this.setImageWidth(this.imageWidth);
    }


}
