package com.thecoder.JavaGame.entity.mob;

import com.thecoder.JavaGame.graphics.Screen;
import com.thecoder.JavaGame.graphics.Sprite;
import com.thecoder.JavaGame.input.Keyboard;

public class Player extends Mob {

    private int xa;
    private int ya;
    private Keyboard kb;
    public Sprite sprite = Sprite.playerDown1;
    private int anim;


    public Player(int x, int y, Keyboard kb) {
        this.x = x;
        this.y = y;
        this.kb = kb;
    }


    @Override
    public boolean collision(int xa, int ya) {
        return false;

    }

    @Override
    public void update() {
        // Input
        xa = 0;
        ya = 0;
        if (kb.up) ya--;
        if (kb.down) ya++;
        if (kb.left) xa--;
        if (kb.right) xa++;
        if (xa != 0 || ya != 0) {
            move(xa, ya);
        } else {
            moving = false;
        }

        // Animation
        if (anim < 7500) anim++;
        else anim = 0;
        
        if (dirX == 1) { // Right
            if (moving) {
                sprite = Sprite.playerRight1;
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
            if (moving) {
                sprite = Sprite.playerUp1;
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

    @Override
    public void render(Screen screen) {
        

        screen.renderPlayer(x, y, this);
    }

    @Override
    public void remove() {

    }

}
