package com.thecoder.JavaGame.graphics.level;

import java.util.Random;

public class RandomLevel extends Level {

    private static final Random random = new Random();

    private static final int GRASS_RERUN = 3; // Probability of a tile to rerun to be grass
    private static final int IDMax = 3; // Maximum number of IDs (exclusive)

    public RandomLevel(int width, int height) {
        super(width, height);
    }

    protected void generateRandomLevel() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int ID = random.nextInt(IDMax);
                // Grass rerun
                for (int i = 0; i < GRASS_RERUN; i++) {
                    if (ID != 0) {
                        ID = random.nextInt(IDMax);
                    }
                }
                tiles[x + y * width] = ID;
            }
        }
    }
}
