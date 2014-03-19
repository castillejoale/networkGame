/* GameBoard.java */

/** This class will hold our Machine Player's own copy of the game board so that it can perform a game tree search and evaluation function
* algorithms.  It is a two dimensional array of Squares, which store whether a square in the game board is occupied with a black or white
* chip, or unoccupied.
**/

package player;

import list.*;

public class GameBoard {

	protected Square[][] board;
	protected MachinePlayer machinePlayer;

/**
* The Gameboard class constructor creates an 8x8 Gameboard of unoccupied Squares such that each Square s.getColor() = -1.
* This Gameboard is created at the beginning of a game in the MachinePlayer class and updated after each player makes a move. 
* @param none
* @return a two dimensional array of Squares
**/
protected GameBoard(MachinePlayer machinePlayer) {

	this.board = new Square[8][8];
	for (int i = 0; i < 8; i++) {
		for (int j = 0; j < 8; j++) {
			board[i][j] = new Square(-1, i, j); // initializes board to empty
		}
	}
	this.machinePlayer = machinePlayer;
}

/**
* isValidMove(Move m) dermines whether a Move m whose color field is -1 (unoccupied) is a valid Move.
* This method uses four helper methods: hasChipsLeft(), isInBound Move m s), isOccupied Move m s), and formsCluster Move m s).
* This method is in the Gameboard class.
* @param Move m
* @return boolean - true if the Move m could hold a valid Move, or false otherwise
**/
protected boolean isValidMove(Move m, int sidecolor) {

  if (m.moveKind == Move.ADD) { 
  	if (hasChipsLeft() && isInBound(m) && !isOccupied(m) && !formsCluster(m, sidecolor)) {
  		return true;
  	}
  }
  if (m.moveKind == Move.STEP) {
  	if (!hasChipsLeft() && isInBound(m) && !isOccupied(m) && !formsCluster(m, sidecolor)) {
  		return true;
  	}
  }
  System.out.println("Move is invalid!");
  return false;

  
}

/**
* hasChipsLeft() is a helper function of isValidMove()
* For an ADD Move to be valid, this must be true.
* For a STEP Move to be valid, this must be false.
* This method is in the Gameboard class.
* @param none -- uses information from "this" Gameboard
* @return boolean - true if there are not 10 Move ms with color field equal to either 0 or 1 (black or white), false otherwise
**/
protected boolean hasChipsLeft() {

  int chips = 0; // initialize count

  for (int i = 0; i < 8; i++) {
    for (int j = 0; j < 8; j++) {
      if (board[i][j].getColor() == machinePlayer.machinePlayerColor) {
        chips++; // counting the number of chips of the MachinePlayer's color
      }
    }
  }
  if (chips < 10) {
    return true;
  } 

  	return false;
}

/**
* isInBound(Move m) is a helper function of isValidMove()
* For a Move to be valid, this method must return true.
* This method is in the Gameboard class.
* @param Move m
* @return boolean - true if that Move m is within the boundaries of the Gameboard: within the 8x8 range, not on a corner,
*                   and not on an opponent's goal area.
**/
protected boolean isInBound(Move m) {

  if (m.x1 < 0 || m.x1 > 7 || m.y1 < 0 || m.y1 > 7) {
  	System.out.println("The move is not within the 8x8 board!");
    return false; // not on the 8x8 board
  }
  if ((m.x1 == 0 && m.y1 == 0) || (m.x1 == 7 && m.y1 == 0) || (m.x1 == 0 && m.y1 == 7) || (m.x1 == 7 && m.y1 == 7)) {
    System.out.println("A chip cannot be placed on the corners of the board!");
    return false; // no corners
  } 
  if (machinePlayer.machinePlayerColor == 0) {
    if (m.x1 == 0 || m.x1 == 7) {
      System.out.println("You cannot place a chip in your opponent's goal!");
      return false; // black cannot be in white's goal
    }  
  }
  if (machinePlayer.machinePlayerColor == 1) {
    if (m.y1 == 0 || m.y1 == 7) {
      System.out.println("You cannot place a chip in your opponent's goal!");
      return false; // white cannot be in black's goal
    }
  }
  return true; // if all if statements are false, the Move is in bounds
}

/**
* isOccupied(Move m) is a helper function of isValidMove()
* For a Move to be valid, this method must return false.
* This method is in the Gameboard class.
* @param Move m
* @return boolean - true if the color field does not equal -1 (it is occupied), false otherwise
**/
protected boolean isOccupied(Move m) {

  if (board[m.x1][m.y1].getColor() == -1) {
    System.out.println("Square is occupied!");
    return false;
  }

  return true;

}

/**
* formsCluster(Move m) is a helper function of isValidMove()
* Refer to the readme for project 2 to understand what a cluster is.
* For a Move to be valid, this must be false.
* This method is in the Gameboard class.
* @param Move m, int sidecolor, either machinePlayer.machinePlayerColor or machinePlayer.opponentColor
* @return boolean - true if a cluster is formed (would result in isValidMove returning false), false otherwise
**/
protected boolean formsCluster(Move m, int sidecolor) {

	if (m.moveKind == Move.ADD) {
		int i = m.x1;
		int j = m.y1;
		int surroundingchips = 1; // initialize number of surrounding chips, which includes itself (hence the 1)

		if (i == 0 && j!=0 && j!=7) { // west edge case
			for (int iloop = i; iloop <= i+1; iloop++) {
				for (int jloop = j-1; jloop <= j+1; jloop++) {
					if (board[iloop][jloop].getColor() == sidecolor) {
						surroundingchips++;
					}
				}
			}
		} else if (i == 7 && j!=0 && j!=7) { // east edge case
			for (int iloop = i-1; iloop <= i; iloop++) {
				for (int jloop = j-1; jloop <= j+1; jloop++) {
					if (board[iloop][jloop].getColor() == sidecolor) {
						surroundingchips++;
					}
				}
			}
		} else if (j == 0 && i!=0 && i!=7) { // north edge case
			for (int iloop = i-1; iloop <= i+1; iloop++) {
				for (int jloop = j; jloop <= j+1; jloop++) {
					if (board[iloop][jloop].getColor() == sidecolor) {
						surroundingchips++;
					}
				}
			}
		} else if (j == 7 && i!=0 && i!=7) { // south edge case
			for (int iloop = i-1; iloop <= i+1; iloop++) {
				for (int jloop = j-1; jloop <= j; jloop++) {
					if (board[iloop][jloop].getColor() == sidecolor) {
						surroundingchips++;
					}
				}
			}
		} else {
			for (int iloop = i-1; iloop <= i+1; iloop++) {
				for (int jloop = j-1; jloop <= j+1; jloop++) {
					if (board[iloop][jloop].getColor() == sidecolor) {
						surroundingchips++;
					}
				}
			}

		}
		if (surroundingchips < 3) {
			return false; // doesn't form cluster --> valid Move
		}

		System.out.println("This move would form a cluster!");
		return true;

	}


	if (m.moveKind == Move.STEP) {

		board[m.x2][m.y2].setColor(-1); // take away old move, then repeat ADD step algorithm

		int i = m.x1;
		int j = m.y1;
		int surroundingchips = 1; // initialize number of surrounding chips, which includes itself (hence the 1)

		if (i == 0 && j!=0 && j!=7) { // west edge case
			for (int iloop = i; iloop <= i+1; iloop++) {
				for (int jloop = j-1; jloop <= j+1; jloop++) {
					if (board[iloop][jloop].getColor() == sidecolor) {
						surroundingchips++;
					}
				}
			}
		} else if (i == 7 && j!=0 && j!=7) { // east edge case
			for (int iloop = i-1; iloop <= i; iloop++) {
				for (int jloop = j-1; jloop <= j+1; jloop++) {
					if (board[iloop][jloop].getColor() == sidecolor) {
						surroundingchips++;
					}
				}
			}
		} else if (j == 0 && i!=0 && i!=7) { // north edge case
			for (int iloop = i-1; iloop <= i+1; iloop++) {
				for (int jloop = j; jloop <= j+1; jloop++) {
					if (board[iloop][jloop].getColor() == sidecolor) {
						surroundingchips++;
					}
				}
			}
		} else if (j == 7 && i!=0 && i!=7) { // south edge case
			for (int iloop = i-1; iloop <= i+1; iloop++) {
				for (int jloop = j-1; jloop <= j; jloop++) {
					if (board[iloop][jloop].getColor() == sidecolor) {
						surroundingchips++;
					}
				}
			}
		} else {
			for (int iloop = i-1; iloop <= i+1; iloop++) {
				for (int jloop = j-1; jloop <= j+1; jloop++) {
					if (board[iloop][jloop].getColor() == sidecolor) {
						surroundingchips++;
					}
				}
			}

		}

		if (surroundingchips < 3) {
			return false; // doesn't form cluster --> valid Move
		}

		System.out.println("This move would form a cluster!");
		return true;


	}

	System.out.println("NOT AN ADEQUATE MOVE!!");
	return true;	

}

/**
* updateGameBoard(Move m) updates the GameBoard with a new Move, as long as this Move is valid.
* @param Move m, int sidecolor, WHICH CAN ONLY SAY ONE OF TWO THINGS: either machinePlayer.machinePlayerColor or machinePlayer.opponentColor
* @return nothing -- the GameBoard is updated
**/
protected void updateGameBoard(Move m, int sidecolor) {

	if (isValidMove(m, sidecolor)) {
		if (m.moveKind == Move.ADD) {
			board[m.x1][m.y1].setColor(sidecolor);
		}
		if (m.moveKind == Move.STEP) {
			board[m.x2][m.y2].setColor(-1);
			board[m.x1][m.y1].setColor(sidecolor);
		}
	}
}

/**
* allValidMoves() returns a SquareList of all Squares s such that isValidMove(Move m) returns true.
* This method is in the Gameboard class.
* @param sidecolor -- MUST BE ONE OF THE FOLLOWING TWO CHOICES: either machinePlayer.machinePlayerColor or machinePlayer.opponentColor 
* @return a DList of all Moves m such that isValidMove(Move m) returns true.
**/
protected DList allValidMoves(int sidecolor) {

	DList validMovesList = new DList();

	for (int i = 0; i < 8; i++) {
		for (int j = 0; j < 8; j++) {
			if (isValidMove(new Move(i,j),sidecolor)) {
				validMovesList.insertBack(new Move(i,j));
			}
		}
	}

	return validMovesList;
}

/** chipConnections() is a helper method of evaluateBoard() that creates a DList of connections for a given occupied (single) Square
* with other occupied Squares.
* @param int i, int j, the (i,j) values from the GameBoard that point to a specific chip
* @return DList 
**/
protected DList chipConnections(int i, int j) {
	// Alejandro's code goes here
	return new DList();
}



//FUTURE chipConnections METHOD

/*
protected DList chipConnections(Square s){
	//Check the color of the Square, reject if it is empty


	//Check 8 methods

}
*/





//8 FUTURE HELPER METHODS FOR chipConnections

/*

private DListNode leftHorizontalConnection(Square s){

}

private DListNode leftTopDiagonalConnection(Square s){

}

private DListNode topVerticalConnection(Square s){

}

private DListNode rightTopDiagonalConnection(Square s){

}

private DListNode rightHorizontalConnection(Square s){

}

private DListNode rightBottomDiagonalConnection(Square s){

}

private DListNode bottomVerticalConnection(Square s){

}

private DListNode leftBottomDiagonalConnection(Square s){

}

*/









/**
* evaluateBoard() is a helper method of alphaBetaPruning() that assigns scores to each board in the game tree search,
* following our evaluation algorithm.
* This method is in the Gameboard class.
* This method calls chipConnections(), in our chip connection module, and hasValidNetwork(), in our valid network module.
* @param the instance of our Gameboard, the depth level of our game tree search (an int)
* @return a score for the board (a double)
**/
protected double evaluateBoard(GameBoard board, int depth) {
	// Alejandro's code goes here
	return 0;
}

/**
*  hasValidNetwork() determines whether "this" GameBoard has a valid network
*  for player "side".  (Does not check whether the opponent has a network.)
*  A full description of what constitutes a valid network appears in the
*  project "readme" file.
*
*  Unusual conditions:
*    If side is neither the MachinePlayer nor the opponent,
*          returns false.
*    If GameBoard squares contain illegal values, the behavior of this
*          method is undefined (i.e., don't expect any reasonable behavior).
*
*  @param int side -- EITHER machinePlayerColor or opponentColor
*  @return true if player "side" has a winning network in "this" GameBoard;
*          false otherwise.
**/
  protected boolean hasValidNetwork(int sidecolor) {
  	// Alejandro's code goes here

// PSEUDOCODE MADE BY SHIR AND ALEJANDRO

/*

public DList<Square> ChipConnection(Square s){
} 

public Boolean hasNetwork(int sidecolor){
//Parameter tells us if we are checking for a white connection or black connection 

//initialize a Dlist of Squares for the begining line
DList beginingList = new DList;
result = false; 
this.depthCounter = 0; //Make a depthCounter field protected (non-static) 

if(sidecolor = white){ 
for(j = 1; j< 7; j++){
if board[0][j].color() = sidecolor{
beginingList.insert(board[0][j]); 
}else{
continue;
} 
} 
if else(sidecolor =black){ 
for(i = 1; i< 7; j++){
if board[i][0].color() = sidecolor{
beginingList.insert(board[i][0]); 
}else{
continue;
} 
} 

if beginingList.size() ==0{
return false; //we have no chips in the begining row 

for(square in beginingList SQ){ 
this.depthCounter = 1;
SQ.setVisited =true;
if(explore(SQ,) == true){
result = true
return result;   
}else{
SQ.setVisited = false;
continue;
}
} 

return result //if we reach this code it means we went through the whole for loop of begining list and found no network so it will return false 

private Boolean explore(Square s){
this.depthCounter++;
s.setVisited =true;
If base case{
return true
}	
else not base case{
DLIst<Square> continueList = new chipConnection(s) 
for( SQUARE each square in continueList){
if explore(SQUARE) == true
return true; 
}else if explore(SQUARE) == false	
depthCounter--
SQUARE.setVisited = false; 
continue; 


*/

  	return false;




  }



}

