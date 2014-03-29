package player;

import list.*;

public class TestChipConnections {

  public static void main (String[] args){

  	MachinePlayer white = new MachinePlayer(1);
  	GameBoard gameBoard = new GameBoard(white);

  	
    //Moves that white will make
    Move m1 = new Move(0,1);
    Move m2 = new Move(1,1);
    Move m3 = new Move(3,3);
    Move m4 = new Move(3,6);
    Move m5 = new Move(6,6);
    Move m6 = new Move(7,5);


  	/*
  	
    Move m7 = new Move(2,6);
    Move m8 = new Move(6,2);
    Move m9 = new Move(6,6);
    Move m10 = new Move(7,7);*/

  	gameBoard.updateGameBoard(m1,1);
  	gameBoard.updateGameBoard(m2,1);
  	gameBoard.updateGameBoard(m3,1);
  	gameBoard.updateGameBoard(m4,1);
  	gameBoard.updateGameBoard(m5,1);
    gameBoard.updateGameBoard(m6,1);
    /*gameBoard.updateGameBoard(m7,1);
    gameBoard.updateGameBoard(m8,1);
    gameBoard.updateGameBoard(m9,1);
    gameBoard.updateGameBoard(m10,1);*/

    //Moves that black will make
    
    /*Move m11 = new Move(3,4);
    gameBoard.updateGameBoard(m11,0);*/
    


  	System.out.println(gameBoard);

  	/*System.out.println(gameBoard.numberChipConnections(gameBoard.board[4][4]));*/
    System.out.println(gameBoard.hasValidNetwork(1));
    System.out.println(gameBoard.hasValidNetwork(0));


  }



}

