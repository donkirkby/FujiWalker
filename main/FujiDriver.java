
package main;
/*
 * Created on 10-Feb-2005
 */

/**
 * @author DKirkby
 * This is the base class for all the classes that drive
 * the Fuji analysis tasks.
 */
public class FujiDriver {

	/**
	 * @param last
	 */
	protected static void runUniqueBoards(IBoardListener last) {
		BoardCounter uniqueCounter = new BoardCounter(last);
		uniqueCounter.setDescription("unique boards: ");
		BoardFilter filter = new BoardFilter(uniqueCounter);
		BoardCounter allCounter = new BoardCounter(filter);
		allCounter.setDescription("All boards: ");
		allCounter.setNextCounter(uniqueCounter); 
		allCounter.enableOutput(true);
		allCounter.enableTimestamp(true);
		
		StatusTracker statusTracker =
			StatusTracker.getInstance();
		last = allCounter;
		BoardBuilder first = null;
		for (int i = 0; i < 4; i++)
		{
			first = new BoardBuilder(
					last, 
					statusTracker.getCoinsToSkip(3-i));
			first.enableExtraSkip(i==0);
			last = first;
		}
		//set both counters based on results so far:
		uniqueCounter.setBoardCount(
				statusTracker.getUniqueBoardCount());
		allCounter.setBoardCount(
				statusTracker.getAllBoardCount());
		first.generate();
		System.out.println(allCounter.getSummary());
	}
}
