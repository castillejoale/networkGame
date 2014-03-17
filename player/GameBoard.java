/* GameBoard.java */

/** This class will hold our Machine Player's own copy of the game board so that it can perform a game tree search and evaluation function
* algorithms.  It is a two dimensional array of Squares, which store whether a square in the game board is occupied with a black or white
* chip, or unoccupied.
**/

package player;

protected class GameBoard {

	protected Square[][] board;

/**
* The Gameboard class constructor creates an 8x8 Gameboard of unoccupied Squares such that each Square s.getColor() = -1.
* This Gameboard is created at the beginning of a game in the MachinePlayer class and updated after each player makes a move. 
* @param none
* @return a two dimensional array of Squares
**/
protected GameBoard() {

	this.board = new Square[8][8];
	for (i = 0; i < 8; i++) {
		for (j = 0; j < 8; j++) {
			board[i][j] = new Square(-1); // initializes board to empty
		}
	}
}

/**
* isValidMove(Move m) dermines whether a Move m whose color field is -1 (unoccupied) is a valid Move.
* This method uses four helper methods: hasChipsLeft(), isInBound Move m s), isOccupied Move m s), and formsCluster Move m s).
* This method is in the Gameboard class.
* @param Move m
* @return boolean - true if the Move m could hold a valid Move, or false otherwise
**/
protected boolean isValidMove(Move m) {

  if (m.moveKind == ADD) { 
  	if (hasChipsLeft() && isInBound(m) && !isOccupied(m) && !formsCluster(m)) {
  		return true;
  	}
  }
  if (m.moveKind == STEP) {
  	if (!hasChipsLeft() && isInBound(m) && !isOccupied(m) && !formsCluster(m)) {
  		return true;
  	}
  }
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

  for (i = 0; i < 8; i++) {
    for (j = 0; j < 8; j++) {
      if (board[i][j].getColor() == color) {
        chips++; // counting the number of chips of the MachinePlayer's color
      }
    }
  }
  if (chips < 10) {
    return true;
  }
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
    return false; // not on the 8x8 board
  }
  if ((m.x1 == 0 && m.y1 == 0) || (m.x1 == 7 && m.y1 == 0) || (m.x1 == 0 && m.y1 == 7) || (m.x1 == 7 && m.y1 == 7)) {
    return false; // no corners
  } 
  if (color == 0) {
    if (m.x1 == 0 || m.x1 == 7) {
      return false; // black cannot be in white's goal
    }  
  }
  if (color == 1) {
    if (m.y1 == 0 || m.y1 == 7) {
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

  if (board[m.x1][m.y1].getColor == -1) {
    return false;
  }

}

/**
* formsCluster(Move m) is a helper function of isValidMove()
* Refer to the readme for project 2 to understand what a cluster is.
* For a Move to be valid, this must be false.
* This method is in the Gameboard class.
* @param Move m
* @return boolean - true if a cluster is formed (would result in isValidMove returning false), false otherwise
**/
protected boolean formsCluster(Move m) {

	if (m.moveKind == ADD) {
		int i = m.x1;
		int j = m.y1;
		int surroundingchips = 1; // initialize number of surrounding chips, which includes itself (hence the 1)

		if (i == 0 && j!=0 && j!=7) { // west edge case
			for (int iloop = i; iloop <= i+1; iloop++) {
				for (int jloop = j-1; jloop <= j+1; jloop++) {
					if (board[iloop][jloop].getColor() == machinePlayerColor) {
						surroundingchips++;
					}
				}
			}
		} else if (i == 7 && j!=0 && j!=7) { // east edge case
			for (int iloop = i-1; iloop <= i; iloop++) {
				for (int jloop = j-1; jloop <= j+1; jloop++) {
					if (board[iloop][jloop].getColor == machinePlayerColor) {
						surroundingchips++;
					}
				}
			}
		} else if (j == 0 && i!=0 && i!=7) { // north edge case
			for (int iloop = i-1; iloop <= i+1; iloop++) {
				for (int jloop = j; jloop <= j+1; jloop++) {
					if (board[iloop][jloop].getColor == machinePlayerColor) {
						surroundingchips++;
					}
				}
			}
		} else if (j == 7 && i!=0 && i!=7) { // south edge case
			for (int iloop = i-1; iloop <= i+1; iloop++) {
				for (int jloop = j-1; jloop <= j; jloop++) {
					if (board[iloop][jloop].getColor == machinePlayerColor) {
						surroundingchips++;
					}
				}
			}
		} else {
			for (int iloop = i-1; iloop <= i+1; iloop++) {
				for (int jloop = j-1; jloop <= j+1; jloop++) {
					if (board[iloop][jloop].getColor == machinePlayerColor) {
						surroundingchips++;
					}
				}
			}

		}
		if (surroundingchips < 3) {
			return false; // doesn't form cluster --> valid Move
		}

	}
	if (m.moveKind == STEP) {

		board[m.x2][m.y2].setColor(-1); // take away old move, then repeat ADD step algorithm

		int i = m.x1;
		int j = m.y1;
		int surroundingchips = 1; // initialize number of surrounding chips, which includes itself (hence the 1)

		if (i == 0 && j!=0 && j!=7) { // west edge case
			for (int iloop = i; iloop <= i+1; iloop++) {
				for (int jloop = j-1; jloop <= j+1; jloop++) {
					if (board[iloop][jloop].getColor() == machinePlayerColor) {
						surroundingchips++;
					}
				}
			}
		} else if (i == 7 && j!=0 && j!=7) { // east edge case
			for (int iloop = i-1; iloop <= i; iloop++) {
				for (int jloop = j-1; jloop <= j+1; jloop++) {
					if (board[iloop][jloop].getColor == machinePlayerColor) {
						surroundingchips++;
					}
				}
			}
		} else if (j == 0 && i!=0 && i!=7) { // north edge case
			for (int iloop = i-1; iloop <= i+1; iloop++) {
				for (int jloop = j; jloop <= j+1; jloop++) {
					if (board[iloop][jloop].getColor == machinePlayerColor) {
						surroundingchips++;
					}
				}
			}
		} else if (j == 7 && i!=0 && i!=7) { // south edge case
			for (int iloop = i-1; iloop <= i+1; iloop++) {
				for (int jloop = j-1; jloop <= j; jloop++) {
					if (board[iloop][jloop].getColor == machinePlayerColor) {
						surroundingchips++;
					}
				}
			}
		} else {
			for (int iloop = i-1; iloop <= i+1; iloop++) {
				for (int jloop = j-1; jloop <= j+1; jloop++) {
					if (board[iloop][jloop].getColor == machinePlayerColor) {
						surroundingchips++;
					}
				}
			}

		}
		if (surroundingchips < 3) {
			return false; // doesn't form cluster --> valid Move
		}


	}	

}

/**
* updateGameBoard(Move m) updates the GameBoard with a new Move, as long as this Move is valid.
* @param Move m, int sidecolor, WHICH CAN ONLY SAY ONE OF TWO THINGS: either machinePlayerColor or opponentColor
* @return nothing -- the GameBoard is updated
**/
protected updateGameBoard(Move m, int sidecolor) {

	if (isValidMove(m)) {
		if (m.moveKind == ADD) {
			board[m.x1][m.y1].setColor(sidecolor);
		}
		if (m.moveKind == STEP) {
			board[m.x2][m.y2].setColor(-1);
			board[m.x1][m.y1].setColor(sidecolor);
		}
	}
}

/**
* allValidMoves() returns a SquareList of all Squares s such that isValidMove(Square s) returns true.
* This method is in the Gameboard class.
* @param sidecolor -- MUST BE ONE OF THE FOLLOWING TWO CHOICES: either machinePlayerColor or opponentColor 
* @return a DList of all Moves m such that isValidMove(Move m) returns true.
**/
protected DList<T> allValidMoves(int sidecolor) {

	DList<Move> validMovesList = new DList<Move>;

	for (int i = 0; i < 8; i++) {
		for (int j = 0; j < 8; j++) {
			if (isValidMove(new Move(i,j))) {
				validMovesList.insertBack(new Move(i,j));
			}
		}
	}
}

/** chipConnections() is a helper method of evaluateBoard() that creates a DList of connections for a given occupied (single) Square
* with other occupied Squares.
* @param int i, int j, the (i,j) values from the GameBoard that point to a specific chip
* @return DList 
**/
protected DList<T> chipConnections(int i, int j) {
	// Alejandro's code goes here
}


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
  }



}

