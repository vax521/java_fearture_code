package thread_synchronization.lock_with_multiCondition;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        FileMock fileMock = new FileMock(100,10);
        Buffer buffer = new Buffer(20);
        Producer producer = new Producer(fileMock,buffer);
        Thread produceThread = new Thread(producer);
        Consumer[] consumers = new Consumer[3];
        Thread[] consumeThreads = new Thread[3];
        for (int i = 0; i < 3; i++) {
            consumers[i] = new Consumer(buffer);
            consumeThreads[i] = new Thread(consumers[i],"Consumers-"+i);
        }
        produceThread.start();
        for (int i = 0; i < 3; i++) {
            consumeThreads[i].start();
        }
    }
}
