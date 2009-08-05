package TTT;

public class TTTHumanPlayer implements TTTPlayer
{
    private TTTInput tttInput;

    public TTTHumanPlayer(TTTInput tttInput)
    {
        this.tttInput = tttInput;
    }

    public boolean makeMove(char playersMark, TTTBoard board)
    {
        int move = askForMove();

        int row = convertToRow(move);
        int column = convertToColumn(move);
        boolean isSpaceAvalible = board.isSpaceAvalible(row, column);

        if(isSpaceAvalible)
        {
            board.markOnTheBoard(playersMark, row, column);
        }
        return isSpaceAvalible;
    }

    private int askForMove()
    {
        int move = 0;
        boolean badInput = false;
        do
        {
            try
            {
                move = Integer.parseInt(tttInput.readingFromInput());
                badInput = false;
            }
            catch (NumberFormatException e)
            {
                badInput = true;
                TTTGame.tttPrint(System.out, "Enter an integer.");
            }
        }
        while(badInput);
        return move;
    }

    private int convertToColumn(int move)
    {
        int column;
                if(move == 1 || move == 4 || move == 7)
        {
            column = 0;
        }
        else if(move == 2 || move == 5 || move == 8)
        {
            column = 1;
        }
        else
        {
            column = 2;
        }
        return column;
    }

    private int convertToRow(int move)
    {
        int row;
        if(move < 4)
        {
            row = 0;
        }
        else if(move < 7)
        {
            row = 1;
        }
        else
        {
            row = 2;
        }
        return row;
    }
}
