package com.example.androidgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

import androidx.core.content.ContextCompat;

public class Background {
    private double width;
    private double height;
    private Paint backgroundSky;
    private int numberOfGroundTiles;

    private Drawable[] groundTiles;

    public Background(Context context, String screen, double width, double height) {
        this.width = width;
        this.height = height;

        backgroundSky = new Paint();
        int color = ContextCompat.getColor(context, R.color.backgroundSky);
        backgroundSky.setColor(color);

        numberOfGroundTiles = (int) (width / 90) + 1;

        groundTiles = new Drawable[numberOfGroundTiles];

        for (int i = 0; i < numberOfGroundTiles; i++) {
            Rect imageBounds = new Rect();
            imageBounds.left = (int) (i * 90);
            imageBounds.top = (int) height - 60;
            imageBounds.right = (int) ((i * 90) + 90);
            imageBounds.bottom = (int) (height);
            Drawable ground = context.getResources().getDrawable(R.drawable.ground90x60);
            ground.setBounds(imageBounds);
            groundTiles[i] = ground;
        }




    }

    public void draw(Canvas canvas) {
        canvas.drawPaint(backgroundSky);
        for (int i = 0; i < numberOfGroundTiles; i++ ) {
            groundTiles[i].draw(canvas);
        }
    }

    public void update() {
    }
}
