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
	public Cursor getCurrentPoint(String player) {

		SQLiteDatabase db = this.getReadableDatabase();
		String[] pl = { player };
		Cursor cursor = db.rawQuery("select point from players where name = ?",
				pl);
		if (cursor.getCount() != 0) {
			cursor.moveToFirst();
		}
		db.close();

		return cursor;
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
	public void updateScores(int counter, String player) {
		Object[] c = { counter, player };
		SQLiteDatabase db = this.getReadableDatabase();
		db.execSQL("update players set point = ? where name = ?", c);
		db.close();
	}

}
