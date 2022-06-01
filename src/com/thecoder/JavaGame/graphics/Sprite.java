package com.thecoder.JavaGame.graphics;

public class Sprite {
    
    // Sprites
    public static Sprite voidTile = new Sprite(16, 0xf0f00f);
    public static Sprite grass = new Sprite(16, 0, 0, SpriteSheet.tiles);
    

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
                System.out.println(pixels[x + y * width]);
			}
		}
	}
}
