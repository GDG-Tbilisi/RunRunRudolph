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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class Results extends Activity {
	DBHelper myDbHelper;
	Button shareBtn;
	ListView list;
	EditText et;
	private List playersList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.results);
		shareBtn = (Button) findViewById(R.id.shareButton);
		// et = (EditText) findViewById(R.id.resultBox);
		loadStatistics();
		shareBtn.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				String zaza = null;
				String koba = null;
				// TODO Auto-generated method stub
				share();

			}

		});

	}

	private void loadStatistics() {
		// TODO Auto-generated method stub
		ArrayList<PlayersEntry> result = new ArrayList<PlayersEntry>();
		PlayersManager players = new PlayersManager(this);
		list = (ListView) findViewById(R.id.list);
		myDbHelper = new DBHelper(this);
		myDbHelper.openDataBase();
		result = players.getStatistics();
		myDbHelper.close();
		playersList = new ArrayList<PlayersEntry>();
		playersList = getList(result);

		list.setAdapter(new ArrayAdapter<PlayersEntry>(Results.this,
				android.R.layout.simple_list_item_1, playersList));

	}

	private List<PlayersEntry> getList(ArrayList<PlayersEntry> result) {
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

			Intent restart = new Intent("ge.gtug.GAME");
			startActivity(restart);
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	// public void share(String subject,String text) {
	// final Intent intent = new Intent(Intent.ACTION_SEND);
	//
	// intent.setType("text/plain");
	// intent.putExtra(Intent.EXTRA_SUBJECT, subject);
	// intent.putExtra(Intent.EXTRA_TEXT, text);
	//
	// startActivity(Intent.createChooser(intent, getString(R.string.share)));
	// }
	// }
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
