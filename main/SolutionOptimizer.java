/*
 * Created on Jun 13, 2005
 *
 */
package main;

import java.util.ArrayList;

/**
 * @author dkirkby
 *
 */
public class SolutionOptimizer {
	private ArrayList m_best;
	private int m_shortestLength;
	private int m_fewestSwitches;
	private IStepper m_stepper;
	
	public SolutionOptimizer() {
		SideStepper stepper = new SideStepper();
		stepper.setNextStepper(new ForwardStepper());
		m_stepper = stepper;
	}
	
	/* (non-Javadoc)
	 * @see main.BoardWalker.ISolutionEvaluator#reset()
	 */
	public void reset() {
		m_best = new ArrayList();
		m_shortestLength = 0;
		m_fewestSwitches = 0;
	}

	/* (non-Javadoc)
	 * @see main.BoardWalker.ISolutionEvaluator#evaluate(java.util.ArrayList)
	 */
	public boolean evaluate(ArrayList solution) {
		if (m_shortestLength == 0)
		{
			m_shortestLength = solution.size();
			m_best = solution;
			m_fewestSwitches = countSwitches(solution);
			return true;
		}
		else
		{
			boolean isFinished = solution.size() != m_shortestLength;
			if (!isFinished)
			{
				int switchCount = countSwitches(solution);
				if (switchCount < m_fewestSwitches)
				{
					m_best = solution;
					m_fewestSwitches = switchCount;
				}
			}
			return !isFinished;
		}
	}
	
	private int countSwitches(ArrayList solution)
	{
		int switchCount = 0;
		int prevPriestNum = -1;
		for (int i=1; i < solution.size(); i++)
		{
			BoardState curr = (BoardState)solution.get(i);
			BoardState prev = (BoardState)curr.getPrevious();
			for (
					int priestNum=0; 
					priestNum < BoardState.NUM_PRIESTS; 
					priestNum++)
			{
				int currPos = curr.getPriestPosition(priestNum);
				int prevPos = prev.getPriestPosition(priestNum);
				if (currPos != prevPos)
				{
					// found moving priest for this move.
					if (priestNum != prevPriestNum)
					{
						switchCount++;
						prevPriestNum = priestNum;
					}
				}
			}
		}
		return switchCount;
	}
	

	/* (non-Javadoc)
	 * @see main.BoardWalker.ISolutionEvaluator#getBestSolution()
	 */
	public ArrayList getBestSolution() {
		return m_best;
	}

	public ArrayList optimize(ArrayList coins, ArrayList solution)
	{
		reset();
		MoveGrid moveGrid = new MoveGrid(solution);
		BoardState state = (BoardState)solution.get(0);
		generateSolutions(state, moveGrid);
		return getBestSolution();
	}
	
	private void generateSolutions(BoardState state, MoveGrid moveGrid){
		if (moveGrid.isEmpty())
		{
			IState stepState = state;
			ArrayList solution = new ArrayList();
			while (stepState != null)
			{
				solution.add(0, stepState);
				stepState = stepState.getPrevious(); 
			}
			evaluate(solution);
		}
		else
		{
			ArrayList legalStates = m_stepper.getPossibleStates(state);
			for (
					int priestNum = 0; 
					priestNum < BoardState.NUM_PRIESTS; 
					priestNum++)
			{
				if (moveGrid.hasMove(priestNum))
				{
					int newPosition = moveGrid.getMove(priestNum);
					BoardState newState = 
						state.setPriestPosition(priestNum, newPosition);
					if (legalStates.contains(newState))
					{
						MoveGrid newMoveGrid = (MoveGrid)moveGrid.clone();
						newMoveGrid.popMove(priestNum);
						generateSolutions(newState, newMoveGrid);
					}
				}
			}
		}
	}
}
