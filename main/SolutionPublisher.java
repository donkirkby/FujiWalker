/*
 * Created on Jun 6, 2005
 *
 */
package main;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;

/**
 * @author dkirkby
 *
 */
public class SolutionPublisher extends SolutionWriter {
	/* (non-Javadoc)
	 * @see main.SolutionWriter#writeBoard(java.util.ArrayList, java.io.Writer)
	 */
	protected void writeBoard(
			ArrayList coins, 
			Writer solutionsFile)
			throws IOException {
		
		solutionsFile.write("A");
		for (int i=2; i < coins.size()-2; i+=2)
		{
			solutionsFile.write(coins.get(i).toString());
		}
		solutionsFile.write("M\r\nC");
		for (int i=3; i < coins.size()-2; i+=2)
		{
			solutionsFile.write(coins.get(i).toString());
		}
		solutionsFile.write("S\r\n");
	}
	
	/* (non-Javadoc)
	 * @see main.SolutionWriter#writeSteps(java.util.ArrayList, java.util.ArrayList, java.io.Writer)
	 */
	protected void writeSteps(
			ArrayList coins, 
			ArrayList solution,
			Writer solutionsFile) throws IOException {
		
		final String priestCodes = "ACMS";
		StringWriter moveLine = new StringWriter();
		
		for (int i=1; i < solution.size(); i++)
		{
			StringWriter moveText = new StringWriter();
			BoardState curr = (BoardState)solution.get(i);
			BoardState prev = (BoardState)curr.getPrevious();
			for (
					int priestNum=0; 
					priestNum < BoardState.NUM_PRIESTS; 
					priestNum++)
			{
				int currPos = curr.getPriestPosition(priestNum);
				int prevPos = prev.getPriestPosition(priestNum);
				if (currPos != prevPos)
				{
					moveText.append(
							priestCodes.charAt(priestNum));
					int move = (currPos - prevPos);
					if (move == 1)
					{
						moveText.write("D");
					}
					else if (move == -1)
					{
						moveText.write("U");
					}
					else
					{
						String direction;
						int steps, jumps;
						if (move > 0)
						{
							direction = "R";
							steps = 
								((Integer)coins.get(currPos)).intValue();
							jumps = move/2 - steps;
						}
						else
						{
							direction = "L";
							steps =
								((Integer)coins.get(currPos)).intValue();
							jumps = -move/2 - steps;
						}
						moveText.write(direction);
						if (prevPos < 12 || 15 < prevPos)
						{
							// Didn't start on peak, so write distance.
							moveText.write(new Integer(steps).toString());
							if (jumps > 0)
							{
								moveText.write(
										"(+"
										+ new Integer(jumps).toString()
										+ ")");
							}
						}
					}
				}
			}
			if (moveLine.getBuffer().length() 
					+ moveText.getBuffer().length() > 75)
			{
				solutionsFile.write(
						moveLine.toString() + "\r\n");
				moveLine = new StringWriter();
			}
			if (moveLine.getBuffer().length() > 0)
				moveLine.write(", ");
			moveLine.write(moveText.toString());
		}
		solutionsFile.write(
				moveLine.toString() + "\r\n");
	}
}
