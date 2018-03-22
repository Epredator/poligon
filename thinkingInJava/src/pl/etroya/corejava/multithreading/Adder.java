package pl.etroya.corejava.multithreading;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Adder implements Runnable {
    private String inFile, outFile;

    public Adder(String inFile, String outFile) throws InterruptedException {
        String[] inFiles = {"./fileA.txt, ./fileB.txt, ./fileC.txt, ./fileD.txt, ./fileE.txt"};
        String[] outFiles = {"./fileA.out.txt, ./fileB.out.txt, ./fileC.out.txt, ./fileD.out.txt, ./fileE.out.txt"};

        Thread[] threads = new Thread[inFiles.length];
        for (int i = 0; i < inFiles.length; i++) {
            Adder adder = new Adder(inFiles[i], outFiles[i]);
            adder.doAdd();
            threads[i] = new Thread(adder);
            threads[i].start();
        }

        for(Thread t :threads){
            t.join(); //block waiting for thread completion
        }

    }

    public void doAdd() {
        int total = 0;
        String line = null;

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(inFile))) {
            while ((line = reader.readLine()) != null) {
                total += Integer.parseInt(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(outFile))) {
            writer.write("Total: " + total);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void run() {
        doAdd();
    }
}
