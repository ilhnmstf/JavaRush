import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //you can find your_file_name.tmp in your TMP directory or adjust outputStream/inputStream according to your file's actual location
        //вы можете найти your_file_name.tmp в папке TMP или исправьте outputStream/inputStream в соответствии с путем к вашему реальному файлу
        try {
            File yourFile = File.createTempFile("your_file_name", null);
            OutputStream outputStream = new FileOutputStream(yourFile);
            InputStream inputStream = new FileInputStream(yourFile);

            JavaRush javaRush = new JavaRush();
            //initialize users field for the javaRush object here - инициализируйте поле users для объекта javaRush тут
            javaRush.save(outputStream);
            outputStream.flush();

            JavaRush loadedObject = new JavaRush();
            loadedObject.load(inputStream);
            //here check that the javaRush object is equal to the loadedObject object - проверьте тут, что javaRush и loadedObject равны

            outputStream.close();
            inputStream.close();

        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Oops, something is wrong with my file");
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Oops, something is wrong with the save/load method");
        }
    }

    public static class JavaRush {
        public List<User> users = new ArrayList<>();

        public void save(OutputStream outputStream) throws Exception {
            PrintWriter writer = new PrintWriter(outputStream);
            for (User user: users) {
                StringBuilder userInfo = new StringBuilder();
                userInfo.append(user.getFirstName());
                userInfo.append("/");
                userInfo.append(user.getLastName());
                userInfo.append("/");
                userInfo.append(user.getBirthDate().getTime());
                userInfo.append("/");
                userInfo.append(user.isMale());
                userInfo.append("/");
                userInfo.append(user.getCountry());
                writer.println(userInfo);
            }
            writer.close();
        }

        public void load(InputStream inputStream) throws Exception {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            while (reader.ready()) {
                String[] userInfo = reader.readLine().split("/");
                if (userInfo.length < 5) break;

                User user = new User();
                user.setFirstName(userInfo[0]);
                user.setLastName(userInfo[1]);
                user.setBirthDate(new Date(Long.parseLong(userInfo[2])));
                user.setMale(Boolean.parseBoolean(userInfo[3]));
                switch (userInfo[4]) {
                    case "UKRAINE" -> user.setCountry(User.Country.UKRAINE);
                    case "RUSSIA" -> user.setCountry(User.Country.RUSSIA);
                    default -> user.setCountry(User.Country.OTHER);
                }
                users.add(user);
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            JavaRush javaRush = (JavaRush) o;

            return users != null ? users.equals(javaRush.users) : javaRush.users == null;

        }

        @Override
        public int hashCode() {
            return users != null ? users.hashCode() : 0;
        }
    }
}