package com.example.crossyroad;

public class PlayerVerification {
    public PlayerVerification() {
    }
    public static boolean nameIsValid(String name) {
        return !name.equals("")
                && !name.replace(" ", "").equals("");
    }
    public static boolean difficultyIsValid(String difficulty) {
        return !difficulty.equals("None");
    }
    public static boolean spriteChosen(String sprite) {
        return !sprite.equals("Not selected");
    }

    public static boolean lifeSet(String lives) {
        return !lives.equals("err");
    }

    public static boolean nameNotNull(String name) {
        return name != null;
    }

    public static boolean difficultyNotSelected(String difficulty) {
        return difficulty.equals("Not selected");
    }
}