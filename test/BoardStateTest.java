package test;
import junit.framework.TestCase;
import java.util.*;

import main.BoardState;

/*
 *  Created on Jul 12, 2004
 *
 */

/**
 * @author dkirkby
 *
 * created: Jul 12, 2004 
 */
public class BoardStateTest extends TestCase
{

	/**
	 * Constructor for BoardStateTest.
	 * @param arg0
	 */
	public BoardStateTest(String arg0)
	{
		super(arg0);
	}

	public void testValue()
	{
		String board[] = 
			{ "05555333311110",
			  "04444222200000" };
		ArrayList coins = BoardFactory.createBoard(board);
		BoardState state = new BoardState(coins);
		assertEquals(
			"Coin value should match.",
			5,
			state.getValue(2));
	}
	
	public void testPriestStart()
	{
		String board[] = 
			{ "05555333311110",
			  "04444222200000" };
		ArrayList coins = BoardFactory.createBoard(board);
		BoardState state = new BoardState(coins);
		assertEquals(
			"Priest starting position should match.",
			0,
			state.getPriestPosition(0));
		assertEquals(
			"Priest starting position should match.",
			1,
			state.getPriestPosition(1));
		assertEquals(
			"Priest starting position should match.",
			26,
			state.getPriestPosition(2));
		assertEquals(
			"Priest starting position should match.",
			27,
			state.getPriestPosition(3));
	}
	
	public void testPriestMove()
	{
		String board[] = 
			{ "05555333311110",
			  "04444222200000" };
		ArrayList coins = BoardFactory.createBoard(board);
		BoardState state = new BoardState(coins);
		BoardState newState = state.setPriestPosition(1, 9);
		assertEquals(
			"New priest position should match.",
			9,
			newState.getPriestPosition(1));
		assertEquals(
			"Original state should be unchanged.",
			1,
			state.getPriestPosition(1));
	}
	
	public void testOccupied()
	{
		String board[] = 
			{ "05555333311110",
			  "04444222200000" };
		ArrayList coins = BoardFactory.createBoard(board);
		BoardState state = new BoardState(coins);
		
		assertTrue("Should be occupied", state.isOccupied(0));
		assertFalse("Should not be occupied", state.isOccupied(4));
	}
	
	public void testToString()
	{
		String board[] = 
			{ "05555333311110",
			  "04444222200000" };
		ArrayList coins = BoardFactory.createBoard(board);
		BoardState state = new BoardState(coins);
		
		assertEquals(
			"String representation should match.",
			"0, 1, 26, 27",
			state.toString());
	}
	
	public void testEquals()
	{
		String board[] = 
			{ "05555333311110",
			  "04444222200000" };
		ArrayList coins = BoardFactory.createBoard(board);
		BoardState state1 = new BoardState(coins);
		BoardState state2 = new BoardState(coins);
		
		assertTrue(
			"Equals should check priest positions.",
			state1.equals(state2));
		
		state1 = state1.setPriestPosition(1, 3).setPriestPosition(2, 22);
		state2 = state2.setPriestPosition(3, 25);
		assertFalse(
			"Equals should not match on different positions.",
			state1.equals(state2));
	}
	
	public void testHashcode()
	{
		String board[] = 
			{ "05555333311110",
			  "04444222200000" };
		ArrayList coins = BoardFactory.createBoard(board);
		BoardState state1 = new BoardState(coins);
		BoardState state2 = new BoardState(coins);
		
		assertEquals(
			"Hashcode should match.",
			state1.hashCode(),
			state2.hashCode());
	}
}
