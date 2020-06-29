package thread_basic.threadGroup;

import java.util.Random;

public class ErrorTask implements Runnable {
    @Override
    public void run() {
        int result;
        Random random = new Random(Thread.currentThread().getId());
        while (true){
            result = 1000/(int)(random.nextDouble()*1000000);
            if(Thread.currentThread().isInterrupted()){
                System.out.printf("Thread %s: interrupted!\n",Thread.currentThread().getName());
                return;
            }
        }
    }
}
