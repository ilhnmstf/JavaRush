import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> words = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(args[0]));
             FileWriter writer = new FileWriter(args[1])
        ) {
            while (reader.ready()) {
                for (String word: reader.readLine().split(" ")) {
                    if (word.length() > 6) words.add(word);
                }
            }
            writer.write(String.join(",", words));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}