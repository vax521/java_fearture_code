package thread_basic.daemonThread;

import java.util.Deque;
import java.util.concurrent.ConcurrentLinkedDeque;

public class Main {
    public static void main(String[] args) {
        Deque<Event> deque = new ConcurrentLinkedDeque<>();
        WriterTask writerTask = new WriterTask(deque);
        for (int i = 0; i < Runtime.getRuntime().availableProcessors(); i++) {
            Thread thread = new Thread(writerTask);
            thread.start();
        }
         CleanerTask cleanerTask = new CleanerTask(deque);
        cleanerTask.start();
    }

}

