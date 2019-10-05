package test;

import java.math.*;
import java.util.ArrayList;

import main.BoardCounter;
import main.IBoardListener;

import junit.framework.TestCase;

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
public class BoardCounterTest extends TestCase
implements IBoardListener {
	private int m_countPassthroughs;
	
	/**
	 * Constructor for BoardCounterTest.
	 * @param arg0
	 */
	public BoardCounterTest(String arg0) {
		super(arg0);
	}
	
	public void setUp()
	{
		m_countPassthroughs = 0;
	}
	
	public void testSummary() {
		BoardCounter counter1 = new BoardCounter(this);
		counter1.setDescription("Number of boards: ");
		assertEquals(
			"Number of boards: 0.",
			counter1.getSummary());
		BoardCounter counter2 = new BoardCounter(this);
		counter2.setDescription("other boards: ");
		counter1.setNextCounter(counter2);
		assertEquals(
			"Number of boards: 0, other boards: 0.",
			counter1.getSummary());
	}

	public void testCount() {
		BoardCounter counter = new BoardCounter(this);
		counter.receiveBoard(null);
		assertEquals(
			"Number of boards should match.",
			new BigInteger("1"),
			counter.getBoardCount());
		counter.receiveBoard(null);
		assertEquals(
			"Number of boards should match.",
			new BigInteger("2"),
			counter.getBoardCount());
		assertEquals(
			"Number of passthroughs should match.",
			2,
			m_countPassthroughs);
	}
	
	public void testSkip() {
		BoardCounter counter = new BoardCounter(this);
		counter.setBoardCount("10000");
		counter.receiveBoard(null);
		assertEquals(
			"Number of boards should match.",
			new BigInteger("10001"),
			counter.getBoardCount());		
	}
	
	/* (non-Javadoc)
	 * @see IBoardListener#receiveBoard(java.util.ArrayList)
	 */
	public void receiveBoard(ArrayList coins) {
		m_countPassthroughs++;
	}
}
