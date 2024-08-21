package com.example.crossyroad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.DisplayMetrics;

public class Screen3 extends AppCompatActivity {
    private static int score = 0;
    private int livesLeft;
    private String diff;
    private String playerName;
    private String spriteName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent toThirdPage = getIntent();

        //Get the user-selected sprite, and use a switch statement to set a  variable equal
        //to a bitmap of the correct image
        spriteName = toThirdPage.getStringExtra("sprite");
        Bitmap playerSprite;
        switch (spriteName) {
        case "Green Bow Buzz":
            playerSprite = BitmapFactory.decodeResource(getResources(), R.drawable.mainchar2);
            break;
        case "Classic Buzz":
            playerSprite = BitmapFactory.decodeResource(getResources(), R.drawable.mainchar3);
            break;
        default:
            playerSprite = BitmapFactory.decodeResource(getResources(), R.drawable.mainchar1);
        }


        //Get the difficulty selected by the user and set a variable equal to the correct bitmap
        //Resize images so they all appear the same when on screen
        diff = toThirdPage.getStringExtra("difficulty");
        int health;
        switch (diff) {
        case "Easy":
            health = 3;
            break;
        case "Normal":
            health = 2;
            break;
        case "Difficult" :
            health = 1;
            break;
        default:
            health = 3;
            break;
        }

        playerName = toThirdPage.getStringExtra("playerName");

        //Get the dimensions of the screen
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        float dpHeight = displayMetrics.heightPixels;
        float dpWidth = displayMetrics.widthPixels;

        playerSprite = Bitmap.createScaledBitmap(playerSprite, 128,
                128, false);
        Player player = new Player(dpWidth / 2 - 64, dpHeight - 140,
                playerSprite, health, playerName);

        //Create the GameView object, which is a subclass of surface view, this is where the images
        //will be displayed
        GameView gameView = new GameView(this, player, dpWidth, dpHeight);

        setContentView(gameView);
        gameView.setBackground(AppCompatResources.getDrawable(this, R.drawable.finalbackground));
    }
    public static int getScore() {
        return score;
    }
    public Screen3 passContext() {
        return this;
    }
}