package ge.gtug.bl;

import java.util.ArrayList;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import ge.gtug.db.DBHelper;
import ge.gtug.entry.PlayersEntry;

public class PlayersManager extends DBHelper{

	public PlayersManager(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

		//DBHelper db = new DBHelper(this);
	public void createPlayer(String name) {
		// TODO Auto-generated method stub
		SQLiteDatabase db = this.getReadableDatabase();
		String[] n = {name};
		Cursor c = db.rawQuery("select count(*) from players where name = ?",n);
		c.moveToFirst();
		if( c.getInt(0)==0){
		
		db.execSQL("INSERT INTO players Values (?,'0')",n);
		}
		c.close();
		db.close();
		
	}
	public ArrayList<PlayersEntry> getStatistics(){
		
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
		db.close();
		return result;
	}

	public void updateScores(int counter, String player) {
		// TODO Auto-generated method stub
		int currentPoint = getCurrentPoint(player);
		Object[] c = {counter,player};
		if(currentPoint < counter){
			//update here
			SQLiteDatabase db = this.getReadableDatabase();
			db.execSQL("update players set point = ? where name = ?",c);
			db.close();
		}
	}

	public int getCurrentPoint(String player) {
		// TODO Auto-generated method stub
		SQLiteDatabase db = this.getReadableDatabase();
		String[] columns = {"point" };
		String[] pl = {player};
		int curScore = 0;
		Cursor c = db.rawQuery("select point from players where name = ?",pl);
		if (c.getCount()!=0) {
			c.moveToFirst();
			curScore = c.getInt(0);
		}
		c.close();
		db.close();
		return curScore;
	}
}
