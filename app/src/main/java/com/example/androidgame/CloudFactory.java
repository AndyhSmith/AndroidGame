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
        clouds = new Cloud[11];
        clouds[0] = new Cloud1(context, width, height);
        clouds[1] = new Cloud5(context, width, height);
        clouds[2] = new Cloud4(context, width, height);
        clouds[3] = new Cloud2(context, width, height);
        clouds[4] = new Cloud3(context, width, height);

        clouds[5] = new Cloud6(context, width, height);
        clouds[6] = new Cloud7(context, width, height);
        clouds[7] = new Cloud8(context, width, height);
        clouds[8] = new Cloud9(context, width, height);
        clouds[9] = new Cloud10(context, width, height);
        clouds[10] = new Cloud11(context, width, height);
    }

    public void createCloud() {

    }

    public void draw(Canvas canvas) {
        for (int i = 0; i < 11; i++) {
            clouds[i].draw(canvas);
        }
    }

    public void update() {
        for (int i = 0; i < 11; i++) {
            clouds[i].update();
        }
    }
}
