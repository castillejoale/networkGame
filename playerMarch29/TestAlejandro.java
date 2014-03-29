package player;

import list.*;

public class TestAlejandro {

  public static void main (String[] args){

  	MachinePlayer white = new MachinePlayer(1);
  	GameBoard board1 = new GameBoard(white);

  	
    //Moves that white will make


    //MOVES in board configuration 1 (Alejandro's paper)
    /*Move m1 = new Move(0,1);
    Move m2 = new Move(1,1);
    Move m3 = new Move(3,3);
    Move m4 = new Move(3,6);
    Move m5 = new Move(6,6);
    Move m6 = new Move(7,5);*/

    //MOVES in board configuration 2 (Alejandro's paper)
    /*Move m1 = new Move(0,1);
    Move m2 = new Move(1,1);
    Move m3 = new Move(1,3);
    Move m4 = new Move(1,5);
    Move m5 = new Move(3,1);
    Move m6 = new Move(3,3);
    Move m7 = new Move(3,6);
    Move m8 = new Move(5,1);
    Move m9 = new Move(7,5);
    Move m10 = new Move(6,6);*/

    //MOVES in board configuration 3 (Alejandro's paper)
    /*Move m1 = new Move(0,1);
    Move m2 = new Move(1,1);
    Move m3 = new Move(1,5);
    Move m4 = new Move(2,5);
    Move m5 = new Move(4,3);
    Move m6 = new Move(7,3);*/

    //MOVES in board configuration 4 (Alejandro's paper)
    /*Move m1 = new Move(0,1);
    Move m2 = new Move(1,1);
    Move m3 = new Move(4,1);
    Move m4 = new Move(7,1);*/



    //MOVES in board configuration 5 (Alejandro's paper)
    /*Move m1 = new Move(0,1);
    Move m2 = new Move(1,1);
    Move m3 = new Move(1,5);
    Move m4 = new Move(2,5);
    Move m5 = new Move(4,3);
    Move m6 = new Move(7,3);
    Move m7 = new Move(5,2);*/

    //MOVES in board configuration 6 (Alejandro's paper)
    /*Move m1 = new Move(0,1);
    Move m2 = new Move(1,1);
    Move m3 = new Move(1,5);
    Move m4 = new Move(2,5);
    Move m5 = new Move(4,3);
    Move m6 = new Move(7,3);
    Move m7 = new Move(6,1);*/

    //MOVES in board configuration 7 (Alejandro's paper)
    /*Move m1 = new Move(0,1);
    Move m2 = new Move(1,1);
    Move m3 = new Move(1,5);
    Move m4 = new Move(2,5);
    Move m5 = new Move(4,3);
    Move m6 = new Move(7,3);
    Move m7 = new Move(5,2);*/

    //MOVES in board configuration 8 (Alejandro's paper)
    /*Move m1 = new Move(0,6);
    Move m2 = new Move(1,6);
    Move m3 = new Move(3,6);
    Move m4 = new Move(4,6);
    Move m5 = new Move(4,3);
    Move m6 = new Move(4,1);
    Move m7 = new Move(6,4);
    Move m8 = new Move(7,5);*/

    //MOVES in board configuration 9 (Alejandro's paper)
    /*Move m1 = new Move(0,5);
    Move m2 = new Move(1,4);
    Move m3 = new Move(3,6);
    Move m4 = new Move(4,6);
    Move m5 = new Move(4,3);
    Move m6 = new Move(4,2);
    Move m7 = new Move(6,4);
    Move m8 = new Move(7,4);*/

    //MOVES in board configuration 10 (Alejandro's paper)
    /*Move m1 = new Move(0,1);
    Move m2 = new Move(1,1);
    Move m3 = new Move(1,6);
    Move m4 = new Move(4,1);
    Move m5 = new Move(5,1);
    Move m6 = new Move(7,6);*/

    //MOVES in board configuration 11 (Alejandro's paper)
    /*Move m1 = new Move(0,1);
    Move m2 = new Move(1,1);
    Move m3 = new Move(1,6);
    Move m4 = new Move(6,1);
    Move m5 = new Move(5,1);
    Move m6 = new Move(7,6);*/

    //MOVES in board configuration 12 (Alejandro's paper)
    /*Move m1 = new Move(0,1);
    Move m2 = new Move(1,1);
    Move m3 = new Move(1,5);
    Move m4 = new Move(6,1);
    Move m5 = new Move(5,1);
    Move m6 = new Move(7,5);
    Move m7 = new Move(6,3);*/

    //MOVES in board configuration 13 (Alejandro's paper)
    /*Move m1 = new Move(0,1);
    Move m2 = new Move(2,1);
    Move m3 = new Move(3,4);
    Move m4 = new Move(5,6);
    Move m5 = new Move(7,4);
    Move m6 = new Move(7,6);*/

    //MOVES in board configuration 14 (Alejandro's paper)
    /*Move m1 = new Move(0,1);
    Move m2 = new Move(1,1);
    Move m3 = new Move(1,3);
    Move m4 = new Move(1,5);
    Move m5 = new Move(3,5);
    Move m6 = new Move(4,6);
    Move m7 = new Move(4,1);
    Move m8 = new Move(5,1);
    Move m9 = new Move(7,1);
    Move m10 = new Move(7,6);*/

    //MOVES in board configuration 15 (Alejandro's paper)
    /*Move m1 = new Move(0,1);
    Move m2 = new Move(2,2);
    Move m3 = new Move(0,4);
    Move m4 = new Move(2,4);
    Move m5 = new Move(4,2);
    Move m6 = new Move(4,3);
    Move m7 = new Move(7,3);*/

    //MOVES in board configuration 16 (Alejandro's paper)
    Move m1 = new Move(0,2);
    Move m2 = new Move(0,3);
    Move m3 = new Move(0,5);
    Move m4 = new Move(3,1);
    Move m5 = new Move(4,2);
    Move m6 = new Move(4,4);
    Move m7 = new Move(5,5);
    Move m8 = new Move(7,1);






    //Make moves

  	board1.updateGameBoard(m1,1);
  	board1.updateGameBoard(m2,1);
  	board1.updateGameBoard(m3,1);
  	board1.updateGameBoard(m4,1);
  	board1.updateGameBoard(m5,1);
    board1.updateGameBoard(m6,1);
    board1.updateGameBoard(m7,1);
    board1.updateGameBoard(m8,1);
    //board1.updateGameBoard(m9,1);
    //board1.updateGameBoard(m10,1);
    /*board1.updateGameBoard(m11,1);
    board1.updateGameBoard(m12,1);
    board1.updateGameBoard(m13,1);
    board1.updateGameBoard(m14,1);
    //board1.updateGameBoard(m15,1); //I CANT MAKE THIS MOVE
    */








    //Moves that black will make
    
    /*Move m11 = new Move(3,4);
    board1.updateGameBoard(m11,0);*/



    //Print board
  	System.out.println(board1);

    //Call chipConnections on white
    //DList lista1 = board1.chipConnections(board1.board[7][5]);
    //DList lista2 = board1.chipConnections(board1.board[1][5]);
    
    //board1.printChipConnections(lista1);
    //board1.printChipConnections(lista2);





    //Count the number of chip connections made
  	//System.out.println(board1.numberChipConnections(board1.board[1][5]));



    //Check if black has network
    //System.out.println(board1.hasValidNetwork(0));


    //Check if white has network
    System.out.println(board1.hasValidNetwork(1));

    //Check the score of the board
    //System.out.println(board1.evaluateBoard(4,1));

    


  }



}

