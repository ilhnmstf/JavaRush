import java.io.*;

/* 
Переопределение сериализации в потоке
*/

public class Solution implements Serializable, AutoCloseable {
    private transient FileOutputStream stream;
    private String fileName;

    public Solution(String fileName) throws FileNotFoundException {
        this.stream = new FileOutputStream(fileName);
        this.fileName = fileName;
    }

    public void writeObject(String string) throws IOException {
        stream.write(string.getBytes());
        stream.write("\n".getBytes());
        stream.flush();
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.stream = new FileOutputStream(fileName, true);
    }

    @Override
    public void close() throws Exception {
        System.out.println("Closing everything!");
        stream.close();
    }

    public static void main(String[] args) {
        String fileName = "level_10/task2022/src/file.ser";
        try (ObjectOutput out = new ObjectOutputStream(new FileOutputStream(fileName));
             ObjectInput in = new ObjectInputStream(new FileInputStream(fileName))
        ) {
            Solution solution = new Solution(fileName);
            solution.writeObject("some date");

            out.writeObject(solution);
            out.flush();

            Solution loader = (Solution) in.readObject();
            loader.writeObject("another date");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}