package ge.gtug;

import java.io.IOException;

import ge.gtug.bl.PlayersManager;
import ge.gtug.db.DBHelper;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RunRunRudolphActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		final PlayersManager p = new PlayersManager(this);
		Button start = (Button) findViewById(R.id.start);
		Button go = (Button) findViewById(R.id.go);
		final TextView txtNewPlayer = (TextView) findViewById(R.id.txtNewPlayer);
		final EditText edName = (EditText) findViewById(R.id.edName);
	    createDB();
	    
	  
		start.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				
				// TODO Auto-generated method stub
				String name  = edName.getText().toString().trim();
				if(name!=null || !name.equals("")){	//checkPlayer(name);
					createPlayer(name);
				}
				Intent gameIntent = new Intent("ge.gtug.GAME");
				gameIntent.putExtra("name", edName.getText().toString());
				startActivity(gameIntent);
			}

			

			public void createPlayer(String name) {
				// TODO Auto-generated method stub
				p.createPlayer(name);
			}
		});
		
		  go.setOnClickListener(new View.OnClickListener() {
				
				public void onClick(View v) {
					// TODO Auto-generated method stub
					String name  = edName.getText().toString().trim();
					if(name!=null || !name.equals("")){	//checkPlayer(name);
						int score = checkPlayer(name);
						if(score>0){
							txtNewPlayer.setText("Hello " + name + " your best score is " + score + "\n Click Start and try again");
						}else{
							txtNewPlayer.setText("Hey " + name + "you are a new Player \n best score on this device is " + score + " \n click start and Run!");
						}
					}
				}

				private int checkPlayer(String name) {
					// TODO Auto-generated method stub
					int curPoint = p.getCurrentPoint(name);
				//	System.out.println("curPoint is : "  + curPoint);
					return curPoint;
				}
			});
		
	}

	private void createDB() {
		// TODO Auto-generated method stub
		DBHelper db = new DBHelper(this);
		try {
			db.createDataBase();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
}