/*
 * Created on 29-May-2005
 *
 */
package test;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

import main.BoardState;
import main.BoardTracker;
import main.SolutionPublisher;
import main.SolutionWriter;


/**
 * @author DKirkby
 *
 */
public class SolutionPublisherTest extends MockOutputTest {
	
	public void testWriteSolution() 
	throws IOException{
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
		state = state.setPriestPosition(3, 23);
		solution.add(state);
		state = state.setPriestPosition(0, 5);
		solution.add(state);
		state = state.setPriestPosition(3, 22);
		solution.add(state);
		state = state.setPriestPosition(1, 7);
		solution.add(state);
		state = state.setPriestPosition(2, 20);
		solution.add(state);
		for (int i = 0; i < 6; i++)
		{
			state = state.setPriestPosition(0, 4);
			solution.add(state);
			state = state.setPriestPosition(0, 5);
			solution.add(state);
		}
		
		// EXEC
		SolutionWriter writer = new SolutionPublisher();
		writer.writeSolution(coins, solution);
		
		// VERIFY
		BufferedReader outputReader = 
			new BufferedReader(
					new StringReader(
							getOutputWriter().toString()));
		try
		{
			String date = outputReader.readLine();
			assertTrue(date.length() > 0);
			String boardDescription = outputReader.readLine();
			assertEquals(
					"A020000000200M",
					boardDescription);
			boardDescription = outputReader.readLine();
			assertEquals(
					"C002000000020S",
					boardDescription);
			String move = outputReader.readLine();
			assertEquals(
					"AR2, SL2, AD, SU, CR2(+1), ML2(+1), AU, AD, AU, AD, AU, AD, AU, AD, AU, AD",
					move);
			move = outputReader.readLine();
			assertEquals(
					"AU, AD",
					move);
			String message = outputReader.readLine();
			assertTrue(
					message.startsWith("previous solution is "));
			assertEquals(
					"",
					outputReader.readLine());
			assertNull(
					outputReader.readLine());
		}
		finally
		{
			outputReader.close();
		}
	}
	
	public void testPrettySolution()
	throws IOException
	{
		String board[] = 
		{ "03023515225040",
		  "01311444502300" };
		ArrayList coins = BoardFactory.createBoard(board);
		BoardTracker tracker = new BoardTracker();
		ArrayList solution = tracker.solveBoard(coins);
/*		
		Mon Jun 13 14:52:07 PDT 2005
		A302351522504M
		C131144450230S
		AR5, CR1, SL5, AD, AL3, CR4(+1), AR4(+1), SU, ML5(+1), SL1(+1)
		previous solution is 10 - 0 moves
		
		AR5, AD, AL3, CR1, CR4(+1), AR4(+1), SL5, SU, ML5(+1), SL1(+1)
*/
		// EXEC
		SolutionWriter writer = new SolutionPublisher();
		writer.writeSolution(coins, solution);
		
		// VERIFY
		BufferedReader outputReader = 
			new BufferedReader(
					new StringReader(
							getOutputWriter().toString()));
		try
		{
			String date = outputReader.readLine();
			assertTrue(date.length() > 0);
			String boardDescription = outputReader.readLine();
			assertEquals(
					"A302351522504M",
					boardDescription);
			boardDescription = outputReader.readLine();
			assertEquals(
					"C131144450230S",
					boardDescription);
			String move = outputReader.readLine();
/*			assertEquals(
					"AR5, AD, AL3, CR1, CR4(+1), AR4(+1), SL5, SU, ML5(+1), SL1(+1)",
					move);
	*/		String message = outputReader.readLine();
			assertTrue(
					message.startsWith("previous solution is "));
			assertEquals(
					"",
					outputReader.readLine());
			assertNull(
					outputReader.readLine());
		}
		finally
		{
			outputReader.close();
		}

	}
}
