package com.thecoder.JavaGame;

import com.thecoder.JavaGame.entity.mob.Player;
import com.thecoder.JavaGame.graphics.Camera;
import com.thecoder.JavaGame.graphics.Screen;
import com.thecoder.JavaGame.graphics.level.HubLevel;
import com.thecoder.JavaGame.graphics.level.Level;
import com.thecoder.JavaGame.graphics.level.RandomLevel;
import com.thecoder.JavaGame.graphics.level.TileCoordinate;
import com.thecoder.JavaGame.input.Keyboard;
import com.thecoder.JavaGame.input.Mouse;
import com.thecoder.JavaGame.utils.Logger;
import javax.swing.JFrame;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.File;
import java.io.IOException;

public class Game extends Canvas implements Runnable {
    // Constants
    private static final long serialVersionUID = 1L;
    private static final String TITLE = "TheCoder's Java Game";
    private static final int WIDTH = 300;
    private static final int HEIGHT = WIDTH / 16 * 9;
    private static final int SCALE = 4;
    public static final double WANTED_UPS = 60;
    private static final String FONT1 = "monaco.ttf";

    // Relations
    private boolean running = false;
    private Thread gameLoop;
    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
    private Screen screen;
    private JFrame frame;
    private Keyboard keyboard;
    private Level currentLevel;
    private Player player;
    private Font font;
    private Camera camera;
    private TileCoordinate playerSpawn;

    public void Start() {
        Logger.log("Starting game...");

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

        // Keyboard & Mouse settings
        keyboard = new Keyboard();
        addKeyListener(keyboard);
        Mouse mouse = new Mouse();
        addMouseListener(mouse);
        addMouseMotionListener(mouse);

        // Font
        try {
            Logger.log("Loading font " + FONT1);
            Font sourcefont = Font.createFont(Font.TRUETYPE_FONT, new File("res/fonts/" + FONT1));
            font = sourcefont.deriveFont(Font.PLAIN, 24);
        } catch (FontFormatException | IOException e) {
            Logger.log("Error loading font " + FONT1, e);
        }

        // Level, player & camera
        playerSpawn = new TileCoordinate(4, 4);
        // currentLevel = new RandomLevel(20, 20);
        currentLevel = new HubLevel("res/data/levels/hub.level");
        player = new Player(playerSpawn, keyboard);
        player.init(currentLevel);
        camera = new Camera(player, screen);

        // Game loop
        gameLoop = new Thread(this, "GameLoop");
        running = true;
        gameLoop.start();
    }

    public void stop() {
        running = false;
        try {
            gameLoop.join();
        } catch (InterruptedException e) {
            Logger.log("Failure to stop game loop", e);
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
        Logger.log("GameLoop Started");
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
        // -- Create buffer strategy & get graphics --
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.setFont(font);
        g.setColor(java.awt.Color.WHITE);


        // -- Clear, render and draw screen --
        screen.clear();
        
        currentLevel.render(camera.x, camera.y, screen);
        player.render(screen);
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        g.drawString(String.format("%dX %dY", player.x, player.y), getWidth() - 155, 20);

        // -- Clear resoruces & show the buffer --
        g.dispose();
        bs.show();
    }

    private void update() {
        keyboard.update();
        player.update();
        camera.update(currentLevel);
        currentLevel.update();
    }

    public static int getWindowWidth() {
        return WIDTH * SCALE;
    }
    
    public static int getWindowHeight() {
        return HEIGHT * SCALE;
    }
}
