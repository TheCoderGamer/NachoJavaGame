package com.thecoder.JavaGame.entity.projectile;

public class BulletProjectile extends Projectile {

    public BulletProjectile(int x, int y, double dir) {
        super(x, y, dir);
        speed = 4;
        range = 200;
        damage = 1;
        nx = Math.cos(angle) * speed;
        ny = Math.sin(angle) * speed;
    }
}
