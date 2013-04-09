package ge.gtug.helpers;

import ge.gtug.service.PlayersService;
import ge.gtug.service.PlayersServiceImpl;
import android.content.Context;
import android.util.Log;

public class StartHelper {
	private static final String TAG = "StartHelper";

	PlayersService playerService;

	public StartHelper(Context context) {
		if (playerService == null) {
			playerService = new PlayersServiceImpl(context);
		}
	}

	public void createPlayer(String name) {
		Log.i(TAG, "createPlayer " + name);
		playerService.createPlayer(name);
	}

	public int getCurrentPoint(String player) {
		Log.i(TAG, "getCurrentPoint");
		/*
		 * SQLiteDatabase db = this.getReadableDatabase(); String[] pl =
		 * {player}; int curScore = 0; Cursor c =
		 * db.rawQuery("select point from players where name = ?",pl); if
		 * (c.getCount()!=0) { c.moveToFirst(); curScore = c.getInt(0); }
		 * c.close(); db.close(); return curScore;
		 */
		return 0;
	}
}
