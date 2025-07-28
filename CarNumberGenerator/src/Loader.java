import numberGenerator.NumberGenerator;

import java.io.FileWriter;
import java.io.IOException;

public class Loader {

    private static final String FILE_PATH =
            "/Users/vanyachemakin/IdeaProjects/multithreading/CarNumberGenerator/res/numbers.txt";

    public static void main(String[] args) throws IOException {
        NumberGenerator numberGenerator = new NumberGenerator(36, FILE_PATH);
        numberGenerator.generateNumbers();
    }
}
