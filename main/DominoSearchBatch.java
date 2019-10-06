package main;

import java.util.ArrayList;

public class DominoSearchBatch extends SearchBatch {
	protected String getDefaultCoins() {
		return "00010203040512131415232425343545";
	}
	
	/**
	 * @param board
	 */
	protected void swapCoins(ArrayList board) {
		int domino1 = m_generator.nextInt(15);
		int domino2 = m_generator.nextInt(15);
		int flip1 = m_generator.nextInt(2);
		int flip2 = m_generator.nextInt(2);
		
		swapChosenCoins(board, domino1*2+2+flip1, domino2*2+2+flip2);
		swapChosenCoins(board, domino1*2+3-flip1, domino2*2+3-flip2);
	}
	
	private void swapChosenCoins(ArrayList board, int choice1, int choice2) {
		Object temp = board.get(choice1);
		board.set(choice1, board.get(choice2));
		board.set(choice2, temp);
	}
}
