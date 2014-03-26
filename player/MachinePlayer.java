/* MachinePlayer.java */

package player;
import list.*;

/**
 *  An implementation of an automatic Network player.  Keeps track of moves
 *  made by both players.  Can select a move for itself.
 */
public class MachinePlayer extends Player {

  protected int machinePlayerColor;
  protected int opponentColor;
  protected int searchDepth;
  protected GameBoard board; // please refer to any GameBoard in MachinePlayer.java as "board".  if there is discrepancy, either change your own code, or consult the group

  // Creates a machine player with the given color.  Color is either 0 (black)
  // or 1 (white).  (White has the first move.)
  public MachinePlayer(int color) {

    this.machinePlayerColor = color;

    if (color == 0) {
      this.opponentColor = 1;
    } else if (color == 1) {
      this.opponentColor = 0;
    } else {
      System.out.println("Color must be either zero or one!");
    }

    searchDepth = 2;

    board = new GameBoard(this);
  }

  // Creates a machine player with the given color and search depth.  Color is
  // either 0 (black) or 1 (white).  (White has the first move.)
  public MachinePlayer(int color, int searchDepth) {

    this.machinePlayerColor = color;

    if (color == 0) {
      this.opponentColor = 1;
    } else if (color == 1) {
      this.opponentColor = 0;
    } else {
      System.out.println("Color must be either zero or one!");
    }

    this.searchDepth = searchDepth;

    board = new GameBoard(this);

  }

  // Returns a new move by "this" player.  Internally records the move (updates
  // the internal game board) as a move by "this" player.
  public Move chooseMove() {
		Best bestMove = alphaBetaPruning(true, -1, 1, this.board, 0); 

	    if (board.isValidMove(bestMove.getMove(),this.machinePlayerColor)) {
	    	board.updateGameBoard(bestMove.getMove(),this.machinePlayerColor); //update the gameboard with the chooseMove() 
			return bestMove.getMove();
		}else{
			System.out.print("alphabetapruning - chooseMove() returned an invalid move");
			System.exit(0);
			return bestMove.getMove();
			
		}

	}
	  
/**
* alphaBetaPruning() is a helper method of chooseMove() that does a game tree search with alpha-beta pruning.
* This method is in the MachinePlayer class.
* This method calls evaluateBoard, in our board evaluation module.
* @param side, which is true if player is MachinePlayer, opponent otherwise, and the alpha and beta values for alpha-beta pruning
* @return the best estimated Move
**/
  protected Best alphaBetaPruning(boolean side, double alpha, double beta, GameBoard gb, int depthCounter){
		Best bestMove = new Best(); //this is the move we will return 
		Best opponentReply; //this is the move the Opponent will reply with
		
		
		//correspond the correct sidecolor and side boolean 
		int sidecolor =-1; 
		if(side == true){ //if the side is true, it is the machinePlayer's turn
			sidecolor = this.machinePlayerColor;
		}
		if(side ==false){ //if the side is false, it is the opponentPlayer's turn 
			sidecolor = this.opponentColor;
		}
		

		
		//Base Case 1: if there the game board has a network, we return the Best that contains that move
		if(gb.hasValidNetwork(0) == true || gb.hasValidNetwork(1)){ 
			bestMove.setScore(gb.evaluateBoard(depthCounter, sidecolor)); //will give either -1 or 1 depending on whose side it is
			return bestMove; 
		}
		
		
		//Base Case 2: if we reached the end of depthLimit we return the move
		if(depthCounter == this.searchDepth){
			bestMove.setScore(gb.evaluateBoard(depthCounter, sidecolor));
			return bestMove; 
		}
		
		
		if(side == true) {
			bestMove.setScore(alpha); 
		}if(side == false) { 
			bestMove.setScore(beta);
		}
		
		DList ValidMovesList = gb.allValidMoves(sidecolor); //generate a list of all the valid moves that the current side can make
		ListNode pointer = ValidMovesList.front();
		
		try{
		bestMove.setMove((Move) pointer.item()); //we set bestMove to be the first valid move of the list
		
		for(int i=0; i<ValidMovesList.length(); i++){ //for each valid move we can place on the board
			
			Move m = (Move) pointer.item();
			
			//Create a new gameboard with the updated move added to it
			GameBoard updatedBoard = new GameBoard(this); 
			for (int x = 0; x < 8; x++) {
				for (int y = 0; y < 8; y++) {
					updatedBoard.board[x][y] = gb.board[x][y];
					}
				}
			updatedBoard.updateGameBoard(m,sidecolor);
			//
			
			depthCounter++; //increment the depthCounter before we do another alphabetaprunning
			
			opponentReply = alphaBetaPruning(!side, alpha, beta, updatedBoard, depthCounter);
			
			if(side == true && opponentReply.getScore() > bestMove.getScore()){
				bestMove.setMove(m); 
				bestMove.setScore(opponentReply.getScore());
				alpha = opponentReply.getScore();
			}else if(side == false && opponentReply.getScore() < bestMove.getScore()){
				bestMove.setMove(m); 
				bestMove.setScore(opponentReply.getScore());
				beta = opponentReply.getScore();
			}
			
			if(alpha >= beta){
				return bestMove;
			}
			depthCounter--; //when we move to next branch, we need to un-increment our depthCounter
			pointer = pointer.next();
		}
		}catch(InvalidNodeException e){
			
		}
		return bestMove;
	}

  // If the Move m is legal, records the move as a move by the opponent
  // (updates the internal game board) and returns true.  If the move is
  // illegal, returns false without modifying the internal state of "this"
  // player.  This method allows your opponents to inform you of their moves.
  public boolean opponentMove(Move m) {
    if (board.isValidMove(m,this.opponentColor)) {
      board.updateGameBoard(m,this.opponentColor);
      return true;
    } else {
    return false;
  }
}

  // If the Move m is legal, records the move as a move by "this" player
  // (updates the internal game board) and returns true.  If the move is
  // illegal, returns false without modifying the internal state of "this"
  // player.  This method is used to help set up "Network problems" for your
  // player to solve.
  public boolean forceMove(Move m) {
    if (board.isValidMove(m,this.machinePlayerColor)) {
      board.updateGameBoard(m,this.machinePlayerColor);
      return true;
    } else {
    return false;
  }
}

}
