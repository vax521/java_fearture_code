package thread_synchronization.sync_block_condition;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;

public class EventStorage {
    private int maxSize;

    private Queue<String> storage;

    public EventStorage(){
        maxSize = 10;
        storage = new LinkedList<>();
    }

    public synchronized void set(){
        while(storage.size()==maxSize){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        SimpleDateFormat sf = new SimpleDateFormat("hh:mm:ss");
        storage.offer(sf.format(new Date()));
        System.out.printf("Set:%d\n", storage.size());
        System.out.println(storage);
        notify();
    }

    public synchronized void get(){
        while (storage.size()==0){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String elem = storage.poll();
        System.out.printf("Get:%d :%s\n",storage.size(),elem);
        notify();
    }
}
