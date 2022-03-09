package com.example.androidgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

public class StatMenu {

    private Button backButton;
    private Context context;

    public StatMenu(Context context, double width, double height) {

        this.context = context;
        backButton = new Button(context, 100, 400, width / 2, height -100, "Back", 50);


    }

    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setTextAlign(Paint.Align.CENTER);
        int color = ContextCompat.getColor(context, R.color.text);
        paint.setColor(color);
        paint.setTextSize(100);
        canvas.drawText("Stats", canvas.getWidth() / 2, 200, paint);

        backButton.draw(canvas);
    }

    public boolean checkPressBackButton(float x, float y) {
        return backButton.checkPress(x, y);
    }

}
