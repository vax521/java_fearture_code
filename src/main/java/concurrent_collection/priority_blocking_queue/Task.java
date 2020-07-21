package concurrent_collection.priority_blocking_queue;

import java.util.concurrent.PriorityBlockingQueue;

public class Task implements Runnable {
    private final int id;

    private final PriorityBlockingQueue queue;

    public Task(int id, PriorityBlockingQueue queue) {
        this.id = id;
        this.queue = queue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            Eevent eevent = new Eevent(id,i);
            queue.add(eevent);
        }
    }
}
