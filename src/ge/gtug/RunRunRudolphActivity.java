package ge.gtug;

import java.io.IOException;

import ge.gtug.bl.PlayersManager;
import ge.gtug.db.DBHelper;
import android.app.Activity;
import android.content.Intent;
import android.opengl.Visibility;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

public class RunRunRudolphActivity extends Activity {
	/** Called when the activity is first created. */
	ToggleButton toggleMusic;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		final PlayersManager p = new PlayersManager(this);
		final Button start = (Button) findViewById(R.id.start);
		Button go = (Button) findViewById(R.id.go);
		final TextView txtNewPlayer = (TextView) findViewById(R.id.txtNewPlayer);
		final EditText edName = (EditText) findViewById(R.id.edName);
		createDB();
		toggleMusic = (ToggleButton) findViewById(R.id.toggleMusic);

		start.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				// TODO Auto-generated method stub
				String name = edName.getText().toString().trim();
				if (name != null || !name.equals("")) { // checkPlayer(name);
					createPlayer(name);
				}
				Intent gameIntent = new Intent("ge.gtug.GAME");
				gameIntent.putExtra("name", edName.getText().toString());
				gameIntent.putExtra("sound", toggleMusic.getText());
				startActivity(gameIntent);
			}

			public void createPlayer(String name) {
				// TODO Auto-generated method stub
				p.createPlayer(name);
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
								+ " ] your best score is [ " + score
								+ " ] \n Click Start and try again");
					} else {
						start.setVisibility(View.VISIBLE);
						txtNewPlayer
								.setText("Hey [ "
										+ name
										+ " ] you are a new Player \n best score on this device is [ "
										+ score + " ] \n click Run and start!");
					}
				} else {
					txtNewPlayer.setText(" Please Type your Name ");
				}
			}

			private int checkPlayer(String name) {
				int curPoint = p.getCurrentPoint(name);
				return curPoint;
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