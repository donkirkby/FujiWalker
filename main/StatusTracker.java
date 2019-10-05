package main;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


/*
 * Created on 9-Mar-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

/**
 * @author DKirkby
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class StatusTracker {

	private int m_maxLength = 0;
	private int m_suffix = 0;
	private String m_allCounter = "0";
	private String m_uniqueCounter = "0";
	private ArrayList m_boardToSkip;
	
	private static StatusTracker s_instance = null;
	
	public static StatusTracker getInstance()
	{
		if (s_instance == null)
		{
			StatusTracker instance0 = 
				new StatusTracker("status0.txt");
			StatusTracker instance1 =
				new StatusTracker("status1.txt");
			
			if (instance0.m_allCounter.length() >
					instance1.m_allCounter.length()
					|| instance0.m_allCounter.compareTo(
							instance1.m_allCounter) > 0)
				s_instance = instance0;
			else
				s_instance = instance1;
		}
		
		return s_instance;
	}
	
	private StatusTracker(String fileName)
	{
		try
		{
			BufferedReader r = new BufferedReader(
					new FileReader(fileName));
			try
			{
				r.readLine(); // skip first label
				m_maxLength = Integer.parseInt(r.readLine());
				
				r.readLine();
				m_allCounter = r.readLine();
				
				r.readLine();
				m_uniqueCounter = r.readLine();
				
				String coins = r.readLine();
				
				String coinValues[] = coins.split(",");
				m_boardToSkip = new ArrayList();
				for (int i = 2; i < coinValues.length-2; i++)
				{
					m_boardToSkip.add(new Integer(
							coinValues[i]));
				}
			}
			finally
			{
				r.close();
			}
		}
		catch (IOException ex)
		{
			System.out.println(ex.getMessage());
		}
	}

	/**
	 * @param coins
	 */
	public void writeStatus(BoardCounter mainCounter, ArrayList coins) {
		try
		{
			String fileName = 
				"status".concat(
						String.valueOf(m_suffix)).concat(
								".txt");
			FileWriter statusFile = 
				new FileWriter(fileName);
			try
			{
				statusFile.write("Max length:\r\n");
				statusFile.write(
						String.valueOf(m_maxLength));
				statusFile.write("\r\n");
				BoardCounter counter = mainCounter;
				while (counter != null)
				{
					statusFile.write(counter.getDescription());
					statusFile.write("\r\n");
					statusFile.write(String.valueOf(
							counter.getBoardCount()));
					statusFile.write("\r\n");
					counter = counter.getNextCounter();
				}
				for (int i = 0; i < coins.size(); i++)
				{
					if (i > 0)
						statusFile.write(",");
					statusFile.write(String.valueOf(
							coins.get(i)));
				}
				
				m_suffix = (m_suffix + 1) % 2;
			}
			finally
			{
				statusFile.close();
			}
		}
		catch (IOException ex)
		{
			System.out.println(ex.getMessage());
		}
	}

	/**
	 * @param m_maxLength The m_maxLength to set.
	 */
	public void setMaxLength(int m_maxLength) {
		this.m_maxLength = m_maxLength;
	}

	/**
	 * @return Returns the m_maxLength.
	 */
	public int getMaxLength() {
		return m_maxLength;
	}

	public ArrayList getCoinsToSkip(int suitNum)
	{
		if (m_boardToSkip == null)
			return null;
		
		ArrayList coinsToSkip = new ArrayList();
		for (int j = 0; j < 6; j++)
		{
			coinsToSkip.add(
					m_boardToSkip.get(
							j/2*8 + j%2 + 2*suitNum));
		}
		return coinsToSkip;
	}

	/**
	 * @return
	 */
	public String getAllBoardCount() {
		return m_allCounter;
	}

	/**
	 * @return
	 */
	public String getUniqueBoardCount() {
		return m_uniqueCounter;
	}

}
