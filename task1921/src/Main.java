import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class Main {
    public static final List<Person> PEOPLE = new ArrayList<Person>();

    public static void main(String[] args) {
        String fileName = args[0];

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            while (reader.ready()) {
                PEOPLE.add(createPerson(reader.readLine()));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Person createPerson(String line) {
        String[] info = line.split(" ");
        String name = getName(info);
        Date birthDay = getBirthDay(info);
        return new Person(name, birthDay);
    }

    private static Date getBirthDay(String[] info) {
        int lenInfo = info.length;
        try {
            int year = Integer.parseInt(info[lenInfo - 1]);
            int month = Integer.parseInt(info[lenInfo - 2]) - 1;
            int day = Integer.parseInt(info[lenInfo - 3]);
            return new GregorianCalendar(year, month, day).getTime();
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }

    private static String getName(String[] info) {
        StringBuilder name = new StringBuilder();
        for (int i = 0; i < info.length - 3; i++) name.append(info[i]).append(" ");
        return name.toString().trim();
    }
}