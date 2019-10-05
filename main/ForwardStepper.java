package main;
import java.util.ArrayList;


/*
 * Created on Jul 8, 2004
 *
 */

/**
 * @author dkirkby
 *
 * created: Jul 8, 2004 
 */
public class ForwardStepper implements IStepper
{
	/* (non-Javadoc)
	 * @see IStepper#getPossibleStates(IState)
	 */
	public ArrayList getPossibleStates(IState current)
	{
		BoardState currentState = (BoardState)current;
		ArrayList newStates = new ArrayList();
		final int numPriests = 4;
		for (int priestNum = 0; priestNum < numPriests; priestNum++)
		{
			// If the priest is already on the peak, stay there.
			int lowerLimit = 12, upperLimit = 15;
			int priestPosition = currentState.getPriestPosition(priestNum); 
			if (priestPosition < lowerLimit || upperLimit < priestPosition)
			{
				lowerLimit = 2;
				upperLimit = 25;
			}
			int step = -2;
			while (step <= 2)
			{
				int target = priestPosition + step;
				int numSteps = 0;
				while (lowerLimit <= target && target <= upperLimit)
				{
					if (!currentState.isOccupied(target))
					{
						numSteps++;
						if (numSteps == currentState.getValue(target))
						{
							newStates.add(currentState.setPriestPosition(
								priestNum,
								target));
						}
					}
					target += step;
				}
				step += 4; // check the other direction.
			}
		}
		return newStates;
	}
}
