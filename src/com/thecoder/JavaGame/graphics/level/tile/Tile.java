package com.thecoder.JavaGame.graphics.level.tile;

import com.thecoder.JavaGame.graphics.Screen;
import com.thecoder.JavaGame.graphics.Sprite;

public abstract class Tile {
    // Tiles
    public static Tile voidTile = new VoidTile(Sprite.voidTile, false);
    public static Tile grass = new GrassTile(Sprite.grass, false);
    
    
    
    public int x, y;
    public Sprite sprite;
    public boolean solid = false;

    public Tile(Sprite sprite, boolean solid) {
        this.sprite = sprite;
        this.solid = solid;
    }

    public abstract void render(int x, int y, Screen screen);
}
