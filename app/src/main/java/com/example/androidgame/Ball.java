package com.example.androidgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.Log;

import androidx.core.content.ContextCompat;

public class Ball {
    private double positionXPerm;
    private double positionYPerm;

    private double positionXSaved;
    private double positionYSaved;

    private double positionX;
    private double positionY;
    private double radius;
    private Paint paint;

    private double permGravity;
    private double savedGravity;
    private double gravity;

    private double xSpeed;
    private double ySpeed;

    private double rotation;

    private Context context;

    private Drawable image;

    private double height;

    private int pointsPerBounce;

    public Ball (Context context, double positionX, double positionY, double radius, double height) {
        this.height = height;
        this.positionXPerm = positionX;
        this.positionYPerm = positionY;

        this.positionXSaved = positionX;
        this.positionYSaved = positionY;

        this.positionX = positionX;
        this.positionY = positionY;
        this.radius = radius;

        this.permGravity = 0.15;
        this.savedGravity = this.permGravity;
        this.gravity = this.savedGravity;

        this.pointsPerBounce = 0;

        this.xSpeed = 0;
        this.ySpeed = 0;

        this.rotation = 1;

        this.context = context;

        paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.ball);
        paint.setColor(color);

        image = context.getResources().getDrawable(R.drawable.balloonred63x75);
    }

    public double getGravity() {
        return gravity;
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

    public void update(String balloonType) {
        this.ySpeed += this.gravity;
        if (Math.abs(this.ySpeed) < Math.abs(this.gravity) && this.gravity < 1 && this.gravity > -1) {
            if (balloonType == "gravity") {
                this.gravity -= 0.005;
            } else {
                this.gravity += 0.005;
            }

            Log.d("gravity", Double.toString(this.gravity));
        }
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


        if (Double.isNaN(d)) {
            d = 0;
        }

        Log.d("actualWidth", Double.toString(actualWidth));
        Log.d("actualHeight", Double.toString(actualHeight));
        Log.d("d", Double.toString(d));

        if (d < -10 + radius && positionX > player.getPositionX() - actualWidth - radius && positionX < player.getPositionX() + actualWidth + radius
            && positionY > player.getPositionY() - actualHeight - radius && positionY < player.getPositionY() + actualHeight + radius) {

            double velocity = Math.sqrt(xSpeed * xSpeed + ySpeed * ySpeed);
            this.ySpeed = Math.cos(Math.toRadians(player.getRotation())) * velocity;
            this.xSpeed = Math.sin(Math.toRadians(player.getRotation())) * velocity;

            this.ySpeed = -this.ySpeed;
            this.positionY += this.ySpeed;

            this.pointsPerBounce = 0;
        }
    }

    public boolean checkCollision(Collectible point) {
        double distance = Math.sqrt((positionX - point.getPositionX()) * (positionX - point.getPositionX()) + (positionY - point.getPositionY()) * (positionY - point.getPositionY()));
        if (distance < radius + point.getRadius()) {
            point.randomLocation();
            pointsPerBounce += 1;
            return true;
        }
        return false;
    }

    public boolean checkOutOfBounds(int width, int height) {
        pointsPerBounce = 0;
        if (this.positionY > height + this.radius || this.positionX > width + radius || this.positionX < 0 - radius) {
            this.reset();
            return true;
        }
        return false;
    }

    private void reset() {
        pointsPerBounce = 0;
        this.positionX = positionXSaved;
        this.positionY = positionYSaved;
        this.xSpeed = 0;
        this.ySpeed = 0;
        this.gravity = this.savedGravity;


    }

    public void setBalloonType(String b) {
        this.gravity = this.permGravity;
        this.savedGravity = this.permGravity;
        this.positionY = this.positionYPerm;
        this.positionYSaved = this.positionYPerm;
        if (b.equals("red")) {
            image = context.getResources().getDrawable(R.drawable.balloonred63x75);
        }
        else if (b.equals("purple")) {
            image = context.getResources().getDrawable(R.drawable.balloonpurple84x100);
        }
        else if (b.equals("teal")) {
            image = context.getResources().getDrawable(R.drawable.balloonteal84x100);
        }
        else if (b.equals("yellow")) {
            image = context.getResources().getDrawable(R.drawable.balloonyellow84x100);
        }
        else if (b.equals("lead")) {
            image = context.getResources().getDrawable(R.drawable.balloonlead84x100);
            this.gravity = 0.8;
            this.savedGravity = this.gravity;
        }
        else if (b.equals("gravity")) {
            image = context.getResources().getDrawable(R.drawable.balloongravity84x100);
            this.gravity = - this.permGravity;
            this.savedGravity = this.gravity;
            this.positionYSaved = height - 200;
            this.positionY = height - 200;
        }
    }

    public boolean checkAchievementOffTheTop() {
        if (positionY + 42 < 0) {
            return true;
        }
        return false;
    }

    public boolean checkAchievementMaxGravity() {
        if (gravity > 1 || gravity < -1) {
            return true;
        }
        return false;
    }

    public boolean checkAchievementTwoBirdsOneStone() {
        if (pointsPerBounce >= 3) {
            return true;
        }
        return false;
    }
}
