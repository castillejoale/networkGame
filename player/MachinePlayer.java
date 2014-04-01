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
  protected GameBoard board; 

  /**
   * MachinePlayer(int color, int searchDepth) creates a machine player with a given color 
   * @param color is either 0 (black) or 1 (white) where white has the first move
   **/
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


  /**
   * MachinePlayer(int color, int searchDepth) creates a machine player with a given color and search depth 
   * @param color is either 0 (black) or 1 (white) where white has the first move; searchDepth limits the search in minimax algorithm 
   **/
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

  /**
   * chooseMove() does a game tree search with alpha-beta pruning on the current Machine Player's game board.
   * This method calls alphaBetaPruning method on the top node (gameBoard) of the tree that performs minimax.
   * If no "best" move is found, a valid move is returned to be played. 
   * @param none
   * @return a new valid move by "this" player.  Internally records the move (updates the internal game board) as a move by "this" player.
   **/
  
  public Move chooseMove() {
		Best bestMove = alphaBetaPruning(true, -1, 1, this.board, 0); 

	    if (board.isValidMove(bestMove.getMove(),this.machinePlayerColor)) {
	    	board.updateGameBoard(bestMove.getMove(),this.machinePlayerColor);  
			return bestMove.getMove();
		}else{
			System.out.print("alphabetapruning - chooseMove() returned an invalid move");
			return bestMove.getMove();
			
		}

	}
	  
/**
* alphaBetaPruning() is a helper method of chooseMove() that does a game tree search with alpha-beta pruning.
* @param side, which is true if the player who will place the move is MachinePlayer, opponent otherwise. 
* @param The alpha and beta values for alpha-beta pruning
* @param gb is the game board we are visiting and depthCounter is the depth of this game board node from the root
* @return the Best with the best Move and its evaluation score.
**/
  protected Best alphaBetaPruning(boolean side, double alpha, double beta, GameBoard gb, int depthCounter){
		Best bestMove = new Best(); //this is the move we will return 
		Best opponentReply; //this is the move the Opponent will reply with
		
		
		
		int sideColor =-1; 
		int previousSideColor = -1; 
		if(side == true){ 
			sideColor = this.machinePlayerColor;
			previousSideColor = this.opponentColor;
		}
		if(side ==false){ 
			sideColor = this.opponentColor;
			previousSideColor = this.machinePlayerColor;
		}
		
		
		
		//Base Case 1: if the game board has a network for the other person's turn, we return the Best that contains that score. 
		// A step move may create a network for the opponent by unblocking a connection between two enemy chips.
		if(gb.hasValidNetwork(sideColor)){ 
			bestMove.setScore(gb.evaluateBoard(depthCounter, sideColor)); 
			return bestMove; 
		}
		
		//Base Case 2: if the game board has a network, we return the Best that contains that score. 
		//Due to the way the alphaBetaPruning method is called, we check the hasNetwork method on the previous turn's color. 
		if(gb.hasValidNetwork(previousSideColor)){ 
			bestMove.setScore(gb.evaluateBoard(depthCounter, previousSideColor)); 
			return bestMove; 
		}
		
		
		//Base Case 3: if we reached the end of depth limit we return the Best that contains that score.
		if(depthCounter == this.searchDepth){
			bestMove.setScore(gb.evaluateBoard(depthCounter, previousSideColor));
			return bestMove; 
		}
		
		
		if(side == true) {
			bestMove.setScore(alpha); 
		}if(side == false) { 
			bestMove.setScore(beta);
		}
		
		DList ValidMovesList = gb.allValidMoves(sideColor); //generate a list of all the valid moves that the current side player can make
		ListNode pointer = ValidMovesList.front();
		
		try{
		bestMove.setMove((Move) pointer.item()); //we set bestMove to be the first valid move of the list
		
		for(int i=0; i<ValidMovesList.length(); i++){ 
			Move m = (Move) pointer.item();
			
			GameBoard updatedBoard = new GameBoard(this); 
			for (int x = 0; x < 8; x++) {
				for (int y = 0; y < 8; y++) {
					updatedBoard.board[x][y].setColor(gb.board[x][y].getColor());
					updatedBoard.board[x][y].setType(gb.board[x][y].getType());
					updatedBoard.board[x][y].setVisited(gb.board[x][y].getVisited());
					
					}
				}
			updatedBoard.updateGameBoard(m,sideColor); //Create a new child game board with the selected valid move 
			
			
			depthCounter++; //increment the depthCounter before we do another alphaBetaPrunning for the next recrusive call.
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
