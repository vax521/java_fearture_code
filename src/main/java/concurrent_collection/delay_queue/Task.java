package concurrent_collection.delay_queue;

import java.util.Date;
import java.util.concurrent.DelayQueue;

public class Task implements Runnable{
    private final int id;

    private final DelayQueue<Event> delayQueue;

    public Task(int id, DelayQueue<Event> delayQueue) {
        this.id = id;
        this.delayQueue = delayQueue;
    }

    @Override
    public void run() {
        Date now = new Date();
        Date delay = new Date();
        delay.setTime(now.getTime()+(id*1000));
        System.out.printf("Thread:%d-%s\n",id,delay);
        for (int i = 0; i < 100; i++) {
            Event event = new Event(delay);
            delayQueue.add(event);
        }
    }
}
