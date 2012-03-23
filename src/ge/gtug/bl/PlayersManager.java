package ge.gtug.bl;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import ge.gtug.db.DBHelper;

public class PlayersManager extends DBHelper{

	public PlayersManager(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	private SQLiteDatabase myDataBase;

	public void createPlayer(String name) {
		// TODO Auto-generated method stub
		System.out.println("name  is " + name);
	}

}
