package com.example.development.pong;

import android.support.constraint.solver.widgets.Rectangle;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by Joshua Azicate on 3/19/18.
 */

public class Paddle {
    private int WIDTH = 100, HEIGHT = 600;
    private int x;
    private int y;

    public Paddle(int initX, int initY) {
        this.x = initX;
        this.y = initY;
    }



    public void draw(Canvas g){
        Paint paddlePaint = new Paint();
        paddlePaint.setColor(Color.RED);
        g.drawRect(x, y, x+WIDTH, y+HEIGHT, paddlePaint);

    }

    public int getX() { return x; }

    public void setWIDTH(int WIDTH) {
        this.WIDTH = WIDTH;
    }

    public void setHEIGHT(int HEIGHT) {
        this.HEIGHT = HEIGHT;
    }

    public int getY(){
        return y;
    }

    public int getWIDTH() { return WIDTH; }

    public int getHEIGHT() { return HEIGHT; }
}
