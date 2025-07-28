package numberGenerator;

import lettersGenerator.FirstLetterGenerator;
import lettersGenerator.SecondLetterGenerator;
import lettersGenerator.ThirdLetterGenerator;

import java.io.IOException;

public class Generator implements Runnable{

    private final StringBuilder builder;

    public Generator() throws IOException {
        builder = new StringBuilder();
    }

    @Override
    public void run() {
        try {
            NumberGenerator.getWriter().write(appendNumber() + "\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String appendNumber() {
        builder.append(FirstLetterGenerator.getFirstLetter());
        builder.append(NumberGenerator.padNumber(NumberGenerator.getCurrentNumber()));
        builder.append(SecondLetterGenerator.getSecondLetter());
        builder.append(ThirdLetterGenerator.getThirdLetter());
        builder.append(NumberGenerator.getRegion());
        return builder.toString();
    }
}
