import java.io.Externalizable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main implements Externalizable, Runnable {
    public static void main(String[] args) {
//        System.out.println(new Main(4));
        String fileName = "level_10/task2014/src/file.ser";
        Main savedObject = new Main(5);
        Main loadedObject = new Main(6);

        try (ObjectOutput out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            savedObject.writeExternal(out);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (ObjectInput in = new ObjectInputStream(new FileInputStream(fileName))) {
            loadedObject.readExternal(in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(savedObject.string.equals(loadedObject.string));
    }

    private transient final String pattern = "dd MMMM yyyy, EEEE";
    private transient Date currentDate;
    private transient int temperature;
    String string;

    public Main(int temperature) {
        this.currentDate = new Date();
        this.temperature = temperature;

        string = "Today is %s, and the current temperature is %s C";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        this.string = String.format(string, format.format(currentDate), this.temperature);
    }

    @Override
    public void run() {
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        try {
            out.writeObject(string);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void readExternal(ObjectInput in) {
        try {
            this.string = (String) in.readObject();
        } catch (ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public String toString() {
        return this.string;
    }
}