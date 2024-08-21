package com.example.crossyroad;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;


public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    //Game View class, needs to be refactored
    private Context context;
    private GameLoop gameLoop;
    private Player player;
    private Bitmap healthImage;

    private String playerName;
    private float screenX;
    private float screenY;
    private SurfaceHolder surfaceHolder;
    private Obstacle vehicle1;
    private Obstacle vehicle2;
    private Obstacle vehicle3;

    private Obstacle log1;
    private Obstacle log2;
    private Obstacle log3;
    private Obstacle log4;
    private Obstacle log5;
    private boolean collided = false;

    private int collisionVelocity = 0;

    public GameView(Context context, Player player, float screenX, float screenY) {
        super(context);

        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        gameLoop = new GameLoop(this, surfaceHolder);
        this.context = context;
        this.screenX = screenX;
        this.screenY = screenY;

        this.player = player;


        vehicle1 = makeCars(R.drawable.vehicle1, 500, 500, false, 35, -500, 1120);
        vehicle2 = makeCars(R.drawable.vehicle2, 500,  500, false, -40, screenX, 1400);
        vehicle3 = makeCars(R.drawable.vehicle3, 500,  500, false, 20, -500, 970);

        Bitmap log1Sprite = BitmapFactory.decodeResource(getResources(), R.drawable.logs2);
        log1Sprite = Bitmap.createScaledBitmap(log1Sprite, player.getPlayerSprite().getWidth(),
                player.getPlayerSprite().getHeight(), false);
        log1 = new Obstacle(log1Sprite, 5, -1 * log1Sprite.getWidth(), 987, screenX);
        log1Sprite = Bitmap.createScaledBitmap(log1Sprite, 3 * player.getPlayerSprite().getWidth(),
                player.getPlayerSprite().getHeight(), false);
        log2 = new Obstacle(log1Sprite, -10, screenX, 817, screenX);
        log1Sprite = Bitmap.createScaledBitmap(log1Sprite, player.getPlayerSprite().getWidth(),
                player.getPlayerSprite().getHeight(), false);
        log3 = new Obstacle(log1Sprite, 5, -1 * log1Sprite.getWidth(), 647, screenX);


        Bitmap log2Sprite = BitmapFactory.decodeResource(getResources(), R.drawable.logs1);
        log2Sprite = Bitmap.createScaledBitmap(log2Sprite, player.getPlayerSprite().getWidth(),
                player.getPlayerSprite().getHeight(), false);
        log4 = new Obstacle(log2Sprite, -5, screenX, 477, screenX);
        //log2Sprite = Bitmap.createScaledBitmap(log2Sprite,
        //(3 * player.getPlayerSprite().getWidth()),
        //player.getPlayerSprite().getHeight(), false);
        log1Sprite = Bitmap.createScaledBitmap(log1Sprite, 3 * player.getPlayerSprite().getWidth(),
                player.getPlayerSprite().getHeight(), false);
        log5 = new Obstacle(log1Sprite, 12, -1 * log1Sprite.getWidth(), 307, screenX);
    }
    //Touch event to move player sprite, player will move in the corresponding direction if
    //the user clicks above, below, to the right, or to the left of the sprite
    //Also handles logic tto keep character sprite within the screen
    //Needs to be refactored, placed within the Player class
    private Obstacle makeCars(int id, int width, int height, Boolean filter,
                              int velocity, float startingX, float startingY) {
        Bitmap sprite = BitmapFactory.decodeResource(getResources(), id);
        sprite = Bitmap.createScaledBitmap(sprite, width, height, filter);
        Obstacle newObstacle = new Obstacle(sprite, velocity, startingX, startingY, screenX);
        return newObstacle;
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
        case MotionEvent.ACTION_DOWN:
            collided = false;
            float downX = event.getX();
            float downY = event.getY();
            player.moveLogic(downX, downY, screenX, screenY);
            invalidate();
            return true;
        default:
            return super.onTouchEvent(event);

        }
    }
    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {

        gameLoop.startLoop();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

    }

    //Handles drawing objects on screen, is called in the game loop
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        drawName(canvas);
        drawScore(canvas);
        if (player.getHealth() != 0) {
            drawHealth(canvas);
        }

        vehicle1.draw(canvas);
        vehicle2.draw(canvas);
        vehicle3.draw(canvas);

        log1.draw(canvas);
        log2.draw(canvas);
        log3.draw(canvas);
        log4.draw(canvas);
        log5.draw(canvas);

        player.draw(canvas);
    }

    //Will be used to handle updates to the game when enemies are added
    public void update() {
        vehicle1.update(vehicle1.getObstacleWidth());
        vehicle2.update(vehicle2.getObstacleWidth());
        vehicle3.update(vehicle3.getObstacleWidth());

        log1.update(log1.getObstacleWidth());
        log2.update(log2.getObstacleWidth());
        log3.update(log3.getObstacleWidth());
        log4.update(log4.getObstacleWidth());
        log5.update(log5.getObstacleWidth());

        //collision for middle vehicle

        // If player moves out of bounds
        RectF playerRect = createRectF(128, 128, player.getXPos(), player.getYPos());
        if (player.getXPos() < -1 * (player.getPlayerSprite().getWidth())
                || player.getXPos() > screenX) {
            player.handleInWater();
        } else if (player.getYPos() < 1500 && player.getYPos() > 1400) {
            RectF vehicle1Rect = createRectF(120, 50, vehicle1.getObstacleX() + 315,
                    vehicle1.getObstacleY() + 410);
            collided = detectCollision(playerRect, vehicle1Rect);
            player.handleCarCollision(collided);
            //collision for first vehicle
        } else if (player.getYPos() < 1700 && player.getYPos() > 1500) {
            RectF vehicle2Rect = createRectF(50, 40, vehicle2.getObstacleX() + 370,
                    vehicle2.getObstacleY() + 330);
            collided = detectCollision(playerRect, vehicle2Rect);
            player.handleCarCollision(collided);
            //collision for last vehicle
        } else if (player.getYPos() < 1400 && player.getYPos() > 1100) {
            // RectF playerRect = createRectF(128, 128, player.getXPos(), player.getYPos());
            RectF vehicle3Rect = createRectF(125, 40, vehicle3.getObstacleX() + 340,
                  vehicle3.getObstacleY() + 370);
            collided = detectCollision(playerRect, vehicle3Rect);
            player.handleCarCollision(collided);

        } else if (player.getYPos() == 987) { // Log No. 1
            if (detectCollision(playerRect, log1.getHitBox())) {
                collided = true;
                collisionVelocity = log1.getObstacleVelocity();
            }

        } else if (player.getYPos() == 817) { // Log No . 2
            if (detectCollision(playerRect, log2.getHitBox())) {
                collided = true;
                collisionVelocity = log2.getObstacleVelocity();
            }
        } else if (player.getYPos() == 647) { // Log No. 3
            if (detectCollision(playerRect, log3.getHitBox())) {
                collided = true;
                collisionVelocity = log3.getObstacleVelocity();
            }
        } else if (player.getYPos() == 477) { // Log No . 4
            if (detectCollision(playerRect, log4.getHitBox())) {
                collided = true;
                collisionVelocity = log4.getObstacleVelocity();
            }
        } else if (player.getYPos() == 307) { // Log No. 5
            if (detectCollision(playerRect, log5.getHitBox())) {
                collided = true;
                collisionVelocity = log5.getObstacleVelocity();
            }
        }

        if (player.checkWater()) {
            if (!(collided)) {
                // If player didn't touch a log, then reset
                player.handleInWater();
            } else {
                // Otherwise, make the player move with the log
                player.moveWithLog(collisionVelocity);
                player.update(false);
            }
        } else {
            player.update(collided);
        }

        if (player.getHealth() == 0) {
            Intent toGameOver = new Intent(context, GameOverScreen.class);
            toGameOver.putExtra("score", Integer.toString(player.getHighestScore()));
            context.startActivity(toGameOver);
        }
        if (player.checkWin()) {
            Intent toWinScreen = new Intent(context, WinScreen.class);
            toWinScreen.putExtra("score", Integer.toString(player.getHighestScore()));
            context.startActivity(toWinScreen);
        }
        invalidate();


    }
    public static boolean detectCollision(RectF obj1, RectF obj2) {
        // Check if obj1 and obj2 intersect
        return obj1.intersect(obj2);
    }
    public void drawName(Canvas canvas) {
        Paint paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.black);
        paint.setColor(color);
        paint.setTextSize(56);
        canvas.drawText("Player: " + player.getName(), 20, 50, paint);
    }
    public void drawScore(Canvas canvas) {
        Paint paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.black);
        paint.setColor(color);
        paint.setTextSize(56);
        canvas.drawText("Score: " + player.getScore(), 20, 150, paint);
    }
    public void drawHealth(Canvas canvas) {
        getHealthImage(player.getHealth());
        canvas.drawBitmap(healthImage, screenX - healthImage.getWidth() - 50, 50, null);
    }
    public RectF createRectF(float spriteWidth, float spriteHeight, float x, float y) {
        // Calculate the left, top, right, and bottom coordinates of the rectangle
        float left = x;
        float top = y;
        float right = x + spriteWidth;
        float bottom = y + spriteHeight;

        // Create and return a new RectF object
        return new RectF(left, top, right, bottom);
    }
    public void getHealthImage(int health) {
        switch (health) {
        case 3:
            healthImage = BitmapFactory.decodeResource(getResources(), R.drawable.lives3);
            healthImage = Bitmap.createScaledBitmap(healthImage, 195, 100, false);
            break;
        case 2:
            healthImage = BitmapFactory.decodeResource(getResources(), R.drawable.lives2);
            healthImage = Bitmap.createScaledBitmap(healthImage, 150 * 2, 100, false);
            break;
        case 1:
            healthImage = BitmapFactory.decodeResource(getResources(), R.drawable.lives1);
            healthImage = Bitmap.createScaledBitmap(healthImage, 195, 110, false);
            break;
        default:
            healthImage = BitmapFactory.decodeResource(getResources(), R.drawable.lives1);
            break;
        }
    }
}
