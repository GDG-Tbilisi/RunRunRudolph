package ge.gtug.helpers;

import ge.gtug.Results;
import ge.gtug.service.PlayersService;
import ge.gtug.service.PlayersServiceImpl;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class GameHelper {
	
	private static final String TAG = "GameHelper";
	
	PlayersService playerService;
	

	public GameHelper(Context context) {
		if (playerService != null) {
			playerService = new PlayersServiceImpl(context);
		}
	}

	
	public GameHelper(){}
	
	public void updateScores(int counter, String player) {
		
		Log.i(TAG,"updateScores");
		// TODO Auto-generated method stub
		/*
		 * int currentPoint = getCurrentPoint(player); Object[] c =
		 * {counter,player}; if(currentPoint < counter){ //update here
		 * SQLiteDatabase db = this.getReadableDatabase();
		 * db.execSQL("update players set point = ? where name = ?",c);
		 * db.close(); }
		 */
	}


}
