package com.example.androidgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
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

    private double savedYSpeed;
    private double ySpeed;

    private double rotation;

    private Context context;

    private Drawable image;

    private int height;
    private int width;

    private int pointsPerBounce;

    private int counter;

    private MediaPlayer ring;
    private MediaPlayer collectPoint;
    private MediaPlayer lose;

    private double voidX;
    private double voidY;
    private double voidRadius;

    private double magicX;
    private double magicY;

    private boolean quantumCheck;


    public Ball (Context context, double positionX, double positionY, double radius, int height, int width) {
        this.height = height;
        this.width = width;
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

        this.savedYSpeed = 0;
        this.ySpeed = 0;

        this.magicX = 0;
        this.magicY = 0;

        this.rotation = 1;

        this.context = context;

        this.counter = 0;

        this.voidRadius = 200;
        this.voidX = width / 2;
        this.voidY = height + voidRadius;

        this.quantumCheck = false;



        paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.black);
        paint.setColor(color);

        image = context.getResources().getDrawable(R.drawable.balloonred63x75);

        ring= MediaPlayer.create(context,R.raw.hit);
        collectPoint = MediaPlayer.create(context, R.raw.collect);
        lose = MediaPlayer.create(context, R.raw.lose);

    }

    public double getGravity() {
        return gravity;
    }

    public void draw(Canvas canvas, String balloonType) {
//        canvas.drawCircle((float) positionX, (float) positionY, (float) radius, paint);

        if (quantumCheck && balloonType == "quantum") {
            radius = Math.random() * 150 + 50;
        }

        Rect imageBounds = new Rect();
        imageBounds.left = (int) (positionX - 42);
        imageBounds.top = (int) (positionY - 50);
        imageBounds.right = (int) (positionX + 42);
        imageBounds.bottom = (int) (positionY + 50);

        if (balloonType == "quantum") {
            imageBounds.left = (int) (positionX - (radius * .84));
            imageBounds.top = (int) (positionY - radius);
            imageBounds.right = (int) (positionX + (radius * .84));
            imageBounds.bottom = (int) (positionY + radius);
            quantumCheck = false;
        }
        else if (balloonType == "heart") {
            imageBounds.left = (int) (positionX - 50);
            imageBounds.right = (int) (positionX + 50);
        }

        image.setBounds(imageBounds);

//        canvas.save();
//        canvas.rotate((float) this.rotation, (float) this.positionX, (float) this.positionY);
        if (balloonType == "glitch") {
            if ((int)(counter / 30) % 2 == 0 ) {
                image.draw(canvas);
            }
        } else {
            image.draw(canvas);
        }

        if (balloonType == "void") {
            int voidSpeed = 1;
            if (voidX < positionX) {
                voidX += voidSpeed;
            } else {
                voidX -= voidSpeed;
            }
            if (voidY < positionY) {
                voidY += voidSpeed;
            } else {
                voidY -= voidSpeed;
            }

            canvas.drawCircle((float) voidX, (float) voidY,(float) voidRadius, paint);

            Paint quickColor = new Paint();
            quickColor.setColor(Color.rgb(200, 0, 0));
            canvas.drawCircle((float) voidX, (float) voidY,(float) 5, quickColor);
        }


//        canvas.restore();

    }

    public void update(String balloonType, boolean sounds, int width) {
        this.ySpeed += this.gravity;
        if ((Math.abs(this.ySpeed) < Math.abs(this.gravity) && this.gravity < 1 && this.gravity > -1) || balloonType == "magic") {
            Log.d("b", balloonType);
            if (balloonType == "gravity") {
                this.gravity -= 0.005;
            }
            else if (balloonType == "moon") {
                this.gravity += 0.25;
            }
            else if (balloonType == "quantum") {
                this.gravity = Math.random() * .95 + .05;
            }
            else if (balloonType == "magic") {
                magicX += (Math.random() - .5) / 10;
                magicY += (Math.random() - .5) / 10;
                int limit = 10;
                if (xSpeed < limit && xSpeed > -limit) {
                    xSpeed += magicX;
                }
                if (ySpeed < limit && ySpeed > -limit) {
                    ySpeed += magicY;
                }

            }
            else {
                this.gravity += 0.005;
            }

            Log.d("gravity", Double.toString(this.gravity));
        }
        this.positionX += this.xSpeed;
        this.positionY += this.ySpeed;
        this.rotation -= 3;

        if (balloonType == "glitch") {
            counter += 1;
        }
        if (balloonType == "glider") {
            if (positionX < width / 2) { // left of mid screen
                this.xSpeed -= .1;
            } else {
                this.xSpeed += .1;
            }

        }

    }


    public void checkCollision(Player player, boolean sounds, String balloonType) {

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

            if (balloonType == "abyss" && ySpeed < 0) {
                return;
            }


            double velocity = Math.sqrt(xSpeed * xSpeed + ySpeed * ySpeed);

            this.ySpeed = Math.cos(Math.toRadians(player.getRotation())) * velocity;
            this.xSpeed = Math.sin(Math.toRadians(player.getRotation())) * velocity;

            if (balloonType == "picasso") {
                double rand = 0;
                if (Math.random() > .5) {
                    rand = 40;
                } else {
                    rand = -40;
                }

                this.ySpeed = Math.cos(Math.toRadians(player.getRotation() + rand)) * velocity;
                this.xSpeed = Math.sin(Math.toRadians(player.getRotation() + rand)) * velocity;
            }

            this.ySpeed = -this.ySpeed;
            this.positionY += this.ySpeed;

            this.pointsPerBounce = 0;

            if (sounds) {
                ring.start();
            }
            quantumCheck = true;
        }
    }

    public boolean checkCollision(Collectible point, boolean sounds, String balloonType) {
        double distance = Math.sqrt((positionX - point.getPositionX()) * (positionX - point.getPositionX()) + (positionY - point.getPositionY()) * (positionY - point.getPositionY()));
        if (balloonType == "abyss"  && distance < radius + point.getRadius() - 10) {
            if (balloonType == "abyss" && ySpeed > 0) {
                boolean corrupted = point.getCorrupted();
                point.randomLocation();
                if (sounds) {
                    collectPoint.start();
                }
                Log.d("check", Boolean.toString(point.getCorrupted()));
                if (corrupted) {
                    this.pointsPerBounce += 1;
                    return false;
                }
                return true;
            } else if (ySpeed < 0) {
                point.corrupt();
                if (sounds) {
                    collectPoint.start();
                }
            }


        }
        else if (distance < radius + point.getRadius() - 10 && balloonType != "abyss") {
            point.randomLocation();
            this.pointsPerBounce += 1;
            if (sounds) {
                collectPoint.start();
            }
            return true;
        }
        return false;
    }

    public boolean checkOutOfBounds(int width, int height, Boolean extraLife, Collectible point, String balloonType, boolean sounds) {

        boolean voidCollision = false;
        if (balloonType == "void") {
            double d = Math.pow(voidX - positionX, 2) + Math.pow(voidY - positionY, 2);
            if (d <= voidRadius * voidRadius) {
                voidCollision = true;
            }
        }

        if (this.positionY > height + this.radius || this.positionX > width + radius || this.positionX < 0 - radius || voidCollision) {
            pointsPerBounce = 0;
            point.randomLocation();
            this.reset(extraLife);
            if (sounds) {
                lose.start();
            }
            return true;
        }
        else if (this.positionY < 0 - radius && balloonType == "gravity") {
            pointsPerBounce = 0;
            point.randomLocation();
            this.reset(extraLife);
            if (sounds) {
                lose.start();
            }
            return true;
        }
        if (this.positionY < 0 - radius && (balloonType == "light" || balloonType == "magic")) {
            pointsPerBounce = 0;
            point.randomLocation();
            this.reset(extraLife);
            if (sounds) {
                lose.start();
            }
            return true;
        }
        return false;
    }

    private void reset(Boolean extraLife) {
        if (!extraLife) {
            pointsPerBounce = 0;
            this.gravity = this.savedGravity;
            this.voidX = width / 2;
            this.voidY = height + voidRadius;
        }
        this.positionX = positionXSaved;
        this.positionY = positionYSaved;
        this.xSpeed = 0;
        this.ySpeed = this.savedYSpeed;
        this.quantumCheck = false;
        magicX = 0;
        magicY = 0;




    }

    public void setBalloonType(String b) {
        this.gravity = this.permGravity;
        this.savedGravity = this.permGravity;
        this.positionY = this.positionYPerm;
        this.positionYSaved = this.positionYPerm;
        this.ySpeed = 0;
        this.savedYSpeed = 0;
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
        else if (b.equals("moon")) {
            this.gravity = 0.02;
            this.savedGravity = this.gravity;
            image = context.getResources().getDrawable(R.drawable.balloonmoon84x100);
        }
        else if (b.equals("glitch")) {
            image = context.getResources().getDrawable(R.drawable.balloonglitch84x100);
        }
        else if (b.equals("heart")) {
            image = context.getResources().getDrawable(R.drawable.balloonheart100x100);
        }
        else if (b.equals("radiation")) {
            image = context.getResources().getDrawable(R.drawable.balloonradiation81x100);
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

        else if (b.equals("void")) {
            image = context.getResources().getDrawable(R.drawable.balloonvoid84x100);
        }
        else if (b.equals("light")) {
            image = context.getResources().getDrawable(R.drawable.balloonlight84x100);
            this.gravity = 0;
            this.savedGravity = 0;
            this.savedYSpeed = 20;
            this.ySpeed = 20;
        }
        else if (b.equals("quantum")) {
            image = context.getResources().getDrawable(R.drawable.balloonquantum210x250);
        }
        else if (b.equals("picasso")) {
            image = context.getResources().getDrawable(R.drawable.balloonpicasso84x100);
        }
        else if (b.equals("glider")) {
            image = context.getResources().getDrawable(R.drawable.balloonglider84x100);
        }
        else if (b.equals("magic")) {
            image = context.getResources().getDrawable(R.drawable.balloonmagic84x100);
            this.gravity = 0;
            this.savedGravity = 0;
            this.positionYSaved = height / 2;
            this.positionY = height / 2;
        }
        else if (b.equals("magnet")) {
            image = context.getResources().getDrawable(R.drawable.balloonmagnet84x100);
            this.gravity = 1.2;
            this.savedGravity = this.gravity;
        }
        else if (b.equals("abyss")) {
            image = context.getResources().getDrawable(R.drawable.balloonabyss84x100);
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

    public double getXSpeed() {
        return xSpeed;
    }

    public double getYSpeed() {
        return ySpeed;
    }

    public double getPositionX() {
        return positionX;
    }
    public double getPositionY() {
        return positionY;
    }

    public boolean checkCollisionItemBoost(Item itemBoost, boolean sounds) {
        double distance = Math.sqrt((positionX - itemBoost.getPositionX()) * (positionX - itemBoost.getPositionX()) + (positionY - itemBoost.getPositionY()) * (positionY - itemBoost.getPositionY()));
        if (distance < radius + itemBoost.getRadius() - 10) {
            ySpeed -= 4;
            itemBoost.randomLocation(false);
            if (sounds) {
                collectPoint.start();
            }
            return true;
        }
        return false;
    }

    public Object getStreak() {
        return pointsPerBounce;
    }

    public double getY() {
        return positionY;
    }
}
