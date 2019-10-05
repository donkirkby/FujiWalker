package test;

import java.util.ArrayList;

import junit.framework.TestCase;
import main.ISuitListener;
import main.Suit;



/*
 * Created on Jun 29, 2004
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
public class SuitTest extends TestCase implements ISuitListener{

	private static final int expectedValues[][] = 
		{
			{2, 1, 0},
			{2, 0, 1},
			{1, 2, 0},
			{1, 0, 2},
			{0, 2, 1},
			{0, 1, 2}
		};
	private int m_numReceived;
	
	/**
	 * Constructor for SuitTest.
	 * @param arg0
	 */
	public SuitTest(String arg0) {
		super(arg0);
	}
	
	public void setUp()
	{
		m_numReceived = 0;
	}
	
	public void testSkip() {
		Suit s = createSuitToSkip();
		m_numReceived = 2;
		s.generate(3);
		assertEquals(
			"Should have skipped & received same number of suits",
			6,
			m_numReceived);
	}

	public void testSkipPlusOne() {
		Suit s = createSuitToSkip();
		s.enableExtraSkip(true);
		m_numReceived = 3;
		s.generate(3);
		assertEquals(
			"Should have skipped & received same number of suits",
			6,
			m_numReceived);
	}

	private Suit createSuitToSkip() {
		ArrayList coinsToSkip = new ArrayList();
		coinsToSkip.add(new Integer(1));
		coinsToSkip.add(new Integer(2));
		coinsToSkip.add(new Integer(0));
		
		Suit s = new Suit(this, coinsToSkip);
		return s;
	}

	public void testGenerate() {
		Suit s = new Suit(this);
		s.generate(3);
		assertEquals(
			"Should have received same number of suits",
			6,
			m_numReceived);
	}
	
	/* (non-Javadoc)
	 * @see ISuitListener#receiveSuit(java.util.ArrayList)
	 */
	public void receiveSuit(ArrayList coins) {
		for (int i = 0; i < 3; i++)
		{
			assertEquals(
				"Coin values should match.",
				expectedValues[m_numReceived][i], 
				((Integer)coins.get(i)).intValue());
		}
		m_numReceived++;
	}
}
