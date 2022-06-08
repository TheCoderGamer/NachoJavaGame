package com.thecoder.JavaGame.graphics.level.tile;

import com.thecoder.JavaGame.graphics.Sprite;

public class BrickTile extends Tile implements Tileable {
    
    public BrickTile(Sprite sprite, boolean solid, int ID) {
        super(sprite, solid, ID);
    }
}
