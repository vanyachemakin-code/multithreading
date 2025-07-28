package numberGenerator;

import lettersGenerator.FirstLetterGenerator;

import java.io.FileWriter;
import java.io.IOException;

public class NumberGenerator {

    private static int region;
    private static int currentNumber;
    private static FileWriter writer;

    public NumberGenerator(int regionNumber, String filePath) throws IOException {
        region = regionNumber;
        writer = new FileWriter(filePath);
    }

    public void generateNumbers() throws IOException {
        for (int number = 1; number < 1000; number++) {
            currentNumber = number;
            new FirstLetterGenerator().run();
            writer.flush();
        }
        writer.close();
    }

    public static String padNumber(int number) {
        String numberStr = Integer.toString(number);
        switch (numberStr.length()) {
            case 1 -> numberStr = "00" + numberStr;
            case 2 -> numberStr = "0" + numberStr;
        }
        return numberStr;
    }

    public static int getCurrentNumber() {
        return currentNumber;
    }

    public static int getRegion() {
        return region;
    }

    public static FileWriter getWriter() {
        return writer;
    }
}
