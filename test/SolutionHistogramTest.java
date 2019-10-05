package test;
import junit.framework.TestCase;
import java.util.*;

import main.SolutionHistogram;

/*
 * Created on Jul 29, 2004
 *
 */

/**
 * @author DKirkby
 *
 * created: Jul 29, 2004 
 */
public class SolutionHistogramTest extends TestCase
{

	/**
	 * Constructor for SolutionHistogramTest.
	 * @param arg0
	 */
	public SolutionHistogramTest(String arg0)
	{
		super(arg0);
	}

	public void testCounting()
	{
		ArrayList solution = new ArrayList();
		
		SolutionHistogram counter = new SolutionHistogram();
		counter.receiveSolution(solution);
		solution.add("a");
		counter.receiveSolution(solution);
		counter.receiveSolution(solution);
		solution.add("b");
		solution.add("c");
		counter.receiveSolution(solution);
		assertEquals("1,2,0,1", counter.toString());
	}

	public void testCounting2()
	{
		ArrayList solution = new ArrayList();
		
		SolutionHistogram counter = new SolutionHistogram();
		counter.receiveSolution(solution);
		solution.add("a");
		counter.receiveSolution(solution);
		solution.add("c");
		counter.receiveSolution(solution);
		assertEquals("1,1,1", counter.toString());
	}
}
