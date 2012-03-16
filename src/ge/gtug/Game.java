 package ge.gtug;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Game extends Activity 
{
	public TextView result, time;
	public ImageView pirveli, meore, mesame, meotxe, mexute, meeqvse, meshvide;
	int[] drawableIds = {R.drawable.pirveli,R.drawable.meore, R.drawable.mesame, R.drawable.meotxe, R.drawable.mexute, R.drawable.meeqvse, R.drawable.meshvide};
	int counter=0;
	private CountDownTimer timer;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game);
		result = (TextView) findViewById(R.id.result);
	  	result.setText("Steps made: " + counter);  
	  	pirveli = (ImageView) findViewById(R.id.pirveli);
	  	meore = (ImageView) findViewById(R.id.meore);
	  	mesame = (ImageView) findViewById(R.id.mesame);
	  	meotxe = (ImageView) findViewById(R.id.meotxe);
	  	mexute = (ImageView) findViewById(R.id.mexute);
	  	meeqvse = (ImageView) findViewById(R.id.meeqvse);
	  	meshvide= (ImageView) findViewById(R.id.meshvide);
	    time = (TextView) findViewById(R.id.time);
	    final MyCounter timer = new MyCounter(20000,1000);
	    timer.start();
	}

	public class MyCounter extends CountDownTimer{
		 
        public MyCounter(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }
 
        @Override
        public void onFinish() {
//            System.out.println("Timer Completed.");
            time.setText("");
            Toast.makeText(getBaseContext(), "Time is up! Your score is: "+ counter, 3000).show();
            Intent resultIntent = new Intent ("ge.gtug.RESULTS");
            startActivity(resultIntent);
        }
 
        @Override
        public void onTick(long millisUntilFinished) {
            time.setText((millisUntilFinished/1000)+"");
            System.out.println("Timer  : " + (millisUntilFinished/1000));
        }
    }
	
    
	public boolean onTouchEvent(MotionEvent event) {
		  if(event.getAction()==MotionEvent.ACTION_DOWN){
		     result.setText("Steps made: " + counter);
		     counter++;    
		     meshvide.setImageResource(drawableIds[counter % drawableIds.length]);
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
}
