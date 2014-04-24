/**
 * Assignment 4
 * Team Oreos
 * Name:					Student No.		GitHub
 * Maria Treacy				09724940		Maria47127	
 * Songjun (William) Lu		12251990		williamlin59 
 * Sofi Drury				12302361		SofiD
 * 
 * */

/**
 * Passing is no longer possible all valid moves must be made.
 * */
package backgammon;

import java.util.ArrayList;
import java.util.Scanner;

import backgammon.Board.Move;

public class HumanPlayer {
	private String userInput;
	private String[] split;
	private int[] from;
	private int[] spaces;
	private Scanner scanner;
	private int team;
	private Board theBoard;
	public HumanPlayer(Board myBoard, int selectedTeam){
		team =selectedTeam;
		theBoard = myBoard;
	}
	
	/*get the user input here and assign it to String Userinput;
	 * 
	 */

	private void getUserInput(){
		System.out.println("Please enter your moves, e.g '3-4 4-5': ");
		scanner = new Scanner(System.in);
		userInput = scanner.nextLine();
		System.out.println("You input: " +userInput);

	}
	/*check if the user input is quit/
	 * 
	 */
	private void quitCheck(){
		//to check if the player wants to quit the game
		if(userInput.equalsIgnoreCase("quit")){
			System.out.println("Good Bye");
			System.exit(0);
		}
	}

	/**[0-9]means digits from 0 to 9.
	 * {1,2}means either 1 or 2 digits
	 * ( )*-( )* means delimeter "-"between each digits
	 * [1-6] is the dice value which has to be between 1 and 6
	 * ( ) is the space between each input
	 * {1}/{3} repeat for 1 or 3 times depends on single or double move
	 * */
	private boolean SyntaxChecking(){
		boolean flag = false;
		//checks syntax of user input may have functionality in future to aslo accept bar but currently bar is 0 or 25
		if(userInput.matches("[0-9]{1,2}( )*-( )*[1-6](( )+[0-9]{1,2}( )*-( )*[1-6]){0,3}")){
			flag = true;
		}

		return flag;
	}


	/**
	 * Splits the input string into all the different components for moves
	 * */
	private boolean StringSplit(){
		boolean flag=true;
	try{
		 split=userInput.split("(-)|( )");
		 from=new int[split.length/2];
		 spaces=new int[split.length/2];
	    int j=0;
		int k=0;
		for(int i=0;i<split.length;i++){			//uses 2 arrays to keep the values for moves
			if(i%2==0){
				from[j]=Integer.parseInt(split[i]);
				j++;
			}
			else{
				spaces[k]=Integer.parseInt(split[i]);
				k++;
			}
		}
		}catch(NumberFormatException exception){
			flag=false;
		}

		return flag;
	}

	/**Checks to user choosing to pass their turn*/
	private boolean PassChecking(){
		return userInput.equalsIgnoreCase("pass");

	}

	/**
	 * Tests all the values for the special strings and syntax and validation, by calling the smaller methods
	 * Displays error to console if input doesn't pass.
	 * */
	private boolean FullChecking(ArrayList<Integer>diceroll){
		boolean flag=true;
		if(PassChecking()==true){
			System.out.println("Passed the round");
			flag=true;
		}
		else if(SyntaxChecking()==false){
			System.out.println("Invalid input");
			flag=false;
		}
		else if(StringSplit()==false){
			System.out.println("Wrong number of spaces");
			flag=false;
		}
		return flag;
	}
	/**
	 * Used to validate user input against all generated possible moves
	 * */
	private boolean maxPlayMatch(ArrayList<ArrayList<Move>> validMoves){
		boolean match = false;
		for(int i =0;i<validMoves.size();i++){
			if(moveMatch(validMoves.get(i))){
				match=true;
				break;
			}
		}
		return match;
	}
	
	/**
	 * checks input against a single list of moves
	 * */
	private boolean moveMatch(ArrayList<Move> pipMove){
		boolean result = true;
		for(int i = 0; i<from.length;i++){
			if(from[i] == pipMove.get(i).fromPosition && spaces[i]==pipMove.get(i).dice);
			else result = false;
		}
		return result;
	}

	/**
	 * controls how user input is processed by calling the methods order
	 * wouldn't move to the next turn until user inputs all correct. or no more moves
	 * */
	public boolean turn() throws ValueInvalidException, InvalidmoveException, InvalidSpacesException{
		boolean switcher=true;
		boolean noMoves = false;
		boolean syntax =false;
		ArrayList<Integer> diceroll=theBoard.diceRoll();
		theBoard.diceDisplay(diceroll);
		if(team == 1){
			System.out.println("Your turn O");
		}
		else if(team == -1){
			System.out.println("Your turn X");
		}
		
		do{
			int i=0;
			switcher=true;//boolean value for the outter loop
			noMoves = false;
			//output all valid moves here
			ArrayList<ArrayList<Move>> validMoves=new ArrayList<ArrayList<Move>>();
			validMoves=theBoard.getPossible(team, diceroll);
			validMoves=theBoard.sortingMoves(validMoves,diceroll);

			theBoard.printMoves(validMoves);
			if(validMoves.size()==0) noMoves =true;
			do{
				theBoard.boardAndPieces(team);
				if(noMoves==false){//only gets user input if there are possible moves
					getUserInput();
					quitCheck();//if its quit just exit straight;
					syntax = FullChecking(diceroll);
					if( syntax == true){//checks for valid moves if input is correct format
						if(maxPlayMatch(validMoves) ==  false){
						System.out.println("Invalid Moves Choose a PossibleMove");
						theBoard.printMoves(validMoves);
						syntax=false;
						}
					} 
				}	
				
			}while(syntax==false && noMoves==false );
			
			if(noMoves==false){//user input pass all the syntax checking and it's not pass its own round.
				int invalidInput=0;//the number of invalid move user inputed
				//@SuppressWarnings("unused");

	
				while(diceroll.size()!=0&&i<Math.min(from.length,validMoves.get(0).size())){//pick the smaller value from from.lenth and size.
					//try all the moves entered, with secondary validation checking as back up, used also to confirm generated moves were correct
					try{
						theBoard.move(from[i],spaces[i],team,theBoard.getTriangles(),false,diceroll);
					}
					catch(InvalidmoveException exception){
						System.out.println("Invalid move from "+from[i]);
						switcher=false;
						invalidInput++;
					}
					catch(ValueInvalidException exception){
						System.out.println("No Pieces on  triangle "+from[i]);
						switcher=false;
						invalidInput++;
					}
					catch(InvalidSpacesException exception){
						System.out.println("Invalid number of spaces "+spaces[i]);
						switcher=false;
						invalidInput++;
					}
					if(theBoard.gameWon()==true){
						System.out.println("Congratulation, you win the game");
						System.exit(0);
					}

					i++;
					
				}

				if(switcher==false ||diceroll.size()!=0){//let user know how many valid inputs needed to be input.
					validMoves=theBoard.getPossible(team, diceroll);
					if(validMoves.size()==0) noMoves=true;
					if(noMoves==true){
						System.out.println("No more valid moves");
						diceroll.clear();
						switcher = true;				//toggle to exit while loop and end turn
					}
					else{
						if(invalidInput>0)System.out.println("You need to input "+(invalidInput)+" valid input");
						else System.out.println("You Have Unused Dice");
						theBoard.diceDisplay(diceroll);
					}
				}	
			}
			else if(noMoves ==true){
				diceroll.clear();
				switcher = true;
			}

		}while(switcher==false||diceroll.size()!=0);
		return switcher;
	}
}

	