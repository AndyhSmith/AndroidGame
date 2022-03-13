package com.example.androidgame;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
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
import androidx.core.content.res.ResourcesCompat;


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
    private Paint text;
    private int score;

    private int highScore;
    private int highScoreLead;
    private int highScoreGravity;

    private Paint settingsPaint;

    private String screen;
    private StartMenu startMenu;
    private HighScoreMenu highScoreMenu;
    private StatMenu statMenu;
    private AchievementsMenu achievementsMenu;
    private SettingsMenu settingsMenu;

    private Background background;
    private CloudFactory cloudFactory;

    private String balloonType;

    // Settings
    private boolean quickAim;
    private boolean showInfo;

    private Message message;




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
                    else if (startMenu.checkPressAchievementsButton(event.getX(), event.getY())) {
                        screen = "achievements";
                        return true;
                    }
                    else if (startMenu.checkPressSettingsButton(event.getX(), event.getY())) {
                        screen = "settings";
                        return true;
                    }
                }
                else if (screen == "play") {
                    Log.d("quickAim", Boolean.toString(quickAim));
                    if (quickAim) {
                        player.updatePosition((double) event.getX(), (double) event.getY());
                        return true;
                    }
                    else if (!quickAim) {
                        player.setStartPosition((double) event.getX(), (double) event.getY());
                        return true;
                    }
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
                    else if (statMenu.checkPressRedBalloonCard(event.getX(), event.getY())) {
                        balloonType = "red";
                        ball.setBalloonType(balloonType);
                        return true;
                    }
                    else if (statMenu.checkPressPurpleBalloonCard(event.getX(), event.getY())) {
                        balloonType = "purple";
                        ball.setBalloonType(balloonType);
                        return true;
                    }
                    else if (statMenu.checkPressTealBalloonCard(event.getX(), event.getY())) {
                        balloonType = "teal";
                        ball.setBalloonType(balloonType);
                        return true;
                    }
                    else if (statMenu.checkPressYellowBalloonCard(event.getX(), event.getY())) {
                        balloonType = "yellow";
                        ball.setBalloonType(balloonType);
                        return true;
                    }
                    else if (statMenu.checkPressLeadBalloonCard(event.getX(), event.getY())) {
                        if (statMenu.getLeadLocked()) {
                            message.postMessage("Complete achievement 'Max Gravity' to unlock");
                            return true;
                        }
                        balloonType = "lead";
                        ball.setBalloonType(balloonType);
                        return true;
                    }
                    else if (statMenu.checkPressGravityBalloonCard(event.getX(), event.getY())) {
                        if (statMenu.getGravityLocked()) {
                            message.postMessage("Complete achievement 'Off The Top' to unlock");
                            return true;
                        }
                        balloonType = "gravity";
                        ball.setBalloonType(balloonType);
                        return true;
                    }
                }
                else if (screen == "achievements") {
                    if (achievementsMenu.checkPressBackButton(event.getX(), event.getY())) {
                        screen = "start";
                        return true;
                    }
                }
                else if (screen == "settings") {
                    if (settingsMenu.checkPressBackButton(event.getX(), event.getY())) {
                        screen = "start";
                        return true;
                    }
                    else if (settingsMenu.CheckPressQuickAimButton(event.getX(), event.getY())) {
                        quickAim = !quickAim;
                        return true;
                    }
                    else if (settingsMenu.CheckPressShowInfoButton(event.getX(), event.getY())) {
                        showInfo = !showInfo;
                        return true;
                    }
                }
            case MotionEvent.ACTION_MOVE:
                if (quickAim) {
                    player.updatePosition((double) event.getX(), (double) event.getY());
                }
                if (!quickAim) {
                    player.setEndPosition((double) event.getX(), (double) event.getY());
                    return true;
                }

        }
        return super.onTouchEvent(event);
    }

    public Game(Context context) {
        super(context);
        getContext();

        settingsPaint = new Paint();
        settingsPaint.setColor(ContextCompat.getColor(getContext(), R.color.black));
        settingsPaint.setTextSize(30);

        // Get Screen Size
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;

        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        gameLoop = new GameLoop(this, surfaceHolder);
        ball = new Ball(getContext(), width / 2,100,50, height);
        player = new Player(getContext(), width / 2, height - 100, 100);
        point = new Collectible(getContext(), width, height, 30);

        startMenu = new StartMenu(getContext(), width, height);
        highScoreMenu = new HighScoreMenu(getContext(), width, height);
        statMenu = new StatMenu(getContext(), width, height);
        achievementsMenu = new AchievementsMenu(getContext(), width, height);
        settingsMenu = new SettingsMenu(getContext(), width, height);

        screen = "start";
        background = new Background(getContext(), screen, width, height);

        cloudFactory = new CloudFactory(getContext(), width, height);

        paintScore = new Paint();
        int colorScore = ContextCompat.getColor(getContext(), R.color.black);
        paintScore.setColor(colorScore);
        paintScore.setTextSize(150);
        paintScore.setTextAlign(Paint.Align.CENTER);
        Typeface customFont = ResourcesCompat.getFont(context, R.font.ka1);
        paintScore.setTypeface(customFont);

        text = new Paint();
        int color2 = ContextCompat.getColor(context, R.color.black);
        text.setColor(color2);
        text.setTextAlign(Paint.Align.CENTER);
        text.setTextSize(40);
        Typeface customFont2 = ResourcesCompat.getFont(context, R.font.november);
        text.setTypeface(customFont2);


        this.highScore = 0;
        this.highScoreLead = 0;
        this.highScoreGravity = 0;

        // Settings
        quickAim = false;

        // Messages
        message = new Message(context, (double) width);





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

//        drawUPS(canvas);
//        drawFPS(canvas);


        if (screen == "play") {
            cloudFactory.draw(canvas);
            player.draw(canvas);
            ball.draw(canvas);
            point.draw(canvas);
            drawScore(canvas);

            if (showInfo) {
                canvas.drawText("Gravity: " + Integer.toString((int)(ball.getGravity() * 100)) + "%", 10, 30, settingsPaint);
            }

        }
        else if (screen == "start") {
            cloudFactory.draw(canvas);
            startMenu.draw(canvas);
            canvas.drawText("High Score: " + Integer.toString((int) highScore), (float) (width / 2), (float) 425, text);


        }
        else if (screen == "highscore") {
            highScoreMenu.draw(canvas, highScore, highScoreLead, highScoreGravity);
        }
        else if (screen == "stat") {
            statMenu.draw(canvas);
        }
        else if (screen == "achievements") {
            achievementsMenu.draw(canvas);
        }
        else if (screen == "settings") {
            settingsMenu.draw(canvas, quickAim, showInfo);
        }
        message.draw(canvas);
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
        Rect textBounds = new Rect();
        paintScore.getTextBounds(Integer.toString(score), 0, Integer.toString(score).length(), textBounds);
        canvas.drawText(Integer.toString(score), width / 2, 200, paintScore);
    }

    public void update() {

        background.update();
        cloudFactory.update();
        message.update();

        if (screen == "play") {
            player.update();
            ball.update(balloonType);
            point.update();

            ball.checkCollision(player);
            if (ball.checkCollision(point)) {
                this.score += 1;
                // achievement handful of points
                if (!achievementsMenu.getHandfulOfPoints()) {
                    if (score >= 5) {
                        message.postMessage("Achievement Complete: Handful Of Points");
                        achievementsMenu.completeHandfulOfPoints();
                    }
                }
            }

            if (ball.checkOutOfBounds(width, height)) {
                player.reset();
                this.screen = "start";
                if (this.score > this.highScore) {
                    this.highScore = score;
                }
                if (balloonType == "gravity" && this.score > this.highScoreGravity) {
                    this.highScoreGravity = score;
                }
                else if (balloonType == "lead" && this.score > this.highScoreLead) {
                    this.highScoreLead = score;
                }
                this.score = 0;
            }

            // Check Achievements
            if (!achievementsMenu.getOffTheTop()) {
                if (ball.checkAchievementOffTheTop()) {
                    message.postMessage("Achievement Complete: Off The Top");
                    achievementsMenu.completeOffTheTop();
                    statMenu.unlockGravity();
                }
            }
            if (!achievementsMenu.getMaxGravity()) {
                if (ball.checkAchievementMaxGravity()) {
                    message.postMessage("Achievement Complete: Max Gravity");
                    achievementsMenu.completeMaxGravity();
                    statMenu.unlockLead();
                }
            }
            if (!achievementsMenu.getTwoBirdsOneStone()) {
                if (ball.checkAchievementTwoBirdsOneStone()) {
                    message.postMessage("Achievement Complete: Three Birds One Stone");
                    achievementsMenu.completeTwoBirdsOneStone();
                }
            }



        }
        if (!achievementsMenu.getBalloonMaster()) {
            if (statMenu.checkAchievementBalloonMaster()) {

                message.postMessage("Achievement Complete: Balloon Master");
                achievementsMenu.completeBalloonMaster();
            }
        }
    }
}
