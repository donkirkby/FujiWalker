/*
 * Created on 29-May-2005
 *
 */
package main;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author DKirkby
 *
 * Writes a solution to standard output.
 */
public class SolutionWriter {

	/**
	 * @param coins
	 * @param solution
	 */
	public void writeSolution(ArrayList coins, ArrayList solution) {
		try
		{
			System.out.println("Solution "
					+ (new Date()).toString());
			
			Writer solutionsFile = getWriter();
			try
			{
				solutionsFile.write(
						(new Date()).toString()
						+ "\r\n");
				
				writeBoard(coins, solutionsFile);
				writeSteps(coins, solution, solutionsFile);

				long offset = 0;//Math.round(Math.random()*100);
				solutionsFile.write(
						"previous solution is "
						+ Long.toString(solution.size()-1+offset)
						+ " - "
						+ Long.toString(offset)
						+ " moves\r\n\r\n");
			}
			finally
			{
				solutionsFile.close();
			}
		}
		catch (IOException ex)
		{
			System.out.println(ex.getMessage());
		}
	}

	/**
	 * @param coins
	 * @param solution
	 * @param solutionsFile
	 * @throws IOException
	 */
	protected void writeSteps(ArrayList coins, ArrayList solution, Writer solutionsFile) throws IOException {
		for (int i = 0; i < solution.size(); i ++)
		{
			solutionsFile.write(solution.get(i).toString());
			solutionsFile.write("\r\n");
		}
	}

	/**
	 * @param coins
	 * @param solutionsFile
	 * @throws IOException
	 */
	protected void writeBoard(ArrayList coins, Writer solutionsFile) throws IOException {
		for (int i = 0; i < coins.size(); i++)
		{
			if (i > 0)
				solutionsFile.write(",");
			solutionsFile.write(String.valueOf(
					coins.get(i)));
		}
		solutionsFile.write("\r\n");
	}

	/**
	 * @return
	 * @throws IOException
	 */
	private Writer getWriter() throws IOException {
		return new FileWriter("solutions.txt", true);
	}

}
