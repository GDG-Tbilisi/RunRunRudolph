package ge.gtug;

import ge.gtug.db.DBHelper;
import ge.gtug.helpers.StartHelper;

import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

public class StartActivity extends Activity {
	/** Called when the activity is first created. */
	ToggleButton toggleMusic;
	StartHelper startHelper;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		startHelper = new StartHelper(this);
		final Button start = (Button) findViewById(R.id.start);
		Button go = (Button) findViewById(R.id.go);
		final TextView txtNewPlayer = (TextView) findViewById(R.id.txtNewPlayer);
		final EditText edName = (EditText) findViewById(R.id.edName);
		createDB();
		toggleMusic = (ToggleButton) findViewById(R.id.toggleMusic);

		start.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				String name = edName.getText().toString().trim();
				if (name != null || !name.equals("")) { 
					// checkPlayer(name);
					createPlayer(name);
				}
				Intent gameIntent = new Intent("ge.gtug.GAME");
				gameIntent.putExtra("name", edName.getText().toString());
				gameIntent.putExtra("sound", toggleMusic.getText());
				startActivity(gameIntent);
			}

			public void createPlayer(String name) {
				startHelper.createPlayer(name);
			}
		});

		go.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				String name = edName.getText().toString().trim();
				if (name != null && !name.equals("")) { // checkPlayer(name);
					int score = checkPlayer(name);
					if (score > 0) {
						start.setVisibility(View.VISIBLE);
						txtNewPlayer.setText("Hello [ " + name
								+ " ] your best score is " + score);
					} else {
						start.setVisibility(View.VISIBLE);
						txtNewPlayer
								.setText("Hey [ "
										+ name
										+ " ] you are a new Player \n click Play  and start!");
					}
				} else {
					txtNewPlayer.setText(" Please Type your Name ");
				}
			}

			private int checkPlayer(String name) {
				return startHelper.getCurrentPoint(name);
			}
		});
	}

	private void createDB() {
		DBHelper db = new DBHelper(this);
		try {
			db.createDataBase();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}