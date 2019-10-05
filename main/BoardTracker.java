package main;

import java.util.ArrayList;



/*
 * Created on Jul 13, 2004
 *
 */

/**
 * @author DKirkby
 *
 * created: Jul 13, 2004 
 */
public class BoardTracker implements IBoardListener
{
	private SolutionHistogram m_histogram = new SolutionHistogram();
	public SolutionWriter m_writer = new SolutionWriter();
	
	/* (non-Javadoc)
	 * @see IBoardListener#receiveBoard(java.util.ArrayList)
	 */
	public void receiveBoard(ArrayList coins)
	{
		ArrayList solution = solveBoard(coins);
		m_histogram.receiveSolution(solution);
		
		StatusTracker statusTracker = 
			StatusTracker.getInstance();
		if (solution.size() >= statusTracker.getMaxLength())
		{
			statusTracker.setMaxLength(solution.size());
			m_writer.writeSolution(coins, solution);
		}
	}


	/**
	 * Solves the board and returns an ArrayList of 
	 * BoardStates representing the solution.
	 * @param coins
	 * @return
	 */
	public ArrayList solveBoard(ArrayList coins) {
		BoardState state = new BoardState(coins);
		SideStepper stepper = new SideStepper();
		stepper.setNextStepper(new ForwardStepper());

		BoardWalker walker = new BoardWalker(
			state,
			stepper, 
			new BoardEvaluator());
		
		ArrayList solution = walker.solve();
		return solution;
	}

	public void writeStatus(BoardCounter mainCounter, ArrayList coins) {
	}
	public SolutionHistogram getHistogram()
	{
		return m_histogram;
	}
}
