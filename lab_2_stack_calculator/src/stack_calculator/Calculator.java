package stack_calculator;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class Calculator {

    public static void calculate(String filename) {
        Reader reader = null;
        try {
            reader = new InputStreamReader(new FileInputStream("input.txt"));

        }
        catch (IOException e) {
            System.err.println("Error while reading file: " + e.getLocalizedMessage());
        }
        finally {
            if (reader != null) {
                try { reader.close(); }
                catch (IOException e) { e.printStackTrace(System.err); }
            }
            // then we can use stdin
        }
    }
}
