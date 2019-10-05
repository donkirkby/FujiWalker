package main;

import java.text.MessageFormat;
import java.util.*;

/*
 * Created on Jun 28, 2004
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
public class FujiWalker extends FujiDriver
{

	public static void main(String[] args) {
		try
		{
			//findLongestSolution();
			countAllBoards();
			//timeASolution();
		}
		catch(Exception e)
		{
			System.out.println(e.getLocalizedMessage());
		}
	}

	private static void timeASolution()
	{
		/* A solution that uses a side step and a forward step. */
		String board[] = 
			{ "05555333311110",
			  "04444222200000" };
		ArrayList coins = new ArrayList();
		for (int i = 0; i < 13; i++)
		{
			coins.add(new Integer(board[0].substring(i, i+1)));
			coins.add(new Integer(board[1].substring(i, i+1)));
		}
		BoardState state = new BoardState(coins);
		BoardEvaluator evaluator = new BoardEvaluator();
		SideStepper stepper = new SideStepper();
		stepper.setNextStepper(new ForwardStepper());

		BoardWalker walker = new BoardWalker(
			state,
			stepper, 
			new BoardEvaluator());

		Date startTime = new Date();
		ArrayList solution = walker.solve();
		Date endTime = new Date();
		Object results[] = {
			new Integer(solution.size()),
			new Long(endTime.getTime() - startTime.getTime())
		};
		String message = MessageFormat.format(
			"Solution of {0} steps completed in {1} milliseconds.",
			results);
		System.out.println(message);
	}

	private static void countAllBoards()
	{
		FujiDriver.runUniqueBoards(new NullBoardListener());
	}
}
