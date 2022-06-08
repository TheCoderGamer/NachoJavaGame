package com.thecoder.JavaGame.graphics;

import com.thecoder.JavaGame.entity.mob.Player;
import com.thecoder.JavaGame.graphics.level.tile.Tile;
import com.thecoder.JavaGame.utils.Logger;

public class Screen {
    public int width, height;
    public int[] pixels;
    public int xOffset, yOffset;

    public Screen(int width, int height, int[] pixels) {
        Logger.log("Screen created");
        this.width = width;
        this.height = height;
        this.pixels = pixels;
    }

    public void clear() {
        // Sets pixels to black
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = 0;
        }
    }

    public void renderTile(int xp, int yp, Tile tile) {
        xp -= xOffset;
        yp -= yOffset;
        // Loop for each pixel and set it to the sprite at the corresponding position
        for (int y = 0; y < tile.sprite.SIZE; y++) {
            int ya = yp + y;
            for (int x = 0; x < tile.sprite.SIZE; x++) {
                int xa = xp + x;
                if (xa < -tile.sprite.SIZE || xa >= width || ya < 0 || ya >= height) break;
                if (xa < 0) xa = 0;
                pixels[xa + ya * width] = tile.sprite.pixels[x + y * tile.sprite.SIZE];
            }
        }
    }

    public void renderPlayer(int xp, int yp, Player player) {
        xp -= xOffset;
        yp -= yOffset;
        // Loop for each pixel and set it to the sprite at the corresponding position
        for (int y = 0; y < player.sprite.SIZE; y++) {
            int ya = yp + y;
            for (int x = 0; x < player.sprite.SIZE; x++) {
                int xa = xp + x;
                if (xa < -player.sprite.SIZE || xa >= width || ya < 0 || ya >= height) break;
                if (xa < 0) xa = 0;
                int pixel = player.sprite.pixels[x + y * player.sprite.SIZE];
                if (pixel == 0XFFFF00FF) continue; // If pink pixel, skip it
                pixels[xa + ya * width] = pixel;
            }
        }
    }

    public void renderDot(int xp, int yp) {
        xp -= xOffset;
        yp -= yOffset;
        pixels[xp + yp * width] = 0xFF00FF00;
    }

    public void renderBox(Box box) {
        int xp = box.x1 - xOffset;
        int yp = box.y1 - yOffset;
        int xp2 = box.x2 - xOffset;
        int yp2 = box.y2 - yOffset;

        for (int x = xp; x <= xp2; x++) {
            pixels[x + yp * width] = 0xFF00FFF0;
            pixels[x + yp2 * width] = 0xFF00FFF0;
        }
        for (int y = yp; y <= yp2; y++) {
            pixels[xp + y * width] = 0xFF00FFF0;
            pixels[xp2 + y * width] = 0xFF00FFF0;
        }
    }

    public void setOffset(int xOffset, int yOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }
}
