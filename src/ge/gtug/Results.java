package ge.gtug;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
public class Results extends Activity {
	Button shareBtn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.results);
		shareBtn = (Button)findViewById(R.id.shareButton);
		shareBtn.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				String zaza = null;
				String koba = null;
				// TODO Auto-generated method stub
				share( );		
			}
		});		
		}
	
	public Game s = new Game();		
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
			Intent restart = new Intent("ge.gtug.GAME");
			startActivity(restart);
		default:
			return super.onOptionsItemSelected(item);			
		}
	}

//	public void share(String subject,String text) {
//		 final Intent intent = new Intent(Intent.ACTION_SEND);
//
//		 intent.setType("text/plain");
//		 intent.putExtra(Intent.EXTRA_SUBJECT, subject);
//		 intent.putExtra(Intent.EXTRA_TEXT, text);
//
//		 startActivity(Intent.createChooser(intent, getString(R.string.share)));
//		}
//    }
	public void share() {
	    Intent i = null;
	    String msg ="My score is: ";

	    i = new Intent(Intent.ACTION_SEND);
	    i.setType("text/plain");

	    i.putExtra(Intent.EXTRA_TEXT, msg + s.counter+ " http://google.com");
	    i.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.share) + " \""
	            + "Share title"+ "\" : " + getResources().getString(R.string.app_name));
	    startActivity(Intent.createChooser(i, this.getString(R.string.share)));
	}}

