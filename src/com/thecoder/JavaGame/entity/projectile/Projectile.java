package com.thecoder.JavaGame.entity.projectile;

import com.thecoder.JavaGame.entity.Entity;
import com.thecoder.JavaGame.graphics.Screen;
import com.thecoder.JavaGame.graphics.Sprite;

public abstract class Projectile extends Entity {
    protected final int xOrigin, yOrigin;
    protected double angle;
    protected Sprite sprite;
    protected double nx, ny;
    protected double speed, range, damage;

    public Projectile(int x, int y, double dir) {
        xOrigin = x;
        yOrigin = y;
        angle = dir;
        this.x = x;
        this.y = y;
    }

    public void update() {
        move();
        //if (distance() > range) remove();
    }

    private double distance() {
        double dx = x - xOrigin;
        double dy = y - yOrigin;
        return Math.sqrt(dx * dx + dy * dy);
    }

    private void move() {
        x += nx;
        y += ny;
    }

    public void render(Screen screen) {
        screen.renderTile(x, y, Sprite.voidTile);
    }

    public void remove() {
        level.removeEntity(this);
    }
}
