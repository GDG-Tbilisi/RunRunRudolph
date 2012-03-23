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

public class RunRunRudolphActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		final PlayersManager p = new PlayersManager(this);
		Button start = (Button) findViewById(R.id.start);
		final EditText edName = (EditText) findViewById(R.id.edName);
	    createDB();
		System.out.println("Created DB");
		
		start.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				
				// TODO Auto-generated method stub
				String name  = edName.getText().toString().trim();
				if(name!=null || !name.equals("")){
					createPkayer(name);
				}
				Intent gameIntent = new Intent("ge.gtug.GAME");
				
				startActivity(gameIntent);
			}

			public void createPkayer(String name) {
				// TODO Auto-generated method stub
				p.createPlayer(name);
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