package com.thecoder.JavaGame.graphics.level.tile;

import com.thecoder.JavaGame.graphics.Screen;
import com.thecoder.JavaGame.graphics.Sprite;

public abstract class Tile {
    
    // Tiles
    public static Tile voidTile = new VoidTile(Sprite.voidTile, false);
    public static Tile grass1 = new GrassTile(Sprite.grass1, false);
    public static Tile grass2 = new GrassTile(Sprite.grass2, false);
    public static Tile grass3 = new GrassTile(Sprite.grass3, false);
    public static Tile grassflowers = new GrassTile(Sprite.grassflowers, false);
    public static Tile grassrock = new GrassTile(Sprite.grassrock, false);


    public int x, y;
    public Sprite sprite;
    public boolean solid = false;

    public Tile(Sprite sprite, boolean solid) {
        this.sprite = sprite;
        this.solid = solid;
    }

    public abstract void render(int x, int y, Screen screen);
    
}
