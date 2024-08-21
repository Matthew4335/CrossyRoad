package com.example.crossyroad;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class WinScreen extends AppCompatActivity {
    private AnimationDrawable winAnimation;
    private String finalScore = "0";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //score needs to be passed into screen
        setContentView(R.layout.activity_win_screen);

        ImageView imageView = (ImageView) findViewById(R.id.winAnimationId);
        imageView.setBackgroundResource(R.drawable.win_animation);

        winAnimation = (AnimationDrawable) imageView.getBackground();
        winAnimation.start();

        Intent toWinScreen = getIntent();
        TextView score = (TextView) findViewById(R.id.scoreFin);
        finalScore = toWinScreen.getStringExtra("score");
        score.setText(finalScore);
        Button restartBtn = (Button) findViewById(R.id.restart);
        restartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toSecondPage = new Intent(WinScreen.this, Screen2.class);
                startActivity(toSecondPage);
            }
        });
        // Add an exit button
        Button exitBtn = (Button) findViewById(R.id.exit);
        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //exit the program
                finishAffinity();
            }
        });
    }
}
/*
<ImageView
        android:id="@+id/winAnimation"
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:scaleType="fitXY"
                android:src="@drawable/win_animation" />

 */