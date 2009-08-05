package TTT;

public class TTTComputerPlayer implements TTTPlayer
{
	public static int[][] findAllLegalMoves(TTTBoard board)
	{
		int [][] allLegalMoves = new int [10][2];
		int legalMoveNumber = 0;
		
		for(int row = 0; row < 3; row++)
		{
			for(int column = 0; column < 3; column++)
			{
				if(board.isSpaceAvalible(row, column)) 
				{
					allLegalMoves [legalMoveNumber][0] = row;
					allLegalMoves [legalMoveNumber][1] = column;
					legalMoveNumber++;
				}
			}
		}
		
		int numberOfLegalMoves = legalMoveNumber;
		allLegalMoves [9][0] = numberOfLegalMoves;
		
		return allLegalMoves;
	}

	public static int minimax(char isTheComputerXOrO, char isItXOrOsTurn, TTTBoard board)
	{
		int minimaxValue;
		if(itIsTheComputersTurn(isTheComputerXOrO, isItXOrOsTurn)) minimaxValue = -2;
		else minimaxValue = +2;

		char winner = board.didSomeoneWin();
		
		if(winner == 'N')
		{
			minimaxValue = addAndEvaluateMoreBranches(isTheComputerXOrO, isItXOrOsTurn, board, minimaxValue);
		}
		else if(winner == 'C')
		{
			minimaxValue = 0;
		}
		else if (winner == isTheComputerXOrO)
		{
			minimaxValue = 1;
		}
		else
		{
			minimaxValue = -1;
		}

		return minimaxValue;
	}

	private static int addAndEvaluateMoreBranches(char isTheComputerXOrO, char isItXOrOsTurn, 
												  TTTBoard board, int minimaxValue)
	{
		int possibleMinimaxValue;
		int[][] allLegalMoves;
		allLegalMoves = findAllLegalMoves(board);
		
		for(int legalMoveNumber = 0; legalMoveNumber < allLegalMoves [9][0]; legalMoveNumber++)
		{
			TTTBoard nextBoard = new TTTBoard();
			nextBoard = board.CreateClonedBoard();
			
			nextBoard.markOnTheBoard(isItXOrOsTurn, allLegalMoves [legalMoveNumber][0], 
					allLegalMoves [legalMoveNumber][1] );
			
			char nextPlayersTurn;
			nextPlayersTurn = setNextPlayersTurn(isItXOrOsTurn);
			
			possibleMinimaxValue = minimax(isTheComputerXOrO, nextPlayersTurn, nextBoard);
			
			minimaxValue = maximizeOrMinimizeMinimaxValue(isTheComputerXOrO, isItXOrOsTurn, minimaxValue, possibleMinimaxValue);
		}
		return minimaxValue;
	}

	private static char setNextPlayersTurn(char isItXOrOsTurn) 
	{
		char nextPlayersTurn;
		if(isItXOrOsTurn == 'X') nextPlayersTurn = 'O';
		else nextPlayersTurn = 'X';
		return nextPlayersTurn;
	}

	private static int maximizeOrMinimizeMinimaxValue(char isTheComputerXOrO, char isItXOrOsTurn, 
													  int minimaxValue, int possibleMinimaxValue) 
	{
		if(itIsTheComputersTurn(isTheComputerXOrO, isItXOrOsTurn))
		{
			if(possibleMinimaxValue > minimaxValue) 
			{
				minimaxValue = possibleMinimaxValue;
			}
		}
		else 
		{
			if(possibleMinimaxValue < minimaxValue) 
			{
				minimaxValue = possibleMinimaxValue;
			}
		}
		return minimaxValue;
	}

	private static boolean itIsTheComputersTurn(char isTheComputerXOrO, char isItXOrOsTurn) 
	{
		return isTheComputerXOrO == isItXOrOsTurn;
	}

	public boolean makeMove(char computersMark, TTTBoard board)
    {
		int [] computersChoice = new int [2];
		int minimaxValue, possibleMinimaxValue;
		int[][] allLegalMoves;
        char isItXOrOsTurn = computersMark;
		char nextPlayersTurn;
		
		minimaxValue = -1;

		allLegalMoves = findAllLegalMoves(board);
		
		for(int legalMoveNumber = 0; legalMoveNumber < allLegalMoves [9][0]; legalMoveNumber++)
		{
			TTTBoard nextBoard = createAndMarkOnNextBoard(isItXOrOsTurn, board, allLegalMoves, legalMoveNumber);
			
			nextPlayersTurn = setNextPlayersTurn(isItXOrOsTurn);
			
			possibleMinimaxValue = minimax(computersMark, nextPlayersTurn, nextBoard);

			minimaxValue = chooseBestMoveForTheGoodGuy(computersChoice, minimaxValue, possibleMinimaxValue, allLegalMoves, legalMoveNumber);

		}
		board.markOnTheBoard(computersMark, computersChoice[0], computersChoice[1]);
		
		return true;
	}

	private static int chooseBestMoveForTheGoodGuy(int[] computersChoice, int minimaxValue, 
										int possibleMinimaxValue, int[][] allLegalMoves, int legalMoveNumber) 
	{
		if(possibleMinimaxValue > minimaxValue) 
		{
			minimaxValue = possibleMinimaxValue;
			computersChoice[0] = allLegalMoves [legalMoveNumber] [0];
			computersChoice[1] = allLegalMoves [legalMoveNumber] [1];
		}
		return minimaxValue;
	}

	private static TTTBoard createAndMarkOnNextBoard(char isItXOrOsTurn, TTTBoard board,
														   int[][] allLegalMoves, int legalMoveNumber) 
	{
		TTTBoard nextBoard = new TTTBoard();
		nextBoard = board.CreateClonedBoard();
		
		nextBoard.markOnTheBoard(isItXOrOsTurn, allLegalMoves [legalMoveNumber][0], 
				allLegalMoves [legalMoveNumber][1] );
		return nextBoard;
	}
	
}
