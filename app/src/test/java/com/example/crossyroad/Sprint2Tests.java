package com.example.crossyroad;
import static androidx.core.content.ContextCompat.getSystemService;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;



public class Sprint2Tests {

    @Test
    public void playerName() {
        assertFalse(PlayerVerification.nameIsValid(" "));
        assertFalse(PlayerVerification.nameIsValid(""));
        assertFalse(PlayerVerification.nameIsValid("     "));
        assertTrue(PlayerVerification.nameIsValid("Name"));
    }

    @Test
    public void playerNameNotNull() {
        assertFalse(PlayerVerification.nameNotNull(null));
    }


    @Test
    public void difficultyLevel() {
        assertFalse(PlayerVerification.difficultyIsValid("None"));
        assertTrue(PlayerVerification.difficultyIsValid("Easy"));
        assertTrue(PlayerVerification.difficultyIsValid("Normal"));
        assertTrue(PlayerVerification.difficultyIsValid("Difficult"));
    }

    @Test
    public void difficultyLevelNotSelected() {
        assertTrue(PlayerVerification.difficultyNotSelected("Not selected"));
    }

    @Test
    public void spriteVerification() {
        assertFalse(PlayerVerification.spriteChosen("Not selected"));
        assertTrue(PlayerVerification.spriteChosen("Buzz"));
        assertTrue(PlayerVerification.spriteChosen("Goat"));
        assertTrue(PlayerVerification.spriteChosen("Google"));
    }
    @Test
    public void playerNewPosition() {
        Player newPlayer = new Player(10, 15, null, 3, null);
        newPlayer.setPosition(2, 3);
        assertEquals(newPlayer.getXPos(), 2, 0);
        assertEquals(newPlayer.getYPos(), 3, 0);
    }

    @Test
    public void playerInitialPosition() {
        Player newPlayer = new Player(10, 15, null, 3, null);
        assertEquals(newPlayer.getXPos(), 10, 0);
        assertEquals(newPlayer.getYPos(), 15, 0);
    }
    @Test
    public void notMoveLeftOfScreen() {
        Player newPlayer = new Player(63, 63, null, 3, null);
        newPlayer.moveLogic(-64, 0, 0, 0);
        assertEquals(newPlayer.getXPos(), 63, 0);
        assertEquals(newPlayer.getYPos(), 63, 0);
    }
    @Test
    public void notMoveVerScreen1() {
        Player newPlayer = new Player(63, 63, null, 3, null);
        newPlayer.moveLogic(0, -64, 0, 0);
        assertEquals(newPlayer.getXPos(), 63, 0);
        assertEquals(newPlayer.getYPos(), 63, 0);
    }
    @Test
    public void notMoveVerScreen2() {
        Player newPlayer = new Player(63, 63, null, 3, null);
        newPlayer.moveLogic(0, 64, 100, 0);
        assertEquals(newPlayer.getXPos(), 10, 0);
        assertEquals(newPlayer.getYPos(), 63, 0);
    }
    @Test
    public void healthVerification() {
        assertFalse(PlayerVerification.lifeSet("err"));
        assertTrue(PlayerVerification.lifeSet("lives3"));
        assertTrue(PlayerVerification.lifeSet("lives2"));
        assertTrue(PlayerVerification.lifeSet("lives1"));
    }
    @Test
    public void testStartScore() {
        Player player = new Player(63, 63, null, 3, null);
        int expectedValue = 0;
        assertEquals(expectedValue, player.getScore());
    }


//    }




}
