// interface for Square class

/** 
* The Square class constructor creates a Square that is referenced by the Gameboard and holds the (i,j) location of the Square within
* Gameboard, and the occupancy of that square.
* -1 = unoccupied
* 0 = black
* 1 = white
**/
protected Square(int i, int j, int color)

/**
* getColor() is a getter method that returns the color of the Square
* -1 = unoccupied
* 0 = black
* 1 = white
* @param none
* @return an int between -1 and 1
**/
protected int getColor()

/**
* setColor() is a setter method that changes the color of a Square after a move
* @param Square s, int color
* @return Square s with updated color field
**/
protected Square setColor(Square s, int color)

