package test;
import junit.framework.TestCase;
import java.util.*;

import main.BoardState;
import main.IStepper;
import main.SideStepper;


/*
 * Created on Jul 13, 2004
 *
 */

/**
 * @author DKirkby
 *
 * created: Jul 13, 2004 
 */
public class SideStepperTest extends TestCase
{

	/**
	 * Constructor for SideStepperTest.
	 * @param arg0
	 */
	public SideStepperTest(String arg0)
	{
		super(arg0);
	}

	public void testOffBoard()
	{
		/* before the priests move onto the board, they can't step sideways. */
		String board[] = 
			{ "00000000000000",
			  "00000000000000" };
		ArrayList coins = BoardFactory.createBoard(board);
		BoardState state = new BoardState(coins);
		IStepper stepper = new SideStepper();
		ArrayList newStates = stepper.getPossibleStates(state);
		
		assertEquals(
			"Number of new states should match.",
			0,
			newStates.size());
	}
	
	public void testOccupied()
	{
		String board[] = 
			{ "00000000000000",
			  "00000000000000" };
		ArrayList coins = BoardFactory.createBoard(board);
		BoardState state = new BoardState(coins);
		IStepper stepper = new SideStepper();

		/* check that priests won't step on each other. */
		state = state.setPriestPosition(0, 4).setPriestPosition(3, 5);
		ArrayList newStates = stepper.getPossibleStates(state);
		
		assertEquals(
			"Number of new states should match.",
			0,
			newStates.size());
	}
	
	public void testStep()
	{
		String board[] = 
			{ "00000000000000",
			  "00000000000000" };
		ArrayList coins = BoardFactory.createBoard(board);
		BoardState startState = new BoardState(coins);
		startState = startState.setPriestPosition(0, 2);
		IStepper stepper = new SideStepper();
		
		ArrayList newStates = stepper.getPossibleStates(startState);
		
		assertEquals(
			"Number of new states should match.",
			1,
			newStates.size());
		BoardState newState = (BoardState)newStates.get(0);
		assertEquals(
			"New position should match.",
			3,
			newState.getPriestPosition(0));
		
		newStates = stepper.getPossibleStates(newState);
		assertEquals(
			"Number of new states should match after second move.",
			1,
			newStates.size());
		assertEquals(
			"New position should match original.",
			startState,
			newStates.get(0));
	}
}
