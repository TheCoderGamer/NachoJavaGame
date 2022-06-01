package com.thecoder.JavaGame.graphics;

import java.util.Random;

public class Screen {
    private int width, height;
    public int[] pixels;
    private final int MAP_SIZE = 64;
    public int[] tiles = new int[MAP_SIZE * MAP_SIZE];

    private Random random = new Random();

    public Screen(int width, int height, int[] pixels) {
        this.width = width;
        this.height = height;
        this.pixels = pixels;

        // Create tiles
        for (int i = 0; i < tiles.length; i++) {
            tiles[i] = random.nextInt(0xffffff);
        }
    }

    public void render(int xOffset, int yOffset) {
        // Loop for each pixel and set it to the color of the tile at the corresponding position
        for (int y = 0; y < height; y++) {
            int yy = y + yOffset;
            // if (y < 0 || y >= height) break;
            for (int x = 0; x < width; x++) {
                int xx = x + xOffset;
                // if (x < 0 || x >= width) break;
                // tileIndex: x and y divided by 16. If that exceeds MAP_SIZE, it will start again from 0.
                int tileIndex = ((xx >> 4) & MAP_SIZE - 1) + ((yy >> 4) & MAP_SIZE - 1) * MAP_SIZE;
                pixels[x + (y * width)] = tiles[tileIndex];
            }
        }
    }

    public void clear() {
        // Sets pixels to black
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = 0;
        }
    }
}
