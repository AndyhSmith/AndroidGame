package com.example.androidgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

public class StartMenu {

    private Button startButton;
    private Context context;
    private Button highScoreButton;
    private Button statButton;

    public StartMenu(Context context, double width, double height) {

        this.context = context;
        startButton = new Button(context, 300, 400, width / 2, height / 2, "Start", 100);
        statButton = new Button(context, 100, 500, width / 2, height - 400, "Stats", 50);
        highScoreButton = new Button(context, 100, 500, width / 2, height - 200, "High Scores", 50);

    }

    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setTextAlign(Paint.Align.CENTER);
        int color = ContextCompat.getColor(context, R.color.text);
        paint.setColor(color);
        paint.setTextSize(100);
        canvas.drawText("Bouncing Ball", canvas.getWidth() / 2, 200, paint);

        startButton.draw(canvas);
        highScoreButton.draw(canvas);
        statButton.draw(canvas);
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
}
