package main;

/*
 * Created on 10-Feb-2005
 *
 */

/**
 * @author DKirkby
 * This class finds the longest solution.
 */
public class FujiFinder extends FujiDriver {
	
	public static void main(String[] args) {
		runUniqueBoards(new BoardTracker());
	}
}
