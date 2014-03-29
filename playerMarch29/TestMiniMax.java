package player;

public class TestMiniMax {
	
	public static void main (String[] args){
		
			MachinePlayer shir = new MachinePlayer(0);

	

		  shir.forceMove(new Move(1,4));
		  shir.forceMove(new Move(4,1));
		  shir.forceMove(new Move(4,4));
		  shir.forceMove(new Move(3,6));
		  shir.forceMove(new Move(2,7));
		  shir.opponentMove(new Move(0,2));
		  shir.opponentMove(new Move(2,2));
		  shir.opponentMove(new Move(2,3));
		  shir.opponentMove(new Move(6,4));
		  shir.opponentMove(new Move(6,5));
		  shir.opponentMove(new Move(4,6));
		 	
		  //System.out.println(shir.board);
		  
		  System.out.println("chooseMove() of GameBoard should return [3,0]: " + shir.chooseMove());
	  	
}
}
