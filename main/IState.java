package main;
/*
 * Created on Jul 9, 2004
 *
 */

/**
 * @author dkirkby
 *
 * created: Jul 9, 2004 
 */
public interface IState
{
	// return the state that generated this one, or null if this
	// is a starting state.
	public IState getPrevious();
}
