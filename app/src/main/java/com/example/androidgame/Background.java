package com.example.androidgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

import androidx.core.content.ContextCompat;

public class Background {
    private int width;
    private int height;

    private Paint backgroundSky;
    private Paint backgroundNightSky;
    private Paint starColor;

    private Paint glitchColor;
    private Paint glitchColor1;
    private Paint glitchColor2;

    private Paint toxic1;
    private Paint toxic2;
    private Paint toxic3;




    private CloudFactory cloudFactory;


    private int numberOfGroundTiles;

    private Drawable[] groundTiles;
    private Drawable[] groundTilesNight;
    private Drawable[] caution;
    private Drawable[] magicGrass;

    private int[] starsX;
    private int[] starsY;

    private int[] toxicBubbleX;
    private int[] toxicBubbleY;
    private int[] toxicBubbleType;
    private int[] toxicBubbleSize;
    private int numberOfBubbles;

    private int[] voidStarsX;
    private int[] voidStarsY;
    private int numberOfVoidStars;
    private Paint voidStarColor;
    private Paint voidBackground;

    private Paint lava;
    private Paint darkGrey;
    private double[] sparksX;
    private double[] sparksY;
    private float[] sparksXSpeed;
    private float[] sparksYSpeed;
    private int numberOfLavaSparks;

    private Paint darkBlue;
    private Paint rain;

    private Paint backgroundMagic;
    private Drawable magicBalloon;

    public Background(Context context, String screen, int width, int height) {
        this.width = width;
        this.height = height;

        backgroundSky = new Paint();
        int color = ContextCompat.getColor(context, R.color.backgroundSky);
        backgroundSky.setColor(color);

        backgroundNightSky = new Paint();
        backgroundNightSky.setColor(ContextCompat.getColor(context, R.color.backgroundNightSky));

        glitchColor = new Paint();
        glitchColor.setColor(Color.parseColor("#EEEEEE"));
//        glitchColor.setColor(ContextCompat.getColor(context, R.color.white));

        starColor = new Paint();
        starColor.setColor(ContextCompat.getColor(context, R.color.starcolor));

        glitchColor1 = new Paint();
        glitchColor1.setColor(ContextCompat.getColor(context, R.color.pink));

        glitchColor2 = new Paint();
        glitchColor2.setColor(ContextCompat.getColor(context, R.color.lightblue));

        toxic1 = new Paint();
        toxic1.setColor(ContextCompat.getColor(context, R.color.greentoxic1));
        toxic2 = new Paint();
        toxic2.setColor(ContextCompat.getColor(context, R.color.greentoxic2));
        toxic3 = new Paint();
        toxic3.setColor(ContextCompat.getColor(context, R.color.greentoxic3));

        voidBackground = new Paint();
        voidBackground.setColor(ContextCompat.getColor(context, R.color.black));
        voidStarColor = new Paint();
        voidStarColor.setColor(ContextCompat.getColor(context, R.color.lightgrey));

        lava = new Paint();
        lava.setColor(ContextCompat.getColor(context, R.color.orange));
        darkGrey = new Paint();
        darkGrey.setColor(ContextCompat.getColor(context, R.color.lightgrey));

        darkBlue = new Paint();
        darkBlue.setColor(ContextCompat.getColor(context, R.color.darkblue));
        rain = new Paint();
        rain.setColor(ContextCompat.getColor(context, R.color.rainblue));

        magicBalloon = context.getResources().getDrawable(R.drawable.balloonmagic84x100);
        backgroundMagic = new Paint();
        backgroundMagic.setColor(ContextCompat.getColor(context, R.color.purple));

        numberOfGroundTiles = (int) (width / 90) + 1;

        Rect imageBounds = new Rect();
        imageBounds.top = (int) height - 60;
        imageBounds.bottom = (int) (height);

        groundTiles = new Drawable[numberOfGroundTiles];
        for (int i = 0; i < numberOfGroundTiles; i++) {
            Drawable ground = context.getResources().getDrawable(R.drawable.ground90x60);
            imageBounds.left = (int) (i * 90);
            imageBounds.right = (int) ((i * 90) + 90);
            ground.setBounds(imageBounds);
            groundTiles[i] = ground;
        }

        groundTilesNight = new Drawable[numberOfGroundTiles];
        for (int i = 0; i < numberOfGroundTiles; i++) {
            Drawable ground = context.getResources().getDrawable(R.drawable.groundnight90x60);
            imageBounds.left = (int) (i * 90);
            imageBounds.right = (int) ((i * 90) + 90);
            ground.setBounds(imageBounds);
            groundTilesNight[i] = ground;
        }

        caution = new Drawable[numberOfGroundTiles];
        for (int i = 0; i < numberOfGroundTiles; i++) {
            Drawable tile = context.getResources().getDrawable(R.drawable.groundcaution90x60);
            imageBounds.left = (int) (i * 90);
            imageBounds.right = (int) ((i * 90) + 90);
            tile.setBounds(imageBounds);
            caution[i] = tile;
        }

        magicGrass = new Drawable[numberOfGroundTiles];
        for (int i = 0; i < numberOfGroundTiles; i++) {
            Drawable tile = context.getResources().getDrawable(R.drawable.groundmagic90x60);
            imageBounds.left = (int) (i * 90);
            imageBounds.right = (int) ((i * 90) + 90);
            tile.setBounds(imageBounds);
            magicGrass[i] = tile;
        }

        // Set Up Stars
        starsX = new int[10];
        starsY = new int[10];
        for (int i = 0; i < 10; i++) {
            starsX[i] = (int)(Math.random() * width);
            starsY[i] = (int)(Math.random() * height);
        }

        // Set Up Toxic Bubbles
        numberOfBubbles = 10;
        toxicBubbleX = new int[numberOfBubbles];
        toxicBubbleY = new int[numberOfBubbles];
        toxicBubbleType = new int[numberOfBubbles];
        toxicBubbleSize = new int[numberOfBubbles];
        for (int i = 0; i < numberOfBubbles; i++) {
            toxicBubbleX[i] = (int)(Math.random() * width);
            toxicBubbleY[i] = (int)(Math.random() * height);
            toxicBubbleType[i] = (int)(Math.random() * 2);
            toxicBubbleSize[i] = (int)(Math.random() * 15 + 10);
        }

        // Set Up Void Stars
        numberOfVoidStars = 250;
        voidStarsX = new int[numberOfVoidStars];
        voidStarsY = new int[numberOfVoidStars];
        for (int i = 0; i < numberOfVoidStars; i++) {
            voidStarsX[i] = (int)(Math.random() * width);
            voidStarsY[i] = (int)(Math.random() * height);
        }

        // Set Up Lava Sparks
        numberOfLavaSparks = 20;
        sparksX = new double[numberOfLavaSparks];
        sparksY = new double[numberOfLavaSparks];
        sparksXSpeed = new float[numberOfLavaSparks];
        sparksYSpeed = new float[numberOfLavaSparks];
        for (int i = 0; i < numberOfLavaSparks; i++) {
            sparksX[i] = Math.random() * width;
            sparksY[i] = Math.random() * height;
            sparksXSpeed[i] = (float)((Math.random() - .5) * 4);
            sparksYSpeed[i] = (float)((Math.random() - .5) * 4);
        }
        cloudFactory = new CloudFactory(context, width, height);

    }

    public void draw(Canvas canvas, String balloonType, double ballHeight) {
        if (balloonType == "moon") {
            canvas.drawPaint(backgroundNightSky);

            // draw stars
            for (int i = 0 ; i < 10; i++) {
                canvas.drawRect(starsX[i], starsY[i], starsX[i] + 20, starsY[i] + 20, starColor);
            }
            for (int i = 0; i < numberOfGroundTiles; i++ ) {
                groundTilesNight[i].draw(canvas);
            }
        }
        else if (balloonType == "glitch") {
            canvas.drawPaint(glitchColor);
            for (int i = 0 ; i < 10; i++) {
                if (Math.random() < .5) {
                    canvas.drawRect(0, starsY[i], width, starsY[i] + 3, glitchColor1);
                } else {
                    canvas.drawRect(0, starsY[i], width, starsY[i] + 3, glitchColor2);
                }

                double rand = (Math.random() - .5) * 60;
                if (Math.random() < .5) {
                    canvas.drawRect(0, (float)(starsY[i] + rand), width, (float)(starsY[i] + 2 + rand), glitchColor1);
                } else {
                    canvas.drawRect(0, (float)(starsY[i] + rand), width, (float)(starsY[i] + 2 + rand), glitchColor2);
                }

            }
        }
        else if (balloonType == "radiation") {
            canvas.drawPaint(toxic1);
            for (int i = 0; i < numberOfGroundTiles; i++ ) {
                caution[i].draw(canvas);
            }
            // draw bubbles
            for (int i = 0 ; i < numberOfBubbles; i++) {
                if (toxicBubbleType[i] == 0) {
                    canvas.drawCircle(toxicBubbleX[i], toxicBubbleY[i], toxicBubbleSize[i],  toxic2);
                } else {
                    canvas.drawCircle(toxicBubbleX[i], toxicBubbleY[i], toxicBubbleSize[i], toxic3);
                }
                toxicBubbleY[i] -= 1;
                if (toxicBubbleY[i] < -100) {
                    toxicBubbleY[i] = (int)(height + 100 + Math.random() * 50);
                    starsX[i] = (int)(Math.random() * width);
                }
            }
        }
        else if(balloonType == "void") {
            canvas.drawPaint(voidBackground);
            // draw void stars
            for (int i = 0 ; i < numberOfVoidStars; i++) {
                canvas.drawRect(voidStarsX[i], voidStarsY[i], voidStarsX[i] + 10, voidStarsY[i] + 10, voidStarColor);
            }
        }
        else if (balloonType == "abyss") {
            canvas.drawPaint(darkGrey);
            // draw void stars
            for (int i = 0 ; i < numberOfLavaSparks; i++) {
                canvas.drawRect((float)sparksX[i], (float)sparksY[i], (float)(sparksX[i] + 10), (float)(sparksY[i] + 10), lava);
                sparksX[i] += sparksXSpeed[i];
                sparksY[i] += sparksYSpeed[i];
                sparksXSpeed[i] += Math.random() - .5;
                sparksYSpeed[i] += Math.random() - .6;
                if (sparksX[i] < -30 || sparksX[i] > width + width || sparksY[i] < -20) {
                    sparksY[i] = height + 20;
                    sparksX[i] = Math.random() * width;
                    sparksYSpeed[i] = (float) (Math.random() * 4);
                    sparksXSpeed[i] = (float) (Math.random() * 2);
                }

            }
        }
        else if (balloonType == "quantum") {
            canvas.drawPaint(darkGrey);
            for (int i = 0 ; i < (int)(numberOfVoidStars / 3); i++) {
                if (Math.random() < .33) {
                    canvas.drawRect(voidStarsX[i], voidStarsY[i], voidStarsX[i] + 10, voidStarsY[i] + 10, toxic1);
                } else if (Math.random() < .5) {
                    canvas.drawRect(voidStarsX[i], voidStarsY[i], voidStarsX[i] + 10, voidStarsY[i] + 10, voidBackground);
                } else {
                    canvas.drawRect(voidStarsX[i], voidStarsY[i], voidStarsX[i] + 10, voidStarsY[i] + 10, darkGrey);
                }
                voidStarsX[i] = (int)(Math.random() * width);
                voidStarsY[i] = (int)(Math.random() * height);
            }
        }
        else if (balloonType == "glider") {
            canvas.drawPaint(darkBlue);

            for (int i = 0 ; i < numberOfLavaSparks; i++) {
                canvas.drawRect((float)sparksX[i], (float)sparksY[i], (float)(sparksX[i] + 3), (float)(sparksY[i] + 15), rain);
                sparksY[i] += 20;
                if (sparksY[i] > height + 30) {
                    sparksY[i] = -20;
                    sparksX[i] = Math.random() * width;
                }
            }
            for (int i = 0; i < numberOfGroundTiles; i++ ) {
                groundTilesNight[i].draw(canvas);
            }

        }
        else if (balloonType == "magic") {
            canvas.drawPaint(backgroundMagic);
            for (int i = 0 ; i < 5; i++) {
                Rect imageBounds = new Rect();
                imageBounds.left = (int) (sparksX[i] - 42);
                imageBounds.top = (int) (sparksY[i] - 50);
                imageBounds.right = (int) (sparksX[i] + 42);
                imageBounds.bottom = (int) (sparksY[i] + 50);
                magicBalloon.setBounds(imageBounds);
                magicBalloon.draw(canvas);

                sparksX[i] += sparksXSpeed[i];
                sparksY[i] += sparksYSpeed[i];
                sparksXSpeed[i] += Math.random() - .5;
                sparksYSpeed[i] += Math.random() - .52;
                if (sparksX[i] < -30 || sparksX[i] > width + width || sparksY[i] < -20) {
                    sparksY[i] = height + 20;
                    sparksX[i] = Math.random() * width;
                    sparksYSpeed[i] = (float) (Math.random() * 3);
                    sparksXSpeed[i] = (float) (Math.random() * 2);
                }

            }
            for (int i = 0; i < numberOfGroundTiles; i++ ) {
                magicGrass[i].draw(canvas);
            }

        }
        else if (balloonType == "light") {
            Paint color = new Paint();
            double ballH = 255 - (ballHeight * 255) / height;
            color.setColor(Color.rgb((int)ballH, (int)ballH, (int)ballH));
            canvas.drawPaint(color);
        } else if (balloonType == "purple") {
            Paint color = new Paint();
            color.setColor(Color.parseColor("#aef2c3"));
            canvas.drawPaint(color);

            for (int i = 0; i < numberOfGroundTiles; i++ ) {
                groundTiles[i].draw(canvas);
            }
            cloudFactory.draw(canvas);

        } else if (balloonType == "teal") {
            Paint color = new Paint();
            color.setColor(Color.parseColor("#DAAB8A"));
            canvas.drawPaint(color);

            for (int i = 0; i < numberOfGroundTiles; i++ ) {
                groundTiles[i].draw(canvas);
            }
            cloudFactory.draw(canvas);
        } else if (balloonType == "yellow") {
            Paint color = new Paint();
            color.setColor(Color.parseColor("#EEBED8"));
            canvas.drawPaint(color);

            for (int i = 0; i < numberOfGroundTiles; i++ ) {
                groundTiles[i].draw(canvas);
            }
            cloudFactory.draw(canvas);
        }
        else {
            canvas.drawPaint(backgroundSky);
            for (int i = 0; i < numberOfGroundTiles; i++ ) {
                groundTiles[i].draw(canvas);
            }
            cloudFactory.draw(canvas);
        }
    }

    public void update() {
        cloudFactory.update();
    }
}
