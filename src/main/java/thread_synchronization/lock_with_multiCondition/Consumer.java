package thread_synchronization.lock_with_multiCondition;

import java.util.Random;

public class Consumer implements Runnable {
    private Buffer buffer;

    public Consumer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while(buffer.hasPendingLines()){
            String line = buffer.get();
            System.out.println("line:"+line);
            processLine(line);
        }
    }

    private void processLine(String line){
        Random random = new Random();
        try {
            Thread.sleep(random.nextInt(5));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
