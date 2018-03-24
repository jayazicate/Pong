package com.example.development.pong;

import android.support.constraint.solver.widgets.Rectangle;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Paddle
 *
 * Class that creates a paddle for the user to control
 *
 * @author Joshua Azicate
 * @userID 001874248
 * @version March 2018
 */

public class Paddle {
    private static int WIDTH = 100, HEIGHT = 600;
    private int x;
    private int y;

    /**
     * Constuctor allows us to pass in are starting x and y coordinates to place the paddle
     * @param initX The initial X coordinate in which we start drawing the paddle
     * @param initY The initial Y coordinate in which we start drawing the paddle
     */
    public Paddle(int initX, int initY) {
        this.x = initX;
        this.y = initY;
    }

    /**
     * draw method draws the paddle when given a canvas
     * @param g
     */
    public void draw(Canvas g){
        Paint paddlePaint = new Paint();
        paddlePaint.setColor(Color.RED);
        g.drawRect(x, y, x+WIDTH, y+HEIGHT, paddlePaint);

    }


    /*
    THE FOLLOWING METHODS ARE SETTER AND GETTER METHODS
     */
    public void setWIDTH(int WIDTH) {
        this.WIDTH = WIDTH;
    }

    public void setHEIGHT(int HEIGHT) {
        this.HEIGHT = HEIGHT;
    }

    public void setY(int y){
        this.y = y;
    }

    public void setX(int x){
        this.x = x;
    }

    public int getX() { return x; }

    public int getY(){
        return y;
    }

    public int getWIDTH() { return WIDTH; }

    public int getHEIGHT() { return HEIGHT; }
}
