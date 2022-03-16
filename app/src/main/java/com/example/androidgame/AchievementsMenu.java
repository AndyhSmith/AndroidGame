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
    private Achievement achievementFortnight;
    private Achievement achievementExtraLife;
    private Achievement achievementGlitchFallout;

    private Achievement endOfReality;
    private Achievement moonlight;
    private Achievement splittingLight;
    private Achievement visionary;
    private Achievement exodus;
    private Achievement mystical;
    private Achievement forces;
    private Achievement theCreature;

    private int currentPage;
    private int totalPages;

    private Button nextPage;
    private Button previousPage;





    public AchievementsMenu(Context context, double width, double height) {

        this.context = context;

        double startHeight = 150;
        double padding = 20;
        double cardHeight = 100;

        currentPage = 1;
        totalPages = 2;

        nextPage = new Button(context, 128, 320, width * .75, height -300, "->", 50);
        previousPage = new Button(context, 128, 320, width / 4, height -300, "<-", 50);

        backButton = new Button(context, 128, 320, width / 2, height -150, "Back", 50);
        achievementHandfulOfPoints = new Achievement(context, "Handful Of Points - Unlocks Glitch Balloon", "Earn 5 points in one round", startHeight, width);
        achievementOffTheTop = new Achievement(context, "Off The Top - Unlocks Gravity Balloon", "Move the balloon off the top of the screen", startHeight + (cardHeight + padding) * 1, width);
        achievementMaxGravity = new Achievement(context, "Max Gravity - Unlocks Lead Balloon", "Reach maximum gravity", startHeight + (cardHeight + padding) * 2, width);
        achievementTwoBirdsOneStone = new Achievement(context, "Three Birds One Stone ", "Get three points in one bounce", startHeight + (cardHeight + padding) * 3, width);
        achievementBalloonMaster = new Achievement(context, "Balloon Master", "Unlock every balloon type", startHeight + (cardHeight + padding) * 4, width);
        achievementFortnight = new Achievement(context, "Fortnight - Unlocks Moon Balloon", "Earn 24 points in one round", startHeight + (cardHeight + padding) * 5, width);

        achievementExtraLife = new Achievement(context, "Extra Life - Unlocks Heart Balloon", "Earn 100 points in one round", startHeight + (cardHeight + padding) * 6, width);
        achievementGlitchFallout = new Achievement(context, "Glitch Fallout - Unlocks Toxic Balloon", "Earn 25 points using the glitch balloon", startHeight + (cardHeight + padding) * 7, width);

        endOfReality = new Achievement(context, "End Of Reality - Unlocks Void Balloon", "Earn a high score of 150 points", startHeight + (cardHeight + padding) * 8, width);
        moonlight = new Achievement(context, "Moon Light - Unlocks Light Balloon", "Earn 50 points using the moon balloon", startHeight + (cardHeight + padding) * 9, width);


        splittingLight = new Achievement(context, "Splitting Light - Unlocks Quantum Balloon", "Earn 25 points using the light balloon", startHeight + (cardHeight + padding) * 0, width);
        visionary = new Achievement(context, "Visionary - Unlocks Picasso's Balloon", "Earn 600 points in total high scores", startHeight + (cardHeight + padding) * 1, width);
        exodus = new Achievement(context, "Exodus - Unlocks Glider Balloon", "Earn 25 points using the fallout balloon", startHeight + (cardHeight + padding) * 2, width);
        mystical = new Achievement(context, "Mystical - Unlocks Magic Balloon", "Earn 25 points using the Quantum balloon", startHeight + (cardHeight + padding) * 3, width);
        forces = new Achievement(context, "Forces - Unlocks Magnetic Balloon", "Earn 50 points using the lead balloon", startHeight + (cardHeight + padding) * 4, width);
        theCreature = new Achievement(context, "The Creature - Unlocks Abyssal Balloon", "Earn 50 points using the void balloon", startHeight + (cardHeight + padding) * 5, width);



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

        canvas.drawText(Integer.toString(currentPage) + "/" + Integer.toString(totalPages), canvas.getWidth() / 2, canvas.getHeight() - 275, paint);


        nextPage.draw(canvas);
        previousPage.draw(canvas);

        backButton.draw(canvas);

        if (currentPage == 1) {

            achievementHandfulOfPoints.draw(canvas);
            achievementOffTheTop.draw(canvas);
            achievementMaxGravity.draw(canvas);
            achievementTwoBirdsOneStone.draw(canvas);
            achievementBalloonMaster.draw(canvas);
            achievementFortnight.draw(canvas);
            achievementExtraLife.draw(canvas);
            achievementGlitchFallout.draw(canvas);
            endOfReality.draw(canvas);
            moonlight.draw(canvas);
        }

        if (currentPage == 2) {
            splittingLight.draw(canvas);
            visionary.draw(canvas);
            exodus.draw(canvas);
            mystical.draw(canvas);
            forces.draw(canvas);
            theCreature.draw(canvas);
        }



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
    public void completeFortnight() {
        achievementFortnight.complete();
    }
    public void completeExtraLife() {
        achievementExtraLife.complete();
    }
    public void completeGlitchFallout() {
        achievementGlitchFallout.complete();
    }

    public void completeEndOfReality() {
        endOfReality.complete();
    }
    public void completeMoonLight() {
        moonlight.complete();
    }
    public void completeSplittingLight() {
        splittingLight.complete();
    }
    public void completeVisionary() {
        visionary.complete();
    }
    public void completeExodus() {
        exodus.complete();
    }
    public void completeMystical() {
        mystical.complete();
    }
    public void completeForces() {
        forces.complete();
    }
    public void completeTheCreature() {
        theCreature.complete();
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
    public boolean getFortnight() {
        return achievementFortnight.getCompleted();
    }
    public boolean getExtraLife() {
        return achievementExtraLife.getCompleted();
    }
    public boolean getGlitchFallout() {
        return achievementGlitchFallout.getCompleted();
    }

    public boolean getEndOfReality() {
        return endOfReality.getCompleted();
    }
    public boolean getMoonLight() {
        return moonlight.getCompleted();
    }
    public boolean getSplittingLight() {
        return splittingLight.getCompleted();
    }
    public boolean getVisionary() {
        return visionary.getCompleted();
    }
    public boolean getExodus() {
        return exodus.getCompleted();
    }
    public boolean getMystical() {
        return mystical.getCompleted();
    }
    public boolean getForces() {
        return forces.getCompleted();
    }
    public boolean getTheCreature() {
        return theCreature.getCompleted();
    }

    public void reset() {
        achievementHandfulOfPoints.uncomplete();
        achievementOffTheTop.uncomplete();
        achievementMaxGravity.uncomplete();
        achievementTwoBirdsOneStone.uncomplete();
        achievementBalloonMaster.uncomplete();
        achievementFortnight.uncomplete();
        achievementExtraLife.uncomplete();
        achievementGlitchFallout.uncomplete();

        endOfReality.uncomplete();
        moonlight.uncomplete();
        splittingLight.uncomplete();
        visionary.uncomplete();
        exodus.uncomplete();
        mystical.uncomplete();
        theCreature.uncomplete();
        forces.uncomplete();
    }
}
