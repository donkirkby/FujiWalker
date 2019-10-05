/*
 * Created on Jun 13, 2005
 *
 */
package main;

import java.util.ArrayList;

/**
 * @author dkirkby
 *
 */
public class MoveGrid implements Cloneable {
	private ArrayList m_priests;
	
	private MoveGrid()
	{
		
	}
	
	public MoveGrid(ArrayList solution){
		m_priests = new ArrayList();
		for (int i=0; i < BoardState.NUM_PRIESTS; i++)
		{
			m_priests.add(new ArrayList());
		}
		
		for (int i=1; i < solution.size(); i++)
		{
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
					ArrayList moves = getMoves(priestNum);
					moves.add(new Integer(currPos));
				}
			}
		}
	}
	
	public int getMove(int priestNum){
		return ((Integer)(getMoves(priestNum).get(0))).intValue();
	}
	
	public void popMove(int priestNum){
		getMoves(priestNum).remove(0);
	}
	
	/**
	 * @param priestNum
	 * @return
	 */
	private ArrayList getMoves(int priestNum) {
		return (ArrayList)m_priests.get(priestNum);
	}

	public boolean isEmpty(){
		for (int i = 0; i < m_priests.size(); i++)
		{
			ArrayList moves = getMoves(i);
			if (moves.size() > 0)
			{
				return false;
			}
		}
		return true;
	}
	
	public boolean hasMove(int priestNum){
		return getMoves(priestNum).size() > 0;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	public Object clone() {
		MoveGrid clone = new MoveGrid();
		clone.m_priests = new ArrayList();
		for (int i=0; i < m_priests.size(); i++)
		{
			ArrayList moves = getMoves(i);
			clone.m_priests.add(moves.clone());
		}
		
		return clone;
	}
}
