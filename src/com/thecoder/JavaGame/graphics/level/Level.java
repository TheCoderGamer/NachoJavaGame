package com.thecoder.JavaGame.graphics.level;

import com.thecoder.JavaGame.graphics.Screen;
import com.thecoder.JavaGame.graphics.level.tile.Tile;

public abstract class Level {

    protected int width, height;
    protected int[] tiles;

    // Constructor for loading level
    public Level(String path) {
        loadLevel(path);
    }

    // Constructor for random level
    public Level(int width, int height) {
        this.width = width;
        this.height = height;
        tiles = new int[width * height];
        generateRandomLevel();
    }

    private void loadLevel(String path) {}

    protected abstract void generateRandomLevel();

    public void update() {}

    public void render(int xScroll, int yScroll, Screen screen) {
        screen.setOffset(xScroll, yScroll);
        // Render all the tiles on the screen
        int x0 = xScroll >> 4;
        int x1 = (xScroll + screen.width + 16) >> 4;
        int y0 = yScroll >> 4;
        int y1 = (yScroll + screen.height + 16) >> 4;

        for (int y = y0; y < y1; y++) {
            for (int x = x0; x < x1; x++) {
                getTile(x, y).render(x, y, screen);
            }
        }
    }

    public Tile getTile(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height) return Tile.voidTile;
        if (tiles[x + y * width] == 0) return Tile.grass;
        return Tile.voidTile;
    }

    protected void time() {}
}
