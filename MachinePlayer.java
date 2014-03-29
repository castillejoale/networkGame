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
  * chooseMove() is a helper method of chooseMove() that does a game tree search with alpha-beta pruning.
  * This method is in the MachinePlayer class.
  * This method calls alphaBetaPruning method on the top node (gameBoard) of the tree
  * @param none
  * @return a new move by "this" player.  Internally records the move (updates the internal game board) as a move by "this" player.
  **/
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
* @param side, which is true if player is MachinePlayer, opponent otherwise, and the alpha and beta values for alpha-beta pruning, we also take gb the Gameboard we are branching and depthCounter is the depth of the node in the tree
* @return the Best with the best Move and its evaluation score.
**/
  protected Best alphaBetaPruning(boolean side, double alpha, double beta, GameBoard gb, int depthCounter){
		Best bestMove = new Best(); //this is the move we will return 
		Best opponentReply; //this is the move the Opponent will reply with
		
		
		
		int sidecolor =-1; //correspond the correct sidecolor and side boolean 
		int previousSideColor = -1; //correspond the previous turn with the color opposite of the current turn
		if(side == true){ //if the side is true, it is the machinePlayer's turn
			sidecolor = this.machinePlayerColor;
			previousSideColor = this.opponentColor;
		}
		if(side ==false){ //if the side is false, it is the opponentPlayer's turn 
			sidecolor = this.opponentColor;
			previousSideColor = this.machinePlayerColor;
		}
		
		//Base Case 1: if the game board has a network for the other person's turn, we return the Best that contains that score. 
		// A step move may create a network for the opponent by unblocking a connection between two enemy chips.
		if(gb.hasValidNetwork(sidecolor)){ 
			bestMove.setScore(gb.evaluateBoard(depthCounter, sidecolor)); //will give either -1 or 1 depending on whose side it is
			System.out.println("SideColor has a valid network with a board a depth: " + depthCounter + " and a eval score: "+ gb.evaluateBoard(depthCounter, sidecolor));
			System.out.println("\nWe are evaluating the following board: \n:" + gb.toString());
			return bestMove; 
		}
		
		//Base Case 2: if the game board has a network, we return the Best that contains that score. 
		//Due to the way the alphaBetaPruning method is called, we check the hasNetwork method on the previous turn's color. 
		if(gb.hasValidNetwork(previousSideColor)){ 
			bestMove.setScore(gb.evaluateBoard(depthCounter, previousSideColor)); //will give either -1 or 1 depending on whose side it is
			System.out.println("previousSideColor has a valid network with a board a depth: " + depthCounter + " and a eval score: "+ gb.evaluateBoard(depthCounter, previousSideColor));
			System.out.println("\nWe are evaluating the following board: \n:" + gb.toString());
			return bestMove; 
		}
		
		
		//Base Case 3: if we reached the end of depth limit we return the Best that contains that score.
		if(depthCounter == this.searchDepth){
			bestMove.setScore(gb.evaluateBoard(depthCounter, previousSideColor));
			System.out.println("we reached the max searchDepth of: " + this.searchDepth + " and with a depthCounter (should equal searchDepth) of: " + depthCounter + " and a eval score: "+ gb.evaluateBoard(depthCounter, previousSideColor));
			System.out.println("\nWe are evaluating the following board: \n:" + gb.toString());
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
		
		for(int i=0; i<ValidMovesList.length(); i++){ //for each valid move we can place on the board, we recursively call the alphaBetaPruning method
			
			Move m = (Move) pointer.item();
			
			//Create a new gameboard with the updated move added to it
			GameBoard updatedBoard = new GameBoard(this); 
			for (int x = 0; x < 8; x++) {
				for (int y = 0; y < 8; y++) {
					updatedBoard.board[x][y] = gb.board[x][y];
					}
				}
			updatedBoard.updateGameBoard(m,sidecolor);
			
			
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
