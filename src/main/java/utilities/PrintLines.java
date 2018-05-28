package utilities;

import org.apache.commons.io.input.ReversedLinesFileReader;

import java.io.File;
import java.io.IOException;

public class PrintLines {
    public static void main(String[] args) throws IOException {


        File file = new File(args[0]);
        int n_lines = args.length > 1 ? Integer.parseInt(args[1]) : 1;
        int counter = 0;
        ReversedLinesFileReader object = new ReversedLinesFileReader(file);
        while(counter < n_lines)
        {
            System.out.println(object.readLine());
            counter++;
        }
    }
}
