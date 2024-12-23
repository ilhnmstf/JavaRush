import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static List<String> words = new ArrayList<>();
    static {
        words.add("Б");
        words.add("А");
        words.add("В");
    }

    public static void main(String[] args) {
        Set<String> setWords = new HashSet<>(words);
        String fileName = getFileName();
        searchMatchingLinesInFile(fileName, setWords);
    }

    private static void searchMatchingLinesInFile(String fileName, Set<String> setWords) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            while (reader.ready()) {
                String line = reader.readLine();
                if (isSuitableString(line, setWords)) System.out.println(line);
            }
        } catch (IOException exc) {
            throw new RuntimeException(exc);
        }
    }

    private static String getFileName() {
        try (BufferedReader readFileName = new BufferedReader(new InputStreamReader(System.in))) {
            return readFileName.readLine();
        } catch (IOException exc) {
            throw new RuntimeException(exc);
        }
    }

    private static boolean isSuitableString(String line, Set<String> setWords) {
        String[] string = line.split(" ");
        int count = 0;
        for (String word: string) {
            if (setWords.contains(word)) count += 1;
            if (count > 2) return false;
        }
        return count == 2;
    }
}