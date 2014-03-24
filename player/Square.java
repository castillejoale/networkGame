/* Square.java */

/** 
* This class will have the squares that make up our GameBoard.  Each GameBoard is 8x8, so there are always 64 Squares in each instance
* of a Gameboard.  A Square records the color of a given square of the GameBoard, represented by an int.
* -1 means the square is unoccupied, and doesn't have a chip on it.  0 means there is a black chip on the square.  1 means there is a white
* chip on the square.
**/

package player;
import list.*;

public class Square {

	private int color;
	private int[] location; 
	private boolean visited;
	private int type = 0; // -1 Is beginning line, 0 is normal square, 1 is ending line.

	/** 
	* The Square class constructor creates a Square that is referenced by the Gameboard and holds the (i,j) location of the Square within
	* Gameboard, and the occupancy of that square.
	* Note: We should never be using the constructor of Square.  Only GameBoard uses it.  We should only be using the getter and setter methods.
	* Another note: board[i][j] references a Square.  After a Move has been played, we need to have a line like the following:
	* board[i][j].setColor(color);
	* -1 = unoccupied
	* 0 = black
	* 1 = white
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

	protected int[] location() {
		return location;
	}

	protected boolean getVisited() {
		return visited;
	}

	protected void setVisited(boolean b) {
		this.visited = b;
	}

	protected int getType() {
		return type;
	}

	protected void setType(int i) {
		this.type = i;
	}

}

