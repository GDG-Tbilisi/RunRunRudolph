package ge.gtug.helpers;

import ge.gtug.entry.PlayersEntry;
import ge.gtug.service.PlayersService;
import ge.gtug.service.PlayersServiceImpl;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;

public class ResultsHelper {
	private static final String TAG = "PlayersManager";
	PlayersService playerService;
	
	public ResultsHelper(Context context) {
		if(playerService == null){
			playerService = new PlayersServiceImpl(context);
		}
	}

	public ArrayList<PlayersEntry> getStatistics(){
		Log.i(TAG,"getStatistics");
		return playerService.getStatistics();
	}

}
