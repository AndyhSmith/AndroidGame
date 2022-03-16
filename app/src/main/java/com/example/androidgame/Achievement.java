package com.example.androidgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;

import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

public class Achievement {

    private Context context;
    private String title;
    private String description;
    private double positionY;
    private double width;
    private double padding;

    private Paint background;
    private Paint backgroundComplete;
    private Paint textColor;

    private boolean complete;

    public Achievement(Context context, String title, String description,  Double positionY, Double width) {
        this.context = context;
        this.title = title;
        this.description = description;
        this.positionY = positionY;
        this.padding = 20;
        this.width = width;

        this.complete = false;

        background = new Paint();
        background.setColor(ContextCompat.getColor(context, R.color.black));


        textColor = new Paint();
        textColor.setColor(ContextCompat.getColor(context, R.color.white));
        textColor.setTypeface(ResourcesCompat.getFont(context, R.font.vt323));
        textColor.setTextSize(50);


    }

    public void complete() {
        this.complete = true;
        this.background.setColor(ContextCompat.getColor(context, R.color.teal_200));
    }

    public void uncomplete() {
        this.complete = false;
        this.background.setColor(ContextCompat.getColor(context, R.color.black));
    }

    public void draw(Canvas canvas) {
        canvas.drawRect((float) padding, (float) positionY, (float)(width - (padding * 2)), (float) (positionY + 100), background);
        canvas.drawText(title, (float) (padding * 2), (float) positionY + 40, textColor);
        canvas.drawText(description, (float) (padding * 2), (float)positionY + 85, textColor);
    }

    public void update() {

    };

    public boolean getCompleted() {
        return this.complete;
    }
}
