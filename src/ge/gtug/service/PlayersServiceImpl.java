package ge.gtug.service;

import ge.gtug.dao.PlayersDao;
import ge.gtug.dao.PlayersDaoImpl;
import ge.gtug.entry.PlayersEntry;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

public class PlayersServiceImpl implements PlayersService{
	PlayersDao playersDao;
	
	public PlayersServiceImpl(Context context) {
		playersDao = new PlayersDaoImpl(context);
	}

	@Override
	public ArrayList<PlayersEntry> getStatistics() {
		Log.w(PlayersServiceImpl.class.getName(), "Service call");

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

	@Override
	public int getCurrentPoint(String player) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean createPlayer(String name) {
		boolean operationStatus = playersDao.createPlayer(name);
		return operationStatus;
	}
	
	@Override
	public boolean updateScores(int counter, String player) {
		// TODO Auto-generated method stub
		return false;
	}

	

}
