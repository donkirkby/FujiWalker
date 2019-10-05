package main;
import java.util.ArrayList;
import java.util.HashSet;


/*
 * Created on Jul 8, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

/**
 * @author dkirkby
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class BoardWalker
{
	private IStepper m_stepper;
	private IEvaluator m_evaluator;
	private IState m_state;
	
	public BoardWalker(
		IState state, 
		IStepper stepper, 
		IEvaluator evaluator)
	{
		m_state = state;
		m_stepper = stepper;
		m_evaluator = evaluator;
	}

	
	public ArrayList solve()
	{
		ArrayList solution = new ArrayList();
		ArrayList unvisitedStates = new ArrayList();
		HashSet allStates = new HashSet();
		
		unvisitedStates.add(m_state);
		allStates.add(m_state);
		boolean isGoalFound = false;
		
		while (!isGoalFound && !unvisitedStates.isEmpty())
		{
			IState state = (IState)unvisitedStates.remove(0);
			isGoalFound = m_evaluator.isGoal(state); 
			if (isGoalFound)
			{
				while (state != null)
				{
					solution.add(0, state);
					state = state.getPrevious(); 
				}
			}
			else
			{
				ArrayList newStates = m_stepper.getPossibleStates(state);
				for (int i = 0; i < newStates.size(); i++)
				{
					Object newState = newStates.get(i);
					if (!allStates.contains(newState))
					{
						allStates.add(newState);
						unvisitedStates.add(newState);
					}
				}
			}
		}
		return solution;
	}
}
