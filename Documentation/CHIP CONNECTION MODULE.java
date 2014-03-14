// Chip connection Module


/**  chipConnections() returns all the possible connections that an occupied Square has
* chipConnections() is a helper method of evaluateBoard()
* @param Square s
* @return DList 
**/
protected DList chipConnections(Square s)
//Check the color of the Square, reject if it is empty
//Check Horizontal
//Check Vertical
//Check Diagonal



/**
* horizontalConnection() returns the horizontal connections
*
*Checks if there are opponents chips in between.
*
* @param Square s,
* @return DList l if Square s has horizontal connections, empty DList (OR NULL, OR EXCEPTION?) otherwise.
**/
protected boolean horizontalConnection(Square s);

/**
* verticalConnection(Square s) returns the vertical connections
*
*Checks if there are opponents chips in between.
*
* @param Square s,
* @return DList l if Square s has vertical connections, empty DList (OR NULL, OR EXCEPTION?) otherwise.
**/
protected boolean verticalConnection(Square s);

/**
* diagonalConnection(Square s) returns the diagonal connections
*
*Checks if there are opponents chips in between.
*
* @param Square s,
* @return DList l if Square s has diagonal connections, empty DList (OR NULL, OR EXCEPTION?) otherwise.
**/
protected boolean diagonalConnection(Square s);

/**
* Checks if there are opponents chips in between a connection
*
* @param DList l formed by two chips
* @return true if the there are no blocks, false otherwise
**/
protected boolean clearPath(DList l);
