package thread_basic.threadLocalVariable.safeCase;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class SafeTask implements Runnable {

    private static ThreadLocal<Date> startDate = ThreadLocal.withInitial(Date::new);

    @Override
    public void run() {
        System.out.printf("Start thread %s at %s\n",Thread.currentThread().getId(),startDate.get());
        try {
            TimeUnit.SECONDS.sleep((int)Math.rint(Math.random()*10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("Finish thread %s at %s\n",Thread.currentThread().getId(),startDate.get());
    }
}
