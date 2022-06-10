package com.thecoder.JavaGame.entity.projectile;

import com.thecoder.JavaGame.entity.Entity;
import com.thecoder.JavaGame.graphics.Screen;
import com.thecoder.JavaGame.graphics.Sprite;

public abstract class Projectile extends Entity {
    protected final int xOrigin, yOrigin;
    protected double angle;
    protected double nx, ny;
    protected double x, y; // overrides Entity.x and Entity.y
    protected double speed, range, damage;
    protected double distance;

    public Projectile(int x, int y, double dir) {
        xOrigin = x;
        yOrigin = y;
        angle = dir;
        this.x = x;
        this.y = y;
        sprite = Sprite.bullet1;
    }

    public void update() {
        move();
        calculateDistance();
        if (distance > range) remove();
    }

    private void calculateDistance() {
        double dx = x - xOrigin;
        double dy = y - yOrigin;
        distance = Math.sqrt(Math.abs(dx * dx + dy * dy));
    }

    private void move() {
        x += nx;
        y += ny;
    }

    public void render(Screen screen) {
        // Render center of bullet based on center of coords
        screen.renderEntity((int) x - sprite.SIZE / 2, (int) y - sprite.SIZE / 2, this);
    }

    public boolean isRemoved() {
        return removed;
    }
}
