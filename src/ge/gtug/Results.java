package ge.gtug;

import ge.gtug.entry.PlayersEntry;
import ge.gtug.helpers.ResultsHelper;
import ge.gtug.service.PlayersService;
import ge.gtug.service.PlayersServiceImpl;

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
	Button shareBtn;
	ListView list;
	EditText et;
	private List playersList;
	Button btnRestart;
	ResultsHelper players;
	@Override
	protected void onCreate(Bundle savedInstanceState) {

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
				
				Bundle bundle = getIntent().getExtras();
				
				String player = bundle.getString("name");
				int score = bundle.getInt("score");
				
				Intent sharingIntent = new Intent(Intent.ACTION_SEND);
				sharingIntent.setType("text/plain");
				sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT,
						"Android Game RunRunRudolph \n Player "+ player +" made "+score+" steps in 10 seconds.");
				sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,
						"runRunRudolph!");
				sharingIntent.putExtra(android.content.Intent.EXTRA_SHORTCUT_INTENT, "goo.gl");
				startActivity(Intent.createChooser(sharingIntent, null));
			}

		});

	}

	private void loadStatistics() {
		ArrayList<PlayersEntry> result;
		players = new ResultsHelper(this);
		
		list = (ListView) findViewById(R.id.list);
		result = players.getStatistics();
		playersList = new ArrayList<PlayersEntry>();
		playersList = prepareList(result);

		list.setAdapter(new ArrayAdapter<PlayersEntry>(Results.this,
				android.R.layout.simple_list_item_1, playersList));

	}

	private List<PlayersEntry> prepareList(ArrayList<PlayersEntry> result) {
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


	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		MenuInflater menInf = getMenuInflater();
		menInf.inflate(R.menu.menu, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.about:
			startActivity(new Intent(this, About.class));
			return true;
		case R.id.restart:
			Intent intent = new Intent(Intent.ACTION_MAIN);
			intent.addCategory(Intent.CATEGORY_HOME);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(intent);
		default:
			return super.onOptionsItemSelected(item);
		}
	}
}
