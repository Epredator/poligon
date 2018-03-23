package pl.etroya.corejava.multithreading;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.*;

public class AdderWithRelationshipBetweenThreadTasks implements Callable<Integer> {private String inFile;

    public AdderWithRelationshipBetweenThreadTasks(String inFile) throws InterruptedException {
        String[] inFiles = {"./fileA.txt, ./fileB.txt, ./fileC.txt, ./fileD.txt, ./fileE.txt"};

        ExecutorService es = Executors.newFixedThreadPool(3);
        Future<Integer>[] results = new Future[inFiles.length];
        for (int i = 0; i < inFiles.length; i++) {
            AdderWithRelationshipBetweenThreadTasks adder = new AdderWithRelationshipBetweenThreadTasks(inFiles[i]);
            adder.doAddWithRelationshipBetweenThreadTasks();
            results[i] = es.submit(adder);
        }

        es.shutdown();
        es.awaitTermination(60, TimeUnit.SECONDS);

    }

    public int doAddWithRelationshipBetweenThreadTasks() {
        int total = 0;
        String line = null;

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(inFile))) {
            while ((line = reader.readLine()) != null) {
                total += Integer.parseInt(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return  total;
    }


    @Override
    public Integer call() throws IOException {
        return doAddWithRelationshipBetweenThreadTasks();
    }
}