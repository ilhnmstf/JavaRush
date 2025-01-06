import java.util.ArrayList;
import java.util.List;

public class Crossword {
    private final int[][] CROSSWORD;
    private final List<Main.Word> FOUND_WORDS;
    private final Integer LEN_Y;
    private final Integer LEN_X;

    public Crossword(int[][] crossword) {
        CROSSWORD = crossword;
        FOUND_WORDS = new ArrayList<>();
        LEN_Y = CROSSWORD.length - 1;
        LEN_X = CROSSWORD[1].length - 1;
    }


    public List<Main.Word> detectAllWords(String... searchWords) {
        Words.fillingWords(searchWords);
        Words words = Words.START;

        for (int y = 0; y < CROSSWORD.length; y++) {
            for (int x = 0; x < CROSSWORD[y].length; x++) {
                fillingWords(words, CROSSWORD, y, x);
            }
        }
        return FOUND_WORDS;
    }

    private void fillingWords(Words words, int[][] crossword, int y, int x) {
        if (words.containsNextChar((char) crossword[y][x])) {
            Line(words, crossword, x, y, -1, 1);  // left-down (vector)
            Line(words, crossword, x, y, -1, 0);  // left
            Line(words, crossword, x, y, -1, -1); // left-top
            Line(words, crossword, x, y, 0, -1);  // top
            Line(words, crossword, x, y, 0, 1);   // down
            Line(words, crossword, x, y, 1, -1);  // top-right
            Line(words, crossword, x, y, 1, 0);   // right
            Line(words, crossword, x, y, 1, 1);   // down-right
        }
    }

    private void Line(Words words, int[][] crossword, int startX, int startY, int offsetX, int offsetY) {
        StringBuilder wordBuilder = new StringBuilder();
        int endX = startX;
        int endY = startY;
        char chr;

        while (LEN_X >= endX && endX >= 0 && LEN_Y >= endY && endY >= 0 && words != null) {
            chr = (char) crossword[endY][endX];

            if (words.containsNextChar(chr)) {
                wordBuilder.append(chr);
                words = words.getNextChar(chr);

                if (words.containsNextChar('.')) {
                    Main.Word word = new Main.Word(wordBuilder.toString());
                    word.setStartPoint(startX, startY);
                    word.setEndPoint(endX, endY);
                    FOUND_WORDS.add(word);
                }
            } else return;

            endX += offsetX;
            endY += offsetY;
        }
    }
}
