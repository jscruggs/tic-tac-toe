package TTT;

import junit.framework.TestCase;
import java.io.PrintStream;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;

public class TTTGameTest extends TestCase
{
	private ByteArrayOutputStream output;
    private PrintStream  stream;

    public void setUp()
	{
		output = new ByteArrayOutputStream();
        stream = new PrintStream(output);
	}

    public void testTttPrint()
    {
        String expectedIntro = "This is a Tic Tac Toe game.\r\n";
        TTTGame.tttPrint(stream, "This is a Tic Tac Toe game.");

        assertEquals(expectedIntro,output.toString());
    }

    public void testReadingFromInput()
    {
        ByteArrayInputStream input = new ByteArrayInputStream("xoxoxo".getBytes());
        TTTInput tttInput = new TTTInput(input);

        assertEquals("xoxoxo",tttInput.readingFromInput());
    }

    public void testPrintOutWinner()
    {
        TTTGame.printOutWinner('X', stream);
        assertEquals("The winner is X\r\n",output.toString());

        output = new ByteArrayOutputStream();
        stream = new PrintStream(output);
        TTTGame.printOutWinner('C', stream);
        assertEquals("Cat's game.\r\n",output.toString());
    }

    public void testAskIfXGoesFirst()
    {
        ByteArrayInputStream input = new ByteArrayInputStream("X".getBytes());
        TTTInput tttInput = new TTTInput(input);

        assertEquals(true, TTTGame.askIfXGoesFirst(tttInput, stream) );
        assertEquals("Which should go first, X or O?\r\n", output.toString());

        output = new ByteArrayOutputStream();
        stream = new PrintStream(output);
        input = new ByteArrayInputStream("2\nO".getBytes());
        tttInput = new TTTInput(input);

        assertEquals(false, TTTGame.askIfXGoesFirst(tttInput, stream) );
        assertEquals("Which should go first, X or O?\r\nEnter 'X' or 'O'.\r\n", output.toString());
    }

    public void testAskIfPlayerIsHumanOrComputer()
    {
        ByteArrayInputStream input = new ByteArrayInputStream("w\nh".getBytes());
        TTTInput tttInput = new TTTInput(input);

        TTTPlayer p = TTTGame.askIfPlayerIsHumanOrComputer(tttInput, stream, 'X');
        assertEquals(new TTTHumanPlayer(tttInput).getClass(),p.getClass());
        String expected = "Should X be a human or a computer?\r\nEnter 'Human' or 'Computer'.\r\n";
        assertEquals(expected, output.toString());
    }

    public void testGameLoopWithHumans()
    {
        ByteArrayInputStream input = new ByteArrayInputStream("1\n2\n4\n5\n7".getBytes());
        TTTInput tttInput = new TTTInput(input);
        TTTBoard board = new TTTBoard();
        TTTPlayer playerX = new TTTHumanPlayer(tttInput);
        TTTPlayer playerO = new TTTHumanPlayer(tttInput);

        boolean doesXGoFirst = true;
        char winner;

        winner = TTTGame.gamePlayLoop(board, playerX, playerO, doesXGoFirst);

        assertEquals('X',winner);
    }

        public void testGameLoopWithOneComputer()
    {
        ByteArrayInputStream input = new ByteArrayInputStream("1\n4\n3\n8\n9".getBytes());
        TTTInput tttInput = new TTTInput(input);
        TTTBoard board = new TTTBoard();
        TTTPlayer playerX = new TTTHumanPlayer(tttInput);
        TTTPlayer playerO = new TTTComputerPlayer();

        boolean doesXGoFirst = true;
        char winner;

        winner = TTTGame.gamePlayLoop(board, playerX, playerO, doesXGoFirst);

        assertEquals('C',winner);
    }
}
