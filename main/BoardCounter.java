package main;

import java.util.*;
import java.math.*;


/*
 * Created on Jul 5, 2004
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
public class BoardCounter implements IBoardListener {
	private IBoardListener m_nextListener;
	private BoardCounter m_nextCounter;
	private String m_description;
	private BigInteger m_count = BigInteger.ZERO;
	private boolean m_isOutputEnabled = false;
	private boolean m_isTimestampEnabled = false;
	private BigInteger m_outputInterval = new BigInteger("10000");
	private BigInteger m_nextOutputInterval = new BigInteger("100000");
	private BoardWriter m_writer;
	private SolutionHistogram m_histogram;
	
	public BoardCounter(IBoardListener next)
	{
		m_nextListener = next;
	}
	
	/* (non-Javadoc)
	 * @see IBoardListener#receiveBoard(java.util.ArrayList)
	 */
	public void receiveBoard(ArrayList coins) {
		m_count = m_count.add(BigInteger.ONE);
		m_nextListener.receiveBoard(coins);
		if (shouldOutput())
		{
			System.out.println(getSummary());
			m_writer.receiveBoard(coins);
			if (m_histogram != null)
			{
				System.out.println(m_histogram.toString());
			}
			StatusTracker.getInstance().writeStatus(
					this, 
					coins);
		}
	}

	private boolean shouldOutput() {
		if (!m_isOutputEnabled)
			return false;
		
		if (m_count.mod(m_outputInterval).equals(BigInteger.ZERO))
		{
			if (m_count.compareTo(m_nextOutputInterval) >= 0)
			{
				// When we're solving the boards, use 100000.
				// When we're only counting, use 500000000.
				final BigInteger maxInterval = 
					new BigInteger("500000"); 
				m_outputInterval = m_nextOutputInterval.min(maxInterval);
				m_nextOutputInterval = 
					m_nextOutputInterval.multiply(new BigInteger("10"));
			}
			return true;
		}
		return false;
	}

	public BigInteger getBoardCount()
	{
		return m_count;
	}
	
	public void setBoardCount(String count)
	{
		m_count = new BigInteger(count);
	}
	
	public BoardCounter getNextCounter()
	{
		return m_nextCounter;
	}
	
	public void setNextCounter(BoardCounter nextCounter)
	{
		m_nextCounter = nextCounter;
	}
	
	public void setHistogram(SolutionHistogram histogram)
	{
		m_histogram = histogram;
	}
	
	public void enableOutput(boolean isEnabled)
	{
		m_isOutputEnabled = isEnabled;
		if (isEnabled)
		{
			m_writer = new BoardWriter();
		}
	}
	
	public void enableTimestamp(boolean isEnabled)
	{
		m_isTimestampEnabled = isEnabled;
	}
	
	public String getDescription()
	{
		return m_description;
	}
	
	public void setDescription(String description)
	{
		m_description = description;
	}
	
	public String getSummary()
	{
		String summary = m_description + m_count.toString();
		if (m_nextCounter == null)
		{
			summary += ".";
		}
		else
		{
			summary += ", " + m_nextCounter.getSummary();
		}
		if (m_isTimestampEnabled)
		{
			summary += " " + (new Date()).toString();
		}
		return summary;
	}
}
