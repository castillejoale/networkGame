// Validity Module

/**
* isValidMove(Square s) dermines whether a Square whose color field is -1 (unoccupied) is a valid Move.
* This method uses four helper methods: hasChipsLeft(), isInBound(Square s), isOccupied(Square s), and formsCluster(Square s).
* This method is in the Gameboard class.
* @param Square s
* @return boolean - true if the Square could hold a valid Move, or false otherwise
**/
protected boolean isValidMove(Square s)

/**
* hasChipsLeft() is a helper function of isValidMove()
* @param none -- uses information from "this" Gameboard
* @return boolean - true if there are not 10 Squares with color field equal to either 0 or 1 (black or white), false otherwise
**/
protected boolean hasChipsLeft()

/**
* isInBound(Square s) is a helper function of isValidMove()
* This method is in the Gameboard class.
* @param Square s
* @return boolean - true if that Square is within the boundaries of the Gameboard: within the 8x8 range, not on a corner,
*                   and not on an opponent's goal area.
**/
protected boolean isInBound(Square s)

/**
* isOccupied(Square s) is a helper function of isValidMove()
* This method is in the Gameboard class.
* @param Square s
* @return boolean - true if the color field does not equal -1 (it is unoccupied), false otherwise
**/
protected boolean isOccupied(Square s)

/**
* formsCluster(Square s) is a helper function of isValidMove()
* Refer to the readme for project 2 to understand what a cluster is.
* This method is in the Gameboard class.
* @param Square s
* @return boolean - true if a cluster is formed (would result in isValidMove returning false), false otherwise
**/
protected boolean formsCluster(Square s)

/**
* allValidMoves() returns a SquareList of all Squares s such that isValidMove(Square s) returns true.
* This method is in the Gameboard class.
* @param none -- uses information from "this" Gameboard
* @return a SquareList of all Squares s such that isValidMove(Square s) returns true.
**/
protected SquareList allValidMoves(Square s)