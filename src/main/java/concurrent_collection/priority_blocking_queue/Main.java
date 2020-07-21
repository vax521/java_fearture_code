package concurrent_collection.priority_blocking_queue;

import thread_basic.daemonThread.Event;

import java.util.concurrent.PriorityBlockingQueue;

public class Main {
    public static void main(String[] args) {
        PriorityBlockingQueue<Eevent> queue = new PriorityBlockingQueue<>();
        Thread[] threads = new Thread[5];
        for (int i = 0; i < threads.length; i++) {
            Task task = new Task(i,queue);
            threads[i] = new Thread(task);
            threads[i].start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.printf("Main:queue size:%d\n",queue.size());
        for (int i = 0; i < threads.length*1000; i++) {
            Eevent eevent = queue.poll();
            System.out.printf("Thread %s: Priority:%s\n",eevent.getThreadId(),eevent.getPriority());
        }
        System.out.printf("Main:queue size:%d\n",queue.size());
    }
}
