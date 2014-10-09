package com.example.danceforhealth;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class HeartRateTwo extends Activity {

	
	CountDownTimer timer;
	private Workout workout;
	private EditText edittext;
	private TextView textview;
	private TextView twoTextView;
	private Button timerButton;
	private TextView threeTextView;
	private Button heartRateButton;
	private TextView heartRateTextView;
	private Button viewSummaryButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_heart_rate_two);
		Typeface font = Typeface.createFromAsset(getAssets(), "Komika_display.ttf");
		
		textview = (TextView)findViewById(R.id.timerTextView);
		textview.setTypeface(font);
        timer = new CountDownTimer(18000, 1000) {

            @Override
			public void onTick(long millisUntilFinished) {
            	if (millisUntilFinished > 17000) {
            		textview.setText("Ready?");
            	} else if (millisUntilFinished > 16000) {
            		textview.setText("Set?");
            	} else if (millisUntilFinished > 15000) {
            		textview.setText("Start Counting!");
            	} else {
            		textview.setText("Time left: " + millisUntilFinished / 1000);
            	}
            }

            @Override
			public void onFinish() {
            	textview.setText("Stop Counting!");
            }
         };
 		
 		Bundle extras = getIntent().getExtras();
 		if (extras != null) {
 			workout = (Workout) extras.get("workout");
 		}
 		
 		edittext = (EditText) findViewById(R.id.heartRateEditText);
		
		twoTextView = (TextView) findViewById(R.id.twoTextView);
		timerButton = (Button) findViewById(R.id.timerButton);
		threeTextView = (TextView) findViewById(R.id.threeTextView);
		heartRateButton = (Button) findViewById(R.id.heartRateButton);
		heartRateTextView = (TextView) findViewById(R.id.heartRateTextView);
		viewSummaryButton = (Button) findViewById(R.id.viewSummaryButton);
		twoTextView.setTypeface(font);
		threeTextView.setTypeface(font);
		heartRateTextView.setTypeface(font);
		edittext.setTypeface(font);
		timerButton.setTypeface(font);
		heartRateButton.setTypeface(font);
		viewSummaryButton.setTypeface(font);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.heart_rate_two, menu);
		return true;
	}
	
	public void startTimer(View view) {
		timer.start();
	}
	
	public void onNextButtonClick(View view) throws IOException {

		PrevWorkout preWorkout = PrevWorkout.getInstance();
		List<Workout> all = preWorkout.getPrevious();
		
		// set date for Workout object
		Date date = new Date();
		SimpleDateFormat ft = 
				new SimpleDateFormat ("E M dd yyyy");
		workout.setDate(ft.format(date));
		
		// add workout to database
		if (!workout.getUpdate()) {
		all.add(workout);
		}

		// add workout to internal memory
		String data = workout.getType() + "," + workout.getStrain() + "," + workout.getHeartrate() + "," + workout.getSteps() 
				+ "," + workout.getWeight() + "," + workout.getDate() + "," + workout.getTime() + "\n";
		String file = "data_workout";
		
		Log.v("duration", "= " + workout.getTime());

		
	      try {
	         FileOutputStream fOut = openFileOutput(file,MODE_APPEND);
	         fOut.write(data.getBytes());
	         fOut.close();
	      //   Toast.makeText(getBaseContext(),"file saved",
	     //    Toast.LENGTH_SHORT).show();
	      } catch (Exception e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      }
		
		// create an Intent using the current Activity 
		// and the Class to be created
		Intent intent = new Intent(this, WorkoutSummary.class).putExtra("workout", workout);


		// pass the Intent to the Activity, 
		// using the specified request code
		startActivity(intent);
	}
	
	public void onHRButtonClick(View view) {
		TextView heartRateTextView = (TextView)findViewById(R.id.heartRateTextView);
		String input = edittext.getText().toString();
		int numIn = Integer.parseInt(input) * 4;
		heartRateTextView.setText("\nYour heart rate is: " + numIn + "\n");
		int heartrate = numIn;
		workout.setHeartrate(numIn);
	}
	public void onBackButtonClick(View view) {
		// create an Intent using the current Activity 
		// and the Class to be created
		Intent intent = new Intent(this, HeartRateActivity.class).putExtra("workout", workout);

		// pass the Intent to the Activity, 
		// using the specified request code
		startActivity(intent);
	}

}
