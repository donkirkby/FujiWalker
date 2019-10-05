package test;
import junit.framework.TestCase;
import java.util.*;

import main.BoardFilter;
import main.IBoardListener;


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
public class BoardFilterTest extends TestCase implements IBoardListener{
	private int m_numReceived = 0;
	private BoardFactory m_boardFactory = new BoardFactory();
	
	/**
	 * Constructor for BoardFilterTest.
	 * @param arg0
	 */
	public BoardFilterTest(String arg0) {
		super(arg0);
	}

	public void testSuccess() {
		String board[] = 
			{ "05555333311110",
			  "04444222200000" };
		ArrayList coins = BoardFactory.createBoard(board);
		BoardFilter filter = new BoardFilter(this);
		filter.receiveBoard(coins);
		assertEquals(
			"Success board should have passed through.",
			1,
			m_numReceived);
	}

	public void testVertical() {
		String board1[] = 
			{ "04555333311110",
			  "05444222200000" };
		ArrayList coins1 = BoardFactory.createBoard(board1);
		String board2[] = 
			{ "05444222200000",
			  "04555333311110"
			  };
		ArrayList coins2 = BoardFactory.createBoard(board2);
		BoardFilter filter = new BoardFilter(this);
		filter.receiveBoard(coins1);
		filter.receiveBoard(coins2);
		assertEquals(
			"Only one board should pass through.",
			1,
			m_numReceived);
	}
	
	public void testHorizontal() {
		String board1[] = 
			{ "05500312313550",
			  "04414203202410" };
		ArrayList coins1 = BoardFactory.createBoard(board1);
		String board2[] = 
			{ "05531321300550",
			  "01420230241440" };
		ArrayList coins2 = BoardFactory.createBoard(board2);
		BoardFilter filter = new BoardFilter(this);
		filter.receiveBoard(coins1);
		filter.receiveBoard(coins2);
		assertEquals(
			"Only one board should pass through.",
			1,
			m_numReceived);
	}
	
	public void testHorizontalSymmetrical()
	{
		String board1[] = 
			{ "05511333311550",
			  "04400222200440" };
		ArrayList coins1 = BoardFactory.createBoard(board1);
		BoardFilter filter = new BoardFilter(this);
		filter.receiveBoard(coins1);
		assertEquals(
			"Only one board should pass through.",
			1,
			m_numReceived);
	}
	
	public void testDiagonal() {
		String board1[] = 
			{ "03333555500000",
			  "01111444422220" };
		ArrayList coins1 = BoardFactory.createBoard(board1);
		String board2[] = 
			{ "02222444411110",
			  "00000555533330" };
		ArrayList coins2 = BoardFactory.createBoard(board2);
		BoardFilter filter = new BoardFilter(this);
		filter.receiveBoard(coins1);
		filter.receiveBoard(coins2);
		assertEquals(
			"Only one board should pass through.",
			1,
			m_numReceived);
	}
	
	public void testDiagonal2() {
		String board1[] = 
			{ "05544222244500",
			  "00033111133050" };
		ArrayList coins1 = BoardFactory.createBoard(board1);
		String board2[] = 
			{ "05033111133000",
			  "00544222244550" };
		ArrayList coins2 = BoardFactory.createBoard(board2);
		BoardFilter filter = new BoardFilter(this);
		filter.receiveBoard(coins1);
		filter.receiveBoard(coins2);
		assertEquals(
			"Only one board should pass through.",
			1,
			m_numReceived);
	}
	
	/* (non-Javadoc)
	 * @see IBoardListener#receiveBoard(java.util.ArrayList)
	 */
	public void receiveBoard(ArrayList coins) {
		m_numReceived++;
	}
}
