package main;
import java.util.*;
/*
 * Created on Jul 29, 2004
 *
 */

/**
 * @author DKirkby
 *
 * created: Jul 29, 2004 
 */
public class SolutionHistogram
{
	private ArrayList m_frequencies = new ArrayList();
	
	public void receiveSolution(ArrayList solution)
	{
		for (int i = m_frequencies.size(); i < solution.size() + 1; i++)
		{
			m_frequencies.add(new Integer(0));
		}
		Integer frequency = (Integer)m_frequencies.get(solution.size());
		frequency = new Integer(frequency.intValue() + 1);
		m_frequencies.set(solution.size(), frequency);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString()
	{
		String result = "";
		for (int i = 0; i < m_frequencies.size(); i++)
		{
			if (i > 0)
			{
				result += ",";
			}
			result += m_frequencies.get(i).toString();
		}
		return result;
	}

}
