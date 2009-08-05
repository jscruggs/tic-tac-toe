package TTT;

public class TestUtils
{
    public static void convertArrayToTttBoard(TTTBoard aBoard, char[][] tttBoardArray)
    {
        for(int row = 0; row < 3; row++)
        {
            for(int column = 0; column < 3; column++)
            {
                aBoard.markOnTheBoard(tttBoardArray [row][column], row, column);
            }
        }
    }
}
