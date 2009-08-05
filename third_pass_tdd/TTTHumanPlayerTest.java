package TTT;

import junit.framework.TestCase;
import java.io.ByteArrayInputStream;

public class TTTHumanPlayerTest extends TestCase
{
    private TTTBoard board;
    private ByteArrayInputStream input;

    public void setUp()
    {
        board = new TTTBoard();
        char [][] findAllLegalMovesArray = {	{    'X', 0 ,'O'},
                                            {(char)   0 ,'O','X'},
                                            {(char)   0 ,'O', 0 } };

        TestUtils.convertArrayToTttBoard(board, findAllLegalMovesArray);
    }

    public void testMakeMove()
    {
        input = new ByteArrayInputStream("2\n9".getBytes());
        TTTInput tttInput = new TTTInput(input);
        TTTPlayer anotherPlayer = new TTTHumanPlayer(tttInput);

        anotherPlayer.makeMove('X', board);

		String expectedOutput = "\n" +
                                "X|X|O\n" +
								"-+-+-\n" +
								"4|O|X\n" +
								"-+-+-\n" +
								"7|O|9\n" +
                                "\n";

		assertEquals(expectedOutput, board.toString() );

        anotherPlayer.makeMove('X', board);

		expectedOutput =        "\n" +
                                "X|X|O\n" +
								"-+-+-\n" +
								"4|O|X\n" +
								"-+-+-\n" +
								"7|O|X\n" +
                                "\n";

		assertEquals(expectedOutput, board.toString() );
    }
}
