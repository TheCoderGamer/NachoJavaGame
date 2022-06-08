package com.thecoder.JavaGame.graphics.level;

import com.thecoder.JavaGame.graphics.Screen;
import com.thecoder.JavaGame.graphics.level.tile.Tile;
import com.thecoder.JavaGame.utils.Logger;
import java.io.File;
import java.util.Scanner;

public abstract class Level {

    public int width, height;
    public static int tileSize = 16;
    public String name = "NULL";
    public String description = "NULL";
    protected int[] tiles;

    // Constructor for loading level
    public Level(String path) {
        Logger.log("Loading level from file " + path);
        loadLevel(path);
        randomizeGroupTiles();
    }

    // Constructor for random level
    public Level(int width, int height) {
        Logger.log("Creating random level of size " + width + "x" + height);
        this.width = width;
        this.height = height;
        name = "Random Level";
        description = "Nivel aleatorio";
        tiles = new int[width * height];
        generateRandomLevel();
        randomizeGroupTiles();
    }

    // Converts ID of tileGroup to random IDs of tiles of tileGroup
    private void randomizeGroupTiles() {
        for (int i = 0; i < tiles.length; i++) {
            if (tiles[i] == 0)
                tiles[i] = Tile.randomChooseTile(Tile.grassTiles).ID;
        }
    }

    protected void loadLevel(String path) {
        // Loads level from file
        try {
            File levelFile = new File(path);
            Scanner sc = new Scanner(levelFile);
            String line;
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                if (line.startsWith("#name")) {
                    name = sc.nextLine();
                }
                if (line.startsWith("#description")) {
                    description = sc.nextLine();
                }
                if (line.startsWith("#width")) {
                    width = Integer.parseInt(sc.nextLine());
                }
                if (line.startsWith("#height")) {
                    height = Integer.parseInt(sc.nextLine());
                }
                if (line.startsWith("#data")) {
                    tiles = new int[width * height];
                    // Loop for each line
                    for (int y = 0; y < height; y++) {
                        line = sc.nextLine();
                        // Loop for each tile in line
                        String[] tileIDs = line.split(",");
                        for (int x = 0; x < tileIDs.length; x++) {
                            tiles[x + y * width] = Integer.parseInt(tileIDs[x]);
                        }
                    }
                }
            }
            sc.close();
        } catch (Exception e) {
            Logger.log("Could not load level " + path, "ERROR", e);
        }
    }

    protected void generateRandomLevel() {
    }

    public void update() {
    }

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
        if (x < 0 || y < 0 || x >= width || y >= height)
            return Tile.voidTile;
        int tile = tiles[x + y * width];
        // Convert ID of tile to tile
        if (tile == Tile.voidTile.ID)
            return Tile.voidTile;
        if (tile == Tile.grass1.ID)
            return Tile.grass1;
        if (tile == Tile.grass2.ID)
            return Tile.grass2;
        if (tile == Tile.grass3.ID)
            return Tile.grass3;
        if (tile == Tile.grassflowers.ID)
            return Tile.grassflowers;
        if (tile == Tile.grassrock.ID)
            return Tile.grassrock;
        if (tile == Tile.brick1.ID)
            return Tile.brick1;
        return Tile.voidTile;
    }

    protected void time() {
    }
}
