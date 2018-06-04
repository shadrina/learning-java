package ru.nsu.shadrina.emulator.threadpool;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyThreadPool {
    private static final int N_THREADS = 10;

    public static void createThreadPool(List<Runnable> runnablz) {
        ExecutorService executor = Executors.newFixedThreadPool(N_THREADS);
        for (Runnable runnable: runnablz) {
            executor.execute(runnable);
        }
        executor.shutdown();
        while (!executor.isTerminated()) {}
        System.out.println("Finished all threads");
    }
}
