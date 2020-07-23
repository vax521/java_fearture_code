package concurrent_collection.thread_safe_navigable_map;

import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

public class Main {
    public static void main(String[] args) {
        ConcurrentSkipListMap<String,Contact> map = new ConcurrentSkipListMap<>();
        Thread[] threads = new Thread[26];
        int counter = 0;
        for (char i = 'A'; i <= 'Z'; i++) {
            Task task = new Task(map,String.valueOf(i));
            threads[counter] = new Thread(task);
            threads[counter].start();
            counter++;
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.printf("Main: size of the map %d\n",map.size());
        Map.Entry<String,Contact> entry = map.firstEntry();
        Contact contact = entry.getValue();
        System.out.printf("Main:First Entry %s:%s\n",contact.getName(),contact.getPhone());

    }
}
