package concurrent_collection.atomic_array;

import java.util.concurrent.atomic.AtomicIntegerArray;

public class Decrementer implements Runnable {
    private AtomicIntegerArray atomicIntegerArray;

    public Decrementer(AtomicIntegerArray atomicIntegerArray) {
        this.atomicIntegerArray = atomicIntegerArray;
    }

    @Override
    public void run() {
        for (int i = 0; i < atomicIntegerArray.length(); i++) {
            atomicIntegerArray.getAndDecrement(i);
        }
    }
}
