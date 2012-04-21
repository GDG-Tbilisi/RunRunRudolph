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
	private static DBHelper myDbHelper = null;
	Button shareBtn;
	ListView list;
	EditText et;
	private List playersList;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.results);
		shareBtn = (Button)findViewById(R.id.shareButton);
		et = (EditText) findViewById(R.id.resultBox);
		loadStatistics();
		shareBtn.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				String zaza = null;
				String koba = null;
				// TODO Auto-generated method stub
				share( );		
				
			}
			
			
		});	
		
		}
	
	private void loadStatistics() {
		// TODO Auto-generated method stub
		ArrayList<PlayersEntry> result = new ArrayList();
		PlayersManager players= new PlayersManager(this);
		myDbHelper = new DBHelper(this);
		list = (ListView) findViewById(R.id.list);
		myDbHelper.openDataBase();
		result = players.getStatistics();
		myDbHelper.close();
		System.out.println("Size of results " + result.size());
		//System.out.println("Size of results get " + result.get(1).toString());
		String stat = "";
		//playersList = getPlayers(result);
		for(PlayersEntry entry : result){
		 	stat += entry.getName() + "-" + entry.getPoint()+"\n";
		//	playersList.add(entry.getName().toString()+ " - " +entry.getPoint().toString());
				}
		//System.out.println(" Size of results " + playersList.size());
	//	list.setAdapter(new ArrayAdapter<PlayersEntry>(Results.this, android.R.layout.simple_list_item_1,playersList));
		et.setText(stat);
	}

	private List getPlayers(ArrayList<PlayersEntry> result) {
		for(PlayersEntry entry : result){
			
		 	//System.out.println(entry.getName() + "-" + entry.getPoint());
			playersList.add(entry.getName().toString()+ " - " +entry.getPoint().toString());
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

