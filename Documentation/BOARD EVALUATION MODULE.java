// Board Evaluation Module

/**
* evaluateBoard() is a helper method of alphaBetaPruning() that assigns scores to each board in the game tree search,
* following our evaluation algorithm.
* This method calls chipConnections(), in our chip connection module, and hasValidNetwork(), in our valid network module.
* @param the instance of our Gameboard, the depth level of our game tree search (an int)
* @return a score for the board (a double)
**/
protected double evaluateBoard(Gameboard board, int depth)