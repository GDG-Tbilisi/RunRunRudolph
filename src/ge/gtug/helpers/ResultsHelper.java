package ge.gtug.helpers;

import ge.gtug.db.DBHelper;
import ge.gtug.entry.PlayersEntry;
import ge.gtug.service.PlayersService;
import ge.gtug.service.PlayersServiceImpl;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;

public class ResultsHelper {
	private static final String TAG = "PlayersManager";
	PlayersService playerService;
	
	public ResultsHelper(Context context) {
		if(playerService == null){
			playerService = new PlayersServiceImpl(context);
		}
	}

	public ArrayList<PlayersEntry> getStatistics(){
		Log.i(TAG,"getStatistics");
		
		/*
		SQLiteDatabase db = this.getReadableDatabase();
		ArrayList<PlayersEntry> result = new ArrayList<PlayersEntry>();
		String[] columns = { "name","point" };
	
		Cursor c = db.query("players", columns,
				null,
				null, null, null, null);
		
		c.moveToFirst();
		
		for(int i=0;i<c.getCount();i++){
			String name = c.getString(0).toString();
			String point = c.getString(1).toString();
			result.add(new PlayersEntry(name, point));
			c.moveToNext();
		}
		c.close();
		db.close();*/
		
		
		return playerService.getStatistics();
	}

}
