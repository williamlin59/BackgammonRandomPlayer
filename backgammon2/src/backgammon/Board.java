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
*   Layout of code in Board Class;
*	defined variables,
*	inner class,
*	methods to access these variables
*	constructor 
*	display methods
*	dice methods & display
*	move methods
*	validation methods
*	possible moves generating methods
* */

package backgammon;

	import java.util.*;

	public class Board {
		//defined for repeated use later and ease of understanding code
		final static int CAPACITY = 9;
		final static int TEAM_PIECES = 15;
		final static int BOARD_SIZE=28;
		final static int TOP_OUTERBOARD_START=13;
		final static int TOP_OUTERBOARD_END=18;
		final static int BOTTOM_OUTERBOARD_START=12;
		final static int BOTTOM_OUTERBOARD_END=7;
		final static int BLACK_HOMEBOARD_START=19;
		final static int BLACK_HOMEBOARD_END=24;
		final static int WHITE_HOMEBOARD_START=6;
		final static int WHITE_HOMEBOARD_END=1;
		final static int X = -1;
		final static int O = 1;
		//triangles is the array for the board named after the triangles on a physical board
		private static int triangles[] = new int[BOARD_SIZE]; 	//the off spaces are at the ends of the array [0] &[25]
		//variables for the bar which is located in the array.
		final static int barX=26;
		final static int barO=27;
		private ArrayList<Integer> dieArray;
		
		/**
		 * Object to hold full details of a move
		 * */
		class Move{
			protected int dice;
			protected int fromPosition;
			public Move (int fromPosition, int dice){
				this. dice = dice;
				this. fromPosition = fromPosition;
			}
		}	

		public Board(){
			reset();		
		}

		public int[] getTriangles(){
			return triangles;
		}
		
		public void reset(){
			//use negative number for X and positive number for O
			//X is for black and O is for white
			for(int i=0;i<BOARD_SIZE;i++){
				triangles[i]=0;
			}
			triangles[1]=  (2 * O);
			triangles[6]=  (5 * X);
			triangles[8]=  (3 * X);
			triangles[12]= (5 * O);
			triangles[13]= (5 * X);
			triangles[17]= (3 * O);
			triangles[19]= (5 * O);
			triangles[24]= (2 * X);

			
		}
		/**
		 * This method copy board j to i.
		 */
		public void copyBoard(int[] i, int[] j){
			System.arraycopy( j, 0, i, 0, j.length );
			
		}
		/**
		 * The function to display the board calls smaller methods which are for the different areas of the board.
		 * Uses defined constants as input to those methods.
		 * */
		public void boardAndPieces(int team) throws ValueInvalidException{
			String teamName="";
			if(team == X)	teamName +="X";
			else			 teamName +="O";
			System.out.println("______________________________________________________");
			System.out.println("The Board According to "+teamName); 
			System.out.println("______________________________________________________");
			topBoard(TOP_OUTERBOARD_START,TOP_OUTERBOARD_END,"BAR");
			topBoard(BLACK_HOMEBOARD_START,BLACK_HOMEBOARD_END,"OFF");
			System.out.print("\n");
			if(team == O){
				topPieces(TOP_OUTERBOARD_START,TOP_OUTERBOARD_END);
				barAndOffPieces(triangles[barX]);
				topPieces(BLACK_HOMEBOARD_START,BLACK_HOMEBOARD_END);
				barAndOffPieces(triangles[25]);
				System.out.print("\n\n\n");
					
				bottomPieces(BOTTOM_OUTERBOARD_START,BOTTOM_OUTERBOARD_END);
				barAndOffPieces(triangles[barO]);
				bottomPieces(WHITE_HOMEBOARD_START,WHITE_HOMEBOARD_END);
				barAndOffPieces(triangles[0]);
			}
			else {//team=X flip it
				bottomPieces(BOTTOM_OUTERBOARD_START,BOTTOM_OUTERBOARD_END);
				barAndOffPieces(triangles[barO]);
				bottomPieces(WHITE_HOMEBOARD_START,WHITE_HOMEBOARD_END);
				barAndOffPieces(triangles[0]);
				System.out.print("\n\n\n");
				topPieces(TOP_OUTERBOARD_START,TOP_OUTERBOARD_END);
				barAndOffPieces(triangles[barX]);
				topPieces(BLACK_HOMEBOARD_START,BLACK_HOMEBOARD_END);
				barAndOffPieces(triangles[25]);
			}
			System.out.print("\n");					
			bottomBoard(BOTTOM_OUTERBOARD_START,BOTTOM_OUTERBOARD_END,"BAR");
			bottomBoard(WHITE_HOMEBOARD_START,WHITE_HOMEBOARD_END,"OFF");
				
			System.out.print("\n\n");		
		}

		/**
		 * For displaying the top line of the board with the numbering of the spaces
		 * */
		private void topBoard(int startTriangle, int endTriangle, String BarOrOff){
				for(int i=startTriangle;i<endTriangle;i++){
					if(i==startTriangle||i==endTriangle){
						System.out.printf("%02d",i);//using %02d to output two digits integer on the board

					}
					else{
						System.out.print('+');
					}
					System.out.print("--");
				}
				System.out.printf("%02d %s  ",endTriangle,BarOrOff); //%s shows the string either bar or off on the board
		}

		/**
		 * For displaying the bottom line of the board with the numbering of the spaces
		 * */
		private void bottomBoard(int startTriangle, int endTriangle, String BarOrOff){
			for(int i=startTriangle;i>endTriangle;i--){
				if(i==startTriangle||i==endTriangle){
					System.out.printf("%02d",i);
				}
				else{
					System.out.print('+');
				}
				System.out.print("--");
			}
			System.out.printf("%02d %s  ",endTriangle,BarOrOff);
		}

		/**
		 * For displaying the pips along the top section of the board
		 * */
		private void topPieces(int startTriangle, int endTriangle){
			for(int i=startTriangle;i<=endTriangle;i++){
				if(triangles[i]==0){
					if(i==startTriangle){//space control, makes the board looks neat and tidy
						System.out.print(" |");
					}
					else{
						System.out.print("|");
					}
					System.out.print("  ");
				}
				else{
					if(triangles[i]<0){
						System.out.print("X");
					}
					else{
						System.out.print("O");
					}	
					System.out.print(Math.abs(triangles[i]));//print out the absolute value(our team use positive number for O and negative number for X)
					if(i==startTriangle){
						System.out.print("  ");
					}
					else{
						System.out.print(" ");
					}
				}
			}
		}

		/**
		 * For displaying the pips along the bottom section of the board
		 * */
		private void bottomPieces(int startTriangle, int endTriangle){
			for(int i=startTriangle;i>=endTriangle;i--){
				if(triangles[i]==0){
					if(i==startTriangle){
						System.out.print(" |");
					}
					else{
						System.out.print("|");
					}
					System.out.print("  ");
				}
				else{
					if(triangles[i]<0){
						System.out.print("X");
					}
					else{
						System.out.print("O");
					}
					System.out.print(Math.abs(triangles[i]));
					if(i==startTriangle){
						System.out.print("  ");
					}
					else{
						System.out.print(" ");
					}
				}
			}
		}

		/**
		 * For displaying the pips in the bar or off sections of the board
		 * */
		private void barAndOffPieces(int barOrOffValue) throws ValueInvalidException{
			System.out.print(" ");
			if(barOrOffValue==triangles[barX]){
				System.out.print((triangles[barX]!=0)?"X"+Math.abs(triangles[barX]):"_ ");//use one line if statement to make the code looks neat.
				System.out.print("  ");
			}
			else if(barOrOffValue==triangles[barO]){
				System.out.print((triangles[barO]!=0)?"O"+Math.abs(triangles[barO]):"_ ");
				System.out.print("  ");
			}
			else if(barOrOffValue==triangles[0]){
				System.out.print((triangles[0]!=0)?"X"+Math.abs(triangles[0]):" _");
			}
			else if(barOrOffValue==triangles[25]){
				System.out.print((triangles[25]!=0)?"O"+Math.abs(triangles[25]):" _");
			}
			else{
				throw new ValueInvalidException();
			}
		}		
		/**
		 * diceRoll gets random values and returns the arraylist of results, 
		 * */
		public ArrayList<Integer> diceRoll() {//changed the return type to arraylist
			Random randomNumbers = new Random();
			dieArray=new ArrayList<Integer>();
			int die1 = (int) (1 + randomNumbers.nextInt(6));
			int die2 = (int) (1 + randomNumbers.nextInt(6));
			if(die1 == die2){
				for(int i=0;i<4;i++){
					dieArray.add(die1);			
				}
			}
			else{	
				
				dieArray.add ( die1);
				dieArray.add( die2);				
			}			
			return dieArray;
		}

		/**
		 * method to display the dice in the console when diceRoll() is called
		 * */
		public void diceDisplay(ArrayList<Integer> dieArray){
			System.out.println("______________________________________________________");
			System.out.println("You rolled:");
			if(dieArray.size()==1){
				System.out.println("+-------+");
				for(int i = 1;i<4;i++){
					dieGraphic(i,dieArray.get(0));
					System.out.print("\n");
				}
				System.out.println("+-------+");	
			}
			else if(dieArray.size() == 2){
				System.out.println("+-------+\t+-------+");
				for(int i = 1;i<4;i++){
					dieGraphic(i,dieArray.get(0));
					dieGraphic(i,dieArray.get(1));
					System.out.print("\n");
				}
				System.out.println("+-------+\t+-------+");
			}
			else if(dieArray.size()== 3){
				System.out.println("+-------+\t+-------+\t+-------+");
				for(int i = 1;i<4;i++){
					dieGraphic(i,dieArray.get(0));
					dieGraphic(i,dieArray.get(1));
					dieGraphic(i,dieArray.get(1));
					System.out.print("\n");
				}
				System.out.println("+-------+\t+-------+\t+-------+");
			}
			else {
				System.out.println("DOUBLES!");
				System.out.println("+-------+\t+-------+\t+-------+\t+-------+");
				for(int i = 1;i<4;i++){
					dieGraphic(i,dieArray.get(0));
					dieGraphic(i,dieArray.get(1));
					dieGraphic(i,dieArray.get(2));
					dieGraphic(i,dieArray.get(3));
					System.out.print("\n");
				}
				System.out.println("+-------+\t+-------+\t+-------+\t+-------+");
			}

		}

		/**
		 * This method prints the text associated with each die when collected correctly by diceGraph()
		 * since the dice are printed out side by side the top lines must all be printed  before the new line
		 * */
		public void dieGraphic(int line, int no){
			if(line == 1){
				if(no == 1){
					System.out.print("|       |\t");
				}
				else if (no == 2){
					System.out.print("|    *  |\t");
				}
				else if(no == 3){
					System.out.print("|     * |\t");
				}
				else if(no == 4){
					System.out.print("|  * *  |\t");
				}
				else if(no == 5){
					System.out.print("|  * *  |\t");
				}
				else if(no == 6){
					System.out.print("|  * *  |\t");
				}
			}
			else if(line == 2){
				if(no == 1){
					System.out.print("|   *   |\t");
				}
				else if (no == 2){
					System.out.print("|       |\t");
				}
				else if(no == 3){
					System.out.print("|   *   |\t");
				}
				else if(no == 4){
					System.out.print("|       |\t");
				}
				else if(no == 5){
					System.out.print("|   *   |\t");
				}
				else if(no == 6){
					System.out.print("|  * *  |\t");
				}
			}
			else if(line == 3){
				if(no == 1){
					System.out.print("|       |\t");
				}
				else if (no == 2){
					System.out.print("|  *    |\t");
				}
				else if(no == 3){
					System.out.print("| *     |\t");
				}
				else if(no == 4){
					System.out.print("|  * *  |\t");
				}
				else if(no == 5){
					System.out.print("|  * *  |\t");
				}
				else if(no == 6){
					System.out.print("|  * *  |\t");
				}
			}
		}
		/**
		 *  What the dice should look like when displayed
		 * |     |	|   * |	|    *|	| * * |	| * * |	| * * |
		 * |  *  |	|     | |  *  |	|     |	|  *  |	| * * |
		 * |     |	| *   |	|*    |	| * * |	| * * |	| * * |
		 * */


		/**
		 * Method for making the moves calls smaller methods for sub task of making a move.
		 * Booleans are used to distinguish between moves to the board and moves as part of validation and generating possible moves
		 * */
		public boolean move(int from, int spaces, int team, int[] triangles, boolean tempBoard,ArrayList<Integer> Dice) throws ValueInvalidException, InvalidmoveException, InvalidSpacesException{
			boolean moveStatus=false;
			int toHere;
			int to;
			if(team==X){								//x needs to be changed to correct format for triangles array. not user input style
				if(from!=25) 		from=25+(-1*from);
				else if(from==0)	from=25;
			}
			if(team==O){
				if(from==25)		from=0;
			}
			toHere = from + (spaces*team);
			if(toHere>25)			to = 25;
			else if(toHere<0)		to = 0;
			else					to = toHere;
			
			if(validationCheck(from,spaces,team,to,true,triangles,Dice)==true){
				moveStatus =true;
				movePip(from,team,to,triangles);
				if(!tempBoard){
					consumeDie(spaces,dieArray);
				}	
			}	
			return moveStatus;
		}	
		/**
		 * Called by move method when appropriate
		 * Input is the position moving from, number of spaces, and team moving
		 * */
		private void movePip(int fromPosition, int team,int toPosition, int [] triangles) throws InvalidmoveException, ValueInvalidException{	
			int otherTeam = -1*team;
			if(triangles[toPosition]== otherTeam){							//only true when 1 pip of the opposing team occupies the space
				removePipForMove(fromPosition,team,triangles);
				sendOponentPipToBar(toPosition, team,triangles);
			}
			else if(toPosition >24 || toPosition <1){	
				removePipForMove(fromPosition,team,triangles);
				movePipOff(team,triangles);									//movePipOff moves a piece to the corresponding off position
			}

			else{															//the space is less than capacity with 0 or the teams pieces there 
				removePipForMove(fromPosition,team,triangles);
				triangles[toPosition]+= team;
			}

		}

		/**
		 * Method to ensure the location the pip was moved from is reduced by 1
		 * */
		private void removePipForMove(int fromPosition, int team, int[] triangles) throws ValueInvalidException{
			if(fromPosition>0 && fromPosition<25){		//pip moves from a normal space
				triangles[fromPosition] -= team;
			}
			else if(fromPosition == 0 && team == O){	//pip moves from the barO and the correct array element needs to be updated
				triangles[barO]-=team;
			}
			else if(fromPosition ==25 && team == X){	//pip moves from the barX and the correct array element needs to be updated
				triangles[barX]-=team;
			}
		}


		/**
		 * Moves a pip to the corresponding off section
		 * */
		private void movePipOff(int team,int[] triangles){
			if (team == O){
				triangles[25]+=O;;
			}
			else if (team == X){
				triangles[0]+=X;
			}
		}


		/**
		 * For when a pip is taken and sent to the bar
		 * */
		private void sendOponentPipToBar(int position, int team, int[] triangles){
			if(team==O && triangles[position]== X){
				triangles[position]=O;
				triangles[barX]+=X;
			}
			else if(team==X && triangles[position]== O){
				triangles[position]=X;
				triangles[barO]+=O;
			}
			
		}
		
		/**
		 * When a move is made the corresponding die is remove from array list so it can't be reused   
		 * */
		private void consumeDie(int spaces,ArrayList<Integer> Dice){
			boolean dieMatch = false;
			int i=0;
			while(i < Dice.size() && dieMatch==false ){
				if(Dice.get(i)== spaces) {
					Dice.remove(i);
					dieMatch=true;
				}
				else{
					i++;
				}
			}
		}
		/**
		 * Validation checks if a move is valid using smaller methods to check different rules
		 * */
		public boolean validationCheck(int from, int spaces, int team,int to, boolean move, int []triangles,ArrayList<Integer> Dice) throws ValueInvalidException, InvalidmoveException, InvalidSpacesException{
			boolean result = true;
			result = checkDie(spaces,move,Dice);
			if (result == true)	result=checkFrom(from,spaces,move,triangles);
			if (result == true) result=checkSpaceContents(to,from,team,move,triangles);
			if (result == true) result=checkBar(team,from,move,triangles);
			if (result == true){
				if(to == 25 || to == 0) {
					result=checkingPipsCanMoveOff(team,move,triangles);
					if (result == true) result = bearOffExactSpaces( team,  spaces, from, to,move,triangles);
				}
			}
			if (result == false) System.out.println("Move does not match die value");
			
			return result;
		}

		/**
		 * Checks if the bar is empty, if not that a move is made from the bar
		 * */
		private boolean checkBar(int team, int from, boolean move, int []triangles) throws InvalidmoveException{
			boolean barResult =true;
			if(team == X && (Math.abs(triangles[barX])> 0) && from!=25){
				if(move)System.out.println("Must move pieces from the bar first");
				barResult = false;
				throw new InvalidmoveException();

			}
			else if(team == O && (Math.abs(triangles[barO])> 0) && from!=0){
				if(move)System.out.println("Can only move pieces from the bar");
				barResult =false;
				throw new InvalidmoveException();
		
			}
			return barResult;
		}
		/**
		 * Checks the contents of the space for validation
		 * */
		private boolean checkSpaceContents(int toPosition, int fromPosition, int team,boolean move, int [] triangles) throws InvalidmoveException{
			boolean spaceResult = true; 
			if(((triangles[toPosition]) > 1 && team == X) ||( (triangles[toPosition]) <-1 && team == O)  ){	
					if(move)System.out.println("Can't move to a position ocupied by more than one oponent pip");
					spaceResult = false;
					 throw new InvalidmoveException();
				}

				else if(fromPosition<25&&fromPosition>0&&(triangles[fromPosition]*team)<0){							
					if(move)System.out.println("None of your pips in this space");
					spaceResult = false;
					throw new InvalidmoveException();
				}
				else if((Math.abs(triangles[toPosition])==CAPACITY) && (toPosition > 0 && toPosition < 25)){	
					if(move)System.out.println("Max of 9 pips on a space");
					spaceResult = false;
					throw new InvalidmoveException();
				}
			return spaceResult;
		}
		
		/**
		 * Checks the space the pip is moving from
		 * */
		private boolean checkFrom(int from,int spaces,boolean move, int [] triangles) throws ValueInvalidException, InvalidmoveException{
			boolean fromResult =true;
			if( from < 0  || from >25 || spaces<=0){	 //cannot move from off or a space with no pip & no. of spaces must be >0
				if(move){
					if(from < 0  || from >25) System.out.println("Not a valid space to move from");
					if(spaces <= 0)System.out.println("Number of spaces to move must match a die value");
				}
				fromResult = false;
				throw new ValueInvalidException();
			}
			else {
				if(from > 0 && from < 25 && Math.abs(triangles[from])<=0){
					if(move)System.out.println("No pip in the selected space");
					fromResult = false;
					throw new InvalidmoveException();
				}

				else if(from == 25 && (Math.abs(triangles[barX]) <=0)){
					if(move)System.out.println("Invaild move, the barX is empty");
					fromResult =false;
					throw new InvalidmoveException();
				}
				else if(from == 0 && (Math.abs(triangles[barO]) <=0)){
					if(move)System.out.println("Invaild move, the barO is empty");
					fromResult = false;
					throw new InvalidmoveException();
				}

			}
			return fromResult;
		}
		/**
		 * Checks if a move uses a die that was rolled (consumed ones are removed from the Dice arraylist)
		 * */
		private boolean checkDie(int spaces,boolean move,ArrayList<Integer> Dice) throws InvalidSpacesException{
			int die;
			boolean dieMatch = false;
			for(int i=0; i<Dice.size();i++){
				die = Dice.get(i);
				if(die == spaces){
					dieMatch=true;	
				}
			}
			if(dieMatch == false) {
				if(move)System.out.println("Move must match a die not yet used");
				throw new InvalidSpacesException();
			}
			return dieMatch;
		}
		
		/**
		 * Checks if it is valid to bear off (not the actual move, bearOffExactSpaces() does that)
		 * */
		private boolean checkingPipsCanMoveOff(int team,boolean move, int[] triangles) throws InvalidmoveException, ValueInvalidException{
			int pipCounter=0;
			boolean validBear = true;
			if(team == X){
				for(int i=6; i>=0; i--){			//cycle through the inner board counting X pips
					if(triangles[i] <0){
						pipCounter+=Math.abs(triangles[i]);
					}
				}
				if(Math.abs(pipCounter)<15){		//the number of pips in the section is less than it should be for bearing off to be valid
					if(move)System.out.println("All pips must be on the inner board or off to bear off.");
					validBear =false;
					throw new InvalidmoveException();
				}				
			}
			
			if(team == O){
				for(int i=19; i<=25; i++){			//cycle through the inner board counting O pips
					if(triangles[i] >0){
						pipCounter+=triangles[i];
					}
				}
				if(Math.abs(pipCounter)<15){		//the number of pips in the section is less than it should be for bearing off to be valid
					if(move)System.out.println("All pips must be on the inner board or off to bear off.");
					validBear =false;
					throw new InvalidmoveException();
				}
			}
			return validBear;
		}
		/**
		 * Checks when bearing off if the move is valid as per backgammon rules
		 * */
		private boolean bearOffExactSpaces(int team, int spaces, int from, int to,boolean move,int triangles[]) throws InvalidmoveException{
			boolean exactResult =true;
			int i;
			int exactSpaces; 
			if(team == X){	
				exactSpaces = to + spaces;
				if(triangles[exactSpaces] < 0 && exactSpaces >0){								//checks if a pip can bear off using the selected die in the exact number of moves
					if(exactSpaces == from) exactResult =true;
					
					else{
						exactResult =false;
						if(move)System.out.println("Must bear off using exact die roll when possible ");
						throw new InvalidmoveException();
					}
				}
				else{
					i=6;
					while(i > 0 && exactResult ==true){
						
						if(triangles[i] <0 && from!=i && i>exactSpaces){							//checks if a normal move closer to off can be make with the selected die
							if(move)System.out.println("Invaild move, must be exact or no other moves, to bear off");
							exactResult=false;
							throw new InvalidmoveException();
						}
						else if(triangles[i] <0 && from!=i && i<exactSpaces){						//checks for pip with is closest to the exact number of moves to bear off
							if(move)System.out.println("Invaild move, must move pip on next largest position");
							exactResult=false;
							throw new InvalidmoveException();
						}
						if(i == from){
							i=0;
						}
						else{
							i--;
						}
					}
				
				}
			}	
			if(team == O){	
				exactSpaces = to - spaces;
				if(triangles[exactSpaces] > 0 && exactSpaces <25){								//checks if a pip can bear off using the selected die in the exact number of moves
					if(exactSpaces == from) exactResult =true;
					
					else{
						exactResult =false;
						if(move)System.out.println("Must bear off using exact die roll when possible ");
						throw new InvalidmoveException();
					}
				}	
				else{	
					i=19;
					while(i <25 && exactResult ==true){
						
						if(triangles[i] >0 && from!=i && i<exactSpaces){							//checks if a normal move closer to off can be make with the selected die
							if(move)System.out.println("Invaild move, must be exact or no other moves to bear off");
							exactResult=false;
							throw new InvalidmoveException();
						}
						else if(triangles[i] >0 && from!=i && i>exactSpaces){						//checks for pip with is closest to the exact number of moves to bear off
							if(move)System.out.println("Invaild move, must pip move pip on next largest position");			
							exactResult=false;
							throw new InvalidmoveException();
						}
						if(i == from){
							i=25;
						}
						else{
							i++;
						}
							
					}
					
				}
			}	
			return exactResult;
		}
		/**
		 * Checks if there is a winner for the game. And the type of win it is
		 * @throws ValueInvalidException 
		 * */
		public boolean gameWon() throws ValueInvalidException{
			int pipsInner=0;
			boolean winner =false;
			if(Math.abs(triangles[25])==15){
				if((triangles[0])!= 0){
					System.out.println("Player O wins the game, it is a single.");
					
				}
				else{
					for(int i=19;i<25;i++){
						if(triangles[i]<0)pipsInner+=triangles[i];
					}
					if(pipsInner>0 || triangles[barX] !=0){
						System.out.println("Player O wins the game, it is a BackGammon");
						boardAndPieces(O);

					}
					else System.out.println("Player O wins the game, it is a Gammon");
						boardAndPieces(X);

				}
				winner = true;
			}
			else if(Math.abs(triangles[0])==15){
				if((triangles[25])!= 0){
					System.out.println("Player X wins the game, it is a single.");
				}
				else{
					for(int i=1;i<7;i++){
					if(triangles[i]>0)pipsInner+=triangles[i];
					}
					if(pipsInner>0 || triangles[barO] !=0){
						System.out.println("Player X wins the game, it is a BackGammon");
					}
					else System.out.println("Player X wins the game, it is a Gammon");
			
					
				}
				winner = true;
			}
			return winner;
		}

		
		

		
	
	
	
		/**
		 * Checks for possible moves with remaining dice.
		 * uses validationCheck()
		 * */
		@SuppressWarnings("unused")
		public ArrayList<Move> possibleMoves(int team, int[] triangles,int die,ArrayList<Integer> Dice){
			ArrayList<Move> possibleMoves=new ArrayList<Move>();
			int to =0;
			
			if(team == O){
				for(int j = 0; j<25; j++){
					try {
						to = j+die;
						if(to > 25) to =25;
						if(validationCheck(j,die,team,to,false,triangles,Dice)){ 
								
							if(j == 0 ) {
								Move move=new Move(j,die);
								possibleMoves.add(move);
							}
							else {
								Move move=new Move(j,die);
								possibleMoves.add(move);
							}
			
						}
					} 
					catch (ValueInvalidException | InvalidmoveException | InvalidSpacesException e) {
						//stops the catch occurring in the HumanPlayerClass
								
					}
				}
				
			}
			else{
				int fromHere;
				for(int j = 25; j>0; j--){
					try {
						to=j-die;
						if(to <0) to =0;
						if(validationCheck(j,die,team,to,false,triangles,Dice)){
							fromHere= 25-j;
							if(j == 0 ) {
								Move move=new Move(25-j,die);
								possibleMoves.add(move);
							}
							else {
								Move move=new Move(25-j,die);
								possibleMoves.add(move);
							}
						}
					} 
					catch (ValueInvalidException | InvalidmoveException | InvalidSpacesException e) {
						//stops the catch occurring in the HumanPlayerClass
						
					}
				}
			}
			
			
			return possibleMoves;
		}
		
		/**
		 * getPossible moves calls a recursive function to get all possible permutations of moves which are valid.
		 */
		public ArrayList<ArrayList<Move>> getPossible(int team,ArrayList<Integer> Dice) throws ValueInvalidException, InvalidmoveException, InvalidSpacesException{
			int[] initialBoard= new int[BOARD_SIZE];
			copyBoard(initialBoard,triangles);										//copies board so gameboard is unchanged.
			ArrayList<ArrayList<Move>> results = new ArrayList<ArrayList<Move>>();
			results = recursive(team,Dice,initialBoard);
			if(results.size() == 0) System.out.print("\n empty return");	
			return results;
		}
	
		/**
		 * recursive method which actually gets the moves.
		 * */
		private ArrayList<ArrayList<Move>> recursive(int team, ArrayList<Integer> Dice,int[] possibleBoard) throws ValueInvalidException, InvalidmoveException, InvalidSpacesException{
			ArrayList<ArrayList<Move>> results = new ArrayList<ArrayList<Move>>();
			ArrayList<ArrayList<Move>> nextResult = new ArrayList<ArrayList<Move>>();
			ArrayList<Move> proposedMoves = new ArrayList<Move>();
			int i=0;
			if(Dice.size()>=2){														//to stop duplication with doubles
				if(Dice.get(0)==Dice.get(1)){
					i = Dice.size()-1;
				}			
			}
			while( i < Dice.size()){												//while there die not checked for search for moves
				proposedMoves = possibleMoves(team,possibleBoard,Dice.get(i),Dice);
				 ArrayList<Integer> leftDice =remainingDice(Dice,Dice.get(i));
				 if (leftDice.size()>0 ){											//checks if another recursive call is required
					 if(proposedMoves.size()>0){									//can only append if moves were fund for current die
						 
					 	for(int j =0; j < proposedMoves.size();j++){
							nextResult = recursive(team , leftDice , tempBoard(proposedMoves.get(j),possibleBoard,team,Dice) );
							if(nextResult.size()>0){
								for(int k =0;k<nextResult.size();k++){
									nextResult.get(k).add(0, proposedMoves.get(j));	//places the proposed move at the front of the list of move generate based on it
								}
								results = appendMoveLists(results,nextResult);		//appends latest results to earlier results
							}	
							else{
								 if(proposedMoves.size()>0){
								results = appendMoveLists(results,startList(proposedMoves));//if no more moves were generated recursively stores the ones from here 
								 }
							 }
						}
					 }	
				 }
				 else{																//base case no more dice so the intial list is created
					 if(proposedMoves.size()>0){
					results = startList(proposedMoves);
					 }
				 }
				 nextResult.clear();												//clears lists for reuse
				 proposedMoves.clear();	
				 i++;
			}
			return results;
			
		}
		
		/**
		 * Creates the initial arraylist of arraylists to store the permutations of moves
		 * */
		@SuppressWarnings("unused")
		private ArrayList<ArrayList<Move>> startList(ArrayList<Move> singleMoves){
			ArrayList<ArrayList<Move>> results = new ArrayList<ArrayList<Move>>();
			
			for(int i = 0;i<singleMoves.size();i++){							//each Move in the list is turned into the start of a new list
				ArrayList<Move> temp = new ArrayList<Move>();
				temp.add(singleMoves.get(i));
				results.add(temp);
			}
			if(results == null) System.out.print("ERROR");
			return results;
		}
		
		/**
		 * Appends ArrayList<ArrayList<Move>> to ArrayList<ArrayList<Move>> to create larger one
		 * */
		private ArrayList<ArrayList<Move>> appendMoveLists(ArrayList<ArrayList<Move>> results, ArrayList<ArrayList<Move>> moreResults){
			if(moreResults!=null){
					for(int i =0;i<moreResults.size();i++){
					
					results.add(moreResults.get(i));
					}
			}
			else{
				System.out.print("\n ERROR empty list\n");
			}
			return results;
		}
		
		
		/**
		 * Creates a temporary/copy Board so original can remain unchanged it also applies the moves the copy was needed for
		 * */
		private int[] tempBoard(Move moveMade,int[] currentBoard,int team,ArrayList<Integer> Dice) throws ValueInvalidException, InvalidmoveException, InvalidSpacesException{
			int [] nextBoard = new int [BOARD_SIZE];
			copyBoard(nextBoard,currentBoard);
			move(moveMade.fromPosition, moveMade.dice, team, nextBoard, true,Dice);
			return nextBoard;
		}
		
		
		/**
		 * Creates a new set of dice based on original minus the one to be removed and thus leaves original unchanged when generatin moves
		 * */
		private ArrayList<Integer> remainingDice(ArrayList<Integer> originalDice,int dieUsed){
			ArrayList<Integer> nextSetDice = new ArrayList<Integer>();
			for(int i =0;i <originalDice.size();i++){
				nextSetDice.add(originalDice.get(i));
			}
			consumeDie(dieUsed,nextSetDice);
			return nextSetDice;	
		}
		
		/**
		 * Sorts list of possible moves to leave only ones using max number of dice or highest die
		 * */
		public  ArrayList<ArrayList<Move>> sortingMoves(ArrayList<ArrayList<Move>> tempvalidMoves,ArrayList<Integer> diceroll){
			int max = 0; 
			for(int i=0; i<tempvalidMoves.size(); i++){		//gets max size
				if(tempvalidMoves.get(i).size()>max){
					max=tempvalidMoves.get(i).size();	
				}
			}
			
			for(int i=0; i<tempvalidMoves.size(); i++){	//removes all smaller than max size
				if(tempvalidMoves.get(i).size()<max){
					tempvalidMoves.remove(i);
				}
			}
			if(max==1&&diceroll.size() == 2){//finds smaller die and removes from diceArraylist
				int die0=0;
				int die1=0;
				for(int i=0 ; i < tempvalidMoves.size(); i++){
					if(tempvalidMoves.get(i).get(0).dice == diceroll.get(0)&&tempvalidMoves.get(i).get(0).fromPosition!=0){
						die0++;
					}
					else if(tempvalidMoves.get(i).get(0).dice == diceroll.get(1)&&tempvalidMoves.get(i).get(0).fromPosition!=0){
						die1++;
					}
				}
				if(die0!=0&&die1!=0){
					for(int j=0 ;j<tempvalidMoves.size(); j++){
						if(tempvalidMoves.get(j).get(0).dice == Math.min(diceroll.get(0),diceroll.get(1))){
							tempvalidMoves.remove(j);
						}
					}
				}
			}
			return tempvalidMoves;
		}
		
		
		
		/**
		 * Method to remove duplicate moves, input order may also vary and will be removed
		 * Unused in this Assignment it is for next assignment.
		 * */
		public ArrayList<ArrayList<Move>> superfluous(ArrayList<ArrayList<Move>> original,int[] curentBoard,int team){
			ArrayList<ArrayList<Move>> result = original;
			for(int i = 0; i<result.size();i++){
				for(int j = 0; j<result.size();j++){
					if(j != i){
						if(sameEndBoard(result.get(i),result.get(j),curentBoard,team)){
							result.remove(j);
							j--;
						}
					}
				}
			}
				
			
			
			return result;
		}
		/**
		 * Checks if two different series of moves result in the same board after the moves
		 * */
		private boolean sameEndBoard(ArrayList<Move> moveA,ArrayList<Move> moveB,int[] curentBoard, int team){
			boolean result;
			int[] BoardA =quickMove(moveA,curentBoard,team);
			int[] BoardB =quickMove(moveB,curentBoard,team);
			int check=0;
			for(int i =0; i<BOARD_SIZE;i++){
				if(BoardA[i]==BoardB[i])check++;
			}
			if(check == 28) result = true;
			else result = false;
		return result;
		}
		
		/**
		 * A move method with out validation checking for the sameEndBoard method 
		 * As it will only recive valid moves.
		 * */
		private int[] quickMove(ArrayList<Move> seriesMoves,int[] curB,int team){
			int [] nextB = new int [BOARD_SIZE];
			copyBoard(nextB,curB);
			int from;
			int to;
			int die;
			for(int i = 0; i< seriesMoves.size();i++){
				if(team == O){
					from =seriesMoves.get(i).fromPosition;
					die = seriesMoves.get(i).dice;
					to = from + die;
					if(from == 0 || from == 25) from = barO;
					if(to >25) to = 25;
					nextB[from]-=O;
					nextB[to]+=O;
				}
				else{
					from =24 - seriesMoves.get(i).fromPosition;
					die = seriesMoves.get(i).dice;
					to = from - die;
					if(from == 0 || from == 25) from = barX;
					if(to < 0) to = 0;
					nextB[from]-=X;
					nextB[to]+=X;
				}
			}
			
			return nextB;
		}
		
		
		/**
		 * Method to display the possible moves
		 * */
		public void printMoves(ArrayList<ArrayList<Move>> results){
			int count = 0;
			int divider;					//divider and count to split them up better on screen for readability
		if(results.size()>0){
			if(results.get(0).size()>2){
				divider = 2;
			}
			else{
				divider = 4;
			}
			for(int i=0;i<results.size();i++){
				if(results.get(i) != null){
					if(count%divider == 0){
						System.out.print("\n");
					}
					ArrayList<Move> test21=results.get(i);
					System.out.print("(");;
					for(int j=0;j<test21.size();j++){
						if(test21!=null)System.out.print(" "+test21.get(j).fromPosition+"-"+test21.get(j).dice+" ");
					}
					System.out.print(")    \t");
				}	
				count++;	
			}
			System.out.print("\n");
		}
		else{
			System.out.println("\nNo valid Move");
		}
		}

}	
