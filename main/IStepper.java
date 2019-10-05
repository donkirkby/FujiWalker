package main;
import java.util.*;

/*
 * Created on Jul 9, 2004
 *
 */

/**
 * @author dkirkby
 *
 * created: Jul 9, 2004 
 */
public interface IStepper
{
	ArrayList getPossibleStates(IState current);
}
