package com.example.crossyroad;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class GameOverScreen extends AppCompatActivity {
    //probably need points var here
    private String finalScore = "0";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //score needs to be passed into screen
        setContentView(R.layout.game_over);
        Intent toGameOver = getIntent();
        TextView score = (TextView) findViewById(R.id.scoreFin);
        finalScore = toGameOver.getStringExtra("score");
        score.setText(finalScore);
        Button restartBtn = (Button) findViewById(R.id.restart);
        restartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toSecondPage = new Intent(GameOverScreen.this, Screen2.class);
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
