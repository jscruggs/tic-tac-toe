package TTT;

import junit.framework.TestCase;

public class TTTBoardTest extends TestCase
{
	private TTTBoard aBoard;
	
	public void setUp()
	{
		aBoard = new TTTBoard();
	}
	
	public void testMarkOnTheBoard()
	 {
	 	char XOrO = 'X';
	 	
	 	for(int i = 0; i<2; i++)
	 	{
	 		if(i == 1) XOrO = 'O';
	 		
	 		for(int row = 0; row < 3; row++)
	 		{
	 			for(int column = 0; column < 3; column++)
	 			{
	 				aBoard.markOnTheBoard(XOrO, row, column);
	 				
	 				assertEquals(XOrO, aBoard.returnBoardAsCharArray() [row][column]);
	 			}
	 		}
	 	}
	 }
	 
	 public void testColumnWinForX()
	 {	
	 	char [][] collumnWinForXArray = { 	{(char)  0 ,'X', 0 },
	 										{       'O','X','O'},
	 										{(char)  0 ,'X','O'} };
	 		
	 	TestUtils.convertArrayToTttBoard(aBoard, collumnWinForXArray);
	 		
	 	assertEquals('X', aBoard.didSomeoneWin());
	 	
	 }
	 
	 public void testDiagonalWinForO()
	 {
	 	char [][] diagonalWinForOArray = { 	{'O','X','X'},
	 										{'O','O','X'},
	 										{'X','O','O'} };
	 	
	 	TestUtils.convertArrayToTttBoard(aBoard, diagonalWinForOArray);
	 
	 	assertEquals('O',aBoard.didSomeoneWin());
	 }
	 
	 public void testCatsGame()
	 {
	 	char [][] catsGameArray = { {'X','X','O'},
	 								{'O','O','X'},
	 								{'X','O','X'} };
	 	
	 	TestUtils.convertArrayToTttBoard(aBoard, catsGameArray);
	 	
	 	assertTrue(aBoard.didSomeoneWin() == 'C');
	 	
	 	TTTBoard anotherBoard = new TTTBoard();
	 	
	 	assertEquals('N', anotherBoard.didSomeoneWin());
	 }

    public void testCreateClonedBoard()
	 {
	 	aBoard.markOnTheBoard('X',1,1);
	 	
	 	TTTBoard clonedBoard;
	 	clonedBoard = aBoard.CreateClonedBoard();
	 	
	 	char [][] clonedBoardArray = new char [3][3];
	 	clonedBoardArray = clonedBoard.returnBoardAsCharArray();
	 	
	 	char [][] aBoardArray = new char [3][3];
	 	aBoardArray = aBoard.returnBoardAsCharArray();
	 	
	 	for(int row = 0; row < 3; row++)
	 	{
	 		for(int column = 0; column < 3; column++)
	 		{
	 			assertEquals(aBoardArray [row][column], clonedBoardArray [row] [column]);
	 		}
	 	}
	 }

	public void testToString()
	{
		char [][] toStringTestArray = {	{  'X', 0 ,'O'},
									{(char) 0 ,'O','X'},
										{  'X','O', 0 } };
		
		TestUtils.convertArrayToTttBoard(aBoard, toStringTestArray);
		
		String toStringOutput = "\n" +
                                "X|2|O\n" +
								"-+-+-\n" +
								"4|O|X\n" +
								"-+-+-\n" +
								"X|O|9\n" +
                                "\n";
		
		assertEquals(toStringOutput, aBoard.toString() );
	}
}
