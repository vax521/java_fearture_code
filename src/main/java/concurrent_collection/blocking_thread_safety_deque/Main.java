package concurrent_collection.blocking_thread_safety_deque;

import util.DateUtil;

import java.util.Date;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        LinkedBlockingDeque<String> deque = new LinkedBlockingDeque<>(3);
        Client client = new Client(deque);
        Thread thread = new Thread(client);
        thread.start();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 3; j++) {
                try {
                    String result = deque.take();
                    System.out.printf("Main:Removed %s at %s Size:%s\n",result, DateUtil.getCurrTime(),deque.size());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
