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

    public void draw(Canvas canvas, int highScore, int highScoreLead, int highScoreGravity, int highScoreMoon, int highScoreGlitch, int highScoreHeart, int highScoreToxic,
                     int highScoreVoid, int highScoreLight, int highScoreQuantum, int highScorePicasso, int highScoreGlider,
                     int highScoreMagic, int highScoreMagnet, int highScoreAbyss, int highScoreTotal) {
        Paint paint = new Paint();
        paint.setTextAlign(Paint.Align.CENTER);
        int color = ContextCompat.getColor(context, R.color.text);
        paint.setColor(color);
        paint.setTextSize(100);
        Typeface customFont = ResourcesCompat.getFont(context, R.font.vt323);
        paint.setTypeface(customFont);
        canvas.drawText("High Scores", canvas.getWidth() / 2, 100, paint);

        int textHeight = 75;
        int topPadding = 175;

        canvas.drawText("Normal: " + Integer.toString(highScore), 20, topPadding + (75 * 0), text);
        canvas.drawText("Lead: " + Integer.toString(highScoreLead), 20, topPadding + (75 * 1), text);
        canvas.drawText("Gravity: " + Integer.toString(highScoreGravity), 20, topPadding + (75 * 2), text);
        canvas.drawText("Moon: " + Integer.toString(highScoreMoon), 20, topPadding + (75 * 3), text);
        canvas.drawText("Glitch: " + Integer.toString(highScoreGlitch), 20, topPadding + (75 * 4), text);
        canvas.drawText("Heart: " + Integer.toString(highScoreHeart), 20, topPadding + (75 * 5), text);
        canvas.drawText("Toxic: " + Integer.toString(highScoreToxic), 20, topPadding + (75 * 6), text);

        canvas.drawText("Void: " + Integer.toString(highScoreVoid), 20, topPadding + (75 * 7), text);
        canvas.drawText("Light: " + Integer.toString(highScoreLight), 20, topPadding + (75 * 8), text);
        canvas.drawText("Quantum: " + Integer.toString(highScoreQuantum), 20, topPadding + (75 * 9), text);
        canvas.drawText("Picasso: " + Integer.toString(highScorePicasso), 20, topPadding + (75 * 10), text);
        canvas.drawText("Glider: " + Integer.toString(highScoreGlider), 20, topPadding + (75 *11), text);
        canvas.drawText("Magic: " + Integer.toString(highScoreMagic), 20, topPadding + (75 * 12), text);
        canvas.drawText("Magnet: " + Integer.toString(highScoreMagnet), 20, topPadding + (75 * 13), text);
        canvas.drawText("Abyss: " + Integer.toString(highScoreAbyss), 20, topPadding + (75 * 14), text);

        canvas.drawText("High Score Total: " + Integer.toString(highScoreTotal), 20, topPadding + (75 * 15), text);

        backButton.draw(canvas);
    }

    public boolean checkPressBackButton(float x, float y) {
        return backButton.checkPress(x, y);
    }


}
