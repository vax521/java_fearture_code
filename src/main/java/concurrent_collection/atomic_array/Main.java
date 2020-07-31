package concurrent_collection.atomic_array;

import java.util.concurrent.atomic.AtomicIntegerArray;

public class Main {
    public static void main(String[] args) {
        final int THREADS = 100;
        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(1000);
        Incrementer incrementer = new Incrementer(atomicIntegerArray);
        Decrementer decrementer = new Decrementer(atomicIntegerArray);
        Thread[] incrThreads = new Thread[THREADS];
        Thread[] decrThreads = new Thread[THREADS];
        for (int i = 0; i < THREADS; i++) {
            incrThreads[i] = new Thread(incrementer);
            decrThreads[i] = new Thread(decrementer);
            incrThreads[i].start();
            decrThreads[i].start();
        }

        for (int i = 0; i < 100; i++) {
            try {
                incrThreads[i].join();
                decrThreads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < atomicIntegerArray.length(); i++) {
                System.out.printf("Vector:%s\n",atomicIntegerArray.get(i));
        }
    }
}
