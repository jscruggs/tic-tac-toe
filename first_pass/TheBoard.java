/*
 * Created on May 12, 2004
 * by Jake Scruggs
 *
 * TheBoard is a tic tac toe board which represents it in a 2D array
 * It can make a copy of itself. 
 * Check for a win or a Cat's game.
 * Check if a space is occupied.
 * Mark an X or an O (X is a 20 in the array and O is a 1.  An empty space is a 0.)
 * And create a text version of itself.
 * 
 */



public class TheBoard {

	private int [] [] myBoard = new int [3] [3];
	
	/*
	 * TheBoard constructor.
	 * Builds a 3 by 3 array and fills it with zeros -- and empty 
	 * tic tac toe board.
	 */
	public TheBoard()
	{
		for(int row = 0; row < 3; row++)
		{
			for(int column = 0; column < 3; column++)
			{
				myBoard [row] [column] = 0;
			}
		}
	}
	
	/*
	 * copyBoard
	 * creates a copy of the curent board.
	 */
	public int [] [] copyBoard()
	{
		return myBoard;
	}
	
	/*
	 * didSomeoneWin checks for a winner or a cat's game
	 * Returns: winner, a char
	 * 'N' is no winner
	 * 'X' is X won
	 * 'O' is O won
	 * 'C' is a cat's game
	 */
	public char didSomeoneWin()
	{
		
		char winner = 'N';
		int catCheck = 1;
		
		// Check the columns
		for(int column = 0; column < 3; column++)
		{
			int product = myBoard [0] [column] * myBoard [1] [column] * myBoard [2] [column];
			if(product == 8000) // 20*20*20 = 8000, a win for X
			{
				winner = 'X';
				break;
			}
			if(product == 1) // 1*1*1 = 1, a win for O
			{
				winner = 'O';
				break;
			}
		}
			
		if(winner != 'N') return winner;
		
		// Check the rows
		for(int row = 0; row < 3; row++)
		{
			int product = myBoard [row] [0] * myBoard [row] [1] * myBoard [row] [2];
			if(product == 8000)
			{
				winner = 'X';
				break;
			}
			if(product == 1)
			{
				winner = 'O';
				break;
			}
		}
			
		if(winner != 'N') return winner;
		
		// Check one diagonal
		int product = myBoard [0] [0] * myBoard [1] [1] * myBoard [2] [2];
		if(product == 1) winner = 'O';
		if(product == 8000) winner = 'X';
		
		// Check the other diagonal
		product = myBoard [0] [2] * myBoard [1] [1] * myBoard [2] [0];
		if(product == 1) winner = 'O';
		if(product == 8000) winner = 'X';
		
		// If nobody's won, Check for a cat's game
		if(winner == 'N')
		{
			for(int row = 0; row < 3; row++)
			{
				for(int column = 0; column < 3; column++)
				{
					catCheck *= myBoard [row] [column];
				}
			}
			if(catCheck != 0) winner = 'C'; // any empty space is a zero. So product is zero if there is space left.
		}
		
		return winner;	
	}
	
	/*
	 * markX makes places a 20 on the tic tac toe board for X
	 */
	public void markX(int row, int column)
	{
		myBoard [row] [column] = 20;
	}
	
	/*
	 * markO makes places a 1 on the tic tac toe board for X
	 */
	public void markO(int row, int column)
	{
		myBoard [row] [column] = 1;
	}
	
	/*
	 * isOccupied returns a true if the space has been taken
	 */
	public boolean isOccupied(int row, int column)
	{
		if(myBoard [row] [column] == 0) return false;
		else return true;
	}
	
	/*
	 *  toString enables printing out of the tic tac toe board
	 * Returns: printBoard, a string containing the current state of the board
	 */
	public String toString()
	{
		String printBoard = ""; 
		char XorO;
		int position = 49;
		
		for(int row = 0; row < 3; row++)
		{
			
			for(int column = 0; column < 3; column++)
			{
				if(myBoard[row] [column] == 1) XorO = (char) (myBoard [row] [column] + 78); // an O
				else if(myBoard[row] [column] == 20) XorO = (char) (myBoard [row] [column] + 68); // an X
				else XorO = (char) (position);
				position++;
				printBoard = printBoard + XorO + " ";
			}
			printBoard = printBoard + "\n" ; // starts a new line at the end of a row
		}
		return printBoard;
	}
}
