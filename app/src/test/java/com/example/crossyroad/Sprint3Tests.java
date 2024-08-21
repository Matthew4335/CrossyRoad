package com.example.crossyroad;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import static org.junit.Assert.*;
public class Sprint3Tests {
    private Context context;
    @Test
    public void vehicleCreation1() {
        Obstacle car = new Obstacle(null, 50, 500, 700, 800);
        car.update(500);
        assertEquals(car.getObstacleX(), 550, 0);
        assertEquals(car.getObstacleY(), 700, 0);
    }
    @Test
    public void vehicleCreation2() {
        Obstacle car = new Obstacle(null, 50, -500, 700, 800);
        car.update(500);
        assertEquals(car.getObstacleX(), -450, 0);
        assertEquals(car.getObstacleY(), 700, 0);
    }

    @Test
    public void scoreStartingTile() {
        Player player = new Player(800, 1600, null, 3, null);
        player.setLowestPosition(1700);
        player.updateScore();
        assertEquals(player.getScore(), 10);
    }
    @Test
    public void scoreFastRoadLane() {
        Player player = new Player(800, 1450, null, 3, null);
        player.setLowestPosition(1700);
        player.updateScore();
        assertEquals(player.getScore(), 30);
    }

    @Test
    public void checkPositiveXMoveAndScore() {
        Player player = new Player(800, 1600, null, 3, null);
        player.setLowestPosition(1700);
        player.updateScore();
        int initialScore = player.getScore();
        player.setPosition(player.getXPos() + 10, player.getYPos());
        player.updateScore();
        int newScore = player.getScore();
        assertEquals(initialScore, newScore);
    }
    @Test
    public void checkNegativeXMoveAndScore() {
        Player player = new Player(800, 1600, null, 3, null);
        player.setLowestPosition(1700);
        player.updateScore();
        int initialScore = player.getScore();
        player.setPosition(player.getXPos() - 10, player.getYPos());
        player.updateScore();
        int newScore = player.getScore();
        assertEquals(initialScore, newScore);
    }

    @Test
    public void checkScoreBackwards() {
        Player player = new Player(800, 1600, null, 3, null);
        player.setLowestPosition(1700);
        player.setPosition(player.getXPos(), player.getYPos() + 10);
        player.updateScore();
        player.setPosition(player.getXPos(), player.getYPos() + 10);
        player.updateScore();
        int latestScore = player.getScore();
        player.setPosition(player.getXPos(), player.getYPos() - 10);
        int newScore = player.getScore();
        assertEquals(latestScore, newScore);
        player.setPosition(player.getXPos(), player.getYPos() - 10);
        newScore = player.getScore();
        assertEquals(latestScore, newScore);
    }

    @Test
    public void noDoubleIncrease() {
        Player player = new Player(800, 1600, null, 3, null);
        player.setLowestPosition(1700);
        player.setPosition(player.getXPos(), player.getYPos() + 10);
        player.updateScore();
        player.setPosition(player.getXPos(), player.getYPos() + 10);
        player.updateScore();
        int oldScore = player.getScore();
        player.setPosition(player.getXPos(), player.getYPos() - 10);
        player.setPosition(player.getXPos(), player.getYPos() - 10);
        player.setPosition(player.getXPos(), player.getYPos() + 10);
        player.setPosition(player.getXPos(), player.getYPos() + 10);
        int newScore = player.getScore();
        assertEquals(oldScore, newScore);
    }

    @Test
    public void vehicleReset() {
        Obstacle vehicle = new Obstacle(null, 2, 25, 15, 500);
        vehicle.update(50);
        assertNotEquals(vehicle.getObstacleX(), 25);
        vehicle.resetPosition();
        assertEquals(vehicle.getObstacleX(), 25, 0);
    }

    @Test
    public void vehicleUpdate() {
        Obstacle vehicle1 = new Obstacle(null, 20, 0, 100, 50);
        vehicle1.update(50);
        assertEquals(vehicle1.getObstacleX(), 20, 0);
        vehicle1.update(50);
        assertEquals(vehicle1.getObstacleX(), 40, 0);
        vehicle1.update(50);
        assertEquals(vehicle1.getObstacleX(), 0, 0);

        Obstacle vehicle2 = new Obstacle(null, -20, 50, 100, 50);
        vehicle2.update(15);
        assertEquals(vehicle2.getObstacleX(), 30, 0);
        vehicle2.update(15);
        assertEquals(vehicle2.getObstacleX(), 10, 0);
        vehicle2.update(15);
        assertEquals(vehicle2.getObstacleX(), -10, 0);
        vehicle2.update(15);
        assertEquals(vehicle2.getObstacleX(), 50, 0);
    }

    @Test
    public void scoreAtSlowLane() {
        Player player = new Player(800, 1200, null, 3, null);
        player.setLowestPosition(1700);
        player.updateScore();
        assertEquals(player.getScore(), 15);
    }
    @Test
    public void scoreAtGoalTile() {
        Player player = new Player(800, 100, null, 3, null);
        player.setLowestPosition(1700);
        player.updateScore();
        assertEquals(player.getScore(), 10);
    }




}
