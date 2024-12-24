import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        String fileName = getFileName();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            while (reader.ready()) {
                StringBuilder reverseLine = new StringBuilder(reader.readLine());
                System.out.println(reverseLine.reverse());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String getFileName() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            return reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}