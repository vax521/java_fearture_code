package thread_synchronization.sync_block_condition;

import java.util.concurrent.TimeUnit;

public class Consumer implements Runnable {

    private EventStorage eventStorage;

    public Consumer(EventStorage eventStorage){
        this.eventStorage = eventStorage;
    }

    @Override
    public void run() {
       /* for (int i = 0; i < 100; i++) {
            eventStorage.get();
        }*/
        while (true){
            eventStorage.get();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
