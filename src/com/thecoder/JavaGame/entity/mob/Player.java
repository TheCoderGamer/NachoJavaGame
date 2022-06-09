package com.thecoder.JavaGame.entity.mob;

import com.thecoder.JavaGame.App;
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
    public Sprite sprite = Sprite.playerDown1;
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
        this.x = x;
        this.y = y;
        this.kb = kb;
        collisionBox = new Box(x - 6, y + 6, x + 6, y + 12);
    }

    @Override
    public void update() {
        // Input
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
            double dx = Mouse.getX() - Game.getWindowWidth() / 2;
            double dy = Mouse.getY() - Game.getWindowHeight() / 2;
            double dir = Math.atan2(dy, dx);
            //dir = Math.toDegrees(dir);
            shoot(x, y, dir);
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
