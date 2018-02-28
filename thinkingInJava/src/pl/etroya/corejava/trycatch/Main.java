package pl.etroya.corejava.trycatch;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

public class Main {

    public static void main(String[] args){
        //doTryCatchFinally();
        //doTryWithResources();
        //doTryWithResourcesMulti();
        doCloseThing();
    }
    public static void doTryCatchFinally(){
        char[] buff = new char[8];
        int length;
        Reader reader = null;
        try{
            reader = Helper.openReader("file.txt");
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

    public static void doTryWithResources(){
        char[] buff = new char[8];
        int length;
        try(Reader reader = Helper.openReader("file.txt")){

            while ((length = reader.read(buff)) >= 0){
                System.out.println("\n length: " + length);
                for (int i=0; i<length; i++)
                    System.out.print(buff[i]);
            }
        } catch (IOException e) {
            System.out.println(e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }

    public static void doTryWithResourcesMulti(){
        char[] buff = new char[8];
        int length;

        try {
            try(Reader reader = Helper.openReader("file.txt")){
                Writer writer = Helper.openWriter("file2.txt");
                while ((length = reader.read(buff)) >= 0){
                    System.out.println("\n length: " + length);
                    writer.write(buff, 0, length);
                    for(int i=0; i<length; i++){
                        System.out.print(buff[i]);
                    }
                }

            }
        } catch (IOException e) {
            System.out.println(e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }

    private static void doCloseThing() {
        try( MyAutoClose ac = new MyAutoClose()) {
            ac.saySomething();
        } catch (IOException e1) {
            System.out.println(e1.getClass().getSimpleName() + " - " + e1.getMessage());
        } catch (Exception e2) {
            System.out.println(e2.getClass().getSimpleName() + " - " + e2.getMessage());
        }
    }
}
