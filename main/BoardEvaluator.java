
package main;
/*
 * Created on Jul 12, 2004
 *
 */

/**
 * @author DKirkby
 *
 * created: Jul 12, 2004 
 */
public class BoardEvaluator implements IEvaluator
{

	/* (non-Javadoc)
	 * @see IEvaluator#isGoal(IState)
	 */
	public boolean isGoal(IState state)
	{
		BoardState board = (BoardState)state;
		for (int i = 0; i < 4; i++)
		{
			if (board.getPriestPosition(i) < 12 || 15 < board.getPriestPosition(i))
			{
				return false;
			}
		}
		return true;
	}

}
