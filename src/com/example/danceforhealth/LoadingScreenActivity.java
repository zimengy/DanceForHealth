package com.example.danceforhealth;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

public class LoadingScreenActivity extends Activity{
	
	private String file = "data_workout";
	private TextView welcomeTextView;
	private TextView levelTextView;

	@Override 
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final Intent i = new Intent(this, HomeActivity.class);
		setContentView(R.layout.loading_screen);
		
		try{
			FileInputStream fin = openFileInput(file);
			int c;
			String content="";
			while( (c = fin.read()) != -1){
				content = content + Character.toString((char)c);
			}
		//	Toast.makeText(getBaseContext(),"file read",
			//		Toast.LENGTH_SHORT).show();
			HomeActivity.createDatabase(content);
			Log.v("home", "read from log file");
			 
			
			//deleteFile(file);
			// clear file
			
/*			FileOutputStream fOut = openFileOutput(file,MODE_PRIVATE);
			String empty = "";
	         fOut.write(empty.getBytes());
	         fOut.close();*/
			
		}catch(Exception e){

		}
		
		
		
		CountDownTimer timer = new CountDownTimer(3000, 1000) {

			@Override
			public void onTick(long millisUntilFinished) {
				
				
			}

			@Override
			public void onFinish() {
				startActivity(i);
			}
			
		};
		Context context = this;
		String path = context.getFilesDir().getAbsolutePath() + "/data_workout";
		File file = new File(path);

		if (file.exists()) {
			try {
				readFromFile();
				Log.v("loading", "read from log file");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			file = new File(context.getFilesDir(), "data_workout");
			Log.v("loading", "created data file");
		}
		// set fonts
		welcomeTextView = (TextView) findViewById(R.id.welcomeTextView);
		Typeface font = Typeface.createFromAsset(getAssets(), "KOMIKAX_.ttf");
		Typeface font_two = Typeface.createFromAsset(getAssets(),
				"Komika_display.ttf");
		welcomeTextView.setTypeface(font);
		
		levelTextView = (TextView) findViewById(R.id.workoutLevelTextView);
		// level up every 5 hours you work out
		// starting level is level 1
		int level = calculateTime()/300 + 1;
		int nextLevel = calculateTime()/300 + 2;
		int minutesUntilNext = nextLevel*300 - calculateTime();
		levelTextView.setText("You are currently at level " + level + " and have lost "+
				 + calculateWeightLoss() + " pounds!\nWork out for " +
				minutesUntilNext + " more minutes to level up!");
		levelTextView.setTypeface(font_two);
		//leveltxt.setTyp
		timer.start();
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_workout, menu);
		return true;
	}
	
	/* need refactoring */
	public void readFromFile() throws Exception {

		String path = getFilesDir().getPath();

		String info = getStringFromFile(path);

		PrevWorkout pw = PrevWorkout.getInstance();
		List<Workout> workouts = (ArrayList<Workout>) pw.getPrevious();

		String delims = "\n";
		String[] lines;
		String[] tokens;

		lines = info.split(delims);

		for (String line : lines) {
			String del = " ";
			tokens = line.split(del);

			String type = tokens[0];
			String str = tokens[1];
			int strain = Integer.parseInt(str);
			String heart = tokens[2];
			int hr = Integer.parseInt(heart);
			String st = tokens[3];
			int steps = Integer.parseInt(st);
			String w = tokens[4];
			int weight = Integer.parseInt(w);
			String date = tokens[5];

			Workout wo = new Workout(type, strain, hr, steps, weight);
			wo.setDate(date);
			workouts.add(wo);
		}

	}
	
	// calculates the level that the person is currently at by their time
	public int calculateTime() {
		PrevWorkout workouts = PrevWorkout.getInstance();
		if(workouts.size() == 0) {
			return 0;
		}
		int sum = 0;
		for (Workout w : workouts.getPrevious()) {
			sum += w.getTime();
		}
		return sum;
	}

	// calculates the level the person is currently at by their weight loss
	public int calculateWeightLoss() {
		PrevWorkout workouts = PrevWorkout.getInstance();
		if(workouts.size()==0) {
			return 0;
		}
		int startWeight = workouts.getPrevious().get(0).getWeight();
		int finishWeight = workouts.getPrevious().get(workouts.size() - 1).getWeight();
		int weightLoss = finishWeight - startWeight;
		return weightLoss;
	}
	
	public static String convertStreamToString(InputStream is) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		String line = null;
		while ((line = reader.readLine()) != null) {
			sb.append(line).append("\n");
		}
		reader.close();

		return sb.toString();
	}



	public static String getStringFromFile(String filePath) throws Exception {
		File file = new File(filePath);
		FileInputStream fin = new FileInputStream(file);
		String ret = convertStreamToString(fin);
		// Make sure you close all streams.
		fin.close();
		return ret;
	}
}
