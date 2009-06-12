/*
 * Created on May 12, 2004
 * by Jake Scruggs
 *
 * TheBoard is a tic tac toe board represented in a 2D array
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
	 * copyBoard returns a copy of the Tic Tac Toe Board
	 */
	public int [] [] copyBoard()
	{
		int [][] copyOfBoard = new int [3][3];
		for(int row = 0; row < 3; row++)
		{
			for(int column = 0; column < 3; column++)
			{
				copyOfBoard [row] [column] = myBoard [row] [column];
			}
		}
		return copyOfBoard;
	}
	
	/*
	 * didSomeoneWin checks for a winner or a cat's game
	 * Receives:
	 * currentBoard, a matrix representing the current Tic Tac Toe board
	 * Returns: winner, a char
	 * 'N' is no winner
	 * 'X' is X won
	 * 'O' is O won
	 * 'C' is a cat's game
	 */
	public static char didSomeoneWin(int [] [] currentBoard)
	{
		int winForX = 8000, winForO = 1, checkIfItsAcatsGame = 1, product;
		char winner = 'N';

		for(int column = 0; column < 3; column++) // Check the columns
		{
			product = currentBoard [0] [column] * currentBoard [1] [column] * currentBoard [2] [column];
			if(product == winForX) winner = 'X';
			if(product == winForO) winner = 'O';
		}
		
		for(int row = 0; row < 3; row++) // Check the rows
		{
			product = currentBoard [row] [0] * currentBoard [row] [1] * currentBoard [row] [2];
			if(product == winForX) winner = 'X';
			if(product == winForO) winner = 'O';
		}

		product = currentBoard [0] [0] * currentBoard [1] [1] * currentBoard [2] [2]; // Check one diagonal
		if(product == winForX) winner = 'X';
		if(product == winForO) winner = 'O';
		
		product = currentBoard [0] [2] * currentBoard [1] [1] * currentBoard [2] [0]; // Check the other diagonal
		if(product == winForX) winner = 'X';
		if(product == winForO) winner = 'O';
		
		if(winner == 'N') // If nobody's won, Check for a cat's game
		{
			for(int row = 0; row < 3; row++)
			{
				for(int column = 0; column < 3; column++)
					checkIfItsAcatsGame *=currentBoard [row] [column];
			}
			if(checkIfItsAcatsGame != 0) winner = 'C'; // any empty space is a zero. So product is zero if there is space left.
		}
		
		return winner;	
	}
	
	/*
	 * markX places a 20 on the tic tac toe board for X
	 */
	public void markX(int row, int column)
	{
		myBoard [row] [column] = 20;
	}
	
	/*
	 * markO places a 1 on the tic tac toe board for X
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
				if(myBoard[row] [column] == 1) XorO = 'O'; // an O
				else if(myBoard[row] [column] == 20) XorO = 'X'; // an X
				else XorO = (char) (position);
				position++;
				if(column == 0 || column == 1) printBoard = printBoard + XorO + "|";
				else printBoard = printBoard + XorO;
			}
			printBoard = printBoard + "\n" ; // starts a new line at the end of a row
			if(row == 0 || row == 1) printBoard = printBoard + "-+-+-\n";
		}
		return printBoard;
	}
	
	/*
	 * findAllLegalMoves finds the legal moves available in the current board
	 * recieves:
	 * currentBoard, a matrix representing the current Tic Tac Toe board
	 * returns:
	 * allLegalMoves, an int [][] with the row and column stored in the rows of the array.  
	 * The rows are marked by the numbers of the legal moves.
	 * allLegalMoves [9] [0] contains the number of legal moves.
	 */
	public static int [] [] findAllLegalMoves(int [][] currentBoard)
	{
		int [][] allLegalMoves = new int [10][2];
		int legalMoveNumber = 0;
		
		for(int row = 0; row<3; row++)
		{
			for(int column = 0; column < 3; column++)
			{
				if(currentBoard [row] [column] == 0) 
				{
					allLegalMoves [legalMoveNumber] [0] = row;
					allLegalMoves [legalMoveNumber] [1] = column;
					legalMoveNumber++;
				}
			}
		}
		allLegalMoves [9][0]= (legalMoveNumber - 1);
		
		return allLegalMoves;
	}
}
