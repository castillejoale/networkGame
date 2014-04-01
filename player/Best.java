/* Best.java */

package player; 

public class Best { 
	private Move move;
	private double score; 
	
	/** 
	* The Best class stores a move and its corresponding score 
	* The score is a double value assigned by the evaluation function 
	* The move is a valid move that can be placed on the gameboard at play
	**/
	
	protected Best() { 
	}
	
	/**
	 * setMove sets the move 
	 * 
	 * @param m is a move. It needs to be a valid move for the current gameboard and player
	 */
	protected void setMove(Move m){
		this.move = m; 
	}
	
	/**
	 * getMove() gets the Move stored in the Best 
	 * @return the move
	 */
	protected Move getMove(){ 
		return this.move; 
	}
	
	/**
	 * setScore() sets the score associated with the move 
	 * @param s is a double score assigned by the evaluation function
	 */
	protected void setScore(double s){
		this.score = s;
	}
	
	/**
	 * getScore() gets the score assigned to the move
	 * @return a double value that represents the score of a move 
	 */
	protected double getScore(){
		return this.score;
	}
}
	