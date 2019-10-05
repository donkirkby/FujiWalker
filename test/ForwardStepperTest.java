package test;
import junit.framework.TestCase;
import java.util.*;

import main.BoardState;
import main.ForwardStepper;


/*
 * Created on Jul 8, 2004
 *
 */

/**
 * @author dkirkby
 *
 * created: Jul 8, 2004
 * Tests to write:
 * 		Step forward
 * 		Step backward?
 * 		More than one step possible
 * 		No steps possible 
 */
public class ForwardStepperTest extends TestCase
{

	/**
	 * Constructor for ForwardStepperTest.
	 * @param arg0
	 */
	public ForwardStepperTest(String arg0)
	{
		super(arg0);
	}

	public void testStep()
	{
		//create a board and a state for that board.
		//make the board so it has only one step possible.
		//give the state to the stepper and check that
		//you get one state back and it matches your expectation.
		String board[] = 
			{ "00200000000000",
			  "00000000000000" };
		ArrayList coins = BoardFactory.createBoard(board);
		BoardState state = new BoardState(coins);
		ForwardStepper stepper = new ForwardStepper();
		ArrayList newStates = stepper.getPossibleStates(state);
		
		assertEquals(
			"Number of new states should match.",
			1,
			newStates.size());
		BoardState newState = (BoardState)newStates.get(0);
		assertEquals(
			"New position should match.",
			4,
			newState.getPriestPosition(0));
	}
	
	public void testPeak()
	{
		/* Once a priest is on the peak (four central spaces), he cannot
		 * leave.
		 */ 
		String board[] = 
		{ "05520335211430",
		  "04405223400110" };
		ArrayList coins = BoardFactory.createBoard(board);
		BoardState state = new BoardState(coins);
		state = state.setPriestPosition(3, 14);
		BoardState goal = state.setPriestPosition(3, 4);
		ForwardStepper stepper = new ForwardStepper();
		ArrayList newStates = stepper.getPossibleStates(state);
		
		assertFalse(
			"Should not be able to leave peak.", 
			newStates.contains(goal));
	}
	
	public void testOccupied()
	{
		/* try a board where the only move is to an occupied space. */
		String board[] = 
			{ "00200000000000",
			  "00000000000000" };
		ArrayList coins = BoardFactory.createBoard(board);
		BoardState state = new BoardState(coins);
		state = state.setPriestPosition(2, 4);
		ForwardStepper stepper = new ForwardStepper();
		ArrayList newStates = stepper.getPossibleStates(state);
		
		assertEquals(
			"Number of new states should match.",
			0,
			newStates.size());
	}
	
	public void testJump()
	{
		/* try a board where the only move is to jump an occupied space. */
		String board[] = 
			{ "00020000000000",
			  "00000000000000" };
		ArrayList coins = BoardFactory.createBoard(board);
		BoardState state = new BoardState(coins);
		state = state.setPriestPosition(2, 4);
		ForwardStepper stepper = new ForwardStepper();
		ArrayList newStates = stepper.getPossibleStates(state);
		
		assertEquals(
			"Number of new states should match.",
			1,
			newStates.size());
		BoardState newState = (BoardState)newStates.get(0);
		assertEquals(
			"New position should match.",
			6,
			newState.getPriestPosition(0));
	}
}
