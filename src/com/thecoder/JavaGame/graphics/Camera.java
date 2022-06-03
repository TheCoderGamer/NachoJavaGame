package com.thecoder.JavaGame.graphics;

import com.thecoder.JavaGame.entity.mob.Player;
import com.thecoder.JavaGame.graphics.level.Level;

public class Camera {
    public int x, y;

    private Player player;
    private Screen screen;

    public Camera(Player player, Screen screen) {
        this.player = player;
        this.screen = screen;
    }

    public void update(Level level) {
        x = player.x - (screen.width / 2) + 16;
        y = player.y - (screen.height / 2) + 16;

        // if (x < -16) x = -16;
        // if (y < -16) y = -16;
        // if (x > (level.width * level.tileSize) - screen.width + 16) x = (level.width * level.tileSize) - screen.width + 16;
        // if (y > (level.height * level.tileSize) - screen.height + 16) y = (level.height * level.tileSize) - screen.height + 16;
    }


}
