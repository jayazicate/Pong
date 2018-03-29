package com.example.development.pong;

import android.graphics.*;
import android.view.MotionEvent;

import java.util.ArrayList;


/**
 * BallAnimator
 *
 * Class that shows a ball that bounces around
 *
 * @author Joshua Azicate
 * @userID 001874248
 * @version March 2018
 */
public class BallAnimator implements Animator {

	private boolean goBackwards = false; // whether clock is ticking backwards
	private int ballX = 200; // the position in which the ball is in horizontally
	private int ballY = 900; // the initial position in which the ball starts
	private int maxYV = 70; // the maximum velocity in which the ball will go in the y direction
	private int maxXV = 70; //the maximum velocity in which the ball will go in the x direction
	private int score = 0; // the amount of balls a player has left
	private int touchY; // the value for touch in y direction
	private Paint scorePaint; // paint for score
	private Paint smallPaint; // paint for instructions



	//creates an instance of a Paddle object, setting the initial placement at x=0, y=500
	Paddle player1 = new Paddle(0,500);



	//creates an instance of a Ball object
	ArrayList<Ball> balls = new ArrayList<Ball>();
	Ball firstBall = new Ball(50, ballX, ballY, (int)(Math.random()*maxXV), (int)(Math.random()*maxYV));


	public BallAnimator(){
		balls.add(firstBall);
		scorePaint = new Paint();
		scorePaint.setColor(Color.BLACK);
		scorePaint.setTextSize(100);
		smallPaint = new Paint();
		smallPaint.setColor(Color.BLACK);
		smallPaint.setTextSize(20);
	}


	/**
	 * Interval between animation frames: .03 seconds (i.e., about 33 times
	 * per second).
	 *
	 * @return the time interval between frames, in milliseconds.
	 */
	public int interval() {
		return 0;
	}//interval

	/**
	 * The background color: a light blue.
	 *
	 * @return the background color onto which we will draw the image.
	 */
	public int backgroundColor() {
		// create/return the background color
		return Color.rgb(117, 216, 230);
	}//backgroundColor

	/**
	 * Tells the animation whether to go backwards.
	 *
	 * @param b true iff animation is to go backwards.
	 */
	public void goBackwards(boolean b) {
		// set our instance variable
		goBackwards = b;
	}//goBackwards

	/**
	 * Action to perform on clock tick
	 *
	 * @param g the graphics object on which to draw
	 */
	public void tick(Canvas g) {

		String stateString = "Current Score:";
		String instructions = "Goal: Make ball hit other wall to gain points";
		String instructions2 = "Lose points when ball passes paddle.";
		String instructions3 = "If you hit zero points, you lose and app closes.";
		//balls.add(firstBall);
		//creates an instance of a Ball object
		player1.draw(g);



		for( int i = 0; i < balls.size(); i++ ) {

			Ball ball = balls.get(i);
			// Determine the pixel position of our ball.  Pixel position is determined by the
			// current pixel position and the current velocity
			ball.setxPos((ball.getxPos() + ball.getxV()));
			ball.setyPos((ball.getyPos() + ball.getyV()));


			ball.draw(g); // Calls the draw method that draws the shape of the ball
			//ball.setxPos(ballX); // Sets the default x position of the ball when starting
			//ball.setyPos(ballY); // Sets the default y position of the ball when starting


			// If the following condition (hitWallY (hitting either the top or bottom wall) is present,
			// then the ball will change the direction and speed in the y direction
			// when the ball hits the wall, the y velocity will be somewhat random
			if (hitWallY(g)) {
				int mode = (int)(Math.random()*2);
				if(mode == 1){
					ball.setyV(-ball.getyV() + ((int)Math.random()*3));
				} else {
					ball.setyV(-ball.getyV() + ((int)Math.random()*15));
				}
			}

			// If the following condition (hitWallX (hitting the wall farthest to the right)) is present
			// then the ball will change the direction and speed in the x direction
			// when the ball hits a wall, the x velocity will be somewhat random
			if (hitWallX(g)) {
				int mode = (int)(Math.random()*2);
				if(mode == 1) {
					ball.setxV(-ball.getxV() + ((int)Math.random()*3));
				} else {
					ball.setxV(-ball.getxV() + ((int)Math.random()*15));
				}
				score+=5;
			}

			// If the following condition (hitPlayerWall (hitting the wall on the side of player's paddle))
			// is present, then the ball will reset with new/random parameters
			// Also removes the current ball to prompt user to create new ball
			if (hitPlayerWall(g)) {
				balls.remove(i);
				ball.setxPos((int)(Math.random()*g.getWidth()));
				ball.setyPos((int)(Math.random()*g.getHeight()));
				ball.setxV((int)(Math.random()*maxXV));
				ball.setyV((int)(Math.random()*maxYV));
				score-=10;
			}

			// If the following condition (hitPaddle (hitting the paddle)) is present, then the ball
			// will bounce in the opposite x direction
			if (hitPaddle(g)) {
				ball.setxV(-ball.getxV()+((int)Math.random()*3));
			}

			//Will exit game if the score reaches below zero
			if(score < 0){
				stateString = "GAME OVER";
				System.exit(0);
			}

			//Text shows instructions and score
			g.drawText(instructions, 300, g.getHeight()-150, smallPaint);
			g.drawText(instructions2, 300, g.getHeight()-100, smallPaint);
			g.drawText(instructions3, 300, g.getHeight()-50, smallPaint);
			g.drawText(stateString, 300, 100, scorePaint);
			g.drawText(""+score,1000,100, scorePaint);


		}


	}//tick

	/**
	 * Checks to see if the ball has hit either the top or bottom wall
	 * @param g the graphics object that we are checking
	 * @return
	 */
	public boolean hitWallY(Canvas g){
		if(firstBall.getyPos() >= g.getHeight()){
			return true;
		} else if (firstBall.getyPos() <= 0){
			return true;
		}
		return false;
	}//hitWallY

	/**
	 * Checks to see if the ball has hit the right side wall
	 * @param g the graphics object that we are checking
	 * @return
	 */
	public boolean hitWallX(Canvas g){
		if(firstBall.getxPos() >= g.getWidth()){
			return true;
		}
		return false;
	}//hitWallX

	/**
	 * Checks to see if the ball has hit the wall with the paddle
	 * @param g the graphics object that we are checking
	 * @return
	 */
	public boolean hitPlayerWall(Canvas g){
		if(firstBall.getxPos() <= 0){
			return true;
		}
		return false;
	}//hitPlayerWall

	/**
	 * Checks to see if the ball has hit the paddle
	 * @param g the graphics object that we are checking
	 * @return
	 */
	public boolean hitPaddle(Canvas g){
		int paddleHeight = player1.getY()+player1.getHEIGHT();

		if(firstBall.getxPos() <= player1.getWIDTH()  && firstBall.getyPos() <= paddleHeight
				&& firstBall.getyPos() >= player1.getY()){
			return true;
		}
		return false;
	}//hitPaddle

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
		touchY = (int)event.getY();
		player1.setY(touchY);
	}//onTouch

	/**
	 * Adds new ball to the ArrayList
	 */
	public void addNewBall(){
		balls.add(firstBall);
	}//addNewBall

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}


}//class TextAnimator
