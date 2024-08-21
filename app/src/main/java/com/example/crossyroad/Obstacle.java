package com.example.crossyroad;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;

public class Obstacle {
    private float startingX;
    private float startingY;
    private float obstacleX;
    private float screenX;

    private Bitmap obstacleSprite;
    private int obstacleVelocity;
    private RectF hitBox;
    // has generic elements of a vehicle
    // For loop will create instances of reach vehicle where it needs to be?
    //Lanes are 57 dp wide
    public Obstacle(Bitmap obstacleSprite, int velocity, float startingX, float startingY,
                   float screenX) {
        this.obstacleSprite = obstacleSprite;
        obstacleVelocity = velocity;
        resetPosition();
        this.startingX = startingX;
        this.startingY = startingY;
        this.screenX = screenX;
        this.obstacleX = startingX;
        //this.hitBox = drawHitBox();
    }

    public int getObstacleVelocity() {
        return obstacleVelocity;
    }
    public void resetPosition() {
        obstacleX = startingX;
        // higher than 200
    }
    public void update(float vehicleWidth) {
        obstacleX += obstacleVelocity;
        //hitBox = drawHitBox();
        if (obstacleX < -1 * vehicleWidth || obstacleX > screenX) {
            resetPosition();
        }
    }

    public float getObstacleWidth() {
        return obstacleSprite.getWidth();
    }
    public void draw(Canvas canvas) {
        canvas.drawBitmap(obstacleSprite, obstacleX, startingY, null);
    }
    public float getObstacleX() {
        return this.obstacleX;
    }
    public float getObstacleY() {
        return this.startingY;
    }

    public Bitmap getObstacleSprite() {
        return obstacleSprite;
    }
    //public RectF getHitBox(){return hitBox; }
    public RectF getHitBox() {
        float left = obstacleX;
        float top = startingY;
        float right = obstacleX + obstacleSprite.getWidth();
        float bottom = startingY + obstacleSprite.getHeight();
        return new RectF(left, top, right, bottom);
    }

}
