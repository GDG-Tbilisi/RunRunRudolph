package ge.gtug.helpers;

import ge.gtug.Results;
import ge.gtug.db.DBHelper;
import ge.gtug.entry.PlayersEntry;
import ge.gtug.service.PlayersService;
import ge.gtug.service.PlayersServiceImpl;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class PlayersManager extends DBHelper{
	private static final String TAG = "PlayersManager";
	PlayersService playerService;
	
	public PlayersManager(Context context) {
		super(context);
		if(playerService == null){
			playerService = new PlayersServiceImpl(context);
		}
	}

/*	public void createPlayer(String name) {
		SQLiteDatabase db = this.getReadableDatabase();
		String[] n = {name};
		Cursor c = db.rawQuery("select count(*) from players where name = ?",n);
		c.moveToFirst();
		if( c.getInt(0)==0){
		
		db.execSQL("INSERT INTO players Values (?,'0')",n);
		}
		c.close();
		db.close();
		
	}*/
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

	/*public void updateScores(int counter, String player) {
		int currentPoint = getCurrentPoint(player);
		Object[] c = {counter,player};
		if(currentPoint < counter){
			//update here
			SQLiteDatabase db = this.getReadableDatabase();
			db.execSQL("update players set point = ? where name = ?",c);
			db.close();
		}
	}*/

	public int getCurrentPoint(String player) {
		Log.i(TAG,"getCurrentPoint");
		/*SQLiteDatabase db = this.getReadableDatabase();
		String[] pl = {player};
		int curScore = 0;
		Cursor c = db.rawQuery("select point from players where name = ?",pl);
		if (c.getCount()!=0) {
			c.moveToFirst();
			curScore = c.getInt(0);
		}
		c.close();
		db.close();
		return curScore;*/
		return 0;
	}
}
