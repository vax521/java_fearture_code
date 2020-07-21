package concurrent_collection.blocking_thread_safety_deque;

import util.DateUtil;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public class Client implements Runnable {

    public Client(LinkedBlockingDeque<String> requestList) {
        this.requestList = requestList;
    }

    private final LinkedBlockingDeque<String> requestList;

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                StringBuilder request = new StringBuilder();
                request.append(i);
                request.append(":");
                request.append(j);
                try {
                    requestList.put(request.toString());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.printf("Client: add %s at %s\n",request.toString(), DateUtil.getCurrTime());
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("Client ended!");
    }
}
