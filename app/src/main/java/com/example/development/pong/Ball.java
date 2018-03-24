package com.example.development.pong;
/**
 * Ball
 *
 * Class that animates a "ball."
 * Created by Joshua Azicate on 3/21/18.
 * @userID 001874248
 */


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
public class Ball {

    private int radius;
    private int xPos;
    private int yPos;
    private int xV;
    private int yV;
    Paint ballPaint;


    /**
     * Constructor that allow us to pass in radius, position and velocity
     * @param initRadius radius of the ball
     * @param initxPos initial x position of the ball
     * @param inityPos initial y position of the ball
     * @param initxV initial x velocity
     * @param inityV initial y velocity
     */
    public Ball (int initRadius, int initxPos, int inityPos, int initxV, int inityV){
        this.radius = initRadius;
        this.xPos = initxPos;
        this.yPos = inityPos;
        this.xV = initxV;
        this.yV = inityV;
    }

    /**
     * Draws out the ball given a canvas
     * @param g the graphics object that we are drawing on
     */
    public void draw(Canvas g){
        ballPaint = new Paint();
        ballPaint.setColor(Color.rgb(255,255,255));
        g.drawCircle(xPos, yPos, radius, ballPaint);

    }

    //SETTER AND GETTER METHODS
    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public int getxV() {
        return xV;
    }

    public void setxV(int xV) {
        this.xV = xV;
    }

    public int getyV() {
        return yV;
    }

    public void setyV(int yV) {
        this.yV = yV;
    }







}
