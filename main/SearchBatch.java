/*
 * Created on Jun 7, 2005
 *
 */
package main;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author dkirkby
 *
 */
public class SearchBatch {
	
	protected Random m_generator = new Random();
	private final BoardTracker m_tracker = new BoardTracker();
	private final SolutionWriter m_writer = new SolutionPublisher();
	private final SolutionOptimizer m_optimizer =
		new SolutionOptimizer();
	private static int s_longestSolutionEver = 0;
	private int m_longestSolutionInSeries;
	private int m_batchesWithoutProgress;
	private ArrayList m_bestBoardInBatch;
	
	public SearchBatch(){
		ArrayList coins = new ArrayList();
		String coinValues = getDefaultCoins();
		for (int i = 0; i < coinValues.length(); i++)
		{
			coins.add(new Integer(coinValues.charAt(i)-'0'));
		}
		
		// start by thoroughly shuffling the coins.
		shuffleBoard(coins, 1000);

		m_bestBoardInBatch = coins;
		m_longestSolutionInSeries = 1;
		m_batchesWithoutProgress = 0;
	}
	
	protected String getDefaultCoins(){
		return "0054545454323232321010101000";
	}
	
	public int getLongestSolutionInSeries(){
		return m_longestSolutionInSeries - 1;
	}
	
	public int getBatchesWithoutProgress(){
		return m_batchesWithoutProgress;
	}

	/**
	 * @param startingBoard - all boards in this batch are cloned from this
	 * 		and then slightly shuffled.
	 * @param bestBoardInBatch - returns the best board from the batch.
	 */
	public void runBatch() {
		// start with the best board from the previous batch.
		ArrayList startingBoard = m_bestBoardInBatch;
		
		int longestSolutionInBatch = 0;
		ArrayList bestBoardInBatch = null;
		m_batchesWithoutProgress++;
		for (int i=0; i < 100; i++)
		{
			ArrayList nextBoard = 
				(ArrayList)startingBoard.clone();
			
			int numSwaps = m_generator.nextInt(5) + 1;
			shuffleBoard(nextBoard, numSwaps);
			
			ArrayList solution = m_tracker.solveBoard(nextBoard);
			if (solution.size() > longestSolutionInBatch)
			{
				longestSolutionInBatch = solution.size();
				bestBoardInBatch = nextBoard;
				if (solution.size() > m_longestSolutionInSeries)
				{
					m_longestSolutionInSeries = solution.size();
					m_batchesWithoutProgress = 0;
					if (solution.size() > s_longestSolutionEver)
					{
						s_longestSolutionEver = solution.size();
						ArrayList prettySolution = 
							m_optimizer.optimize(nextBoard, solution);
						m_writer.writeSolution(nextBoard, prettySolution);
					}
				}
			}
		}
		
		if (bestBoardInBatch != null)
		{
			m_bestBoardInBatch = bestBoardInBatch;
		}
	}

	/**
	 * @param board
	 * @param swapCount
	 */
	private void shuffleBoard(ArrayList board, int swapCount) {
		for (int j=0; j < swapCount; j++)
		{
			swapCoins(board);
		}
	}

	/**
	 * @param board
	 */
	protected void swapCoins(ArrayList board) {
		int suit = m_generator.nextInt(4);
		int coin1 = m_generator.nextInt(6);
		int coin2 = (coin1 + m_generator.nextInt(5) + 1) % 6;
		
		int index1 = calculateCoinIndex(suit, coin1);
		int index2 = calculateCoinIndex(suit, coin2);
		Object temp = board.get(index1);
		board.set(index1, board.get(index2));
		board.set(index2, temp);
	}

	/**
	 * @param suit
	 * @param coin
	 * @return
	 */
	private int calculateCoinIndex(int suit, int coin) {
		return (suit+1)*2 + coin%2 + 8*(coin/2);
	}
}
