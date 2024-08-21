package com.example.crossyroad;

import static com.example.crossyroad.GameView.detectCollision;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import android.content.Context;
import android.content.Intent;
import android.graphics.RectF;


import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;



public class Sprint5Tests {
    @Test
    public void testingWaterResetHealth() {
        Player player = new Player(500, 987, null, 3, null);
        player.handleInWater();
        assertEquals(player.getHealth(), 2);
    }

    @Test
    public void testPlayerMoveWithLogRight() {
        int logVelocity = 10;
        Player player = new Player(500, 987, null, 3, null);
        player.moveWithLog(logVelocity);
        assertEquals(player.getXPos(), 510, 0);
    }

    @Test
    public void testWaterCheck() {
        Player player = new Player(25,25, null,3, "steve");
        player.setScore(25);
        assertFalse(player.checkWater());
        assertEquals(25, player.getScore(), 0);
    }


    @Test
    public void testObstacleReset() {
        Obstacle log1 = new Obstacle(null, 12, 0, 12,10);
        log1.update(15);
        assertEquals(0, log1.getObstacleX(),0 );

    }

    @Test
    public void testPlayerMoveWithLogLeft() {
        int logVelocity = 20;
        Player player = new Player(500, 987, null, 2, null);
        player.moveWithLog(logVelocity);
        assertEquals(player.getXPos(), 520, 0);
    }

    @Test
    public void checkScoreAfterJump() {
        int logVelocity = 10;
        Player player = new Player(500, 987, null, 2, null);
        int currScore = player.getScore();
        player.moveWithLog(logVelocity);
        player.setPosition(player.getXPos() + 5, player.getYPos());  //a jump movement from log
        assertEquals(player.getScore(), currScore);
    }

    @Test
    public void checkScoreAfterJumpInWater() {
        int logVelocity = 10;
        Player player = new Player(500, 987, null, 2, null);
        int currScore = player.getScore();
        player.handleInWater();
        player.setPosition(player.getXPos(), player.getYPos() + 5);
        assertEquals(player.getScore(), currScore);
    }

    @Test
    public void testingWaterResetPosition() {
        Player player = new Player(500, 987, null, 3, null);
        player.handleInWater();
        assertEquals(500, player.getXPos(),  0);
        assertEquals(987, player.getYPos(),  0);
    }

    @Test
    public void testLogsNotColliding() {
        Obstacle log1 = new Obstacle(null, -50, 200, 500, 200);
        Obstacle log2 = new Obstacle(null, 50, 200, 100, 200);
        for (int i = 0; i < 5; i++) {
            log1.update(25);
            log2.update(25);
            assertNotEquals(log1.getObstacleY(), log2.getObstacleY());
        }
    }

    @Test
    public void testScoreBetweenLogs() {
        Obstacle log1 = new Obstacle(null, -50, 200, 150, 200);
        Obstacle log2 = new Obstacle(null, 50, 200, 100, 200);
        Player player = new Player(200, 100, null, 3, null);
        player.setPosition(player.getXPos(), player.getYPos() + 50);
        player.setLowestPosition(200);
        boolean collided = false;
        if (player.getYPos() != log1.getObstacleY() && player.getYPos() != log2.getObstacleY()) {
            collided = true;
        }
        player.update(collided);
        assertEquals(player.getScore(), 50, 0);
    }
    @Test
    public void testPlayerWinTrue() {
        Player player = new Player(500, 200, null, 3, null);
        assertTrue(player.checkWin());
    }
    @Test
    public void testPlayerWinFalse() {
        Player player = new Player(500, 500, null, 3, null);
        assertFalse(player.checkWin());
    }
}
