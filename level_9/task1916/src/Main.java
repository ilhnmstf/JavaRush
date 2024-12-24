import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
Знакомство с тегами
*/

public class Main {
    private static final List<String> TAGS = new ArrayList<>();
    private static final Stack<String> STACK = new Stack<>();

    public static void main(String[] args) {
        String fileName = getFileName();
        StringBuilder fileContent = getFileContent(fileName);
        String tag = args[0];
        addTags(fileContent, tag);
        TAGS.forEach(System.out::println);
    }

    private static StringBuilder getFileContent(String fileName) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            while (reader.ready()) {
                builder.append(reader.readLine());
            }
            return builder;
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

    public static void addTags(StringBuilder builder, String tag) {
        String fileContent = builder.toString().replaceAll("[\\r\\n]+", "");

        while(!fileContent.isEmpty()) {
            int beginning = fileContent.indexOf("<" + tag);
            int ending = fileContent.indexOf(">", beginning) + 1;
            STACK.push(fileContent.substring(beginning, ending));

            while (!STACK.empty()) {
                int start = fileContent.indexOf("<", ending);
                int end = fileContent.indexOf(">", start) + 1;
                String nestedTag = fileContent.substring(start, end);

                if (nestedTag.startsWith("</")) STACK.pop();
                else STACK.push(nestedTag);
                ending = end;
            }

            TAGS.add(fileContent.substring(beginning, ending));
            fileContent = fileContent.substring(ending);
        }
    }
}