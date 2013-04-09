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
		return playerService.getCurrentPoint(player);
	}
}
