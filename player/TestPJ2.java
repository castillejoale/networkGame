
/* TestPJ2.java */

package player;

public class TestPJ2 {

	public static void main(String[] args) {

	//System.out.println("Testing MachinePlayer constructor...");

	MachinePlayer p1 = new MachinePlayer(0); // black
	//System.out.println("p1.machinePlayerColor should = 0 : " + p1.machinePlayerColor);
	//System.out.println("p1.opponentColor should = 1 : " + p1.opponentColor);

	MachinePlayer p2 = new MachinePlayer(1); // white
	//System.out.println("p1.machinePlayerColor should = 1 : " + p2.machinePlayerColor);
	//System.out.println("p1.opponentColor should = 0 : " + p2.opponentColor);

	//System.out.println("Testing allValidMoves() for clusters...");

/*
	GameBoard b1 = new GameBoard(p1);

	System.out.println("adding [3,0]");
	Move b1m1 = new Move(3,0);
	b1.board[3][0].setColor(0);
	p1.forceMove(b1m1);
	System.out.println("adding [2,1]");
	Move b1m2 = new Move(2,1);
	b1.board[2][1].setColor(0);
	p1.forceMove(b1m2);
	System.out.println("adding [5,2]");
	Move b1m3 = new Move(5,2);
	b1.board[5][2].setColor(0);
	p1.forceMove(b1m3);
	System.out.println("adding [5,3]");
	Move b1m4 = new Move(5,3);
	b1.board[5][3].setColor(0);
	p1.forceMove(b1m4);
	System.out.println("adding [2,4]");
	Move b1m5 = new Move(2,4);
	b1.board[2][4].setColor(0);
	p1.forceMove(b1m5);
	System.out.println("adding [6,5]");
	Move b1m6 = new Move(6,5);
	b1.board[6][5].setColor(0);
	p1.forceMove(b1m6);
	System.out.println("adding [1,6]");
	Move b1m7 = new Move(1,6);
	b1.board[1][6].setColor(0);
	p1.forceMove(b1m7);
	System.out.println("adding [4,7]");
	Move b1m8 = new Move(4,7);
	b1.board[4][7].setColor(0);
	p1.forceMove(b1m8);
	

	System.out.println("here's the board: " + "\n" + p1.board);

	System.out.println("here's the valid moves: " + b1.allValidMoves(p1.machinePlayerColor));

	System.out.println("should be false due to corner bounds: " + b1.isValidMove(new Move(0,0), p1.machinePlayerColor));
	System.out.println("should be false due to cluster: " + b1.isValidMove(new Move(1,0), p1.machinePlayerColor));
	System.out.println("should be false due to cluster: " + b1.isValidMove(new Move(2,0), p1.machinePlayerColor));
	System.out.println("should be false due to occupied: " + b1.isValidMove(new Move(3,0), p1.machinePlayerColor));
	System.out.println("should be false due to cluster: " + b1.isValidMove(new Move(4,0), p1.machinePlayerColor));
	System.out.println("should be true: " + b1.isValidMove(new Move(5,0), p1.machinePlayerColor));
	System.out.println("diagnosis: results of formsCluster(): " + b1.formsCluster(new Move(5,0), p1.machinePlayerColor));
	System.out.println("should be true: " + b1.isValidMove(new Move(6,0), p1.machinePlayerColor));
	System.out.println("should be false due to corner bounds: " + b1.isValidMove(new Move(7,0), p1.machinePlayerColor));
	System.out.println("should be false due to goal bounds : " + b1.isValidMove(new Move(0,1), p1.machinePlayerColor));
	System.out.println("should be false due to cluster: " + b1.isValidMove(new Move(1,1), p1.machinePlayerColor));
	System.out.println("should be false due to occupied: " + b1.isValidMove(new Move(2,1), p1.machinePlayerColor));
	System.out.println("should be false due to cluster: " + b1.isValidMove(new Move(3,1), p1.machinePlayerColor));
	System.out.println("should be false due to cluster: " + b1.isValidMove(new Move(4,1), p1.machinePlayerColor));
	System.out.println("should be false due to cluster: " + b1.isValidMove(new Move(5,1), p1.machinePlayerColor));
	System.out.println("should be false due to cluster: " + b1.isValidMove(new Move(6,1), p1.machinePlayerColor));
	System.out.println("should be false due to goal bounds: " + b1.isValidMove(new Move(7,1), p1.machinePlayerColor));
	System.out.println("should be false due to goal bounds: " + b1.isValidMove(new Move(0,2), p1.machinePlayerColor));
	System.out.println("should be false due to cluster: " + b1.isValidMove(new Move(1,2), p1.machinePlayerColor));
	System.out.println("should be false due to cluster: " + b1.isValidMove(new Move(2,2), p1.machinePlayerColor));
	System.out.println("should be false due to cluster: " + b1.isValidMove(new Move(3,2), p1.machinePlayerColor));
	System.out.println("should be false due to cluster: " + b1.isValidMove(new Move(4,2), p1.machinePlayerColor));
	System.out.println("should be false due to occupied: " + b1.isValidMove(new Move(5,2), p1.machinePlayerColor));
	System.out.println("should be false due to cluster: " + b1.isValidMove(new Move(6,2), p1.machinePlayerColor));
	System.out.println("should be false due to goal bounds: " + b1.isValidMove(new Move(7,2), p1.machinePlayerColor));
	System.out.println("should be false due to goal bounds: " + b1.isValidMove(new Move(0,3), p1.machinePlayerColor));
	System.out.println("should be true: " + b1.isValidMove(new Move(1,3), p1.machinePlayerColor));
	System.out.println("should be true: " + b1.isValidMove(new Move(2,3), p1.machinePlayerColor));
	System.out.println("should be true: " + b1.isValidMove(new Move(3,3), p1.machinePlayerColor));
	System.out.println("should be false due to cluster: " + b1.isValidMove(new Move(4,3), p1.machinePlayerColor));
	System.out.println("should be false due to occupied: " + b1.isValidMove(new Move(5,3), p1.machinePlayerColor));
	System.out.println("should be false due to cluster: " + b1.isValidMove(new Move(6,3), p1.machinePlayerColor));
	System.out.println("should be false due to goal bounds: " + b1.isValidMove(new Move(7,3), p1.machinePlayerColor));
	System.out.println("should be false due to goal bounds: " + b1.isValidMove(new Move(0,4), p1.machinePlayerColor));
	System.out.println("should be true: " + b1.isValidMove(new Move(1,4), p1.machinePlayerColor));
	System.out.println("should be false due to occupied: " + b1.isValidMove(new Move(2,4), p1.machinePlayerColor));
	System.out.println("should be true: " + b1.isValidMove(new Move(3,4), p1.machinePlayerColor));
	System.out.println("should be false due to cluster: " + b1.isValidMove(new Move(4,4), p1.machinePlayerColor));
	System.out.println("should be false due to cluster: " + b1.isValidMove(new Move(5,4), p1.machinePlayerColor));
	System.out.println("should be false due to cluster: " + b1.isValidMove(new Move(6,4), p1.machinePlayerColor));
	System.out.println("should be false due to goal bounds: " + b1.isValidMove(new Move(7,4), p1.machinePlayerColor));
	System.out.println("should be false due to goal bounds: " + b1.isValidMove(new Move(0,5), p1.machinePlayerColor));
	System.out.println("should be false due to cluster: " + b1.isValidMove(new Move(1,5), p1.machinePlayerColor));
	System.out.println("should be false due to cluster: " + b1.isValidMove(new Move(2,5), p1.machinePlayerColor));
	System.out.println("should be true: " + b1.isValidMove(new Move(3,5), p1.machinePlayerColor));
	System.out.println("should be true: " + b1.isValidMove(new Move(4,5), p1.machinePlayerColor));
	System.out.println("should be true: " + b1.isValidMove(new Move(5,5), p1.machinePlayerColor));
	System.out.println("should be false due to occupied: " + b1.isValidMove(new Move(6,5), p1.machinePlayerColor));
	System.out.println("should be false due to goal bounds: " + b1.isValidMove(new Move(7,5), p1.machinePlayerColor));
	System.out.println("should be false due to goal bounds: " + b1.isValidMove(new Move(0,6), p1.machinePlayerColor));
	System.out.println("should be false due to occupied: " + b1.isValidMove(new Move(1,6), p1.machinePlayerColor));
	System.out.println("should be true: " + b1.isValidMove(new Move(2,6), p1.machinePlayerColor));
	System.out.println("should be true: " + b1.isValidMove(new Move(3,6), p1.machinePlayerColor));
	System.out.println("should be true: " + b1.isValidMove(new Move(4,6), p1.machinePlayerColor));
	System.out.println("should be false due to cluster: " + b1.isValidMove(new Move(5,6), p1.machinePlayerColor));
	System.out.println("should be true: " + b1.isValidMove(new Move(6,6), p1.machinePlayerColor));
	System.out.println("should be false due to goal bounds: " + b1.isValidMove(new Move(7,6), p1.machinePlayerColor));
	System.out.println("should be false due to corner bounds: " + b1.isValidMove(new Move(0,7), p1.machinePlayerColor));
	System.out.println("should be true: " + b1.isValidMove(new Move(1,7), p1.machinePlayerColor));
	System.out.println("should be true: " + b1.isValidMove(new Move(2,7), p1.machinePlayerColor));
	System.out.println("should be true: " + b1.isValidMove(new Move(3,7), p1.machinePlayerColor));
	System.out.println("should be false due to occupied: " + b1.isValidMove(new Move(4,7), p1.machinePlayerColor));
	System.out.println("should be true: " + b1.isValidMove(new Move(5,7), p1.machinePlayerColor));
	System.out.println("should be true: " + b1.isValidMove(new Move(6,7), p1.machinePlayerColor));
	System.out.println("should be false due to corner bounds: " + b1.isValidMove(new Move(7,7), p1.machinePlayerColor));

	//System.out.println("testing hasChipsLeft(): should be true : " + b1.hasChipsLeft());

	//System.out.println("testing isOccupied(): should be true: " + b1.isOccupied(new Move(3,0)));
	//System.out.println("testing isOccupied(): should say 0: " + b1.board[b1m1.x1][b1m1.y1].getColor()); 
	//System.out.println("testing isOccupied(): should say false: " + b1.isValidMove(b1m1, p1.machinePlayerColor));

*/
/*
	GameBoard b2black = new GameBoard(p1);
		p1.forceMove(new Move(2,0));
		b2black.board[2][0].setColor(0);
		p1.forceMove(new Move(3,1));
		b2black.board[3][1].setColor(0);
		p1.forceMove(new Move(2,3));
		b2black.board[2][3].setColor(0);
		p1.forceMove(new Move(5,3));
		b2black.board[5][3].setColor(0);
		p1.forceMove(new Move(3,5));
		b2black.board[3][5].setColor(0);
		p1.forceMove(new Move(2,7));
		b2black.board[2][7].setColor(0);
		p1.opponentMove(new Move(0,1));
		b2black.board[0][1].setColor(1);
		p1.opponentMove(new Move(5,1));
		b2black.board[5][1].setColor(1);
		p1.opponentMove(new Move(4,2));
		b2black.board[4][2].setColor(1);
		p1.opponentMove(new Move(0,4));
		b2black.board[0][4].setColor(1);
		p1.opponentMove(new Move(1,5));
		b2black.board[1][5].setColor(1);
		p1.opponentMove(new Move(3,6));
		b2black.board[3][6].setColor(1);

		System.out.println("here's the board: " + "\n" + p1.board);

		System.out.println("here's the valid moves: " + b2black.allValidMoves(p1.machinePlayerColor));

		//PASSED!!!

*/
/*
	GameBoard b2white = new GameBoard(p2);
		p2.opponentMove(new Move(2,0));
		b2white.board[2][0].setColor(0);
		p2.opponentMove(new Move(3,1));
		b2white.board[3][1].setColor(0);
		p2.opponentMove(new Move(2,3));
		b2white.board[2][3].setColor(0);
		p2.opponentMove(new Move(5,3));
		b2white.board[5][3].setColor(0);
		p2.opponentMove(new Move(3,5));
		b2white.board[3][5].setColor(0);
		p2.opponentMove(new Move(2,7));
		b2white.board[2][7].setColor(0);
		p2.forceMove(new Move(0,1));
		b2white.board[0][1].setColor(1);
		p2.forceMove(new Move(5,1));
		b2white.board[5][1].setColor(1);
		p2.forceMove(new Move(4,2));
		b2white.board[4][2].setColor(1);
		p2.forceMove(new Move(0,4));
		b2white.board[0][4].setColor(1);
		p2.forceMove(new Move(1,5));
		b2white.board[1][5].setColor(1);
		p2.forceMove(new Move(3,6));
		b2white.board[3][6].setColor(1);

		System.out.println("here's the board: " + "\n" + p2.board);

		System.out.println("here's the valid moves: " + b2white.allValidMoves(p2.machinePlayerColor));

		//PASSED
*/

/*

//testing step moves
		GameBoard b3 = new GameBoard(p1);
			p1.forceMove(new Move(2,0));
			b3.updateGameBoard(new Move(2,0),0);
			p1.forceMove(new Move(6,0));
			b3.updateGameBoard(new Move(6,0),0);
			p1.forceMove(new Move(2,1));
			b3.updateGameBoard(new Move(2,1),0);
			p1.forceMove(new Move(4,2));
			b3.updateGameBoard(new Move(4,2),0);
			p1.forceMove(new Move(1,3));
			b3.updateGameBoard(new Move(1,3),0);
			p1.forceMove(new Move(3,3));
			b3.updateGameBoard(new Move(3,3),0);
			p1.forceMove(new Move(2,5));
			b3.updateGameBoard(new Move(2,5),0);
			p1.forceMove(new Move(3,5));
			b3.updateGameBoard(new Move(3,5),0);
			p1.forceMove(new Move(5,5));
			b3.updateGameBoard(new Move(5,5),0);
			p1.forceMove(new Move(6,5));
			b3.updateGameBoard(new Move(6,5),0);

			System.out.println("here's the board: " + "\n" + p1.board);
			System.out.println("here's the valid moves: " + b3.allValidMoves(p1.machinePlayerColor));

			*/
/*
	MachinePlayer testMachinePlayer = new MachinePlayer(1);
  		//Machine is white
 	  
 	  
 	  
  GameBoard board1 = testMachinePlayer.board;
 	  
	  Move m = new Move(0,1);
	  board1.updateGameBoard(m, 1);
	  System.out.println(board1);
	  System.out.println(board1.allValidMoves(0));

	  */

	  // testing choose move for almost network

	  MachinePlayer shir = new MachinePlayer(0); // black player

	  shir.forceMove(new Move(1,4));
	  shir.forceMove(new Move(4,1));
	  shir.forceMove(new Move(4,4));
	  shir.forceMove(new Move(3,6));
	  shir.forceMove(new Move(2,4));
	  shir.opponentMove(new Move(0,2));
	  shir.opponentMove(new Move(2,2));
	  shir.opponentMove(new Move(2,3));
	  shir.opponentMove(new Move(6,4));
	  shir.opponentMove(new Move(6,5));
	  shir.opponentMove(new Move(4,6));
	  
	  System.out.println("here's the board: \n" + shir.board);
	  System.out.println("chooseMove() of GameBoard b4 should return [3,0]: " + shir.chooseMove());
	}
}