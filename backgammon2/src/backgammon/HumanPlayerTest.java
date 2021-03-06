/**
 * Assignment 3
 * Team Oreos
 * Name:					Student No.		GitHub
 * Maria Treacy				09724940		Maria47127	
 * Songjun (William) Lu		12251990		williamlin59 
 * Sofi Drury				12302361		SofiD
 * 
 * */
package backgammon;

import java.util.Random;

public class HumanPlayerTest {
	private int sidePick(){
		Random randomNumbers = new Random();
		int diePlayerX1 = 0;
		int diePlayerO1 = 0;
		int side=0;
		while(diePlayerX1==diePlayerO1){
			diePlayerX1 = (int) (1 + randomNumbers.nextInt(6));
			diePlayerO1 = (int) (1 + randomNumbers.nextInt(6));
			System.out.println("Player1 O rolled "+diePlayerX1);
			System.out.println("Player2 X rolled "+diePlayerO1);
			if(diePlayerX1==diePlayerO1){
				System.out.println("Roll dices again");
			}
		}
		if(diePlayerX1<diePlayerO1){
			side=1;
		}
		return side;
		
	}

	/**
	 * Simple game set up to test out user input uses turn function in HumanPlayer class to test how it works.
	 * @throws InvalidSpacesException 
	 * */
	public static void main(String[] args) throws ValueInvalidException, InvalidmoveException, InvalidSpacesException{
		Board gameBoard = new Board();
		HumanPlayer playerO =new HumanPlayer(gameBoard,1);
		HumanPlayer playerX =new HumanPlayer(gameBoard,-1);
		HumanPlayerTest test=new HumanPlayerTest();
		int counter=test.sidePick();
		
		while(gameBoard.gameWon()!=true){
			boolean turn=false;
			if(gameBoard.gameWon()!=true){
				if(counter%2==0){
					while(turn==false){
						turn=playerO.turn();
					}
				}
				else{
					while(turn==false){
						turn=playerX.turn();
					}
				}
			}
			else{
				System.out.println("Congratulation, you win the game");
			}
			counter++;
		}
	
		 
	}
}