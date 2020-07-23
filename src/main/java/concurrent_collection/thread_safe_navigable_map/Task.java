package concurrent_collection.thread_safe_navigable_map;

import java.util.concurrent.ConcurrentSkipListMap;

public class Task implements Runnable {
    private final ConcurrentSkipListMap<String,Contact> skipListMap;
    private final String id;

    public Task(ConcurrentSkipListMap<String, Contact> skipListMap, String id) {
        this.skipListMap = skipListMap;
        this.id = id;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            Contact contact = new Contact(id,String.valueOf(i+1000));
            skipListMap.put(id+contact.getPhone(),contact);
        }
    }
}
