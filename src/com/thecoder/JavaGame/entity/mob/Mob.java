package com.thecoder.JavaGame.entity.mob;

import java.util.ArrayList;
import java.util.List;

import com.thecoder.JavaGame.entity.Entity;
import com.thecoder.JavaGame.entity.projectile.BulletProjectile;
import com.thecoder.JavaGame.entity.projectile.Projectile;
import com.thecoder.JavaGame.graphics.Box;
import com.thecoder.JavaGame.utils.Logger;

public abstract class Mob extends Entity {

    protected int dirX = 0; // -1 = left, 1 = right
    protected int dirY = 0; // 1 = up, -1 = down
    protected boolean moving = false;
    public Box collisionBox;

    protected List<Projectile> projectiles = new ArrayList<Projectile>();

    public void move(int xa, int ya) {
        // Sets direction
        if (xa > 0) dirX = 1;
        else if (xa < 0) dirX = -1;
        else dirX = 0;
        if (ya > 0) dirY = -1;
        else if (ya < 0) dirY = 1;
        else dirY = 0;

        // Move the mob if not colliding
        if (!collision(xa, 0)) {
            x += xa;
            moving = true;
            collisionBox.move(xa, 0);
        } 
        if (!collision(0, ya)) {
            y += ya;
            moving = true;
            collisionBox.move(0, ya);
        }
    }

    public boolean collision(int xa, int ya) {
        if (level == null) {
            Logger.log("Level is null", "ERROR");
            throw new NullPointerException("Level is null");
        }

        // up side
        for (int c = collisionBox.x1; c < collisionBox.x1 + collisionBox.width; c++) {
            if (level.getTile((c + xa) >> 4, (collisionBox.y1 + ya) >> 4).isSolid()) {
                return true;
            }
        }
        // down side
        for (int c = collisionBox.x2 - collisionBox.width; c < collisionBox.x2; c++) {
            if (level.getTile((c + xa) >> 4, (collisionBox.y2 + ya) >> 4).isSolid()) {
                return true;
            }
        }
        // left side
        for (int c = collisionBox.y1; c < collisionBox.y1 + collisionBox.height; c++) {
            if (level.getTile((collisionBox.x1 + xa) >> 4, (c + ya) >> 4).isSolid()) {
                return true;
            }
        }
        // right side
        for (int c = collisionBox.y2 - collisionBox.height; c < collisionBox.y2; c++) {
            if (level.getTile((collisionBox.x2 + xa) >> 4, (c + ya) >> 4).isSolid()) {
                return true;
            }
        }
        return false;
    }

    protected void shoot(int x, int y, double dir){
        Projectile p = new BulletProjectile(x, y, dir);
        projectiles.add(p);
        level.add(p);
    }
}
