package JavaConcept.ThreadingConcepts.InterThreadCommunication;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

class PipedReaderClass implements Runnable {

    String name;
    PipedReader reader;

    public PipedReaderClass(String name ,  PipedReader reader) {
        this.name = name;
        this.reader = reader;
    }

    @Override
    public void run() {
        while (true) {
            try {
                char c = (char)reader.read();
                if(c!= -1)
                    System.out.print(c);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

class PipedWriterClass implements Runnable {

    String name;
    PipedWriter writer;

    public PipedWriterClass(String name ,  PipedWriter writer) {
        this.name = name;
        this.writer = writer;
    }

    @Override
    public void run() {
        while (true) {
            try {
                writer.write("This is for demo purpose\n");
                writer.flush();
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

public class PipedReaderWriterDemo {
    public static void main(String[] args) throws IOException {
        PipedReader reader = new PipedReader();
        PipedWriter writer = new PipedWriter();
        writer.connect(reader);

        new Thread(new PipedReaderClass("reader" , reader)).start();
        new Thread(new PipedWriterClass("writer" , writer)).start();
    }
}
