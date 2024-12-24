import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Main {
    public static TestString testString = new TestString();
    public static String advertisingText = "JavaRush - курсы Java онлайн";

    public static void main(String[] args) {
        PrintStream defaulrOut = System.out;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        System.setOut(new PrintStream(byteArrayOutputStream));
        testString.printSomething();
        System.setOut(defaulrOut);

        String[] lines = byteArrayOutputStream.toString().split("\n");
        for (int numOfLine = 1; numOfLine < lines.length + 1; numOfLine++) {
            System.out.println(lines[numOfLine - 1]);
            if (numOfLine % 2 == 0) System.out.println(advertisingText);
        }
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("first");
            System.out.println("second");
            System.out.println("third");
            System.out.println("fourth");
            System.out.println("fifth");
        }
    }
}