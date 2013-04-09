package ge.gtug.dao;

import android.database.Cursor;

public interface PlayersDao {
	/**
	 * Select top players list with scores   
	 * @return top scores
	 */
	public Cursor getStatistics();
	/**
	 * 
	 * @param player
	 * @return score of player
	 */
	public int getCurrentPoint(String player);
	
	/**
	 * create new player 
	 * @param name
	 * @return operation success status  if created true 
	 */
	public boolean createPlayer(String name);
	
	/**
	 * 
	 * @param counter
	 * @param player
	 * @return operation success status if updated true 
	 */
	public boolean updateScores(int counter, String player);
}
