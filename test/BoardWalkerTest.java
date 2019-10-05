package test;
import junit.framework.TestCase;
import java.util.*;

import main.BoardEvaluator;
import main.BoardState;
import main.BoardWalker;
import main.ForwardStepper;
import main.IEvaluator;
import main.IState;
import main.IStepper;
import main.SideStepper;


/*
 * Created on Jul 8, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

/**
 * @author dkirkby
 *
 *	tests to write:
 *		- single step solution
 *		- multistep solution
 *		solution with dead ends?
 */
public class BoardWalkerTest extends TestCase {

	/**
	 * Constructor for BoardWalkerTest.
	 * @param arg0
	 */
	public BoardWalkerTest(String arg0) {
		super(arg0);
	}

	public void testSingleStep() 
	{
		/* create a mock stepper with an array of arrays of states.
		 * For example, put in state 1, get back state 2.
		 * create a mock evaluator that checks for success.
		 * Create the walker and give it the mock stepper and evaluator.
		 * Tell it to walk with the stepper until the evaluator
		 * says it's successful.
		 * Start with A->B!
		 */
		String steps[][] = 
		 	{
		 		{"A", "B"}
		 	};
		BoardWalker walker = new BoardWalker(
			new MockState("A"),
		 	new MockStepper(steps), 
			new MockEvaluator("B"));
		
		ArrayList solution = walker.solve();
		String expectedSolution[] =
			{
				"A", "B"
			};
		checkSolution(expectedSolution, solution);
	}
	
	public void testMultistep()
	{
		/* Test a solution that takes several steps to solve.
		 * A -> B -> C -> D!
		 */
		String steps[][] = 
			{
				{"A", "B"},
				{"B", "C"},
				{"C", "D"}
			};
		BoardWalker walker = new BoardWalker(
			new MockState("A"),
			new MockStepper(steps), 
			new MockEvaluator("D"));
		
		ArrayList solution = walker.solve();
		String expectedSolution[] =
			{
				"A", "B", "C", "D"
			};
		checkSolution(expectedSolution, solution);
	}
	
	public void testCycle()
	{
		/* Test that a cycle in an unsolvable problem won't cause an
		 * infinite loop.
		 * A <-> B, but goal is C!
		 */
		String steps[][] = 
			{
				{"A", "B"},
				{"B", "A"}
			};
		BoardWalker walker = new BoardWalker(
			new MockState("A"),
			new MockStepper(steps), 
			new MockEvaluator("C"));

		ArrayList solution = walker.solve();
		String expectedSolution[] =
			{
			};
		checkSolution(expectedSolution, solution);
	}

	/* No possible steps and we're not at the goal.
	 * Should return an empty list.
	 */	
	public void testNoSolution() 
	{
		String steps[][] = 
			{
			};
		BoardWalker walker = new BoardWalker(
			new MockState("A"),
			new MockStepper(steps), 
			new MockEvaluator("B"));
		
		ArrayList solution = walker.solve();
		String expectedSolution[] =
			{
			};
		checkSolution(expectedSolution, solution);
	}
	
	public void testTrivialSolution()
	{
		String steps[][] = 
			{
			};
		BoardWalker walker = new BoardWalker(
			new MockState("A"),
			new MockStepper(steps), 
			new MockEvaluator("A"));
		
		ArrayList solution = walker.solve();
		String expectedSolution[] =
			{
				"A"
			};
		checkSolution(expectedSolution, solution);
	}

	public void testBoardForward()
	{
		/* A solution that only requires forward steps. */
		String board[] = 
			{ "04442511522240",
			  "01003055033310" };
		ArrayList coins = BoardFactory.createBoard(board);
		BoardState state = new BoardState(coins);
		BoardEvaluator evaluator = new BoardEvaluator();

		BoardWalker walker = new BoardWalker(
			state,
			new ForwardStepper(), 
			new BoardEvaluator());
		
		ArrayList solution = walker.solve();
		String expectedSolution[] =
			{
 				"0, 1, 26, 27",
				"10, 1, 26, 27",
				"12, 1, 26, 27",
				"12, 3, 26, 27",
				"12, 13, 26, 27",
				"12, 13, 16, 27",
				"12, 13, 14, 27",
				"12, 13, 14, 25",
				"12, 13, 14, 15"
			};
		checkSolution(expectedSolution, solution);
	}

	public void testBoardSide()
	{
		/* A solution that uses a side step and a forward step. */
		String board[] = 
			{ "00000000000000",
			  "00000001000000" };
		ArrayList coins = BoardFactory.createBoard(board);
		BoardState state = new BoardState(coins);
		state = state.setPriestPosition(0, 12).setPriestPosition(1, 13);
		state = state.setPriestPosition(2, 14).setPriestPosition(3, 16);
		BoardEvaluator evaluator = new BoardEvaluator();
		SideStepper stepper = new SideStepper();
		stepper.setNextStepper(new ForwardStepper());

		BoardWalker walker = new BoardWalker(
			state,
			stepper, 
			new BoardEvaluator());
		
		ArrayList solution = walker.solve();
		String expectedSolution[] =
			{
				"0, 1, 26, 27",
				"12, 1, 26, 27",
				"12, 13, 26, 27",
				"12, 13, 14, 27",
				"12, 13, 14, 16",
				"12, 13, 14, 17",
				"12, 13, 14, 15"
			};
		checkSolution(expectedSolution, solution);
	}

	private void checkSolution(String[] expectedSolution, ArrayList solution)
	{
		assertEquals(
			"Should get expected number of steps.",
			expectedSolution.length,
			solution.size());
		for (int i = 0; i < solution.size(); i++)
		{
			assertEquals(
				"States should match at step " + Integer.toString(i),
				expectedSolution[i],
				solution.get(i).toString());
		}
	}
	
	private class MockStepper implements IStepper
	{
		private String m_steps[][];
		public MockStepper(String steps[][])
		{
			m_steps = steps;
		}
		
		/* (non-Javadoc)
		 * @see IStepper#getPossibleStates(IState)
		 */
		public ArrayList getPossibleStates(IState current)
		{
			MockState currentMockState = (MockState)current;
			ArrayList states = new ArrayList();
			boolean isFound = false;
			for (int i = 0; i < m_steps.length && !isFound; i++)
			{
				isFound = m_steps[i][0].equals(currentMockState.getState());
				if (isFound)
				{
					for (int j = 1; j < m_steps[i].length; j++)
					{
						states.add(new MockState(
							m_steps[i][j], 
							currentMockState));
					}
				}
			}
			return states;
		}

	}
	
	private class MockEvaluator implements IEvaluator
	{
		private IState m_goal;
		public MockEvaluator(String goal)
		{
			m_goal = new MockState(goal);
		}
		
		public MockEvaluator(IState goal)
		{
			m_goal = goal;
		}
		
		public boolean isGoal(IState state)
		{
			return state.equals(m_goal);
		}
	}
	
	private class MockState implements IState
	{
		private String m_state;
		private MockState m_previous;

		public MockState(String state)
		{
			m_state = state;
			m_previous = null;
		}
		
		public MockState(String state, MockState previous)
		{
			m_state = state;
			m_previous = previous;
		}
		
		public String getState()
		{
			return m_state;
		}
		
		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		public String toString()
		{
			return m_state;
		}

		
		/* (non-Javadoc)
		 * @see IState#getPrevious()
		 */
		public IState getPrevious()
		{
			return m_previous;
		}

		
		public boolean equals(Object o)
		{
			return ((MockState)o).m_state.equals(m_state);
		}
	}
}
