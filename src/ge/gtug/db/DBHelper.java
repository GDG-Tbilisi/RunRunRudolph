	package ge.gtug.db;

import java.io.File;
import java.io.IOException;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
	// The Android's default system path of your application database.
	private static String DB_PATH = "/data/data/ge.gtug/databases/";
	private static String DB_NAME = "run.db";
	private static final int DB_VERSION = 1;
	private static final String TABLE_NAME = "players";
	private String CREATE_Table = "CREATE TABLE players (name TEXT, point TEXT);";
	public static final String NAME = "name";
	public static final String POINT = "point";

	private SQLiteDatabase myDataBase;
	private final Context context;
	
	
		
	

	public DBHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
		this.context = context;
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		myDataBase = db;
		myDataBase.execSQL(CREATE_Table);
		
		// System.out.println("onCreate Called");
		// System.out.println("View Created");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		context.deleteDatabase(DB_NAME);
		System.out.println("DB DELETED");
		db.execSQL("DROP TABLE IF EXISTS " + DB_NAME);
		onCreate(db);
	}

	public void createDataBase() throws IOException {

		boolean dbExist = checkDataBase();

		if (dbExist) {
			// do nothing - database already exist
		} else {
			this.getReadableDatabase();
		}

	}

	private boolean checkDataBase() {

		File dbFile = new File(DB_PATH + DB_NAME);
		return dbFile.exists();
	}


	public void openDataBase() throws SQLException {
		// Open the database
		String myPath = DB_PATH + DB_NAME;
		myDataBase = SQLiteDatabase.openDatabase(myPath, null,
				SQLiteDatabase.OPEN_READONLY);
		System.out.println("DB is Opened");
	}

	@Override
	public synchronized void close() {
		if (myDataBase != null) {
			myDataBase.close();
		}
		System.out.println("DB is Closed");

		super.close();
	}
}