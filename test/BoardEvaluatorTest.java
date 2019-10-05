package test;
import junit.framework.TestCase;
import java.util.*;

import main.BoardEvaluator;
import main.BoardState;


/*
 * Created on Jul 12, 2004
 *
 */

/**
 * @author DKirkby
 *
 * created: Jul 12, 2004 
 */
public class BoardEvaluatorTest extends TestCase
{

	/**
	 * Constructor for BoardEvaluatorTest.
	 * @param arg0
	 */
	public BoardEvaluatorTest(String arg0)
	{
		super(arg0);
	}

	public void testNonMatch()
	{
		/* starting positions should not match goal */
		String board[] = 
			{ "05555333311110",
			  "04444222200000" };
		ArrayList coins = BoardFactory.createBoard(board);
		BoardState state = new BoardState(coins);
		BoardEvaluator evaluator = new BoardEvaluator();
		assertFalse("Should not match.", evaluator.isGoal(state));
	}

	public void testMatch()
	{
		/* Set positions to match goal */
		String board[] = 
			{ "05555333311110",
			  "04444222200000" };
		ArrayList coins = BoardFactory.createBoard(board);
		BoardState state = new BoardState(coins);
		state = state.setPriestPosition(0, 12).setPriestPosition(1, 13);
		state = state.setPriestPosition(2, 14).setPriestPosition(3, 15);
		BoardEvaluator evaluator = new BoardEvaluator();
		assertTrue("Should match.", evaluator.isGoal(state));
	}
}
