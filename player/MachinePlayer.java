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
	  Move Best;
	  
	  board.updateGameBoard(Best,this.opponentColor);
      if (Best.moveKind == Move.ADD){
        	board.chipsOnBoard++;
        }
    return new Move();
  } 

/**
* alphaBetaPruning() is a helper method of chooseMove() that does a game tree search with alpha-beta pruning.
* This method is in the MachinePlayer class.
* This method calls evaluateBoard, in our board evaluation module.
* @param side, which is true if player is MachinePlayer, opponent otherwise, and the alpha and beta values for alpha-beta pruning
* @return the best estimated Move
**/
protected Move alphaBetaPruning(boolean side, int alpha, int beta) {
  // Shir's code goes here
  return new Move();
}

  // If the Move m is legal, records the move as a move by the opponent
  // (updates the internal game board) and returns true.  If the move is
  // illegal, returns false without modifying the internal state of "this"
  // player.  This method allows your opponents to inform you of their moves.
  public boolean opponentMove(Move m) {
    if (board.isValidMove(m,this.opponentColor)) {
      board.updateGameBoard(m,this.opponentColor);
      if (m.moveKind == Move.ADD){
        	board.chipsOnBoard++;
        }
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
