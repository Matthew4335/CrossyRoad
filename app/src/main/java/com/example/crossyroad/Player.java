package com.example.crossyroad;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;

public class Player {

    private float playerX;
    private float playerY;
    private Bitmap playerSprite;
    private int playerScore = 0;
    private int highestScore = 0;

    private float lowestPosition;

    private float startingX;
    private float startingY;
    private int playerHealth;
    private RectF pHitBox;

    private String playerName;

    private boolean isColliding = false;
    public Bitmap getPlayerSprite() {
        return this.playerSprite;
    }
    public Player(float playerX, float playerY, Bitmap playerSprite, int playerHealth,
                  String playerName) {
        this.playerSprite = playerSprite;
        this.playerX = playerX;
        this.playerY = playerY;
        this.startingX = playerX;
        this.startingY = playerY;
        this.playerHealth = playerHealth;
        this.playerName = playerName;
        lowestPosition = playerY;
    }

    public void setLowestPosition(int lowestPosition) {
        this.lowestPosition = lowestPosition;
    }
    public void draw(Canvas canvas) {
        canvas.drawBitmap(playerSprite, playerX, playerY, null);
    }
    //update only calls updateScore, can be combined?

    public void update(boolean collided) {
        if (!collided) {
            updateScore();
        }
    }
    public void updateScore() {
        if (playerY < lowestPosition) {
            playerScore += 10;
            lowestPosition = playerY;
            if (playerY < 1500 && playerY > 1400) {
                playerScore += 20;
            } else if (playerY < 1400 && playerY > 1300) {
                playerScore += 10;
            } else if (playerY < 1300 && playerY > 1100) {
                playerScore += 5;
            } else if (checkWin()) {
                playerScore += 40;
            }
        }
        if (playerScore > highestScore) {
            highestScore = playerScore;
        }
    }

    public int getScore() {
        return playerScore;
    }

    public int getHighestScore() {
        return highestScore;
    }

    public void setPosition(float x, float y) {
        this.playerX = x;
        this.playerY = y;
    }

    public float getXPos() {

        return playerX;
    }

    public float getYPos() {
        return playerY;
    }

    public void moveLogic(float downX, float downY, float screenX, float screenY) {
        if (downY > playerY + 128 && downX > playerX
                && downX < playerX + 128) {

            if (playerY + 200 < screenY) {
                setPosition(playerX, playerY + 170);
            }
        } else if (downY < playerY && downX > playerX && downX < playerX + 128) {
            if (playerY - 170 > 0) {
                setPosition(playerX, playerY - 170);
            }
        } else if (downX < playerX && downY > playerY
                && downY < playerY + 128) {
            if (playerX - 170 > 0) {
                setPosition(playerX - 170, playerY);
            } else {
                setPosition(10, playerY);
            }
        } else if (downX > playerX + 128 && downY > playerY
                && downY < playerY + 128) {
            if (playerX + 128 + 170 < screenX) {
                setPosition(playerX + 170, playerY);
            } else {
                setPosition(screenX - 145, playerY);
            }
        }
        System.out.println(playerX);
        pHitBox = drawHitBox();
    }

    public int getHealth() {
        return playerHealth;
    }

    public void setScore(int score) {
        playerScore = score;
        if (playerScore > highestScore) {
            highestScore = playerScore;
        }
    }

    public void resetPosition() {
        setPosition(startingX, startingY);
        lowestPosition = startingY;
        //pHitBox = drawHitBox();
    }

    public void setHealth(int health) {
        playerHealth = health;
    }

    public String getName() {
        return this.playerName;
    }

    // Player Collision Handling Updates
    private void resetPlayer() {
        setScore(0);
        setHealth(playerHealth - 1);
        resetPosition();
    }

    public void handleCarCollision(boolean collisionDetected) {
        if (collisionDetected) {
            resetPlayer();
        }
    }

    public boolean checkWater() {
        if (playerY < 1100 && playerY > 150) {
            return true;
        }
        return false;
    }

    public void moveWithLog(int velocity) {
        setPosition(playerX + velocity, playerY);
    }

    public void handleInWater() {
        resetPlayer();
    }
    private RectF drawHitBox() {
        float left = playerX;
        float top = playerY;
        float right = playerX + playerSprite.getWidth();
        float bottom = playerY + playerSprite.getHeight();
        return new RectF(left, top, right, bottom);
    }
    public RectF getHitBox() {
        return pHitBox;
    }

    public boolean checkWin() {
        return playerY <= 250 && playerX >= 315 - 128 && playerX <= 640;
    }

}
