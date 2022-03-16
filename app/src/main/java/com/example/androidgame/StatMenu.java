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

    private Button nextPage;
    private Button previousPage;

    private Card redBalloon;
    private Card purpleBalloon;
    private Card tealBalloon;
    private Card yellowBalloon;
    private Card leadBalloon;
    private Card gravityBalloon;
    private Card moonBalloon;
    private Card glitchBalloon;
    private Card heartBalloon;

    private Card radiationBalloon;
    private Card voidBalloon;
    private Card lightBalloon;
    private Card quantumBalloon;
    private Card picassoBalloon;
    private Card gliderBalloon;
    private Card magicBalloon;
    private Card magnetBalloon;
    private Card abyssBalloon;

    private int currentPage;
    private int totalPages;

    public StatMenu(Context context, double width, double height) {

        this.context = context;
        backButton = new Button(context, 128, 320, width / 2, height -150, "Back", 50);
        nextPage = new Button(context, 128, 320, width * .75, height -300, "->", 50);
        previousPage = new Button(context, 128, 320, width / 4, height -300, "<-", 50);

        currentPage = 1;
        totalPages = 2;

        int startHeight = 200;
        int cardWidth = 280;
        int cardHeight = 376;
        int padding = 20;

        redBalloon    = new Card (context, (width / 2) - padding - (cardWidth / 2) - cardWidth, startHeight, cardWidth, cardHeight, context.getResources().getDrawable(R.drawable.cardredballoon280x376), "Red");
        purpleBalloon = new Card (context, (width / 2) - (cardWidth / 2), startHeight, cardWidth, cardHeight, context.getResources().getDrawable(R.drawable.cardpurple280x376), "Purple");
        tealBalloon   = new Card (context, (width / 2) + padding + (cardWidth / 2), startHeight, cardWidth, cardHeight, context.getResources().getDrawable(R.drawable.cardtealballoon280x376), "Teal");

        yellowBalloon = new Card (context, (width / 2) - padding - (cardWidth / 2) - cardWidth, startHeight + cardHeight + padding, cardWidth, cardHeight, context.getResources().getDrawable(R.drawable.cardyellowballoon280x375), "Yellow");
        glitchBalloon = new Card (context, (width / 2) - (cardWidth / 2), startHeight + cardHeight + padding, cardWidth, cardHeight, context.getResources().getDrawable(R.drawable.cardglitch280x376), "Glitch");
        gravityBalloon = new Card (context, (width / 2) + padding + (cardWidth / 2), startHeight + cardHeight + padding, cardWidth, cardHeight, context.getResources().getDrawable(R.drawable.cardgravity280x375), "Gravity");

        moonBalloon = new Card (context, (width / 2) - padding - (cardWidth / 2) - cardWidth, startHeight + cardHeight + padding + cardHeight + padding, cardWidth, cardHeight, context.getResources().getDrawable(R.drawable.cardmoon280x376), "Moon");
        leadBalloon = new Card (context, (width / 2) - (cardWidth / 2), startHeight + cardHeight + padding + cardHeight + padding, cardWidth, cardHeight, context.getResources().getDrawable(R.drawable.cardlead280x376), "Lead");
        heartBalloon = new Card (context, (width / 2) + padding + (cardWidth / 2), startHeight + cardHeight + padding + cardHeight + padding, cardWidth, cardHeight, context.getResources().getDrawable(R.drawable.cardheart289x376), "Heart");


        // page 2
        radiationBalloon  = new Card (context, (width / 2) - padding - (cardWidth / 2) - cardWidth, startHeight, cardWidth, cardHeight, context.getResources().getDrawable(R.drawable.cardradiation280x376), "Toxic");
        voidBalloon = new Card (context, (width / 2) - (cardWidth / 2), startHeight, cardWidth, cardHeight, context.getResources().getDrawable(R.drawable.cardvoid280x376), "Void");
        lightBalloon   = new Card (context, (width / 2) + padding + (cardWidth / 2), startHeight, cardWidth, cardHeight, context.getResources().getDrawable(R.drawable.cardlight280x376), "Light");

        quantumBalloon = new Card (context, (width / 2) - padding - (cardWidth / 2) - cardWidth, startHeight + cardHeight + padding, cardWidth, cardHeight, context.getResources().getDrawable(R.drawable.cardquantum280x376), "Quantum");
        picassoBalloon = new Card (context, (width / 2) - (cardWidth / 2), startHeight + cardHeight + padding, cardWidth, cardHeight, context.getResources().getDrawable(R.drawable.cardpicasso280x376), "Picasso");
        gliderBalloon = new Card (context, (width / 2) + padding + (cardWidth / 2), startHeight + cardHeight + padding, cardWidth, cardHeight, context.getResources().getDrawable(R.drawable.cardglider280x376), "Glider");

        magicBalloon = new Card (context, (width / 2) - padding - (cardWidth / 2) - cardWidth, startHeight + cardHeight + padding + cardHeight + padding, cardWidth, cardHeight, context.getResources().getDrawable(R.drawable.cardmagic280x376), "Magic");
        magnetBalloon = new Card (context, (width / 2) - (cardWidth / 2), startHeight + cardHeight + padding + cardHeight + padding, cardWidth, cardHeight, context.getResources().getDrawable(R.drawable.cardmagnet280x376), "Magnet");
        abyssBalloon = new Card (context, (width / 2) + padding + (cardWidth / 2), startHeight + cardHeight + padding + cardHeight + padding, cardWidth, cardHeight, context.getResources().getDrawable(R.drawable.cardabyss280x376), "Abyssal");

        // Water balloon moving coins as bubbles

        // void
        // Light - no gravity moves in a strait line
        // Quantum  - Random balloon set gravity to random value at peak
        // Picasso - bounces do not bounce as expected
        // Glider - balloon that constently moves side ways (whichever side is closest
        // Magic - Moves in random directions random.y - Reward score 75
        // Magnet - Lead but heavyier pulls in coins. - Reward Lead 25
        // Abyss  - Only can collects points while moving down




        redBalloon.setLocked(false);
        purpleBalloon.setLocked(false);
        tealBalloon.setLocked(false);
        yellowBalloon.setLocked(false);
//        radiationBalloon.setLocked(false);
//        heartBalloon.setLocked(false);
//        heartBalloon.setLocked(false);
//        heartBalloon.setLocked(false);
//        glitchBalloon.setLocked(false);
//        moonBalloon.setLocked(false);

//        voidBalloon.setLocked(false);
//        lightBalloon.setLocked(false);
//        quantumBalloon.setLocked(false);
//        picassoBalloon.setLocked(false);
//        gliderBalloon.setLocked(false);
//        magicBalloon.setLocked(false);
//        magnetBalloon.setLocked(false);
//        abyssBalloon.setLocked(false);

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
        canvas.drawText("Balloons", canvas.getWidth() / 2, 100, paint);

        canvas.drawText(Integer.toString(currentPage) + "/" + Integer.toString(totalPages), canvas.getWidth() / 2, canvas.getHeight() - 275, paint);


        backButton.draw(canvas);
        nextPage.draw(canvas);
        previousPage.draw(canvas);

        if (currentPage == 1) {
            redBalloon.draw(canvas);
            purpleBalloon.draw(canvas);
            tealBalloon.draw(canvas);
            yellowBalloon.draw(canvas);
            leadBalloon.draw(canvas);
            gravityBalloon.draw(canvas);

            moonBalloon.draw(canvas);
            glitchBalloon.draw(canvas);
            heartBalloon.draw(canvas);
        }
        else if (currentPage == 2) {
            radiationBalloon.draw(canvas);
            voidBalloon.draw(canvas);
            lightBalloon.draw(canvas);
            quantumBalloon.draw(canvas);
            picassoBalloon.draw(canvas);
            gliderBalloon.draw(canvas);
            magicBalloon.draw(canvas);
            magnetBalloon.draw(canvas);
            abyssBalloon.draw(canvas);
        }


    }

    public void unselect() {
        redBalloon.unselect();
        purpleBalloon.unselect();
        tealBalloon.unselect();
        yellowBalloon.unselect();
        leadBalloon.unselect();
        gravityBalloon.unselect();
        moonBalloon.unselect();
        glitchBalloon.unselect();
        heartBalloon.unselect();

        radiationBalloon.unselect();
        voidBalloon.unselect();
        lightBalloon.unselect();
        quantumBalloon.unselect();
        picassoBalloon.unselect();
        gliderBalloon.unselect();
        magicBalloon.unselect();
        magnetBalloon.unselect();
        abyssBalloon.unselect();
    }

    public boolean checkPressBackButton(float x, float y) {
        return backButton.checkPress(x, y);
    }
    public boolean checkPressNextPageButton(float x, float y) {
        Boolean pressed = nextPage.checkPress(x, y);
        if (pressed && currentPage < totalPages) {
            currentPage += 1;
        }
        return pressed;
    }
    public boolean checkPressPreviousPageButton(float x, float y) {
        Boolean pressed = previousPage.checkPress(x, y);
        if (pressed && currentPage > 1) {
            currentPage -= 1;
        }
        return pressed;
    }

    public boolean checkPressRedBalloonCard(float x, float y) {
        if (currentPage != 1) {
            return false;
        }
        Boolean pressed = redBalloon.checkPress(x, y);
        if (pressed) {
            unselect();
            redBalloon.select();
        }
        return pressed;
    }

    public boolean checkPressPurpleBalloonCard(float x, float y) {
        if (currentPage != 1) {
            return false;
        }
        Boolean pressed = purpleBalloon.checkPress(x, y);
        if (pressed) {
            unselect();
            purpleBalloon.select();
        }
        return pressed;
    }

    public boolean checkPressTealBalloonCard(float x, float y) {
        if (currentPage != 1) {
            return false;
        }
        Boolean pressed = tealBalloon.checkPress(x, y);
        if (pressed) {
            unselect();
            tealBalloon.select();
        }
        return pressed;
    }

    public boolean checkPressYellowBalloonCard(float x, float y) {
        if (currentPage != 1) {
            return false;
        }
        Boolean pressed = yellowBalloon.checkPress(x, y);
        if (pressed) {
            unselect();
            yellowBalloon.select();
        }
        return pressed;
    }

    public boolean checkPressLeadBalloonCard(float x, float y) {
        if (currentPage != 1) {
            return false;
        }
        Boolean pressed = leadBalloon.checkPress(x, y);
        if (pressed && !leadBalloon.getLocked()) {
            unselect();
            leadBalloon.select();
        }
        return pressed;
    }

    public boolean checkPressGravityBalloonCard(float x, float y) {
        if (currentPage != 1) {
            return false;
        }
        Boolean pressed = gravityBalloon.checkPress(x, y);
        if (pressed && !gravityBalloon.getLocked()) {
            unselect();
            gravityBalloon.select();
        }
        return pressed;
    }

    public boolean checkPressMoonBalloonCard(float x, float y) {
        if (currentPage != 1) {
            return false;
        }
        Boolean pressed = moonBalloon.checkPress(x, y);
        if (pressed && !moonBalloon.getLocked()) {
            unselect();
            moonBalloon.select();
        }
        return pressed;
    }

    public boolean checkPressGlitchBalloonCard(float x, float y) {
        if (currentPage != 1) {
            return false;
        }
        Boolean pressed = glitchBalloon.checkPress(x, y);
        if (pressed && !glitchBalloon.getLocked()) {
            unselect();
            glitchBalloon.select();
        }
        return pressed;
    }
    public boolean checkPressHeartBalloonCard(float x, float y) {
        if (currentPage != 1) {
            return false;
        }
        Boolean pressed = heartBalloon.checkPress(x, y);
        if (pressed && !heartBalloon.getLocked()) {
            unselect();
            heartBalloon.select();
        }
        return pressed;
    }


    public boolean checkPressRadiationBalloonCard(float x, float y) {
        if (currentPage != 2) {
            return false;
        }
        Boolean pressed = radiationBalloon.checkPress(x, y);
        if (pressed && !radiationBalloon.getLocked()) {
            unselect();
            radiationBalloon.select();
        }
        return pressed;
    }
    public boolean checkPressVoidBalloonCard(float x, float y) {
        if (currentPage != 2) {
            return false;
        }
        Boolean pressed = voidBalloon.checkPress(x, y);
        if (pressed && !voidBalloon.getLocked()) {
            unselect();
            voidBalloon.select();
        }
        return pressed;
    }
    public boolean checkPressLightBalloonCard(float x, float y) {
        if (currentPage != 2) {
            return false;
        }
        Boolean pressed = lightBalloon.checkPress(x, y);
        if (pressed && !lightBalloon.getLocked()) {
            unselect();
            lightBalloon.select();
        }
        return pressed;
    }
    public boolean checkPressQuantumBalloonCard(float x, float y) {
        if (currentPage != 2) {
            return false;
        }
        Boolean pressed = quantumBalloon.checkPress(x, y);
        if (pressed && !quantumBalloon.getLocked()) {
            unselect();
            quantumBalloon.select();
        }
        return pressed;
    }
    public boolean checkPressPicassoBalloonCard(float x, float y) {
        if (currentPage != 2) {
            return false;
        }
        Boolean pressed = picassoBalloon.checkPress(x, y);
        if (pressed && !picassoBalloon.getLocked()) {
            unselect();
            picassoBalloon.select();
        }
        return pressed;
    }
    public boolean checkPressGliderBalloonCard(float x, float y) {
        if (currentPage != 2) {
            return false;
        }
        Boolean pressed = gliderBalloon.checkPress(x, y);
        if (pressed && !gliderBalloon.getLocked()) {
            unselect();
            gliderBalloon.select();
        }
        return pressed;
    }
    public boolean checkPressMagicBalloonCard(float x, float y) {
        if (currentPage != 2) {
            return false;
        }
        Boolean pressed = magicBalloon.checkPress(x, y);
        if (pressed && !magicBalloon.getLocked()) {
            unselect();
            magicBalloon.select();
        }
        return pressed;
    }
    public boolean checkPressMagnetBalloonCard(float x, float y) {
        if (currentPage != 2) {
            return false;
        }
        Boolean pressed = magnetBalloon.checkPress(x, y);
        if (pressed && !magnetBalloon.getLocked()) {
            unselect();
            magnetBalloon.select();
        }
        return pressed;
    }
    public boolean checkPressAbyssBalloonCard(float x, float y) {
        if (currentPage != 2) {
            return false;
        }
        Boolean pressed = abyssBalloon.checkPress(x, y);
        if (pressed && !abyssBalloon.getLocked()) {
            unselect();
            abyssBalloon.select();
        }
        return pressed;
    }




    public boolean checkAchievementBalloonMaster() {
        if (redBalloon.getLocked() || purpleBalloon.getLocked() || tealBalloon.getLocked() || yellowBalloon.getLocked() || leadBalloon.getLocked()
            || gravityBalloon.getLocked()|| moonBalloon.getLocked()|| glitchBalloon.getLocked()|| radiationBalloon.getLocked()|| heartBalloon.getLocked() ||
        voidBalloon.getLocked() || lightBalloon.getLocked() || quantumBalloon.getLocked() || picassoBalloon.getLocked() || gliderBalloon.getLocked() ||
        magicBalloon.getLocked() || magnetBalloon.getLocked() || abyssBalloon.getLocked()) {
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
    public void unlockMoon() {
        moonBalloon.setLocked(false);
    }
    public void unlockGlitch() {
        glitchBalloon.setLocked(false);
    }
    public void unlockRadiation() {
        radiationBalloon.setLocked(false);
    }
    public void unlockHeart() {
        heartBalloon.setLocked(false);
    }

    public void unlockVoid() {
        voidBalloon.setLocked(false);
    }
    public void unlockLight() {
        lightBalloon.setLocked(false);
    }
    public void unlockQuantum() {
        quantumBalloon.setLocked(false);
    }
    public void unlockPicasso() {
        picassoBalloon.setLocked(false);
    }
    public void unlockGlider() {
        gliderBalloon.setLocked(false);
    }
    public void unlockMagic() {
        magicBalloon.setLocked(false);
    }
    public void unlockMagnet() {
        magnetBalloon.setLocked(false);
    }
    public void unlockAbyss() {
        abyssBalloon.setLocked(false);
    }



    public boolean getLeadLocked() {
        return leadBalloon.getLocked();
    }
    public boolean getGravityLocked() {
        return gravityBalloon.getLocked();
    }
    public boolean getMoonLocked() {
        return moonBalloon.getLocked();
    }
    public boolean getGlitchLocked() {
        return glitchBalloon.getLocked();
    }
    public boolean getRadiationLocked() {
        return radiationBalloon.getLocked();
    }
    public boolean getHeartLocked() {
        return heartBalloon.getLocked();
    }

    public boolean getVoidLocked() {
        return voidBalloon.getLocked();
    }
    public boolean getLightLocked() {
        return lightBalloon.getLocked();
    }
    public boolean getQuantumLocked() {
        return quantumBalloon.getLocked();
    }
    public boolean getPicassoLocked() {
        return picassoBalloon.getLocked();
    }
    public boolean getGliderLocked() {
        return gliderBalloon.getLocked();
    }
    public boolean getMagicLocked() {
        return magicBalloon.getLocked();
    }
    public boolean getMagnetLocked() {
        return magnetBalloon.getLocked();
    }
    public boolean getAbyssLocked() {
        return abyssBalloon.getLocked();
    }

    public void reset() {

        radiationBalloon.setLocked(true);
        heartBalloon.setLocked(true);
        leadBalloon.setLocked(true);
        gravityBalloon.setLocked(true);
        glitchBalloon.setLocked(true);
        moonBalloon.setLocked(true);

        voidBalloon.setLocked(true);
        lightBalloon.setLocked(true);
        quantumBalloon.setLocked(true);
        picassoBalloon.setLocked(true);
        gliderBalloon.setLocked(true);
        magicBalloon.setLocked(true);
        magnetBalloon.setLocked(true);
        abyssBalloon.setLocked(true);
    }
}
