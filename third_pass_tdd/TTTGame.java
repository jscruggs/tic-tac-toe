package TTT;

import java.io.*;

public class TTTGame {

    private TTTInput tttInput;
	private String introduction = 	"This is a Tic Tac Toe game.\n" +
							        "You can play a friend, the computer, or watch" +
							        " the computer play itself.\n";
    public TTTGame(InputStream input)
    {
         tttInput = new TTTInput(input);
    }

    public static void main(String args [])
    {
        TTTGame game = new TTTGame(System.in);
        TTTBoard board = new TTTBoard();
        TTTPlayer playerX, playerO;

        tttPrint(System.out, game.introduction);

        playerX = askIfPlayerIsHumanOrComputer(game.tttInput, System.out, 'X');
        playerO = askIfPlayerIsHumanOrComputer(game.tttInput, System.out, 'O');
        boolean doesXGoFirst = askIfXGoesFirst(game.tttInput, System.out);

        char winner = gamePlayLoop(board, playerX, playerO, doesXGoFirst);

        tttPrint(System.out, board.toString());
        printOutWinner(winner, System.out);
    }

    public static void printOutWinner(char winner, PrintStream output)
    {
        if(winner == 'C')
        {
            tttPrint(output, "Cat's game.");
        }
        else
        {
            tttPrint(output, "The winner is " + winner);
        }
    }

    public static boolean askIfXGoesFirst(TTTInput tttInput, PrintStream stream)
    {
        boolean doesXGoFirst;
        char mark;
        tttPrint(stream, "Which should go first, X or O?");
        while(true)
        {
            mark = getFirstLetterOfInput(tttInput);
            if( (mark == 'X') || (mark == 'O') ) break;
            tttPrint(stream, "Enter 'X' or 'O'.");
        }
        if(mark == 'X')
        {
            doesXGoFirst = true;
        }
        else
        {
            doesXGoFirst = false;
        }
        return doesXGoFirst;
    }

    public static TTTPlayer askIfPlayerIsHumanOrComputer(TTTInput tttInput,
                                                         PrintStream stream,  char mark)
    {
        TTTPlayer player;
        char markController;
        tttPrint(stream, "Should " + mark + " be a human or a computer?");
        while(true)
        {
            markController = getFirstLetterOfInput(tttInput);
            if( (markController == 'H') || (markController == 'C') ) break;
            tttPrint(stream, "Enter 'Human' or 'Computer'.");
        }
        if(markController == 'C')
        {
            player = new TTTComputerPlayer();
        }
        else
        {
            player = new TTTHumanPlayer(tttInput);
        }
        return player;
    }

    private static char getFirstLetterOfInput(TTTInput tttInput)
    {
        return tttInput.readingFromInput().toUpperCase().charAt(0);
    }


    public static char gamePlayLoop(TTTBoard board, TTTPlayer playerX,
                                     TTTPlayer playerO, boolean doesXGoFirst)
    {
        boolean isItALegalMove;
        boolean isItTheFirstTurn = true;
        char winner;
        do
        {
            if(doesXGoFirst || !isItTheFirstTurn)
            {
                tttPrint(System.out, board.toString() + "X's move: ");
                do
                {
                    isItALegalMove = playerX.makeMove('X',board);
                }
                while(!isItALegalMove);
            }
            isItTheFirstTurn = false;

            winner = board.didSomeoneWin();
            if(winner != 'N') break;

            tttPrint(System.out, board.toString() + "O's move");
            do
            {
                isItALegalMove = playerO.makeMove('O',board);
            }
            while(!isItALegalMove);

            winner = board.didSomeoneWin();
        }
        while(winner == 'N');
        return winner;
    }

    public static void tttPrint(PrintStream stream, String s)
    {
        stream.println(s);
    }
}
