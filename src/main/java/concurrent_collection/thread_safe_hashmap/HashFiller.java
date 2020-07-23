package concurrent_collection.thread_safe_hashmap;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

public class HashFiller implements Runnable {
    private ConcurrentHashMap<String, ConcurrentLinkedDeque<Opreation>> userHash;

    public HashFiller(ConcurrentHashMap<String, ConcurrentLinkedDeque<Opreation>> userHash) {
        this.userHash = userHash;
    }

    @Override
    public void run() {
        Random random = new Random();

        for (int i = 0; i < 100; i++) {
            Opreation opreation = new Opreation();
            opreation.setUser("User-"+random.nextInt(100));
            opreation.setOpreation("Opreation-"+random.nextInt(10));
            opreation.setDate(new Date());
            addOperationToHash(userHash,opreation);
        }
    }

    public void addOperationToHash(ConcurrentHashMap<String,ConcurrentLinkedDeque<Opreation>> userHash, Opreation opreation){
        ConcurrentLinkedDeque<Opreation> opreations = userHash.computeIfAbsent(opreation.getUser(),user->new ConcurrentLinkedDeque<>());
        opreations.add(opreation);
    }
}
