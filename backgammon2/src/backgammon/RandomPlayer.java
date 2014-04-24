/**
 * Assignment 4
 * Team Oreos
 * Name:					Student No.		GitHub
 * Maria Treacy				09724940		Maria47127	
 * Songjun (William) Lu		12251990		williamlin59 
 * Sofi Drury				12302361		SofiD
 * 
 * */
package backgammon;

import java.util.ArrayList;
import java.util.Random;

import backgammon.Board.Move;

public class RandomPlayer {

	private int team;
	private Board theBoard=new Board();
	public RandomPlayer(Board myBoard, int selectedTeam){
		team =selectedTeam;
		theBoard = myBoard;
	}
	//check if there is any possible moves exists.
	private boolean PassChecking(ArrayList<ArrayList<Move>> tempvalidMoves){
		return tempvalidMoves.size() == 0;
	}
	
	//randomly generates a number between 1 and the number of possibleMoves in the ArrayList available and picks one
	public int randomNoGen(int arrayListSize){
		
		
		Random randomNumbers = new Random();
		int noOfMoveSeq = (int) ( randomNumbers.nextInt(arrayListSize));
		return noOfMoveSeq;
		
	}
	
/*turn method for random player class. It contains all functionality that a turn needs.
 * 
 */

	public boolean turn(ArrayList<ArrayList<Move>> temp, ArrayList<Integer> diceroll) throws ValueInvalidException, InvalidmoveException, InvalidSpacesException{

		if(team == 1){
			System.out.println("Bot turn O");
		}
		else if(team == -1){
			System.out.println("Bot turn X");
		}

		theBoard.diceDisplay(diceroll);
		theBoard.boardAndPieces(team);
		
			if(PassChecking(temp)==false){	

					temp=theBoard.sortingMoves(temp,diceroll);
					theBoard.printMoves(temp);
					ArrayList<Move> randomValidMove=temp.get( randomNoGen(temp.size()));
					for(int i = 0; i<randomValidMove.size(); i++){
						theBoard.move(randomValidMove.get(i).fromPosition,randomValidMove.get(i).dice,team,theBoard.getTriangles(),true,diceroll);
						System.out.println("Bot plays "+randomValidMove.get(i).fromPosition+"-"+randomValidMove.get(i).dice);
					}
						
			}
			else{
				System.out.println("No valid Move, Pass the round");
			}
			return  true;

	}

}


 