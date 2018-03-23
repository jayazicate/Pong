package com.example.development.pong;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Button;

/**
 * PongMainActivity
 * 
 * This is the activity for the Pong game. It attaches a PongAnimator to
 * an AnimationSurface.
 * 
 * @author Andrew Nuxoll
 * @author Steven R. Vegdahl
 * @version July 2013
 * 
 */
public class PongMainActivity extends Activity {

	/**
	 * creates an AnimationSurface containing a TestAnimator.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pong_main);

		// Connect the animation surface with the animator
		AnimationSurface mySurface = (AnimationSurface) this
				.findViewById(R.id.animationSurface);
		mySurface.setAnimator(new BallAnimator());


		/**
		 * External Citation
		 * Date: March 21, 2018
		 * Problem: Forgot how to deal with buttons
		 * Resource:https://developer.android.com/guide/topics/ui/controls/button.html
		 */
		// Button that allows the Paddle to become smaller
		Button hardButton = (Button)findViewById(R.id.hardButton);
		hardButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Paddle player1 = new Paddle(0, 600);
				player1.setHEIGHT(300);
			}
		});

		// Button that allows the Paddle to become its normal size
		Button easyButton = (Button)findViewById(R.id.easyButton);
		easyButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Paddle player1 = new Paddle(0, 500);
				player1.setHEIGHT(600);
			}
		});

		//Button that allows a new ball to be put into play
		Button newButton = (Button)findViewById(R.id.newBallButton);
		newButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

			}
		});
	}


}
