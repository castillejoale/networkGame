/* TestPJ2.java */

public class TestPJ2 {

	import player.*;
	import list.*;

	public static void main(String[] args) {

	System.out.println("Testing MachinePlayer constructor...");

	MachinePlayer player = new MachinePlayer(1);
	System.out.println("player.machinePlayerColor should = 1 : " + player.machinePlayerColor);
	System.out.println("player.opponentColor should = 0 : " + player.opponentColor);

	}

}