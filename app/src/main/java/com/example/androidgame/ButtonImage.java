package com.example.androidgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

public class ButtonImage extends Button {

    private Drawable image;

    public ButtonImage(Context context, double height, double width, double positionX, double positionY, Drawable image) {
        super(context, height, width, positionX, positionY, "", 0);
        this.image = image;
        Rect imageBounds = new Rect();
        imageBounds.left = (int) (positionX - 424);
        imageBounds.top = (int) (positionY - 116);
        imageBounds.right = (int) (positionX + 425);
        imageBounds.bottom = (int) (positionY + 116);
        image.setBounds(imageBounds);
    }

    @Override
    public void draw(Canvas canvas) {
        image.draw(canvas);
    }
}
