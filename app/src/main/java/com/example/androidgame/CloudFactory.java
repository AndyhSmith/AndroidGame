package com.example.androidgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

public class CloudFactory {
    private int width;
    private int height;

    private Cloud[] clouds;

    public CloudFactory(Context context, int width, int height) {
        clouds = new Cloud[5];
        for (int i = 0; i < 5; i++) {
            clouds[i] = new Cloud(context, width, height);
        }


    }

    public void createCloud() {

    }

    public void draw(Canvas canvas) {
        for (int i = 0; i < 5; i++) {
            clouds[i].draw(canvas);
        }
    }

    public void update() {
        for (int i = 0; i < 5; i++) {
            clouds[i].update();
        }
    }
}
