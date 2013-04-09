package ge.gtug.service;

import ge.gtug.Results;
import ge.gtug.dao.PlayersDao;
import ge.gtug.dao.PlayersDaoImpl;
import ge.gtug.entry.PlayersEntry;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

public class PlayersServiceImpl implements PlayersService{
	Context c ;
	public PlayersServiceImpl(Context context) {
		this.c = context;
	}

	@Override
	public ArrayList<PlayersEntry> getStatistics() {
		Log.w(PlayersServiceImpl.class.getName(), "Service call");
		System.out.println("Service Call");
		PlayersDao playersDao = new PlayersDaoImpl(c);
		ArrayList<PlayersEntry> result = new ArrayList<PlayersEntry>();
		Cursor c = playersDao.getStatistics(); 
		for(int i=0;i<c.getCount();i++){
			String name = c.getString(0).toString();
			String point = c.getString(1).toString();
			result.add(new PlayersEntry(name, point));
			c.moveToNext();
		}
		c.close();
		return result;
	}

}
