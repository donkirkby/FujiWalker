package main;
import java.util.*;

/*
 * Created on Jul 26, 2004
 *
 */

/**
 * @author DKirkby
 *
 * created: Jul 26, 2004 
 */
public interface ISolutionListener
{
	/**
	 * receive the list of moves used to solve a board. This can be called
	 * several times if there is more than one solution to a problem.
	 * @param solution - contains a set of IState objects that follow the
	 * solution sequence.
	 * @return true if more solutions are welcome.
	 */
	public boolean receiveSolution(ArrayList solution);
	
	/**
	 * wrap up analysis of the current problem. This is called after all
	 * relevant solutions have been passed to receiveSolution().
	 * A common pattern is to record a bunch of solutions during calls
	 * to receiveSolution(). Then when finishProblem() is called, pass
	 * any relevant solutions on to the next link in the chain by calling
	 * receiveSolution() for each solution.
	 */
	public void finishProblem();
}
