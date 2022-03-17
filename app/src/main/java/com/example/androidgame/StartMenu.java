package com.example.androidgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

import androidx.core.content.ContextCompat;

public class StartMenu {

    private ButtonImage startButton;
    private Context context;
    private Button highScoreButton;
    private Button statButton;
    private Button achievementsButton;
    private Button settingsButton;

    private int width;
    private int height;

    private Drawable image;

    public StartMenu(Context context, double width, double height) {


        this.height = (int) height;
        this.width = (int) width;
        this.context = context;

        statButton = new Button(context, 128, 320, width / 3, height - 400, "Balloons", 50);
        highScoreButton = new Button(context, 128, 320, width / 3, height - 250, "High Scores", 50);
        achievementsButton = new Button(context, 128, 320, (width / 3) * 2, height - 400, "Achievements", 50);
        settingsButton = new Button(context, 128, 320, (width / 3) * 2, height - 250, "Settings", 50);


        Drawable startImage = context.getResources().getDrawable(R.drawable.start849x232);

        startButton = new ButtonImage(context, 116, 424, width / 2, height / 2, startImage);


        image = context.getResources().getDrawable(R.drawable.title810x306);
        image.getBounds();

    }

    public void draw(Canvas canvas) {
//        Paint paint = new Paint();
//        paint.setTextAlign(Paint.Align.CENTER);
//        int color = ContextCompat.getColor(context, R.color.text);
//        paint.setColor(color);
//        paint.setTextSize(100);
//        canvas.drawText("Bouncing Ball", canvas.getWidth() / 2, 200, paint);


        Rect imageBounds = new Rect();
        imageBounds.left = (int) (width/2 - 405);
        imageBounds.top = (int) (150);
        imageBounds.right = (int) (width/2 + 405);
        imageBounds.bottom = (int) (456);

        image.setBounds(imageBounds);
        image.draw(canvas);

        startButton.draw(canvas);
        highScoreButton.draw(canvas);
        statButton.draw(canvas);
        achievementsButton.draw(canvas);
        settingsButton.draw(canvas);
    }

    public boolean checkPressStartButton(float x, float y) {
        return startButton.checkPress(x, y);
    }

    public boolean checkPressHighScoreButton(float x, float y) {
        return highScoreButton.checkPress(x, y);
    }

    public boolean checkPressStatButton(float x, float y) {
        return statButton.checkPress(x, y);
    }

    public boolean checkPressAchievementsButton(float x, float y) {
        return achievementsButton.checkPress(x, y);
    }

    public boolean checkPressSettingsButton(float x, float y) {
        return settingsButton.checkPress(x, y);
    }
}
