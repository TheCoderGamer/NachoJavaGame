package com.thecoder.JavaGame.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import com.thecoder.JavaGame.utils.Logger;

public class SpriteSheet {
    
    // SpriteSheets
    public static SpriteSheet tiles = new SpriteSheet("/textures/tiles.png", 256);
    
    public int[] pixels;
    private String path;
    final int SIZE;
    private int width, height;

    public SpriteSheet(String path, int size) {
        this.path = path;
        SIZE = size;
        pixels = new int[SIZE * SIZE];
        load();
    }

    private void load() {
		try {
			Logger.log("Trying to load: " + path);
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
			Logger.log("Succeeded!");
			width = image.getWidth();
			height = image.getHeight();
			pixels = new int[width * height];
			image.getRGB(0, 0, width, height, pixels, 0, width);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			Logger.log(" failed!", "ERROR");
		}

	}
}
