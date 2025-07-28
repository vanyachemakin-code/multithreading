package lettersGenerator;

public class FirstLetterGenerator implements Runnable{

    public static final char[] LETTERS = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};
    private static char firstLetter;

    public synchronized static char getFirstLetter() {
        return firstLetter;
    }

    @Override
    public void run() {
        for (char letter: FirstLetterGenerator.LETTERS) {
            firstLetter = letter;
            new SecondLetterGenerator().run();
        }
    }
}
