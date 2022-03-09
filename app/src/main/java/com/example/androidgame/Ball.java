package com.example.androidgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.Log;

import androidx.core.content.ContextCompat;

public class Ball {
    private double positionXReset;
    private double positionYReset;

    private double positionX;
    private double positionY;
    private double radius;
    private Paint paint;
    private double gravity;

    private double xSpeed;
    private double ySpeed;

    private double rotation;

    private Drawable image;

    public Ball (Context context, double positionX, double positionY, double radius) {
        this.positionXReset = positionX;
        this.positionYReset = positionY;

        this.positionX = positionX;
        this.positionY = positionY;
        this.radius = radius;
        this.gravity = .2;
        this.xSpeed = 0;
        this.ySpeed = 0;

        this.rotation = 1;

        paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.ball);
        paint.setColor(color);

        image = context.getResources().getDrawable(R.drawable.balloonred63x75);
    }

    public void draw(Canvas canvas) {
//        canvas.drawCircle((float) positionX, (float) positionY, (float) radius, paint);

        Rect imageBounds = new Rect();
        imageBounds.left = (int) (positionX - 42);
        imageBounds.top = (int) (positionY - 50);
        imageBounds.right = (int) (positionX + 42);
        imageBounds.bottom = (int) (positionY + 50);

        image.setBounds(imageBounds);

//        canvas.save();
//        canvas.rotate((float) this.rotation, (float) this.positionX, (float) this.positionY);
        image.draw(canvas);

//        canvas.restore();

    }

    public void update() {
        this.ySpeed += this.gravity;
        this.positionX += this.xSpeed;
        this.positionY += this.ySpeed;
        this.rotation -= 3;
    }


    public void checkCollision(Player player) {

        double b = player.getPositionY() - ((-1/player.getAimSlope()) * player.getPositionX());
        double b2 = positionY - (player.getAimSlope() * positionX);
        double x =  (b2 - b) / ((-1/player.getAimSlope()) - player.getAimSlope());
        double y = player.getAimSlope() * x + b2;
        double d = Math.sqrt((positionX - x) * (positionX - x)  +  (positionY - y) * (positionY - y));
        d = Math.abs(d);

        double actualWidth = Math.abs(Math.cos(Math.toRadians(player.getRotation()))) * player.getWidth();
        double actualHeight = Math.abs(Math.sin(Math.toRadians(player.getRotation()))) * player.getWidth();




        if (d < -10 + radius && positionX > player.getPositionX() - actualWidth - radius && positionX < player.getPositionX() + actualWidth + radius
            && positionY > player.getPositionY() - actualHeight - radius && positionY < player.getPositionY() + actualHeight + radius) {

            double velocity = Math.sqrt(xSpeed * xSpeed + ySpeed * ySpeed);
            this.ySpeed = Math.cos(Math.toRadians(player.getRotation())) * velocity;
            this.xSpeed = Math.sin(Math.toRadians(player.getRotation())) * velocity;

            this.ySpeed = -this.ySpeed;
            this.positionY += this.ySpeed;
        }
    }

    public boolean checkCollision(Collectible point) {
        double distance = Math.sqrt((positionX - point.getPositionX()) * (positionX - point.getPositionX()) + (positionY - point.getPositionY()) * (positionY - point.getPositionY()));
        if (distance < radius + point.getRadius()) {
            point.randomLocation();
            return true;
        }
        return false;
    }

    public boolean checkOutOfBounds(int width, int height) {

        if (this.positionY > height + this.radius || this.positionX > width + radius || this.positionX < 0 - radius) {
            this.reset();
            return true;
        }
        return false;
    }

    private void reset() {
        this.positionX = positionXReset;
        this.positionY = positionYReset;
        this.xSpeed = 0;
        this.ySpeed = 0;

    }
}
