package com.example.androidgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;

import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

public class Button {
    private double height;
    private double width;
    private double positionX;
    private double positionY;
    private String text;
    private Context context;
    private int textSize;

    private Paint paint;
    private Paint textColor;

    private Drawable image;

    private final Rect textBounds = new Rect();

    public Button(Context context, double height, double width, double positionX, double positionY, String text, int textSize){
        this.context = context;
        this.height = height;
        this.width = width;
        this.positionX = positionX;
        this.positionY = positionY;
        this.text = text;
        this.textSize = textSize;

        //
        paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.player);
        paint.setColor(color);

        textColor = new Paint();
        int color2 = ContextCompat.getColor(context, R.color.black);
        textColor.setColor(color2);
//        textColor.setTextAlign(Paint.Align.CENTER);
        textColor.setTextSize(this.textSize - 10);
        Typeface customFont = ResourcesCompat.getFont(context, R.font.november);
        textColor.setTypeface(customFont);
        textColor.getTextBounds(text, 0, text.length(), textBounds);

        this.image = context.getResources().getDrawable(R.drawable.button320x128);

        Rect imageBounds = new Rect();
        imageBounds.left = (int) (positionX - (this.width / 2));
        imageBounds.top = (int) (positionY - (this.height / 2));
        imageBounds.right = (int) (positionX + (this.width / 2));
        imageBounds.bottom = (int) (positionY + (this.height / 2));
        image.setBounds(imageBounds);
    }

    public void draw(Canvas canvas) {
//        canvas.drawRect((float) (positionX - (width / 2)), (float) (positionY - (height / 2)), (float) (positionX + (width / 2)), (float) (positionY + (height / 2)), paint);
        image.draw(canvas);
        canvas.drawText(text, (float) ((positionX) - textBounds.exactCenterX()), (float) positionY - textBounds.exactCenterY(), textColor);


//        canvas.drawText(text, (float) positionX, (float) positionY, textColor);
    }

    public boolean checkPress(float x, float y) {
        if (x < positionX + (width / 2) && x > positionX - (width / 2) && y > positionY - (height / 2) && y < positionY + (height / 2)) {
            return true;
        }
        return false;
    }


}
