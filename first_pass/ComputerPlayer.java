/*
 * Created on May 14, 2004
 *
 *ComputerPlayer.java 
 *Looks at the Tic Tac Toe board and the number of turns elapsed and decideds what move to make
 *
 */

/**
 * @author Jake
 *
 */
public class ComputerPlayer {
// computer is always X
	
	
	/*
	 * makeMove 
	 * Receives: theBoard [][], a matrix representing a tic tac toe board and turn, an int 
	 * which is the number of turns that have elapsed.
	 * 
	 */
	public static int [] makeMove(int theBoard [] [], int turn)
	{
		int [] analysisAndMove = new int [3]; 
		int square = 5; 
		int choice [] = new int [2];
		boolean moveMade = false;
		
	
		if(turn==1) // grab the center if X has the first move
			{
			square = 5;
			choice = squareConvert(square);
			return choice;
			}
		
		if(turn ==2)
		{
			if(theBoard [1] [1] == 0) // take the center if O doesn't take it first
			{
				square = 5;
				choice = squareConvert(square);
				return choice;
			}
			else  // or take square 1
			{
				square = 1;
				choice = squareConvert(square);
				return choice;
			}
		}
		
		// start of custom blocks
		if(turn == 3)
		{
			if( (theBoard [0] [1] + theBoard [1] [0] + theBoard [1] [2] + theBoard [2] [1]) == 1  )
			{
				square = 1;
				choice = squareConvert(square);
				return choice;
			}
		}
		
		if(turn == 4 && defense(theBoard) [0] == 0)
		{
			if( (theBoard [0] [1] + theBoard [1] [0] + theBoard [1] [2] + theBoard [2] [1]) == 0 &&
				theBoard [1] [1] != 20)
			{
				square = 7;
				choice = squareConvert(square);
				return choice;
			}
			
			if(theBoard [1] [1] == 20)
			{
				if(theBoard[0] [2] == 1 && theBoard [2] [0] == 1)
				{
					square = 4;
					choice = squareConvert(square);
					return choice;
				}
				if(theBoard[0] [0] == 1 && theBoard [2] [2] == 1)
				{
					square = 4;
					choice = squareConvert(square);
					return choice;
				}
			}
			
			if(theBoard [0] [1] == 1)
			{
				square = 1;
				choice = squareConvert(square);
				return choice;
			}
			else if(theBoard [1] [2] == 1)
			{
				square = 3;
				choice = squareConvert(square);
				return choice;
			}
			else if(theBoard [2] [1] == 1)
			{
				square = 9;
				choice = squareConvert(square);
				return choice;
			}
			else 
			{
				square = 7;
				choice = squareConvert(square);
				return choice;
			}
		}
		// End of custom blocks
		
		analysisAndMove = offense(theBoard); // check for a win 
		if(analysisAndMove [0] != 0)
		{
			choice [0] = analysisAndMove [1];
			choice [1] = analysisAndMove [2];
			return choice;
		}
		
		analysisAndMove = defense(theBoard); // defend against a loss
		if(analysisAndMove [0] != 0)
		{
			choice [0] = analysisAndMove [1];
			choice [1] = analysisAndMove [2];
			return choice;
		}
		
		choice = randomMove(theBoard); // if no custom move, offense, or defense, the make a random move.
		return choice;
	}
	
	/*
	 * deffense looks for two O's in a row, column, or diagonal and blocks the win if it needs to
	 * 
	 * recieves: theBoard [] [], an int array representing the tic tac toe board
	 * Returns: analysisAndMove [], a 1D array with the [1]row and [2]column of the choice
	 * If there is no defense needed analysisAndMove [0] = 0 
	 */
	
	public static int [] defense(int theBoard [] [] )
	{
		
		int analysisAndMove [] = new int [3]; // analysisAndMove [0] = 0 if no defense needed
		analysisAndMove [0] = 0;
		int sum = 0;
		
		// Check the columns
		for(int column = 0; column < 3; column++)
		{
			sum = theBoard [0] [column] + theBoard [1] [column] + theBoard [2] [column];
			if(sum == 2)
			{
				for(int row=0; row<3; row++)
				{
					// Block a winning move
					if(theBoard[row] [column] == 0)
					{
						analysisAndMove [0]=1;
						analysisAndMove [1]=row;
						analysisAndMove [2]=column;
					}
				}	
			}
			if(analysisAndMove[0] == 1) return analysisAndMove;
		}
		
		// Check the rows
		for(int row = 0; row < 3; row++)
		{
			sum = theBoard [row] [0] + theBoard [row] [1] + theBoard [row] [2];
			if(sum == 2)
			{
				for(int column=0; column<3; column++)
				{
					// Block a winning move
					if(theBoard[row] [column] == 0)
					{
						analysisAndMove [0]=1;
						analysisAndMove [1]=row;
						analysisAndMove [2]=column;
					}
				}	
			}
			if(analysisAndMove[0] == 1) return analysisAndMove;
		}
		
		// Check diagonal
		sum = theBoard [0] [0] + theBoard [1] [1] + theBoard [2] [2];
		if(sum == 2)
		{
			for(int i=0; i<3; i++)
			{
				if(theBoard[i] [i] == 0)
				{
					analysisAndMove [0]=1;
					analysisAndMove [1]=i;
					analysisAndMove [2]=i;
					return analysisAndMove;
				}
				
			}	
		}
		
		// Check other diagonal
		sum = theBoard [0] [2] + theBoard [1] [1] + theBoard [2] [0];
		if(sum == 2)
		{
			int column = 2;
			for(int row=0; row<3; row++)
			{
				if(theBoard[row] [column] == 0)
				{
					analysisAndMove [0]=1;
					analysisAndMove [1]=row;
					analysisAndMove [2]=column;
					return analysisAndMove;
				}
				column--;
			}	
		}
		return analysisAndMove;
	}
	
	/*
	 * offense looks for two X's in a row, column, or diagonal and makes it three X's in
	 * a line if it finds the possibility
	 * 
	 * recieves: theBoard [] [], an int array representing the tic tac toe board
	 * Returns: analysisAndMove [], a 1D array with the [1]row and [2]column of the choice
	 * If there is no offense possible analysisAndMove [0] = 0 
	 */
	public static int [] offense(int theBoard [] [] )
	{
		
		int analysisAndMove [] = new int [3]; // analysisAndMove [0] = 0 if no defense needed
		analysisAndMove [0] = 0;
		
		int sum = 0;
		
		// Check the columns
		for(int column = 0; column < 3; column++)
		{
			sum = theBoard [0] [column] + theBoard [1] [column] + theBoard [2] [column];
			if(sum == 40)
			{
				for(int row=0; row<3; row++)
				{
					// Make a winning move
					if(theBoard[row] [column] == 0)
					{
						analysisAndMove [0]=1;
						analysisAndMove [1]=row;
						analysisAndMove [2]=column;
					}
				}	
			}
			if(analysisAndMove[0] == 1) return analysisAndMove;
		}
		
		// Check the rows
		for(int row = 0; row < 3; row++)
		{
			sum = theBoard [row] [0] + theBoard [row] [1] + theBoard [row] [2];
			if(sum == 40)
			{
				for(int column=0; column<3; column++)
				{
					// Make a winning move
					if(theBoard[row] [column] == 0)
					{
						analysisAndMove [0]=1;
						analysisAndMove [1]=row;
						analysisAndMove [2]=column;
					}
				}	
			}
			if(analysisAndMove[0] == 1) return analysisAndMove;
		}
		
		// Check diagonal
		sum = theBoard [0] [0] + theBoard [1] [1] + theBoard [2] [2];
		if(sum == 40)
		{
			for(int i=0; i<3; i++)
			{
				if(theBoard[i] [i] == 0)
				{
					analysisAndMove [0]=1;
					analysisAndMove [1]=i;
					analysisAndMove [2]=i;
					return analysisAndMove;
				}
				
			}	
		}
		
		// Check other diagonal
		sum = theBoard [0] [2] + theBoard [1] [1] + theBoard [2] [0];
		if(sum == 40)
		{
			int column = 2;
			for(int row=0; row<3; row++)
			{
				if(theBoard[row] [column] == 0)
				{
					analysisAndMove [0]=1;
					analysisAndMove [1]=row;
					analysisAndMove [2]=column;
					return analysisAndMove;
				}
				column--;
			}	
		}
		return analysisAndMove;
	}
	
	/*
	 * randomMove makes a random, legal move
	 * recieves: theBoard [] [], an int array representing the tic tac toe board
	 * Returns: choice [], a 1D array with the row and column of the choice
	 */
	
	public static int [] randomMove(int theBoard [] [] )
	{
		int choice [] = new int [2]; 
		int possibleRow; 
		int possibleColumn; 
		
		while(true)
		{
			possibleRow = (int) (Math.random()*3 );
			possibleColumn = (int) (Math.random()*3 );
			if(theBoard [possibleRow] [possibleColumn] == 0) break;
		}
		choice [0] = possibleRow;
		choice [1] = possibleColumn;
		
		return choice;
	}	
	
	/*
	 * squareConvert takes in a square (1-9) and converts it to a row and column
	 * Recieve: square, and int (1-9)
	 * Returns: choice [], a 2D array with the row and column of the choice
	 * 
	 */
	public static int [] squareConvert(int square)
	{
		int choice [] = new int [2];
		
		if(square == 1) {choice [0] = 0; choice[1] = 0;} 
		else if(square == 2) {choice [0] = 0; choice[1] = 1;}
		else if(square == 3) {choice [0] = 0; choice[1] = 2;}
		else if(square == 4) {choice [0] = 1; choice[1] = 0;}
		else if(square == 5) {choice [0] = 1; choice[1] = 1;}
		else if(square == 6) {choice [0] = 1; choice[1] = 2;}
		else if(square == 7) {choice [0] = 2; choice[1] = 0;}
		else if(square == 8) {choice [0] = 2; choice[1] = 1;}
		else if(square == 9) {choice [0] = 2; choice[1] = 2;}
		
		return choice;
	}

	
}
