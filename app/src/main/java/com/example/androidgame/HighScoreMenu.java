package com.example.androidgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;

import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

public class HighScoreMenu {

    private Button backButton;
    private Context context;

    private Paint text;

    public HighScoreMenu(Context context, double width, double height) {

        this.context = context;
        backButton = new Button(context, 128, 320, width / 2, height -150, "Back", 50);

        text = new Paint();
        text.setTextSize(80);
        Typeface customFont = ResourcesCompat.getFont(context, R.font.vt323);
        text.setTypeface(customFont);


    }

    public void draw(Canvas canvas, int highscore, int highscoreLead, int highscoreGravity) {
        Paint paint = new Paint();
        paint.setTextAlign(Paint.Align.CENTER);
        int color = ContextCompat.getColor(context, R.color.text);
        paint.setColor(color);
        paint.setTextSize(100);
        Typeface customFont = ResourcesCompat.getFont(context, R.font.vt323);
        paint.setTypeface(customFont);
        canvas.drawText("High Scores", canvas.getWidth() / 2, 100, paint);

        canvas.drawText("High Score: " + Integer.toString(highscore), 20, 200, text);
        canvas.drawText("High Score Lead: " + Integer.toString(highscoreLead), 20, 300, text);
        canvas.drawText("High Score Gravity: " + Integer.toString(highscoreGravity), 20, 400, text);

        backButton.draw(canvas);
    }

    public boolean checkPressBackButton(float x, float y) {
        return backButton.checkPress(x, y);
    }


}
