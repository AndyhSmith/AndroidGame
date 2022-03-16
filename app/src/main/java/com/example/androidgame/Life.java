package com.example.androidgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

public class Life {

    private Context context;
    private int width;
    private Drawable heart;

    public Life(Context context, int width) {

        int padding = 20;
        heart = context.getResources().getDrawable(R.drawable.heart72x64);

        Rect imageBounds = new Rect();
        imageBounds.left = (int) width - 72 - padding;
        imageBounds.top = (int) padding;
        imageBounds.right = (int) (width - padding);
        imageBounds.bottom = (int) (padding + 64);
        heart.setBounds(imageBounds);
    }

    public void draw(Canvas canvas) {
        heart.draw(canvas);
    }
}
