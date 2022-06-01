package com.thecoder.JavaGame.graphics;

import java.awt.Color;

public class Sprite {
    
    // Sprites
    public static Sprite voidTile = new Sprite(16, Color.blue.getRGB());
    public static Sprite grass1 = new Sprite(16, 0, 0, SpriteSheet.tiles);
    public static Sprite grass2 = new Sprite(16, 1, 0, SpriteSheet.tiles);
    public static Sprite grass3 = new Sprite(16, 2, 0, SpriteSheet.tiles);
    

    final int SIZE;
    private int x, y;
    private int width, height;
    public int[] pixels;
    private SpriteSheet sheet;

    public Sprite(int size, int x, int y, SpriteSheet sheet) {
		SIZE = size;
		this.width = size;
		this.height = size;
		pixels = new int[SIZE * SIZE];
		this.x = x * size;
		this.y = y * size;
		this.sheet = sheet;
		load();
	}
    public Sprite(int size, int color){
        SIZE = size;
        pixels = new int[SIZE * SIZE];
        setColor(color);
    }

    private void setColor(int color) {
        for (int i = 0; i < SIZE * SIZE; i++) {
            pixels[i] = color;
        }
    }

    private void load() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				pixels[x + y * width] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.SIZE];
			}
		}
	}
}
