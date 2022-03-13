package com.example.androidgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

public class Message {

    private Paint background;
    private Paint textColor;
    private String message;
    private String messageState;

    private double messageHeight;
    private double width;
    private double positionY;
    private int counter;
    private boolean showingMessage;
    private double speed;

    public Message(Context context, Double width) {
        this.messageHeight = 70;
        this.positionY = 100 +  this.messageHeight;
        this.width = width;
        this.speed = 3;

        background = new Paint();
        background.setColor(ContextCompat.getColor(context, R.color.black));

        textColor = new Paint();
        textColor.setColor(ContextCompat.getColor(context, R.color.white));
        textColor.setTypeface(ResourcesCompat.getFont(context, R.font.vt323));
        textColor.setTextSize(50);
    }

    public void draw(Canvas canvas) {
        if (showingMessage) {
            canvas.drawRect(0, (float) positionY - 5, (float) width, (float) (positionY + messageHeight), background);
            canvas.drawText(message, 20, (float) positionY + 50, textColor);
        }

    }

    public void update() {

        if (showingMessage) {
            Log.d("message", (String) messageState);
            if (messageState == "start") {
                positionY += this.speed;
                if (positionY >= 0) {
                    messageState = "display";
                }
            }
            else if (messageState == "display") {
                counter += 1;
                if (counter >= 240) {
                    messageState = "hide";
                }
            }
            else if (messageState == "hide") {
                positionY -= this.speed;
                if (positionY <= -messageHeight) {
                    showingMessage = false;
                }

            }
        }

    }

    public void postMessage(String message) {
        positionY = -messageHeight;
        showingMessage = true;
        messageState = "start";
        this.counter = 0;
        this.message = message;
    }

}
