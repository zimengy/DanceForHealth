package com.example.danceforhealth;

import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class PrevWorkoutActivity extends ListActivity {
  @Override
public void onCreate(Bundle icicle) {
    super.onCreate(icicle);
    
	PrevWorkout preWorkout = PrevWorkout.getInstance();
	List<Workout> workouts = preWorkout.getPrevious();
	Workout[] values = new Workout[workouts.size()];
	for (int i = 0; i < workouts.size(); i++) {
		values[i] = workouts.get(i);
	}
    
    View header = getLayoutInflater().inflate(R.layout.header, null);
    View footer = getLayoutInflater().inflate(R.layout.footer, null);
    ListView listView = getListView();
    listView.addHeaderView(header);
    listView.addFooterView(footer);
    
    // use your custom layout
    ArrayAdapter<Workout> adapter = new MySimpleArrayAdapter(this, values);
    setListAdapter(adapter);
    
    // set font
	Typeface font_two = Typeface.createFromAsset(getAssets(), "Komika_display.ttf");
	TextView t = (TextView) findViewById(R.id.textView1);
	Button b = (Button) findViewById(R.id.preWorkoutButton);
	t.setTypeface(font_two);
	b.setTypeface(font_two);
  }
  
	public void onBackButtonClick(View view) {
		// create an Intent using the current Activity 
		// and the Class to be created
		Intent intent = new Intent(this, HomeActivity.class);

		// pass the Intent to the Activity, 
		// using the specified request code
		startActivity(intent);
	}
  
  
  @Override
  protected void onListItemClick(ListView l, View v, int position, long id) {
    Workout item = (Workout) getListAdapter().getItem(position - 1);
    Toast.makeText(this, item + " selected", Toast.LENGTH_LONG).show();
    
	Intent intent = new Intent();
	Bundle b = new Bundle();
	b.putParcelable("workout", item);
	intent.putExtras(b);

	intent.setClass(v.getContext(), WorkoutSummary.class);

	// pass the Intent to the Activity, 
	// using the specified request code
	startActivity(intent);    
  }
} 