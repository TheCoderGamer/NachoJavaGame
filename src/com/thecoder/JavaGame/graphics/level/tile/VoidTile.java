package com.thecoder.JavaGame.graphics.level.tile;

import com.thecoder.JavaGame.graphics.Screen;
import com.thecoder.JavaGame.graphics.Sprite;

public class VoidTile extends Tile implements Tileable {

    public VoidTile(Sprite sprite, boolean solid) {
        super(sprite, solid);
    }

    @Override
    public void render(int x, int y, Screen screen) {
        screen.renderTile(x << 4, y << 4, this);
    }
}
