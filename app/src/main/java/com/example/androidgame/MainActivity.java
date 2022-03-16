package com.example.androidgame;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Window;
import android.view.WindowManager;


public class MainActivity extends Activity {

    public Game game;

    public void check() {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set window to fullscreen.
        Window window = getWindow();
        window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );

        SharedPreferences sharedPref = this.getSharedPreferences("keepitalive", Context.MODE_PRIVATE);

        game = new Game(this, sharedPref);
        // Set content view to game
        setContentView(game);

    }
}