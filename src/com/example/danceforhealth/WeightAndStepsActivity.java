package com.example.danceforhealth;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class WeightAndStepsActivity extends Activity{
	private int weight;
	private int steps;
	private Workout workout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_weight_and_steps);
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			workout = (Workout) extras.get("workout");
		}
		
		TextView stepTextView = (TextView) findViewById(R.id.stepTextView);
		EditText stepEditText = (EditText) findViewById(R.id.stepEditText);
		TextView weightTextView = (TextView) findViewById(R.id.weightTextView);
		EditText weightEditText = (EditText) findViewById(R.id.weightEditText);
		Button nextButton = (Button) findViewById(R.id.nextButton);
		Typeface font = Typeface.createFromAsset(getAssets(), "Komika_display.ttf");
		stepTextView.setTypeface(font);
		stepEditText.setTypeface(font);
		weightTextView.setTypeface(font);
		weightEditText.setTypeface(font);
		nextButton.setTypeface(font);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.weight_and_steps, menu);
		return true;
	}
	
	public void onNextButtonClick(View view) {
		EditText stepEditText = (EditText) findViewById(R.id.stepEditText);
		EditText weightEditText = (EditText) findViewById(R.id.weightEditText);
		if(!stepEditText.getText().toString().trim().equals("")) {
			steps = Integer.parseInt(stepEditText.getText().toString());
			workout.setSteps(steps);
		}
		else {
			workout.setSteps(0);
		}
		if(!(weightEditText.getText().toString().trim().equals(""))) {
			weight = Integer.parseInt(weightEditText.getText().toString());
			workout.setWeight(weight);

		}
		else {
			workout.setWeight(0);
		}
	
		Log.v("duration", "= " + workout.getTime());
	
		// create an Intent using the current Activity 
		// and the Class to be created
		Intent intent = new Intent(this, HeartRateActivity.class).putExtra("workout", workout);

		// pass the Intent to the Activity, 
		// using the specified request code
		startActivity(intent);
	}
	public void onBackButtonClick(View view) {
		
		EditText stepEditText = (EditText) findViewById(R.id.stepEditText);
		EditText weightEditText = (EditText) findViewById(R.id.weightEditText);
		if(!stepEditText.getText().toString().trim().equals("")) {
			steps = Integer.parseInt(stepEditText.getText().toString());
			workout.setSteps(steps);
		}
		else {
			workout.setSteps(0);
		}
		if(!weightEditText.getText().toString().trim().equals("")) {
			weight = Integer.parseInt(weightEditText.getText().toString());
			workout.setWeight(weight);

		}
		else {
			workout.setWeight(0);
		}
		// create an Intent using the current Activity 
		// and the Class to be created
		Intent intent = new Intent(this, RatingActivity.class).putExtra("workout", workout);

		// pass the Intent to the Activity, 
		// using the specified request code
		startActivity(intent);
	}
}
