package thread_synchronization.sync_block_lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrintQueue {

    private Lock queueLock;

    public PrintQueue(boolean fairMode){
        queueLock = new ReentrantLock(fairMode);
    }

    public void printJob(Object document){
        queueLock.lock();
        long duration = (long)(Math.random()*10000);
        System.out.printf("%s PrintQueue: Print a Job during %s seconds\n",Thread.currentThread().getName(),duration/1000);
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            queueLock.unlock();
        }
    }
}
