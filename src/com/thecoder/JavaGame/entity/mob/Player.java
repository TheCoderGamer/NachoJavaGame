package com.thecoder.JavaGame.entity.mob;

import com.thecoder.JavaGame.Game;
import com.thecoder.JavaGame.graphics.Box;
import com.thecoder.JavaGame.graphics.Screen;
import com.thecoder.JavaGame.graphics.Sprite;
import com.thecoder.JavaGame.graphics.level.TileCoordinate;
import com.thecoder.JavaGame.input.Keyboard;
import com.thecoder.JavaGame.input.Mouse;
import com.thecoder.JavaGame.utils.Logger;

public class Player extends Mob {

    private int xa, ya;
    private Keyboard kb;
    private int anim;
    public int speed = 60; // pixels per second
    private int speedCounter = 0;

    public Player(int x, int y, Keyboard kb) {
        createPlayer(x, y, kb);
    }

    public Player(TileCoordinate spawnPos, Keyboard kb) {
        createPlayer(spawnPos.x, spawnPos.y, kb);
    }

    private void createPlayer(int x, int y, Keyboard kb) {
        Logger.log("Player created x: " + x + ", y: " + y + ", speed: " + speed + " pixels per second");
        sprite = Sprite.playerDown1;
        this.x = x;
        this.y = y;
        this.kb = kb;
        collisionBox = new Box(x - 6, y + 6, x + 6, y + 12);
    }

    @Override
    public void update() {
        // Moving
        xa = 0;
        ya = 0;
        if (kb.up)
            ya--;
        if (kb.down)
            ya++;
        if (kb.left)
            xa--;
        if (kb.right)
            xa++;
        if (xa != 0 || ya != 0) {
            moving = true;
            // Limit updates per second to control speed
            if (speedCounter >= Game.WANTED_UPS / speed) {
                speedCounter = 0;
                move(xa, ya);
            }
            speedCounter++;
        } else {
            moving = false;
        }

        // Shooting
        shootingManager();

        // Animation
        if (anim < 7500 && moving)
            anim++;
        else
            anim = 0;

        if (dirX == 1) { // Right
            sprite = Sprite.playerRight1;
            if (moving) {
                if (anim % 20 > 10) {
                    sprite = Sprite.playerRight2;
                } else {
                    sprite = Sprite.playerRight3;
                }
            }
        }

        if (dirX == -1) { // Left
            sprite = Sprite.playerLeft1;
            if (moving) {
                if (anim % 20 > 10) {
                    sprite = Sprite.playerLeft2;
                } else {
                    sprite = Sprite.playerLeft3;
                }
            }
        }
        if (dirY == 1) { // Up
            sprite = Sprite.playerUp1;
            if (moving) {
                if (anim % 20 > 10) {
                    sprite = Sprite.playerUp2;
                } else {
                    sprite = Sprite.playerUp3;
                }
            }
        }
        if (dirY == -1) { // Down
            sprite = Sprite.playerDown1;
            if (moving) {
                if (anim % 20 > 10) {
                    sprite = Sprite.playerDown2;
                } else {
                    sprite = Sprite.playerDown3;
                }
            }
        }
    }

    private void shootingManager() {
        if (Mouse.isLeftButtonPressed()) {
            // Calculate mouse angle based on real player position on screen
            double dx = (Mouse.getX() / Game.SCALE) - (x - Game.screen.xOffset);
            double dy = (Mouse.getY() / Game.SCALE) - (y - Game.screen.yOffset);
            double dir = Math.atan2(dy, dx);
            // dir = Math.toDegrees(dir);
            shoot(x, y, dir);
        }

        // Clear
        for (int i = 0; i < projectiles.size(); i++) {
            if (projectiles.get(i).isRemoved()) {
                level.removeEntity(projectiles.get(i));
                projectiles.remove(projectiles.get(i));
            }
        }
    }

    @Override
    public void render(Screen screen) {
        screen.renderPlayer(x - 16, y - 20, this);
        screen.renderBox(collisionBox); // Debug
        screen.renderDot(x, y); // Debug
    }

    @Override
    public void remove() {
    }

}
