/*
 * Created on Jun 13, 2005
 *
 */
package test;

import java.util.ArrayList;

import main.BoardState;
import main.MoveGrid;
import junit.framework.TestCase;

/**
 * @author dkirkby
 *
 */
public class MoveGridTest extends TestCase {
	public void testCreate(){
		// SETUP
		ArrayList solution = createSolution();
		
		// EXEC & VERIFY
		MoveGrid moveGrid = new MoveGrid(solution);
		assertFalse(
				"grid starts full",
				moveGrid.isEmpty());
		assertTrue(
				"grid starts with at least one move for priest 0",
				moveGrid.hasMove(0));
		assertEquals(
				"first move for priest 0",
				4,
				moveGrid.getMove(0));
		moveGrid.popMove(0);
		assertEquals(
				"second move for priest 0",
				5,
				moveGrid.getMove(0));
		moveGrid.popMove(0);
		assertFalse(
				"grid ends with no moves for priest 0",
				moveGrid.hasMove(0));
		
		assertEquals(
				"first move for priest 1",
				3,
				moveGrid.getMove(1));
		moveGrid.popMove(1);
		assertEquals(
				"second move for priest 1",
				7,
				moveGrid.getMove(1));
		moveGrid.popMove(1);
		assertTrue(
				"grid ends empty",
				moveGrid.isEmpty());
	}
	
	public void testClone(){
		// SETUP
		ArrayList solution = createSolution();
		
		MoveGrid moveGrid1 = new MoveGrid(solution);
		
		// EXEC
		MoveGrid moveGrid2 = (MoveGrid)moveGrid1.clone();
		moveGrid1.popMove(0);
		
		// VERIFY
		assertEquals(
				"moveGrid1 should have second move for priest 0",
				5,
				moveGrid1.getMove(0));
		assertEquals(
				"moveGrid2 should have first move for priest 0",
				4,
				moveGrid2.getMove(0));
	}

	/**
	 * @return
	 */
	private ArrayList createSolution() {
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
		return solution;
	}
}
