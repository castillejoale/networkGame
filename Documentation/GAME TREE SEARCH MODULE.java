// Game Tree Search Module

/**
* chooseMove() returns the best Move for a given Gameboard for "this" MachinePlayer
* chooseMove() calls allValidMoves() to see what potential moves can be made and then uses the alphaBetaPruning() helper method to
* conduct the game tree search algorithm.
* This method is in the MachinePlayer class.
* @param none - uses information from the Gameboard
* @return the best estimated Move
**/
public Move chooseMove()

/**
* alphaBetaPruning() is a helper method of chooseMove() that does a game tree search with alpha-beta pruning.
* This method is in the MachinePlayer class.
* This method calls evaluateBoard, in our board evaluation module.
* @param the instance of our Gameboard, the SquareList returned from allValidMoves()
* @return the best estimated Move
**/
protected Move alphaBetaPruning(Gameboard board, SquareList validmoves)

