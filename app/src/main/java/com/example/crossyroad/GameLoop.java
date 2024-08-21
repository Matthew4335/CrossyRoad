package com.example.crossyroad;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class GameLoop extends Thread {

    private boolean isRunning = false;
    private GameView game;
    private SurfaceHolder surfaceHolder;
    public GameLoop(GameView game, SurfaceHolder surfaceHolder) {
        this.surfaceHolder = surfaceHolder;
        this.game = game;
    }
    public void startLoop() {
        isRunning = true;
        start();
    }

    //Loop that runs while the game is running, calls the update and draw methods in the GameView
    //class
    @Override
    public void run() {
        super.run();
        Canvas canvas = null;
        while (isRunning) {
            try {
                canvas = surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    game.update();
                    game.draw(canvas);
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } finally {
                if (canvas != null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
