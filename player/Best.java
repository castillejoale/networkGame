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
	
	protected void setMove(Move m){
		this.move = m; 
	}
	
	protected Move getMove(){ 
		return this.move; 
	}
	
	protected void setScore(double s){
		this.score = s;
	}
	
	protected double getScore(){
		return this.score;
	}
}
	