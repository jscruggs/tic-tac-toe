package TTT;

public class TTTBoard
{
	private char myBoard [][] = new char [3][3];

	public String toString()
	{
		String tttBoardAsString = "\n";
		int tttBoardPosition = 1;
		
		for(int row = 0; row < 3; row++)
		{
			for(int column = 0; column < 3; column++)
			{
				if(myBoard[row][column] == 0)
				{
					tttBoardAsString += tttBoardPosition;
				}
				else
				{
					tttBoardAsString += myBoard[row][column];
				}
				if(column < 2)
				{
					tttBoardAsString += "|";
				}
				tttBoardPosition++;
			}
			tttBoardAsString += "\n";
			if(row < 2)
			{
				tttBoardAsString += "-+-+-\n";
			}
		}
		return tttBoardAsString + "\n";
	}
	
	public void markOnTheBoard(char XOrO, int row, int column) 
	{
		myBoard [row][column] = XOrO;
	}


	public char [][] returnBoardAsCharArray() 
	{
		char [][] copyOfBoard = new char [3][3];
		
		for(int row = 0; row < 3; row++)
		{
			for(int column = 0; column < 3; column++)
			{
				copyOfBoard [row][column] = myBoard [row][column];
			}
		}
		return copyOfBoard;
	}


	public char didSomeoneWin() 
	{
		char winner = 'N', XOrO = 'X';
		char [][] tttBoard = myBoard;
		
		for(int i = 0; i < 2; i++)
		{
			if(i == 1) XOrO = 'O';
			
			if( winForXOrO(XOrO, tttBoard) )
			{
				winner = XOrO;
			}
		}
		
		if(winner == 'N')
		{
			winner = isItACatsGame(winner, tttBoard);
		}

		return winner;
	}

	private char isItACatsGame(char winner, char[][] tttBoard) {
		int catsGameCheck = 0;
		for(int row = 0; row < 3; row++)
		{
			for(int column = 0; column < 3; column++)
			{
				if(tttBoard[row][column] == 0) catsGameCheck++;
			}
		}
		
		if(catsGameCheck == 0) winner = 'C';
		return winner;
	}

	private boolean winForXOrO(char XOrO, char[][] tttBoard) 
	{
		return 	checkRowsForWin(XOrO, tttBoard) || 
				checkColumnsForWin(XOrO, tttBoard)||
				checkDiagonalsForWin(XOrO, tttBoard);
	}

	private boolean checkDiagonalsForWin(char XOrO, char[][] tttBoard) {
		return ((tttBoard[0][0] == XOrO) && (tttBoard[1][1] == XOrO) && (tttBoard[2][2] == XOrO) ||
		        (tttBoard[0][2] == XOrO) && (tttBoard[1][1] == XOrO) && (tttBoard[2][0] == XOrO) );
	}

	private boolean checkColumnsForWin(char XOrO, char[][] tttBoard) {
		return (
				(tttBoard[0][0] == XOrO) && (tttBoard[1][0] == XOrO) && (tttBoard[2][0] == XOrO) || 
				(tttBoard[0][1] == XOrO) && (tttBoard[1][1] == XOrO) && (tttBoard[2][1] == XOrO) ||
				(tttBoard[0][2] == XOrO) && (tttBoard[1][2] == XOrO) && (tttBoard[2][2] == XOrO) );
	}

	private boolean checkRowsForWin(char XOrO, char[][] tttBoard) {
		return  (tttBoard[0][0] == XOrO) && (tttBoard[0][1] == XOrO) && (tttBoard[0][2] == XOrO) ||
			    (tttBoard[1][0] == XOrO) && (tttBoard[1][1] == XOrO) && (tttBoard[1][2] == XOrO) ||
				(tttBoard[2][0] == XOrO) && (tttBoard[2][1] == XOrO) && (tttBoard[2][2] == XOrO);
	}

	public TTTBoard CreateClonedBoard()
	{
		TTTBoard clonedBoard = new TTTBoard();

		for(int row = 0; row < 3; row++)
		{
			for(int column = 0; column < 3; column++)
			{
				clonedBoard.markOnTheBoard(myBoard [row][column], row, column);
			}
		}
		return clonedBoard;
	}
	
	public boolean isSpaceAvalible(int row, int column)
	{
		boolean isSpaceAvalible = false;
		
		if(myBoard [row][column] == 0) isSpaceAvalible = true; 
		
		return isSpaceAvalible;
	}
}
