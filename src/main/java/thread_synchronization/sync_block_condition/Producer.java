package thread_synchronization.sync_block_condition;

import java.util.concurrent.TimeUnit;

public class Producer implements Runnable {

    private EventStorage eventStorage;

    public Producer(EventStorage eventStorage){
        this.eventStorage = eventStorage;
    }

    @Override
    public void run() {
       /* for (int i = 0; i < 100; i++) {
            eventStorage.set();
        }*/
        while (true){
            eventStorage.set();
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
