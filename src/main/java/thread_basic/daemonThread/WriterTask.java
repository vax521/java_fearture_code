package thread_basic.daemonThread;

import java.util.Date;
import java.util.Deque;
import java.util.concurrent.TimeUnit;

public class WriterTask implements Runnable{
    private Deque<Event> deque;

    public WriterTask(Deque<Event> deque) {
        this.deque = deque;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            Event event = new Event();
            event.setDate(new Date());
            event.setEvent(String.format("Thread %d generate a event!",Thread.currentThread().getId()));
            deque.addFirst(event);
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
