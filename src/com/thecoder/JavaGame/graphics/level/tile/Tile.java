package com.thecoder.JavaGame.graphics.level.tile;

import java.util.Random;
import com.thecoder.JavaGame.graphics.Screen;
import com.thecoder.JavaGame.graphics.Sprite;

public abstract class Tile {

    // Tiles
    public static Tile voidTile = new VoidTile(Sprite.voidTile, false, -1);
    public static Tile grass1 = new GrassTile(Sprite.grass1, false, 1000);
    public static Tile grass2 = new GrassTile(Sprite.grass2, false, 1001);
    public static Tile grass3 = new GrassTile(Sprite.grass3, false, 1002);
    public static Tile grassflowers = new GrassTile(Sprite.grassflowers, false, 1);
    public static Tile grassrock = new GrassTile(Sprite.grassrock, false, 2);

    // Tile Groups
    public static Tile[] grassTiles = {grass1, grass2, grass3}; // ID: 0

    public int x, y;
    public int ID;
    public Sprite sprite;
    public boolean solid = false;
    private static Random random = new Random();

    public Tile(Sprite sprite, boolean solid, int ID) {
        this.sprite = sprite;
        this.solid = solid;
        this.ID = ID;
    }

    public static Tile randomChooseTile(Tile[] tileGroup) {
        return tileGroup[random.nextInt(tileGroup.length)];
    }

    public abstract void render(int x, int y, Screen screen);

}
