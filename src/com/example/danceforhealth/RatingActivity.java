package com.example.danceforhealth;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class RatingActivity extends Activity{
	private Workout workout;
	private Button nextButton;
	private TextView tv_instruction;
	private TextView tv_like;
	private TextView tv_fun;
	private TextView tv_tired;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rating);
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			workout = (Workout) extras.get("workout");
		}
		
		tv_instruction = (TextView) findViewById(R.id.instructionTextView);
		tv_like = (TextView) findViewById(R.id.likeTextView);
		tv_fun = (TextView) findViewById(R.id.funTextView);
		tv_tired = (TextView) findViewById(R.id.tiredTextView);
		nextButton = (Button) findViewById(R.id.nextButton);
		Typeface font = Typeface.createFromAsset(getAssets(), "Komika_display.ttf");
		tv_instruction.setTypeface(font);
		tv_like.setTypeface(font);
		tv_fun.setTypeface(font);
		tv_tired.setTypeface(font);
		nextButton.setTypeface(font);

	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.rating, menu);
		return true;
	}
	
	public void onNextButtonClick(View view) {
		RadioGroup rg1 = (RadioGroup) findViewById(R.id.radioGroup1);
		int id1 = rg1.getCheckedRadioButtonId();
		RadioButton b1 = (RadioButton) findViewById(id1);
		int index1 = rg1.indexOfChild(b1);
		
		RadioGroup rg2 = (RadioGroup) findViewById(R.id.RadioGroup01);
		int id2 = rg2.getCheckedRadioButtonId();
		RadioButton b2 = (RadioButton) findViewById(id2);
		int index2 = rg2.indexOfChild(b2);
		
		RadioGroup rg3 = (RadioGroup) findViewById(R.id.RadioGroup04);
		int id3 = rg3.getCheckedRadioButtonId();
		RadioButton b3 = (RadioButton) findViewById(id3);
		int index3 = rg3.indexOfChild(b3);
		
		int liked = 7 - index1 + 1;
		int fun = 7 - index2 + 1;
		int tired = index3 + 1;
		// strain is average of survey
		int strain = (liked + fun + tired)/3;
		
		// create an Intent using the current Activity 
		// and the Class to be created
		workout.setStrain(strain);
			
		Log.v("duration", "= " + workout.getTime());
			
		Intent intent = new Intent(this, WeightAndStepsActivity.class).putExtra("workout", workout);
	
		// pass the Intent to the Activity, 
		// using the specified request code
		startActivity(intent);
		
	}
//	public void onBackButtonClick(View view) {
//		
//		// create an Intent using the current Activity 
//		// and the Class to be created
//		Intent i = new Intent(this, NewWorkoutActivity.class).putExtra("workout", w);
//
//		// pass the Intent to the Activity, 
//		// using the specified request code
//		startActivity(i);
//}
}
