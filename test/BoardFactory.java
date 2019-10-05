package test;
import java.util.ArrayList;

/*
 * Created on Jul 12, 2004
 *
 */

/**
 * @author dkirkby
 *
 * created: Jul 12, 2004 
 */
public class BoardFactory
{

	public static ArrayList createBoard(String[] board) {
		ArrayList coins = new ArrayList();
		for (int i = 0; i < 14; i++)
		{
			coins.add(new Integer(board[0].substring(i, i+1)));
			coins.add(new Integer(board[1].substring(i, i+1)));
		}
		return coins;
	}
}
