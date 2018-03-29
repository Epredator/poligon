package pl.etroya.corejava.multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TxWorkerDispatcher {

    ExecutorService es = Executors.newFixedThreadPool(5);
    TxWorker[] workers = new TxWorker[10];







}
