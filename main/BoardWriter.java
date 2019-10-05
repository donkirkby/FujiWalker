package main;

import java.util.ArrayList;

/**
 * @author Don.Kirkby
 *
 * Purpose: to write out the selected combination to the console.
 */
public class BoardWriter
implements IBoardListener 
{
	
	public void receiveBoard(ArrayList coins) {
		for(int i = 0; i < coins.size(); i++)
		{
			if (i > 0)
				System.out.print(", ");
			System.out.print(coins.get(i));
		}
		System.out.println("");
	}
}
