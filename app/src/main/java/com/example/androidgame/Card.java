package com.example.androidgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.Log;

import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

public class Card {

    private double width;
    private double height;
    private double positionX;
    private double positionY;
    private String text;

    private Drawable image;

    private Paint backgroundColor;
    private Paint textColor;
    private Paint selectedColor;

    private final Rect textBounds = new Rect();

    private boolean selected;
    private boolean locked;

    private Drawable cardLocked;
    private Drawable cardShown;

    public Card(Context context, double positionX, double positionY, double width, double height, Drawable image, String text) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.width = width;
        this.height = height;
        this.image = image;
        this.text = text;
        this.selected = false;
        this.locked = true;

        cardLocked = context.getResources().getDrawable(R.drawable.cardlocked280x376);
        this.cardShown = cardLocked;

        backgroundColor = new Paint();
        backgroundColor.setColor(ContextCompat.getColor(context, R.color.cardbackground));

        selectedColor = new Paint();
        selectedColor.setColor(Color.GREEN);

        Rect imageBounds = new Rect();
        imageBounds.left = (int) positionX;
        imageBounds.top = (int) positionY;
        imageBounds.right = (int) (positionX + width);
        imageBounds.bottom = (int) (positionY + height);
        cardShown.setBounds(imageBounds);
        image.setBounds(imageBounds);
        cardLocked.setBounds(imageBounds);

        textColor = new Paint();
        int color2 = ContextCompat.getColor(context, R.color.black);
        textColor.setColor(color2);
        textColor.setTextSize(50);
        Typeface customFont = ResourcesCompat.getFont(context, R.font.november);
        textColor.setTypeface(customFont);
        textColor.getTextBounds(text, 0, text.length(), textBounds);
    }

    public void draw(Canvas canvas) {
//        canvas.drawRect((float) positionX, (float) positionY, (float) (positionX + width), (float) (positionY + height), backgroundColor);



        cardShown.draw(canvas);
        canvas.drawText(text, (float) (((positionX + (width / 2)) - textBounds.exactCenterX())), (float) positionY - textBounds.exactCenterY() + 310, textColor);
        if (this.selected) {
            canvas.drawCircle((float) positionX + 50, (float) positionY + 50, 16, selectedColor);
        }

    }

    public void update() {

    }

    public boolean checkPress(float x, float y) {
        if (x > positionX && x < positionX + width && y > positionY && y < positionY + height) {
            if (!locked) {
                selected = true;
            }

            return true;
        }
        return false;
    }

    public void unselect() {
        this.selected = false;
    }

    public void select() {
        this.selected = true;
    }

    public void setLocked(boolean value) {

        this.locked = value;
        if (this.locked) {
            cardShown = cardLocked;
        } else {
            cardShown = image;
        }
    }

    public boolean getLocked() {
        return this.locked;
    }
}
