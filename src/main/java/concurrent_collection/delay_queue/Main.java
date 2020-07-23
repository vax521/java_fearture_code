package concurrent_collection.delay_queue;

import util.DateUtil;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        DelayQueue<Event> delayQueue = new DelayQueue<>();
        Thread[] threads = new Thread[5];
        for (int i = 0; i < threads.length; i++) {
            Task task = new Task(i+1,delayQueue);
            threads[i] = new Thread(task);
            threads[i].start();
        }
        for (int i = 0; i < threads.length; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        do{
            int counter = 0;
            Event event;
            do{
                event = delayQueue.poll();
                if(event!=null){
                    counter++;
                }
            }while (event!=null);
            System.out.printf("At %s you have read %d events\n", DateUtil.getCurrTime(),counter);
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }while (delayQueue.size()>0);
    }
}
