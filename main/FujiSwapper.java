/*
 * Created on Jun 14, 2005
 *
 */
package main;

import java.util.ArrayList;
import java.util.Date;

/** Searches for longest solution by slightly shuffling a board, picking
 * the best result and repeating with that board. Uses two batches in parallel:
 * a short-term one that gives up and completely shuffles when progress slows
 * down, plus a long-term batch that keeps plugging away on the same series.
 * If the short-term finds a better result that the long-term one, it becomes
 * the long-term one.
 * @author dkirkby
 *
 */
public class FujiSwapper {

	public static void main(String[] args) {
		
		ArrayList coins = new ArrayList();
		
		ArrayList bestBoardInShortBatch = null;
		ArrayList bestBoardInLongBatch = null;
		SearchBatch longBatch = new SearchBatch();
		SearchBatch shortBatch = new SearchBatch();
		while (true)
		{
			longBatch.runBatch();
			if (shortBatch.getBatchesWithoutProgress() > 20)
			{
				System.out.print(" New short-term batch. ");
				System.out.println(new Date());
				showProgress(longBatch, "long batch");
				showProgress(shortBatch, "short batch");
				shortBatch = new SearchBatch();
			}
			shortBatch.runBatch();
			if (shortBatch.getLongestSolutionInSeries() > 
					longBatch.getLongestSolutionInSeries())
			{
				System.out.print(" Switch long-term batch.");
				System.out.println(new Date());
				showProgress(longBatch, "long batch");
				showProgress(shortBatch, "short batch");
				longBatch = shortBatch;
				shortBatch = new SearchBatch();
			}
		}
	}
	
	private static void showProgress(SearchBatch batch, String batchName){
		System.out.printf(
				" %s: at length %s for %s batches. %s\n",
				new Object[] 
					{
						batchName,
						new Integer(batch.getLongestSolutionInSeries()),
						new Integer(batch.getBatchesWithoutProgress()),
						new Date()
					});
	}
}
