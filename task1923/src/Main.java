import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader(args[0]));
             FileWriter writer = new FileWriter(args[0])
        ) {
            while (reader.ready()) {
                for (String word: reader.readLine().split(" ")) {
                    if (word.matches(".*[0-9].*")) writer.write(word + " ");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}