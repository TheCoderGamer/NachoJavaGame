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

        // TODO: Animation
    }

    @Override
    public void render(Screen screen) {
        

        screen.renderPlayer(x, y, this);
    }

    @Override
    public void remove() {

    }

}
