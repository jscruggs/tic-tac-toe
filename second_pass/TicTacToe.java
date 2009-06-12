
/*
 * Created on May 12, 2004
 * by Jake Scruggs
 * 
 * TicTacToe lets 2 people play Tic Tac Toe, 1 person play the computer,
 * or the computer play itself
 * 
 */
import java.io.*;

public class TicTacToe {

	public static void main(String[] args) 
	{
		boolean isHumanX, isHumanO, doesXgoFirst, isComputerX, isItTheHumansMove;
		TheBoard aBoard = new TheBoard();
		int turn, numberOfPlayers, searchDepthLimit = 0;
		int [] choice = new int [2];
		char winner, XorOsTurn;
		
		numberOfPlayers = introductionAndNumberOfPlayers(aBoard); // print Intro. and get number of players
		
		if(numberOfPlayers == 0) {isHumanX = false; isHumanO = false;}
		else if(numberOfPlayers == 1) {isHumanX = isHumanX(); isHumanO = !isHumanX;}
		else {isHumanX = true; isHumanO = true;}
		
		doesXgoFirst = findOutIfXgoesFirst(); // ask the user who should go first
		if(doesXgoFirst) turn = 1;
			else turn = 2;
		
		if( !(isHumanX && isHumanO) )searchDepthLimit = getSearchDepthLimit(); // ask the user how hard to play, if the computer is at least one player
		
		while(true) // loop only breaks when X or O wins, or a cat's game
		{
			if(turn%2 == 0) {XorOsTurn = 'O'; isComputerX = false; isItTheHumansMove = isHumanO;} //figure out who's move it is
				else {XorOsTurn = 'X'; isComputerX = true; isItTheHumansMove = isHumanX;}
			int searchDepthLimitCopy = searchDepthLimit;
			choice = humanOrComputerMove(isItTheHumansMove, XorOsTurn, aBoard, isComputerX, searchDepthLimitCopy);
				
			if(isComputerX) aBoard.markX(choice[0], choice[1]); // mark the appropriate square with the right number
				else aBoard.markO(choice[0], choice[1]);
			System.out.println(aBoard); // print the board
			
			winner = TheBoard.didSomeoneWin(aBoard.copyBoard()); // Check for a win or a cat's game
			if( winner != 'N') break; 

			turn++;
		}
		
		if(winner=='C') System.out.println("Cat's game.");
		if(winner != 'C') System.out.println("The winner is: " + winner);
	}
	
	/*
	 * humanOrComputerMove either directs the computer to move or lets the Human move.
	 * 
	 * Receives:
	 * IsItTheHumansMove, a boolean
	 * XorOsTurn, a char indicating the turn
	 * aBoard, a TheBoard object
	 * isComputerX, a boolean indicating what side the computer is playing
	 * 
	 * Returns:
	 * choice an int[] with the row and column of the move made 
	 */
	public static int [] humanOrComputerMove(boolean IsItTheHumansMove, char XorOsTurn, TheBoard aBoard, 
											 boolean isComputerX, int searchDepthLimit)
	{
		int [] choice = new int [2];
		
		if(IsItTheHumansMove) // Human player
		{
			System.out.print("Player " + XorOsTurn + ", where will you make your mark?");
			while(true)
			{
				choice = getHumanPlayersChoice();
				if(!aBoard.isOccupied(choice[0], choice[1])) break; // can't take occupied space
				System.out.println("That space is occupied.");
			}
		}
		else  // Or computer player
		{
			int [][] theCurrentBoard = aBoard.copyBoard();
			choice = ComputerPlayer.makeTheComputerMove(theCurrentBoard, isComputerX, searchDepthLimit);
		}
		
		return choice;
	}
	
	/*
	 * isHumanX asks the user if (s)he would like to be X or O
	 * Return:
	 * isHumanX a Boolean
	 */
	public static boolean isHumanX()
	{
		boolean isHumanX = false;
		boolean badInput;
		String input;
		
		do
		{
			System.out.println("Would you like to be X or O? ");
			input = getInput();
			
			if(input.equalsIgnoreCase("X")){isHumanX = true; badInput = false; }
			else if(input.equalsIgnoreCase("O")){isHumanX = false; badInput = false; }
			else badInput = true;
			if(badInput) System.out.println("Enter an X or an O: ");
		}
		while(badInput);
		return isHumanX;
	}
	
	/*
	 * introductionAndNumberOfPlayers introduces the game and gets the number of players
	 * recieves:
	 * aBoard, a TheBoard object
	 * returns:
	 * numberOfPlayers, an int with the number of players
	 */
	public static int introductionAndNumberOfPlayers(TheBoard aBoard)
	{
		int numberOfPlayers=0;
		
		System.out.println("This is a Tic Tac Toe game.\n" +
				"Each player takes turns typing in a number, 1-9, where they would " +
		"like to make a mark." );
		
		System.out.println(aBoard); // print the board

		System.out.print("How many players? \n" +
				"0 - The Computer plays itself.\n" +
				"1 - You play the Computer.\n" +
				"2 - You play a friend. \n" );

		String input;
		boolean badInput;
		
		do // get the number of players -- only accept 1, 2, or 3
		{
			input = getInput();
			
			if(input.equals("0")) {badInput = false; numberOfPlayers = 0;}
			else if(input.equals("1")) {badInput = false; numberOfPlayers = 1;}
			else if(input.equals("2")){badInput = false; numberOfPlayers = 2;}
			else badInput = true;

			if(badInput) System.out.print("Enter a number, 0-2: ");
		}
		while(badInput);
		
		return numberOfPlayers;
	}
	
	
	/*
	 * getInput gets a string input from the user
	 * Return:
	 * input a string
	 */
	public static String getInput()
	{
		new InputStreamReader(System.in);
		BufferedReader theKeyboard = new BufferedReader(
				new InputStreamReader(System.in));
		String input ="";
		
		try
		{
			input = theKeyboard.readLine();
		}
		catch(IOException e)
		{
			System.out.println("input error:" + e);
			System.exit(1);
		}
		
		return input;
		
	}
	
	/*
	 * getHumanPlayersChoice reads the users choice and translates it to rows and columns
	 * Returns: choice [], a 1D int array with the [0]row and [1]column of the choice
	 * Won't accept a choice other than 1-9
	 * 
	 */
	public static int[] getHumanPlayersChoice()
	{
		new InputStreamReader(System.in);
		BufferedReader theKeyboard = new BufferedReader(
				new InputStreamReader(System.in));
		
		String input = "";
		int [] choice = new int[2];
		boolean badInput = false;

		do
		{
			input = getInput();

			if(input.equals("1")) {choice [0] = 0; choice[1] = 0; badInput = false;} 
			else if(input.equals("2")) {choice [0] = 0; choice[1] = 1; badInput = false;}
			else if(input.equals("3")) {choice [0] = 0; choice[1] = 2; badInput = false;}
			else if(input.equals("4")) {choice [0] = 1; choice[1] = 0; badInput = false;}
			else if(input.equals("5")) {choice [0] = 1; choice[1] = 1; badInput = false;}
			else if(input.equals("6")) {choice [0] = 1; choice[1] = 2; badInput = false;}
			else if(input.equals("7")) {choice [0] = 2; choice[1] = 0; badInput = false;}
			else if(input.equals("8")) {choice [0] = 2; choice[1] = 1; badInput = false;}
			else if(input.equals("9")) {choice [0] = 2; choice[1] = 2; badInput = false;}
			else badInput = true;

			if(badInput) System.out.print("Enter a number, 1-9: ");
		}
		while(badInput);
		
		return choice ;
	}
	
	/*
	 * findOutIfXgoesFirst asks the user who should go first
	 * returns:
	 * doesXgoFirst, a boolean that indicates who goes first
	 */
	public static boolean findOutIfXgoesFirst()
	{
		boolean doesXgoFirst = true;
		String input ="";
		boolean badInput;
		
		System.out.println("Who will go first(X or O)? ");
		do
		{
			input = getInput();
			
			if(input.equalsIgnoreCase("O")) {badInput = false; doesXgoFirst = false;}
			else if(input.equalsIgnoreCase("X")) {badInput = false; doesXgoFirst = true;}
			else badInput = true;

			if(badInput) System.out.print("Enter either X or O: ");
		}
		while(badInput);
		
		return doesXgoFirst;
	}
	
	public static int getSearchDepthLimit()
	{
		int searchDepthLimit = 0;
		
		String input ="";
		boolean badInput;
		
		System.out.println("How hard should the computer play on a scale of 0-8?\n" +
							"With 8 being the hardest and 0 the weakest computer player: ");
		do
		{
			input = getInput();
			
			if(input.equals("0")) {searchDepthLimit = 0; badInput = false;} 
			else if(input.equals("1")) {searchDepthLimit = 1; badInput = false;} 
			else if(input.equals("2")) {searchDepthLimit = 2; badInput = false;} 
			else if(input.equals("3")) {searchDepthLimit = 3; badInput = false;} 
			else if(input.equals("4")) {searchDepthLimit = 4; badInput = false;} 
			else if(input.equals("5")) {searchDepthLimit = 5; badInput = false;} 
			else if(input.equals("6")) {searchDepthLimit = 6; badInput = false;} 
			else if(input.equals("7")) {searchDepthLimit = 7; badInput = false;} 
			else if(input.equals("8")) {searchDepthLimit = 8; badInput = false;} 
			else badInput = true;

			if(badInput) System.out.print("Enter a number 0-8: ");
		}
		while(badInput);
		
		return searchDepthLimit;
	}
}
