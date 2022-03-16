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
    private Paint backgroundNightSky;
    private Paint starColor;
    private int numberOfGroundTiles;

    private Drawable[] groundTiles;
    private Drawable[] groundTilesNight;

    private int[] starsX;
    private int[] starsY;

    public Background(Context context, String screen, double width, double height) {
        this.width = width;
        this.height = height;

        backgroundSky = new Paint();
        int color = ContextCompat.getColor(context, R.color.backgroundSky);
        backgroundSky.setColor(color);

        backgroundNightSky = new Paint();
        backgroundNightSky.setColor(ContextCompat.getColor(context, R.color.backgroundNightSky));

        starColor = new Paint();
        starColor.setColor(ContextCompat.getColor(context, R.color.starcolor));


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

        groundTilesNight = new Drawable[numberOfGroundTiles];
        for (int i = 0; i < numberOfGroundTiles; i++) {
            Rect imageBounds2 = new Rect();
            imageBounds2.left = (int) (i * 90);
            imageBounds2.top = (int) height - 60;
            imageBounds2.right = (int) ((i * 90) + 90);
            imageBounds2.bottom = (int) (height);
            Drawable ground = context.getResources().getDrawable(R.drawable.groundnight90x60);
            ground.setBounds(imageBounds2);
            groundTilesNight[i] = ground;
        }

        starsX = new int[10];
        starsY = new int[10];
        for (int i = 0; i < 10; i++) {
            starsX[i] = (int)(Math.random() * width);
            starsY[i] = (int)(Math.random() * height);
        }


    }

    public void draw(Canvas canvas, String balloonType) {
        if (balloonType == "moon") {
            canvas.drawPaint(backgroundNightSky);
            for (int i = 0; i < numberOfGroundTiles; i++ ) {
                groundTilesNight[i].draw(canvas);
            }
            // draw stars
            for (int i = 0 ; i < 10; i++) {
                canvas.drawRect(starsX[i], starsY[i], starsX[i] + 20, starsY[i] + 20, starColor);
            }
        } else {
            canvas.drawPaint(backgroundSky);
            for (int i = 0; i < numberOfGroundTiles; i++ ) {
                groundTiles[i].draw(canvas);
            }
        }


    }

    public void update() {
    }
}
