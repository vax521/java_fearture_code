package thread_synchronization.sync_block_lock;

public class Main {
    public static void main(String[] args) {
        System.out.println("printJob with fairMode false:");
        testPrintJob(false);
        System.out.println("printJob with fairMode true:");
        testPrintJob(true);
    }

    private static void testPrintJob(boolean fairMode){
        PrintQueue printQueue = new PrintQueue(fairMode);
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(new Job(printQueue),"Thread-"+i);
        }
        for (int i = 0; i < 10; i++) {
            threads[i].start();
        }
        for (int i = 0; i < 10; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
