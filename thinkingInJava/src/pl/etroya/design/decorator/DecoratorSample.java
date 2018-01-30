package pl.etroya.design.decorator;

import java.io.*;

public class DecoratorSample {
    public static void main(String args[]) throws IOException {
        File file = new File("./output.txt");
        file.createNewFile();

        OutputStream outputStream = new FileOutputStream(file);
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        dataOutputStream.writeChars("text");

        dataOutputStream.close();
        outputStream.close();
    }
}
