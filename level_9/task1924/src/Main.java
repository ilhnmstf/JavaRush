import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static Map<Integer, String> map = new HashMap<Integer, String>();

    static {
        map.put(0, "ноль");
        map.put(1, "один");
        map.put(2, "два");
        map.put(3, "три");
        map.put(4, "четыре");
        map.put(5, "пять");
        map.put(6, "шесть");
        map.put(7, "семь");
        map.put(8, "восемь");
        map.put(9, "девять");
        map.put(10, "десять");
        map.put(11, "одинадцать");
        map.put(12, "двенадцать");
    }
    public static void main(String[] args) {
        String fileName = "/Users/ilhnmstf/IdeaProjects/JavaRush/task1924/src/file";
        printModifiedFile(fileName);
    }

    private static String getFileName() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
            return reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void printModifiedFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            while (reader.ready()) {
                printModifiedLine(reader.readLine());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void printModifiedLine(String line) {
        for (Map.Entry<Integer, String> entry: map.entrySet()){
            line = line.replaceAll("\\b" + entry.getKey() + "\\b", entry.getValue());
        }
        System.out.println(line);
    }
}