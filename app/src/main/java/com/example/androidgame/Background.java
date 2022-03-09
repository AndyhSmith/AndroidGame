package com.example.androidgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

public class Background {
    private double width;
    private double height;
    private Paint backgroundSky;

    public Background(Context context, String screen, double width, double height) {
        this.width = width;
        this.height = height;

        backgroundSky = new Paint();
        int color = ContextCompat.getColor(context, R.color.backgroundSky);
        backgroundSky.setColor(color);
    }

    public void draw(Canvas canvas) {
        canvas.drawPaint(backgroundSky);
//        canvas.drawRect(0, 0, (float) width, (float) height, backgroundSky);

    }

    public void update() {
    }
}
