/* GameBoard.java */

/** This class will hold our Machine Player's own copy of the game board so that it can perform a game tree search and evaluation function
* algorithms.  It is a two dimensional array of Squares, which store whether a square in the game board is occupied with a black or white
* chip, or unoccupied.
**/

package player;

import list.*;

public class GameBoard {

	public Square[][] board; //CHANGE THIS TO PROTECTED, IT IS JUST FOR TESTING
	protected MachinePlayer machinePlayer;
	private int depthCounter;

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
			if (i==0 || j == 0) { //Set the squares types of the beginning lines to -1 for, which means begining line.
				board[i][j].setType(-1);
			}
			if (i==7 || j == 7) { //Set the squares types of the ending lines to 1, which means ending line.
				board[i][j].setType(1);
			}
		}
	}
	this.machinePlayer = machinePlayer;
}

/**
* isValidMove(Move m) dermines whether a Move m whose color field is -1 (unoccupied) is a valid Move.
* This method uses four helper methods: hasChipsLeft(), isInBound(), isOccupied(), and formsCluster().
* This method is in the Gameboard class.
* @param Move m, int sidecolor
* @return boolean - true if the Move m could hold a valid Move, or false otherwise
**/
protected boolean isValidMove(Move m, int sidecolor) {

  if (m.moveKind == Move.ADD) { 
  	if (hasChipsLeft(sidecolor) && isInBound(m, sidecolor) && !isOccupied(m) && !formsCluster(m, sidecolor)) {
  		return true;
  	}
  }
  if (m.moveKind == Move.STEP) {
  	if (!hasChipsLeft(sidecolor) && isInBound(m, sidecolor) && !isOccupied(m) && !formsCluster(m, sidecolor)) {
  		return true;
  	}
  }
  System.out.println("Move" + m + "is invalid!");
  return false;

  
}

/**
* hasChipsLeft() is a helper function of isValidMove()
* For an ADD Move to be valid, this must be true.
* For a STEP Move to be valid, this must be false.
* This method is in the Gameboard class.
* @param int sidecolor -- passed in from isValidMove()
* @return boolean - true if there are not 10 Move ms with color field equal to either 0 or 1 (black or white), false otherwise
**/
protected boolean hasChipsLeft(int sidecolor) {

  int chips = 0; // initialize count

  for (int i = 0; i < 8; i++) {
    for (int j = 0; j < 8; j++) {
      if (board[i][j].getColor() == sidecolor) {
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
* @param Move m, int sidecolor -- passed in from isValidMove()
* @return boolean - true if that Move m is within the boundaries of the Gameboard: within the 8x8 range, not on a corner,
*                   and not on an opponent's goal area.
**/
protected boolean isInBound(Move m, int sidecolor) {

  if (m.x1 < 0 || m.x1 > 7 || m.y1 < 0 || m.y1 > 7) {
  	System.out.println("The move is not within the 8x8 board!");
    return false; // not on the 8x8 board
  }
  if ((m.x1 == 0 && m.y1 == 0) || (m.x1 == 7 && m.y1 == 0) || (m.x1 == 0 && m.y1 == 7) || (m.x1 == 7 && m.y1 == 7)) {
    System.out.println("A chip cannot be placed on the corners of the board!");
    return false; // no corners
  } 
  if (sidecolor == 0) {
    if (m.x1 == 0 || m.x1 == 7) {
      System.out.println("You cannot place a chip in your opponent's goal!");
      return false; // black cannot be in white's goal
    }  
  }
  if (sidecolor == 1) {
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
* @param Move m -- passed in from isValidMove()
* @return boolean - true if the color field does not equal -1 (it is occupied), false otherwise
**/
protected boolean isOccupied(Move m) {

  if (board[m.x1][m.y1].getColor() == -1) {
    return false;
  }

  System.out.println("Square is occupied!");

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

boolean returnval = false;

if (m.moveKind == Move.ADD) {
	//System.out.println("formsCluster entered add move conditional and the move is" + m);
	board[m.x1][m.y1].setColor(sidecolor); // put move on board to see if it forms a cluster
	//System.out.println("adding the move [" + m.x1 + "," + m.y1 + "] to the gameboard");
	for (int i = 0; i < 8; i++) {
		for (int j = 0; j < 8; j++) { // loop through the whole board to find chips of color sidecolor
			if (board[i][j].getColor() == sidecolor) { 
				int surroundingchips = 0;
				//System.out.println("looking at [" + i + "," + j + "] and surroundingchips = " + surroundingchips);
				if (i==0 && j!=0 && j!=7) {
					//System.out.println("west edge cluster case add");
					for (int iloop = i; iloop <= i+1; iloop++) {
						for (int jloop = j-1; jloop <= j+1; jloop++) {
							if (board[iloop][jloop].getColor() == sidecolor) { // look at surrounding chips
								//System.out.println("looking at ["+ iloop + "," + jloop + "]");
								surroundingchips++;
								//System.out.println("surrounding chips west edge add: " + surroundingchips);
							}
						}
					}
				} else if (i==7 && j!=0 && j!=7) {
					//System.out.println("east edge cluster case add");
					for (int iloop = i-1; iloop <= i; iloop++) {
						for (int jloop = j-1; jloop <= j+1; jloop++) {
							if (board[iloop][jloop].getColor() == sidecolor) {
								//System.out.println("looking at ["+ iloop + "," + jloop + "]");
								surroundingchips++;
								//System.out.println("surrounding chips east edge add: " + surroundingchips);
							}
						}
					}
				} else if (j == 0 && i!=0 && i!=7) {
					//System.out.println("north edge cluster case add");
					for (int iloop = i-1; iloop <= i+1; iloop++) {
						for (int jloop = j; jloop <= j+1; jloop++) {
							if (board[iloop][jloop].getColor() == sidecolor) {
								//System.out.println("looking at ["+ iloop + "," + jloop + "]");
								surroundingchips++;
								//System.out.println("surrounding chips north edge add: " + surroundingchips);
							}
						}
					}
				} else if (j == 7 && i!=0 && i!=7) {
					//System.out.println("south edge cluster case add");
					for (int iloop = i-1; iloop <= i+1; iloop++) {
						for (int jloop = j-1; jloop <= j; jloop++) {
							if (board[iloop][jloop].getColor() == sidecolor) {
								//System.out.println("looking at ["+ iloop + "," + jloop + "]");
								surroundingchips++;
								//System.out.println("surrounding chips south edge add: " + surroundingchips);
							}
						}
					}
				} else {
					//System.out.println("non edge cluster case add");
					for (int iloop = i-1; iloop <= i+1; iloop++) {
						for (int jloop = j-1; jloop <= j+1; jloop++) {
							if (board[iloop][jloop].getColor() == sidecolor) {
								//System.out.println("looking at ["+ iloop + "," + jloop + "]");
								surroundingchips++;
								//System.out.println("surrounding chips non edge add: " + surroundingchips);
							}
						}
					}
				}
				if (surroundingchips >= 3) {
				System.out.println("This move would form a cluster!");
				returnval = true;
				}
			}
		}
	}

	board[m.x1][m.y1].setColor(-1); // put back board the way it came
}

if (m.moveKind == Move.STEP) {
//System.out.println("formsCluster entered step move conditional");
board[m.x2][m.y2].setColor(-1); // remove old move
board[m.x1][m.y1].setColor(sidecolor); // place new move
for (int i = 0; i < 8; i++) { // repeat same thing as add moves
for (int j = 0; j < 8; j++) { // loop through the whole board to find chips of color sidecolor
if (board[i][j].getColor() == sidecolor) { 
int surroundingchips = 0; // then you have at least one surrounding chip (itself)
if (i==0 && j!=0 && j!=7) {
//System.out.println("west edge cluster case");
for (int iloop = i; iloop <= i+1; iloop++) {
for (int jloop = j-1; jloop <= j+1; jloop++) {
if (board[iloop][jloop].getColor() == sidecolor) { // look at surrounding chips
surroundingchips++;
//System.out.println("surrounding chips: " + surroundingchips);
}
}
}
} else if (i==7 && j!=0 && j!=7) {
//System.out.println("east edge cluster case");
for (int iloop = i-1; iloop <= i; iloop++) {
for (int jloop = j-1; jloop <= j+1; jloop++) {
if (board[iloop][jloop].getColor() == sidecolor) {
surroundingchips++;
//System.out.println("surrounding chips: " + surroundingchips);
}
}
}
} else if (j == 0 && i!=0 && i!=7) {
//System.out.println("north edge cluster case");
for (int iloop = i-1; iloop <= i+1; iloop++) {
for (int jloop = j; jloop <= j+1; jloop++) {
if (board[iloop][jloop].getColor() == sidecolor) {
surroundingchips++;
//System.out.println("surrounding chips: " + surroundingchips);
}
}
}
} else if (j == 7 && i!=0 && i!=7) {
//System.out.println("south edge cluster case");
for (int iloop = i-1; iloop <= i+1; iloop++) {
for (int jloop = j-1; jloop <= j; jloop++) {
if (board[iloop][jloop].getColor() == sidecolor) {
surroundingchips++;
//System.out.println("surrounding chips: " + surroundingchips);
}
}
}
} else {
//System.out.println("non edge cluster case");
for (int iloop = i-1; iloop <= i+1; iloop++) {
for (int jloop = j-1; jloop <= j+1; jloop++) {
if (board[iloop][jloop].getColor() == sidecolor) {
surroundingchips++;
//System.out.println("surrounding chips: " + surroundingchips);
}
}
}
}
if (surroundingchips >= 3) {
//System.out.println("This move would form a cluster!");
returnval = true;
}
}
}
}
board[m.x1][m.y1].setColor(-1);
board[m.x2][m.y2].setColor(sidecolor); // put board back the way it came
}
return returnval;
}

/**
* helperToFormsCluster() is a helper method for formsCluster().  It takes in the same parameters, and because of the similarities in
* implementation of formsCluster() for ADD Moves and STEP Moves, a helper method is much cleaner.
* @param Move m, int sidecolor (either machinePlayer.machinePlayerColor or machinePlayer.opponentColor)
* @return boolean true if the Move m would form a cluster, false otherwise.
**/
protected boolean helperToFormsCluster(Move m, int sidecolor) {
	return false;

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

		if (hasChipsLeft(sidecolor)) { // create a list of ADD moves
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if (isValidMove(new Move(i,j),sidecolor)) {
						validMovesList.insertBack(new Move(i,j));
					}
				}
			}
		} else { // create a list of STEP moves
			for (int occi = 0; occi < 8; occi ++) { // occi and occj are finding the occupied squares, i and j will function as above
				for (int occj = 0; occj < 8; occj++) {
					if (board[occi][occj].getColor() == machinePlayer.machinePlayerColor) {
						for (int i = 0; i < 8; i++) {
							for (int j = 0; j < 8; j++) {
								if (isValidMove(new Move(i,j, occi, occj),sidecolor)) {
									validMovesList.insertBack(new Move(i,j,occi,occj));
								}
							}
						}
					}
				}
			}
		}

		return validMovesList;
	}

	/** chipConnections() is a helper method of evaluateBoard() that creates a DList of connections for a given occupied (single) Square
	* with other occupied Squares.
	* @param Square s, look for connections of given Square
	* @return DList of Squares
	**/
	protected DList chipConnections(Square s) {

		int i = s.location()[0];
		int j = s.location()[1];
		int color = s.getColor();
		DList list = new DList();


		// Make sure Square is black or white
		if (color == -1){ 
			System.out.println("Checking chipsConnections in a not black nor white Square!");
			return null;
		}


		//We call the 8 helper methods that will check the 8 different possible connections
		topVerticalConnection(i, j, color, list);
		rightTopDiagonalConnection(i, j, color, list);
		rightHorizontalConnection(i, j, color, list);
		rightBottomDiagonalConnection(i, j, color, list);
		bottomVerticalConnection(i, j, color, list);
		leftBottomDiagonalConnection(i, j, color, list);
		leftHorizontalConnection(i, j, color, list);
		leftTopDiagonalConnection(i, j, color, list);
		
		
		

		return list;

	}

	//Print list of connections
	public String printChipConnections(DList d) {

		try{

			ListNode n = d.front();

			System.out.println(d.length() + " is the length of the list, from printChipConnections");

			for (int i = 0; i < d.length() ;i++){
				System.out.println(((Square) n.item()).location()[0] + ", " + ((Square) n.item()).location()[1] + " from printChipConnections");
				n = n.next();
			}
		} catch (InvalidNodeException i){
			System.out.println("InvalidNodeException thrown in printChipConnections");
		}

		return "";



	}


	//Method to test is chipConnections works
	//Returns number of connections that a chip has.
	protected int numberChipConnections(Square s) {

		DList list = chipConnections(s);

		if (list.length() == 0) {
			return 0;
		} else {
			return list.length();
		}

	}

	protected int numberOfNonOccupiedChipConnections(Square s) {

		DList list = chipConnections(s);

		if (list.length() == 0) {
			return 0;
		} else {

			int counter= 0;

			try {

				ListNode iterator = list.front();



				for (int i = 0; i < list.length(); i++){

					if ( ((Square) iterator.item()).getVisited() == false){
						counter++;
					}

					iterator = iterator.next();

				}

			}catch(InvalidNodeException i){

			}

			return counter;
		}

	}


	private void leftHorizontalConnection(int i, int j, int color, DList list){

		int x = i;

		while(true) {

			if (x < 1 ){
				break;
			}


			Square nextSquare = board[x-1][j];

			int nextSquareColor = nextSquare.getColor();

			if (nextSquareColor == -1){

				x--;

			} else if (color == nextSquareColor){

				list.insertBack(nextSquare);
				return;

			} else {

				return;

			}


		}



	}

	private void leftTopDiagonalConnection(int i, int j, int color, DList list){

		int x = i;
		int y = j;

		while(true) {

			if (x < 1 || y < 1 ){
				break;
			}

			Square nextSquare = board[x-1][y-1];

			int nextSquareColor = nextSquare.getColor();

			if (nextSquareColor == -1){

				x--;
				y--;

			} else if (color == nextSquareColor){

				list.insertBack(nextSquare);
				return;

			} else {

				return;

			}


		}

	}

	private void topVerticalConnection(int i, int j, int color, DList list){
		int y = j;

		while(true) {

			if (y < 1){
				break;
			}


			Square nextSquare = board[i][y-1];

			int nextSquareColor = nextSquare.getColor();

			if (nextSquareColor == -1){

				y--;

			} else if (color == nextSquareColor){

				list.insertBack(nextSquare);
				return;

			} else {

				return;

			}

		}

	}

	private void rightTopDiagonalConnection(int i, int j, int color, DList list){

		int x = i;
		int y = j;

		while(true) {

			if (x > 6 || y < 1 ){
				break;
			}

			Square nextSquare = board[x+1][y-1];

			int nextSquareColor = nextSquare.getColor();

			if (nextSquareColor == -1){

				x++;
				y--;

			} else if (color == nextSquareColor){

				list.insertBack(nextSquare);
				return;

			} else {

				return;

			}

		}

	}

	private void rightHorizontalConnection(int i, int j, int color, DList list){

		int x = i;

		while(true) {

			if (x > 6 ){
				break;
			}

			Square nextSquare = board[x+1][j];

			int nextSquareColor = nextSquare.getColor();

			if (nextSquareColor == -1){

				x++;

			} else if (color == nextSquareColor){

				list.insertBack(nextSquare);
				return;

			} else {

				return;

			}

		}




	}

	private void rightBottomDiagonalConnection(int i, int j, int color, DList list){

		int x = i;
		int y = j;

		while(true) {

			if (x > 6 || y > 6 ){
				break;
			}

			Square nextSquare = board[x+1][y+1];

			int nextSquareColor = nextSquare.getColor();

			if (nextSquareColor == -1){

				x++;
				y++;

			} else if (color == nextSquareColor){

				list.insertBack(nextSquare);
				return;

			} else {

				return;

			}

		}


	}

	private void bottomVerticalConnection(int i, int j, int color, DList list){

		int y = j;

		while(true) {

			if (y > 6 ){
				break;
			}

			Square nextSquare = board[i][y+1];

			int nextSquareColor = nextSquare.getColor();

			if (nextSquareColor == -1){

				y++;

			} else if (color == nextSquareColor){

				list.insertBack(nextSquare);
				return;

			} else {

				return;

			}

		}

	}

	private void leftBottomDiagonalConnection(int i, int j, int color, DList list){

		int x = i;
		int y = j;

		while(true) {

			if (x < 1 || y > 6 ){
				break;
			}

			Square nextSquare = board[x-1][y+1];

			int nextSquareColor = nextSquare.getColor();

			if (nextSquareColor == -1){

				x--;
				y++;

			} else if (color == nextSquareColor){

				list.insertBack(nextSquare);
				return;

			} else {

				return;

			}

		}

	}









	/**
	* evaluateBoard() is a helper method of alphaBetaPruning() that assigns scores to each board in the game tree search,
	* following our evaluation algorithm.
	* This method is in the Gameboard class.
	* This method calls chipConnections(), in our chip connection module, and hasValidNetwork(), in our valid network module.
	* @param the depth level of our game tree search (an int), the sidecolor will determine if this is a MIN (opponentPlayer) or MAX (machinePlayer) player 
	* @return a score for the board (a double)
	**/
	protected double evaluateBoard(int depth, int sidecolor) {

		double score = 0.0; 


		//If the board has a network with just 1 move (or 0 move), we assign it the highest/lowest value 
		// possible of 1 if the MachinePlayer has a network, and -1 if the OpponentPlayer has a network
		// the higher the depth it takes to reach the network, the worse the board score will be 
		// a board that has a network can have an absolute score between 0.9 and 1.0 where 1.0 is the best
		// we subtract a depthPenalizer form the best case score where the depthPenalizer is greater as the depth gets higher

		double depthPenalizer = 0.0;

		if(depth <=1){
			depthPenalizer = 0.0;
		}if(depth < 10 ){
			depthPenalizer = depth*(0.01);
		}else{ //if the depth is 10 or beyond, we penalize 0.1
			depthPenalizer = 0.1; 
		} 


		//System.out.println(this.toString());

		//System.out.println("aaaaa: " + hasValidNetwork(sidecolor) + " sidecolor is: " + sidecolor);



		if(hasValidNetwork(sidecolor) == true){
			if(sidecolor == machinePlayer.machinePlayerColor){
				score = 1.0 - depthPenalizer; 
				return score;
			}if(sidecolor == machinePlayer.opponentColor){
				score = -1.0 + depthPenalizer;
				return score;
			}else{
				System.out.println("Error: sidecolor for hasNetwork is not 1 or 0");
			}
		}

		//If the board does not have a network, we evaluate it when we reach the searchdepth of the MachinePlayer 
		//sidecolor is the color of player who currently placed the chip
		//opponentSideColor is the color of the opponent of the player who currently placed the chip


		int opponentSideColor =-1; 

		if(sidecolor == machinePlayer.machinePlayerColor){ //if the side is true, it is the machinePlayer's turn
			opponentSideColor = machinePlayer.opponentColor;
		}
		if(sidecolor == machinePlayer.opponentColor){ //if the side is false, it is the opponentPlayer's turn 
			opponentSideColor = machinePlayer.machinePlayerColor;
		}

		int sideTotalConnections =0; //counts all of the connections on the board of the player who currently placed the chip
		int opponentTotalConnections=0; //counts all of the connections on the board of the opponent of the player who currently placed the chip 

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if(board[i][j].getColor() == sidecolor){ //if we come to a square that has a sidecolor, we find how many connections it has on the current board
				sideTotalConnections = sideTotalConnections + ((DList) chipConnections(board[i][j])).length();
				}if(board[i][j].getColor() == opponentSideColor){
				opponentTotalConnections = opponentTotalConnections + ((DList) chipConnections(board[i][j])).length();
				}else{
				continue;
				}
			}
		}

		// We give a score based on the difference between the total number of connections on the board for the current player and the current player's opponent
		//We assume that in a best case scenario, if a player has 10 chips on the board, the max 
		//total number of connections on the board 80 ( a chip surrounded by 8 directions of connections)
		// In the best case scenario, if the player has 80 total connections and the opponent has (worst case)
		// 0 total connections, then we assign that board a score of (80 - 0)/100 = 0.8 to fit out -1.0 to 1.0 scale
		//In the worst case scenario, if the player has 0 total connections and the opponent has (best case)
		// 80 total connections, then we assign that board a score of (0-80)/100 = -0.8 Which is unfavorable.

		score  = (sideTotalConnections - opponentTotalConnections)/100.0;

		if(score == 0){
			return score;
		}

		if(sidecolor == machinePlayer.machinePlayerColor){ //if we are evaluating the board for the MachinePlayer (MAX player), we return the score 
			return score;
		}
		if(sidecolor == machinePlayer.opponentColor){ //if we are evaluating the board for the Opponent of MachinePlayer (MIN player), we negate the score  
			return -1*score; 
		}

		return score;

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

	  	DList begList = new DList();
	  	DList conList = new DList();
		this.depthCounter = 0; 

		
		//Create list of possible first chips.
		if(sidecolor == 1){  //CHECK IF IT IS WHITE
			for(int j = 1; j < 7; j++){ //LOOK FOR A CHIP IN THE BEGINING LINE
				if (board[0][j].getColor() == sidecolor){ //CHECK IF THE SQUARE IS WHITE
					begList.insertBack(board[0][j]); //INSERT SQUARE TO THE LIST IF FOUND
				}
			}
		} else if (sidecolor == 0){ //CHECK IF IT IS BLACK
			for(int i = 1; i < 7; i++){ //LOOK FOR A CHIP IN THE BEGINING LINE
				if (board[i][0].getColor() == sidecolor){ //CHECK IF THE SQUARE IS WHITE
					begList.insertBack(board[i][0]); //INSERT SQUARE TO THE LIST IF FOUND
				}
			} 
		}


		if (begList.length() == 0){ //CHECK IF NO CHIPS WERE FOUND IN THE BEGINING LINE 
			System.out.println("NO CHIPS WERE FOUND IN THE BEGINING LINE");
			return false; 
		}





		//Create iterator that will go through the Square in the beginning line.
		ListNode iterator = begList.front();

		//Create first chip in the network connection


		
		//item(), and next() throw exceptions.
		try{

			for (int i = 0; i < begList.length(); i++) {

				//System.out.println("Going through the " + (i + 1) + " begining chip");


		    	this.depthCounter = 1;

				//System.out.println("Going through " + ((Square) iterator.item()).location()[0] + "," + ((Square) iterator.item()).location()[1] + " in begList with depthlevel: "+ depthCounter );

				conList.insertBack(iterator.item());

		    	if( explore( (Square) iterator.item(), conList) == true) {

					clearVisited();
					return true;

				}else{

					

					( (Square) iterator.item() ).setVisited(false);
					iterator = iterator.next();
					conList.removeBack();

				}
			 
			}



		} catch (InvalidNodeException i){

			System.out.println("return false from InvalidNodeException in hasValidNetwork");

			clearVisited();
			return false;

		}

		System.out.println("No network found");

		clearVisited();
		return false;



	}

	private boolean explore(Square s, DList conList){

		s.setVisited(true);

		DList list = chipConnections(s);

		ListNode iterator = list.front();



		try{

			for (int i = 0; i < list.length(); i++) {

				conList.insertBack(iterator.item());
				//System.out.println("Inserting: " + ((Square) iterator.item()).location()[0] + "," + ((Square) iterator.item()).location()[1]);



				//Check if the chip has been visited and changes direction
				if (   !((Square) iterator.item()).getVisited()  && changeDirection(conList) && (((Square) iterator.item()).getType() != -1)    ){

					depthCounter++;

//printChipConnections(conList);
					//System.out.println("At " + ((Square) iterator.item()).location()[0] + "," + ((Square) iterator.item()).location()[1] + " in list with depthlevel: "+ depthCounter );

					


					//BASE CASE OF RECURSION, WHEN WE FIND NETWORK
					if (depthCounter >= 6 && ((Square) iterator.item()).getType() == 1){
						System.out.println("FOUND NETWORK :-)");
						System.out.println("The number of chips connected is: " + depthCounter);
						printChipConnections(conList);
						return true;

					}

					//If we are at an ending line chip with less that 6 connected chips
					else if (((Square) iterator.item()).getType() == 1){

						//System.out.println("CHIP IN ENDING LINE WITH LESS THAN 6 CHIPS, NOT OK");

						((Square) iterator.item()).setVisited(false);
						iterator = iterator.next();
						depthCounter--;
						conList.removeBack();
						continue;
					}



					//PRINT CONNECTIONS OF CURRENT CHIP
					//printChipConnections(chipConnections((Square) iterator.item()));


					if ( numberOfNonOccupiedChipConnections((Square) iterator.item()) > 0 ){

						//System.out.println(((Square) iterator.item()).location()[0] + "," + ((Square) iterator.item()).location()[1] + "  has MORE possible connections, in explore method");
						
						if (explore( (Square) iterator.item(), conList)){
							return true;
						} else {
							
							depthCounter--;
							((Square) iterator.item()).setVisited(false);
							iterator = iterator.next();
							conList.removeBack();
						}
					
					} else {

						//System.out.println("DEAD END " + ((Square) iterator.item()).location()[0] + "," + ((Square) iterator.item()).location()[1] + "  has NO more possible connections, in explore method");
						depthCounter--;
						((Square) iterator.item()).setVisited(false);
						iterator = iterator.next();
						conList.removeBack();
						continue;
					}


				} else{

					if (((Square) iterator.item()).getVisited()){
					//	System.out.println(((Square) iterator.item()).location()[0] + "," + ((Square) iterator.item()).location()[1] + " has been visited"); 
					}

					if (!changeDirection(conList)){
					//	System.out.println(((Square) iterator.item()).location()[0] + "," + ((Square) iterator.item()).location()[1] + " doesn't change direction"); 
					}
					//depthCounter--;
					conList.removeBack();
					iterator = iterator.next();
				}

			}//END OF FOR LOOP

			return false;

		} catch(InvalidNodeException i) {

			System.out.println("InvalidNodeException in explore method");

			return false;

		}



	}




	private boolean changeDirection(DList d){

		//System.out.println("IN CHANGE DIRECTION");
		//printChipConnections(d);

		if (d.length() >= 3){

			try{

				ListNode n1 = d.back();
				ListNode n2 = d.back().prev();
				ListNode n3 = d.back().prev().prev();

				double xSlope1 = (   ((Square) n1.item()).location()[0] - ((Square) n2.item()).location()[0]   );
				double xSlope2 = (   ((Square) n1.item()).location()[0] - ((Square) n3.item()).location()[0]   );

				double ySlope1 = (   ((Square) n1.item()).location()[1] - ((Square) n2.item()).location()[1]   );
				double ySlope2 = (   ((Square) n1.item()).location()[1] - ((Square) n3.item()).location()[1]   );

				double slope1;
				double slope2;


				if (ySlope1 == 0){

					if (xSlope1 > 0){
						slope1 = 100;
					} else {
						slope1 = -100;
					}

				} else{
					slope1 =  xSlope1 / ySlope1;
				}



				if (ySlope2 == 0){

					if (xSlope2 > 0){
						slope2 = 100;
					} else {
						slope2 = -100;
					}
					
				} else {

					slope2 =   xSlope2 / ySlope2; 
				}

				/*System.out.println("xSlope1 is: "+ xSlope1);
				System.out.println("xSlope2 is: "+ xSlope2);
				System.out.println("ySlope1 is: "+ ySlope1);
				System.out.println("ySlope2 is: "+ ySlope2);

				System.out.println("Slope1 is: "+ slope1);
				System.out.println("Slope2 is: "+ slope2);*/


				

				if (slope1 == slope2){
					return false;
				} else {
					return true;
				}

			} catch (InvalidNodeException i){
				System.out.println("You will never se this message, it is impossible");
				return true;
			}

		} else {
			return true;
		}




	}

	private void clearVisited(){


		for (int i = 0; i<8;i++){
			for (int j = 0; j<8;j++){
				board[i][j].setVisited(false);
			}
		}

	}



  	public String toString() {

	    String aString;     
	    aString = "";
	    String strsqr = "";
	    int i;
	    int j;

	    for (j = 0; j < 8; j++) {
	        for (i = 0; i < 8; i++ ) {
	        	if (board[i][j].getColor() == -1) {
	        		strsqr = "[ ]";
	        	} else if (board[i][j].getColor() == 0) {
	        		strsqr = "[B]";
	        	} else if (board[i][j].getColor() == 1) {
	        		strsqr = "[W]";
	        	}
	        	aString = aString + "  " + strsqr;
	        }

	    aString = aString + "\n\n\n";
	    }
	    return aString;
  	}





}

