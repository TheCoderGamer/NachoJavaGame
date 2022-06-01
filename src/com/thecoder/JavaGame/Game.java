package com.thecoder.JavaGame;

import com.thecoder.JavaGame.graphics.Screen;
import com.thecoder.JavaGame.graphics.level.Level;
import com.thecoder.JavaGame.graphics.level.RandomLevel;
import com.thecoder.JavaGame.input.Keyboard;
import com.thecoder.JavaGame.utils.Logger;
import javax.swing.JFrame;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Game extends Canvas implements Runnable {
    // Constants
    private static final long serialVersionUID = 1L;
    private static final String TITLE = "TheCoder's Java Game";
    private static final int WIDTH = 300;
    private static final int HEIGHT = WIDTH / 16 * 9;
    private static final int SCALE = 3;
    private static final double WANTED_UPS = 60;

    // Relations
    private boolean running = false;
    private Thread gameLoop;
    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
    private Screen screen;
    private JFrame frame;
    private Keyboard key;
    private Level level;

    // Game
    int xPos, yPos;

    public Game() {
        Logger.log("Starting game");

        // Window settings
        Dimension size = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
        setPreferredSize(size);
        screen = new Screen(WIDTH, HEIGHT, pixels);
        frame = new JFrame();
        frame.setResizable(false);
        frame.setTitle(TITLE);
        frame.add(this);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        requestFocusInWindow();

        // Keyboard settings
        key = new Keyboard();
        addKeyListener(key);

        // Game loop
        gameLoop = new Thread(this, "GameLoop");
        running = true;
        gameLoop.start();

        // Level
        level = new RandomLevel(64, 64);
    }

    public void stop() {
        running = false;
        try {
            gameLoop.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        // Init timer
        long lastTime = System.nanoTime();
        final double ns = 1000000000.0 / WANTED_UPS;
        double delta = 0;
        // Init fps counter
        long timer = System.currentTimeMillis();
        int fps = 0;
        int avgFps = 0;
        int ups = 0;

        // Game loop
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;

            // Game logic
            while (delta >= 1) {
                update();
                delta--;
                ups++;
            }

            // Render
            render();
            fps++;
            lastTime = now;

            // Fps counter
            if (System.currentTimeMillis() - timer > 1000) {
                avgFps = (fps + avgFps) / 2;
                frame.setTitle(String.format("%s [fps: %d, avg fps: %d, ups: %d]", TITLE, fps, avgFps, ups));
                timer += 1000;
                fps = 0;
                ups = 0;
            }
        }
        stop();
    }

    private void render() {
        // Create buffer strategy & get graphics
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();

        // Clear & draw screen
        screen.clear();
        level.render(xPos, yPos, screen);
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);

        // Clear resoruces & show the buffer
        g.dispose();
        bs.show();
    }

    private void update() {
        // Input
        key.update();
        if (key.up) {
            yPos--;
        }
        if (key.down) {
            yPos++;
        }
        if (key.left) {
            xPos--;
        }
        if (key.right) {
            xPos++;
        }
    }
}
