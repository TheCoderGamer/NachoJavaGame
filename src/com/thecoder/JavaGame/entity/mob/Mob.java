package com.thecoder.JavaGame.entity.mob;

import com.thecoder.JavaGame.entity.Entity;
import com.thecoder.JavaGame.graphics.Sprite;

public abstract class Mob extends Entity {

    protected Sprite sprite;
    protected int dirX = 0; // -1 = left, 1 = right
    protected int dirY = 0; // 1 = up, -1 = down
    protected boolean moving = false;

    public void move(int xa, int ya) {
        if (xa > 0) dirX = 1;
        else if (xa < 0) dirX = -1;
        else dirX = 0;
        if (ya > 0) dirY = -1;
        else if (ya < 0) dirY = 1;
        else dirY = 0;

        if (!collision(xa, ya)) {
            x += xa;
            y += ya;
        }
    }

    public abstract void move();

    public abstract boolean collision(int xa, int ya);

}
