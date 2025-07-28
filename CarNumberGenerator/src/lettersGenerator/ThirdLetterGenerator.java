package lettersGenerator;

import numberGenerator.Generator;

import java.io.IOException;

public class ThirdLetterGenerator implements Runnable{

    private static char thirdLetter;

    public synchronized static char getThirdLetter() {
        return thirdLetter;
    }

    @Override
    public void run() {
        for (char letter: FirstLetterGenerator.LETTERS) {
            thirdLetter = letter;
            try {
                new Generator().run();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
