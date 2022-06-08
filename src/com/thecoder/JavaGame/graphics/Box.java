package com.thecoder.JavaGame.graphics;

public class Box {
    public int x1, y1, x2, y2;
    public int width, height;

    public Box(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.width = x2 - x1;
        this.height = y2 - y1;
    }

    public void move(int xa, int ya) {
        x1 += xa;
        y1 += ya;
        x2 += xa;
        y2 += ya;
    }
}
