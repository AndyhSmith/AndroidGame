package com.example.androidgame;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

public class Cloud8 extends Cloud{
    private Drawable cloud;
    private int imageWidth;
    private int imageHeight;

    public Cloud8(Context context, int width, int height) {
        super(context, width, height);
        cloud = context.getResources().getDrawable(R.drawable.cloud8s180x50);
        this.imageWidth = 180;
        this.imageHeight = 50;
        this.setImageCloud(cloud);
        this.setImageHeight(this.imageHeight);
        this.setImageWidth(this.imageWidth);
    }


}
