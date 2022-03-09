package com.example.androidgame;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

public class Cloud5 extends Cloud{
    private Drawable cloud;
    private int imageWidth;
    private int imageHeight;

    public Cloud5(Context context, int width, int height) {
        super(context, width, height);
        cloud = context.getResources().getDrawable(R.drawable.cloud5s310x100);
        this.imageWidth = 310;
        this.imageHeight = 100;
        this.setImageCloud(cloud);
        this.setImageHeight(this.imageHeight);
        this.setImageWidth(this.imageWidth);
    }


}
