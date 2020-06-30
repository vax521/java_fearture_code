package thread_synchronization.sync_read_write_block;


public class Main {
    public static void main(String[] args) {
        PriceInfo priceInfo  = new PriceInfo();
        PriceReader[] readers = new PriceReader[5];
        Thread[]  readThreads = new Thread[5];
        for (int i = 0; i < 5; i++) {
            readers[i] = new PriceReader(priceInfo);
            readThreads[i] = new Thread(readers[i]);
        }
        PriceWriter priceWriter = new PriceWriter(priceInfo);
        Thread writeThread = new Thread(priceWriter);
        for (int i = 0; i < 5; i++) {
            readThreads[i].start();
        }
        writeThread.start();
    }
}
