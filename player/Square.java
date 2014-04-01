/* Square.java */

/** 
* This class will have the squares that make up our GameBoard.  Each GameBoard is 8x8, so there are always 64 Squares in each instance
* of a Gameboard.  A Square records the color of a given square of the GameBoard, represented by an int.
* -1 means the square is unoccupied, and doesn't have a chip on it.  0 means there is a black chip on the square.  1 means there is a white
* chip on the square.
**/

package player;

public class Square {

	private int color;
	private int[] location; 
	private boolean visited;
	private int type = 0; 

	/** 
	* The Square class constructor creates a Square that is referenced by the Gameboard and holds the (i,j) location of the Square within
	* Gameboard, and the occupancy of that square.
	* The color field for a square has the following values:  
	* -1 = unoccupied
	* 0 = black
	* 1 = white
	* A newly initialized square has visited field set to false. When a game board creates the squares, the type fielld of the square is 
	* set depending on the location of where this game board is on the board.
	**/
	protected Square(int color, int i, int j) {
		if (color < -1 || color > 1) {
			return;
		} else {
			this.color = color;
		}
		
		location = new int[2];
		location[0] = i;
		location[1] = j;
	}

	/**
	* getColor() is a getter method that returns the color of the Square
	* -1 = unoccupied
	* 0 = black
	* 1 = white
	* @param none
	* @return an int between -1 and 1
	**/
	protected int getColor() {
		return color;

	}

	/**
	* setColor() is a setter method that changes the color of a Square after a move
	* @param int color
	* @return Square s with updated color field
	**/
	protected void setColor(int color) {
		if (color < -1 || color > 1) {
			return;
		} else {
			this.color = color;
		}

	}
	
	/**
	 * location() will return the location of the Square on the game board
	 * @return an int array with the location of the Square on the game board
	 */
	protected int[] location() {
		return location;
	}

	/**
	 * getVisited() will get whether the square has been visited. This is used in the hasNetwork() method
	 * @return a boolean false (if not visited) or true (if visited)
	 */
	protected boolean getVisited() {
		return visited;
	}

	/**
	 * setVisited() sets whether the Square has been visited or not
	 * @param b is a boolean where b=true means the square is visited and b=false, otherwise.
	 */
	protected void setVisited(boolean b) {
		this.visited = b;
	}
	/**
	 * getType() returns the square type. 
	 * The type values are: -1 if the square is in the beginning line, 0 if it is a normal square,
	 *  1 if it is in an ending line.
	 * @return an int -1, 0, or 1.
	 */
	protected int getType() {
		return type;
	}

	/**
	 * setType() will set the square's type when the square is first initialized for the game board
	 * @param i is an int -1 if the square is in the beginning line, 0 if it is a normal square,
	 *  1 if it is in an ending line.
	 */
	protected void setType(int i) {
		this.type = i;
	}

}

