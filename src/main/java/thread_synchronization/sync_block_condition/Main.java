package thread_synchronization.sync_block_condition;


public class Main {
    public static void main(String[] args) {
        EventStorage eventStorage = new EventStorage();
        Producer producer = new Producer(eventStorage);
        Consumer consumer = new Consumer(eventStorage);
        Thread t1 = new Thread(producer);
        Thread t2 =  new Thread(consumer);
        t1.start();
        t2.start();
    }
}
