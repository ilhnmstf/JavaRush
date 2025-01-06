import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Words {
    public static final Words START = new Words('.');

    public static void fillingWords(String... words) {
        for (String word: words) {
            Words currentChar = Words.START;
            for (char chr: word.toCharArray()) {
                if (!currentChar.containsNextChar(chr)) currentChar.setNextChar(new Words(chr));
                currentChar = currentChar.getNextChar(chr);
            }
            currentChar.setNextChar(new Words('.'));
        }
    }

    public static String tostring() {
        List<String> words = new ArrayList<>();

        for (Words chr: START.getNextChars()) {
            String word = "";
            word += chr.getVal();
            buildWord(words ,word, chr);
        }
        return words.toString();
    }

    private static void buildWord(List<String> words, String word, Words chr){
        for (Words nextChar: chr.getNextChars()) {
            StringBuilder builder = new StringBuilder(word);
            if (nextChar.getVal() != '.') {
                builder.append(nextChar.getVal());
                buildWord(words, builder.toString(), nextChar);
            }
            else words.add(word);
        }
    }

    private final Character VAL;
    private final ArrayList<Words> NEXT_CHARS;
    private final Map<Character, Integer> CHARS_INDEXES_IN_ARRAY;
    private final Set<Integer> FREE_INDEXES;

    private Words(char val) {
        this.VAL = val;
        this.NEXT_CHARS = new ArrayList<>();
        this.CHARS_INDEXES_IN_ARRAY = new HashMap<>();
        this.FREE_INDEXES = new HashSet<>();
    }

    public char getVal()  {
        return VAL;
    }

    private List<Words> getNextChars(){
        return NEXT_CHARS;
    }

    public boolean containsNextChar(Character chr) {
        return CHARS_INDEXES_IN_ARRAY.containsKey(chr);
    }

    public Words getNextChar(Character chr) {
        return NEXT_CHARS.get(CHARS_INDEXES_IN_ARRAY.get(chr));
    }

    private void setNextChar(Words chr) {
        int setIndex;

        if (!FREE_INDEXES.isEmpty()) {
            setIndex = FREE_INDEXES.stream().findFirst().get();
            FREE_INDEXES.remove(setIndex);
            NEXT_CHARS.set(setIndex, chr);
        }
        else  {
            setIndex = NEXT_CHARS.size();
            NEXT_CHARS.add(chr);
        }

        CHARS_INDEXES_IN_ARRAY.put(chr.getVal(), setIndex);
    }
}

