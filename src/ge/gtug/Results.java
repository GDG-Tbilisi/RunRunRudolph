package ge.gtug;

import ge.gtug.bl.PlayersManager;
import ge.gtug.db.DBHelper;
import ge.gtug.entry.PlayersEntry;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

public class Results extends Activity {
	DBHelper myDbHelper;

	Button shareBtn;
	ListView list;
	EditText et;
	private List playersList;
	Button btnRestart;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.results);
		shareBtn = (Button) findViewById(R.id.shareButton);
		btnRestart = (Button) findViewById(R.id.btnRestart);

		loadStatistics();

		btnRestart.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
			    finish();
			  }
		});

		shareBtn.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				String zaza = null;
				String koba = null;

				Intent sharingIntent = new Intent(Intent.ACTION_SEND);
				sharingIntent.setType("text/plain");
				sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT,
						"I've made %s steps in 10 seconds. ??..??");
				sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,
						"runRunRudolph!");
				startActivity(Intent.createChooser(sharingIntent, null));

			}

		});

	}

	private void loadStatistics() {
		// TODO Auto-generated method stub
		ArrayList<PlayersEntry> result = new ArrayList<PlayersEntry>();
		PlayersManager players = new PlayersManager(this);
		list = (ListView) findViewById(R.id.list);
		// myDbHelper = new DBHelper(this);
		// myDbHelper.openDataBase();
		result = players.getStatistics();
		// myDbHelper.close();
		playersList = new ArrayList<PlayersEntry>();
		playersList = prepareList(result);

		list.setAdapter(new ArrayAdapter<PlayersEntry>(Results.this,
				android.R.layout.simple_list_item_1, playersList));

	}

	private List<PlayersEntry> prepareList(ArrayList<PlayersEntry> result) {
		// TODO Auto-generated method stub
		for (PlayersEntry entry : result) {
			playersList.add(entry.getName() + " - " + entry.getPoint());
		}
		return playersList;
	}

	private List getPlayers(ArrayList<PlayersEntry> result) {
		for (PlayersEntry entry : result) {
			playersList.add(entry.getName().toString() + " - "
					+ entry.getPoint().toString());
		}
		return playersList;
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

			// Intent restart = new Intent("ge.gtug.GAME");
			// startActivity(restart);
			Intent intent = new Intent(Intent.ACTION_MAIN);
			intent.addCategory(Intent.CATEGORY_HOME);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(intent);
		default:

			return super.onOptionsItemSelected(item);
		}
	}

	public void share() {
		Intent i = null;
		String msg = "My score is: ";

		i = new Intent(Intent.ACTION_SEND);
		i.setType("text/plain");

		i.putExtra(Intent.EXTRA_TEXT, msg + s.counter + " http://google.com");
		i.putExtra(
				Intent.EXTRA_SUBJECT,
				getResources().getString(R.string.share) + " \""
						+ "Share title" + "\" : "
						+ getResources().getString(R.string.app_name));
		startActivity(Intent.createChooser(i, this.getString(R.string.share)));
	}
}
