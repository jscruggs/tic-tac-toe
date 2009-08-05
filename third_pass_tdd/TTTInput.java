package TTT;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TTTInput
{
    private BufferedReader reader;

    public TTTInput(InputStream input)
    {
        reader = new BufferedReader( new InputStreamReader(input) );
    }

    public String readingFromInput()
    {
        String inputString = "";

        try
        {
            inputString = reader.readLine();
        }
        catch(IOException e)
        {
            System.out.println(e.getStackTrace());
        }

        return inputString;
    }
}
