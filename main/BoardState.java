package main;
import java.util.*;

 
/*	Purpose: to hold the state of the board and priests.
 * 				It will also generate new states by moving priests
 * 				from one space to another.
 * Created on Jul 12, 2004
 *
 */

/**
 * @author dkirkby
 *
 * created: Jul 12, 2004 
 */
public class BoardState implements IState
{
	private static int s_bitOffsets[];
	
	private static final int s_startingPositions[] = {0, 1, 26, 27}; 
	private int m_coins[];
	private int m_priestPositions[];
	private int m_combinedPositions;
	private BoardState m_previous;
	 
	public static final int NUM_PRIESTS = 4;
	public BoardState(ArrayList coins)
	{
		m_coins = new int[coins.size()];
		for (int i = 0; i < coins.size(); i++)
		{
			m_coins[i] = ((Integer)coins.get(i)).intValue();
		}
		m_priestPositions = s_startingPositions;
		calculatePositions();
	}
	
	private BoardState(int coins[], int priestPositions[])
	{
		m_coins = coins;
		m_priestPositions = priestPositions;
		calculatePositions();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString()
	{
		String result = "";
		for (int i = 0; i < NUM_PRIESTS; i++)
		{
			if (i > 0)
			{
				result += ", ";
			}
			result += Integer.toString(m_priestPositions[i]);
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object arg0)
	{
		BoardState otherState = (BoardState)arg0;
		return m_combinedPositions == otherState.m_combinedPositions;
		/*
		for (int i = 0; i < NUM_PRIESTS; i++)
		{
			if (m_priestPositions[i] != otherState.m_priestPositions[i])
			{
				Object details[] = {
					this,
					new Integer(m_combinedPositions),
					otherState,
					new Integer(otherState.m_combinedPositions)
				};
				String message = MessageFormat.format(
					"combined should not match: {0} = {1}; {2} = {3}",
					details);
				assert m_combinedPositions != otherState.m_combinedPositions 
					: message;
				return false; 
			}
		}
		assert m_combinedPositions == otherState.m_combinedPositions 
			: "combined should match:" + toString() 
			+ "; " + otherState.toString();
		return true;
		*/
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode()
	{
		return m_combinedPositions;
	}

	private void calculatePositions()
	{
		if (s_bitOffsets == null)
		{
			int numSpaces = 28;
			s_bitOffsets = new int[numSpaces];
			int currentBitValue = 1;
			for (int i = 0; i < numSpaces; i++)
			{
				s_bitOffsets[i] = currentBitValue;
				currentBitValue <<= 1;
			}
		}
		m_combinedPositions = 0;
		for (int i = 0; i < NUM_PRIESTS; i++)
		{
			m_combinedPositions |= s_bitOffsets[m_priestPositions[i]]; 
		}
	}
	
	public int getValue(int space)
	{
		return m_coins[space];
	}
	
	public int getPriestPosition(int priestNumber)
	{
		return m_priestPositions[priestNumber];
	}
	
	public boolean isOccupied(int position)
	{
		return (m_combinedPositions & s_bitOffsets[position]) != 0;
	}
	
	public BoardState setPriestPosition(int priestNumber, int newPosition)
	{
		int newPriestPositions[] = new int[NUM_PRIESTS];
		for (int i = 0; i < NUM_PRIESTS; i++)
		{
			newPriestPositions[i] = m_priestPositions[i];
		}
		newPriestPositions[priestNumber] = newPosition;
		BoardState newState = new BoardState(m_coins, newPriestPositions);
		newState.m_previous = this;
		return newState;
	}
	
	/* (non-Javadoc)
	 * @see IState#getPrevious()
	 */
	public IState getPrevious()
	{
		return m_previous;
	}

}
