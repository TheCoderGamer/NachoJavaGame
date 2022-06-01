package com.thecoder.JavaGame.entity;

import java.util.Random;
import com.thecoder.JavaGame.graphics.Screen;
import com.thecoder.JavaGame.graphics.level.Level;

public abstract class Entity {
    public int x, y;
    protected Level level;
    protected Random random = new Random();

    public abstract void update();

    public abstract void render(Screen screen);

    public abstract void remove();
}
