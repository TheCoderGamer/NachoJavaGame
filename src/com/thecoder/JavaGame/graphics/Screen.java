package com.thecoder.JavaGame.graphics;

import java.util.Random;
import com.thecoder.JavaGame.graphics.level.tile.Tile;

public class Screen {
    public int width, height;
    public int[] pixels;
    private final int MAP_SIZE = 64;
    public int[] tiles = new int[MAP_SIZE * MAP_SIZE];
    public int xOffset, yOffset;

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

    public void clear() {
        // Sets pixels to black
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = 0;
        }
    }

    public void renderTile(int xx, int yy, Tile tile) {
        xx -= xOffset;
        yy -= yOffset;
        // Loop for each pixel and set it to the sprite at the corresponding position
        for(int y = 0; y < tile.sprite.SIZE; y++) {
            int ya = yy + y;
            for(int x = 0; x < tile.sprite.SIZE; x++) {
                int xa = xx + x;
                if (xa < -tile.sprite.SIZE || xa >= width || ya < 0 || ya >= height) break;
                if (xa < 0) xa = 0;
                pixels[xa + ya * width] = tile.sprite.pixels[x + y * tile.sprite.SIZE];
            }
        }
    }

    public void setOffset(int xOffset, int yOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }
}
