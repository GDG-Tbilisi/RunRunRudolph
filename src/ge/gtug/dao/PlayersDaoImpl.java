package ge.gtug.dao;

import ge.gtug.db.DBHelper;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class PlayersDaoImpl extends DBHelper implements PlayersDao {

	public PlayersDaoImpl(Context context) {
		super(context);
	}

	@Override
	public Cursor getStatistics() {
		SQLiteDatabase db = this.getReadableDatabase();

		String[] columns = { "name", "point" };

		Cursor c = db.query("players", columns, null, null, null, null, null);

		if (c != null) {
			c.moveToFirst();
		}

		db.close();
		return c;
	}

	@Override
	public int getCurrentPoint(String player) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean createPlayer(String name) {
		try {
			SQLiteDatabase db = this.getReadableDatabase();
			String[] n = { name };
			Cursor c = db.rawQuery(
					"select count(*) from players where name = ?", n);
			c.moveToFirst();
			if (c.getInt(0) == 0) {
				db.execSQL("INSERT INTO players Values (?,'0')", n);

				c.close();
				db.close();
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateScores(int counter, String player) {
		// TODO Auto-generated method stub
		return false;
	}

}
