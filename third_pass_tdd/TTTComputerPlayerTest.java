package TTT;

import junit.framework.TestCase;

public class TTTComputerPlayerTest extends TestCase
{
	private TTTBoard aBoard;

	public void setUp()
	{
		aBoard = new TTTBoard();
	}

    public void testFindAllLegalMoves()
    {

        char [][] findAllLegalMovesArray = {	{    'X', 0 ,'O'},
                                            {(char)   0 ,'O','X'},
                                            {(char)   0 ,'O', 0 } };

        TestUtils.convertArrayToTttBoard(aBoard, findAllLegalMovesArray);

        int [][] allLegalMoves = new int [10][2];
        allLegalMoves = TTTComputerPlayer.findAllLegalMoves(aBoard);

        assertEquals(allLegalMoves [9][0], 4);
        assertEquals(allLegalMoves [0][0], 0);
        assertEquals(allLegalMoves [0][1], 1);
        assertEquals(allLegalMoves [3][0], 2);
        assertEquals(allLegalMoves [3][1], 2);
    }

    public void testMinimaxOnePossibleMove()
    {
        char isTheComputerXOrO = 'X';
        char isItXOrOsTurn = 'O';

        char [][] minimaxMinusOneTestArray = {	{'X', 0 ,'O'},
                                                {'O','O','X'},
                                                {'X','O','X'} };

        TestUtils.convertArrayToTttBoard(aBoard, minimaxMinusOneTestArray);
        int minimaxResult = TTTComputerPlayer.minimax(isTheComputerXOrO, isItXOrOsTurn, aBoard);

        assertEquals(-1, minimaxResult);
    }

    public void testMinimaxTwoPossibleMoves()
    {
        char isTheComputerXOrO = 'X';
        char isItXOrOsTurn = 'O';

        char [][] minimaxMinusOneTestArray = {	{       'X', 0 ,'O'},
                                                {(char)  0 ,'O','X'},
                                                {       'X','O','X'} };

        TestUtils.convertArrayToTttBoard(aBoard, minimaxMinusOneTestArray);
        int minimaxResult = TTTComputerPlayer.minimax(isTheComputerXOrO, isItXOrOsTurn, aBoard);

        assertEquals(-1, minimaxResult);
    }

    public void testMinimaxManyPossibleMoves()
    {
        char isTheComputerXOrO = 'X';
        char isItXOrOsTurn = 'X';

        char [][] minimaxMinusOneTestArray = {	{  'O','X', 0 },
                                                {  'O', 0 , 0 },
                                            {(char) 0 , 0 , 0 } };

        TestUtils.convertArrayToTttBoard(aBoard, minimaxMinusOneTestArray);
        int minimaxResult = TTTComputerPlayer.minimax(isTheComputerXOrO, isItXOrOsTurn, aBoard);

        assertEquals(-1, minimaxResult);
    }

    public void testMinimaxManyMorePossibleMoves()
    {
        char isTheComputerXOrO = 'O';
        char isItXOrOsTurn = 'O';

        char [][] minimaxPlusOneTestArray = {	{  'O','X', 0 },
                                            {(char) 0 , 0 , 0 },
                                            {(char) 0 , 0 , 0 } };

        TestUtils.convertArrayToTttBoard(aBoard, minimaxPlusOneTestArray);
        int minimaxResult = TTTComputerPlayer.minimax(isTheComputerXOrO, isItXOrOsTurn, aBoard);

        assertEquals(1, minimaxResult);
    }

    public void testMakeTheComputerMove()
    {
        TTTPlayer player = new TTTComputerPlayer();
        char computersMark = 'O';

        char [][] makeTheComputerMoveTestArray = {	{  'O','X', 0 },
                                                {(char) 0 , 0 , 0 },
                                                {(char) 0 , 0 , 0 } };

        TestUtils.convertArrayToTttBoard(aBoard, makeTheComputerMoveTestArray);

        player.makeMove(computersMark, aBoard);

		String expected =       "\n" +
                                "O|X|3\n" +
								"-+-+-\n" +
								"O|5|6\n" +
								"-+-+-\n" +
								"7|8|9\n" +
                                "\n";

        assertEquals(expected, aBoard.toString());
    }

    public void testWillTheComputerFindAWin()
    {
        char [][] testArray                     = {	{  'O', 0 , 0 },
                                                {(char)'O','X','X'},
                                                {(char) 0 , 0 ,'X'} };

        TestUtils.convertArrayToTttBoard(aBoard, testArray);
        int minimaxResult = TTTComputerPlayer.minimax('O', 'O', aBoard);

        assertEquals(1, minimaxResult);
    }
}
