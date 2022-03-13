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

    public SettingsMenu(Context context, double width, double height) {

        this.context = context;
        backButton = new Button(context, 128, 320, width / 2, height -150, "Back", 50);
        quickAimToggle = new Button(context, 128, 320, width /4, 200, "Quick Aim", 50);
        showInfoToggle = new Button(context, 128, 320, width /4, 350, "Show Info", 50);
        // show info gravity, wind

    }

    public void draw(Canvas canvas, Boolean quickAim, Boolean showInfo) {
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

    }

    public boolean checkPressBackButton(float x, float y) {
        return backButton.checkPress(x, y);
    }


    public boolean CheckPressQuickAimButton(float x, float y) {
        return quickAimToggle.checkPress(x, y);
    }

    public boolean CheckPressShowInfoButton(float x, float y) {
        return showInfoToggle.checkPress(x, y);
    }
}
