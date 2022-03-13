package com.example.androidgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.Log;

import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

public class StatMenu {

    private Button backButton;
    private Context context;

    private Card redBalloon;
    private Card purpleBalloon;
    private Card tealBalloon;
    private Card yellowBalloon;
    private Card leadBalloon;
    private Card gravityBalloon;

    public StatMenu(Context context, double width, double height) {

        this.context = context;
        backButton = new Button(context, 128, 320, width / 2, height -150, "Back", 50);

        int cardWidth = 280;
        int cardHeight = 376;
        int padding = 20;

        redBalloon    = new Card (context, (width / 2) - padding - (cardWidth / 2) - cardWidth, 300, cardWidth, cardHeight, context.getResources().getDrawable(R.drawable.cardredballoon280x376), "Red");
        purpleBalloon = new Card (context, (width / 2) - (cardWidth / 2), 300, cardWidth, cardHeight, context.getResources().getDrawable(R.drawable.cardpurple280x376), "Purple");
        tealBalloon   = new Card (context, (width / 2) + padding + (cardWidth / 2), 300, cardWidth, cardHeight, context.getResources().getDrawable(R.drawable.cardtealballoon280x376), "Teal");

        yellowBalloon = new Card (context, (width / 2) - padding - (cardWidth / 2) - cardWidth, 300 + cardHeight + padding, cardWidth, cardHeight, context.getResources().getDrawable(R.drawable.cardyellowballoon280x375), "Yellow");
        leadBalloon = new Card (context, (width / 2) - (cardWidth / 2), 300 + cardHeight + padding, cardWidth, cardHeight, context.getResources().getDrawable(R.drawable.cardlead280x376), "Lead");
        gravityBalloon = new Card (context, (width / 2) + padding + (cardWidth / 2), 300 + cardHeight + padding, cardWidth, cardHeight, context.getResources().getDrawable(R.drawable.cardgravity280x375), "Gravity");

        redBalloon.setLocked(false);
        purpleBalloon.setLocked(false);
        tealBalloon.setLocked(false);
        yellowBalloon.setLocked(false);

        redBalloon.select();
    }

    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setTextAlign(Paint.Align.CENTER);
        int color = ContextCompat.getColor(context, R.color.text);
        paint.setColor(color);
        paint.setTextSize(100);
        Typeface customFont = ResourcesCompat.getFont(context, R.font.vt323);
        paint.setTypeface(customFont);
        canvas.drawText("Equips", canvas.getWidth() / 2, 100, paint);

        backButton.draw(canvas);
        redBalloon.draw(canvas);
        purpleBalloon.draw(canvas);
        tealBalloon.draw(canvas);
        yellowBalloon.draw(canvas);
        leadBalloon.draw(canvas);
        gravityBalloon.draw(canvas);
    }

    public void unselect() {
        redBalloon.unselect();
        purpleBalloon.unselect();
        tealBalloon.unselect();
        yellowBalloon.unselect();
        leadBalloon.unselect();
        gravityBalloon.unselect();
    }

    public boolean checkPressBackButton(float x, float y) {
        return backButton.checkPress(x, y);
    }

    public boolean checkPressRedBalloonCard(float x, float y) {
        Boolean pressed = redBalloon.checkPress(x, y);
        if (pressed) {
            unselect();
            redBalloon.select();
        }
        return pressed;
    }

    public boolean checkPressPurpleBalloonCard(float x, float y) {
        Boolean pressed = purpleBalloon.checkPress(x, y);
        if (pressed) {
            unselect();
            purpleBalloon.select();
        }
        return pressed;
    }

    public boolean checkPressTealBalloonCard(float x, float y) {
        Boolean pressed = tealBalloon.checkPress(x, y);
        if (pressed) {
            unselect();
            tealBalloon.select();
        }
        return pressed;
    }

    public boolean checkPressYellowBalloonCard(float x, float y) {
        Boolean pressed = yellowBalloon.checkPress(x, y);
        if (pressed) {
            unselect();
            yellowBalloon.select();
        }
        return pressed;
    }

    public boolean checkPressLeadBalloonCard(float x, float y) {
        Boolean pressed = leadBalloon.checkPress(x, y);
        if (pressed && !leadBalloon.getLocked()) {
            unselect();
            leadBalloon.select();
        }
        return pressed;
    }

    public boolean checkPressGravityBalloonCard(float x, float y) {
        Boolean pressed = gravityBalloon.checkPress(x, y);
        if (pressed && !gravityBalloon.getLocked()) {
            unselect();
            gravityBalloon.select();
        }
        return pressed;
    }

    public boolean checkAchievementBalloonMaster() {
        if (redBalloon.getLocked() || purpleBalloon.getLocked() || tealBalloon.getLocked() || yellowBalloon.getLocked() || leadBalloon.getLocked()
            || gravityBalloon.getLocked()) {
            return false;
        }
        return true;
    }

    public void unlockLead() {
        leadBalloon.setLocked(false);
    }
    public void unlockGravity() {
        gravityBalloon.setLocked(false);
    }

    public boolean getLeadLocked() {
        return leadBalloon.getLocked();
    }
    public boolean getGravityLocked() {
        return gravityBalloon.getLocked();
    }
}
