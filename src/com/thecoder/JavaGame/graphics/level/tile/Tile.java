package com.thecoder.JavaGame.graphics.level.tile;

import java.util.Random;
import com.thecoder.JavaGame.graphics.Screen;
import com.thecoder.JavaGame.graphics.Sprite;

public abstract class Tile {

    // Tiles
    public static Tile voidTile = new VoidTile(Sprite.voidTile, true, -1);
    public static Tile grass1 = new GrassTile(Sprite.grass1, false, 1000);
    public static Tile grass2 = new GrassTile(Sprite.grass2, false, 1001);
    public static Tile grass3 = new GrassTile(Sprite.grass3, false, 1002);
    public static Tile grassflowers = new GrassTile(Sprite.grassflowers, false, 1);
    public static Tile grassrock = new RockTile(Sprite.grassrock, false, 2);
    public static Tile brick1 = new BrickTile(Sprite.brick1, true, 3);

    // Tile Groups
    public static Tile[] grassTiles = {grass1, grass2, grass3}; // ID: 0

    public int x, y;
    public int ID;
    public Sprite sprite;
    private boolean solid = false;
    private static Random random = new Random();

    public Tile(Sprite sprite, boolean solid, int ID) {
        this.sprite = sprite;
        this.solid = solid;
        this.ID = ID;
    }

    public static Tile randomChooseTile(Tile[] tileGroup) {
        return tileGroup[random.nextInt(tileGroup.length)];
    }

    public void render(int x, int y, Screen screen) {
        this.x = x << 4;
        this.y = y << 4;
        screen.renderTile(this.x, this.y, this);
    }

    public boolean isSolid() {
        return solid;
    }

    @Override
    public String toString() {
        return "Tile [ID=" + ID + ", solid=" + solid + ", x=" + x + ", y=" + y + "]";
    }
}
