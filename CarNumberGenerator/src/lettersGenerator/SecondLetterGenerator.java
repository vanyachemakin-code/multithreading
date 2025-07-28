package lettersGenerator;

public class SecondLetterGenerator implements Runnable{

    private static char secondLetter;

    public synchronized static char getSecondLetter() {
        return secondLetter;
    }

    @Override
    public void run() {
        for (char letter: FirstLetterGenerator.LETTERS) {
            secondLetter = letter;
            new ThirdLetterGenerator().run();
        }
    }
}
