package com.example.androidgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;

import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

public class AchievementsMenu {

    private Button backButton;
    private Context context;

    private Achievement achievementHandfulOfPoints;
    private Achievement achievementOffTheTop;
    private Achievement achievementMaxGravity;
    private Achievement achievementTwoBirdsOneStone;
    private Achievement achievementBalloonMaster;

    public AchievementsMenu(Context context, double width, double height) {

        this.context = context;

        double startHeight = 150;
        double padding = 20;
        double cardHeight = 100;

        backButton = new Button(context, 128, 320, width / 2, height -150, "Back", 50);
        achievementHandfulOfPoints = new Achievement(context, "Handful Of Points", "Earn 5 points in one round", startHeight, width);
        achievementOffTheTop = new Achievement(context, "Off The Top - Unlocks Gravity Balloon", "Move the balloon off the top of the screen", startHeight + (cardHeight + padding) * 1, width);
        achievementMaxGravity = new Achievement(context, "Max Gravity - Unlocks Lead Balloon", "Reach maximum gravity", startHeight + (cardHeight + padding) * 2, width);
        achievementTwoBirdsOneStone = new Achievement(context, "Three Birds One Stone - ", "Get three points in one bounce", startHeight + (cardHeight + padding) * 3, width);
        achievementBalloonMaster = new Achievement(context, "Balloon Master", "Unlock every balloon type", startHeight + (cardHeight + padding) * 4, width);

    }

    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setTextAlign(Paint.Align.CENTER);
        int color = ContextCompat.getColor(context, R.color.text);
        paint.setColor(color);
        paint.setTextSize(100);
        Typeface customFont = ResourcesCompat.getFont(context, R.font.vt323);
        paint.setTypeface(customFont);
        canvas.drawText("Achievements", canvas.getWidth() / 2, 100, paint);

        backButton.draw(canvas);
        achievementHandfulOfPoints.draw(canvas);
        achievementOffTheTop.draw(canvas);
        achievementMaxGravity.draw(canvas);
        achievementTwoBirdsOneStone.draw(canvas);
        achievementBalloonMaster.draw(canvas);


    }

    public boolean checkPressBackButton(float x, float y) {
        return backButton.checkPress(x, y);
    }


    public void completeOffTheTop() {
        achievementOffTheTop.complete();
    }
    public void completeHandfulOfPoints() {
        achievementHandfulOfPoints.complete();
    }
    public void completeMaxGravity() {
        achievementMaxGravity.complete();
    }
    public void completeTwoBirdsOneStone() {
        achievementTwoBirdsOneStone.complete();
    }
    public void completeBalloonMaster() {
        achievementBalloonMaster.complete();
    }

    public boolean getOffTheTop() {
        return achievementOffTheTop.getCompleted();
    }
    public boolean getHandfulOfPoints() {
        return achievementHandfulOfPoints.getCompleted();
    }
    public boolean getMaxGravity() {
        return achievementMaxGravity.getCompleted();
    }
    public boolean getTwoBirdsOneStone() {
        return achievementTwoBirdsOneStone.getCompleted();
    }
    public boolean getBalloonMaster() {
        return achievementBalloonMaster.getCompleted();
    }
}
