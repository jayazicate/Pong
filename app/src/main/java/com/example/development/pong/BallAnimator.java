package com.example.development.pong;

import android.graphics.*;
import android.view.MotionEvent;


/**
 * class that animates a ball repeatedly moving diagonally on
 * simple background
 *
 * @author Joshua Azicate
 */
public class BallAnimator implements Animator {

	// instance variables
	private int count = 0; // counts the number of logical clock ticks
	private boolean goBackwards = false; // whether clock is ticking backwards
	private int ballX; // the position in which the ball is in horizontally
	private int ballY = 900; // the initial position in which the ball starts
	private int maxYV = 70; // the maximum velocity in which the ball will go in the y direction
	private int maxXV = 70; //the maximum velocity in which the ball will go in the x direction

	//creates an instance of a Paddle object, setting the initial placement at x=0, y=500
	Paddle player1 = new Paddle(0,500);

	//creates an instance of a Ball object
	Ball ball = new Ball(50, ballX, ballY, (int)(Math.random()*maxXV), (int)(Math.random()*maxYV));

	/**
	 * Interval between animation frames: .03 seconds (i.e., about 33 times
	 * per second).
	 *
	 * @return the time interval between frames, in milliseconds.
	 */
	public int interval() {
		return 0;
	}

	/**
	 * The background color: a light blue.
	 *
	 * @return the background color onto which we will draw the image.
	 */
	public int backgroundColor() {
		// create/return the background color
		return Color.rgb(117, 216, 230);
	}

	/**
	 * Tells the animation whether to go backwards.
	 *
	 * @param b true iff animation is to go backwards.
	 */
	public void goBackwards(boolean b) {
		// set our instance variable
		goBackwards = b;
	}

	/**
	 * Action to perform on clock tick
	 *
	 * @param g the graphics object on which to draw
	 */
	public void tick(Canvas g) {

		player1.draw(g);
		// Determine the pixel position of our ball.  Multiplying by 15
		// has the effect of moving 15 pixel per frame.  Modding by 600
		// (with the appropriate correction if the value was negative)
		// has the effect of "wrapping around" when we get to either end
		// (since our canvas size is 600 in each dimension).
		ballX = (ball.getxPos()+ball.getxV());
		ballY = (ball.getyPos()+ball.getyV());

		ball.draw(g);
		ball.setxPos(ballX);
		ball.setyPos(ballY);

		if(hitWallY(g)){
			ball.setyV(-ball.getyV());
		}

		if(hitWallX(g)){
			ball.setxV(-ball.getxV());
		}

		if(hitPlayerWall(g)){
			ball.setxPos((int)(Math.random())*g.getWidth());
			ball.setyPos((int)(Math.random()*g.getHeight()));
			ball.setxV((int)(Math.random()*maxXV));
			ball.setyV((int)(Math.random()*maxYV));
		}

		if(hitPaddle(g)){
			ball.setxV(-ball.getxV());
		}

	}

	public boolean hitWallY(Canvas g){
		if(ball.getyPos() >= g.getHeight()){
			return true;
		} else if (ball.getyPos() <= 0){
			return true;
		}
		return false;
	}

	public boolean hitWallX(Canvas g){
		if(ball.getxPos() >= g.getWidth()){
			return true;
		}
		return false;
	}

	public boolean hitPlayerWall(Canvas g){
		if(ball.getxPos() <= 0){
			return true;
		}
		return false;
	}

	public boolean hitPaddle(Canvas g){
		int paddleHeight = player1.getY()+player1.getHEIGHT();

		if(ball.getxPos() <= player1.getWIDTH()  && ball.getyPos() <= paddleHeight
				&& ball.getyPos() >= player1.getY()){
			return true;
		}
		return false;
	}




	/**
	 * Tells that we never pause.
	 *
	 * @return indication of whether to pause
	 */
	public boolean doPause() {
		return false;
	}

	/**
	 * Tells that we never stop the animation.
	 *
	 * @return indication of whether to quit.
	 */
	public boolean doQuit() {
		return false;
	}

	/**
	 * reverse the ball's direction when the screen is tapped
	 */
	public void onTouch(MotionEvent event)
	{
		if (event.getAction() == MotionEvent.ACTION_DOWN)
		{
			goBackwards = !goBackwards;
		}
	}



}//class TextAnimator
