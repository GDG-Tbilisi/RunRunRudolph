package ge.gtug;

import android.app.Activity;
import android.os.Bundle;
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
		if(event.getAction()==1){
		
		result.setText("Number of steps made: " + counter);
		counter++; 
		}
		
		
  		return false;
	}


//	public boolean onTouch(View v, MotionEvent event) {
//		// TODO Auto-generated method stub
//		
//		
//		return false;
//	}

}
