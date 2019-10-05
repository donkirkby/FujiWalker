package main;
import java.util.ArrayList;


/**
 * @author Don.Kirkby
 *
 * Purpose: to generate all possible combination of a suit of six
 * 			coins.
 */
public class Suit {
	private ISuitListener m_listener;
	private ArrayList m_coinsToSkip;
	private boolean m_isExtraSkipEnabled = false;
	
	public Suit(ISuitListener listener)
	{
		m_listener = listener;
	}
	
	public Suit(ISuitListener listener, ArrayList coinsToSkip)
	{
		m_listener = listener;
		m_coinsToSkip = coinsToSkip;
	}
	
	private void generate(ArrayList chosen, ArrayList remaining)
	{
		if (remaining.size() == 0)
		{
			if (!shouldSkip(chosen))
			{
				m_listener.receiveSuit(chosen);
			}
		}
		else
		{
			ArrayList nextChosen = (ArrayList)chosen.clone();
			int numChosen = nextChosen.size();
			nextChosen.add(new Integer(-1));
			for (int i = 0; i < remaining.size(); i++)
			{
				nextChosen.set(numChosen, remaining.get(i));
				ArrayList nextRemaining = (ArrayList)remaining.clone();
				nextRemaining.remove(i);
				generate(nextChosen, nextRemaining); 
			}
		}
	}
	
	private boolean shouldSkip(ArrayList coins)
	{
		boolean shouldSkip = false;
		if (m_coinsToSkip != null)
		{
			boolean isMatch = true;
			for (int i = 0; i < m_coinsToSkip.size() && isMatch; i++)
			{
				isMatch = coins.get(i).equals(m_coinsToSkip.get(i));
			}
			if (isMatch)
			{
				m_coinsToSkip = null;
				shouldSkip = m_isExtraSkipEnabled;
			}
			else
			{
				shouldSkip = true;
			}
		}
		return shouldSkip;
	}
	
	public void enableExtraSkip(boolean isEnabled)
	{
		m_isExtraSkipEnabled = isEnabled;
	}
	
	public void generate() {
		generate(6);
	}

	public void generate(int numCoins) {
		ArrayList chosen = new ArrayList();
		ArrayList remaining = new ArrayList();
		for (int i = 0; i < numCoins; i++)
		{
			remaining.add(new Integer(numCoins - i - 1));
		}
		
		generate(chosen, remaining);
	}
}
