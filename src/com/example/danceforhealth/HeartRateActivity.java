package com.example.danceforhealth;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HeartRateActivity extends Activity {
	
	private Workout workout;
	private TextView howToTextView;
	private TextView textView;
	private Button nextButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_heart_rate);
		
		Bundle extras = getIntent().getExtras();
 		if (extras != null) {
 		    workout = (Workout) extras.get("workout");
 		}
 		

		howToTextView = (TextView) findViewById(R.id.howToTextView);
		textView = (TextView) findViewById(R.id.textView);
		nextButton = (Button) findViewById(R.id.nextButton);
		Typeface font = Typeface.createFromAsset(getAssets(), "Komika_display.ttf");
		howToTextView.setTypeface(font);
		textView.setTypeface(font);
		nextButton.setTypeface(font);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.heart_rate, menu);
		return true;
	}
	
	public void onNextButtonClick(View view) {
		
		// create an Intent using the current Activity 
		// and the Class to be created
		Intent intent = new Intent(this, HeartRateTwo.class).putExtra("workout", workout);

		// pass the Intent to the Activity, 
		// using the specified request code
		startActivity(intent);
	}
	
public void onBackButtonClick(View view) {
		
		// create an Intent using the current Activity 
		// and the Class to be created
		Intent intent = new Intent(this, WeightAndStepsActivity.class).putExtra("workout", workout);

		// pass the Intent to the Activity, 
		// using the specified request code
		startActivity(intent);
	}

}
