package com.thecoder.JavaGame.entity.mob;

import com.thecoder.JavaGame.graphics.Screen;
import com.thecoder.JavaGame.input.Keyboard;

public class Player extends Mob {

    private Keyboard kb;

    public Player(int x, int y, Keyboard kb) {
        this.x = x;
        this.y = y;
        this.kb = kb;
    }
    public Player(Keyboard kb){
        this.kb = kb;
    }

    @Override
    public void move() {

    }

    @Override
    public boolean collision(int xa, int ya) {
        return false;

    }

    @Override
    public void update() {
        // Input
        if (kb.up) y--;
        if (kb.down) y++;
        if (kb.left) x--;
        if (kb.right) x++;
        
        move();
    }

    @Override
    public void render(Screen screen) {

    }

    @Override
    public void remove() {

    }

}
