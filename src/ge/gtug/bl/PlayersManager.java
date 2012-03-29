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
		Cursor c = db.rawQuery("select count(*) from players where name = '"+name+"'",null);
		c.moveToFirst();
		if( c.getInt(0)==0){
		
		db.execSQL("INSERT INTO players Values ('"+name+"','0');");
		}
		db.close();
		
	}
	public ArrayList<PlayersEntry> getStatistics(){
		
		SQLiteDatabase db = this.getReadableDatabase();
		
		ArrayList<PlayersEntry> result = new ArrayList<PlayersEntry>();
		
		String[] columns = new String[] { "name","point" };
	
		Cursor c = db.query("players", columns,
				null,
				null, null, null, null);
		System.out.println("Count " + c.getCount());
		for (c.moveToFirst(); !c.isLast(); c.moveToNext()) {
			String name = c.getString(0).toString();
			String point = c.getString(1).toString();
			result.add(new PlayersEntry(name, point));
		}
			c = null;
			return result;
	}

	public void updateScores(int counter, String player) {
		// TODO Auto-generated method stub
		int currentPoint = getCurrentPoint(player);
		if(currentPoint < counter){
			//update here
			SQLiteDatabase db = this.getReadableDatabase();
			db.execSQL("update players set point = "+counter+" where name = '"+player+"'");
			db.close();
		}
		
		
		
	}

	private int getCurrentPoint(String player) {
		// TODO Auto-generated method stub
		SQLiteDatabase db = this.getReadableDatabase();
		String[] columns = new String[] {"point" };
		
	//	Cursor c = db.execSQL("select point from players where name = '"+player+"'");
		
		Cursor c = db.rawQuery("select point from players where name = '"+player+"'",null);
		c.moveToFirst();
		int curScore = c.getInt(0);
		db.close();
		
		return curScore;
	}
}
