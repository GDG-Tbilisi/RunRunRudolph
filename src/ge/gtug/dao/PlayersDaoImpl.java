package ge.gtug.dao;

import ge.gtug.db.DBHelper;
import ge.gtug.entry.PlayersEntry;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class PlayersDaoImpl extends DBHelper implements PlayersDao {
	
	public PlayersDaoImpl(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Cursor getStatistics() {
		SQLiteDatabase db = this.getReadableDatabase();
		
		String[] columns = { "name","point" };
	
		Cursor c = db.query("players", columns,
				null,
				null, null, null, null);
		
		if(c!=null){
			c.moveToFirst();
		}

		db.close();
		return c;
	}

}
