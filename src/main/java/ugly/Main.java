package ugly;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


/**
 * Main class method to invoke the Ugly Service.
 */
public class Main {

    public static void main(String[] args) throws IOException {

        // Pass the program first argument the file location. an example input file with data is in resources folder.
        if (args.length == 1)
            executeByReadFromFile(args[0]);
        else
            testExecute();

        System.exit(0);

    }

    private static void testExecute() {
        System.out.println(new UglyService().findUglyNumbersCount("1"));
        System.out.println(new UglyService().findUglyNumbersCount("9"));
        System.out.println(new UglyService().findUglyNumbersCount("011"));
        System.out.println(new UglyService().findUglyNumbersCount("12345"));
        System.out.println(new UglyService().findUglyNumbersCount("0011"));
        System.out.println(new UglyService().findUglyNumbersCountOptimized("999999999999"));

    }

    private static void executeByReadFromFile(String arg) throws IOException {
        File file = new File(arg);
        BufferedReader buffer = new BufferedReader(new FileReader(file));
        String line;
        while ((line = buffer.readLine()) != null) {
            line = line.trim();
            System.out.println(new UglyService().findUglyNumbersCount(line));
        }
    }

}


