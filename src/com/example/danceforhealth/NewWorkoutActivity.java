package com.example.danceforhealth;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class NewWorkoutActivity extends Activity implements OnItemSelectedListener{

	public String selection;
	private Button nextButton;
	private Spinner spinner;
	Workout workout = new Workout();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_workout);
		
		Bundle extras = getIntent().getExtras();
 		if (extras != null) {
 			workout = (Workout) extras.get("workout");
 		}
		

		spinner = (Spinner) findViewById(R.id.workoutTypeSpinner);
		spinner.setOnItemSelectedListener(this);
		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
		        R.array.workouts_array, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spinner.setAdapter(adapter);
		if(!workout.getType().equals("")) {
			setSpinnerSelection(workout.getType(), spinner);
		}
		EditText edittext = (EditText) findViewById(R.id.editText);
		String time = Integer.toString(workout.getTime());
		edittext.setText(time);


		selection = "Dance";

		Typeface font = Typeface.createFromAsset(getAssets(), "Komika_display.ttf");
		nextButton = (Button) findViewById(R.id.nextButton);
		nextButton.setTypeface(font);
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_workout, menu);
		return true;
	}
	
	
	public void onNextButtonClick(View view) {
		
		EditText edittext = (EditText) findViewById(R.id.editText);
		int time = Integer.parseInt(edittext.getText().toString());
		Log.v("new workout", "time = " + time);
		workout.setType(selection);
		workout.setTime(time);
		Log.v("duration", "= " + time);
		// create an Intent using the current Activity 
		// and the Class to be created
		Intent intent = new Intent(this, RatingActivity.class).putExtra("workout", workout);

		// pass the Intent to the Activity, 
		// using the specified request code
		startActivity(intent);
	}

	
	// figure out how to access string array elements--not just hardcode workouts
	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		switch(position) {
		case 0:
			selection = "Dance";
			break;
		case 1:
			selection = "Run";
			break;
		case 2:
			selection = "Walk";
			break;
		case 3:
			selection = "Bike";
			break;
		case 4:
			selection = "Swim";
		}
		
	}
	
	public void setSpinnerSelection(String type, Spinner spinner) {
		if(type.equals("Dance")) {
			spinner.setSelection(0);
		}
		else if(type.equals("Run")) {
			spinner.setSelection(1);
		}
		else if(type.equals("Walk")) {
			spinner.setSelection(2);
		}
		else if(type.equals("Bike")) {
			spinner.setSelection(3);
		}
		else if(type.equals("Swim")) {
			spinner.setSelection(4);
		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
	}
}
