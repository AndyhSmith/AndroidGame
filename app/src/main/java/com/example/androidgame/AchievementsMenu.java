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

    public AchievementsMenu(Context context, double width, double height) {

        this.context = context;
        backButton = new Button(context, 100, 400, width / 2, height -150, "Back", 50);


    }

    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setTextAlign(Paint.Align.CENTER);
        int color = ContextCompat.getColor(context, R.color.text);
        paint.setColor(color);
        paint.setTextSize(100);
        Typeface customFont = ResourcesCompat.getFont(context, R.font.vt323);
        paint.setTypeface(customFont);
        canvas.drawText("Achievements", canvas.getWidth() / 2, 200, paint);

        backButton.draw(canvas);
    }

    public boolean checkPressBackButton(float x, float y) {
        return backButton.checkPress(x, y);
    }


}
