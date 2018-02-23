package pl.etroya.corejava.trycatch;

import java.io.IOException;
import java.io.Reader;

public class Main {
    public static void doTryCatchFinally(){
        char[] buff = new char[8];
        int length;
        Reader reader = null;
        try{
            reader = Helper.openReader("sampleText.txt");
            while ((length = reader.read(buff)) >= 0){
                System.out.println("\nlength: " + length);
                for(int i=0; i<length; i++)
                    System.out.println(buff[i]);
            }
        } catch (IOException e) {
            System.out.println(e.getClass().getSimpleName() + " - " + e.getMessage());
        } finally {
            try {
                if(reader != null){
                    reader.close();
                }
            } catch (IOException e2) {
                System.out.println(e2.getClass().getSimpleName() + " - " + e2.getMessage());
            }
        }
    }
}
