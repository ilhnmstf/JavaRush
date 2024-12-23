import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("/Users/ilhnmstf/IdeaProjects/JavaRush/task1923/src/file1"));
             FileWriter writer = new FileWriter("/Users/ilhnmstf/IdeaProjects/JavaRush/task1923/src/file2")
        ) {
            while (reader.ready()) {
                for (String word: reader.readLine().split(" ")) {
                    if (word.matches(".*\\d.*")) writer.write(word + " ");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}