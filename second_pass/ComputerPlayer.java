/*
 * Created on May 14, 2004
 *
 *ComputerPlayer.java 
 *Looks at the Tic Tac Toe board and decideds what move to make
 *
 */

public class ComputerPlayer {

	/*
	 * makeTheComputerMove looks at all the possible moves and determines the best one
	 * Receives: 
	 * theBoard [][], a matrix representing a tic tac toe board 
	 * isComputerX a boolean which tells the computer which side it's on
	 * Returns:
	 * choice, an int [] with the [0]row and [1]column of the best choice
	 */
	public static int [] makeTheComputerMove(int currentBoard [] [], boolean isComputerX, int searchDepthLimit)
	{
		char theComputerPlayersLetter, nextTurn;
		int possibleWinnerScore, randomBestMoveNumber, goodGuysNumber, winnerScore = -2,
			currentSearchDepth = 0, numberOfBestMoves=1;
		int [][] bestMoves = new int [10][2];
		int [] choice = new int [2];
		int [][] futureBoard;
		int [][] allLegalMoves = new int [10] [2];
		
		if(isComputerX) {theComputerPlayersLetter = 'X'; goodGuysNumber = 20;}
			else {theComputerPlayersLetter = 'O'; goodGuysNumber = 1;}

		allLegalMoves = TheBoard.findAllLegalMoves(currentBoard);
		
		for(int i = 0; i <= allLegalMoves [9][0]; i++ ) // allLegalMoves [9][0] is the number of legal moves
		{
			futureBoard = copy(currentBoard);
			futureBoard [ allLegalMoves[i][0] ] [ allLegalMoves[i][1] ] = goodGuysNumber;
			
			if(theComputerPlayersLetter == 'X') nextTurn = 'O';
			else nextTurn ='X';
			
			possibleWinnerScore = minimax(theComputerPlayersLetter, nextTurn, futureBoard,
					searchDepthLimit, currentSearchDepth);
			
			if(possibleWinnerScore > winnerScore)
			{
				winnerScore = possibleWinnerScore;
				choice [0] = allLegalMoves [i] [0];
				choice [1] = allLegalMoves [i] [1];
			}
		}

		return choice;
	}
	
	/*
	 * minimax looks at all the possible moves and scores them
	 * recieves:
	 * theComputerPlayersLetter, a char indicating the computer's letter
	 * XorOsTurn, a char indicating who's turn it is
	 * currentBoard, a matrix representing a tic tac toe board
	 * Returns:
	 * winnerScore, an int which is -1 if the bad guy wins, +1 is the good guy wins, and 0 is it's a tie
	 */
	public static int minimax(char theComputerPlayersLetter, char XorOsTurn, int [][] currentBoard, 
							  int searchDepthLimit, int currentSearchDepth)
	{
		int winnerScore, goodGuysNumber, possibleWinnerScore;
		int [][] futureBoard;
		int [][] allLegalMoves = new int [10] [2];
		char theWinner = TheBoard.didSomeoneWin(currentBoard);
		char whosTurnIsItNext;
		
		if(theWinner == theComputerPlayersLetter) winnerScore = 1;
			else if( (theWinner == 'N') || (theWinner == 'C') ) winnerScore = 0;
			else winnerScore = -1;
	
		if(theWinner == 'N')
		{
			currentSearchDepth++;
			if(currentSearchDepth > searchDepthLimit) {theWinner = 'C'; winnerScore = 0;}
		}
		
		if(theWinner == 'N') // skip this next bit if there's already a winner
		{
			allLegalMoves = TheBoard.findAllLegalMoves(currentBoard);
			if(theComputerPlayersLetter == XorOsTurn) winnerScore = -2;
			else winnerScore = 2;
			
			for(int i = 0; i <= allLegalMoves [9][0]; i++ ) // allLegalMoves [9][0] is the number of legal moves
			{
				if(XorOsTurn == 'X') goodGuysNumber = 20;
					else goodGuysNumber = 1;
				
				futureBoard = copy(currentBoard);
				futureBoard[ allLegalMoves[i][0] ] [ allLegalMoves[i][1] ] = goodGuysNumber;

				if(XorOsTurn == 'X') whosTurnIsItNext = 'O';
					else whosTurnIsItNext ='X';
				
				int currentSearchDepthCopy = currentSearchDepth;
				possibleWinnerScore = minimax (theComputerPlayersLetter, whosTurnIsItNext, futureBoard,
						searchDepthLimit, currentSearchDepthCopy);
				
				if(theComputerPlayersLetter == XorOsTurn) 
				{
					if(possibleWinnerScore > winnerScore) winnerScore = possibleWinnerScore; // maximize if it's the good guy's turn
				}
				else if(possibleWinnerScore < winnerScore) winnerScore = possibleWinnerScore; // minimize if it's the bad guy's
			}
		}
		return winnerScore;
	}
	
	/*
	 * copy produces a copy of the current board
	 */
	public static int [][] copy(int[][] boardToCopy)
	{
		int [][] copiedBoard = new int [3] [3];
		
		for(int row = 0; row < 3; row++)
		{
			for(int column = 0; column < 3; column++)
			{
				copiedBoard [row] [column] = boardToCopy [row] [column];
			}
		}

		return copiedBoard;
	}
}
