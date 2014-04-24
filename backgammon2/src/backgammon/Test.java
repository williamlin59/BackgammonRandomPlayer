/**
 * Assignment 2
 * Team Oreos
 * Name:					Student No.		GitHub
 * Maria Treacy				09724940		Maria47127	
 * Songjun (William) Lu		12251990		williamlin59 
 * Sofi Drury				12302361		SofiD
 * 
 * */
/**
 * Test class now obsolete and  unused
 * */
package backgammon;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Test {
	public static void main(String[] args) throws ValueInvalidException,InvalidmoveException, InvalidSpacesException {
		/*int X = -1;
		int O = 1;
		int barX=25;
		int barO=0;
		Board myBoard = new Board();
		System.out.println("Starter Board: ");
		myBoard.boardAndPieces(X);
		myBoard.diceRoll();
		System.out.println("Checks a basic move moving X  (1,4,X)");
		myBoard.move(1, 4, X); // direction is not validated yet so can move in either direction
		
		myBoard.boardAndPieces(O);
		System.out.println("Pip move to Bar - O take X (19,1,O)");
		myBoard.move(19, 1, O); // O takes X, checks that pip moves to bar

		myBoard.boardAndPieces(X);
		System.out.println("Pip on Bar - X moves from bar (barX,3,X)");
		myBoard.move(25, 3,X); // checks can move pip on bar out - this moved the X from the bar, to 21
		
		myBoard.boardAndPieces(O);
		System.out.println("Regular O Move : (01,2,O)");
		myBoard.move(1, 2,O); // typical move; doesn't validate whose turn it is or how many spaces a player is allowed to move
		
		myBoard.boardAndPieces(X);
		System.out.println("Checks X can take O and moves to Bar : (19,3,X)");
		myBoard.move(19, 3, X);
		
		myBoard.boardAndPieces(O); // checks X can take an O and moves to bar (cant take a pip that isnt there)
		System.out.println("Pip on Bar - O moves from bar (barO,4,O)");
		myBoard.move(0, 4,O); // checks O moves to 04 on the grid
		
		myBoard.boardAndPieces(O);
		System.out.println("Moves Pip to the OFF position and displays O1 :(19,6,O)");
		myBoard.move(19, 6,O); // checks that you can move a pip to OFF position and displays for O's
	
		myBoard.boardAndPieces(O);
		System.out.println("Same move made again (19,6,O)\nto check there is now 2 O's in the OFF position");
		myBoard.move(19, 6,O); // checks the more then 2 O's can move to their correct OFF position
		
		myBoard.boardAndPieces(X);
		System.out.println("Moves X Pip to the OFF position and displays :(19,6,X)");
		myBoard.move(19, 6,X); // moving X to the OFF position just check it shows an X, had to fix this as previously showed O1
		myBoard.boardAndPieces(X);

		System.out.println("Send one X piece to BarX and let it take one O piece back to bar when it is off the bar\n (1,2,O) & (barX,5,X)");
		myBoard.move(1, 2,O);
		myBoard.move(25, 5,X);
		
		myBoard.boardAndPieces(O);
		System.out.println("Send one O piece to BarO and let it take one X piece back to bar when it is off the bar\n(19,1,X)(barO,5,O)");
		myBoard.move(19, 1,X);
		myBoard.move(0, 5,O);
		
		myBoard.boardAndPieces(O);
		System.out.println("Send all O pieces to the off and see if off can hold all pieces");
		myBoard.move(3, 28,O);
		System.out.println("3,25,O");
		myBoard.move(4, 25,O);
		System.out.println("4,25,O");
		myBoard.move(5, 25,O);
		System.out.println("5,25,O");
		myBoard.move(12, 25,O);
		System.out.println("12,25,O");
		myBoard.move(12, 25,O);
		System.out.println("12,25,O");
		myBoard.move(12, 25,O);
		System.out.println("12,25,O");
		myBoard.move(12, 25,O);
		System.out.println("12,25,O");
		myBoard.move(12, 25,O);
		System.out.println("12,25,O");
		myBoard.move(17, 25,O);
		System.out.println("17,25,O");
		myBoard.move(17, 25,O);
		System.out.println("17,25,O");
		myBoard.move(17, 25,O);
		System.out.println("17,25,O");
		myBoard.move(19, 25,O);
		System.out.println("19,25,O");
		myBoard.move(19, 25,O);
		System.out.println("19,25,O");
		myBoard.boardAndPieces(O);

		System.out.println("Send all X pieces to the off and see if off can hold all pieces");
		myBoard.move(barX, 1,X);
		System.out.println("barX,1,X");
		myBoard.move(19, 25,X);
		System.out.println("6,25,X");
		myBoard.move(19, 25,X);
		System.out.println("6,25,X");
		myBoard.move(17, 25,X);
		System.out.println("8,25,X");
		myBoard.move(17, 25,X);
		System.out.println("8,25,X");
		myBoard.move(17, 25,X);
		System.out.println("8,25,X");
		myBoard.move(12, 25,X);
		System.out.println("13,25,X");
		myBoard.move(12, 25,X);
		System.out.println("13,25,X");
		myBoard.move(12, 25,X);
		System.out.println("13,25,X");
		myBoard.move(12, 25,X);
		System.out.println("13,25,X");
		myBoard.move(12, 25,X);
		System.out.println("13,25,X");
		myBoard.move(5, 25,X);
		System.out.println("20,25,X");
		myBoard.move(3, 25,X);
		System.out.println("22,25,X");
		myBoard.move(1, 25,X);
		System.out.println("24,25,X");
		myBoard.boardAndPieces(X);
		
		System.out.println("Reset is called to check it works");
		myBoard.reset();
		myBoard.boardAndPieces(O);
		
		System.out.println("try to send more than one O's pieces to the bar");
		System.out.println("set up O pieces to be taken");
		myBoard.move(1, 22,O);
		System.out.println("1,22,O");
		myBoard.move(12, 10,O);
		System.out.println("12,10,O");
		myBoard.move(12, 9,O);
		System.out.println("12,9,O");
		myBoard.move(12, 8,O);
		System.out.println("12,8,O");
		myBoard.move(12, 7,O);
		System.out.println("12,7,O");
		myBoard.boardAndPieces(O);
		System.out.println("send more than one O's pieces to the bar");
		myBoard.move(1, 1,X);
		System.out.println("24,1,X");
		myBoard.move(1, 2,X);
		System.out.println("24,2,X");
		myBoard.move(2, 2,X);
		System.out.println("23,2,X");
		myBoard.move(3, 2,X);
		System.out.println("22,2,X");
		myBoard.boardAndPieces(O);
		myBoard.move(barO, 11, O);
		
		myBoard.reset();
		myBoard.boardAndPieces(O);
		
		System.out.println("try to send more than one X's pieces to the bar");
		System.out.println("set up X pieces to be taken");
		myBoard.move(12, 2,X);
		System.out.println("13,2,X");
		myBoard.move(12, 3,X);
		System.out.println("13,3,X");
		myBoard.move(12, 4,X);
		System.out.println("13,4,X");
		myBoard.move(12, 6,X);
		System.out.println("13,6,X");
		myBoard.boardAndPieces(X);
		System.out.println("send more than one X's pieces to the bar");
		myBoard.move(1, 4,O);
		System.out.println("1,4,O");
		myBoard.move(5, 2,O);
		System.out.println("5,2,O");
		myBoard.move(7, 2,O);
		System.out.println("7,2,O");
		myBoard.move(9, 1,O);
		System.out.println("9,1,O");
		myBoard.move(10, 1,O);
		System.out.println("10,1,O");
		myBoard.boardAndPieces(O);

		System.out.println("Reset is called to check it works");
		myBoard.reset();
		myBoard.boardAndPieces(O);

		System.out
				.println("Check the empty triangles to see if ValueInvalidExceptions works properly.");
		try {
			myBoard.move(2, 8,O);
			System.out.println("2,8,O");
		} catch (InvalidmoveException exception) {
			System.out.println("ValueInvalidException works properly.");
		}

		System.out
				.println("Check if one triangle is out of its own capacity, the InvalidMoveException works properly or not");
		try {
			myBoard.boardAndPieces(X);
			myBoard.move(17, 2,X);
			System.out.println("17,2,X");
			myBoard.move(17, 2,X);
			System.out.println("17,2,X");
			myBoard.move(17, 2,X);
			System.out.println("17,2,X");
			myBoard.move(12, 7,X);
			System.out.println("12,7,X");
			myBoard.move(12, 7,X);
			System.out.println("12,7,X");
			myBoard.boardAndPieces(X);
		} catch (InvalidmoveException exception) {
			System.out.println("InvalidmoveException works properly");
		}

		System.out
				.println("if one team tries to move one piece to over another team's pieces, the InvalidMoveException works properly or not");
		try {
			myBoard.move(19, 5,X);
			System.out.println("19,5,X");
		} catch (InvalidmoveException exception) {
			System.out.println("InvalidmoveException works properly");
		}

		System.out.println("_____________________________________________\nReset is called again");
		myBoard.reset();
		myBoard.boardAndPieces(O);
		ArrayList<Integer> dice;
//		System.out.println("Checks Dice");
//		for(int j=0;j<6;j++){
//			dice= myBoard.diceRoll(); // tests random dice function.
//		}
//		
//		
//		System.out.println("Expected: uniform distribution 10000/6=1666");
//		int freq[];
//		int dieValue;
//		freq = new int[] {0,0,0,0,0,0,0};
//		for (int i=0; i<10000; i++) {
//			dieValue = myBoard.diceRoll()[0];
//			if ((dieValue < 1) || (dieValue > 6)) {
//				System.out.println("Error");
//			}
//			freq[dieValue] += 1;
//		}
//		for (int i=1; i<=6; i++)  {
//			System.out.println("Frequency of " + i + " is " + freq[i]);
//		}
		
		*//**
		 * Frequency of 1 is 1666
		 * Frequency of 2 is 1706
		 * Frequency of 3 is 1650
		 * Frequency of 4 is 1696
		 * Frequency of 5 is 1644
		 * Frequency of 6 is 1638
		 * *//*
		
		
		int team;
		int space;
		String myTeam;
		String from;

		*//**
		 * Allows you to play the game entering your own input, allows for quick
		 * tests. Repeatedly using shows dice generates random numbers 1-6
		 *//*
		System.out.println("Optional extra checking.");
		String playAgain = "y";
		playAgain = JOptionPane.showInputDialog(null,"Enter y to make another move");
		if(playAgain == null){
			playAgain = "n";
		}
		while (playAgain.equalsIgnoreCase("y") ) {
			dice = myBoard.diceRoll();
			myTeam = JOptionPane.showInputDialog(null, "Choose a team X's or O's");
			if(myTeam.equalsIgnoreCase("x")){
				team = X;
				myBoard.boardAndPieces(team);
			}
			else if(myTeam.equalsIgnoreCase("o")){
				team = O;
				myBoard.boardAndPieces(team);
			}
			else{
				System.out.println("Invalid entry team defaulting to O's");
				team = O;
			}

	
			for(int i = 0; i <dice.size();i++){
				from = JOptionPane.showInputDialog(null, "Dice rolled: " + dice.get(i)+ " Move From: ");
				space = Integer.parseInt(from);
				myBoard.move(space, dice.get(i),team);
				System.out.println("Move:(" + from + "," + dice.get(i) + ")");
				myBoard.boardAndPieces(team);
			}
			playAgain = JOptionPane.showInputDialog(null,"Enter y to make another move");
			if(playAgain == null){
				playAgain = "n";
			}
		}

		System.out.println("Testing complete");
		System.exit(0);*/

	}
}
