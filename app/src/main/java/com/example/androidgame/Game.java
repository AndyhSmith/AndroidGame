package com.example.androidgame;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;


/**
 * Game mages all objects in the game and is responsible for updating all states and rendering all objects to the screen
 */
class Game extends SurfaceView implements SurfaceHolder.Callback {
    private final Player player;
    private final Ball ball;
    private final Collectible point;
    private GameLoop gameLoop;

    private int height;
    private int width;

    private Paint paintScore;
    private int score;

    private String screen;
    private StartMenu startMenu;
    private HighScoreMenu highScoreMenu;
    private StatMenu statMenu;

    private Background background;
    private CloudFactory cloudFactory;




    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // Handle Touch Events
        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (screen == "start") {
                    if (startMenu.checkPressStartButton(event.getX(), event.getY())) {
                        screen = "play";
                        return true;
                    }
                    else if (startMenu.checkPressHighScoreButton(event.getX(), event.getY())) {
                        screen = "highscore";
                        return true;
                    }
                    else if (startMenu.checkPressStatButton(event.getX(), event.getY())) {
                        screen = "stat";
                        return true;
                    }
                }
                else if (screen == "play") {
                    player.setStartPosition((double) event.getX(), (double) event.getY());
                    return true;
                }
                else if (screen == "highscore") {
                    if (highScoreMenu.checkPressBackButton(event.getX(), event.getY())) {
                        screen = "start";
                        return true;
                    }
                }
                else if (screen == "stat") {
                    if (statMenu.checkPressBackButton(event.getX(), event.getY())) {
                        screen = "start";
                        return true;
                    }
                }
            case MotionEvent.ACTION_MOVE:
                player.setEndPosition((double) event.getX(), (double) event.getY());
                return true;
        }
        return super.onTouchEvent(event);
    }

    public Game(Context context) {
        super(context);
        getContext();


        // Get Screen Size
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;

        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        gameLoop = new GameLoop(this, surfaceHolder);
        ball = new Ball(getContext(), width / 2,100,50);
        player = new Player(getContext(), width / 2, height - 100, 100);
        point = new Collectible(getContext(), width, height, 30);

        startMenu = new StartMenu(getContext(), width, height);
        highScoreMenu = new HighScoreMenu(getContext(), width, height);
        statMenu = new StatMenu(getContext(), width, height);

        screen = "start";
        background = new Background(getContext(), screen, width, height);

        cloudFactory = new CloudFactory(getContext(), width, height);

        paintScore = new Paint();
        int colorScore = ContextCompat.getColor(getContext(), R.color.magenta);
        paintScore.setColor(colorScore);
        paintScore.setTextSize(100);



        setFocusable(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        gameLoop.startLoop();

    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

    }

    @Override
    public void draw(Canvas canvas) {



        super.draw(canvas);
        background.draw(canvas);
        drawUPS(canvas);
        drawFPS(canvas);

        if (screen == "play") {
            cloudFactory.draw(canvas);
            player.draw(canvas);
            ball.draw(canvas);
            point.draw(canvas);
            drawScore(canvas);

        }
        else if (screen == "start") {
            cloudFactory.draw(canvas);
            startMenu.draw(canvas);

        }
        else if (screen == "highscore") {
            highScoreMenu.draw(canvas);
        }
        else if (screen == "stat") {
            statMenu.draw(canvas);
        }
    }

    public void drawUPS(Canvas canvas) {
        String averageUPS = Double.toString(gameLoop.getAverageUPS());
        Paint paint = new Paint();
        int color = ContextCompat.getColor(getContext(), R.color.magenta);
        paint.setColor(color);
        paint.setTextSize(30);
        canvas.drawText("UPS: " + averageUPS, 10, 60, paint);
    }

    public void drawFPS(Canvas canvas) {
        String averageFPS = Double.toString(gameLoop.getAverageFPS());
        Paint paint = new Paint();
        int color = ContextCompat.getColor(getContext(), R.color.black);
        paint.setColor(color);
        paint.setTextSize(30);
        canvas.drawText("FPS: " + averageFPS, 10, 30, paint);

    }

    public void drawScore(Canvas canvas) {
        canvas.drawText(Integer.toString(score), width / 2, 100, paintScore);
    }

    public void update() {

        background.update();
        cloudFactory.update();

        if (screen == "play") {
            player.update();
            ball.update();
            point.update();

            ball.checkCollision(player);
            if (ball.checkCollision(point)) {
                this.score += 1;
            }

            if (ball.checkOutOfBounds(width, height)) {
                this.screen = "start";
                this.score = 0;
            }


        }
    }
}
