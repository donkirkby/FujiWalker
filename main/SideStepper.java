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
public class SideStepper implements IStepper
{
	private IStepper m_nextStepper;
	
	public void setNextStepper(IStepper nextStepper)
	{
		m_nextStepper = nextStepper;
	}

	/* (non-Javadoc)
	 * @see IStepper#getPossibleStates(IState)
	 */
	public ArrayList getPossibleStates(IState current)
	{
		BoardState currentBoard = (BoardState)current;
		
		ArrayList newStates = m_nextStepper == null
			? new ArrayList()
			: m_nextStepper.getPossibleStates(current);
		for (int i = 0; i < BoardState.NUM_PRIESTS; i++)
		{
			int priestPosition = currentBoard.getPriestPosition(i);
			if (2 <= priestPosition && priestPosition <= 25)
			{
				int target =
					priestPosition%2 == 0
					? priestPosition + 1
					: priestPosition - 1;
				if (!currentBoard.isOccupied(target))
				{
					newStates.add(currentBoard.setPriestPosition(
						i,
						target));
				}
			}
		}
		return newStates;
	}

}
