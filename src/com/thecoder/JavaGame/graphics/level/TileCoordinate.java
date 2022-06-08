package com.thecoder.JavaGame.graphics.level;

public class TileCoordinate {
    public final int x, y;
    
    public TileCoordinate(int tileX, int tileY) {
        this.x = tileX * Level.tileSize;
        this.y = tileY * Level.tileSize;
    }

    public boolean equals(TileCoordinate other) {
        return x == other.x && y == other.y;
    }
}