package com.example.crossyroad;
import org.junit.Test;
import static org.junit.Assert.*;

import android.graphics.RectF;

public class Sprint4Tests {
    @Test
    public void resetPosition() {
        Player player = new Player(500, 500, null, 3, null);
        player.setPosition(200, 200);
        player.resetPosition();
        assertEquals(player.getXPos(), 500, 0);
        assertEquals(player.getYPos(), 500, 0);
    }
    @Test
    public void inWaterHealth() {
        Player player = new Player(500, 800, null, 3, null);
        player.checkWater();
        assertEquals(player.getHealth(), 2);
    }
    @Test
    public void carCollision(){
        Player player = new Player(500, 500, null, 3, null);
        player.setPosition(200, 200);
        player.handleCarCollision(true);
        assertEquals(player.getXPos(), 500,0);
        assertEquals(player.getYPos(), 500,0);
        assertEquals(player.getHealth(), 2,0);
    }
    @Test
    public void carCollision2(){
        Player player = new Player(500, 500, null, 3, null);
        player.setPosition(200, 200);
        player.handleCarCollision(false);
        assertEquals(player.getXPos(), 200,0);
        assertEquals(player.getYPos(), 200,0);
        assertEquals(player.getHealth(), 3,0);
    }
    @Test
    public void testUpdateScore() {
        Player player = new Player(500, 900, null, 3, null);

        player.setPosition(0, 1000);
        player.updateScore();
        assertEquals(0, player.getScore());

        player.setPosition(0, 800);
        player.updateScore();
        assertEquals(10, player.getScore());

        player.setPosition(0, 700);
        player.updateScore();
        assertEquals(20, player.getScore());

        player.setPosition(0, 600);
        player.updateScore();
        assertEquals(30, player.getScore());

        player.setPosition(0, 550);
        player.updateScore();
        assertEquals(40, player.getScore());

        player.setPosition(0, 400);
        player.updateScore();
        assertEquals(50, player.getScore());

        player.setPosition(0, 200);
        player.updateScore();
        assertEquals(60, player.getScore());
    }
    @Test
    public void carCollisionWhenCollisionDetected() {
        Player player = new Player(500, 500, null, 3, null);
        boolean collisionDetected = true;
        player.handleCarCollision(collisionDetected);
        assertEquals(2, player.getHealth());
        assertEquals(0, player.getScore());
        assertEquals(500, player.getXPos(), 0);
        assertEquals(500, player.getYPos(), 0);
    }

    @Test
    public void playerRespawned(){
        Player player = new Player(500, 500, null, 3, null);
        player.setPosition(200, 200);

        player.handleCarCollision(true);
        assertEquals(player.getScore(), 0);
    }

    @Test
    public void waterScoreZero() {
        Player player = new Player(500, 900, null, 3, null);
        player.checkWater();

        assertEquals(player.getScore(), 0);
    }

    @Test
    public void waterRespawn() {
        Player player = new Player(500, 500, null, 3, null);
        player.setPosition(500, 800);
        player.checkWater();
        assertEquals(player.getXPos(), 500, 0);
        assertEquals(player.getYPos(), 500, 0);
    }
    @Test
    public void multipleCollision() {
        Player player = new Player(500, 500, null, 3, null);
        player.setPosition(200, 200);
        player.handleCarCollision(true);
        assertEquals(player.getHealth(), 2,0);
        player.handleCarCollision(true);
        assertEquals(player.getHealth(), 1,0);
    }
    @Test
    public void highestScoreUpdate() {
        Player player = new Player(500, 500, null, 3, null);
        assertEquals(player.getHighestScore(), 0, 0);
        player.setScore(100);
        assertEquals(player.getHighestScore(), 100, 0);
        player.setScore(50);
        assertEquals(player.getHighestScore(), 100, 0);
    }

    @Test
    public void noExtraScoreIncrease() {
        Player player = new Player(500, 1700, null, 3, null);
        RectF rect1 = new RectF(20,50,200,200);
        RectF rect2 = new RectF(20,50,181,171);
        player.setPosition(500, 1450);
        player.update(false);
        assertEquals(30, player.getScore());
        player.setPosition(500, 1350);
        player.update(true);
       assertEquals(30, player.getScore());
    }
}
