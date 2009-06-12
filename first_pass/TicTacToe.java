
/*
 * Created on May 12, 2004
 * by Jake Scruggs
 * 
 * TicTacToe lets 2 people play Tic Tac Toe or 1 person play the computer
 * 
 */
import java.io.*;

public class TicTacToe {

	public static void main(String[] args) 
	{
		new InputStreamReader(System.in);
		BufferedReader theKeyboard = new BufferedReader(
				new InputStreamReader(System.in));

		TheBoard aBoard = new TheBoard();
		
	
		System.out.println("This is a Tic Tac Toe game.\n" +
				"Each player takes turns typing in a number, 1-9, where they would " +
				"like to make a mark." );
		
		System.out.println(aBoard); // print the board
		
		System.out.print("How many players? \n" +
				" (type 1 to play the computer or 2 to play a friend) " );

		int players =1;
		String input = "";
		boolean badInput = false;
		
		do // get the number of players -- only accept 1 or 2
		{
			try
			{
				input = theKeyboard.readLine();
			}
			catch(IOException e)
			{
				System.out.println("input error:" + e);
				System.exit(1);
			}
			if(input.equals("1")) 
			{
				badInput = false;
				players = 1; 
			}
			else if(input.equals("2"))
			{
				badInput = false;
				players = 2;
			}
			else badInput = true;

			if(badInput) System.out.print("Enter a number, 1 or 2: ");
		}
		while(badInput);

		double coinToss = Math.random(); // Coin toss to see who goes first
		if(coinToss < .5) System.out.println("X wins the coin toss and will go first.");
		else System.out.println("O wins the coin toss and will go first.");
		
		
		
		int [] choice = new int [2];
		char winner;
		int turn = 1;
		
		
		
		while(true) // loop only breaks when X or O wins, or a cat's game
		{
			if( (turn == 1 && coinToss < .5) || turn > 1) // skips X's turn if X losses coin toss
			{
				if(players == 2) // Human player
				{
					System.out.print("Player X, where will you make your mark?");
					while(true)
					{
						choice = choice();
						if(!aBoard.isOccupied(choice[0], choice[1])) break; // can't take occupied space
						System.out.println("That space is occupied.");
					}
				}
				else // Or computer player
				{
					choice = ComputerPlayer.makeMove(aBoard.copyBoard(), turn);
				}
				
				aBoard.markX(choice[0], choice[1]); // mark an 'X' (20) on the board
				
				winner = aBoard.didSomeoneWin(); // Check for a win
				if( winner != 'N') break;
				
				System.out.println(aBoard);
				
				turn++;
			}

			// Player O's turn
			System.out.print("Player O, where will you make your mark? ");
			
			while(true)
			{
				choice = choice();
				if(!aBoard.isOccupied(choice[0], choice[1])) break;
				System.out.println("That space is occupied.");
			}
			
			aBoard.markO(choice[0], choice[1]);
			
			winner = aBoard.didSomeoneWin();
			if( winner != 'N') break;
			
			System.out.println(aBoard);
			turn++;
		}
		
		System.out.println(aBoard);
		if(winner=='C') System.out.println("Cat's game.");
		
		if(winner != 'C') System.out.println("The winner is: " + winner);

	}
	
	/*
	 * choice reads the users choice and translates it to rows and columns
	 * Returns: choice [], a 1D int array with the [0]row and [1]column of the choice
	 * Won't accept a choice other than 1-9
	 * 
	 */
	public static int[] choice()
	{
		new InputStreamReader(System.in);
		BufferedReader theKeyboard = new BufferedReader(
				new InputStreamReader(System.in));
		
		String input = "";
		int [] choice = new int[2];
		boolean badInput = false;
		do
		{
		
			try
			{
				input = theKeyboard.readLine();
			}
			catch(IOException e)
			{
				System.out.println("input error:" + e);
				System.exit(1);
			}
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
}
