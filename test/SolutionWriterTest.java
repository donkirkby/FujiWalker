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
import main.SolutionWriter;



/**
 * @author DKirkby
 *
 */
public class SolutionWriterTest extends MockOutputTest {
	public void testWriteSolution() 
	throws IOException{
		// SETUP
		String board[] = 
			{ "00000000000000",
			  "00000000000000" };
		ArrayList coins = BoardFactory.createBoard(board);
		BoardState state = new BoardState(coins);
		ArrayList solution = new ArrayList();
		solution.add(state);
		state = state.setPriestPosition(0, 4);
		solution.add(state);
		
		// EXEC
		SolutionWriter writer = new SolutionWriter();
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
					"0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0",
					boardDescription);
			String move = outputReader.readLine();
			assertEquals(
					"0, 1, 26, 27",
					move);
			String move2 = outputReader.readLine();
			assertEquals(
					"4, 1, 26, 27",
					move2);
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

}
