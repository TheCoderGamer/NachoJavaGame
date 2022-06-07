package com.thecoder.JavaGame.graphics;

public class Sprite {
    
    // Sprites
    public static Sprite playerUp1 = new Sprite(32, 0, 10, SpriteSheet.tiles);
    public static Sprite playerUp2 = new Sprite(32, 0, 12, SpriteSheet.tiles);
    public static Sprite playerUp3 = new Sprite(32, 0, 14, SpriteSheet.tiles);
    public static Sprite playerRight1 = new Sprite(32, 2, 10, SpriteSheet.tiles);
    public static Sprite playerRight2 = new Sprite(32, 2, 12, SpriteSheet.tiles);
    public static Sprite playerRight3 = new Sprite(32, 2, 14, SpriteSheet.tiles);
    public static Sprite playerDown1 = new Sprite(32, 4, 10, SpriteSheet.tiles);
    public static Sprite playerDown2 = new Sprite(32, 4, 12, SpriteSheet.tiles);
    public static Sprite playerDown3 = new Sprite(32, 4, 14, SpriteSheet.tiles);
    public static Sprite playerLeft1 = new Sprite(32, 6, 10, SpriteSheet.tiles);
    public static Sprite playerLeft2 = new Sprite(32, 6, 12, SpriteSheet.tiles);
    public static Sprite playerLeft3 = new Sprite(32, 6, 14, SpriteSheet.tiles);

    public static Sprite voidTile = new Sprite(16, 0x505050);
    public static Sprite grass1 = new Sprite(16, 0, 0, SpriteSheet.tiles);
    public static Sprite grass2 = new Sprite(16, 1, 0, SpriteSheet.tiles);
    public static Sprite grass3 = new Sprite(16, 2, 0, SpriteSheet.tiles);
    public static Sprite grassflowers = new Sprite(16, 3, 0, SpriteSheet.tiles);
    public static Sprite grassrock = new Sprite(16, 4, 0, SpriteSheet.tiles);
    

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
		this.x = x * sheet.TILE_SIZE;
		this.y = y * sheet.TILE_SIZE;  
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
