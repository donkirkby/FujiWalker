package main;
import java.util.ArrayList;


/*
 * Created on Jun 29, 2004
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
public class BoardFilter implements IBoardListener {
	private IBoardListener m_next;
	
	public BoardFilter(IBoardListener next)
	{
		m_next = next;
	}
	
	/* (non-Javadoc)
	 * @see IBoardListener#receiveBoard(java.util.ArrayList)
	 */
	public void receiveBoard(ArrayList coins) {
		int i = 2;
		int verticalDiff = 0, horizontalDiff = 0, diagonalDiff = 0;
		while (i <= 12 && (verticalDiff == 0 || horizontalDiff == 0
			|| diagonalDiff == 0))
		{
			int identityValue = getIdentity(i, coins);
			if (verticalDiff == 0)
				verticalDiff = 
					identityValue - getVertical(i, coins);
			if (horizontalDiff == 0)
				horizontalDiff = 
					identityValue - getHorizontal(i, coins);
			if (diagonalDiff == 0)
				diagonalDiff = 
					identityValue - getDiagonal(i, coins);
			i+=2;
		}
		if (verticalDiff >= 0 && horizontalDiff >= 0 && diagonalDiff >= 0)
		{
			m_next.receiveBoard(coins);
		}
	}
	
	private int getVertical(int coinNum, ArrayList coins)
	{
		return ((Integer)coins.get(coinNum - 2*(coinNum%2) + 1)).intValue();
	}
	private int getIdentity(int coinNum, ArrayList coins)
	{
		return ((Integer)coins.get(coinNum)).intValue();
	}
	private int getHorizontal(int coinNum, ArrayList coins)
	{
		return ((Integer)coins.get(26 - coinNum + 2*(coinNum%2))).intValue();
	}
	private int getDiagonal(int coinNum, ArrayList coins)
	{
		return ((Integer)coins.get(27 - coinNum)).intValue();
	}
}
