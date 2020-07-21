package concurrent_collection.non_blocking_thread_safety_deque;

import java.util.concurrent.ConcurrentLinkedDeque;

public class Main {
    public static void main(String[] args) {
        ConcurrentLinkedDeque<String> linkedDeque = new ConcurrentLinkedDeque<>();
        Thread[] threads = new Thread[100];
        for (int i = 0; i < threads.length; i++) {
            AddTask addTask = new AddTask(linkedDeque);
            threads[i]  = new Thread(addTask);
            threads[i].start();
        }
        System.out.printf("Main:%d threads have been launched!\n",threads.length);
        for (int i = 0; i < threads.length; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < threads.length; i++) {
            PollTask pollTask = new PollTask(linkedDeque);
            threads[i]  = new Thread(pollTask);
            threads[i].start();
        }
        System.out.printf("Main:%d PollTask have been launched!\n",threads.length);
        for (int i = 0; i < threads.length; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
