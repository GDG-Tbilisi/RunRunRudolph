package ge.gtug;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;

public class Game extends Activity 
{
	TextView result;

	int counter=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game);
		result = (TextView) findViewById(R.id.result);
      //  result.setOnTouchListener(this);
	  	result.setText("Number of steps made: " + counter);
        
	}
	

	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		if(event.getAction()==0){
		
		result.setText("Number of steps made: " + counter);
		counter++; 
		}
		
		
  		return false;
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu);
		MenuInflater koba = getMenuInflater();
		koba.inflate(R.menu.menu, menu);
		return true;
		
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.about:
			startActivity(new Intent(this, About.class));
			return true;
		case R.id.restart:
			counter = 0;
			result.setText("Number of steps made: " + counter);
			
		default:
			return super.onOptionsItemSelected(item);

		}
	}

//	public boolean onTouch(View v, MotionEvent event) {
//		// TODO Auto-generated method stub
//		
//		
//		return false;
//	}

}
