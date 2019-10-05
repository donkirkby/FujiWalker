package main;
import java.util.ArrayList;


/*
 * Created on Jun 28, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

/**
 * @author Don.Kirkby
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class BoardBuilder 
implements ISuitListener, IBoardListener
{
	private IBoardListener m_next;
	private Suit m_suit;
	private ArrayList m_coins;
	private int m_offset;
	
	public BoardBuilder(IBoardListener next)
	{
		m_suit = new Suit(this);
		m_next = next;
	}
	
	public BoardBuilder(IBoardListener next, ArrayList coinsToSkip)
	{
		m_suit = new Suit(this, coinsToSkip);
		m_next = next;
	}
	
	public void generate()
	{
		ArrayList coins = new ArrayList();
		for (int i = 0; i < 28; i++)
		{
			coins.add(
				i < 2 || i > 25
				? new Integer(0)
				: null);
		}
		receiveBoard(coins);
	}
	
	public void enableExtraSkip(boolean isEnabled)
	{
		m_suit.enableExtraSkip(isEnabled);
	}
	
	/* (non-Javadoc)
	 * @see ISuitListener#receiveSuit(java.util.ArrayList)
	 */
	public void receiveSuit(ArrayList coins) {
		for (int i = 0; i < 6; i++)
		{
			m_coins.set(m_offset + i%2 + 8*(i/2), coins.get(i));
		}
		m_next.receiveBoard(m_coins);
	}
	
	public void receiveBoard(ArrayList coins)
	{
		m_coins = (ArrayList)coins.clone();
		for (m_offset = 2; coins.get(m_offset) != null; m_offset += 2)
		{
			;
		}
		m_suit.generate();
	}
}
