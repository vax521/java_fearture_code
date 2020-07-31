package custom_concurrent_class.priority_executor;

import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(4,4,1, TimeUnit.SECONDS,new PriorityBlockingQueue<>());
        for (int i = 0; i < 10; i++) {
            MyPriorityTask myPriorityTask = new MyPriorityTask(i,"task-"+i);
            executor.execute(myPriorityTask);
        }
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 10; i < 20; i++) {
            MyPriorityTask myPriorityTask = new MyPriorityTask(i,"task-"+i);
            executor.execute(myPriorityTask);
        }

        executor.shutdown();
        try {
            executor.awaitTermination(1,TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
