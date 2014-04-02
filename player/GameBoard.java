/* GameBoard.java */

/** This class will hold our Machine Player's own copy of the game board so that it can perform a game tree search and evaluation function
* algorithms.  It is a two dimensional array of Squares, which store whether a square in the game board is occupied with a black or white
* chip, or unoccupied, and gives information about the Square to be used to find a network.
**/

package player;

import list.*;

public class GameBoard {

	protected Square[][] board;
	protected MachinePlayer machinePlayer;
	private int depthCounter; //Field that saves the number of connections made in a network

	/**
	* The GameBoard class constructor creates an 8x8 GameBoard of unoccupied Squares such that each Square s.getColor() = -1 and
	* sets the type (normal, beginning goal area or end goal area).
	* This GameBoard is created at the beginning of a game in the MachinePlayer class and updated after each player makes a move. 
	* @param machinePlayer
	* @return nothing, it simply modifies the board field
	**/
	protected GameBoard(MachinePlayer machinePlayer) {

		this.board = new Square[8][8];

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				board[i][j] = new Square(-1, i, j); // initializes board to empty
				if (i==0 || j == 0) { //Set the squares types of the begining lines to -1 for, which means begining line.
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
	* This method is in the GameBoard class.
	* @param Move m, int sideColor
	* @return boolean - true if the Move m is valid, or false otherwise
	**/
	protected boolean isValidMove(Move m, int sideColor) {

	  if (m.moveKind == Move.ADD) { 
	  	if (hasChipsLeft(sideColor) && isInBound(m, sideColor) && !isOccupied(m) && !formsCluster(m, sideColor)) {
	  		return true;
	  	}
	  }
	  if (m.moveKind == Move.STEP) {
	  	if (!hasChipsLeft(sideColor) && isInBound(m, sideColor) && !isOccupied(m) && !formsCluster(m, sideColor)) {
	  		return true;
	  	}
	  }
	  return false;

	  
	}

	/**
	* hasChipsLeft() is a helper function of isValidMove()
	* For an ADD Move to be valid, this method must return true.
	* For a STEP Move to be valid, this method must return false.
	* This method is in the GameBoard class.
	* @param int sideColor -- passed in from isValidMove()
	* @return boolean - true if there are not 10 Squares with color field equal to either 0 or 1 (black or white), false otherwise
	**/
	private boolean hasChipsLeft(int sideColor) {

	  int chips = 0; // initialize count

	  for (int i = 0; i < 8; i++) {
	    for (int j = 0; j < 8; j++) {
	      if (board[i][j].getColor() == sideColor) {
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
	* This method is in the GameBoard class.
	* @param Move m, int sideColor -- passed in from isValidMove()
	* @return boolean - true if that Move m is within the boundaries of the Gameboard: within the 8x8 range, not on a corner,
	*                   and not on an opponent's goal area.
	**/
	private boolean isInBound(Move m, int sideColor) {

	  if (m.x1 < 0 || m.x1 > 7 || m.y1 < 0 || m.y1 > 7) {
	    return false; // not on the 8x8 board
	  }
	  if ((m.x1 == 0 && m.y1 == 0) || (m.x1 == 7 && m.y1 == 0) || (m.x1 == 0 && m.y1 == 7) || (m.x1 == 7 && m.y1 == 7)) {
	    return false; // no corners
	  } 
	  if (sideColor == 0) {
	    if (m.x1 == 0 || m.x1 == 7) {
	      return false; // black cannot be in white's goal
	    }  
	  }
	  if (sideColor == 1) {
	    if (m.y1 == 0 || m.y1 == 7) {
	      return false; // white cannot be in black's goal
	    }
	  }
	  return true; // if all if statements are false, the Move is in bounds
	}

	/**
	* isOccupied() is a helper function of isValidMove()
	* For a Move to be valid, this method must return false.
	* This method is in the GameBoard class.
	* @param Move m -- passed in from isValidMove()
	* @return boolean - true if the color field does not equal -1 (it is occupied), false otherwise
	**/
	private boolean isOccupied(Move m) {

	  if (board[m.x1][m.y1].getColor() == -1) {
	    return false;
	  }

	  return true;

	}

	/**
	* formsCluster(Move m) is a helper function of isValidMove()
	* Refer to the readme for project 2 to understand what a cluster is.
	* For a Move to be valid, this method must return false.
	* This method is in the GameBoard class.
	* @param Move m, int sideColor, either machinePlayer.machinePlayerColor or machinePlayer.opponentColor
	* @return boolean - true if a cluster is formed, false otherwise
	**/
	private boolean formsCluster(Move m, int sideColor) {

	boolean returnVal = false;

	if (m.moveKind == Move.ADD) {
		board[m.x1][m.y1].setColor(sideColor); // put move on board to see if it forms a cluster
		if (helperToFormsCluster(m,sideColor)) {
			returnVal = true;
		}

		board[m.x1][m.y1].setColor(-1); // put back board the way it came
	}

	if (m.moveKind == Move.STEP) {
		board[m.x2][m.y2].setColor(-1); // remove old move
		board[m.x1][m.y1].setColor(sideColor); // place new move
		if (helperToFormsCluster(m,sideColor)) {
			returnVal = true;
		}

		board[m.x1][m.y1].setColor(-1);
		board[m.x2][m.y2].setColor(sideColor); // put board back the way it came
	}

	return returnVal;
	}

	/**
	* helperToFormsCluster() is a helper method for formsCluster().  It takes in the same parameters, and because of the similarities in
	* implementation of formsCluster() for ADD Moves and STEP Moves, a helper method is much cleaner.
	* @param Move m, int sideColor (passed in to formsCluster())
	* @return boolean true if the Move m would form a cluster, false otherwise.
	**/
	private boolean helperToFormsCluster(Move m, int sideColor) {

	boolean returnVal = false;

	for (int i = 0; i < 8; i++) {
		for (int j = 0; j < 8; j++) { // loop through the whole board to find chips of color sideColor
			if (board[i][j].getColor() == sideColor) { 
				int surroundingChips = 0;
				if (i==0 && j!=0 && j!=7) {
					for (int iLoop = i; iLoop <= i+1; iLoop++) {
						for (int jLoop = j-1; jLoop <= j+1; jLoop++) {
							if (board[iLoop][jLoop].getColor() == sideColor) { // look at surrounding chips
								surroundingChips++;
							}
						}
					}
				} else if (i==7 && j!=0 && j!=7) {
					for (int iLoop = i-1; iLoop <= i; iLoop++) {
						for (int jLoop = j-1; jLoop <= j+1; jLoop++) {
							if (board[iLoop][jLoop].getColor() == sideColor) {
								surroundingChips++;
							}
						}
					}
				} else if (j == 0 && i!=0 && i!=7) {
					for (int iLoop = i-1; iLoop <= i+1; iLoop++) {
						for (int jLoop = j; jLoop <= j+1; jLoop++) {
							if (board[iLoop][jLoop].getColor() == sideColor) {
								surroundingChips++;
							}
						}
					}
				} else if (j == 7 && i!=0 && i!=7) {
					for (int iLoop = i-1; iLoop <= i+1; iLoop++) {
						for (int jLoop = j-1; jLoop <= j; jLoop++) {
							if (board[iLoop][jLoop].getColor() == sideColor) {
								surroundingChips++;
							}
						}
					}
				} else {
					for (int iLoop = i-1; iLoop <= i+1; iLoop++) {
						for (int jLoop = j-1; jLoop <= j+1; jLoop++) {
							if (board[iLoop][jLoop].getColor() == sideColor) {
								surroundingChips++;
							}
						}
					}
				}
				if (surroundingChips >= 3) {
				returnVal = true;
				}
			}
		}
	}
	return returnVal;
}
	/**
	* updateGameBoard(Move m) updates the GameBoard with a new Move, as long as the Move is valid.
	* @param Move m, int sideColor
	* @return nothing -- the GameBoard is updated
	**/
	protected void updateGameBoard(Move m, int sideColor) {

		if (isValidMove(m, sideColor)) {
			if (m.moveKind == Move.ADD) {
				board[m.x1][m.y1].setColor(sideColor);
			}
			if (m.moveKind == Move.STEP) {
				board[m.x2][m.y2].setColor(-1);
				board[m.x1][m.y1].setColor(sideColor);
			}
		}
	}

	/**
	* allValidMoves(int sideColor) returns a DList of all Moves m such that isValidMove(Move m, int sideColor),
	* a helper method, returns true.
	* This method will either return a list of all ADD Moves, or all STEP Moves.
	* isValidMove(Move m, int sideColor) checks four things to see if a Move is valid.
	* 1. The Move is within the bounds of the GameBoard for the given chip color.
	* 2. The Move does not move to an occupied Square.
	* 3. The Move does not form a cluster.
	* 4. An ADD Move is only taken if there are less than 10 chips on the board, and
	*    a STEP Move is only taken if there are already 10 chips on the board.
	* Clarifications of these rules can be found in the readme.
	* This method is in the GameBoard class.
	* @param int sideColor 
	* @return a DList of all Moves m such that isValidMove(Move m) returns true.
	**/
	protected DList allValidMoves(int sideColor) {

		DList validMovesList = new DList();

		if (hasChipsLeft(sideColor)) { // create a list of ADD moves
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if (isValidMove(new Move(i,j),sideColor)) {
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
								if (isValidMove(new Move(i,j, occi, occj),sideColor)) {
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

	/** chipConnections() is a helper method of evaluateBoard() that creates a DList of connections for a given
	* occupied (single) Square with other occupied Squares.
	* @param Square s, look for connections of given Square
	* @return DList of Squares connected to the Square given by the parameter
	**/
	protected DList chipConnections(Square s) {

		int i = s.location()[0];
		int j = s.location()[1];
		int color = s.getColor();
		DList list = new DList();


		// Make sure Square is black or white
		if (color == -1){ 
			//System.out.println("Checking chipsConnections in a not black nor white Square!");
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


	/** leftHorizontalConnection(int i, int j, int color, DList list) is a helper method that looks for a connection
	* to the left. If it find a connection it will add it to the list parameter.
	* @param int i and int j is the location of the Square. Int color, the color of the player. DList list, list of connections
	* @return nothing, it modifies the list
	**/
	private void leftHorizontalConnection(int i, int j, int color, DList list) {

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

	/** leftTopDiagonalConnection(int i, int j, int color, DList list) is a helper method that looks for a connection
	* to the left top diagonal. If it find a connection it will add it to the list parameter.
	* @param int i and int j is the location of the Square. Int color, the color of the player. DList list, list of connections
	* @return nothing, it modifies the list
	**/
	private void leftTopDiagonalConnection(int i, int j, int color, DList list) {

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
			} else if (color == nextSquareColor) {
				list.insertBack(nextSquare);
				return;
			} else {
				return;
			}
		}
	}


	/** topVerticalConnection(int i, int j, int color, DList list) is a helper method that looks for a connection
	* to the top. If it find a connection it will add it to the list parameter.
	* @param int i and int j is the location of the Square. Int color, the color of the player. DList list, list of connections
	* @return nothing, it modifies the list
	**/
	private void topVerticalConnection(int i, int j, int color, DList list) {
		int y = j;

		while(true) {
			if (y < 1) {
				break;
			}
			Square nextSquare = board[i][y-1];
			int nextSquareColor = nextSquare.getColor();
			if (nextSquareColor == -1){
				y--;
			} else if (color == nextSquareColor) {
				list.insertBack(nextSquare);
				return;
			} else {
				return;
			}
		}
	}

	/** rightTopDiagonalConnection(int i, int j, int color, DList list) is a helper method that looks for a connection
	* to the right top diagonal. If it find a connection it will add it to the list parameter.
	* @param int i and int j is the location of the Square. Int color, the color of the player. DList list, list of connections
	* @return nothing, it modifies the list
	**/
	private void rightTopDiagonalConnection(int i, int j, int color, DList list) {

		int x = i;
		int y = j;

		while(true) {
			if (x > 6 || y < 1){
				break;
			}
			Square nextSquare = board[x+1][y-1];
			int nextSquareColor = nextSquare.getColor();
			if (nextSquareColor == -1){
				x++;
				y--;
			} else if (color == nextSquareColor) {
				list.insertBack(nextSquare);
				return;
			} else {
				return;
			}
		}
	}


	/** rightHorizontalConnection(int i, int j, int color, DList list) is a helper method that looks for a connection
	* to the right. If it find a connection it will add it to the list parameter.
	* @param int i and int j is the location of the Square. Int color, the color of the player. DList list, list of connections
	* @return nothing, it modifies the list
	**/
	private void rightHorizontalConnection(int i, int j, int color, DList list) {

		int x = i;

		while(true) {
			if (x > 6) {
				break;
			}
			Square nextSquare = board[x+1][j];
			int nextSquareColor = nextSquare.getColor();
			if (nextSquareColor == -1){
				x++;
			} else if (color == nextSquareColor) {
				list.insertBack(nextSquare);
				return;
			} else {
				return;
			}
		}
	}


	/** rightBottomDiagonalConnection(int i, int j, int color, DList list) is a helper method that looks for a connection
	* to the right bottom diagonal. If it find a connection it will add it to the list parameter.
	* @param int i and int j is the location of the Square. Int color, the color of the player. DList list, list of connections
	* @return nothing, it modifies the list
	**/
	private void rightBottomDiagonalConnection(int i, int j, int color, DList list) {

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

	/** bottomVerticalConnection(int i, int j, int color, DList list) is a helper method that looks for a connection
	* to the bottom. If it find a connection it will add it to the list parameter.
	* @param int i and int j is the location of the Square. Int color, the color of the player. DList list, list of connections
	* @return nothing, it modifies the list
	**/
	private void bottomVerticalConnection(int i, int j, int color, DList list) {

		int y = j;

		while(true) {
			if (y > 6 ) {
				break;
			}
			Square nextSquare = board[i][y+1];
			int nextSquareColor = nextSquare.getColor();
			if (nextSquareColor == -1){
				y++;
			} else if (color == nextSquareColor) {
				list.insertBack(nextSquare);
				return;
			} else {
				return;
			}
		}
	}

	/** leftBottomDiagonalConnection(int i, int j, int color, DList list) is a helper method that looks for a connection
	* to the left bottom diagonal. If it find a connection it will add it to the list parameter.
	* @param int i and int j is the location of the Square. Int color, the color of the player. DList list, list of connections
	* @return nothing, it modifies the list
	**/
	private void leftBottomDiagonalConnection(int i, int j, int color, DList list) {

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
			} else if (color == nextSquareColor) {
				list.insertBack(nextSquare);
				return;
			} else {
				return;
			}
		}
	}


	/** numberOfNonOccupiedChipConnections(Square s) is a helper method that counts the number of connections
	* with Squares that have not been visited.
	* @param Square s, Square in which we will check the number of connections
	* @return int, returns number of non-visited connections that a chip has.
	**/
	private int numberOfNonOccupiedChipConnections(Square s) {

		DList list = chipConnections(s);

		if (list.length() == 0) {
			return 0;
		} else {

			int counter= 0;

			try {

				ListNode iterator = list.front();

				for (int i = 0; i < list.length(); i++) {
					if ( ((Square) iterator.item()).getVisited() == false) {
						counter++;
					}
					iterator = iterator.next();
				}

			} catch(InvalidNodeException i) {}

			return counter;
		}

	}


	/**
	* evaluateBoard() assigns scores to each board in the game tree search following our evaluation algorithm based on
	* comparing first which player has a network and if not compare the number of total connections that each player has
	* @param the depth level of our game tree search (an int), and the int sideColor, the color of the player 
	* @return a score for the board (a double)
	**/
	protected double evaluateBoard(int depth, int sideColor) {

		double score = 0.0; 

		//If the board has a network with just 1 move (or 0 move), we assign it the highest/lowest value 
		// possible of 1 if the MachinePlayer has a network, and -1 if the OpponentPlayer has a network
		// the higher the depth it takes to reach the network, the worse the board score will be 
		// a board that has a network can have an absolute score between 0.9 and 1.0 where 1.0 is the best
		// we subtract a depthPenalizer of 0.1 for each level of depth

		double depthPenalizer = 0.0;

		if(depth <=1){
			depthPenalizer = 0.0;
		}if(depth < 10 ){
			depthPenalizer = depth*(0.01);
		}else{ //if the depth is 10 or beyond, highly unlikely, we penalize 0.1
			depthPenalizer = 0.1; 
		} 

		if(hasValidNetwork(sideColor) == true){
			if(sideColor == machinePlayer.machinePlayerColor){
				score = 1.0 - depthPenalizer; 
				return score;
			}if(sideColor == machinePlayer.opponentColor){
				score = -1.0 + depthPenalizer;
				return score;
			}
		}

		//If the board does not have a network, we evaluate it when we reach the searchdepth of the MachinePlayer 
		//sideColor is the color of player who currently placed the chip
		//opponentsideColor is the color of the opponent of the player who currently placed the chip

		int opponentsideColor =-1; 

		if(sideColor == machinePlayer.machinePlayerColor){ //if the side is true, it is the machinePlayer's turn
			opponentsideColor = machinePlayer.opponentColor;
		}
		if(sideColor == machinePlayer.opponentColor){ //if the side is false, it is the opponentPlayer's turn 
			opponentsideColor = machinePlayer.machinePlayerColor;
		}

		int sideTotalConnections =0; //counts all of the connections on the board of the player who currently placed the chip
		int opponentTotalConnections=0; //counts all of the connections on the board of the opponent of the player who currently placed the chip 

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if(board[i][j].getColor() == sideColor){ //if we come to a square that has a sideColor, we find how many connections it has on the current board
				sideTotalConnections = sideTotalConnections + ((DList) chipConnections(board[i][j])).length();
				}if(board[i][j].getColor() == opponentsideColor){
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

		if(sideColor == machinePlayer.machinePlayerColor){ //if we are evaluating the board for the MachinePlayer (MAX player), we return the score 
			return score;
		}
		if(sideColor == machinePlayer.opponentColor){ //if we are evaluating the board for the Opponent of MachinePlayer (MIN player), we negate the score  
			return -1*score; 
		}

		return score;

	}

  /**
  *  hasValidNetwork(int sideColor) determines whether "this" GameBoard has a valid network
  *  for the player with the chip color given as a parameter. 
  *  A full description of what constitutes a valid network appears in the
  *  project "readme" file.
  *
  *  @param int sideColor (0 for black, 1 for white)
  *  @return true if player "side" has a winning network in "this" GameBoard;
  *          false otherwise.
  **/
  	protected boolean hasValidNetwork(int sideColor) {

	  	DList begList = new DList();
	  	DList conList = new DList();
		this.depthCounter = 0; 

		
		//Create list of possible chips in the begining line.
		if(sideColor == 1) {  //CHECK IF IT IS WHITE
			for(int j = 1; j < 7; j++) { //LOOK FOR A CHIP IN THE BEGINING LINE
				if (board[0][j].getColor() == sideColor) { //CHECK IF THE SQUARE IS WHITE
					begList.insertBack(board[0][j]); //INSERT SQUARE TO THE LIST IF FOUND
				}
			}
		} else if (sideColor == 0) { //CHECK IF IT IS BLACK
			for(int i = 1; i < 7; i++) { //LOOK FOR A CHIP IN THE BEGINING LINE
				if (board[i][0].getColor() == sideColor) { //CHECK IF THE SQUARE IS WHITE
					begList.insertBack(board[i][0]); //INSERT SQUARE TO THE LIST IF FOUND
				}
			} 
		}

		if (begList.length() == 0) { //CHECK IF NO CHIPS WERE FOUND IN THE BEGINING LINE 
			return false; 
		}

		//Create iterator that will go through the Square in the beginning line.
		ListNode iterator = begList.front();

		//item(), and next() throw exceptions.
		try {
			for (int i = 0; i < begList.length(); i++) {
		    	this.depthCounter = 1;
				conList.insertBack(iterator.item());
		    	if( explore( (Square) iterator.item(), conList) == true) {
					clearVisited();
					return true;
				}else {
					( (Square) iterator.item() ).setVisited(false);
					iterator = iterator.next();
					conList.removeBack();
				}
			}
		} catch (InvalidNodeException i) {
			clearVisited();
			return false;
		}

		clearVisited();
		return false;
	}


	/**
	* explore(Square s, DList conList) is a recursive method that looks if there is a network. 
	* @param Square s, if this Square is in the network, and DList conList is the list of
	* Squares in a connected network
	* @return true if Square s will have a network, false otherwise.
	**/
	private boolean explore(Square s, DList conList) {

		s.setVisited(true);
		DList list = chipConnections(s);
		ListNode iterator = list.front();

		try{
			for (int i = 0; i < list.length(); i++) {
				conList.insertBack(iterator.item());
				//Check if the chip has been visited and changes direction
				if (   !((Square) iterator.item()).getVisited()  && changeDirection(conList) && (((Square) iterator.item()).getType() != -1)    ) {
					depthCounter++;
					//BASE CASE OF RECURSION, WHEN WE FIND NETWORK
					if (depthCounter >= 6 && ((Square) iterator.item()).getType() == 1) {
						return true;
					}
					//If we are at an ending line chip with less that 6 connected chips
					else if (((Square) iterator.item()).getType() == 1) {
						((Square) iterator.item()).setVisited(false);
						iterator = iterator.next();
						depthCounter--;
						conList.removeBack();
						continue;
					}
					if ( numberOfNonOccupiedChipConnections((Square) iterator.item()) > 0 ) {
						if (explore( (Square) iterator.item(), conList)){
							return true;
						} else {	
							depthCounter--;
							((Square) iterator.item()).setVisited(false);
							iterator = iterator.next();
							conList.removeBack();
						}
					} else {
						depthCounter--;
						((Square) iterator.item()).setVisited(false);
						iterator = iterator.next();
						conList.removeBack();
						continue;
					}
				} else {
					if (((Square) iterator.item()).getVisited()){
					}
					if (!changeDirection(conList)){
					}
					conList.removeBack();
					iterator = iterator.next();
				}
			}//END OF FOR LOOP
			return false;
		} catch(InvalidNodeException i) {
			return false;
		}
	}



	/**
	* changeDirection(DList d) checks if a given network has changed directions.
	* @param DList d, list of Square in a network.
	* @return true if the new Square is changing direction, false otherwise.
	**/
	private boolean changeDirection(DList d) {
		if (d.length() >= 3) {
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

				if (ySlope1 == 0) {
					if (xSlope1 > 0) {
						slope1 = 100;
					} else {
						slope1 = -100;
					}
				} else {
					slope1 =  xSlope1 / ySlope1;
				}
				if (ySlope2 == 0) {
					if (xSlope2 > 0) {
						slope2 = 100;
					} else {
						slope2 = -100;
					}	
				} else {
					slope2 =   xSlope2 / ySlope2; 
				}				

				if (slope1 == slope2) {
					return false;
				} else {
					return true;
				}
			} catch (InvalidNodeException i) {
				return true;
			}
		} else {
			return true;
		}
	}


	/**
	* clearVisited() is a helper method that clears the board by setting every square to unvisited.
	* @param none
	* @return none
	**/
	private void clearVisited() {

		for (int i = 0; i<8;i++){
			for (int j = 0; j<8;j++){
				board[i][j].setVisited(false);
			}
		}

	}


	/**
	* toString() is a helper method that prints "this" board.
	* @param none
	* @return String with an 8x8 board with each player's chips
	**/
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

