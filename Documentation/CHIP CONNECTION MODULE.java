// Chip connection Module


/**  chipConnections() returns all the possible connections that an occupied Square has
* chipConnections() is a helper method of evaluateBoard()
* @param Square s
* @return SquareList 
**/
protected SquareList chipConnections(Square s)



/**
* horizontalConnection() returns the horizontal connections
*
*Checks if there are opponents chips in between.
*
* @param Square s,
* @return SquareList l if Square s has horizontal connections, empty SquareList (OR NULL, OR EXCEPTION?) otherwise.
**/
protected boolean horizontalConnection(Square s);

/**
* verticalConnection(Square s) returns the vertical connections
*
*Checks if there are opponents chips in between.
*
* @param Square s,
* @return SquareList l if Square s has vertical connections, empty SquareList (OR NULL, OR EXCEPTION?) otherwise.
**/
protected boolean verticalConnection(Square s);

/**
* diagonalConnection(Square s) returns the diagonal connections
*
*Checks if there are opponents chips in between.
*
* @param Square s,
* @return SquareList l if Square s has diagonal connections, empty SquareList (OR NULL, OR EXCEPTION?) otherwise.
**/
protected boolean diagonalConnection(Square s);

/**
* Checks if there are opponents chips in between a connection
*
* @param SquareList l formed by two chips
* @return true if the there are no blocks, false otherwise
**/
protected boolean clearPath(SquareList l);
