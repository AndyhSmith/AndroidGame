package com.example.androidgame;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Build;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;


/**
 * Game mages all objects in the game and is responsible for updating all states and rendering all objects to the screen
 */
class Game extends SurfaceView implements SurfaceHolder.Callback {
    private Context context;
    private final Player player;
    private final Ball ball;
    private final Collectible point;
    private final Item itemBoost;
    private GameLoop gameLoop;

    private Life life;
    private Boolean extraLife;

    private int height;
    private int width;

    private Paint paintScore;
    private Paint text;
    private int score;

    private int highScore;
    private int highScoreNormal;
    private int highScoreLead;
    private int highScoreGravity;
    private int highScoreMoon;
    private int highScoreGlitch;
    private int highScoreHeart;
    private int highScoreToxic;

    private int highScoreVoid;
    private int highScoreLight;
    private int highScoreQuantum;
    private int highScorePicasso;
    private int highScoreGlider;
    private int highScoreMagic;
    private int highScoreMagnet;
    private int highScoreAbyss;

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
    private boolean backgroundMusic;
    private boolean sounds;

    private Message message;

    private SharedPreferences sharedPref;
    private MediaPlayer click;
    private MediaPlayer music1;





    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // Handle Touch Events
        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (screen == "start") {
                    if (startMenu.checkPressStartButton(event.getX(), event.getY())) {
                        playClick();
                        screen = "play";
                        return true;
                    }
                    else if (startMenu.checkPressHighScoreButton(event.getX(), event.getY())) {
                        playClick();
                        screen = "highscore";
                        return true;
                    }
                    else if (startMenu.checkPressStatButton(event.getX(), event.getY())) {
                        playClick();
                        screen = "stat";
                        return true;
                    }
                    else if (startMenu.checkPressAchievementsButton(event.getX(), event.getY())) {
                        playClick();
                        screen = "achievements";
                        return true;
                    }
                    else if (startMenu.checkPressSettingsButton(event.getX(), event.getY())) {
                        playClick();
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
                        playClick();
                        screen = "start";
                        return true;
                    }
                }
                else if (screen == "stat") {
                    if (statMenu.checkPressBackButton(event.getX(), event.getY())) {
                        playClick();
                        screen = "start";
                        return true;
                    }
                    if (statMenu.checkPressNextPageButton(event.getX(), event.getY())) {
                        playClick();
                        return true;
                    }
                    if (statMenu.checkPressPreviousPageButton(event.getX(), event.getY())) {
                        playClick();
                        return true;
                    }
                    else if (statMenu.checkPressRedBalloonCard(event.getX(), event.getY())) {
                        balloonType = "red";
                        playClick();
                        ball.setBalloonType(balloonType);
                        return true;
                    }
                    else if (statMenu.checkPressPurpleBalloonCard(event.getX(), event.getY())) {
                        balloonType = "purple";
                        playClick();
                        ball.setBalloonType(balloonType);
                        return true;
                    }
                    else if (statMenu.checkPressTealBalloonCard(event.getX(), event.getY())) {
                        balloonType = "teal";
                        playClick();
                        ball.setBalloonType(balloonType);
                        return true;
                    }
                    else if (statMenu.checkPressYellowBalloonCard(event.getX(), event.getY())) {
                        balloonType = "yellow";
                        playClick();
                        ball.setBalloonType(balloonType);
                        return true;
                    }
                    else if (statMenu.checkPressLeadBalloonCard(event.getX(), event.getY())) {
                        if (statMenu.getLeadLocked()) {
                            message.postMessage("Complete achievement 'Max Gravity' to unlock");
                            return true;
                        }
                        balloonType = "lead";
                        playClick();
                        ball.setBalloonType(balloonType);
                        return true;
                    }
                    else if (statMenu.checkPressGravityBalloonCard(event.getX(), event.getY())) {
                        if (statMenu.getGravityLocked()) {
                            message.postMessage("Complete achievement 'Off The Top' to unlock");
                            return true;
                        }
                        balloonType = "gravity";
                        playClick();
                        ball.setBalloonType(balloonType);
                        return true;
                    }
                    else if (statMenu.checkPressMoonBalloonCard(event.getX(), event.getY())) {
                        if (statMenu.getMoonLocked()) {
                            message.postMessage("Complete achievement 'Fortnight' to unlock");
                            return true;
                        }
                        balloonType = "moon";
                        playClick();
                        ball.setBalloonType(balloonType);
                        return true;
                    }
                    else if (statMenu.checkPressGlitchBalloonCard(event.getX(), event.getY())) {
                        if (statMenu.getGlitchLocked()) {
                            message.postMessage("Complete achievement 'Handful Of Points' to unlock");
                            return true;
                        }
                        balloonType = "glitch";
                        playClick();
                        ball.setBalloonType(balloonType);
                        return true;
                    }
                    else if (statMenu.checkPressRadiationBalloonCard(event.getX(), event.getY())) {
                        if (statMenu.getRadiationLocked()) {
                            message.postMessage("Complete achievement 'Glitch Fallout' to unlock");
                            return true;
                        }
                        balloonType = "radiation";
                        playClick();
                        ball.setBalloonType(balloonType);
                        return true;
                    }
                    else if (statMenu.checkPressHeartBalloonCard(event.getX(), event.getY())) {
                        if (statMenu.getHeartLocked()) {
                            message.postMessage("Complete achievement 'Extra Life' to unlock");
                            return true;
                        }
                        balloonType = "heart";
                        playClick();
                        extraLife = true;
                        ball.setBalloonType(balloonType);
                        return true;
                    }

                    else if (statMenu.checkPressVoidBalloonCard(event.getX(), event.getY())) {
                        if (statMenu.getVoidLocked()) {
                            message.postMessage("Complete achievement 'End Of Reality' to unlock");
                            return true;
                        }
                        balloonType = "void";
                        playClick();
                        ball.setBalloonType(balloonType);
                        return true;
                    }
                    else if (statMenu.checkPressLightBalloonCard(event.getX(), event.getY())) {
                        if (statMenu.getLightLocked()) {
                            message.postMessage("Complete achievement 'Moon Light' to unlock");
                            return true;
                        }
                        balloonType = "light";
                        playClick();
                        ball.setBalloonType(balloonType);
                        return true;
                    }
                    else if (statMenu.checkPressQuantumBalloonCard(event.getX(), event.getY())) {
                        if (statMenu.getQuantumLocked()) {
                            message.postMessage("Complete achievement 'Splitting Light' to unlock");
                            return true;
                        }
                        balloonType = "quantum";
                        playClick();
                        ball.setBalloonType(balloonType);
                        return true;
                    }
                    else if (statMenu.checkPressPicassoBalloonCard(event.getX(), event.getY())) {
                        if (statMenu.getPicassoLocked()) {
                            message.postMessage("Complete achievement 'Visionary' to unlock");
                            return true;
                        }
                        balloonType = "picasso";
                        playClick();
                        ball.setBalloonType(balloonType);
                        return true;
                    }
                    else if (statMenu.checkPressGliderBalloonCard(event.getX(), event.getY())) {
                        if (statMenu.getGliderLocked()) {
                            message.postMessage("Complete achievement 'Exodus' to unlock");
                            return true;
                        }
                        balloonType = "glider";
                        playClick();
                        ball.setBalloonType(balloonType);
                        return true;
                    }
                    else if (statMenu.checkPressMagicBalloonCard(event.getX(), event.getY())) {
                        if (statMenu.getMagicLocked()) {
                            message.postMessage("Complete achievement 'Mystical' to unlock");
                            return true;
                        }
                        balloonType = "magic";
                        playClick();
                        ball.setBalloonType(balloonType);
                        return true;
                    }
                    else if (statMenu.checkPressMagnetBalloonCard(event.getX(), event.getY())) {
                        if (statMenu.getMagnetLocked()) {
                            message.postMessage("Complete achievement 'Forces' to unlock");
                            return true;
                        }
                        balloonType = "magnet";
                        playClick();
                        ball.setBalloonType(balloonType);
                        return true;
                    }
                    else if (statMenu.checkPressAbyssBalloonCard(event.getX(), event.getY())) {
                        if (statMenu.getAbyssLocked()) {
                            message.postMessage("Complete achievement 'The Creature' to unlock");
                            return true;
                        }
                        balloonType = "abyss";
                        playClick();
                        ball.setBalloonType(balloonType);
                        return true;
                    }
                }
                else if (screen == "achievements") {
                    if (achievementsMenu.checkPressBackButton(event.getX(), event.getY())) {
                        screen = "start";
                        playClick();
                        return true;
                    }
                    if (achievementsMenu.checkPressNextPageButton(event.getX(), event.getY())) {
                        playClick();
                        return true;
                    }
                    if (achievementsMenu.checkPressPreviousPageButton(event.getX(), event.getY())) {
                        playClick();
                        return true;
                    }
                }
                else if (screen == "settings") {
                    if (settingsMenu.checkPressBackButton(event.getX(), event.getY())) {
                        screen = "start";
                        playClick();
                        return true;
                    }
                    else if (settingsMenu.CheckPressQuickAimButton(event.getX(), event.getY())) {
                        quickAim = !quickAim;
                        playClick();
                        saveData();
                        return true;
                    }
                    else if (settingsMenu.CheckPressShowInfoButton(event.getX(), event.getY())) {
                        showInfo = !showInfo;
                        playClick();
                        saveData();
                        return true;
                    }
                    else if (settingsMenu.CheckPressBackgroundMusicButton(event.getX(), event.getY())) {
                        backgroundMusic = !backgroundMusic;
                        if (backgroundMusic) {
                            startBackgroundMusic();
                        } else {
                            endBackgroundMusic();
                        }
                        playClick();
                        saveData();
                        return true;
                    }

                    else if (settingsMenu.CheckPressSoundsButton(event.getX(), event.getY())) {
                        sounds = !sounds;
                        playClick();
                        saveData();
                        return true;
                    }
                    int resetCounter = settingsMenu.CheckPressResetDataButton(event.getX(), event.getY());
                    if (resetCounter != 10 && resetCounter > 0) {
                        playClick();
                    }
                    if (resetCounter <= 0) {
                        resetData();
                        playClick();
                        saveData();
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

    public void playClick() {
        if (sounds) {
            click.start();
        }
    }

    public Game(Context context, SharedPreferences sharedPref) {
        super(context);
        this.sharedPref = sharedPref;


        settingsPaint = new Paint();
        settingsPaint.setColor(ContextCompat.getColor(getContext(), R.color.black));
        settingsPaint.setTextSize(30);

        // Get Screen Size
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;

        Log.d("size", Integer.toString(height) + "," + Integer.toString(width));

        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        gameLoop = new GameLoop(this, surfaceHolder);
        ball = new Ball(getContext(), width / 2,100,50, height);
        player = new Player(getContext(), width / 2, height - 100, 100);
        point = new Collectible(getContext(), width, height, 30);
        itemBoost = new Item(getContext(), width, height, 30);

        life = new Life(getContext(), width);
        extraLife = false;

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
        this.highScoreNormal = 0;
        this.highScoreLead = 0;
        this.highScoreGravity = 0;
        this.highScoreMoon = 0;
        this.highScoreGlitch = 0;
        this.highScoreHeart = 0;
        this.highScoreToxic = 0;

        this.highScoreVoid = 0;
        this.highScoreLight = 0;
        this.highScoreQuantum = 0;
        this.highScorePicasso = 0;
        this.highScoreGlider = 0;
        this.highScoreMagic = 0;
        this.highScoreMagnet = 0;
        this.highScoreAbyss = 0;

        // Settings
        quickAim = false;
        backgroundMusic = true;
        sounds = true;

        // Messages
        message = new Message(context, (double) width);

        loadData();

        // Sound
        click = MediaPlayer.create(context, R.raw.click);
        music1 = MediaPlayer.create(context, R.raw.music1);
        startBackgroundMusic();


        balloonType = "red";


        setFocusable(true);
    }

    public void startBackgroundMusic() {
        if (backgroundMusic) {
            Log.d("music", Boolean.toString(backgroundMusic));
            music1.start();
            music1.setLooping(true);
        }
    }

    public void endBackgroundMusic() {
        music1.pause();
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
        background.draw(canvas, balloonType);

//        drawUPS(canvas);
//        drawFPS(canvas);


        if (screen == "play") {
            if (balloonType != "moon") {
                cloudFactory.draw(canvas);
            }
            player.draw(canvas);
            ball.draw(canvas, balloonType);
            if (extraLife) {
                life.draw(canvas);
            }


            point.draw(canvas);
            itemBoost.draw(canvas);
            drawScore(canvas);

            if (showInfo) {
                canvas.drawText("Gravity: " + Integer.toString((int)(ball.getGravity() * 100)) + "%", 10, 30, settingsPaint);
                canvas.drawText("Streak: " + Integer.toString((int)(ball.getStreak())), 10, 60, settingsPaint);
            }

        }
        else if (screen == "start") {
            if (balloonType != "moon") {
                cloudFactory.draw(canvas);
            }

            startMenu.draw(canvas);
            canvas.drawText("High Score: " + Integer.toString((int) highScore), (float) (width / 2), (float) 425, text);


        }
        else if (screen == "highscore") {
            highScoreMenu.draw(canvas, highScore, highScoreLead, highScoreGravity, highScoreMoon, highScoreGlitch, highScoreHeart, highScoreToxic,
                    highScoreVoid, highScoreLight, highScoreQuantum, highScorePicasso, highScoreGlider, highScoreMagic, highScoreMagnet, highScoreAbyss);
        }
        else if (screen == "stat") {
            statMenu.draw(canvas);
        }
        else if (screen == "achievements") {
            achievementsMenu.draw(canvas);
        }
        else if (screen == "settings") {
            settingsMenu.draw(canvas, quickAim, showInfo, sounds, backgroundMusic);
        }
        message.draw(canvas);
    }

    public void drawUPS(Canvas canvas) {
        String averageUPS = Double.toString(gameLoop.getAverageUPS());
        Paint paint = new Paint();
        int color = ContextCompat.getColor(getContext(), R.color.magenta);
        paint.setColor(color);
        paint.setTextSize(30);
        canvas.drawText("UPS: " + averageUPS, 10, 120, paint);
    }

    public void drawFPS(Canvas canvas) {
        String averageFPS = Double.toString(gameLoop.getAverageFPS());
        Paint paint = new Paint();
        int color = ContextCompat.getColor(getContext(), R.color.black);
        paint.setColor(color);
        paint.setTextSize(30);
        canvas.drawText("FPS: " + averageFPS, 10, 90, paint);

    }

    public void drawScore(Canvas canvas) {
        Rect textBounds = new Rect();
        paintScore.getTextBounds(Integer.toString(score), 0, Integer.toString(score).length(), textBounds);
        canvas.drawText(Integer.toString(score), width / 2, 200, paintScore);
    }

    public void resetData() {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.clear();
        editor.commit();
        achievementsMenu.reset();
        statMenu.reset();
        startBackgroundMusic();
        loadData();
    }

    public void loadData() {
        highScore = sharedPref.getInt("highScore", 0);
        highScoreNormal = sharedPref.getInt("highScoreNormal", 0);
        highScoreLead = sharedPref.getInt("highScoreLead", 0);
        highScoreGravity = sharedPref.getInt("highScoreGravity", 0);
        highScoreMoon = sharedPref.getInt("highScoreMoon", 0);
        highScoreGlitch = sharedPref.getInt("highScoreGlitch", 0);
        highScoreHeart = sharedPref.getInt("highScoreHeart", 0);
        highScoreToxic = sharedPref.getInt("highScoreToxic", 0);

        highScoreVoid = sharedPref.getInt("highScoreVoid", 0);
        highScoreLight = sharedPref.getInt("highScoreLight", 0);
        highScoreQuantum = sharedPref.getInt("highScoreQuantum", 0);
        highScorePicasso = sharedPref.getInt("highScorePicasso", 0);
        highScoreGlider = sharedPref.getInt("highScoreGlider", 0);
        highScoreMagic = sharedPref.getInt("highScoreMagic", 0);
        highScoreMagnet = sharedPref.getInt("highScoreMagnet", 0);
        highScoreAbyss = sharedPref.getInt("highScoreAbyss", 0);

        boolean achHandfulOfPoints = sharedPref.getBoolean("achHandfulOfPoints", false);
        boolean achOffTheTop = sharedPref.getBoolean("achOffTheTop", false);
        boolean achMaxGravity = sharedPref.getBoolean("achMaxGravity", false);
        boolean achThreeBirdsOneStone = sharedPref.getBoolean("achThreeBirdsOneStone", false);
        boolean achBalloonMaster = sharedPref.getBoolean("achBalloonMaster", false);
        boolean achFortnight = sharedPref.getBoolean("achFortnight", false);
        boolean achExtraLife = sharedPref.getBoolean("archExtraLife", false);
        boolean achGlitchFallout = sharedPref.getBoolean("archGlitchFallout", false);



        if (achHandfulOfPoints) {
            achievementsMenu.completeHandfulOfPoints();
            statMenu.unlockGlitch();
        }
        if (achOffTheTop) {
            achievementsMenu.completeOffTheTop();
            statMenu.unlockGravity();
        }
        if (achMaxGravity) {
            achievementsMenu.completeMaxGravity();
            statMenu.unlockLead();
        }
        if (achThreeBirdsOneStone) {
            achievementsMenu.completeTwoBirdsOneStone();
        }
        if (achBalloonMaster) {
            achievementsMenu.completeBalloonMaster();
        }
        if (achFortnight) {
            achievementsMenu.completeFortnight();
            statMenu.unlockMoon();
        }
        if (achExtraLife) {
            achievementsMenu.completeExtraLife();
            statMenu.unlockHeart();
        }
        if (achGlitchFallout) {
            achievementsMenu.completeGlitchFallout();
            statMenu.unlockRadiation();
        }

        boolean achEndOfReality = sharedPref.getBoolean("achEndOfReality", false);
        boolean achMoonLight = sharedPref.getBoolean("achMoonLight", false);
        boolean achSplittingLight = sharedPref.getBoolean("achSplittingLight", false);
        boolean achVisionary = sharedPref.getBoolean("achVisionary", false);
        boolean achExodus = sharedPref.getBoolean("achExodus", false);
        boolean achMystical = sharedPref.getBoolean("achMystical", false);
        boolean achForces = sharedPref.getBoolean("achForces", false);
        boolean achTheCreature = sharedPref.getBoolean("achTheCreature", false);
        if (achEndOfReality) {
            achievementsMenu.completeEndOfReality();
            statMenu.unlockVoid();
        }
        if (achMoonLight) {
            achievementsMenu.completeMoonLight();
            statMenu.unlockLight();
        }
        if (achSplittingLight) {
            achievementsMenu.completeSplittingLight();
            statMenu.unlockQuantum();
        }
        if (achVisionary) {
            achievementsMenu.completeVisionary();
            statMenu.unlockPicasso();
        }
        if (achExodus) {
            achievementsMenu.completeExodus();
            statMenu.unlockGlider();
        }
        if (achMystical) {
            achievementsMenu.completeMystical();
            statMenu.unlockMagic();
        }
        if (achForces) {
            achievementsMenu.completeForces();
            statMenu.unlockMagnet();
        }
        if (achTheCreature) {
            achievementsMenu.completeTheCreature();
            statMenu.unlockAbyss();
        }

        sounds = sharedPref.getBoolean("sounds", true);
        backgroundMusic = sharedPref.getBoolean("backgroundMusic", true);
        quickAim = sharedPref.getBoolean("quickAim", false);
        showInfo = sharedPref.getBoolean("showInfo", false);
    }

    public void saveData() {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("highScore", highScore);
        editor.putInt("highScoreNormal", highScoreNormal);
        editor.putInt("highScoreLead", highScoreLead);
        editor.putInt("highScoreGravity", highScoreGravity);
        editor.putInt("highScoreMoon", highScoreMoon);
        editor.putInt("highScoreGlitch", highScoreGlitch);
        editor.putInt("highScoreHeart", highScoreHeart);
        editor.putInt("highScoreToxic", highScoreToxic);

        editor.putInt("highScoreVoid", highScoreVoid);
        editor.putInt("highScoreLight", highScoreLight);
        editor.putInt("highScoreQuantum", highScoreQuantum);
        editor.putInt("highScorePicasso", highScorePicasso);
        editor.putInt("highScoreGlider", highScoreGlider);
        editor.putInt("highScoreMagic", highScoreMagic);
        editor.putInt("highScoreMagnet", highScoreMagnet);
        editor.putInt("highScoreAbyss", highScoreAbyss);


        editor.putBoolean("achHandfulOfPoints", achievementsMenu.getHandfulOfPoints());
        editor.putBoolean("achOffTheTop", achievementsMenu.getOffTheTop());
        editor.putBoolean("achMaxGravity", achievementsMenu.getMaxGravity());
        editor.putBoolean("achThreeBirdsOneStone", achievementsMenu.getTwoBirdsOneStone());
        editor.putBoolean("achBalloonMaster", achievementsMenu.getBalloonMaster());
        editor.putBoolean("achFortnight", achievementsMenu.getFortnight());
        editor.putBoolean("archExtraLife", achievementsMenu.getExtraLife());
        editor.putBoolean("archGlitchFallout", achievementsMenu.getGlitchFallout());

        editor.putBoolean("achEndOfReality", achievementsMenu.getEndOfReality());
        editor.putBoolean("achMoonLight", achievementsMenu.getMoonLight());
        editor.putBoolean("achSplittingLight", achievementsMenu.getSplittingLight());
        editor.putBoolean("achVisionary", achievementsMenu.getVisionary());
        editor.putBoolean("achExodus", achievementsMenu.getExodus());
        editor.putBoolean("achMystical", achievementsMenu.getMystical());
        editor.putBoolean("achForces", achievementsMenu.getForces());
        editor.putBoolean("achTheCreature", achievementsMenu.getTheCreature());

        editor.putBoolean("backgroundMusic", backgroundMusic);
        editor.putBoolean("sounds", sounds);
        editor.putBoolean("showInfo", showInfo);
        editor.putBoolean("quickAim", quickAim);

        editor.apply();
    }

    public int calcTotalScore() {
        return highScore + highScoreLead + highScoreGravity + highScoreMoon + highScoreGlitch + highScoreHeart + highScoreToxic +
                highScoreVoid + highScoreLight + highScoreQuantum + highScorePicasso + highScoreGlider + highScoreMagic + highScoreMagnet + highScoreAbyss;
    }

    public void update() {

        background.update();
        cloudFactory.update();
        message.update();

        if (screen == "play") {
            player.update();
            ball.update(balloonType, sounds);
            itemBoost.update();

            if (balloonType == "radiation") {
                point.update(ball.getXSpeed(), ball.getYSpeed(), ball.getPositionX(), ball.getPositionY(), width, height);
            }
            ball.checkCollision(player, sounds);
            ball.checkCollisionItemBoost(itemBoost, sounds);
            if (ball.checkCollision(point, sounds)) {
                this.score += 1;
                if (this.score > this.highScore && (balloonType == "red" || balloonType == "purple" || balloonType == "teal" || balloonType == "yellow")) {
                    this.highScore = score;
                }
                if (balloonType == "gravity" && this.score > this.highScoreGravity) {
                    this.highScoreGravity = score;
                } else if (balloonType == "lead" && this.score > this.highScoreLead) {
                    this.highScoreLead = score;
                } else if (balloonType == "heart") {
                    extraLife = true;
                    if (this.score > this.highScoreHeart) {
                        this.highScoreHeart = this.score;
                    }
                } else if (balloonType == "moon" && this.score > this.highScoreMoon) {
                    this.highScoreMoon = score;
                } else if (balloonType == "glitch" && this.score > this.highScoreGlitch) {
                    this.highScoreGlitch = score;
                }
                else if (balloonType == "radiation" && this.score > this.highScoreToxic) {
                    this.highScoreToxic = score;
                }

                else if (balloonType == "void" && this.score > this.highScoreVoid) {
                    this.highScoreVoid = score;
                }
                else if (balloonType == "light" && this.score > this.highScoreLight) {
                    this.highScoreLight = score;
                }
                else if (balloonType == "quantum" && this.score > this.highScoreQuantum) {
                    this.highScoreQuantum = score;
                }
                else if (balloonType == "picasso" && this.score > this.highScorePicasso) {
                    this.highScorePicasso = score;
                }
                else if (balloonType == "glider" && this.score > this.highScoreGlider) {
                    this.highScoreGlider = score;
                }
                else if (balloonType == "magic" && this.score > this.highScoreMagic) {
                    this.highScoreMagic = score;
                }
                else if (balloonType == "magnet" && this.score > this.highScoreMagnet) {
                    this.highScoreMagnet = score;
                }
                else if (balloonType == "abyss" && this.score > this.highScoreAbyss) {
                    this.highScoreAbyss = score;
                }



                // Give extra life
                if ((int) score % Con.HEART_THRESHOLD == 0) {
                    if (!achievementsMenu.getExtraLife()) {
                        if (score >= Con.HEART_THRESHOLD) {
                            message.postMessage("Achievement Complete: Extra Life");
                            achievementsMenu.completeExtraLife();
                            statMenu.unlockHeart();
                        }
                    }
                    extraLife = true;
                }
                if (!achievementsMenu.getGlitchFallout()) {
                    if (score >= Con.FALLOUT_THRESHOLD && balloonType == "glitch") {
                        message.postMessage("Achievement Complete: Glitch Fallout");
                        achievementsMenu.completeGlitchFallout();
                        statMenu.unlockRadiation();
                    }
                }
                // achievement handful of points
                if (!achievementsMenu.getHandfulOfPoints()) {
                    if (score >= Con.GLITCH_THRESHOLD) {
                        message.postMessage("Achievement Complete: Handful Of Points");
                        achievementsMenu.completeHandfulOfPoints();
                        statMenu.unlockGlitch();
                    }
                }
                if (!achievementsMenu.getFortnight()) {
                    if (score >= Con.MOON_THRESHOLD) {
                        message.postMessage("Achievement Complete: Fortnight");
                        achievementsMenu.completeFortnight();
                        statMenu.unlockMoon();
                    }
                }
                if (!achievementsMenu.getEndOfReality()) {
                    if (score >= Con.VOID_THRESHOLD) {
                        message.postMessage("Achievement Complete: End Of Reality");
                        achievementsMenu.completeEndOfReality();
                        statMenu.unlockVoid();
                    }
                }
                if (!achievementsMenu.getMoonLight()) {
                    if (highScoreMoon >= Con.LIGHT_THRESHOLD) {
                        message.postMessage("Achievement Complete: Moon Light");
                        achievementsMenu.completeMoonLight();
                        statMenu.unlockLight();
                    }
                }
                if (!achievementsMenu.getSplittingLight()) {
                    if (highScoreLight >= Con.QUANTUM_THRESHOLD) {
                        message.postMessage("Achievement Complete: Splitting Light");
                        achievementsMenu.completeSplittingLight();
                        statMenu.unlockQuantum();
                    }
                }
                if (!achievementsMenu.getVisionary()) {
                    if (calcTotalScore() > Con.PICASSO_THRESHOLD) {
                        message.postMessage("Achievement Complete: Visionary");
                        achievementsMenu.completeVisionary();
                        statMenu.unlockPicasso();
                    }
                }
                if (!achievementsMenu.getExodus()) {
                    if (highScoreToxic >= Con.GLIDER_THRESHOLD) {
                        message.postMessage("Achievement Complete: Exodus");
                        achievementsMenu.completeExodus();
                        statMenu.unlockGlider();
                    }
                }
                if (!achievementsMenu.getMystical()) {
                    if (highScoreQuantum >= Con.MAGIC_THRESHOLD) {
                        message.postMessage("Achievement Complete: Mystical");
                        achievementsMenu.completeMystical();
                        statMenu.unlockMagic();
                    }
                }
                if (!achievementsMenu.getForces()) {
                    if (highScoreLead >= Con.MAGNET_THRESHOLD) {
                        message.postMessage("Achievement Complete: Forces");
                        achievementsMenu.completeForces();
                        statMenu.unlockMagnet();
                    }
                }
                if (!achievementsMenu.getTheCreature()) {
                    if (highScoreVoid >= Con.ABYSS_THRESHOLD) {
                        message.postMessage("Achievement Complete: The Creature");
                        achievementsMenu.completeTheCreature();
                        statMenu.unlockAbyss();
                    }
                }
            }

            if (ball.checkOutOfBounds(width, height, extraLife, point, balloonType, sounds)) {
                player.reset();
                itemBoost.reset();
                if (extraLife) {
                    extraLife = false;
                }
                else {
                    this.screen = "start";


                    this.score = 0;
                    saveData();
                }
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
