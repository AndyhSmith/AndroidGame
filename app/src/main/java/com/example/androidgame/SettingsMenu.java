package com.example.androidgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;

import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

public class SettingsMenu {

    private Button backButton;
    private Context context;

    private Button quickAimToggle;
    private Button showInfoToggle;
    private Button buttonBackgroundMusic;
    private Button buttonSounds;
    private Button resetData;

    private int resetCounter;

    public SettingsMenu(Context context, double width, double height) {

        this.context = context;
        backButton = new Button(context, 128, 320, width / 2, height -150, "Back", 50);
        quickAimToggle = new Button(context, 128, 320, width /4, 200, "Quick Aim", 50);
        showInfoToggle = new Button(context, 128, 320, width /4, 350, "Show Info", 50);
        buttonBackgroundMusic = new Button(context, 128, 320, width /4, 500, "Music", 50);
        buttonSounds = new Button(context, 128, 320, width /4, 650, "Sounds", 50);

        resetData = new Button(context, 128, 320, width /4, 800, "Reset Data", 50);

        resetCounter = 4;

    }

    public void draw(Canvas canvas, boolean quickAim, boolean showInfo, boolean sounds, boolean backgroundMusic) {
        Paint paint = new Paint();
        paint.setTextAlign(Paint.Align.CENTER);
        int color = ContextCompat.getColor(context, R.color.text);
        paint.setColor(color);
        paint.setTextSize(100);
        Typeface customFont = ResourcesCompat.getFont(context, R.font.vt323);
        paint.setTypeface(customFont);
        canvas.drawText("Settings", canvas.getWidth() / 2, 100, paint);

        backButton.draw(canvas);
        quickAimToggle.draw(canvas);
        if (quickAim) {
            canvas.drawText("On", (float) ((canvas.getWidth() / 3) * 2), 225, paint);
        }
        else {
            canvas.drawText("Off", (float) ((canvas.getWidth() / 3) * 2), 225, paint);
        }

        showInfoToggle.draw(canvas);
        if (showInfo) {
            canvas.drawText("On", (float) ((canvas.getWidth() / 3) * 2), 375, paint);
        }
        else {
            canvas.drawText("Off", (float) ((canvas.getWidth() / 3) * 2), 375, paint);
        }

        buttonBackgroundMusic.draw(canvas);
        if (backgroundMusic) {
            canvas.drawText("On", (float) ((canvas.getWidth() / 3) * 2), 525, paint);
        }
        else {
            canvas.drawText("Off", (float) ((canvas.getWidth() / 3) * 2), 525, paint);
        }

        buttonSounds.draw(canvas);
        if (sounds) {
            canvas.drawText("On", (float) ((canvas.getWidth() / 3) * 2), 675, paint);
        }
        else {
            canvas.drawText("Off", (float) ((canvas.getWidth() / 3) * 2), 675, paint);
        }

        resetData.draw(canvas);
        if (resetCounter <= 3) {
            canvas.drawText(Integer.toString(resetCounter), (float) ((canvas.getWidth() / 3) * 2), 825, paint);
        }

    }

    public boolean checkPressBackButton(float x, float y) {
        boolean pressed = backButton.checkPress(x, y);
        if (pressed) {
            resetCounter = 4;
        }
        return pressed;
    }


    public boolean CheckPressQuickAimButton(float x, float y) {
        return quickAimToggle.checkPress(x, y);
    }

    public boolean CheckPressShowInfoButton(float x, float y) {
        return showInfoToggle.checkPress(x, y);
    }

    public boolean CheckPressSoundsButton(float x, float y) {
        return buttonSounds.checkPress(x, y);
    }

    public boolean CheckPressBackgroundMusicButton(float x, float y) {
        return buttonBackgroundMusic.checkPress(x, y);
    }

    public int CheckPressResetDataButton(float x, float y) {
        boolean pressed = resetData.checkPress(x, y);

        if (pressed) {
            resetCounter -= 1;
            if (resetCounter <= 0) {
                resetCounter = 4;
                return -1;
            }

            return resetCounter;
        }
        return 10;
    }
}
