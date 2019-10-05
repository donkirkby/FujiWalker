/*
 * Created on Jun 13, 2005
 *
 */
package test;

import java.util.ArrayList;

import main.BoardState;
import main.SolutionOptimizer;
import junit.framework.TestCase;

/**
 * @author dkirkby
 *
 */
public class SolutionOptimizerTest extends TestCase {

	// Show that it stops evaluating when it gets a longer solution.
	public void testLength() { 
		// SETUP
		String board[] = 
			{ "00200000002000",
			  "00020000000200" };
		ArrayList coins = BoardFactory.createBoard(board);
		BoardState state = new BoardState(coins);
		ArrayList solution = new ArrayList();
		solution.add(state);
		state = state.setPriestPosition(0, 4);
		solution.add(state);
		
		ArrayList solution2 = (ArrayList)solution.clone();
		ArrayList solution3 = (ArrayList)solution.clone();
		state = state.setPriestPosition(3, 23);
		solution3.add(state);
		// At this point, solution and solution2 have 1 move, and solution3
		// has 2 moves.
		
		SolutionOptimizer optimizer = new SolutionOptimizer();
		
		// EXEC
		optimizer.reset();
		assertTrue(
				"First solution should not stop evaluation.",
				optimizer.evaluate(solution));
		assertTrue(
				"Second solution should not stop evaluation.",
				optimizer.evaluate(solution2));
		assertFalse(
				"Third solution should stop evaluation.",
				optimizer.evaluate(solution3));
	}
	
	public void testCompare() {
		// SETUP
		String board[] = 
			{ "00200000002000",
			  "00020000000200" };
		ArrayList coins = BoardFactory.createBoard(board);
		BoardState state = new BoardState(coins);
		ArrayList solution = new ArrayList();
		solution.add(state);
		state = state.setPriestPosition(0, 4);
		solution.add(state);
		state = state.setPriestPosition(1, 5);
		solution.add(state);
		state = state.setPriestPosition(0, 6);
		solution.add(state);
		
		state = new BoardState(coins);
		ArrayList solution2 = new ArrayList();
		solution2.add(state);
		state = state.setPriestPosition(0, 4);
		solution2.add(state);
		state = state.setPriestPosition(0, 6);
		solution2.add(state);
		state = state.setPriestPosition(1, 5);
		solution2.add(state);
		
		// At this point, solution moves priest 0, 1, 0 and solution2 
		// moves priests 0, 0, 1. solution2 should be better.
		
		SolutionOptimizer optimizer = new SolutionOptimizer();
		
		// EXEC
		optimizer.reset();
		assertTrue(
				"First solution should not stop evaluation.",
				optimizer.evaluate(solution));
		assertTrue(
				"Second solution should not stop evaluation.",
				optimizer.evaluate(solution2));
		assertSame(
				"Second solution should be best.",
				solution2,
				optimizer.getBestSolution());
	}
	
	public void testOptimize(){
		// SETUP
		String board[] = 
			{ "00200000000000",
			  "01010000000000" };
		ArrayList coins = BoardFactory.createBoard(board);
		BoardState state = new BoardState(coins);
		ArrayList solution = new ArrayList();
		solution.add(state);
		state = state.setPriestPosition(0, 4);
		solution.add(state);
		state = state.setPriestPosition(1, 3);
		solution.add(state);
		state = state.setPriestPosition(0, 5);
		solution.add(state);
		state = state.setPriestPosition(1, 7);
		solution.add(state);

		// EXEC
		SolutionOptimizer optimizer = new SolutionOptimizer();
		ArrayList prettySolution = optimizer.optimize(coins, solution);
		
		// VERIFY
		state = (BoardState)prettySolution.get(1);
		assertEquals(
				"first step for priest 0",
				4,
				state.getPriestPosition(0));
		state = (BoardState)prettySolution.get(2);
		assertEquals(
				"second step for priest 0",
				5,
				state.getPriestPosition(0));
	}
}
