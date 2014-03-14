// Validity Module

/**
* isValidMove(Move m) dermines whether a s a valid Move.
* This method uses four helper methods: hasChipsLeft(), isInBound(Move m), isOccupied(Move m), and formsCluster(Move m).
* This method is in the Gameboard class.
* @param Move m
* @return boolean - true if the Square could hold a valid Move, or false otherwise
**/
protected boolean isValidMove(Move m)

/**
* hasChipsLeft() is a helper function of isValidMove()
* @param none -- uses information from "this" Gameboard
* @return boolean - true if there are not 10 Squares with color field equal to either 0 or 1 (black or white), false otherwise
**/
protected boolean hasChipsLeft()

/**
* isInBound(Move m) is a helper function of isValidMove()
* This method is in the Gameboard class.
* @param Move m
* @return boolean - true if that Square is within the boundaries of the Gameboard: within the 8x8 range, not on a corner,
*                   and not on an opponent's goal area.
**/
protected boolean isInBound(Move m)

/**
* isOccupied(Move m) is a helper function of isValidMove()
* This method is in the Gameboard class.
* @param Move m
* @return boolean - true if the color field does not equal -1 (it is unoccupied), false otherwise
**/
protected boolean isOccupied(Move m)

/**
* formsCluster(Move m) is a helper function of isValidMove()
* Refer to the readme for project 2 to understand what a cluster is.
* This method is in the Gameboard class.
* @param Move m
* @return boolean - true if a cluster is formed (would result in isValidMove returning false), false otherwise
**/
protected boolean formsCluster(Move m)

/**
* allValidMoves() returns a DList of all Squares s such that isValidMove(Move m) returns true.
* This method is in the Gameboard class.
* @param none -- uses information from "this" Gameboard
* @return a DList of all Squares s such that isValidMove(Move m) returns true.
**/
protected DList allValidMoves(int sideColor)



