package ge.gtug.helpers;

import ge.gtug.service.PlayersService;
import ge.gtug.service.PlayersServiceImpl;
import android.content.Context;
import android.util.Log;

public class GameHelper {
	
	private static final String TAG = "GameHelper";
	
	PlayersService playerService;
	

	public GameHelper(Context context) {
		if (playerService == null) {
			playerService = new PlayersServiceImpl(context);
		}
	}

	
	
	public void updateScores(int counter, String player) {
		
		Log.i(TAG,"updateScores");
		playerService.updateScores(counter, player);

	}


}
